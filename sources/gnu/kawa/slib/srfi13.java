package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.view.InputDeviceCompat;
import android.support.p000v4.view.PointerIconCompat;
import android.support.p003v7.widget.helper.ItemTouchHelper;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.BitwiseOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.Invoke;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.Telnet;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.C1245lists;
import kawa.lib.characters;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;
import org.shaded.apache.http.HttpStatus;

/* compiled from: srfi13.scm */
public class srfi13 extends ModuleBody {
    public static final ModuleMethod $Pccheck$Mnbounds;
    public static final ModuleMethod $Pcfinish$Mnstring$Mnconcatenate$Mnreverse;
    public static final ModuleMethod $Pckmp$Mnsearch;
    public static final ModuleMethod $Pcmultispan$Mnrepcopy$Ex;
    public static final ModuleMethod $Pcstring$Mncompare;
    public static final ModuleMethod $Pcstring$Mncompare$Mnci;
    public static final ModuleMethod $Pcstring$Mncopy$Ex;
    public static final ModuleMethod $Pcstring$Mnhash;
    public static final ModuleMethod $Pcstring$Mnmap;
    public static final ModuleMethod $Pcstring$Mnmap$Ex;
    public static final ModuleMethod $Pcstring$Mnprefix$Mnci$Qu;
    public static final ModuleMethod $Pcstring$Mnprefix$Mnlength;
    public static final ModuleMethod $Pcstring$Mnprefix$Mnlength$Mnci;
    public static final ModuleMethod $Pcstring$Mnprefix$Qu;
    public static final ModuleMethod $Pcstring$Mnsuffix$Mnci$Qu;
    public static final ModuleMethod $Pcstring$Mnsuffix$Mnlength;
    public static final ModuleMethod $Pcstring$Mnsuffix$Mnlength$Mnci;
    public static final ModuleMethod $Pcstring$Mnsuffix$Qu;
    public static final ModuleMethod $Pcstring$Mntitlecase$Ex;
    public static final ModuleMethod $Pcsubstring$Slshared;
    public static final srfi13 $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final IntNum Lit10 = IntNum.make(4194304);
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit105;
    static final SimpleSymbol Lit106;
    static final SimpleSymbol Lit107;
    static final SimpleSymbol Lit108;
    static final SimpleSymbol Lit109;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit110;
    static final SimpleSymbol Lit111;
    static final SimpleSymbol Lit112;
    static final SimpleSymbol Lit113;
    static final SimpleSymbol Lit114;
    static final SimpleSymbol Lit115;
    static final SimpleSymbol Lit116;
    static final SimpleSymbol Lit117;
    static final SimpleSymbol Lit118;
    static final SimpleSymbol Lit119;
    static final Char Lit12 = Char.make(32);
    static final SimpleSymbol Lit120;
    static final SimpleSymbol Lit121;
    static final SimpleSymbol Lit122;
    static final SimpleSymbol Lit123;
    static final SimpleSymbol Lit124;
    static final SimpleSymbol Lit125;
    static final SimpleSymbol Lit126;
    static final SimpleSymbol Lit127;
    static final SimpleSymbol Lit128;
    static final SimpleSymbol Lit129;
    static final IntNum Lit13 = IntNum.make(-1);
    static final SimpleSymbol Lit130;
    static final SimpleSymbol Lit131;
    static final SimpleSymbol Lit132;
    static final SimpleSymbol Lit133;
    static final SimpleSymbol Lit134;
    static final SimpleSymbol Lit135;
    static final SimpleSymbol Lit136;
    static final SimpleSymbol Lit137;
    static final SimpleSymbol Lit138;
    static final SimpleSymbol Lit139;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit140;
    static final SimpleSymbol Lit141;
    static final SimpleSymbol Lit142;
    static final SimpleSymbol Lit143;
    static final SimpleSymbol Lit144;
    static final SimpleSymbol Lit145;
    static final SimpleSymbol Lit146;
    static final SimpleSymbol Lit147;
    static final SimpleSymbol Lit148;
    static final SimpleSymbol Lit149;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit150;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final IntNum Lit2 = IntNum.make(4096);
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final IntNum Lit3 = IntNum.make(40);
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final IntNum Lit4 = IntNum.make(4096);
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SyntaxRules Lit42;
    static final SimpleSymbol Lit43;
    static final SyntaxRules Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final IntNum Lit5 = IntNum.make(65536);
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit59;
    static final IntNum Lit6 = IntNum.make(37);
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final SimpleSymbol Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final IntNum Lit7 = IntNum.make(4194304);
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final SimpleSymbol Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit79;
    static final IntNum Lit8 = IntNum.make(4194304);
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final SimpleSymbol Lit82;
    static final SimpleSymbol Lit83;
    static final SimpleSymbol Lit84;
    static final SimpleSymbol Lit85;
    static final SimpleSymbol Lit86;
    static final SimpleSymbol Lit87;
    static final SimpleSymbol Lit88;
    static final SimpleSymbol Lit89;
    static final IntNum Lit9 = IntNum.make(4194304);
    static final SimpleSymbol Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SimpleSymbol Lit93;
    static final SimpleSymbol Lit94;
    static final SimpleSymbol Lit95;
    static final SimpleSymbol Lit96;
    static final SimpleSymbol Lit97;
    static final SimpleSymbol Lit98;
    static final SimpleSymbol Lit99;
    public static final ModuleMethod check$Mnsubstring$Mnspec;
    public static final ModuleMethod kmp$Mnstep;
    static final ModuleMethod lambda$Fn100;
    static final ModuleMethod lambda$Fn105;
    static final ModuleMethod lambda$Fn106;
    static final ModuleMethod lambda$Fn111;
    static final ModuleMethod lambda$Fn116;
    static final ModuleMethod lambda$Fn117;
    static final ModuleMethod lambda$Fn122;
    static final ModuleMethod lambda$Fn123;
    static final ModuleMethod lambda$Fn128;
    static final ModuleMethod lambda$Fn133;
    static final ModuleMethod lambda$Fn138;
    static final ModuleMethod lambda$Fn163;
    static final ModuleMethod lambda$Fn166;
    static final ModuleMethod lambda$Fn17;
    static final ModuleMethod lambda$Fn18;
    static final ModuleMethod lambda$Fn210;
    static final ModuleMethod lambda$Fn216;
    static final ModuleMethod lambda$Fn220;
    static final ModuleMethod lambda$Fn27;
    static final ModuleMethod lambda$Fn5;
    static final ModuleMethod lambda$Fn72;
    static final ModuleMethod lambda$Fn73;
    static final ModuleMethod lambda$Fn78;
    static final ModuleMethod lambda$Fn83;
    static final ModuleMethod lambda$Fn84;
    static final ModuleMethod lambda$Fn89;
    static final ModuleMethod lambda$Fn90;
    static final ModuleMethod lambda$Fn95;
    public static final Macro let$Mnstring$Mnstart$Plend = Macro.make(Lit41, Lit42, $instance);
    public static final Macro let$Mnstring$Mnstart$Plend2 = Macro.make(Lit43, Lit44, $instance);
    static final Location loc$$Cloptional = ThreadLocation.getInstance(Lit20, (Object) null);
    static final Location loc$base = ThreadLocation.getInstance(Lit22, (Object) null);
    static final Location loc$bound = ThreadLocation.getInstance(Lit26, (Object) null);
    static final Location loc$c$Eq = ThreadLocation.getInstance(Lit31, (Object) null);
    static final Location loc$char$Mncased$Qu = ThreadLocation.getInstance(Lit28, (Object) null);
    static final Location loc$char$Mnset = ThreadLocation.getInstance(Lit30, (Object) null);
    static final Location loc$char$Mnset$Mncontains$Qu = ThreadLocation.getInstance(Lit25, (Object) null);
    static final Location loc$char$Mnset$Qu = ThreadLocation.getInstance(Lit24, (Object) null);
    static final Location loc$check$Mnarg = ThreadLocation.getInstance(Lit19, (Object) null);
    static final Location loc$criterion = ThreadLocation.getInstance(Lit29, (Object) null);
    static final Location loc$delim = ThreadLocation.getInstance(Lit39, (Object) null);
    static final Location loc$end = ThreadLocation.getInstance(Lit33, (Object) null);
    static final Location loc$final = ThreadLocation.getInstance(Lit37, (Object) null);
    static final Location loc$grammar = ThreadLocation.getInstance(Lit40, (Object) null);
    static final Location loc$let$Mnoptionals$St = ThreadLocation.getInstance(Lit21, (Object) null);
    static final Location loc$make$Mnfinal = ThreadLocation.getInstance(Lit23, (Object) null);
    static final Location loc$p$Mnstart = ThreadLocation.getInstance(Lit34, (Object) null);
    static final Location loc$rest = ThreadLocation.getInstance(Lit27, (Object) null);
    static final Location loc$s$Mnend = ThreadLocation.getInstance(Lit36, (Object) null);
    static final Location loc$s$Mnstart = ThreadLocation.getInstance(Lit35, (Object) null);
    static final Location loc$start = ThreadLocation.getInstance(Lit32, (Object) null);
    static final Location loc$token$Mnchars = ThreadLocation.getInstance(Lit38, (Object) null);
    public static final ModuleMethod make$Mnkmp$Mnrestart$Mnvector;
    public static final ModuleMethod reverse$Mnlist$Mn$Grstring;
    public static final ModuleMethod string$Eq;
    public static final ModuleMethod string$Gr;
    public static final ModuleMethod string$Gr$Eq;
    public static final ModuleMethod string$Ls;
    public static final ModuleMethod string$Ls$Eq;
    public static final ModuleMethod string$Ls$Gr;
    public static final ModuleMethod string$Mn$Grlist;
    public static final ModuleMethod string$Mnany;
    public static final ModuleMethod string$Mnappend$Slshared;
    public static final ModuleMethod string$Mnci$Eq;
    public static final ModuleMethod string$Mnci$Gr;
    public static final ModuleMethod string$Mnci$Gr$Eq;
    public static final ModuleMethod string$Mnci$Ls;
    public static final ModuleMethod string$Mnci$Ls$Eq;
    public static final ModuleMethod string$Mnci$Ls$Gr;
    public static final ModuleMethod string$Mncompare;
    public static final ModuleMethod string$Mncompare$Mnci;
    public static final ModuleMethod string$Mnconcatenate;
    public static final ModuleMethod string$Mnconcatenate$Mnreverse;
    public static final ModuleMethod string$Mnconcatenate$Mnreverse$Slshared;
    public static final ModuleMethod string$Mnconcatenate$Slshared;
    public static final ModuleMethod string$Mncontains;
    public static final ModuleMethod string$Mncontains$Mnci;
    public static final ModuleMethod string$Mncopy;
    public static final ModuleMethod string$Mncopy$Ex;
    public static final ModuleMethod string$Mncount;
    public static final ModuleMethod string$Mndelete;
    public static final ModuleMethod string$Mndowncase;
    public static final ModuleMethod string$Mndowncase$Ex;
    public static final ModuleMethod string$Mndrop;
    public static final ModuleMethod string$Mndrop$Mnright;
    public static final ModuleMethod string$Mnevery;
    public static final ModuleMethod string$Mnfill$Ex;
    public static final ModuleMethod string$Mnfilter;
    public static final ModuleMethod string$Mnfold;
    public static final ModuleMethod string$Mnfold$Mnright;
    public static final ModuleMethod string$Mnfor$Mneach;
    public static final ModuleMethod string$Mnfor$Mneach$Mnindex;
    public static final ModuleMethod string$Mnhash;
    public static final ModuleMethod string$Mnhash$Mnci;
    public static final ModuleMethod string$Mnindex;
    public static final ModuleMethod string$Mnindex$Mnright;
    public static final ModuleMethod string$Mnjoin;
    public static final ModuleMethod string$Mnkmp$Mnpartial$Mnsearch;
    public static final ModuleMethod string$Mnmap;
    public static final ModuleMethod string$Mnmap$Ex;
    public static final ModuleMethod string$Mnnull$Qu;
    public static final ModuleMethod string$Mnpad;
    public static final ModuleMethod string$Mnpad$Mnright;
    public static final ModuleMethod string$Mnparse$Mnfinal$Mnstart$Plend;
    public static final ModuleMethod string$Mnparse$Mnstart$Plend;
    public static final ModuleMethod string$Mnprefix$Mnci$Qu;
    public static final ModuleMethod string$Mnprefix$Mnlength;
    public static final ModuleMethod string$Mnprefix$Mnlength$Mnci;
    public static final ModuleMethod string$Mnprefix$Qu;
    public static final ModuleMethod string$Mnreplace;
    public static final ModuleMethod string$Mnreverse;
    public static final ModuleMethod string$Mnreverse$Ex;
    public static final ModuleMethod string$Mnskip;
    public static final ModuleMethod string$Mnskip$Mnright;
    public static final ModuleMethod string$Mnsuffix$Mnci$Qu;
    public static final ModuleMethod string$Mnsuffix$Mnlength;
    public static final ModuleMethod string$Mnsuffix$Mnlength$Mnci;
    public static final ModuleMethod string$Mnsuffix$Qu;
    public static final ModuleMethod string$Mntabulate;
    public static final ModuleMethod string$Mntake;
    public static final ModuleMethod string$Mntake$Mnright;
    public static final ModuleMethod string$Mntitlecase;
    public static final ModuleMethod string$Mntitlecase$Ex;
    public static final ModuleMethod string$Mntokenize;
    public static final ModuleMethod string$Mntrim;
    public static final ModuleMethod string$Mntrim$Mnboth;
    public static final ModuleMethod string$Mntrim$Mnright;
    public static final ModuleMethod string$Mnunfold;
    public static final ModuleMethod string$Mnunfold$Mnright;
    public static final ModuleMethod string$Mnupcase;
    public static final ModuleMethod string$Mnupcase$Ex;
    public static final ModuleMethod string$Mnxcopy$Ex;
    public static final ModuleMethod substring$Mnspec$Mnok$Qu;
    public static final ModuleMethod substring$Slshared;
    public static final ModuleMethod xsubstring;

    /* compiled from: srfi13.scm */
    public class frame55 extends ModuleBody {
        Object char$Mn$Grint;

        public frame55() {
        }
    }

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        SimpleSymbol simpleSymbol9;
        SimpleSymbol simpleSymbol10;
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        SimpleSymbol simpleSymbol24;
        SimpleSymbol simpleSymbol25;
        SimpleSymbol simpleSymbol26;
        SimpleSymbol simpleSymbol27;
        SimpleSymbol simpleSymbol28;
        SimpleSymbol simpleSymbol29;
        SimpleSymbol simpleSymbol30;
        SimpleSymbol simpleSymbol31;
        SimpleSymbol simpleSymbol32;
        SimpleSymbol simpleSymbol33;
        SimpleSymbol simpleSymbol34;
        SimpleSymbol simpleSymbol35;
        SimpleSymbol simpleSymbol36;
        SimpleSymbol simpleSymbol37;
        SimpleSymbol simpleSymbol38;
        SimpleSymbol simpleSymbol39;
        SimpleSymbol simpleSymbol40;
        SimpleSymbol simpleSymbol41;
        SimpleSymbol simpleSymbol42;
        SimpleSymbol simpleSymbol43;
        SimpleSymbol simpleSymbol44;
        SimpleSymbol simpleSymbol45;
        SimpleSymbol simpleSymbol46;
        SimpleSymbol simpleSymbol47;
        SimpleSymbol simpleSymbol48;
        SimpleSymbol simpleSymbol49;
        SimpleSymbol simpleSymbol50;
        SimpleSymbol simpleSymbol51;
        SimpleSymbol simpleSymbol52;
        SimpleSymbol simpleSymbol53;
        SimpleSymbol simpleSymbol54;
        SimpleSymbol simpleSymbol55;
        SimpleSymbol simpleSymbol56;
        SimpleSymbol simpleSymbol57;
        SimpleSymbol simpleSymbol58;
        SimpleSymbol simpleSymbol59;
        SimpleSymbol simpleSymbol60;
        SimpleSymbol simpleSymbol61;
        SimpleSymbol simpleSymbol62;
        SimpleSymbol simpleSymbol63;
        SimpleSymbol simpleSymbol64;
        SimpleSymbol simpleSymbol65;
        SimpleSymbol simpleSymbol66;
        SimpleSymbol simpleSymbol67;
        SimpleSymbol simpleSymbol68;
        SimpleSymbol simpleSymbol69;
        SimpleSymbol simpleSymbol70;
        SimpleSymbol simpleSymbol71;
        SimpleSymbol simpleSymbol72;
        SimpleSymbol simpleSymbol73;
        SimpleSymbol simpleSymbol74;
        SimpleSymbol simpleSymbol75;
        SimpleSymbol simpleSymbol76;
        SimpleSymbol simpleSymbol77;
        SimpleSymbol simpleSymbol78;
        SimpleSymbol simpleSymbol79;
        SimpleSymbol simpleSymbol80;
        SimpleSymbol simpleSymbol81;
        SimpleSymbol simpleSymbol82;
        SimpleSymbol simpleSymbol83;
        SimpleSymbol simpleSymbol84;
        SimpleSymbol simpleSymbol85;
        SimpleSymbol simpleSymbol86;
        SimpleSymbol simpleSymbol87;
        SimpleSymbol simpleSymbol88;
        SimpleSymbol simpleSymbol89;
        SimpleSymbol simpleSymbol90;
        SimpleSymbol simpleSymbol91;
        SimpleSymbol simpleSymbol92;
        SimpleSymbol simpleSymbol93;
        SimpleSymbol simpleSymbol94;
        SimpleSymbol simpleSymbol95;
        SimpleSymbol simpleSymbol96;
        SimpleSymbol simpleSymbol97;
        SimpleSymbol simpleSymbol98;
        SimpleSymbol simpleSymbol99;
        SimpleSymbol simpleSymbol100;
        SimpleSymbol simpleSymbol101;
        SimpleSymbol simpleSymbol102;
        SimpleSymbol simpleSymbol103;
        SimpleSymbol simpleSymbol104;
        SimpleSymbol simpleSymbol105;
        SimpleSymbol simpleSymbol106;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol107;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol108;
        SimpleSymbol simpleSymbol109;
        SimpleSymbol simpleSymbol110;
        SimpleSymbol simpleSymbol111;
        SimpleSymbol simpleSymbol112;
        SyntaxRules syntaxRules2;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol113;
        SimpleSymbol simpleSymbol114;
        SimpleSymbol simpleSymbol115;
        SimpleSymbol simpleSymbol116;
        SimpleSymbol simpleSymbol117;
        SimpleSymbol simpleSymbol118;
        SimpleSymbol simpleSymbol119;
        SimpleSymbol simpleSymbol120;
        SimpleSymbol simpleSymbol121;
        SimpleSymbol simpleSymbol122;
        SimpleSymbol simpleSymbol123;
        SimpleSymbol simpleSymbol124;
        SimpleSymbol simpleSymbol125;
        SimpleSymbol simpleSymbol126;
        SimpleSymbol simpleSymbol127;
        SimpleSymbol simpleSymbol128;
        SimpleSymbol simpleSymbol129;
        SimpleSymbol simpleSymbol130;
        SimpleSymbol simpleSymbol131;
        SimpleSymbol simpleSymbol132;
        SimpleSymbol simpleSymbol133;
        SimpleSymbol simpleSymbol134;
        SimpleSymbol simpleSymbol135;
        SimpleSymbol simpleSymbol136;
        SimpleSymbol simpleSymbol137;
        SimpleSymbol simpleSymbol138;
        SimpleSymbol simpleSymbol139;
        srfi13 srfi13;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        ModuleMethod moduleMethod9;
        ModuleMethod moduleMethod10;
        ModuleMethod moduleMethod11;
        ModuleMethod moduleMethod12;
        ModuleMethod moduleMethod13;
        ModuleMethod moduleMethod14;
        ModuleMethod moduleMethod15;
        ModuleMethod moduleMethod16;
        ModuleMethod moduleMethod17;
        ModuleMethod moduleMethod18;
        ModuleMethod moduleMethod19;
        ModuleMethod moduleMethod20;
        ModuleMethod moduleMethod21;
        ModuleMethod moduleMethod22;
        ModuleMethod moduleMethod23;
        ModuleMethod moduleMethod24;
        ModuleMethod moduleMethod25;
        ModuleMethod moduleMethod26;
        ModuleMethod moduleMethod27;
        ModuleMethod moduleMethod28;
        ModuleMethod moduleMethod29;
        ModuleMethod moduleMethod30;
        ModuleMethod moduleMethod31;
        ModuleMethod moduleMethod32;
        ModuleMethod moduleMethod33;
        ModuleMethod moduleMethod34;
        ModuleMethod moduleMethod35;
        ModuleMethod moduleMethod36;
        ModuleMethod moduleMethod37;
        ModuleMethod moduleMethod38;
        ModuleMethod moduleMethod39;
        ModuleMethod moduleMethod40;
        ModuleMethod moduleMethod41;
        ModuleMethod moduleMethod42;
        ModuleMethod moduleMethod43;
        ModuleMethod moduleMethod44;
        ModuleMethod moduleMethod45;
        ModuleMethod moduleMethod46;
        ModuleMethod moduleMethod47;
        ModuleMethod moduleMethod48;
        ModuleMethod moduleMethod49;
        ModuleMethod moduleMethod50;
        ModuleMethod moduleMethod51;
        ModuleMethod moduleMethod52;
        ModuleMethod moduleMethod53;
        ModuleMethod moduleMethod54;
        ModuleMethod moduleMethod55;
        ModuleMethod moduleMethod56;
        ModuleMethod moduleMethod57;
        ModuleMethod moduleMethod58;
        ModuleMethod moduleMethod59;
        ModuleMethod moduleMethod60;
        ModuleMethod moduleMethod61;
        ModuleMethod moduleMethod62;
        ModuleMethod moduleMethod63;
        ModuleMethod moduleMethod64;
        ModuleMethod moduleMethod65;
        ModuleMethod moduleMethod66;
        ModuleMethod moduleMethod67;
        ModuleMethod moduleMethod68;
        ModuleMethod moduleMethod69;
        ModuleMethod moduleMethod70;
        ModuleMethod moduleMethod71;
        ModuleMethod moduleMethod72;
        ModuleMethod moduleMethod73;
        ModuleMethod moduleMethod74;
        ModuleMethod moduleMethod75;
        ModuleMethod moduleMethod76;
        ModuleMethod moduleMethod77;
        ModuleMethod moduleMethod78;
        ModuleMethod moduleMethod79;
        ModuleMethod moduleMethod80;
        ModuleMethod moduleMethod81;
        ModuleMethod moduleMethod82;
        ModuleMethod moduleMethod83;
        ModuleMethod moduleMethod84;
        ModuleMethod moduleMethod85;
        ModuleMethod moduleMethod86;
        ModuleMethod moduleMethod87;
        ModuleMethod moduleMethod88;
        ModuleMethod moduleMethod89;
        ModuleMethod moduleMethod90;
        ModuleMethod moduleMethod91;
        ModuleMethod moduleMethod92;
        ModuleMethod moduleMethod93;
        ModuleMethod moduleMethod94;
        ModuleMethod moduleMethod95;
        ModuleMethod moduleMethod96;
        ModuleMethod moduleMethod97;
        ModuleMethod moduleMethod98;
        ModuleMethod moduleMethod99;
        ModuleMethod moduleMethod100;
        ModuleMethod moduleMethod101;
        ModuleMethod moduleMethod102;
        ModuleMethod moduleMethod103;
        ModuleMethod moduleMethod104;
        ModuleMethod moduleMethod105;
        ModuleMethod moduleMethod106;
        ModuleMethod moduleMethod107;
        ModuleMethod moduleMethod108;
        ModuleMethod moduleMethod109;
        ModuleMethod moduleMethod110;
        ModuleMethod moduleMethod111;
        ModuleMethod moduleMethod112;
        ModuleMethod moduleMethod113;
        ModuleMethod moduleMethod114;
        ModuleMethod moduleMethod115;
        ModuleMethod moduleMethod116;
        ModuleMethod moduleMethod117;
        ModuleMethod moduleMethod118;
        ModuleMethod moduleMethod119;
        ModuleMethod moduleMethod120;
        ModuleMethod moduleMethod121;
        ModuleMethod moduleMethod122;
        ModuleMethod moduleMethod123;
        ModuleMethod moduleMethod124;
        ModuleMethod moduleMethod125;
        ModuleMethod moduleMethod126;
        ModuleMethod moduleMethod127;
        ModuleMethod moduleMethod128;
        ModuleMethod moduleMethod129;
        ModuleMethod moduleMethod130;
        ModuleMethod moduleMethod131;
        ModuleMethod moduleMethod132;
        ModuleMethod moduleMethod133;
        new SimpleSymbol("receive");
        Lit150 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("string-join");
        Lit149 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("%multispan-repcopy!");
        Lit148 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("string-xcopy!");
        Lit147 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("xsubstring");
        Lit146 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("string-tokenize");
        Lit145 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("string-replace");
        Lit144 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("%finish-string-concatenate-reverse");
        Lit143 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("string-concatenate-reverse/shared");
        Lit142 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("string-concatenate-reverse");
        Lit141 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("string-concatenate");
        Lit140 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("string-concatenate/shared");
        Lit139 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("string-append/shared");
        Lit138 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("string->list");
        Lit137 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("reverse-list->string");
        Lit136 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("string-reverse!");
        Lit135 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("string-reverse");
        Lit134 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("string-null?");
        Lit133 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("string-kmp-partial-search");
        Lit132 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("kmp-step");
        Lit131 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("make-kmp-restart-vector");
        Lit130 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("%kmp-search");
        Lit129 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("string-contains-ci");
        Lit128 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("string-contains");
        Lit127 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("%string-copy!");
        Lit126 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("string-copy!");
        Lit125 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("string-fill!");
        Lit124 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("string-count");
        Lit123 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("string-skip-right");
        Lit122 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("string-skip");
        Lit121 = (SimpleSymbol) simpleSymbol30.readResolve();
        new SimpleSymbol("string-index-right");
        Lit120 = (SimpleSymbol) simpleSymbol31.readResolve();
        new SimpleSymbol("string-index");
        Lit119 = (SimpleSymbol) simpleSymbol32.readResolve();
        new SimpleSymbol("string-filter");
        Lit118 = (SimpleSymbol) simpleSymbol33.readResolve();
        new SimpleSymbol("string-delete");
        Lit117 = (SimpleSymbol) simpleSymbol34.readResolve();
        new SimpleSymbol("string-pad");
        Lit116 = (SimpleSymbol) simpleSymbol35.readResolve();
        new SimpleSymbol("string-pad-right");
        Lit115 = (SimpleSymbol) simpleSymbol36.readResolve();
        new SimpleSymbol("string-trim-both");
        Lit114 = (SimpleSymbol) simpleSymbol37.readResolve();
        new SimpleSymbol("string-trim-right");
        Lit113 = (SimpleSymbol) simpleSymbol38.readResolve();
        new SimpleSymbol("string-trim");
        Lit112 = (SimpleSymbol) simpleSymbol39.readResolve();
        new SimpleSymbol("string-drop-right");
        Lit111 = (SimpleSymbol) simpleSymbol40.readResolve();
        new SimpleSymbol("string-drop");
        Lit110 = (SimpleSymbol) simpleSymbol41.readResolve();
        new SimpleSymbol("string-take-right");
        Lit109 = (SimpleSymbol) simpleSymbol42.readResolve();
        new SimpleSymbol("string-take");
        Lit108 = (SimpleSymbol) simpleSymbol43.readResolve();
        new SimpleSymbol("string-titlecase");
        Lit107 = (SimpleSymbol) simpleSymbol44.readResolve();
        new SimpleSymbol("string-titlecase!");
        Lit106 = (SimpleSymbol) simpleSymbol45.readResolve();
        new SimpleSymbol("%string-titlecase!");
        Lit105 = (SimpleSymbol) simpleSymbol46.readResolve();
        new SimpleSymbol("string-downcase!");
        Lit104 = (SimpleSymbol) simpleSymbol47.readResolve();
        new SimpleSymbol("string-downcase");
        Lit103 = (SimpleSymbol) simpleSymbol48.readResolve();
        new SimpleSymbol("string-upcase!");
        Lit102 = (SimpleSymbol) simpleSymbol49.readResolve();
        new SimpleSymbol("string-upcase");
        Lit101 = (SimpleSymbol) simpleSymbol50.readResolve();
        new SimpleSymbol("string-hash-ci");
        Lit100 = (SimpleSymbol) simpleSymbol51.readResolve();
        new SimpleSymbol("string-hash");
        Lit99 = (SimpleSymbol) simpleSymbol52.readResolve();
        new SimpleSymbol("%string-hash");
        Lit98 = (SimpleSymbol) simpleSymbol53.readResolve();
        new SimpleSymbol("string-ci>=");
        Lit97 = (SimpleSymbol) simpleSymbol54.readResolve();
        new SimpleSymbol("string-ci<=");
        Lit96 = (SimpleSymbol) simpleSymbol55.readResolve();
        new SimpleSymbol("string-ci>");
        Lit95 = (SimpleSymbol) simpleSymbol56.readResolve();
        new SimpleSymbol("string-ci<");
        Lit94 = (SimpleSymbol) simpleSymbol57.readResolve();
        new SimpleSymbol("string-ci<>");
        Lit93 = (SimpleSymbol) simpleSymbol58.readResolve();
        new SimpleSymbol("string-ci=");
        Lit92 = (SimpleSymbol) simpleSymbol59.readResolve();
        new SimpleSymbol("string>=");
        Lit91 = (SimpleSymbol) simpleSymbol60.readResolve();
        new SimpleSymbol("string<=");
        Lit90 = (SimpleSymbol) simpleSymbol61.readResolve();
        new SimpleSymbol("string>");
        Lit89 = (SimpleSymbol) simpleSymbol62.readResolve();
        new SimpleSymbol("string<");
        Lit88 = (SimpleSymbol) simpleSymbol63.readResolve();
        new SimpleSymbol("string<>");
        Lit87 = (SimpleSymbol) simpleSymbol64.readResolve();
        new SimpleSymbol("string=");
        Lit86 = (SimpleSymbol) simpleSymbol65.readResolve();
        new SimpleSymbol("string-compare-ci");
        Lit85 = (SimpleSymbol) simpleSymbol66.readResolve();
        new SimpleSymbol("string-compare");
        Lit84 = (SimpleSymbol) simpleSymbol67.readResolve();
        new SimpleSymbol("%string-compare-ci");
        Lit83 = (SimpleSymbol) simpleSymbol68.readResolve();
        new SimpleSymbol("%string-compare");
        Lit82 = (SimpleSymbol) simpleSymbol69.readResolve();
        new SimpleSymbol("%string-suffix-ci?");
        Lit81 = (SimpleSymbol) simpleSymbol70.readResolve();
        new SimpleSymbol("%string-prefix-ci?");
        Lit80 = (SimpleSymbol) simpleSymbol71.readResolve();
        new SimpleSymbol("%string-suffix?");
        Lit79 = (SimpleSymbol) simpleSymbol72.readResolve();
        new SimpleSymbol("%string-prefix?");
        Lit78 = (SimpleSymbol) simpleSymbol73.readResolve();
        new SimpleSymbol("string-suffix-ci?");
        Lit77 = (SimpleSymbol) simpleSymbol74.readResolve();
        new SimpleSymbol("string-prefix-ci?");
        Lit76 = (SimpleSymbol) simpleSymbol75.readResolve();
        new SimpleSymbol("string-suffix?");
        Lit75 = (SimpleSymbol) simpleSymbol76.readResolve();
        new SimpleSymbol("string-prefix?");
        Lit74 = (SimpleSymbol) simpleSymbol77.readResolve();
        new SimpleSymbol("string-suffix-length-ci");
        Lit73 = (SimpleSymbol) simpleSymbol78.readResolve();
        new SimpleSymbol("string-prefix-length-ci");
        Lit72 = (SimpleSymbol) simpleSymbol79.readResolve();
        new SimpleSymbol("string-suffix-length");
        Lit71 = (SimpleSymbol) simpleSymbol80.readResolve();
        new SimpleSymbol("string-prefix-length");
        Lit70 = (SimpleSymbol) simpleSymbol81.readResolve();
        new SimpleSymbol("%string-suffix-length-ci");
        Lit69 = (SimpleSymbol) simpleSymbol82.readResolve();
        new SimpleSymbol("%string-prefix-length-ci");
        Lit68 = (SimpleSymbol) simpleSymbol83.readResolve();
        new SimpleSymbol("%string-suffix-length");
        Lit67 = (SimpleSymbol) simpleSymbol84.readResolve();
        new SimpleSymbol("%string-prefix-length");
        Lit66 = (SimpleSymbol) simpleSymbol85.readResolve();
        new SimpleSymbol("string-tabulate");
        Lit65 = (SimpleSymbol) simpleSymbol86.readResolve();
        new SimpleSymbol("string-any");
        Lit64 = (SimpleSymbol) simpleSymbol87.readResolve();
        new SimpleSymbol("string-every");
        Lit63 = (SimpleSymbol) simpleSymbol88.readResolve();
        new SimpleSymbol("string-for-each-index");
        Lit62 = (SimpleSymbol) simpleSymbol89.readResolve();
        new SimpleSymbol("string-for-each");
        Lit61 = (SimpleSymbol) simpleSymbol90.readResolve();
        new SimpleSymbol("string-unfold-right");
        Lit60 = (SimpleSymbol) simpleSymbol91.readResolve();
        new SimpleSymbol("string-unfold");
        Lit59 = (SimpleSymbol) simpleSymbol92.readResolve();
        new SimpleSymbol("string-fold-right");
        Lit58 = (SimpleSymbol) simpleSymbol93.readResolve();
        new SimpleSymbol("string-fold");
        Lit57 = (SimpleSymbol) simpleSymbol94.readResolve();
        new SimpleSymbol("%string-map!");
        Lit56 = (SimpleSymbol) simpleSymbol95.readResolve();
        new SimpleSymbol("string-map!");
        Lit55 = (SimpleSymbol) simpleSymbol96.readResolve();
        new SimpleSymbol("%string-map");
        Lit54 = (SimpleSymbol) simpleSymbol97.readResolve();
        new SimpleSymbol("string-map");
        Lit53 = (SimpleSymbol) simpleSymbol98.readResolve();
        new SimpleSymbol("string-copy");
        Lit52 = (SimpleSymbol) simpleSymbol99.readResolve();
        new SimpleSymbol("%substring/shared");
        Lit51 = (SimpleSymbol) simpleSymbol100.readResolve();
        new SimpleSymbol("substring/shared");
        Lit50 = (SimpleSymbol) simpleSymbol101.readResolve();
        new SimpleSymbol("check-substring-spec");
        Lit49 = (SimpleSymbol) simpleSymbol102.readResolve();
        new SimpleSymbol("substring-spec-ok?");
        Lit48 = (SimpleSymbol) simpleSymbol103.readResolve();
        new SimpleSymbol("string-parse-final-start+end");
        Lit47 = (SimpleSymbol) simpleSymbol104.readResolve();
        new SimpleSymbol("%check-bounds");
        Lit46 = (SimpleSymbol) simpleSymbol105.readResolve();
        new SimpleSymbol("string-parse-start+end");
        Lit45 = (SimpleSymbol) simpleSymbol106.readResolve();
        SyntaxRules syntaxRules3 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        new SimpleSymbol("l-s-s+e2");
        objArr[0] = (SimpleSymbol) simpleSymbol107.readResolve();
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule4 = syntaxRule;
        SyntaxPattern syntaxPattern4 = syntaxPattern;
        new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\f\u0017\f\u001f\b\f'\f/\f7\f?\rG@\b\b", new Object[0], 9);
        Object[] objArr3 = new Object[5];
        new SimpleSymbol("let");
        objArr3[0] = (SimpleSymbol) simpleSymbol108.readResolve();
        Object[] objArr4 = objArr3;
        new SimpleSymbol("procv");
        objArr4[1] = (SimpleSymbol) simpleSymbol109.readResolve();
        Object[] objArr5 = objArr4;
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr5;
        new SimpleSymbol("let-string-start+end");
        SimpleSymbol simpleSymbol140 = (SimpleSymbol) simpleSymbol110.readResolve();
        Lit41 = simpleSymbol140;
        objArr7[2] = simpleSymbol140;
        Object[] objArr8 = objArr6;
        Object[] objArr9 = objArr8;
        Object[] objArr10 = objArr8;
        new SimpleSymbol("rest");
        SimpleSymbol simpleSymbol141 = (SimpleSymbol) simpleSymbol111.readResolve();
        Lit27 = simpleSymbol141;
        objArr10[3] = PairWithPosition.make(simpleSymbol141, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 553003);
        Object[] objArr11 = objArr9;
        objArr11[4] = Lit27;
        new SyntaxRule(syntaxPattern4, "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b#\b\u0011\u0018\u00141\t\u0003\t\u000b\u0018\u001c\u0011\u0018\f\t+\t;\b\u0011\u0018\u0014!\t\u0013\b\u001b\u0011\u0018\f\t3\u0011\u0018$\bEC", objArr11, 1);
        syntaxRuleArr3[0] = syntaxRule4;
        new SyntaxRules(objArr2, syntaxRuleArr2, 9);
        Lit44 = syntaxRules3;
        new SimpleSymbol("let-string-start+end2");
        Lit43 = (SimpleSymbol) simpleSymbol112.readResolve();
        SyntaxRules syntaxRules4 = syntaxRules2;
        Object[] objArr12 = new Object[1];
        Object[] objArr13 = objArr12;
        objArr12[0] = Lit41;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule5 = syntaxRule2;
        SyntaxPattern syntaxPattern5 = syntaxPattern2;
        new SyntaxPattern("\f\u0018,\f\u0007\f\u000f\b\f\u0017\f\u001f\f'\r/(\b\b", new Object[0], 6);
        Object[] objArr14 = new Object[2];
        objArr14[0] = Lit150;
        Object[] objArr15 = objArr14;
        objArr15[1] = Lit47;
        new SyntaxRule(syntaxPattern5, "\u0001\u0001\u0001\u0001\u0001\u0003", "\u0011\u0018\u0004!\t\u0003\b\u000bI\u0011\u0018\f\t\u0013\t\u001b\b#\b-+", objArr15, 1);
        syntaxRuleArr6[0] = syntaxRule5;
        SyntaxRule[] syntaxRuleArr7 = syntaxRuleArr5;
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule6 = syntaxRule3;
        SyntaxPattern syntaxPattern6 = syntaxPattern3;
        new SyntaxPattern("\f\u0018<\f\u0007\f\u000f\f\u0017\b\f\u001f\f'\f/\r70\b\b", new Object[0], 7);
        Object[] objArr16 = new Object[2];
        objArr16[0] = Lit150;
        Object[] objArr17 = objArr16;
        objArr17[1] = Lit45;
        new SyntaxRule(syntaxPattern6, "\u0001\u0001\u0001\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\t\u0013\t\u0003\b\u000bI\u0011\u0018\f\t\u001b\t#\b+\b53", objArr17, 1);
        syntaxRuleArr9[1] = syntaxRule6;
        new SyntaxRules(objArr13, syntaxRuleArr8, 7);
        Lit42 = syntaxRules4;
        new SimpleSymbol("grammar");
        Lit40 = (SimpleSymbol) simpleSymbol113.readResolve();
        new SimpleSymbol("delim");
        Lit39 = (SimpleSymbol) simpleSymbol114.readResolve();
        new SimpleSymbol("token-chars");
        Lit38 = (SimpleSymbol) simpleSymbol115.readResolve();
        new SimpleSymbol("final");
        Lit37 = (SimpleSymbol) simpleSymbol116.readResolve();
        new SimpleSymbol("s-end");
        Lit36 = (SimpleSymbol) simpleSymbol117.readResolve();
        new SimpleSymbol("s-start");
        Lit35 = (SimpleSymbol) simpleSymbol118.readResolve();
        new SimpleSymbol("p-start");
        Lit34 = (SimpleSymbol) simpleSymbol119.readResolve();
        new SimpleSymbol("end");
        Lit33 = (SimpleSymbol) simpleSymbol120.readResolve();
        new SimpleSymbol("start");
        Lit32 = (SimpleSymbol) simpleSymbol121.readResolve();
        new SimpleSymbol("c=");
        Lit31 = (SimpleSymbol) simpleSymbol122.readResolve();
        new SimpleSymbol("char-set");
        Lit30 = (SimpleSymbol) simpleSymbol123.readResolve();
        new SimpleSymbol("criterion");
        Lit29 = (SimpleSymbol) simpleSymbol124.readResolve();
        new SimpleSymbol("char-cased?");
        Lit28 = (SimpleSymbol) simpleSymbol125.readResolve();
        new SimpleSymbol("bound");
        Lit26 = (SimpleSymbol) simpleSymbol126.readResolve();
        new SimpleSymbol("char-set-contains?");
        Lit25 = (SimpleSymbol) simpleSymbol127.readResolve();
        new SimpleSymbol("char-set?");
        Lit24 = (SimpleSymbol) simpleSymbol128.readResolve();
        new SimpleSymbol("make-final");
        Lit23 = (SimpleSymbol) simpleSymbol129.readResolve();
        new SimpleSymbol("base");
        Lit22 = (SimpleSymbol) simpleSymbol130.readResolve();
        new SimpleSymbol("let-optionals*");
        Lit21 = (SimpleSymbol) simpleSymbol131.readResolve();
        new SimpleSymbol(":optional");
        Lit20 = (SimpleSymbol) simpleSymbol132.readResolve();
        new SimpleSymbol("check-arg");
        Lit19 = (SimpleSymbol) simpleSymbol133.readResolve();
        new SimpleSymbol("suffix");
        Lit18 = (SimpleSymbol) simpleSymbol134.readResolve();
        new SimpleSymbol("prefix");
        Lit17 = (SimpleSymbol) simpleSymbol135.readResolve();
        new SimpleSymbol("strict-infix");
        Lit16 = (SimpleSymbol) simpleSymbol136.readResolve();
        new SimpleSymbol("infix");
        Lit15 = (SimpleSymbol) simpleSymbol137.readResolve();
        new SimpleSymbol("graphic");
        Lit14 = (SimpleSymbol) simpleSymbol138.readResolve();
        new SimpleSymbol("whitespace");
        Lit11 = (SimpleSymbol) simpleSymbol139.readResolve();
        new srfi13();
        $instance = srfi13;
        srfi13 srfi132 = $instance;
        new ModuleMethod(srfi132, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, Lit45, 12291);
        string$Mnparse$Mnstart$Plend = moduleMethod;
        new ModuleMethod(srfi132, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, Lit46, 16388);
        $Pccheck$Mnbounds = moduleMethod2;
        new ModuleMethod(srfi132, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, Lit47, 12291);
        string$Mnparse$Mnfinal$Mnstart$Plend = moduleMethod3;
        new ModuleMethod(srfi132, 197, Lit48, 12291);
        substring$Mnspec$Mnok$Qu = moduleMethod4;
        new ModuleMethod(srfi132, 198, Lit49, 16388);
        check$Mnsubstring$Mnspec = moduleMethod5;
        new ModuleMethod(srfi132, 199, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod134 = moduleMethod6;
        moduleMethod134.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:223");
        lambda$Fn5 = moduleMethod134;
        new ModuleMethod(srfi132, 200, Lit50, -4094);
        substring$Slshared = moduleMethod7;
        new ModuleMethod(srfi132, 201, Lit51, 12291);
        $Pcsubstring$Slshared = moduleMethod8;
        new ModuleMethod(srfi132, 202, Lit52, -4095);
        string$Mncopy = moduleMethod9;
        new ModuleMethod(srfi132, HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, Lit53, -4094);
        string$Mnmap = moduleMethod10;
        new ModuleMethod(srfi132, HttpStatus.SC_NO_CONTENT, Lit54, 16388);
        $Pcstring$Mnmap = moduleMethod11;
        new ModuleMethod(srfi132, HttpStatus.SC_RESET_CONTENT, Lit55, -4094);
        string$Mnmap$Ex = moduleMethod12;
        new ModuleMethod(srfi132, HttpStatus.SC_PARTIAL_CONTENT, Lit56, 16388);
        $Pcstring$Mnmap$Ex = moduleMethod13;
        new ModuleMethod(srfi132, HttpStatus.SC_MULTI_STATUS, Lit57, -4093);
        string$Mnfold = moduleMethod14;
        new ModuleMethod(srfi132, 208, Lit58, -4093);
        string$Mnfold$Mnright = moduleMethod15;
        new ModuleMethod(srfi132, 209, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod135 = moduleMethod16;
        moduleMethod135.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:377");
        lambda$Fn17 = moduleMethod135;
        new ModuleMethod(srfi132, 210, Lit59, -4092);
        string$Mnunfold = moduleMethod17;
        new ModuleMethod(srfi132, 211, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod136 = moduleMethod18;
        moduleMethod136.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:422");
        lambda$Fn18 = moduleMethod136;
        new ModuleMethod(srfi132, 212, Lit60, -4092);
        string$Mnunfold$Mnright = moduleMethod19;
        new ModuleMethod(srfi132, 213, Lit61, -4094);
        string$Mnfor$Mneach = moduleMethod20;
        new ModuleMethod(srfi132, 214, Lit62, -4094);
        string$Mnfor$Mneach$Mnindex = moduleMethod21;
        new ModuleMethod(srfi132, 215, Lit63, -4094);
        string$Mnevery = moduleMethod22;
        new ModuleMethod(srfi132, 216, Lit64, -4094);
        string$Mnany = moduleMethod23;
        new ModuleMethod(srfi132, 217, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod137 = moduleMethod24;
        moduleMethod137.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:535");
        lambda$Fn27 = moduleMethod137;
        new ModuleMethod(srfi132, 218, Lit65, 8194);
        string$Mntabulate = moduleMethod25;
        new ModuleMethod(srfi132, 219, Lit66, 24582);
        $Pcstring$Mnprefix$Mnlength = moduleMethod26;
        new ModuleMethod(srfi132, 220, Lit67, 24582);
        $Pcstring$Mnsuffix$Mnlength = moduleMethod27;
        new ModuleMethod(srfi132, 221, Lit68, 24582);
        $Pcstring$Mnprefix$Mnlength$Mnci = moduleMethod28;
        new ModuleMethod(srfi132, 222, Lit69, 24582);
        $Pcstring$Mnsuffix$Mnlength$Mnci = moduleMethod29;
        new ModuleMethod(srfi132, 223, Lit70, -4094);
        string$Mnprefix$Mnlength = moduleMethod30;
        new ModuleMethod(srfi132, YaVersion.YOUNG_ANDROID_VERSION, Lit71, -4094);
        string$Mnsuffix$Mnlength = moduleMethod31;
        new ModuleMethod(srfi132, 225, Lit72, -4094);
        string$Mnprefix$Mnlength$Mnci = moduleMethod32;
        new ModuleMethod(srfi132, 226, Lit73, -4094);
        string$Mnsuffix$Mnlength$Mnci = moduleMethod33;
        new ModuleMethod(srfi132, 227, Lit74, -4094);
        string$Mnprefix$Qu = moduleMethod34;
        new ModuleMethod(srfi132, 228, Lit75, -4094);
        string$Mnsuffix$Qu = moduleMethod35;
        new ModuleMethod(srfi132, 229, Lit76, -4094);
        string$Mnprefix$Mnci$Qu = moduleMethod36;
        new ModuleMethod(srfi132, 230, Lit77, -4094);
        string$Mnsuffix$Mnci$Qu = moduleMethod37;
        new ModuleMethod(srfi132, 231, Lit78, 24582);
        $Pcstring$Mnprefix$Qu = moduleMethod38;
        new ModuleMethod(srfi132, 232, Lit79, 24582);
        $Pcstring$Mnsuffix$Qu = moduleMethod39;
        new ModuleMethod(srfi132, 233, Lit80, 24582);
        $Pcstring$Mnprefix$Mnci$Qu = moduleMethod40;
        new ModuleMethod(srfi132, 234, Lit81, 24582);
        $Pcstring$Mnsuffix$Mnci$Qu = moduleMethod41;
        new ModuleMethod(srfi132, 235, Lit82, 36873);
        $Pcstring$Mncompare = moduleMethod42;
        new ModuleMethod(srfi132, 236, Lit83, 36873);
        $Pcstring$Mncompare$Mnci = moduleMethod43;
        new ModuleMethod(srfi132, 237, Lit84, -4091);
        string$Mncompare = moduleMethod44;
        new ModuleMethod(srfi132, 238, Lit85, -4091);
        string$Mncompare$Mnci = moduleMethod45;
        new ModuleMethod(srfi132, 239, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod138 = moduleMethod46;
        moduleMethod138.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:756");
        lambda$Fn72 = moduleMethod138;
        new ModuleMethod(srfi132, 240, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod139 = moduleMethod47;
        moduleMethod139.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:758");
        lambda$Fn73 = moduleMethod139;
        new ModuleMethod(srfi132, LispEscapeFormat.ESCAPE_NORMAL, Lit86, -4094);
        string$Eq = moduleMethod48;
        new ModuleMethod(srfi132, LispEscapeFormat.ESCAPE_ALL, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod140 = moduleMethod49;
        moduleMethod140.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:767");
        lambda$Fn78 = moduleMethod140;
        new ModuleMethod(srfi132, 243, Lit87, -4094);
        string$Ls$Gr = moduleMethod50;
        new ModuleMethod(srfi132, 244, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod141 = moduleMethod51;
        moduleMethod141.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:778");
        lambda$Fn83 = moduleMethod141;
        new ModuleMethod(srfi132, 245, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod142 = moduleMethod52;
        moduleMethod142.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:779");
        lambda$Fn84 = moduleMethod142;
        new ModuleMethod(srfi132, 246, Lit88, -4094);
        string$Ls = moduleMethod53;
        new ModuleMethod(srfi132, 247, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod143 = moduleMethod54;
        moduleMethod143.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:788");
        lambda$Fn89 = moduleMethod143;
        new ModuleMethod(srfi132, ComponentConstants.LISTVIEW_PREFERRED_WIDTH, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod144 = moduleMethod55;
        moduleMethod144.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:789");
        lambda$Fn90 = moduleMethod144;
        new ModuleMethod(srfi132, 249, Lit89, -4094);
        string$Gr = moduleMethod56;
        new ModuleMethod(srfi132, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod145 = moduleMethod57;
        moduleMethod145.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:801");
        lambda$Fn95 = moduleMethod145;
        new ModuleMethod(srfi132, Telnet.WILL, Lit90, -4094);
        string$Ls$Eq = moduleMethod58;
        new ModuleMethod(srfi132, Telnet.WONT, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod146 = moduleMethod59;
        moduleMethod146.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:810");
        lambda$Fn100 = moduleMethod146;
        new ModuleMethod(srfi132, Telnet.f261DO, Lit91, -4094);
        string$Gr$Eq = moduleMethod60;
        new ModuleMethod(srfi132, Telnet.DONT, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod147 = moduleMethod61;
        moduleMethod147.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:820");
        lambda$Fn105 = moduleMethod147;
        new ModuleMethod(srfi132, 255, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod148 = moduleMethod62;
        moduleMethod148.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:822");
        lambda$Fn106 = moduleMethod148;
        new ModuleMethod(srfi132, 256, Lit92, -4094);
        string$Mnci$Eq = moduleMethod63;
        new ModuleMethod(srfi132, InputDeviceCompat.SOURCE_KEYBOARD, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod149 = moduleMethod64;
        moduleMethod149.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:831");
        lambda$Fn111 = moduleMethod149;
        new ModuleMethod(srfi132, 258, Lit93, -4094);
        string$Mnci$Ls$Gr = moduleMethod65;
        new ModuleMethod(srfi132, 259, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod150 = moduleMethod66;
        moduleMethod150.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:842");
        lambda$Fn116 = moduleMethod150;
        new ModuleMethod(srfi132, 260, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod151 = moduleMethod67;
        moduleMethod151.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:843");
        lambda$Fn117 = moduleMethod151;
        new ModuleMethod(srfi132, 261, Lit94, -4094);
        string$Mnci$Ls = moduleMethod68;
        new ModuleMethod(srfi132, 262, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod152 = moduleMethod69;
        moduleMethod152.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:852");
        lambda$Fn122 = moduleMethod152;
        new ModuleMethod(srfi132, 263, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod153 = moduleMethod70;
        moduleMethod153.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:853");
        lambda$Fn123 = moduleMethod153;
        new ModuleMethod(srfi132, 264, Lit95, -4094);
        string$Mnci$Gr = moduleMethod71;
        new ModuleMethod(srfi132, 265, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod154 = moduleMethod72;
        moduleMethod154.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:865");
        lambda$Fn128 = moduleMethod154;
        new ModuleMethod(srfi132, 266, Lit96, -4094);
        string$Mnci$Ls$Eq = moduleMethod73;
        new ModuleMethod(srfi132, 267, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod155 = moduleMethod74;
        moduleMethod155.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:874");
        lambda$Fn133 = moduleMethod155;
        new ModuleMethod(srfi132, 268, Lit97, -4094);
        string$Mnci$Gr$Eq = moduleMethod75;
        new ModuleMethod(srfi132, 269, Lit98, 20485);
        $Pcstring$Mnhash = moduleMethod76;
        new ModuleMethod(srfi132, 270, Lit99, -4095);
        string$Mnhash = moduleMethod77;
        new ModuleMethod(srfi132, 271, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod156 = moduleMethod78;
        moduleMethod156.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:922");
        lambda$Fn138 = moduleMethod156;
        new ModuleMethod(srfi132, 272, Lit100, -4095);
        string$Mnhash$Mnci = moduleMethod79;
        new ModuleMethod(srfi132, 273, Lit101, -4095);
        string$Mnupcase = moduleMethod80;
        new ModuleMethod(srfi132, 274, Lit102, -4095);
        string$Mnupcase$Ex = moduleMethod81;
        new ModuleMethod(srfi132, 275, Lit103, -4095);
        string$Mndowncase = moduleMethod82;
        new ModuleMethod(srfi132, 276, Lit104, -4095);
        string$Mndowncase$Ex = moduleMethod83;
        new ModuleMethod(srfi132, 277, Lit105, 12291);
        $Pcstring$Mntitlecase$Ex = moduleMethod84;
        new ModuleMethod(srfi132, 278, Lit106, -4095);
        string$Mntitlecase$Ex = moduleMethod85;
        new ModuleMethod(srfi132, 279, Lit107, -4095);
        string$Mntitlecase = moduleMethod86;
        new ModuleMethod(srfi132, 280, Lit108, 8194);
        string$Mntake = moduleMethod87;
        new ModuleMethod(srfi132, 281, Lit109, 8194);
        string$Mntake$Mnright = moduleMethod88;
        new ModuleMethod(srfi132, 282, Lit110, 8194);
        string$Mndrop = moduleMethod89;
        new ModuleMethod(srfi132, 283, Lit111, 8194);
        string$Mndrop$Mnright = moduleMethod90;
        new ModuleMethod(srfi132, 284, Lit112, -4095);
        string$Mntrim = moduleMethod91;
        new ModuleMethod(srfi132, 285, Lit113, -4095);
        string$Mntrim$Mnright = moduleMethod92;
        new ModuleMethod(srfi132, 286, Lit114, -4095);
        string$Mntrim$Mnboth = moduleMethod93;
        new ModuleMethod(srfi132, 287, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod157 = moduleMethod94;
        moduleMethod157.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1047");
        lambda$Fn163 = moduleMethod157;
        new ModuleMethod(srfi132, 288, Lit115, -4094);
        string$Mnpad$Mnright = moduleMethod95;
        new ModuleMethod(srfi132, 289, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod158 = moduleMethod96;
        moduleMethod158.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1059");
        lambda$Fn166 = moduleMethod158;
        new ModuleMethod(srfi132, 290, Lit116, -4094);
        string$Mnpad = moduleMethod97;
        new ModuleMethod(srfi132, 291, Lit117, -4094);
        string$Mndelete = moduleMethod98;
        new ModuleMethod(srfi132, 292, Lit118, -4094);
        string$Mnfilter = moduleMethod99;
        new ModuleMethod(srfi132, 293, Lit119, -4094);
        string$Mnindex = moduleMethod100;
        new ModuleMethod(srfi132, 294, Lit120, -4094);
        string$Mnindex$Mnright = moduleMethod101;
        new ModuleMethod(srfi132, 295, Lit121, -4094);
        string$Mnskip = moduleMethod102;
        new ModuleMethod(srfi132, 296, Lit122, -4094);
        string$Mnskip$Mnright = moduleMethod103;
        new ModuleMethod(srfi132, 297, Lit123, -4094);
        string$Mncount = moduleMethod104;
        new ModuleMethod(srfi132, 298, Lit124, -4094);
        string$Mnfill$Ex = moduleMethod105;
        new ModuleMethod(srfi132, 299, Lit125, 20483);
        string$Mncopy$Ex = moduleMethod106;
        new ModuleMethod(srfi132, 302, Lit126, 20485);
        $Pcstring$Mncopy$Ex = moduleMethod107;
        new ModuleMethod(srfi132, 303, Lit127, -4094);
        string$Mncontains = moduleMethod108;
        new ModuleMethod(srfi132, 304, Lit128, -4094);
        string$Mncontains$Mnci = moduleMethod109;
        new ModuleMethod(srfi132, 305, Lit129, 28679);
        $Pckmp$Mnsearch = moduleMethod110;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED, Lit130, -4095);
        make$Mnkmp$Mnrestart$Mnvector = moduleMethod111;
        new ModuleMethod(srfi132, 307, Lit131, 24582);
        kmp$Mnstep = moduleMethod112;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED, Lit132, -4092);
        string$Mnkmp$Mnpartial$Mnsearch = moduleMethod113;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED, Lit133, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnnull$Qu = moduleMethod114;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED, Lit134, -4095);
        string$Mnreverse = moduleMethod115;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED, Lit135, -4095);
        string$Mnreverse$Ex = moduleMethod116;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED, Lit136, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        reverse$Mnlist$Mn$Grstring = moduleMethod117;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED, Lit137, -4095);
        string$Mn$Grlist = moduleMethod118;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_SEARCH_FAILED, Lit138, -4096);
        string$Mnappend$Slshared = moduleMethod119;
        new ModuleMethod(srfi132, ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH, Lit139, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnconcatenate$Slshared = moduleMethod120;
        new ModuleMethod(srfi132, 316, Lit140, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnconcatenate = moduleMethod121;
        new ModuleMethod(srfi132, 317, Lit141, -4095);
        string$Mnconcatenate$Mnreverse = moduleMethod122;
        new ModuleMethod(srfi132, 318, Lit142, -4095);
        string$Mnconcatenate$Mnreverse$Slshared = moduleMethod123;
        new ModuleMethod(srfi132, 319, Lit143, 16388);
        $Pcfinish$Mnstring$Mnconcatenate$Mnreverse = moduleMethod124;
        new ModuleMethod(srfi132, ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION, Lit144, -4092);
        string$Mnreplace = moduleMethod125;
        new ModuleMethod(srfi132, 321, Lit145, -4095);
        string$Mntokenize = moduleMethod126;
        new ModuleMethod(srfi132, 322, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod159 = moduleMethod127;
        moduleMethod159.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1738");
        lambda$Fn210 = moduleMethod159;
        new ModuleMethod(srfi132, 323, Lit146, -4094);
        xsubstring = moduleMethod128;
        new ModuleMethod(srfi132, 324, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod160 = moduleMethod129;
        moduleMethod160.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1779");
        lambda$Fn216 = moduleMethod160;
        new ModuleMethod(srfi132, 325, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod161 = moduleMethod130;
        moduleMethod161.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1785");
        lambda$Fn220 = moduleMethod161;
        new ModuleMethod(srfi132, 326, Lit147, -4092);
        string$Mnxcopy$Ex = moduleMethod131;
        new ModuleMethod(srfi132, 327, Lit148, 28679);
        $Pcmultispan$Mnrepcopy$Ex = moduleMethod132;
        new ModuleMethod(srfi132, 328, Lit149, -4095);
        string$Mnjoin = moduleMethod133;
        $instance.run();
    }

    public srfi13() {
        ModuleInfo.register(this);
    }

    public static Object stringCopy$Ex(Object obj, int i, CharSequence charSequence) {
        return stringCopy$Ex(obj, i, charSequence, 0);
    }

    public static Object stringCopy$Ex(Object obj, int i, CharSequence charSequence, int i2) {
        CharSequence charSequence2 = charSequence;
        return stringCopy$Ex(obj, i, charSequence2, i2, charSequence2.length());
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Object stringParseStart$PlEnd(Object proc, Object s, Object obj) {
        frame frame97;
        Throwable th;
        Object proc2;
        boolean x;
        Object args = obj;
        new frame();
        frame frame98 = frame97;
        frame98.proc = proc;
        frame98.f142s = s;
        if (!strings.isString(frame98.f142s)) {
            Object[] objArr = new Object[2];
            objArr[0] = frame98.proc;
            Object[] objArr2 = objArr;
            objArr2[1] = frame98.f142s;
            Object error$V = misc.error$V("Non-string value", objArr2);
        }
        Object obj2 = frame98.f142s;
        Object obj3 = obj2;
        try {
            frame98.slen = strings.stringLength((CharSequence) obj2);
            if (C1245lists.isPair(args)) {
                Object apply1 = C1245lists.car.apply1(args);
                frame98.args = C1245lists.cdr.apply1(args);
                frame98.start = apply1;
                boolean x2 = numbers.isInteger(frame98.start);
                if (!x2 ? !x2 : !(x = numbers.isExact(frame98.start)) ? !x : Scheme.numGEq.apply2(frame98.start, Lit0) == Boolean.FALSE) {
                    Object[] objArr3 = new Object[3];
                    objArr3[0] = frame98.proc;
                    Object[] objArr4 = objArr3;
                    objArr4[1] = frame98.start;
                    Object[] objArr5 = objArr4;
                    objArr5[2] = frame98.f142s;
                    proc2 = misc.error$V("Illegal substring START spec", objArr5);
                } else {
                    proc2 = call_with_values.callWithValues(frame98.lambda$Fn1, frame98.lambda$Fn2);
                }
            } else {
                Object[] objArr6 = new Object[3];
                objArr6[0] = LList.Empty;
                Object[] objArr7 = objArr6;
                objArr7[1] = Lit0;
                Object[] objArr8 = objArr7;
                objArr8[2] = Integer.valueOf(frame98.slen);
                proc2 = misc.values(objArr8);
            }
            return proc2;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "string-length", 1, obj3);
            throw th2;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 197:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 201:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof CharSequence)) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 277:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 299:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                CallContext callContext4 = callContext2;
                Object obj9 = obj6;
                Object obj10 = obj9;
                if (!(obj9 instanceof CharSequence)) {
                    return -786429;
                }
                callContext4.value3 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame extends ModuleBody {
        Object args;
        final ModuleMethod lambda$Fn1;
        final ModuleMethod lambda$Fn2;
        Object proc;

        /* renamed from: s */
        Object f142s;
        int slen;
        Object start;

        public frame() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 1, (Object) null, 0);
            this.lambda$Fn1 = moduleMethod;
            new ModuleMethod(this, 2, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:150");
            this.lambda$Fn2 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 1 ? lambda1() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 1) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 2) {
                return lambda2(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda2(Object obj, Object obj2) {
            Object error$V;
            Object end = obj;
            Object args2 = obj2;
            if (Scheme.numLEq.apply2(this.start, end) != Boolean.FALSE) {
                Object[] objArr = new Object[3];
                objArr[0] = args2;
                Object[] objArr2 = objArr;
                objArr2[1] = this.start;
                Object[] objArr3 = objArr2;
                objArr3[2] = end;
                error$V = misc.values(objArr3);
            } else {
                Object[] objArr4 = new Object[4];
                objArr4[0] = this.proc;
                Object[] objArr5 = objArr4;
                objArr5[1] = this.start;
                Object[] objArr6 = objArr5;
                objArr6[2] = end;
                Object[] objArr7 = objArr6;
                objArr7[3] = this.f142s;
                error$V = misc.error$V("Illegal substring START/END spec", objArr7);
            }
            return error$V;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 2) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda1() {
            Object values;
            boolean x;
            if (C1245lists.isPair(this.args)) {
                Object apply1 = C1245lists.car.apply1(this.args);
                Object args2 = C1245lists.cdr.apply1(this.args);
                Object end = apply1;
                boolean x2 = numbers.isInteger(end);
                if (!x2 ? !x2 : !(x = numbers.isExact(end)) ? !x : Scheme.numLEq.apply2(end, Integer.valueOf(this.slen)) == Boolean.FALSE) {
                    Object[] objArr = new Object[3];
                    objArr[0] = this.proc;
                    Object[] objArr2 = objArr;
                    objArr2[1] = end;
                    Object[] objArr3 = objArr2;
                    objArr3[2] = this.f142s;
                    values = misc.error$V("Illegal substring END spec", objArr3);
                } else {
                    Object[] objArr4 = new Object[2];
                    objArr4[0] = end;
                    Object[] objArr5 = objArr4;
                    objArr5[1] = args2;
                    values = misc.values(objArr5);
                }
            } else {
                Object[] objArr6 = new Object[2];
                objArr6[0] = Integer.valueOf(this.slen);
                Object[] objArr7 = objArr6;
                objArr7[1] = this.args;
                values = misc.values(objArr7);
            }
            return values;
        }
    }

    public static Object $PcCheckBounds(Object obj, CharSequence charSequence, int i, int i2) {
        Object proc;
        Object proc2 = obj;
        CharSequence s = charSequence;
        int start = i;
        int end = i2;
        if (start < 0) {
            Object[] objArr = new Object[3];
            objArr[0] = proc2;
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(start);
            Object[] objArr3 = objArr2;
            objArr3[2] = s;
            proc = misc.error$V("Illegal substring START spec", objArr3);
        } else if (start > end) {
            proc = misc.error$V("Illegal substring START/END spec", new Object[0]);
        } else if (end > strings.stringLength(s)) {
            Object[] objArr4 = new Object[3];
            objArr4[0] = proc2;
            Object[] objArr5 = objArr4;
            objArr5[1] = Integer.valueOf(end);
            Object[] objArr6 = objArr5;
            objArr6[2] = s;
            proc = misc.error$V("Illegal substring END spec", objArr6);
        } else {
            proc = Values.empty;
        }
        return proc;
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN:
                callContext2.value1 = obj5;
                CallContext callContext3 = callContext2;
                Object obj9 = obj6;
                Object obj10 = obj9;
                if (!(obj9 instanceof CharSequence)) {
                    return -786430;
                }
                callContext3.value2 = obj10;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 198:
                callContext2.value1 = obj5;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case HttpStatus.SC_NO_CONTENT /*204*/:
                callContext2.value1 = obj5;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case HttpStatus.SC_PARTIAL_CONTENT /*206*/:
                callContext2.value1 = obj5;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 299:
                callContext2.value1 = obj5;
                callContext2.value2 = obj6;
                CallContext callContext4 = callContext2;
                Object obj11 = obj7;
                Object obj12 = obj11;
                if (!(obj11 instanceof CharSequence)) {
                    return -786429;
                }
                callContext4.value3 = obj12;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 319:
                callContext2.value1 = obj5;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame0 extends ModuleBody {
        Object args;
        final ModuleMethod lambda$Fn3;
        final ModuleMethod lambda$Fn4;
        Object proc;

        /* renamed from: s */
        Object f143s;

        public frame0() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 3, (Object) null, 0);
            this.lambda$Fn3 = moduleMethod;
            new ModuleMethod(this, 4, (Object) null, 12291);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:174");
            this.lambda$Fn4 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 3 ? lambda3() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 3) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            if (moduleMethod2.selector == 4) {
                return lambda4(obj4, obj5, obj6);
            }
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        /* access modifiers changed from: package-private */
        public Object lambda3() {
            return srfi13.stringParseStart$PlEnd(this.proc, this.f143s, this.args);
        }

        /* access modifiers changed from: package-private */
        public Object lambda4(Object obj, Object obj2, Object obj3) {
            Object values;
            Object rest = obj;
            Object start = obj2;
            Object end = obj3;
            if (C1245lists.isPair(rest)) {
                Object[] objArr = new Object[2];
                objArr[0] = this.proc;
                Object[] objArr2 = objArr;
                objArr2[1] = rest;
                values = misc.error$V("Extra arguments to procedure", objArr2);
            } else {
                Object[] objArr3 = new Object[2];
                objArr3[0] = start;
                Object[] objArr4 = objArr3;
                objArr4[1] = end;
                values = misc.values(objArr4);
            }
            return values;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 4) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }
    }

    public static Object stringParseFinalStart$PlEnd(Object proc, Object s, Object args) {
        frame0 frame02;
        new frame0();
        frame0 frame03 = frame02;
        frame03.proc = proc;
        frame03.f143s = s;
        frame03.args = args;
        return call_with_values.callWithValues(frame03.lambda$Fn3, frame03.lambda$Fn4);
    }

    public static boolean isSubstringSpecOk(Object obj, Object obj2, Object obj3) {
        boolean z;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Object s = obj;
        Object start = obj2;
        Object end = obj3;
        boolean x = strings.isString(s);
        if (x) {
            boolean x2 = numbers.isInteger(start);
            if (x2) {
                boolean x3 = numbers.isExact(start);
                if (x3) {
                    boolean x4 = numbers.isInteger(end);
                    if (x4) {
                        boolean x5 = numbers.isExact(end);
                        if (x5) {
                            Object apply2 = Scheme.numLEq.apply2(Lit0, start);
                            Object obj4 = apply2;
                            try {
                                boolean x6 = ((Boolean) apply2).booleanValue();
                                if (x6) {
                                    Object apply22 = Scheme.numLEq.apply2(start, end);
                                    Object obj5 = apply22;
                                    try {
                                        boolean x7 = ((Boolean) apply22).booleanValue();
                                        if (x7) {
                                            Object obj6 = s;
                                            Object obj7 = obj6;
                                            try {
                                                z = ((Boolean) Scheme.numLEq.apply2(end, Integer.valueOf(strings.stringLength((CharSequence) obj6)))).booleanValue();
                                            } catch (ClassCastException e) {
                                                ClassCastException classCastException = e;
                                                Throwable th4 = th3;
                                                new WrongType(classCastException, "string-length", 1, obj7);
                                                throw th4;
                                            }
                                        } else {
                                            z = x7;
                                        }
                                    } catch (ClassCastException e2) {
                                        ClassCastException classCastException2 = e2;
                                        Throwable th5 = th2;
                                        new WrongType(classCastException2, "x", -2, obj5);
                                        throw th5;
                                    }
                                } else {
                                    z = x6;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th6 = th;
                                new WrongType(classCastException3, "x", -2, obj4);
                                throw th6;
                            }
                        } else {
                            z = x5;
                        }
                    } else {
                        z = x4;
                    }
                } else {
                    z = x3;
                }
            } else {
                z = x2;
            }
        } else {
            z = x;
        }
        return z;
    }

    public static Object checkSubstringSpec(Object obj, Object obj2, Object obj3, Object obj4) {
        Object proc;
        Object proc2 = obj;
        Object s = obj2;
        Object start = obj3;
        Object end = obj4;
        if (!isSubstringSpecOk(s, start, end)) {
            Object[] objArr = new Object[4];
            objArr[0] = proc2;
            Object[] objArr2 = objArr;
            objArr2[1] = s;
            Object[] objArr3 = objArr2;
            objArr3[2] = start;
            Object[] objArr4 = objArr3;
            objArr4[3] = end;
            proc = misc.error$V("Illegal substring spec.", objArr4);
        } else {
            proc = Values.empty;
        }
        return proc;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (r4 != false) goto L_0x000f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object $PcCheckSubstringSpec(java.lang.Object r12, java.lang.CharSequence r13, int r14, int r15) {
        /*
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r6 = r2
            if (r6 >= 0) goto L_0x003f
            r6 = 1
        L_0x0008:
            r4 = r6
            r6 = r4
            if (r6 == 0) goto L_0x0041
            r6 = r4
            if (r6 == 0) goto L_0x0059
        L_0x000f:
            java.lang.String r6 = "Illegal substring spec."
            r7 = 4
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = 0
            r10 = r0
            r8[r9] = r10
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = 1
            r10 = r1
            r8[r9] = r10
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = 2
            r10 = r2
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r8[r9] = r10
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = 3
            r10 = r3
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r8[r9] = r10
            java.lang.Object r6 = kawa.lib.misc.error$V(r6, r7)
        L_0x003d:
            r0 = r6
            return r0
        L_0x003f:
            r6 = 0
            goto L_0x0008
        L_0x0041:
            r6 = r2
            r7 = r3
            if (r6 <= r7) goto L_0x004e
            r6 = 1
        L_0x0046:
            r5 = r6
            r6 = r5
            if (r6 == 0) goto L_0x0050
            r6 = r5
            if (r6 == 0) goto L_0x0059
            goto L_0x000f
        L_0x004e:
            r6 = 0
            goto L_0x0046
        L_0x0050:
            r6 = r3
            r7 = r1
            int r7 = kawa.lib.strings.stringLength(r7)
            if (r6 <= r7) goto L_0x0059
            goto L_0x000f
        L_0x0059:
            gnu.mapping.Values r6 = gnu.mapping.Values.empty
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.$PcCheckSubstringSpec(java.lang.Object, java.lang.CharSequence, int, int):java.lang.Object");
    }

    public static Object substring$SlShared$V(Object obj, Object start, Object[] argsArray) {
        frame1 frame110;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object s = obj;
        new frame1();
        frame1 frame111 = frame110;
        frame111.start = start;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList maybe$Mnend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), strings.string$Qu, s, substring$Slshared);
            Object obj2 = s;
            Object obj3 = obj2;
            try {
                frame111.slen = strings.stringLength((CharSequence) obj2);
                try {
                    Object apply42 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), lambda$Fn5, frame111.start, substring$Slshared);
                    Object obj4 = s;
                    Object obj5 = obj4;
                    try {
                        CharSequence charSequence = (CharSequence) obj4;
                        Object obj6 = frame111.start;
                        Object obj7 = obj6;
                        try {
                            int intValue = ((Number) obj6).intValue();
                            try {
                                Object apply43 = Scheme.applyToArgs.apply4(loc$$Cloptional.get(), maybe$Mnend, Integer.valueOf(frame111.slen), frame111.lambda$Fn6);
                                Object obj8 = apply43;
                                try {
                                    return $PcSubstring$SlShared(charSequence, intValue, ((Number) apply43).intValue());
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th5 = th4;
                                    new WrongType(classCastException, "%substring/shared", 2, obj8);
                                    throw th5;
                                }
                            } catch (UnboundLocationException e2) {
                                UnboundLocationException unboundLocationException = e2;
                                UnboundLocationException unboundLocationException2 = unboundLocationException;
                                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 226, 10);
                                throw unboundLocationException2;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException2 = e3;
                            Throwable th6 = th3;
                            new WrongType(classCastException2, "%substring/shared", 1, obj7);
                            throw th6;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException3 = e4;
                        Throwable th7 = th2;
                        new WrongType(classCastException3, "%substring/shared", 0, obj5);
                        throw th7;
                    }
                } catch (UnboundLocationException e5) {
                    UnboundLocationException unboundLocationException3 = e5;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 223, 5);
                    throw unboundLocationException4;
                }
            } catch (ClassCastException e6) {
                ClassCastException classCastException4 = e6;
                Throwable th8 = th;
                new WrongType(classCastException4, "string-length", 1, obj3);
                throw th8;
            }
        } catch (UnboundLocationException e7) {
            UnboundLocationException unboundLocationException5 = e7;
            UnboundLocationException unboundLocationException6 = unboundLocationException5;
            unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 221, 3);
            throw unboundLocationException6;
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 200:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 202:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION /*203*/:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case HttpStatus.SC_RESET_CONTENT /*205*/:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case HttpStatus.SC_MULTI_STATUS /*207*/:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 208:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 210:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 212:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 213:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 214:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 215:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 216:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 219:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 220:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 221:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 222:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 223:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case YaVersion.YOUNG_ANDROID_VERSION:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 225:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 226:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 227:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 228:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 229:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 230:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 231:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 232:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 233:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 234:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 235:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 236:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 237:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 238:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case LispEscapeFormat.ESCAPE_NORMAL:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 243:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 246:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 249:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case Telnet.WILL /*251*/:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case Telnet.f261DO /*253*/:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 256:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 258:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 261:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 264:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 266:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 268:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 269:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 270:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 272:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 273:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 274:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 275:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 276:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 278:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 279:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 284:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 285:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 286:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 288:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 290:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 291:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 292:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 293:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 294:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 295:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 296:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 297:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 298:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 299:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 302:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 303:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 304:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 305:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 307:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_SEARCH_FAILED:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 317:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 318:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 321:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 323:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 326:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 327:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 328:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame1 extends ModuleBody {
        final ModuleMethod lambda$Fn6;
        int slen;
        Object start;

        public frame1() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 5, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:227");
            this.lambda$Fn6 = moduleMethod2;
        }

        static boolean lambda5(Object obj) {
            boolean z;
            Object start2 = obj;
            boolean x = numbers.isInteger(start2);
            if (x) {
                boolean x2 = numbers.isExact(start2);
                z = x2 ? ((Boolean) Scheme.numLEq.apply2(srfi13.Lit0, start2)).booleanValue() : x2;
            } else {
                z = x;
            }
            return z;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 5) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda6(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda6(Object obj) {
            boolean z;
            Throwable th;
            Object end = obj;
            boolean x = numbers.isInteger(end);
            if (x) {
                boolean x2 = numbers.isExact(end);
                if (x2) {
                    Object apply2 = Scheme.numLEq.apply2(this.start, end);
                    Object obj2 = apply2;
                    try {
                        boolean x3 = ((Boolean) apply2).booleanValue();
                        z = x3 ? ((Boolean) Scheme.numLEq.apply2(end, Integer.valueOf(this.slen))).booleanValue() : x3;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "x", -2, obj2);
                        throw th2;
                    }
                } else {
                    z = x2;
                }
            } else {
                z = x;
            }
            return z;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 5) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 199:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 209:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 211:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 217:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 239:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 240:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case LispEscapeFormat.ESCAPE_ALL:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 244:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 245:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 247:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case ComponentConstants.LISTVIEW_PREFERRED_WIDTH:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case Telnet.WONT /*252*/:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case Telnet.DONT /*254*/:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 255:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case InputDeviceCompat.SOURCE_KEYBOARD:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 259:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 260:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 262:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 263:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 265:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 267:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 271:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 287:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 289:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 316:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 322:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 324:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 325:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Object $PcSubstring$SlShared(CharSequence charSequence, int i, int i2) {
        CharSequence s;
        CharSequence s2 = charSequence;
        int start = i;
        int end = i2;
        boolean x = numbers.isZero(Integer.valueOf(start));
        if (!x ? !x : end != strings.stringLength(s2)) {
            s = strings.substring(s2, start, end);
        } else {
            s = s2;
        }
        return s;
    }

    /* compiled from: srfi13.scm */
    public class frame2 extends ModuleBody {
        final ModuleMethod lambda$Fn7;
        final ModuleMethod lambda$Fn8;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f155s;

        public frame2() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 6, (Object) null, 0);
            this.lambda$Fn7 = moduleMethod;
            new ModuleMethod(this, 7, (Object) null, 8194);
            this.lambda$Fn8 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 6 ? lambda7() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 7 ? lambda8(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 6) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 7) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda7() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncopy, this.f155s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda8(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object start = obj;
            Object end = obj2;
            Object obj3 = this.f155s;
            Object obj4 = obj3;
            try {
                CharSequence charSequence = (CharSequence) obj3;
                Object obj5 = start;
                Object obj6 = obj5;
                try {
                    int intValue = ((Number) obj5).intValue();
                    Object obj7 = end;
                    Object obj8 = obj7;
                    try {
                        return strings.substring(charSequence, intValue, ((Number) obj7).intValue());
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th4 = th3;
                        new WrongType(classCastException, "substring", 3, obj8);
                        throw th4;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "substring", 2, obj6);
                    throw th5;
                }
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th6 = th;
                new WrongType(classCastException3, "substring", 1, obj4);
                throw th6;
            }
        }
    }

    public static Object stringCopy$V(Object s, Object[] argsArray) {
        frame2 frame210;
        new frame2();
        frame2 frame211 = frame210;
        frame211.f155s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame211.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame211.lambda$Fn7, frame211.lambda$Fn8);
    }

    public static Object stringMap$V(Object proc, Object s, Object[] argsArray) {
        frame3 frame310;
        new frame3();
        frame3 frame311 = frame310;
        frame311.proc = proc;
        frame311.f166s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame311.maybe$Mnstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame311.proc, string$Mnmap);
            return call_with_values.callWithValues(frame311.lambda$Fn9, frame311.lambda$Fn10);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 271, 3);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn9;
        LList maybe$Mnstart$Plend;
        Object proc;

        /* renamed from: s */
        Object f166s;

        public frame3() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 8, (Object) null, 0);
            this.lambda$Fn9 = moduleMethod;
            new ModuleMethod(this, 9, (Object) null, 8194);
            this.lambda$Fn10 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 8 ? lambda9() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 9 ? lambda10(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 8) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 9) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda9() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnmap, this.f166s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda10(Object start, Object end) {
            return srfi13.$PcStringMap(this.proc, this.f166s, start, end);
        }
    }

    public static Object $PcStringMap(Object obj, Object obj2, Object start, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Object proc = obj;
        Object s = obj2;
        Object end = obj3;
        Object len = AddOp.$Mn.apply2(end, start);
        Object obj4 = len;
        Object obj5 = obj4;
        try {
            Object ans = strings.makeString(((Number) obj4).intValue());
            Object apply2 = AddOp.$Mn.apply2(end, Lit1);
            Object apply22 = AddOp.$Mn.apply2(len, Lit1);
            while (true) {
                Object obj6 = apply22;
                Object i = apply2;
                if (Scheme.numLss.apply2(obj6, Lit0) != Boolean.FALSE) {
                    return ans;
                }
                Object obj7 = ans;
                Object obj8 = obj7;
                try {
                    CharSeq charSeq = (CharSeq) obj7;
                    Object obj9 = obj6;
                    Object obj10 = obj9;
                    try {
                        int intValue = ((Number) obj9).intValue();
                        Object obj11 = s;
                        Object obj12 = obj11;
                        try {
                            CharSequence charSequence = (CharSequence) obj11;
                            Object obj13 = i;
                            Object obj14 = obj13;
                            try {
                                Object apply23 = Scheme.applyToArgs.apply2(proc, Char.make(strings.stringRef(charSequence, ((Number) obj13).intValue())));
                                Object obj15 = apply23;
                                try {
                                    strings.stringSet$Ex(charSeq, intValue, ((Char) apply23).charValue());
                                    apply2 = AddOp.$Mn.apply2(i, Lit1);
                                    apply22 = AddOp.$Mn.apply2(obj6, Lit1);
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th7 = th6;
                                    new WrongType(classCastException, "string-set!", 3, obj15);
                                    throw th7;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th8 = th5;
                                new WrongType(classCastException2, "string-ref", 2, obj14);
                                throw th8;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th9 = th4;
                            new WrongType(classCastException3, "string-ref", 1, obj12);
                            throw th9;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th10 = th3;
                        new WrongType(classCastException4, "string-set!", 2, obj10);
                        throw th10;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th11 = th2;
                    new WrongType(classCastException5, "string-set!", 1, obj8);
                    throw th11;
                }
            }
        } catch (ClassCastException e6) {
            ClassCastException classCastException6 = e6;
            Throwable th12 = th;
            new WrongType(classCastException6, "make-string", 1, obj5);
            throw th12;
        }
    }

    public static Object stringMap$Ex$V(Object proc, Object s, Object[] argsArray) {
        frame4 frame410;
        new frame4();
        frame4 frame411 = frame410;
        frame411.proc = proc;
        frame411.f177s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame411.maybe$Mnstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame411.proc, string$Mnmap$Ex);
            return call_with_values.callWithValues(frame411.lambda$Fn11, frame411.lambda$Fn12);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 285, 3);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame4 extends ModuleBody {
        final ModuleMethod lambda$Fn11;
        final ModuleMethod lambda$Fn12;
        LList maybe$Mnstart$Plend;
        Object proc;

        /* renamed from: s */
        Object f177s;

        public frame4() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 10, (Object) null, 0);
            this.lambda$Fn11 = moduleMethod;
            new ModuleMethod(this, 11, (Object) null, 8194);
            this.lambda$Fn12 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 10 ? lambda11() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 11 ? lambda12(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 10) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 11) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda11() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnmap$Ex, this.f177s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda12(Object start, Object end) {
            return srfi13.$PcStringMap$Ex(this.proc, this.f177s, start, end);
        }
    }

    public static Object $PcStringMap$Ex(Object obj, Object obj2, Object obj3, Object end) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object proc = obj;
        Object s = obj2;
        Object start = obj3;
        Object apply2 = AddOp.$Mn.apply2(end, Lit1);
        while (true) {
            Object i = apply2;
            if (Scheme.numLss.apply2(i, start) != Boolean.FALSE) {
                return Values.empty;
            }
            Object obj4 = s;
            Object obj5 = obj4;
            try {
                CharSeq charSeq = (CharSeq) obj4;
                Object obj6 = i;
                Object obj7 = obj6;
                try {
                    int intValue = ((Number) obj6).intValue();
                    Object obj8 = s;
                    Object obj9 = obj8;
                    try {
                        CharSequence charSequence = (CharSequence) obj8;
                        Object obj10 = i;
                        Object obj11 = obj10;
                        try {
                            Object apply22 = Scheme.applyToArgs.apply2(proc, Char.make(strings.stringRef(charSequence, ((Number) obj10).intValue())));
                            Object obj12 = apply22;
                            try {
                                strings.stringSet$Ex(charSeq, intValue, ((Char) apply22).charValue());
                                apply2 = AddOp.$Mn.apply2(i, Lit1);
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th6 = th5;
                                new WrongType(classCastException, "string-set!", 3, obj12);
                                throw th6;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th7 = th4;
                            new WrongType(classCastException2, "string-ref", 2, obj11);
                            throw th7;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th8 = th3;
                        new WrongType(classCastException3, "string-ref", 1, obj9);
                        throw th8;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "string-set!", 2, obj7);
                    throw th9;
                }
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th10 = th;
                new WrongType(classCastException5, "string-set!", 1, obj5);
                throw th10;
            }
        }
    }

    public static Object stringFold$V(Object kons, Object knil, Object s, Object[] argsArray) {
        frame5 frame510;
        new frame5();
        frame5 frame511 = frame510;
        frame511.kons = kons;
        frame511.knil = knil;
        frame511.f188s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame511.maybe$Mnstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame511.kons, string$Mnfold);
            return call_with_values.callWithValues(frame511.lambda$Fn13, frame511.lambda$Fn14);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 295, 3);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame5 extends ModuleBody {
        Object knil;
        Object kons;
        final ModuleMethod lambda$Fn13;
        final ModuleMethod lambda$Fn14;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f188s;

        public frame5() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 12, (Object) null, 0);
            this.lambda$Fn13 = moduleMethod;
            new ModuleMethod(this, 13, (Object) null, 8194);
            this.lambda$Fn14 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 12 ? lambda13() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 13 ? lambda14(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 12) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 13) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda13() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfold, this.f188s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda14(Object start, Object obj) {
            Throwable th;
            Throwable th2;
            Object end = obj;
            Object obj2 = this.knil;
            Object obj3 = start;
            while (true) {
                Object obj4 = obj3;
                Object v = obj2;
                if (Scheme.numLss.apply2(obj4, end) == Boolean.FALSE) {
                    return v;
                }
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                Object obj5 = this.kons;
                Object obj6 = this.f188s;
                Object obj7 = obj6;
                try {
                    CharSequence charSequence = (CharSequence) obj6;
                    Object obj8 = obj4;
                    Object obj9 = obj8;
                    try {
                        obj2 = applyToArgs.apply3(obj5, Char.make(strings.stringRef(charSequence, ((Number) obj8).intValue())), v);
                        obj3 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "string-ref", 2, obj9);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-ref", 1, obj7);
                    throw th4;
                }
            }
        }
    }

    public static Object stringFoldRight$V(Object kons, Object knil, Object s, Object[] argsArray) {
        frame6 frame610;
        new frame6();
        frame6 frame611 = frame610;
        frame611.kons = kons;
        frame611.knil = knil;
        frame611.f197s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame611.maybe$Mnstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame611.kons, string$Mnfold$Mnright);
            return call_with_values.callWithValues(frame611.lambda$Fn15, frame611.lambda$Fn16);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 302, 3);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame6 extends ModuleBody {
        Object knil;
        Object kons;
        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f197s;

        public frame6() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 14, (Object) null, 0);
            this.lambda$Fn15 = moduleMethod;
            new ModuleMethod(this, 15, (Object) null, 8194);
            this.lambda$Fn16 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 14 ? lambda15() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 15 ? lambda16(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 14) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 15) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda15() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfold$Mnright, this.f197s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda16(Object obj, Object end) {
            Throwable th;
            Throwable th2;
            Object start = obj;
            Object obj2 = this.knil;
            Object apply2 = AddOp.$Mn.apply2(end, srfi13.Lit1);
            while (true) {
                Object obj3 = apply2;
                Object v = obj2;
                if (Scheme.numGEq.apply2(obj3, start) == Boolean.FALSE) {
                    return v;
                }
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                Object obj4 = this.kons;
                Object obj5 = this.f197s;
                Object obj6 = obj5;
                try {
                    CharSequence charSequence = (CharSequence) obj5;
                    Object obj7 = obj3;
                    Object obj8 = obj7;
                    try {
                        obj2 = applyToArgs.apply3(obj4, Char.make(strings.stringRef(charSequence, ((Number) obj7).intValue())), v);
                        apply2 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "string-ref", 2, obj8);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-ref", 1, obj6);
                    throw th4;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v77, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v1, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Boolean} */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0428, code lost:
        r29 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0429, code lost:
        r39 = r29;
        r29 = r40;
        new gnu.mapping.WrongType(r39, "chunk-len2", -2, r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0447, code lost:
        throw r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0448, code lost:
        r29 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0449, code lost:
        r39 = r29;
        r29 = r40;
        new gnu.mapping.WrongType(r39, "string-set!", 1, (java.lang.Object) r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0467, code lost:
        throw r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0468, code lost:
        r29 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0469, code lost:
        r39 = r29;
        r29 = r40;
        new gnu.mapping.WrongType(r39, "string-set!", 3, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0487, code lost:
        throw r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x014b, code lost:
        r39 = new java.lang.Object[2];
        r39[0] = Lit2;
        r39 = r39;
        r39[1] = java.lang.Integer.valueOf(r11 + r9);
        r39 = kawa.lib.numbers.min(r39);
        r20 = r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0188, code lost:
        r19 = ((java.lang.Number) r39).intValue();
        r20 = kawa.lib.strings.makeString(r19);
        r39 = r20;
        r21 = r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r33 = (gnu.lists.CharSeq) r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x019f, code lost:
        r39 = r16;
        r21 = r39;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringUnfold$V(java.lang.Object r41, java.lang.Object r42, java.lang.Object r43, java.lang.Object r44, java.lang.Object[] r45) {
        /*
            r2 = r41
            r3 = r42
            r4 = r43
            r5 = r44
            r6 = r45
            r29 = r6
            r30 = 0
            gnu.lists.LList r29 = gnu.lists.LList.makeList(r29, r30)
            r39 = r29
            r29 = r39
            r30 = r39
            r8 = r30
            r7 = r29
            gnu.kawa.functions.ApplyToArgs r29 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r30 = loc$check$Mnarg
            java.lang.Object r30 = r30.get()     // Catch:{ UnboundLocationException -> 0x0338 }
            gnu.expr.ModuleMethod r31 = kawa.lib.misc.procedure$Qu
            r32 = r2
            gnu.expr.ModuleMethod r33 = string$Mnunfold
            java.lang.Object r29 = r29.apply4(r30, r31, r32, r33)
            gnu.kawa.functions.ApplyToArgs r29 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r30 = loc$check$Mnarg
            java.lang.Object r30 = r30.get()     // Catch:{ UnboundLocationException -> 0x034a }
            gnu.expr.ModuleMethod r31 = kawa.lib.misc.procedure$Qu
            r32 = r3
            gnu.expr.ModuleMethod r33 = string$Mnunfold
            java.lang.Object r29 = r29.apply4(r30, r31, r32, r33)
            gnu.kawa.functions.ApplyToArgs r29 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r30 = loc$check$Mnarg
            java.lang.Object r30 = r30.get()     // Catch:{ UnboundLocationException -> 0x035c }
            gnu.expr.ModuleMethod r31 = kawa.lib.misc.procedure$Qu
            r32 = r4
            gnu.expr.ModuleMethod r33 = string$Mnunfold
            java.lang.Object r29 = r29.apply4(r30, r31, r32, r33)
            gnu.kawa.functions.ApplyToArgs r29 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r30 = loc$let$Mnoptionals$St
            java.lang.Object r30 = r30.get()     // Catch:{ UnboundLocationException -> 0x036e }
            r31 = r7
            gnu.kawa.functions.ApplyToArgs r32 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r33 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r34 = loc$base
            java.lang.Object r34 = r34.get()     // Catch:{ UnboundLocationException -> 0x0380 }
            java.lang.String r35 = ""
            gnu.mapping.Location r36 = loc$base
            java.lang.Object r36 = r36.get()     // Catch:{ UnboundLocationException -> 0x0392 }
            boolean r36 = kawa.lib.strings.isString(r36)
            if (r36 == 0) goto L_0x0143
            java.lang.Boolean r36 = java.lang.Boolean.TRUE
        L_0x0077:
            java.lang.Object r33 = r33.apply3(r34, r35, r36)
            gnu.kawa.functions.ApplyToArgs r34 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r35 = loc$make$Mnfinal
            java.lang.Object r35 = r35.get()     // Catch:{ UnboundLocationException -> 0x03a4 }
            gnu.expr.ModuleMethod r36 = lambda$Fn17
            gnu.mapping.Location r37 = loc$make$Mnfinal
            java.lang.Object r37 = r37.get()     // Catch:{ UnboundLocationException -> 0x03b6 }
            boolean r37 = kawa.lib.misc.isProcedure(r37)
            if (r37 == 0) goto L_0x0147
            java.lang.Boolean r37 = java.lang.Boolean.TRUE
        L_0x0093:
            java.lang.Object r34 = r34.apply3(r35, r36, r37)
            java.lang.Object r32 = r32.apply2(r33, r34)
            gnu.lists.LList r33 = gnu.lists.LList.Empty
            r34 = 0
            r35 = 40
            java.lang.CharSequence r35 = kawa.lib.strings.makeString(r35)
            r36 = 40
            r37 = 0
            r38 = r5
            r13 = r38
            r12 = r37
            r11 = r36
            r10 = r35
            r9 = r34
            r8 = r33
        L_0x00b7:
            r33 = r12
            java.lang.Integer r33 = java.lang.Integer.valueOf(r33)
            r34 = r13
            r15 = r34
            r14 = r33
        L_0x00c3:
            gnu.kawa.functions.ApplyToArgs r33 = kawa.standard.Scheme.applyToArgs
            r34 = r2
            r35 = r15
            java.lang.Object r33 = r33.apply2(r34, r35)
            java.lang.Boolean r34 = java.lang.Boolean.FALSE
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x01d8
            gnu.kawa.functions.ApplyToArgs r33 = kawa.standard.Scheme.applyToArgs
            r34 = r3
            r35 = r15
            java.lang.Object r33 = r33.apply2(r34, r35)
            gnu.kawa.functions.ApplyToArgs r34 = kawa.standard.Scheme.applyToArgs
            r35 = r4
            r36 = r15
            java.lang.Object r34 = r34.apply2(r35, r36)
            r17 = r34
            r16 = r33
            gnu.kawa.functions.NumberCompare r33 = kawa.standard.Scheme.numLss
            r34 = r14
            r35 = r11
            java.lang.Integer r35 = java.lang.Integer.valueOf(r35)
            java.lang.Object r33 = r33.apply2(r34, r35)
            java.lang.Boolean r34 = java.lang.Boolean.FALSE
            r0 = r33
            r1 = r34
            if (r0 == r1) goto L_0x014b
            r33 = r10
            r39 = r33
            r33 = r39
            r34 = r39
            r18 = r34
            gnu.lists.CharSeq r33 = (gnu.lists.CharSeq) r33     // Catch:{ ClassCastException -> 0x03c8 }
            r34 = r14
            r39 = r34
            r34 = r39
            r35 = r39
            r18 = r35
            java.lang.Number r34 = (java.lang.Number) r34     // Catch:{ ClassCastException -> 0x03e8 }
            int r34 = r34.intValue()     // Catch:{ ClassCastException -> 0x03e8 }
            r35 = r16
            r39 = r35
            r35 = r39
            r36 = r39
            r18 = r36
            gnu.text.Char r35 = (gnu.text.Char) r35     // Catch:{ ClassCastException -> 0x0408 }
            char r35 = r35.charValue()     // Catch:{ ClassCastException -> 0x0408 }
            kawa.lib.strings.stringSet$Ex(r33, r34, r35)
            gnu.kawa.functions.AddOp r33 = gnu.kawa.functions.AddOp.$Pl
            r34 = r14
            gnu.math.IntNum r35 = Lit1
            java.lang.Object r33 = r33.apply2(r34, r35)
            r34 = r17
            r15 = r34
            r14 = r33
            goto L_0x00c3
        L_0x0143:
            java.lang.Boolean r36 = java.lang.Boolean.FALSE
            goto L_0x0077
        L_0x0147:
            java.lang.Boolean r37 = java.lang.Boolean.FALSE
            goto L_0x0093
        L_0x014b:
            r33 = r11
            r34 = r9
            int r33 = r33 + r34
            r18 = r33
            r33 = 2
            r0 = r33
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r33 = r0
            r39 = r33
            r33 = r39
            r34 = r39
            r35 = 0
            gnu.math.IntNum r36 = Lit2
            r34[r35] = r36
            r39 = r33
            r33 = r39
            r34 = r39
            r35 = 1
            r36 = r18
            java.lang.Integer r36 = java.lang.Integer.valueOf(r36)
            r34[r35] = r36
            java.lang.Object r33 = kawa.lib.numbers.min(r33)
            r39 = r33
            r33 = r39
            r34 = r39
            r20 = r34
            java.lang.Number r33 = (java.lang.Number) r33     // Catch:{ ClassCastException -> 0x0428 }
            int r33 = r33.intValue()     // Catch:{ ClassCastException -> 0x0428 }
            r19 = r33
            r33 = r19
            java.lang.CharSequence r33 = kawa.lib.strings.makeString(r33)
            r20 = r33
            r33 = r20
            r39 = r33
            r33 = r39
            r34 = r39
            r21 = r34
            gnu.lists.CharSeq r33 = (gnu.lists.CharSeq) r33     // Catch:{ ClassCastException -> 0x0448 }
            r34 = 0
            r35 = r16
            r39 = r35
            r35 = r39
            r36 = r39
            r21 = r36
            gnu.text.Char r35 = (gnu.text.Char) r35     // Catch:{ ClassCastException -> 0x0468 }
            char r35 = r35.charValue()     // Catch:{ ClassCastException -> 0x0468 }
            kawa.lib.strings.stringSet$Ex(r33, r34, r35)
            r33 = r10
            r34 = r8
            gnu.lists.Pair r33 = kawa.lib.C1245lists.cons(r33, r34)
            r34 = r9
            r35 = r11
            int r34 = r34 + r35
            r35 = r20
            r36 = r19
            r37 = 1
            r38 = r17
            r13 = r38
            r12 = r37
            r11 = r36
            r10 = r35
            r9 = r34
            r8 = r33
            goto L_0x00b7
        L_0x01d8:
            gnu.kawa.functions.ApplyToArgs r33 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r34 = loc$make$Mnfinal
            java.lang.Object r34 = r34.get()     // Catch:{ UnboundLocationException -> 0x0488 }
            r35 = r15
            java.lang.Object r33 = r33.apply2(r34, r35)
            r16 = r33
            r33 = r16
            r39 = r33
            r33 = r39
            r34 = r39
            r18 = r34
            java.lang.CharSequence r33 = (java.lang.CharSequence) r33     // Catch:{ ClassCastException -> 0x049a }
            int r33 = kawa.lib.strings.stringLength(r33)
            r17 = r33
            gnu.mapping.Location r33 = loc$base
            java.lang.Object r33 = r33.get()     // Catch:{ UnboundLocationException -> 0x04ba }
            r39 = r33
            r33 = r39
            r34 = r39
            r19 = r34
            java.lang.CharSequence r33 = (java.lang.CharSequence) r33     // Catch:{ ClassCastException -> 0x04cc }
            int r33 = kawa.lib.strings.stringLength(r33)
            r18 = r33
            gnu.kawa.functions.AddOp r33 = gnu.kawa.functions.AddOp.$Pl
            r34 = r18
            r35 = r9
            int r34 = r34 + r35
            java.lang.Integer r34 = java.lang.Integer.valueOf(r34)
            r35 = r14
            java.lang.Object r33 = r33.apply2(r34, r35)
            r39 = r33
            r33 = r39
            r34 = r39
            r20 = r34
            java.lang.Number r33 = (java.lang.Number) r33     // Catch:{ ClassCastException -> 0x04ec }
            int r33 = r33.intValue()     // Catch:{ ClassCastException -> 0x04ec }
            r19 = r33
            r33 = r19
            r34 = r17
            int r33 = r33 + r34
            java.lang.CharSequence r33 = kawa.lib.strings.makeString(r33)
            r20 = r33
            r33 = r20
            r34 = r19
            r35 = r16
            r39 = r35
            r35 = r39
            r36 = r39
            r21 = r36
            java.lang.CharSequence r35 = (java.lang.CharSequence) r35     // Catch:{ ClassCastException -> 0x050c }
            r36 = 0
            r37 = r17
            java.lang.Object r33 = $PcStringCopy$Ex(r33, r34, r35, r36, r37)
            gnu.kawa.functions.AddOp r33 = gnu.kawa.functions.AddOp.$Mn
            r34 = r19
            java.lang.Integer r34 = java.lang.Integer.valueOf(r34)
            r35 = r14
            java.lang.Object r33 = r33.apply2(r34, r35)
            r39 = r33
            r33 = r39
            r34 = r39
            r22 = r34
            java.lang.Number r33 = (java.lang.Number) r33     // Catch:{ ClassCastException -> 0x052c }
            int r33 = r33.intValue()     // Catch:{ ClassCastException -> 0x052c }
            r21 = r33
            r33 = r20
            r34 = r21
            r35 = r10
            r39 = r35
            r35 = r39
            r36 = r39
            r22 = r36
            java.lang.CharSequence r35 = (java.lang.CharSequence) r35     // Catch:{ ClassCastException -> 0x054c }
            r36 = 0
            r37 = r14
            r39 = r37
            r37 = r39
            r38 = r39
            r22 = r38
            java.lang.Number r37 = (java.lang.Number) r37     // Catch:{ ClassCastException -> 0x056c }
            int r37 = r37.intValue()     // Catch:{ ClassCastException -> 0x056c }
            java.lang.Object r33 = $PcStringCopy$Ex(r33, r34, r35, r36, r37)
            r33 = r21
            java.lang.Integer r33 = java.lang.Integer.valueOf(r33)
            r34 = r8
            r23 = r34
            r22 = r33
        L_0x02a6:
            r33 = r23
            boolean r33 = kawa.lib.C1245lists.isPair(r33)
            if (r33 == 0) goto L_0x0313
            gnu.expr.GenericProc r33 = kawa.lib.C1245lists.car
            r34 = r23
            java.lang.Object r33 = r33.apply1(r34)
            r24 = r33
            gnu.expr.GenericProc r33 = kawa.lib.C1245lists.cdr
            r34 = r23
            java.lang.Object r33 = r33.apply1(r34)
            r25 = r33
            r33 = r24
            r39 = r33
            r33 = r39
            r34 = r39
            r27 = r34
            java.lang.CharSequence r33 = (java.lang.CharSequence) r33     // Catch:{ ClassCastException -> 0x058c }
            int r33 = kawa.lib.strings.stringLength(r33)
            r26 = r33
            gnu.kawa.functions.AddOp r33 = gnu.kawa.functions.AddOp.$Mn
            r34 = r22
            r35 = r26
            java.lang.Integer r35 = java.lang.Integer.valueOf(r35)
            java.lang.Object r33 = r33.apply2(r34, r35)
            r27 = r33
            r33 = r20
            r34 = r27
            r39 = r34
            r34 = r39
            r35 = r39
            r28 = r35
            java.lang.Number r34 = (java.lang.Number) r34     // Catch:{ ClassCastException -> 0x05ac }
            int r34 = r34.intValue()     // Catch:{ ClassCastException -> 0x05ac }
            r35 = r24
            r39 = r35
            r35 = r39
            r36 = r39
            r28 = r36
            java.lang.CharSequence r35 = (java.lang.CharSequence) r35     // Catch:{ ClassCastException -> 0x05cc }
            r36 = 0
            r37 = r26
            java.lang.Object r33 = $PcStringCopy$Ex(r33, r34, r35, r36, r37)
            r33 = r27
            r34 = r25
            r23 = r34
            r22 = r33
            goto L_0x02a6
        L_0x0313:
            r33 = r20
            r34 = 0
            gnu.mapping.Location r35 = loc$base
            java.lang.Object r35 = r35.get()     // Catch:{ UnboundLocationException -> 0x05ec }
            r39 = r35
            r35 = r39
            r36 = r39
            r21 = r36
            java.lang.CharSequence r35 = (java.lang.CharSequence) r35     // Catch:{ ClassCastException -> 0x05fe }
            r36 = 0
            r37 = r18
            java.lang.Object r33 = $PcStringCopy$Ex(r33, r34, r35, r36, r37)
            r33 = r20
            java.lang.Object r29 = r29.apply4(r30, r31, r32, r33)
            r2 = r29
            return r2
        L_0x0338:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 372(0x174, float:5.21E-43)
            r33 = 3
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x034a:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 373(0x175, float:5.23E-43)
            r33 = 3
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x035c:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 374(0x176, float:5.24E-43)
            r33 = 3
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x036e:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 375(0x177, float:5.25E-43)
            r33 = 3
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x0380:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 376(0x178, float:5.27E-43)
            r33 = 20
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x0392:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 376(0x178, float:5.27E-43)
            r33 = 57
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x03a4:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 377(0x179, float:5.28E-43)
            r33 = 6
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x03b6:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 377(0x179, float:5.28E-43)
            r33 = 46
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x03c8:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "string-set!"
            r33 = 1
            r34 = r18
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x03e8:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "string-set!"
            r33 = 2
            r34 = r18
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x0408:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "string-set!"
            r33 = 3
            r34 = r18
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x0428:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "chunk-len2"
            r33 = -2
            r34 = r20
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x0448:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "string-set!"
            r33 = 1
            r34 = r21
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x0468:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "string-set!"
            r33 = 3
            r34 = r21
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x0488:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 400(0x190, float:5.6E-43)
            r33 = 20
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x049a:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "string-length"
            r33 = 1
            r34 = r18
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x04ba:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 402(0x192, float:5.63E-43)
            r33 = 38
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x04cc:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "string-length"
            r33 = 1
            r34 = r19
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x04ec:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "j"
            r33 = -2
            r34 = r20
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x050c:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "%string-copy!"
            r33 = 2
            r34 = r21
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x052c:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "j"
            r33 = -2
            r34 = r22
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x054c:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "%string-copy!"
            r33 = 2
            r34 = r22
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x056c:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "%string-copy!"
            r33 = 4
            r34 = r22
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x058c:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "string-length"
            r33 = 1
            r34 = r27
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x05ac:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "%string-copy!"
            r33 = 1
            r34 = r28
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x05cc:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "%string-copy!"
            r33 = 2
            r34 = r28
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        L_0x05ec:
            r29 = move-exception
            r39 = r29
            r29 = r39
            r30 = r39
            java.lang.String r31 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r32 = 416(0x1a0, float:5.83E-43)
            r33 = 29
            r30.setLine(r31, r32, r33)
            throw r29
        L_0x05fe:
            r29 = move-exception
            gnu.mapping.WrongType r30 = new gnu.mapping.WrongType
            r39 = r29
            r40 = r30
            r29 = r40
            r30 = r39
            r31 = r40
            r39 = r30
            r40 = r31
            r30 = r40
            r31 = r39
            java.lang.String r32 = "%string-copy!"
            r33 = 2
            r34 = r21
            r30.<init>((java.lang.ClassCastException) r31, (java.lang.String) r32, (int) r33, (java.lang.Object) r34)
            throw r29
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringUnfold$V(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    static String lambda17(Object obj) {
        Object obj2 = obj;
        return "";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v83, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v2, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v91, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v101, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: gnu.math.IntNum} */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x03f0, code lost:
        r28 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x03f1, code lost:
        r38 = r28;
        r28 = r39;
        new gnu.mapping.WrongType(r38, "make-string", 1, (java.lang.Object) r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x040f, code lost:
        throw r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0410, code lost:
        r28 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0411, code lost:
        r38 = r28;
        r28 = r39;
        new gnu.mapping.WrongType(r38, "string-set!", 1, (java.lang.Object) r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x042f, code lost:
        throw r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0430, code lost:
        r28 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0431, code lost:
        r38 = r28;
        r28 = r39;
        new gnu.mapping.WrongType(r38, "string-set!", 2, (java.lang.Object) r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x044f, code lost:
        throw r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0450, code lost:
        r28 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0451, code lost:
        r38 = r28;
        r28 = r39;
        new gnu.mapping.WrongType(r38, "string-set!", 3, r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x046f, code lost:
        throw r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0111, code lost:
        r18 = gnu.kawa.functions.AddOp.$Pl.apply2(r11, r9);
        r38 = new java.lang.Object[2];
        r38[0] = Lit4;
        r38 = r38;
        r38[1] = r18;
        r19 = kawa.lib.numbers.min(r38);
        r38 = r19;
        r21 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0152, code lost:
        r20 = kawa.lib.strings.makeString(r38.intValue());
        r21 = gnu.kawa.functions.AddOp.$Mn.apply2(r19, Lit1);
        r38 = r20;
        r22 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r32 = (gnu.lists.CharSeq) r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0171, code lost:
        r38 = r21;
        r22 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r33 = r38.intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0180, code lost:
        r38 = r16;
        r22 = r38;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringUnfoldRight$V(java.lang.Object r40, java.lang.Object r41, java.lang.Object r42, java.lang.Object r43, java.lang.Object[] r44) {
        /*
            r2 = r40
            r3 = r41
            r4 = r42
            r5 = r43
            r6 = r44
            r28 = r6
            r29 = 0
            gnu.lists.LList r28 = gnu.lists.LList.makeList(r28, r29)
            r38 = r28
            r28 = r38
            r29 = r38
            r8 = r29
            r7 = r28
            gnu.kawa.functions.ApplyToArgs r28 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r29 = loc$let$Mnoptionals$St
            java.lang.Object r29 = r29.get()     // Catch:{ UnboundLocationException -> 0x0336 }
            r30 = r7
            gnu.kawa.functions.ApplyToArgs r31 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r32 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r33 = loc$base
            java.lang.Object r33 = r33.get()     // Catch:{ UnboundLocationException -> 0x0348 }
            java.lang.String r34 = ""
            gnu.mapping.Location r35 = loc$base
            java.lang.Object r35 = r35.get()     // Catch:{ UnboundLocationException -> 0x035a }
            boolean r35 = kawa.lib.strings.isString(r35)
            if (r35 == 0) goto L_0x0109
            java.lang.Boolean r35 = java.lang.Boolean.TRUE
        L_0x0041:
            java.lang.Object r32 = r32.apply3(r33, r34, r35)
            gnu.kawa.functions.ApplyToArgs r33 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r34 = loc$make$Mnfinal
            java.lang.Object r34 = r34.get()     // Catch:{ UnboundLocationException -> 0x036c }
            gnu.expr.ModuleMethod r35 = lambda$Fn18
            gnu.mapping.Location r36 = loc$make$Mnfinal
            java.lang.Object r36 = r36.get()     // Catch:{ UnboundLocationException -> 0x037e }
            boolean r36 = kawa.lib.misc.isProcedure(r36)
            if (r36 == 0) goto L_0x010d
            java.lang.Boolean r36 = java.lang.Boolean.TRUE
        L_0x005d:
            java.lang.Object r33 = r33.apply3(r34, r35, r36)
            java.lang.Object r31 = r31.apply2(r32, r33)
            gnu.lists.LList r32 = gnu.lists.LList.Empty
            gnu.math.IntNum r33 = Lit0
            r34 = 40
            java.lang.CharSequence r34 = kawa.lib.strings.makeString(r34)
            gnu.math.IntNum r35 = Lit3
            gnu.math.IntNum r36 = Lit3
            r37 = r5
            r13 = r37
            r12 = r36
            r11 = r35
            r10 = r34
            r9 = r33
            r8 = r32
        L_0x0081:
            r32 = r12
            r33 = r13
            r15 = r33
            r14 = r32
        L_0x0089:
            gnu.kawa.functions.ApplyToArgs r32 = kawa.standard.Scheme.applyToArgs
            r33 = r2
            r34 = r15
            java.lang.Object r32 = r32.apply2(r33, r34)
            java.lang.Boolean r33 = java.lang.Boolean.FALSE
            r0 = r32
            r1 = r33
            if (r0 != r1) goto L_0x01bc
            gnu.kawa.functions.ApplyToArgs r32 = kawa.standard.Scheme.applyToArgs
            r33 = r3
            r34 = r15
            java.lang.Object r32 = r32.apply2(r33, r34)
            gnu.kawa.functions.ApplyToArgs r33 = kawa.standard.Scheme.applyToArgs
            r34 = r4
            r35 = r15
            java.lang.Object r33 = r33.apply2(r34, r35)
            r17 = r33
            r16 = r32
            gnu.kawa.functions.NumberCompare r32 = kawa.standard.Scheme.numGrt
            r33 = r14
            gnu.math.IntNum r34 = Lit0
            java.lang.Object r32 = r32.apply2(r33, r34)
            java.lang.Boolean r33 = java.lang.Boolean.FALSE
            r0 = r32
            r1 = r33
            if (r0 == r1) goto L_0x0111
            gnu.kawa.functions.AddOp r32 = gnu.kawa.functions.AddOp.$Mn
            r33 = r14
            gnu.math.IntNum r34 = Lit1
            java.lang.Object r32 = r32.apply2(r33, r34)
            r18 = r32
            r32 = r10
            r38 = r32
            r32 = r38
            r33 = r38
            r19 = r33
            gnu.lists.CharSeq r32 = (gnu.lists.CharSeq) r32     // Catch:{ ClassCastException -> 0x0390 }
            r33 = r18
            r38 = r33
            r33 = r38
            r34 = r38
            r19 = r34
            java.lang.Number r33 = (java.lang.Number) r33     // Catch:{ ClassCastException -> 0x03b0 }
            int r33 = r33.intValue()     // Catch:{ ClassCastException -> 0x03b0 }
            r34 = r16
            r38 = r34
            r34 = r38
            r35 = r38
            r19 = r35
            gnu.text.Char r34 = (gnu.text.Char) r34     // Catch:{ ClassCastException -> 0x03d0 }
            char r34 = r34.charValue()     // Catch:{ ClassCastException -> 0x03d0 }
            kawa.lib.strings.stringSet$Ex(r32, r33, r34)
            r32 = r18
            r33 = r17
            r15 = r33
            r14 = r32
            goto L_0x0089
        L_0x0109:
            java.lang.Boolean r35 = java.lang.Boolean.FALSE
            goto L_0x0041
        L_0x010d:
            java.lang.Boolean r36 = java.lang.Boolean.FALSE
            goto L_0x005d
        L_0x0111:
            gnu.kawa.functions.AddOp r32 = gnu.kawa.functions.AddOp.$Pl
            r33 = r11
            r34 = r9
            java.lang.Object r32 = r32.apply2(r33, r34)
            r18 = r32
            r32 = 2
            r0 = r32
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r32 = r0
            r38 = r32
            r32 = r38
            r33 = r38
            r34 = 0
            gnu.math.IntNum r35 = Lit4
            r33[r34] = r35
            r38 = r32
            r32 = r38
            r33 = r38
            r34 = 1
            r35 = r18
            r33[r34] = r35
            java.lang.Object r32 = kawa.lib.numbers.min(r32)
            r19 = r32
            r32 = r19
            r38 = r32
            r32 = r38
            r33 = r38
            r21 = r33
            java.lang.Number r32 = (java.lang.Number) r32     // Catch:{ ClassCastException -> 0x03f0 }
            int r32 = r32.intValue()     // Catch:{ ClassCastException -> 0x03f0 }
            java.lang.CharSequence r32 = kawa.lib.strings.makeString(r32)
            r20 = r32
            gnu.kawa.functions.AddOp r32 = gnu.kawa.functions.AddOp.$Mn
            r33 = r19
            gnu.math.IntNum r34 = Lit1
            java.lang.Object r32 = r32.apply2(r33, r34)
            r21 = r32
            r32 = r20
            r38 = r32
            r32 = r38
            r33 = r38
            r22 = r33
            gnu.lists.CharSeq r32 = (gnu.lists.CharSeq) r32     // Catch:{ ClassCastException -> 0x0410 }
            r33 = r21
            r38 = r33
            r33 = r38
            r34 = r38
            r22 = r34
            java.lang.Number r33 = (java.lang.Number) r33     // Catch:{ ClassCastException -> 0x0430 }
            int r33 = r33.intValue()     // Catch:{ ClassCastException -> 0x0430 }
            r34 = r16
            r38 = r34
            r34 = r38
            r35 = r38
            r22 = r35
            gnu.text.Char r34 = (gnu.text.Char) r34     // Catch:{ ClassCastException -> 0x0450 }
            char r34 = r34.charValue()     // Catch:{ ClassCastException -> 0x0450 }
            kawa.lib.strings.stringSet$Ex(r32, r33, r34)
            r32 = r10
            r33 = r8
            gnu.lists.Pair r32 = kawa.lib.C1245lists.cons(r32, r33)
            gnu.kawa.functions.AddOp r33 = gnu.kawa.functions.AddOp.$Pl
            r34 = r9
            r35 = r11
            java.lang.Object r33 = r33.apply2(r34, r35)
            r34 = r20
            r35 = r19
            r36 = r21
            r37 = r17
            r13 = r37
            r12 = r36
            r11 = r35
            r10 = r34
            r9 = r33
            r8 = r32
            goto L_0x0081
        L_0x01bc:
            gnu.kawa.functions.ApplyToArgs r32 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r33 = loc$make$Mnfinal
            java.lang.Object r33 = r33.get()     // Catch:{ UnboundLocationException -> 0x0470 }
            r34 = r15
            java.lang.Object r32 = r32.apply2(r33, r34)
            r16 = r32
            r32 = r16
            r38 = r32
            r32 = r38
            r33 = r38
            r18 = r33
            java.lang.CharSequence r32 = (java.lang.CharSequence) r32     // Catch:{ ClassCastException -> 0x0482 }
            int r32 = kawa.lib.strings.stringLength(r32)
            r17 = r32
            gnu.mapping.Location r32 = loc$base
            java.lang.Object r32 = r32.get()     // Catch:{ UnboundLocationException -> 0x04a2 }
            r38 = r32
            r32 = r38
            r33 = r38
            r19 = r33
            java.lang.CharSequence r32 = (java.lang.CharSequence) r32     // Catch:{ ClassCastException -> 0x04b4 }
            int r32 = kawa.lib.strings.stringLength(r32)
            r18 = r32
            gnu.kawa.functions.AddOp r32 = gnu.kawa.functions.AddOp.$Mn
            r33 = r11
            r34 = r14
            java.lang.Object r32 = r32.apply2(r33, r34)
            r19 = r32
            gnu.kawa.functions.AddOp r32 = gnu.kawa.functions.AddOp.$Pl
            gnu.kawa.functions.AddOp r33 = gnu.kawa.functions.AddOp.$Pl
            r34 = r18
            java.lang.Integer r34 = java.lang.Integer.valueOf(r34)
            r35 = r9
            java.lang.Object r33 = r33.apply2(r34, r35)
            r34 = r19
            java.lang.Object r32 = r32.apply2(r33, r34)
            r20 = r32
            gnu.kawa.functions.AddOp r32 = gnu.kawa.functions.AddOp.$Pl
            r33 = r20
            r34 = r17
            java.lang.Integer r34 = java.lang.Integer.valueOf(r34)
            java.lang.Object r32 = r32.apply2(r33, r34)
            r38 = r32
            r32 = r38
            r33 = r38
            r22 = r33
            java.lang.Number r32 = (java.lang.Number) r32     // Catch:{ ClassCastException -> 0x04d4 }
            int r32 = r32.intValue()     // Catch:{ ClassCastException -> 0x04d4 }
            java.lang.CharSequence r32 = kawa.lib.strings.makeString(r32)
            r21 = r32
            r32 = r21
            r33 = 0
            r34 = r16
            r38 = r34
            r34 = r38
            r35 = r38
            r22 = r35
            java.lang.CharSequence r34 = (java.lang.CharSequence) r34     // Catch:{ ClassCastException -> 0x04f4 }
            r35 = 0
            r36 = r17
            java.lang.Object r32 = $PcStringCopy$Ex(r32, r33, r34, r35, r36)
            r32 = r21
            r33 = r17
            r34 = r10
            r38 = r34
            r34 = r38
            r35 = r38
            r22 = r35
            java.lang.CharSequence r34 = (java.lang.CharSequence) r34     // Catch:{ ClassCastException -> 0x0514 }
            r35 = r14
            r38 = r35
            r35 = r38
            r36 = r38
            r22 = r36
            java.lang.Number r35 = (java.lang.Number) r35     // Catch:{ ClassCastException -> 0x0534 }
            int r35 = r35.intValue()     // Catch:{ ClassCastException -> 0x0534 }
            r36 = r11
            r38 = r36
            r36 = r38
            r37 = r38
            r22 = r37
            java.lang.Number r36 = (java.lang.Number) r36     // Catch:{ ClassCastException -> 0x0554 }
            int r36 = r36.intValue()     // Catch:{ ClassCastException -> 0x0554 }
            java.lang.Object r32 = $PcStringCopy$Ex(r32, r33, r34, r35, r36)
            gnu.kawa.functions.AddOp r32 = gnu.kawa.functions.AddOp.$Pl
            r33 = r17
            java.lang.Integer r33 = java.lang.Integer.valueOf(r33)
            r34 = r19
            java.lang.Object r32 = r32.apply2(r33, r34)
            r33 = r8
            r23 = r33
            r22 = r32
        L_0x029a:
            r32 = r23
            boolean r32 = kawa.lib.C1245lists.isPair(r32)
            if (r32 == 0) goto L_0x0303
            gnu.expr.GenericProc r32 = kawa.lib.C1245lists.car
            r33 = r23
            java.lang.Object r32 = r32.apply1(r33)
            r24 = r32
            gnu.expr.GenericProc r32 = kawa.lib.C1245lists.cdr
            r33 = r23
            java.lang.Object r32 = r32.apply1(r33)
            r25 = r32
            r32 = r24
            r38 = r32
            r32 = r38
            r33 = r38
            r27 = r33
            java.lang.CharSequence r32 = (java.lang.CharSequence) r32     // Catch:{ ClassCastException -> 0x0574 }
            int r32 = kawa.lib.strings.stringLength(r32)
            r26 = r32
            r32 = r21
            r33 = r22
            r38 = r33
            r33 = r38
            r34 = r38
            r27 = r34
            java.lang.Number r33 = (java.lang.Number) r33     // Catch:{ ClassCastException -> 0x0594 }
            int r33 = r33.intValue()     // Catch:{ ClassCastException -> 0x0594 }
            r34 = r24
            r38 = r34
            r34 = r38
            r35 = r38
            r27 = r35
            java.lang.CharSequence r34 = (java.lang.CharSequence) r34     // Catch:{ ClassCastException -> 0x05b4 }
            r35 = 0
            r36 = r26
            java.lang.Object r32 = $PcStringCopy$Ex(r32, r33, r34, r35, r36)
            gnu.kawa.functions.AddOp r32 = gnu.kawa.functions.AddOp.$Pl
            r33 = r22
            r34 = r26
            java.lang.Integer r34 = java.lang.Integer.valueOf(r34)
            java.lang.Object r32 = r32.apply2(r33, r34)
            r33 = r25
            r23 = r33
            r22 = r32
            goto L_0x029a
        L_0x0303:
            r32 = r21
            r33 = r22
            r38 = r33
            r33 = r38
            r34 = r38
            r24 = r34
            java.lang.Number r33 = (java.lang.Number) r33     // Catch:{ ClassCastException -> 0x05d4 }
            int r33 = r33.intValue()     // Catch:{ ClassCastException -> 0x05d4 }
            gnu.mapping.Location r34 = loc$base
            java.lang.Object r34 = r34.get()     // Catch:{ UnboundLocationException -> 0x05f4 }
            r38 = r34
            r34 = r38
            r35 = r38
            r24 = r35
            java.lang.CharSequence r34 = (java.lang.CharSequence) r34     // Catch:{ ClassCastException -> 0x0606 }
            r35 = 0
            r36 = r18
            java.lang.Object r32 = $PcStringCopy$Ex(r32, r33, r34, r35, r36)
            r32 = r21
            java.lang.Object r28 = r28.apply4(r29, r30, r31, r32)
            r2 = r28
            return r2
        L_0x0336:
            r28 = move-exception
            r38 = r28
            r28 = r38
            r29 = r38
            java.lang.String r30 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r31 = 420(0x1a4, float:5.89E-43)
            r32 = 3
            r29.setLine(r30, r31, r32)
            throw r28
        L_0x0348:
            r28 = move-exception
            r38 = r28
            r28 = r38
            r29 = r38
            java.lang.String r30 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r31 = 421(0x1a5, float:5.9E-43)
            r32 = 20
            r29.setLine(r30, r31, r32)
            throw r28
        L_0x035a:
            r28 = move-exception
            r38 = r28
            r28 = r38
            r29 = r38
            java.lang.String r30 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r31 = 421(0x1a5, float:5.9E-43)
            r32 = 57
            r29.setLine(r30, r31, r32)
            throw r28
        L_0x036c:
            r28 = move-exception
            r38 = r28
            r28 = r38
            r29 = r38
            java.lang.String r30 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r31 = 422(0x1a6, float:5.91E-43)
            r32 = 6
            r29.setLine(r30, r31, r32)
            throw r28
        L_0x037e:
            r28 = move-exception
            r38 = r28
            r28 = r38
            r29 = r38
            java.lang.String r30 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r31 = 422(0x1a6, float:5.91E-43)
            r32 = 46
            r29.setLine(r30, r31, r32)
            throw r28
        L_0x0390:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-set!"
            r32 = 1
            r33 = r19
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x03b0:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-set!"
            r32 = 2
            r33 = r19
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x03d0:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-set!"
            r32 = 3
            r33 = r19
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x03f0:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "make-string"
            r32 = 1
            r33 = r21
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0410:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-set!"
            r32 = 1
            r33 = r22
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0430:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-set!"
            r32 = 2
            r33 = r22
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0450:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-set!"
            r32 = 3
            r33 = r22
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0470:
            r28 = move-exception
            r38 = r28
            r28 = r38
            r29 = r38
            java.lang.String r30 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r31 = 447(0x1bf, float:6.26E-43)
            r32 = 20
            r29.setLine(r30, r31, r32)
            throw r28
        L_0x0482:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-length"
            r32 = 1
            r33 = r18
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x04a2:
            r28 = move-exception
            r38 = r28
            r28 = r38
            r29 = r38
            java.lang.String r30 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r31 = 449(0x1c1, float:6.29E-43)
            r32 = 31
            r29.setLine(r30, r31, r32)
            throw r28
        L_0x04b4:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-length"
            r32 = 1
            r33 = r19
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x04d4:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "make-string"
            r32 = 1
            r33 = r22
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x04f4:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "%string-copy!"
            r32 = 2
            r33 = r22
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0514:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "%string-copy!"
            r32 = 2
            r33 = r22
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0534:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "%string-copy!"
            r32 = 3
            r33 = r22
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0554:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "%string-copy!"
            r32 = 4
            r33 = r22
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0574:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "string-length"
            r32 = 1
            r33 = r27
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x0594:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "%string-copy!"
            r32 = 1
            r33 = r27
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x05b4:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "%string-copy!"
            r32 = 2
            r33 = r27
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x05d4:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "%string-copy!"
            r32 = 1
            r33 = r24
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        L_0x05f4:
            r28 = move-exception
            r38 = r28
            r28 = r38
            r29 = r38
            java.lang.String r30 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r31 = 463(0x1cf, float:6.49E-43)
            r32 = 30
            r29.setLine(r30, r31, r32)
            throw r28
        L_0x0606:
            r28 = move-exception
            gnu.mapping.WrongType r29 = new gnu.mapping.WrongType
            r38 = r28
            r39 = r29
            r28 = r39
            r29 = r38
            r30 = r39
            r38 = r29
            r39 = r30
            r29 = r39
            r30 = r38
            java.lang.String r31 = "%string-copy!"
            r32 = 2
            r33 = r24
            r29.<init>((java.lang.ClassCastException) r30, (java.lang.String) r31, (int) r32, (java.lang.Object) r33)
            throw r28
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringUnfoldRight$V(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    static String lambda18(Object obj) {
        Object obj2 = obj;
        return "";
    }

    public static Object stringForEach$V(Object proc, Object s, Object[] argsArray) {
        frame7 frame710;
        new frame7();
        frame7 frame711 = frame710;
        frame711.proc = proc;
        frame711.f209s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame711.maybe$Mnstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame711.proc, string$Mnfor$Mneach);
            return call_with_values.callWithValues(frame711.lambda$Fn19, frame711.lambda$Fn20);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 468, 3);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn19;
        final ModuleMethod lambda$Fn20;
        LList maybe$Mnstart$Plend;
        Object proc;

        /* renamed from: s */
        Object f209s;

        public frame7() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 16, (Object) null, 0);
            this.lambda$Fn19 = moduleMethod;
            new ModuleMethod(this, 17, (Object) null, 8194);
            this.lambda$Fn20 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 16 ? lambda19() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 17 ? lambda20(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 16) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 17) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda19() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfor$Mneach, this.f209s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda20(Object start, Object obj) {
            Throwable th;
            Throwable th2;
            Object end = obj;
            Object obj2 = start;
            while (true) {
                Object i = obj2;
                if (Scheme.numLss.apply2(i, end) == Boolean.FALSE) {
                    return Values.empty;
                }
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                Object obj3 = this.proc;
                Object obj4 = this.f209s;
                Object obj5 = obj4;
                try {
                    CharSequence charSequence = (CharSequence) obj4;
                    Object obj6 = i;
                    Object obj7 = obj6;
                    try {
                        Object apply2 = applyToArgs.apply2(obj3, Char.make(strings.stringRef(charSequence, ((Number) obj6).intValue())));
                        obj2 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "string-ref", 2, obj7);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-ref", 1, obj5);
                    throw th4;
                }
            }
        }
    }

    public static Object stringForEachIndex$V(Object proc, Object s, Object[] argsArray) {
        frame8 frame810;
        new frame8();
        frame8 frame811 = frame810;
        frame811.proc = proc;
        frame811.f217s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame811.maybe$Mnstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame811.proc, string$Mnfor$Mneach$Mnindex);
            return call_with_values.callWithValues(frame811.lambda$Fn21, frame811.lambda$Fn22);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 476, 3);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame8 extends ModuleBody {
        final ModuleMethod lambda$Fn21;
        final ModuleMethod lambda$Fn22;
        LList maybe$Mnstart$Plend;
        Object proc;

        /* renamed from: s */
        Object f217s;

        public frame8() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 18, (Object) null, 0);
            this.lambda$Fn21 = moduleMethod;
            new ModuleMethod(this, 19, (Object) null, 8194);
            this.lambda$Fn22 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 18 ? lambda21() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 19 ? lambda22(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 18) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 19) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda21() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfor$Mneach$Mnindex, this.f217s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda22(Object start, Object obj) {
            Object end = obj;
            Object obj2 = start;
            while (true) {
                Object i = obj2;
                if (Scheme.numLss.apply2(i, end) == Boolean.FALSE) {
                    return Values.empty;
                }
                Object apply2 = Scheme.applyToArgs.apply2(this.proc, i);
                obj2 = AddOp.$Pl.apply2(i, srfi13.Lit1);
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame9 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn23;
        final ModuleMethod lambda$Fn24;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f222s;

        public frame9() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 20, (Object) null, 0);
            this.lambda$Fn23 = moduleMethod;
            new ModuleMethod(this, 21, (Object) null, 8194);
            this.lambda$Fn24 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 20 ? lambda23() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 21 ? lambda24(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 20) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 21) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda23() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnevery, this.f222s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda24(Object obj, Object obj2) {
            Object error$V;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Object start = obj;
            Object end = obj2;
            if (characters.isChar(this.criterion)) {
                Object obj3 = start;
                while (true) {
                    Object i = obj3;
                    Object apply2 = Scheme.numGEq.apply2(i, end);
                    Object obj4 = apply2;
                    try {
                        boolean x = ((Boolean) apply2).booleanValue();
                        if (x) {
                            error$V = x ? Boolean.TRUE : Boolean.FALSE;
                        } else {
                            Object obj5 = this.criterion;
                            Object obj6 = obj5;
                            try {
                                Char charR = (Char) obj5;
                                Object obj7 = this.f222s;
                                Object obj8 = obj7;
                                try {
                                    CharSequence charSequence = (CharSequence) obj7;
                                    Object obj9 = i;
                                    Object obj10 = obj9;
                                    try {
                                        boolean x2 = characters.isChar$Eq(charR, Char.make(strings.stringRef(charSequence, ((Number) obj9).intValue())));
                                        if (x2) {
                                            obj3 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                                        } else {
                                            error$V = x2 ? Boolean.TRUE : Boolean.FALSE;
                                        }
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th11 = th10;
                                        new WrongType(classCastException, "string-ref", 2, obj10);
                                        throw th11;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th12 = th9;
                                    new WrongType(classCastException2, "string-ref", 1, obj8);
                                    throw th12;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th13 = th8;
                                new WrongType(classCastException3, "char=?", 1, obj6);
                                throw th13;
                            }
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th14 = th7;
                        new WrongType(classCastException4, "x", -2, obj4);
                        throw th14;
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object obj11 = start;
                        while (true) {
                            Object i2 = obj11;
                            Object apply22 = Scheme.numGEq.apply2(i2, end);
                            Object obj12 = apply22;
                            try {
                                boolean x3 = ((Boolean) apply22).booleanValue();
                                if (x3) {
                                    error$V = x3 ? Boolean.TRUE : Boolean.FALSE;
                                } else {
                                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                    try {
                                        Object obj13 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                        Object obj14 = this.criterion;
                                        Object obj15 = this.f222s;
                                        Object obj16 = obj15;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj15;
                                            Object obj17 = i2;
                                            Object obj18 = obj17;
                                            try {
                                                Object x4 = applyToArgs.apply3(obj13, obj14, Char.make(strings.stringRef(charSequence2, ((Number) obj17).intValue())));
                                                if (x4 == Boolean.FALSE) {
                                                    error$V = x4;
                                                    break;
                                                }
                                                obj11 = AddOp.$Pl.apply2(i2, srfi13.Lit1);
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException5 = e5;
                                                Throwable th15 = th6;
                                                new WrongType(classCastException5, "string-ref", 2, obj18);
                                                throw th15;
                                            }
                                        } catch (ClassCastException e6) {
                                            ClassCastException classCastException6 = e6;
                                            Throwable th16 = th5;
                                            new WrongType(classCastException6, "string-ref", 1, obj16);
                                            throw th16;
                                        }
                                    } catch (UnboundLocationException e7) {
                                        UnboundLocationException unboundLocationException = e7;
                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 492, 9);
                                        throw unboundLocationException2;
                                    }
                                }
                            } catch (ClassCastException e8) {
                                ClassCastException classCastException7 = e8;
                                Throwable th17 = th4;
                                new WrongType(classCastException7, "x", -2, obj12);
                                throw th17;
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object apply23 = Scheme.numEqu.apply2(start, end);
                        Object obj19 = apply23;
                        try {
                            boolean x5 = ((Boolean) apply23).booleanValue();
                            if (x5) {
                                error$V = x5 ? Boolean.TRUE : Boolean.FALSE;
                            } else {
                                Object obj20 = start;
                                while (true) {
                                    Object i3 = obj20;
                                    Object obj21 = this.f222s;
                                    Object obj22 = obj21;
                                    try {
                                        CharSequence charSequence3 = (CharSequence) obj21;
                                        Object obj23 = i3;
                                        Object obj24 = obj23;
                                        try {
                                            char stringRef = strings.stringRef(charSequence3, ((Number) obj23).intValue());
                                            Object i1 = AddOp.$Pl.apply2(i3, srfi13.Lit1);
                                            char c = stringRef;
                                            if (Scheme.numEqu.apply2(i1, end) == Boolean.FALSE) {
                                                Object x6 = Scheme.applyToArgs.apply2(this.criterion, Char.make(c));
                                                if (x6 == Boolean.FALSE) {
                                                    error$V = x6;
                                                    break;
                                                }
                                                obj20 = i1;
                                            } else {
                                                error$V = Scheme.applyToArgs.apply2(this.criterion, Char.make(c));
                                                break;
                                            }
                                        } catch (ClassCastException e9) {
                                            ClassCastException classCastException8 = e9;
                                            Throwable th18 = th3;
                                            new WrongType(classCastException8, "string-ref", 2, obj24);
                                            throw th18;
                                        }
                                    } catch (ClassCastException e10) {
                                        ClassCastException classCastException9 = e10;
                                        Throwable th19 = th2;
                                        new WrongType(classCastException9, "string-ref", 1, obj22);
                                        throw th19;
                                    }
                                }
                            }
                        } catch (ClassCastException e11) {
                            ClassCastException classCastException10 = e11;
                            Throwable th20 = th;
                            new WrongType(classCastException10, "x", -2, obj19);
                            throw th20;
                        }
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = srfi13.string$Mnevery;
                        Object[] objArr2 = objArr;
                        objArr2[1] = this.criterion;
                        error$V = misc.error$V("Second param is neither char-set, char, or predicate procedure.", objArr2);
                    }
                } catch (UnboundLocationException e12) {
                    UnboundLocationException unboundLocationException3 = e12;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 489, 5);
                    throw unboundLocationException4;
                }
            }
            return error$V;
        }
    }

    public static Object stringEvery$V(Object criterion, Object s, Object[] argsArray) {
        frame9 frame97;
        new frame9();
        frame9 frame98 = frame97;
        frame98.criterion = criterion;
        frame98.f222s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame98.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame98.lambda$Fn23, frame98.lambda$Fn24);
    }

    /* compiled from: srfi13.scm */
    public class frame10 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn25;
        final ModuleMethod lambda$Fn26;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f144s;

        public frame10() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 22, (Object) null, 0);
            this.lambda$Fn25 = moduleMethod;
            new ModuleMethod(this, 23, (Object) null, 8194);
            this.lambda$Fn26 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 22 ? lambda25() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 23 ? lambda26(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 22) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 23) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda25() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnany, this.f144s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda26(Object obj, Object obj2) {
            Object error$V;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Object start = obj;
            Object end = obj2;
            if (characters.isChar(this.criterion)) {
                Object obj3 = start;
                while (true) {
                    Object i = obj3;
                    Object apply2 = Scheme.numLss.apply2(i, end);
                    Object obj4 = apply2;
                    try {
                        boolean x = ((Boolean) apply2).booleanValue();
                        if (x) {
                            Object obj5 = this.criterion;
                            Object obj6 = obj5;
                            try {
                                Char charR = (Char) obj5;
                                Object obj7 = this.f144s;
                                Object obj8 = obj7;
                                try {
                                    CharSequence charSequence = (CharSequence) obj7;
                                    Object obj9 = i;
                                    Object obj10 = obj9;
                                    try {
                                        boolean x2 = characters.isChar$Eq(charR, Char.make(strings.stringRef(charSequence, ((Number) obj9).intValue())));
                                        if (x2) {
                                            error$V = x2 ? Boolean.TRUE : Boolean.FALSE;
                                        } else {
                                            obj3 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                                        }
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th11 = th10;
                                        new WrongType(classCastException, "string-ref", 2, obj10);
                                        throw th11;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th12 = th9;
                                    new WrongType(classCastException2, "string-ref", 1, obj8);
                                    throw th12;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th13 = th8;
                                new WrongType(classCastException3, "char=?", 1, obj6);
                                throw th13;
                            }
                        } else {
                            error$V = x ? Boolean.TRUE : Boolean.FALSE;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th14 = th7;
                        new WrongType(classCastException4, "x", -2, obj4);
                        throw th14;
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object obj11 = start;
                        while (true) {
                            Object i2 = obj11;
                            Object apply22 = Scheme.numLss.apply2(i2, end);
                            Object obj12 = apply22;
                            try {
                                boolean x3 = ((Boolean) apply22).booleanValue();
                                if (x3) {
                                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                    try {
                                        Object obj13 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                        Object obj14 = this.criterion;
                                        Object obj15 = this.f144s;
                                        Object obj16 = obj15;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj15;
                                            Object obj17 = i2;
                                            Object obj18 = obj17;
                                            try {
                                                Object x4 = applyToArgs.apply3(obj13, obj14, Char.make(strings.stringRef(charSequence2, ((Number) obj17).intValue())));
                                                if (x4 != Boolean.FALSE) {
                                                    error$V = x4;
                                                    break;
                                                }
                                                obj11 = AddOp.$Pl.apply2(i2, srfi13.Lit1);
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException5 = e5;
                                                Throwable th15 = th6;
                                                new WrongType(classCastException5, "string-ref", 2, obj18);
                                                throw th15;
                                            }
                                        } catch (ClassCastException e6) {
                                            ClassCastException classCastException6 = e6;
                                            Throwable th16 = th5;
                                            new WrongType(classCastException6, "string-ref", 1, obj16);
                                            throw th16;
                                        }
                                    } catch (UnboundLocationException e7) {
                                        UnboundLocationException unboundLocationException = e7;
                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_BLUETOOTH_END_OF_STREAM, 9);
                                        throw unboundLocationException2;
                                    }
                                } else {
                                    error$V = x3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e8) {
                                ClassCastException classCastException7 = e8;
                                Throwable th17 = th4;
                                new WrongType(classCastException7, "x", -2, obj12);
                                throw th17;
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object apply23 = Scheme.numLss.apply2(start, end);
                        Object obj19 = apply23;
                        try {
                            boolean x5 = ((Boolean) apply23).booleanValue();
                            if (x5) {
                                Object obj20 = start;
                                while (true) {
                                    Object i3 = obj20;
                                    Object obj21 = this.f144s;
                                    Object obj22 = obj21;
                                    try {
                                        CharSequence charSequence3 = (CharSequence) obj21;
                                        Object obj23 = i3;
                                        Object obj24 = obj23;
                                        try {
                                            char stringRef = strings.stringRef(charSequence3, ((Number) obj23).intValue());
                                            Object i1 = AddOp.$Pl.apply2(i3, srfi13.Lit1);
                                            char c = stringRef;
                                            if (Scheme.numEqu.apply2(i1, end) != Boolean.FALSE) {
                                                error$V = Scheme.applyToArgs.apply2(this.criterion, Char.make(c));
                                                break;
                                            }
                                            Object x6 = Scheme.applyToArgs.apply2(this.criterion, Char.make(c));
                                            if (x6 != Boolean.FALSE) {
                                                error$V = x6;
                                                break;
                                            }
                                            obj20 = i1;
                                        } catch (ClassCastException e9) {
                                            ClassCastException classCastException8 = e9;
                                            Throwable th18 = th3;
                                            new WrongType(classCastException8, "string-ref", 2, obj24);
                                            throw th18;
                                        }
                                    } catch (ClassCastException e10) {
                                        ClassCastException classCastException9 = e10;
                                        Throwable th19 = th2;
                                        new WrongType(classCastException9, "string-ref", 1, obj22);
                                        throw th19;
                                    }
                                }
                            } else {
                                error$V = x5 ? Boolean.TRUE : Boolean.FALSE;
                            }
                        } catch (ClassCastException e11) {
                            ClassCastException classCastException10 = e11;
                            Throwable th20 = th;
                            new WrongType(classCastException10, "x", -2, obj19);
                            throw th20;
                        }
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = srfi13.string$Mnany;
                        Object[] objArr2 = objArr;
                        objArr2[1] = this.criterion;
                        error$V = misc.error$V("Second param is neither char-set, char, or predicate procedure.", objArr2);
                    }
                } catch (UnboundLocationException e12) {
                    UnboundLocationException unboundLocationException3 = e12;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, 5);
                    throw unboundLocationException4;
                }
            }
            return error$V;
        }
    }

    public static Object stringAny$V(Object criterion, Object s, Object[] argsArray) {
        frame10 frame102;
        new frame10();
        frame10 frame103 = frame102;
        frame103.criterion = criterion;
        frame103.f144s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame103.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame103.lambda$Fn25, frame103.lambda$Fn26);
    }

    public static CharSequence stringTabulate(Object obj, int i) {
        Throwable th;
        Throwable th2;
        Object proc = obj;
        int len = i;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, proc, string$Mntabulate);
            try {
                Object apply42 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), lambda$Fn27, Integer.valueOf(len), string$Mntabulate);
                CharSequence s = strings.makeString(len);
                int i2 = len - 1;
                while (i2 >= 0) {
                    CharSequence charSequence = s;
                    CharSequence charSequence2 = charSequence;
                    try {
                        CharSeq charSeq = (CharSeq) charSequence;
                        int i3 = i2;
                        Object apply2 = Scheme.applyToArgs.apply2(proc, Integer.valueOf(i2));
                        Object obj2 = apply2;
                        try {
                            strings.stringSet$Ex(charSeq, i3, ((Char) apply2).charValue());
                            i2--;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th3 = th2;
                            new WrongType(classCastException, "string-set!", 3, obj2);
                            throw th3;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th4 = th;
                        new WrongType(classCastException2, "string-set!", 1, (Object) charSequence2);
                        throw th4;
                    }
                }
                return s;
            } catch (UnboundLocationException e3) {
                UnboundLocationException unboundLocationException = e3;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 535, 3);
                throw unboundLocationException2;
            }
        } catch (UnboundLocationException e4) {
            UnboundLocationException unboundLocationException3 = e4;
            UnboundLocationException unboundLocationException4 = unboundLocationException3;
            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 534, 3);
            throw unboundLocationException4;
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 218:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 280:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 281:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 282:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof CharSequence)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 283:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof CharSequence)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    static boolean lambda27(Object obj) {
        boolean z;
        Object val = obj;
        boolean x = numbers.isInteger(val);
        if (x) {
            boolean x2 = numbers.isExact(val);
            z = x2 ? ((Boolean) Scheme.numLEq.apply2(Lit0, val)).booleanValue() : x2;
        } else {
            z = x;
        }
        return z;
    }

    public static Object $PcStringPrefixLength(Object obj, Object obj2, Object end1, Object obj3, Object obj4, Object end2) {
        Object i;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object s1;
        Object s12 = obj;
        Object start1 = obj2;
        Object s2 = obj3;
        Object start2 = obj4;
        Object[] objArr = new Object[2];
        objArr[0] = AddOp.$Mn.apply2(end1, start1);
        Object[] objArr2 = objArr;
        objArr2[1] = AddOp.$Mn.apply2(end2, start2);
        Object delta = numbers.min(objArr2);
        Object end12 = AddOp.$Pl.apply2(start1, delta);
        boolean x = s12 == s2;
        if (!x ? !x : Scheme.numEqu.apply2(start1, start2) == Boolean.FALSE) {
            Object obj5 = start1;
            Object obj6 = start2;
            while (true) {
                Object obj7 = obj6;
                i = obj5;
                Object apply2 = Scheme.numGEq.apply2(i, end12);
                Object obj8 = apply2;
                try {
                    boolean x2 = ((Boolean) apply2).booleanValue();
                    if (!x2) {
                        Object obj9 = s12;
                        Object obj10 = obj9;
                        try {
                            CharSequence charSequence = (CharSequence) obj9;
                            Object obj11 = i;
                            Object obj12 = obj11;
                            try {
                                Char make = Char.make(strings.stringRef(charSequence, ((Number) obj11).intValue()));
                                Object obj13 = s2;
                                Object obj14 = obj13;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj13;
                                    Object obj15 = obj7;
                                    Object obj16 = obj15;
                                    try {
                                        if (!characters.isChar$Eq(make, Char.make(strings.stringRef(charSequence2, ((Number) obj15).intValue())))) {
                                            break;
                                        }
                                        obj5 = AddOp.$Pl.apply2(i, Lit1);
                                        obj6 = AddOp.$Pl.apply2(obj7, Lit1);
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th6 = th5;
                                        new WrongType(classCastException, "string-ref", 2, obj16);
                                        throw th6;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th7 = th4;
                                    new WrongType(classCastException2, "string-ref", 1, obj14);
                                    throw th7;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th8 = th3;
                                new WrongType(classCastException3, "string-ref", 2, obj12);
                                throw th8;
                            }
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th9 = th2;
                            new WrongType(classCastException4, "string-ref", 1, obj10);
                            throw th9;
                        }
                    } else if (x2) {
                        break;
                    } else {
                        obj5 = AddOp.$Pl.apply2(i, Lit1);
                        obj6 = AddOp.$Pl.apply2(obj7, Lit1);
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "x", -2, obj8);
                    throw th10;
                }
            }
            s1 = AddOp.$Mn.apply2(i, start1);
        } else {
            s1 = delta;
        }
        return s1;
    }

    public static Object $PcStringSuffixLength(Object obj, Object start1, Object obj2, Object obj3, Object start2, Object obj4) {
        Object i;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object s1;
        Object s12 = obj;
        Object end1 = obj2;
        Object s2 = obj3;
        Object end2 = obj4;
        Object[] objArr = new Object[2];
        objArr[0] = AddOp.$Mn.apply2(end1, start1);
        Object[] objArr2 = objArr;
        objArr2[1] = AddOp.$Mn.apply2(end2, start2);
        Object delta = numbers.min(objArr2);
        Object start12 = AddOp.$Mn.apply2(end1, delta);
        boolean x = s12 == s2;
        if (!x ? !x : Scheme.numEqu.apply2(end1, end2) == Boolean.FALSE) {
            Object apply2 = AddOp.$Mn.apply2(end1, Lit1);
            Object apply22 = AddOp.$Mn.apply2(end2, Lit1);
            while (true) {
                Object obj5 = apply22;
                i = apply2;
                Object apply23 = Scheme.numLss.apply2(i, start12);
                Object obj6 = apply23;
                try {
                    boolean x2 = ((Boolean) apply23).booleanValue();
                    if (!x2) {
                        Object obj7 = s12;
                        Object obj8 = obj7;
                        try {
                            CharSequence charSequence = (CharSequence) obj7;
                            Object obj9 = i;
                            Object obj10 = obj9;
                            try {
                                Char make = Char.make(strings.stringRef(charSequence, ((Number) obj9).intValue()));
                                Object obj11 = s2;
                                Object obj12 = obj11;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj11;
                                    Object obj13 = obj5;
                                    Object obj14 = obj13;
                                    try {
                                        if (!characters.isChar$Eq(make, Char.make(strings.stringRef(charSequence2, ((Number) obj13).intValue())))) {
                                            break;
                                        }
                                        apply2 = AddOp.$Mn.apply2(i, Lit1);
                                        apply22 = AddOp.$Mn.apply2(obj5, Lit1);
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th6 = th5;
                                        new WrongType(classCastException, "string-ref", 2, obj14);
                                        throw th6;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th7 = th4;
                                    new WrongType(classCastException2, "string-ref", 1, obj12);
                                    throw th7;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th8 = th3;
                                new WrongType(classCastException3, "string-ref", 2, obj10);
                                throw th8;
                            }
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th9 = th2;
                            new WrongType(classCastException4, "string-ref", 1, obj8);
                            throw th9;
                        }
                    } else if (x2) {
                        break;
                    } else {
                        apply2 = AddOp.$Mn.apply2(i, Lit1);
                        apply22 = AddOp.$Mn.apply2(obj5, Lit1);
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "x", -2, obj6);
                    throw th10;
                }
            }
            s1 = AddOp.$Mn.apply2(AddOp.$Mn.apply2(end1, i), Lit1);
        } else {
            s1 = delta;
        }
        return s1;
    }

    public static int $PcStringPrefixLengthCi(Object obj, int i, int end1, Object obj2, int i2, int end2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        int i3;
        Object s1 = obj;
        int start1 = i;
        Object s2 = obj2;
        int start2 = i2;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(end1 - start1);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(end2 - start2);
        Object min = numbers.min(objArr2);
        Object obj3 = min;
        try {
            int delta = ((Number) min).intValue();
            int end12 = start1 + delta;
            boolean x = s1 == s2;
            if (!x ? !x : start1 != start2) {
                int i4 = start2;
                int i5 = start1;
                while (true) {
                    boolean x2 = i5 >= end12;
                    if (!x2) {
                        Object obj4 = s1;
                        Object obj5 = obj4;
                        try {
                            Char make = Char.make(strings.stringRef((CharSequence) obj4, i5));
                            Object obj6 = s2;
                            Object obj7 = obj6;
                            try {
                                if (!unicode.isCharCi$Eq(make, Char.make(strings.stringRef((CharSequence) obj6, i4)))) {
                                    break;
                                }
                                i4++;
                                i5++;
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th4 = th3;
                                new WrongType(classCastException, "string-ref", 1, obj7);
                                throw th4;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th5 = th2;
                            new WrongType(classCastException2, "string-ref", 1, obj5);
                            throw th5;
                        }
                    } else if (x2) {
                        break;
                    } else {
                        i4++;
                        i5++;
                    }
                }
                i3 = i5 - start1;
            } else {
                i3 = delta;
            }
            return i3;
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "delta", -2, obj3);
            throw th6;
        }
    }

    public static int $PcStringSuffixLengthCi(Object obj, int start1, int i, Object obj2, int start2, int i2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        int i3;
        Object s1 = obj;
        int end1 = i;
        Object s2 = obj2;
        int end2 = i2;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(end1 - start1);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(end2 - start2);
        Object min = numbers.min(objArr2);
        Object obj3 = min;
        try {
            int delta = ((Number) min).intValue();
            int start12 = end1 - delta;
            boolean x = s1 == s2;
            if (!x ? !x : end1 != end2) {
                int i4 = end2 - 1;
                int i5 = end1 - 1;
                while (true) {
                    boolean x2 = i5 < start12;
                    if (!x2) {
                        Object obj4 = s1;
                        Object obj5 = obj4;
                        try {
                            Char make = Char.make(strings.stringRef((CharSequence) obj4, i5));
                            Object obj6 = s2;
                            Object obj7 = obj6;
                            try {
                                if (!unicode.isCharCi$Eq(make, Char.make(strings.stringRef((CharSequence) obj6, i4)))) {
                                    break;
                                }
                                i4--;
                                i5--;
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th4 = th3;
                                new WrongType(classCastException, "string-ref", 1, obj7);
                                throw th4;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th5 = th2;
                            new WrongType(classCastException2, "string-ref", 1, obj5);
                            throw th5;
                        }
                    } else if (x2) {
                        break;
                    } else {
                        i4--;
                        i5--;
                    }
                }
                i3 = (end1 - i5) - 1;
            } else {
                i3 = delta;
            }
            return i3;
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "delta", -2, obj3);
            throw th6;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame11 extends ModuleBody {
        final ModuleMethod lambda$Fn28;
        final ModuleMethod lambda$Fn29;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f145s1;

        /* renamed from: s2 */
        Object f146s2;

        public frame11() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 26, (Object) null, 0);
            this.lambda$Fn28 = moduleMethod;
            new ModuleMethod(this, 27, (Object) null, 12291);
            this.lambda$Fn29 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 26 ? lambda28() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 27 ? lambda29(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 26) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 27) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda28() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnlength, this.f145s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda29(Object rest, Object start1, Object end1) {
            frame12 frame12;
            new frame12();
            frame12 frame122 = frame12;
            frame122.staticLink = this;
            frame12 frame123 = frame122;
            frame123.rest = rest;
            frame123.start1 = start1;
            frame123.end1 = end1;
            return call_with_values.callWithValues(frame123.lambda$Fn30, frame123.lambda$Fn31);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame12 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn30;
        final ModuleMethod lambda$Fn31;
        Object rest;
        Object start1;
        frame11 staticLink;

        public frame12() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 24, (Object) null, 0);
            this.lambda$Fn30 = moduleMethod;
            new ModuleMethod(this, 25, (Object) null, 8194);
            this.lambda$Fn31 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 24 ? lambda30() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 25 ? lambda31(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 24) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 25) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda30() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength, this.staticLink.f146s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda31(Object start2, Object end2) {
            return srfi13.$PcStringPrefixLength(this.staticLink.f145s1, this.start1, this.end1, this.staticLink.f146s2, start2, end2);
        }
    }

    public static Object stringPrefixLength$V(Object s1, Object s2, Object[] argsArray) {
        frame11 frame112;
        new frame11();
        frame11 frame113 = frame112;
        frame113.f145s1 = s1;
        frame113.f146s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame113.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame113.lambda$Fn28, frame113.lambda$Fn29);
    }

    /* compiled from: srfi13.scm */
    public class frame13 extends ModuleBody {
        final ModuleMethod lambda$Fn32;
        final ModuleMethod lambda$Fn33;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f147s1;

        /* renamed from: s2 */
        Object f148s2;

        public frame13() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 30, (Object) null, 0);
            this.lambda$Fn32 = moduleMethod;
            new ModuleMethod(this, 31, (Object) null, 12291);
            this.lambda$Fn33 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 30 ? lambda32() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 31 ? lambda33(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 30) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 31) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda32() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, this.f147s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda33(Object rest, Object start1, Object end1) {
            frame14 frame14;
            new frame14();
            frame14 frame142 = frame14;
            frame142.staticLink = this;
            frame14 frame143 = frame142;
            frame143.rest = rest;
            frame143.start1 = start1;
            frame143.end1 = end1;
            return call_with_values.callWithValues(frame143.lambda$Fn34, frame143.lambda$Fn35);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame14 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn34;
        final ModuleMethod lambda$Fn35;
        Object rest;
        Object start1;
        frame13 staticLink;

        public frame14() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 28, (Object) null, 0);
            this.lambda$Fn34 = moduleMethod;
            new ModuleMethod(this, 29, (Object) null, 8194);
            this.lambda$Fn35 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 28 ? lambda34() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 29 ? lambda35(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 28) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 29) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda34() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, this.staticLink.f148s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda35(Object start2, Object end2) {
            return srfi13.$PcStringSuffixLength(this.staticLink.f147s1, this.start1, this.end1, this.staticLink.f148s2, start2, end2);
        }
    }

    public static Object stringSuffixLength$V(Object s1, Object s2, Object[] argsArray) {
        frame13 frame132;
        new frame13();
        frame13 frame133 = frame132;
        frame133.f147s1 = s1;
        frame133.f148s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame133.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame133.lambda$Fn32, frame133.lambda$Fn33);
    }

    /* compiled from: srfi13.scm */
    public class frame15 extends ModuleBody {
        final ModuleMethod lambda$Fn36;
        final ModuleMethod lambda$Fn37;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f149s1;

        /* renamed from: s2 */
        Object f150s2;

        public frame15() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 34, (Object) null, 0);
            this.lambda$Fn36 = moduleMethod;
            new ModuleMethod(this, 35, (Object) null, 12291);
            this.lambda$Fn37 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 34 ? lambda36() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 35 ? lambda37(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 34) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 35) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda36() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, this.f149s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda37(Object rest, Object start1, Object end1) {
            frame16 frame16;
            new frame16();
            frame16 frame162 = frame16;
            frame162.staticLink = this;
            frame16 frame163 = frame162;
            frame163.rest = rest;
            frame163.start1 = start1;
            frame163.end1 = end1;
            return call_with_values.callWithValues(frame163.lambda$Fn38, frame163.lambda$Fn39);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame16 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn38;
        final ModuleMethod lambda$Fn39;
        Object rest;
        Object start1;
        frame15 staticLink;

        public frame16() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 32, (Object) null, 0);
            this.lambda$Fn38 = moduleMethod;
            new ModuleMethod(this, 33, (Object) null, 8194);
            this.lambda$Fn39 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 32 ? lambda38() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 33 ? Integer.valueOf(lambda39(obj3, obj4)) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 32) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 33) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda38() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, this.staticLink.f150s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public int lambda39(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object start2 = obj;
            Object end2 = obj2;
            Object obj3 = this.staticLink.f149s1;
            Object obj4 = this.start1;
            Object obj5 = obj4;
            try {
                int intValue = ((Number) obj4).intValue();
                Object obj6 = this.end1;
                Object obj7 = obj6;
                try {
                    int intValue2 = ((Number) obj6).intValue();
                    Object obj8 = this.staticLink.f150s2;
                    Object obj9 = start2;
                    Object obj10 = obj9;
                    try {
                        int intValue3 = ((Number) obj9).intValue();
                        Object obj11 = end2;
                        Object obj12 = obj11;
                        try {
                            return srfi13.$PcStringPrefixLengthCi(obj3, intValue, intValue2, obj8, intValue3, ((Number) obj11).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "%string-prefix-length-ci", 5, obj12);
                            throw th5;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "%string-prefix-length-ci", 4, obj10);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "%string-prefix-length-ci", 2, obj7);
                    throw th7;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th8 = th;
                new WrongType(classCastException4, "%string-prefix-length-ci", 1, obj5);
                throw th8;
            }
        }
    }

    public static Object stringPrefixLengthCi$V(Object s1, Object s2, Object[] argsArray) {
        frame15 frame152;
        new frame15();
        frame15 frame153 = frame152;
        frame153.f149s1 = s1;
        frame153.f150s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame153.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame153.lambda$Fn36, frame153.lambda$Fn37);
    }

    /* compiled from: srfi13.scm */
    public class frame17 extends ModuleBody {
        final ModuleMethod lambda$Fn40;
        final ModuleMethod lambda$Fn41;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f151s1;

        /* renamed from: s2 */
        Object f152s2;

        public frame17() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 38, (Object) null, 0);
            this.lambda$Fn40 = moduleMethod;
            new ModuleMethod(this, 39, (Object) null, 12291);
            this.lambda$Fn41 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 38 ? lambda40() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 39 ? lambda41(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 38) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 39) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda40() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, this.f151s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda41(Object rest, Object start1, Object end1) {
            frame18 frame18;
            new frame18();
            frame18 frame182 = frame18;
            frame182.staticLink = this;
            frame18 frame183 = frame182;
            frame183.rest = rest;
            frame183.start1 = start1;
            frame183.end1 = end1;
            return call_with_values.callWithValues(frame183.lambda$Fn42, frame183.lambda$Fn43);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame18 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn42;
        final ModuleMethod lambda$Fn43;
        Object rest;
        Object start1;
        frame17 staticLink;

        public frame18() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 36, (Object) null, 0);
            this.lambda$Fn42 = moduleMethod;
            new ModuleMethod(this, 37, (Object) null, 8194);
            this.lambda$Fn43 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 36 ? lambda42() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 37 ? Integer.valueOf(lambda43(obj3, obj4)) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 36) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 37) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda42() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, this.staticLink.f152s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public int lambda43(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object start2 = obj;
            Object end2 = obj2;
            Object obj3 = this.staticLink.f151s1;
            Object obj4 = this.start1;
            Object obj5 = obj4;
            try {
                int intValue = ((Number) obj4).intValue();
                Object obj6 = this.end1;
                Object obj7 = obj6;
                try {
                    int intValue2 = ((Number) obj6).intValue();
                    Object obj8 = this.staticLink.f152s2;
                    Object obj9 = start2;
                    Object obj10 = obj9;
                    try {
                        int intValue3 = ((Number) obj9).intValue();
                        Object obj11 = end2;
                        Object obj12 = obj11;
                        try {
                            return srfi13.$PcStringSuffixLengthCi(obj3, intValue, intValue2, obj8, intValue3, ((Number) obj11).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "%string-suffix-length-ci", 5, obj12);
                            throw th5;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "%string-suffix-length-ci", 4, obj10);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "%string-suffix-length-ci", 2, obj7);
                    throw th7;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th8 = th;
                new WrongType(classCastException4, "%string-suffix-length-ci", 1, obj5);
                throw th8;
            }
        }
    }

    public static Object stringSuffixLengthCi$V(Object s1, Object s2, Object[] argsArray) {
        frame17 frame172;
        new frame17();
        frame17 frame173 = frame172;
        frame173.f151s1 = s1;
        frame173.f152s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame173.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame173.lambda$Fn40, frame173.lambda$Fn41);
    }

    /* compiled from: srfi13.scm */
    public class frame19 extends ModuleBody {
        final ModuleMethod lambda$Fn44;
        final ModuleMethod lambda$Fn45;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f153s1;

        /* renamed from: s2 */
        Object f154s2;

        public frame19() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 42, (Object) null, 0);
            this.lambda$Fn44 = moduleMethod;
            new ModuleMethod(this, 43, (Object) null, 12291);
            this.lambda$Fn45 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 42 ? lambda44() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 43 ? lambda45(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 42) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 43) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda44() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Qu, this.f153s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda45(Object rest, Object start1, Object end1) {
            frame20 frame20;
            new frame20();
            frame20 frame202 = frame20;
            frame202.staticLink = this;
            frame20 frame203 = frame202;
            frame203.rest = rest;
            frame203.start1 = start1;
            frame203.end1 = end1;
            return call_with_values.callWithValues(frame203.lambda$Fn46, frame203.lambda$Fn47);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame20 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn46;
        final ModuleMethod lambda$Fn47;
        Object rest;
        Object start1;
        frame19 staticLink;

        public frame20() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 40, (Object) null, 0);
            this.lambda$Fn46 = moduleMethod;
            new ModuleMethod(this, 41, (Object) null, 8194);
            this.lambda$Fn47 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 40 ? lambda46() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 41 ? lambda47(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 40) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 41) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda46() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Qu, this.staticLink.f154s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda47(Object start2, Object end2) {
            return srfi13.$PcStringPrefix$Qu(this.staticLink.f153s1, this.start1, this.end1, this.staticLink.f154s2, start2, end2);
        }
    }

    public static Object isStringPrefix$V(Object s1, Object s2, Object[] argsArray) {
        frame19 frame192;
        new frame19();
        frame19 frame193 = frame192;
        frame193.f153s1 = s1;
        frame193.f154s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame193.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame193.lambda$Fn44, frame193.lambda$Fn45);
    }

    /* compiled from: srfi13.scm */
    public class frame21 extends ModuleBody {
        final ModuleMethod lambda$Fn48;
        final ModuleMethod lambda$Fn49;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f156s1;

        /* renamed from: s2 */
        Object f157s2;

        public frame21() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 46, (Object) null, 0);
            this.lambda$Fn48 = moduleMethod;
            new ModuleMethod(this, 47, (Object) null, 12291);
            this.lambda$Fn49 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 46 ? lambda48() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 47 ? lambda49(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 46) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 47) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda48() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Qu, this.f156s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda49(Object rest, Object start1, Object end1) {
            frame22 frame22;
            new frame22();
            frame22 frame222 = frame22;
            frame222.staticLink = this;
            frame22 frame223 = frame222;
            frame223.rest = rest;
            frame223.start1 = start1;
            frame223.end1 = end1;
            return call_with_values.callWithValues(frame223.lambda$Fn50, frame223.lambda$Fn51);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame22 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn50;
        final ModuleMethod lambda$Fn51;
        Object rest;
        Object start1;
        frame21 staticLink;

        public frame22() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 44, (Object) null, 0);
            this.lambda$Fn50 = moduleMethod;
            new ModuleMethod(this, 45, (Object) null, 8194);
            this.lambda$Fn51 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 44 ? lambda50() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 45 ? lambda51(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 44) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 45) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda50() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Qu, this.staticLink.f157s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda51(Object start2, Object end2) {
            return srfi13.$PcStringSuffix$Qu(this.staticLink.f156s1, this.start1, this.end1, this.staticLink.f157s2, start2, end2);
        }
    }

    public static Object isStringSuffix$V(Object s1, Object s2, Object[] argsArray) {
        frame21 frame212;
        new frame21();
        frame21 frame213 = frame212;
        frame213.f156s1 = s1;
        frame213.f157s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame213.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame213.lambda$Fn48, frame213.lambda$Fn49);
    }

    /* compiled from: srfi13.scm */
    public class frame23 extends ModuleBody {
        final ModuleMethod lambda$Fn52;
        final ModuleMethod lambda$Fn53;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f158s1;

        /* renamed from: s2 */
        Object f159s2;

        public frame23() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 50, (Object) null, 0);
            this.lambda$Fn52 = moduleMethod;
            new ModuleMethod(this, 51, (Object) null, 12291);
            this.lambda$Fn53 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 50 ? lambda52() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 51 ? lambda53(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 50) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 51) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda52() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, this.f158s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda53(Object rest, Object start1, Object end1) {
            frame24 frame24;
            new frame24();
            frame24 frame242 = frame24;
            frame242.staticLink = this;
            frame24 frame243 = frame242;
            frame243.rest = rest;
            frame243.start1 = start1;
            frame243.end1 = end1;
            return call_with_values.callWithValues(frame243.lambda$Fn54, frame243.lambda$Fn55);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame24 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn54;
        final ModuleMethod lambda$Fn55;
        Object rest;
        Object start1;
        frame23 staticLink;

        public frame24() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 48, (Object) null, 0);
            this.lambda$Fn54 = moduleMethod;
            new ModuleMethod(this, 49, (Object) null, 8194);
            this.lambda$Fn55 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 48 ? lambda54() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 49 ? lambda55(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 48) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 49) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda54() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, this.staticLink.f159s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda55(Object start2, Object end2) {
            return srfi13.$PcStringPrefixCi$Qu(this.staticLink.f158s1, this.start1, this.end1, this.staticLink.f159s2, start2, end2);
        }
    }

    public static Object isStringPrefixCi$V(Object s1, Object s2, Object[] argsArray) {
        frame23 frame232;
        new frame23();
        frame23 frame233 = frame232;
        frame233.f158s1 = s1;
        frame233.f159s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame233.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame233.lambda$Fn52, frame233.lambda$Fn53);
    }

    /* compiled from: srfi13.scm */
    public class frame25 extends ModuleBody {
        final ModuleMethod lambda$Fn56;
        final ModuleMethod lambda$Fn57;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f160s1;

        /* renamed from: s2 */
        Object f161s2;

        public frame25() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 54, (Object) null, 0);
            this.lambda$Fn56 = moduleMethod;
            new ModuleMethod(this, 55, (Object) null, 12291);
            this.lambda$Fn57 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 54 ? lambda56() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 55 ? lambda57(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 54) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 55) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda56() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, this.f160s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda57(Object rest, Object start1, Object end1) {
            frame26 frame26;
            new frame26();
            frame26 frame262 = frame26;
            frame262.staticLink = this;
            frame26 frame263 = frame262;
            frame263.rest = rest;
            frame263.start1 = start1;
            frame263.end1 = end1;
            return call_with_values.callWithValues(frame263.lambda$Fn58, frame263.lambda$Fn59);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame26 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn58;
        final ModuleMethod lambda$Fn59;
        Object rest;
        Object start1;
        frame25 staticLink;

        public frame26() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 52, (Object) null, 0);
            this.lambda$Fn58 = moduleMethod;
            new ModuleMethod(this, 53, (Object) null, 8194);
            this.lambda$Fn59 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 52 ? lambda58() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 53 ? lambda59(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 52) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 53) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda58() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, this.staticLink.f161s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda59(Object start2, Object end2) {
            return srfi13.$PcStringSuffixCi$Qu(this.staticLink.f160s1, this.start1, this.end1, this.staticLink.f161s2, start2, end2);
        }
    }

    public static Object isStringSuffixCi$V(Object s1, Object s2, Object[] argsArray) {
        frame25 frame252;
        new frame25();
        frame25 frame253 = frame252;
        frame253.f160s1 = s1;
        frame253.f161s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame253.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame253.lambda$Fn56, frame253.lambda$Fn57);
    }

    public static Object $PcStringPrefix$Qu(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Throwable th;
        Object s1;
        Object s12 = obj;
        Object start1 = obj2;
        Object end1 = obj3;
        Object s2 = obj4;
        Object start2 = obj5;
        Object end2 = obj6;
        Object len1 = AddOp.$Mn.apply2(end1, start1);
        Object apply2 = Scheme.numLEq.apply2(len1, AddOp.$Mn.apply2(end2, start2));
        Object obj7 = apply2;
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            if (x) {
                s1 = Scheme.numEqu.apply2($PcStringPrefixLength(s12, start1, end1, s2, start2, end2), len1);
            } else {
                s1 = x ? Boolean.TRUE : Boolean.FALSE;
            }
            return s1;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "x", -2, obj7);
            throw th2;
        }
    }

    public static Object $PcStringSuffix$Qu(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Throwable th;
        Object s1;
        Object s12 = obj;
        Object start1 = obj2;
        Object end1 = obj3;
        Object s2 = obj4;
        Object start2 = obj5;
        Object end2 = obj6;
        Object len1 = AddOp.$Mn.apply2(end1, start1);
        Object apply2 = Scheme.numLEq.apply2(len1, AddOp.$Mn.apply2(end2, start2));
        Object obj7 = apply2;
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            if (x) {
                s1 = Scheme.numEqu.apply2(len1, $PcStringSuffixLength(s12, start1, end1, s2, start2, end2));
            } else {
                s1 = x ? Boolean.TRUE : Boolean.FALSE;
            }
            return s1;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "x", -2, obj7);
            throw th2;
        }
    }

    public static Object $PcStringPrefixCi$Qu(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Throwable th;
        Object s1;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object s12 = obj;
        Object start1 = obj2;
        Object end1 = obj3;
        Object s2 = obj4;
        Object start2 = obj5;
        Object end2 = obj6;
        Object len1 = AddOp.$Mn.apply2(end1, start1);
        Object apply2 = Scheme.numLEq.apply2(len1, AddOp.$Mn.apply2(end2, start2));
        Object obj7 = apply2;
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            if (x) {
                NumberCompare numberCompare = Scheme.numEqu;
                Object obj8 = len1;
                Object obj9 = s12;
                Object obj10 = start1;
                Object obj11 = obj10;
                try {
                    int intValue = ((Number) obj10).intValue();
                    Object obj12 = end1;
                    Object obj13 = obj12;
                    try {
                        int intValue2 = ((Number) obj12).intValue();
                        Object obj14 = s2;
                        Object obj15 = start2;
                        Object obj16 = obj15;
                        try {
                            int intValue3 = ((Number) obj15).intValue();
                            Object obj17 = end2;
                            Object obj18 = obj17;
                            try {
                                s1 = numberCompare.apply2(obj8, Integer.valueOf($PcStringPrefixLengthCi(obj9, intValue, intValue2, obj14, intValue3, ((Number) obj17).intValue())));
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th6 = th5;
                                new WrongType(classCastException, "%string-prefix-length-ci", 5, obj18);
                                throw th6;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th7 = th4;
                            new WrongType(classCastException2, "%string-prefix-length-ci", 4, obj16);
                            throw th7;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th8 = th3;
                        new WrongType(classCastException3, "%string-prefix-length-ci", 2, obj13);
                        throw th8;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "%string-prefix-length-ci", 1, obj11);
                    throw th9;
                }
            } else {
                s1 = x ? Boolean.TRUE : Boolean.FALSE;
            }
            return s1;
        } catch (ClassCastException e5) {
            ClassCastException classCastException5 = e5;
            Throwable th10 = th;
            new WrongType(classCastException5, "x", -2, obj7);
            throw th10;
        }
    }

    public static Object $PcStringSuffixCi$Qu(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Throwable th;
        Object s1;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object s12 = obj;
        Object start1 = obj2;
        Object end1 = obj3;
        Object s2 = obj4;
        Object start2 = obj5;
        Object end2 = obj6;
        Object len1 = AddOp.$Mn.apply2(end1, start1);
        Object apply2 = Scheme.numLEq.apply2(len1, AddOp.$Mn.apply2(end2, start2));
        Object obj7 = apply2;
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            if (x) {
                NumberCompare numberCompare = Scheme.numEqu;
                Object obj8 = len1;
                Object obj9 = s12;
                Object obj10 = start1;
                Object obj11 = obj10;
                try {
                    int intValue = ((Number) obj10).intValue();
                    Object obj12 = end1;
                    Object obj13 = obj12;
                    try {
                        int intValue2 = ((Number) obj12).intValue();
                        Object obj14 = s2;
                        Object obj15 = start2;
                        Object obj16 = obj15;
                        try {
                            int intValue3 = ((Number) obj15).intValue();
                            Object obj17 = end2;
                            Object obj18 = obj17;
                            try {
                                s1 = numberCompare.apply2(obj8, Integer.valueOf($PcStringSuffixLengthCi(obj9, intValue, intValue2, obj14, intValue3, ((Number) obj17).intValue())));
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th6 = th5;
                                new WrongType(classCastException, "%string-suffix-length-ci", 5, obj18);
                                throw th6;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th7 = th4;
                            new WrongType(classCastException2, "%string-suffix-length-ci", 4, obj16);
                            throw th7;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th8 = th3;
                        new WrongType(classCastException3, "%string-suffix-length-ci", 2, obj13);
                        throw th8;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "%string-suffix-length-ci", 1, obj11);
                    throw th9;
                }
            } else {
                s1 = x ? Boolean.TRUE : Boolean.FALSE;
            }
            return s1;
        } catch (ClassCastException e5) {
            ClassCastException classCastException5 = e5;
            Throwable th10 = th;
            new WrongType(classCastException5, "x", -2, obj7);
            throw th10;
        }
    }

    public static Object $PcStringCompare(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object obj10;
        Object s1;
        Object s12 = obj;
        Object start1 = obj2;
        Object end1 = obj3;
        Object s2 = obj4;
        Object start2 = obj5;
        Object end2 = obj6;
        Object proc$Ls = obj7;
        Object proc$Eq = obj8;
        Object proc$Gr = obj9;
        Object apply2 = AddOp.$Mn.apply2(end1, start1);
        Object size2 = AddOp.$Mn.apply2(end2, start2);
        Object size1 = apply2;
        Object match = $PcStringPrefixLength(s12, start1, end1, s2, start2, end2);
        if (Scheme.numEqu.apply2(match, size1) != Boolean.FALSE) {
            s1 = Scheme.applyToArgs.apply2(Scheme.numEqu.apply2(match, size2) != Boolean.FALSE ? proc$Eq : proc$Ls, end1);
        } else {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            if (Scheme.numEqu.apply2(match, size2) != Boolean.FALSE) {
                obj10 = proc$Gr;
            } else {
                Object obj11 = s12;
                Object obj12 = obj11;
                try {
                    CharSequence charSequence = (CharSequence) obj11;
                    Object apply22 = AddOp.$Pl.apply2(start1, match);
                    Object obj13 = apply22;
                    try {
                        Char make = Char.make(strings.stringRef(charSequence, ((Number) apply22).intValue()));
                        Object obj14 = s2;
                        Object obj15 = obj14;
                        try {
                            CharSequence charSequence2 = (CharSequence) obj14;
                            Object apply23 = AddOp.$Pl.apply2(start2, match);
                            Object obj16 = apply23;
                            try {
                                obj10 = characters.isChar$Ls(make, Char.make(strings.stringRef(charSequence2, ((Number) apply23).intValue()))) ? proc$Ls : proc$Gr;
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th5 = th4;
                                new WrongType(classCastException, "string-ref", 2, obj16);
                                throw th5;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th6 = th3;
                            new WrongType(classCastException2, "string-ref", 1, obj15);
                            throw th6;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th7 = th2;
                        new WrongType(classCastException3, "string-ref", 2, obj13);
                        throw th7;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "string-ref", 1, obj12);
                    throw th8;
                }
            }
            s1 = applyToArgs.apply2(obj10, AddOp.$Pl.apply2(match, start1));
        }
        return s1;
    }

    public static Object $PcStringCompareCi(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Object obj10;
        Object s1;
        Object s12 = obj;
        Object start1 = obj2;
        Object end1 = obj3;
        Object s2 = obj4;
        Object start2 = obj5;
        Object end2 = obj6;
        Object proc$Ls = obj7;
        Object proc$Eq = obj8;
        Object proc$Gr = obj9;
        Object apply2 = AddOp.$Mn.apply2(end1, start1);
        Object size2 = AddOp.$Mn.apply2(end2, start2);
        Object size1 = apply2;
        Object obj11 = s12;
        Object obj12 = start1;
        Object obj13 = obj12;
        try {
            int intValue = ((Number) obj12).intValue();
            Object obj14 = end1;
            Object obj15 = obj14;
            try {
                int intValue2 = ((Number) obj14).intValue();
                Object obj16 = s2;
                Object obj17 = start2;
                Object obj18 = obj17;
                try {
                    int intValue3 = ((Number) obj17).intValue();
                    Object obj19 = end2;
                    Object obj20 = obj19;
                    try {
                        int match = $PcStringPrefixLengthCi(obj11, intValue, intValue2, obj16, intValue3, ((Number) obj19).intValue());
                        if (Scheme.numEqu.apply2(Integer.valueOf(match), size1) != Boolean.FALSE) {
                            s1 = Scheme.applyToArgs.apply2(Scheme.numEqu.apply2(Integer.valueOf(match), size2) != Boolean.FALSE ? proc$Eq : proc$Ls, end1);
                        } else {
                            ApplyToArgs applyToArgs = Scheme.applyToArgs;
                            if (Scheme.numEqu.apply2(Integer.valueOf(match), size2) != Boolean.FALSE) {
                                obj10 = proc$Gr;
                            } else {
                                Object obj21 = s12;
                                Object obj22 = obj21;
                                try {
                                    CharSequence charSequence = (CharSequence) obj21;
                                    Object apply22 = AddOp.$Pl.apply2(start1, Integer.valueOf(match));
                                    Object obj23 = apply22;
                                    try {
                                        Char make = Char.make(strings.stringRef(charSequence, ((Number) apply22).intValue()));
                                        Object obj24 = s2;
                                        Object obj25 = obj24;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj24;
                                            Object apply23 = AddOp.$Pl.apply2(start2, Integer.valueOf(match));
                                            Object obj26 = apply23;
                                            try {
                                                obj10 = unicode.isCharCi$Ls(make, Char.make(strings.stringRef(charSequence2, ((Number) apply23).intValue()))) ? proc$Ls : proc$Gr;
                                            } catch (ClassCastException e) {
                                                ClassCastException classCastException = e;
                                                Throwable th9 = th8;
                                                new WrongType(classCastException, "string-ref", 2, obj26);
                                                throw th9;
                                            }
                                        } catch (ClassCastException e2) {
                                            ClassCastException classCastException2 = e2;
                                            Throwable th10 = th7;
                                            new WrongType(classCastException2, "string-ref", 1, obj25);
                                            throw th10;
                                        }
                                    } catch (ClassCastException e3) {
                                        ClassCastException classCastException3 = e3;
                                        Throwable th11 = th6;
                                        new WrongType(classCastException3, "string-ref", 2, obj23);
                                        throw th11;
                                    }
                                } catch (ClassCastException e4) {
                                    ClassCastException classCastException4 = e4;
                                    Throwable th12 = th5;
                                    new WrongType(classCastException4, "string-ref", 1, obj22);
                                    throw th12;
                                }
                            }
                            s1 = applyToArgs.apply2(obj10, AddOp.$Pl.apply2(start1, Integer.valueOf(match)));
                        }
                        return s1;
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th13 = th4;
                        new WrongType(classCastException5, "%string-prefix-length-ci", 5, obj20);
                        throw th13;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th14 = th3;
                    new WrongType(classCastException6, "%string-prefix-length-ci", 4, obj18);
                    throw th14;
                }
            } catch (ClassCastException e7) {
                ClassCastException classCastException7 = e7;
                Throwable th15 = th2;
                new WrongType(classCastException7, "%string-prefix-length-ci", 2, obj15);
                throw th15;
            }
        } catch (ClassCastException e8) {
            ClassCastException classCastException8 = e8;
            Throwable th16 = th;
            new WrongType(classCastException8, "%string-prefix-length-ci", 1, obj13);
            throw th16;
        }
    }

    public static Object stringCompare$V(Object s1, Object s2, Object proc$Ls, Object proc$Eq, Object proc$Gr, Object[] argsArray) {
        frame27 frame272;
        new frame27();
        frame27 frame273 = frame272;
        frame273.f162s1 = s1;
        frame273.f163s2 = s2;
        frame273.proc$Ls = proc$Ls;
        frame273.proc$Eq = proc$Eq;
        frame273.proc$Gr = proc$Gr;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame273.maybe$Mnstarts$Plends = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame273.proc$Ls, string$Mncompare);
            try {
                Object apply42 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame273.proc$Eq, string$Mncompare);
                try {
                    Object apply43 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame273.proc$Gr, string$Mncompare);
                    return call_with_values.callWithValues(frame273.lambda$Fn60, frame273.lambda$Fn61);
                } catch (UnboundLocationException e) {
                    UnboundLocationException unboundLocationException = e;
                    UnboundLocationException unboundLocationException2 = unboundLocationException;
                    unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 728, 3);
                    throw unboundLocationException2;
                }
            } catch (UnboundLocationException e2) {
                UnboundLocationException unboundLocationException3 = e2;
                UnboundLocationException unboundLocationException4 = unboundLocationException3;
                unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 727, 3);
                throw unboundLocationException4;
            }
        } catch (UnboundLocationException e3) {
            UnboundLocationException unboundLocationException5 = e3;
            UnboundLocationException unboundLocationException6 = unboundLocationException5;
            unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 726, 3);
            throw unboundLocationException6;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame27 extends ModuleBody {
        final ModuleMethod lambda$Fn60;
        final ModuleMethod lambda$Fn61;
        LList maybe$Mnstarts$Plends;
        Object proc$Eq;
        Object proc$Gr;
        Object proc$Ls;

        /* renamed from: s1 */
        Object f162s1;

        /* renamed from: s2 */
        Object f163s2;

        public frame27() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 58, (Object) null, 0);
            this.lambda$Fn60 = moduleMethod;
            new ModuleMethod(this, 59, (Object) null, 12291);
            this.lambda$Fn61 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 58 ? lambda60() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 59 ? lambda61(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 58) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 59) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda60() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncompare, this.f162s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda61(Object rest, Object start1, Object end1) {
            frame28 frame28;
            new frame28();
            frame28 frame282 = frame28;
            frame282.staticLink = this;
            frame28 frame283 = frame282;
            frame283.rest = rest;
            frame283.start1 = start1;
            frame283.end1 = end1;
            return call_with_values.callWithValues(frame283.lambda$Fn62, frame283.lambda$Fn63);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame28 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn62;
        final ModuleMethod lambda$Fn63;
        Object rest;
        Object start1;
        frame27 staticLink;

        public frame28() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 56, (Object) null, 0);
            this.lambda$Fn62 = moduleMethod;
            new ModuleMethod(this, 57, (Object) null, 8194);
            this.lambda$Fn63 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 56 ? lambda62() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 57 ? lambda63(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 56) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 57) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda62() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare, this.staticLink.f163s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda63(Object start2, Object end2) {
            return srfi13.$PcStringCompare(this.staticLink.f162s1, this.start1, this.end1, this.staticLink.f163s2, start2, end2, this.staticLink.proc$Ls, this.staticLink.proc$Eq, this.staticLink.proc$Gr);
        }
    }

    public static Object stringCompareCi$V(Object s1, Object s2, Object proc$Ls, Object proc$Eq, Object proc$Gr, Object[] argsArray) {
        frame29 frame292;
        new frame29();
        frame29 frame293 = frame292;
        frame293.f164s1 = s1;
        frame293.f165s2 = s2;
        frame293.proc$Ls = proc$Ls;
        frame293.proc$Eq = proc$Eq;
        frame293.proc$Gr = proc$Gr;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame293.maybe$Mnstarts$Plends = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame293.proc$Ls, string$Mncompare$Mnci);
            try {
                Object apply42 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame293.proc$Eq, string$Mncompare$Mnci);
                try {
                    Object apply43 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame293.proc$Gr, string$Mncompare$Mnci);
                    return call_with_values.callWithValues(frame293.lambda$Fn64, frame293.lambda$Fn65);
                } catch (UnboundLocationException e) {
                    UnboundLocationException unboundLocationException = e;
                    UnboundLocationException unboundLocationException2 = unboundLocationException;
                    unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 736, 3);
                    throw unboundLocationException2;
                }
            } catch (UnboundLocationException e2) {
                UnboundLocationException unboundLocationException3 = e2;
                UnboundLocationException unboundLocationException4 = unboundLocationException3;
                unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 735, 3);
                throw unboundLocationException4;
            }
        } catch (UnboundLocationException e3) {
            UnboundLocationException unboundLocationException5 = e3;
            UnboundLocationException unboundLocationException6 = unboundLocationException5;
            unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 734, 3);
            throw unboundLocationException6;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame29 extends ModuleBody {
        final ModuleMethod lambda$Fn64;
        final ModuleMethod lambda$Fn65;
        LList maybe$Mnstarts$Plends;
        Object proc$Eq;
        Object proc$Gr;
        Object proc$Ls;

        /* renamed from: s1 */
        Object f164s1;

        /* renamed from: s2 */
        Object f165s2;

        public frame29() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 62, (Object) null, 0);
            this.lambda$Fn64 = moduleMethod;
            new ModuleMethod(this, 63, (Object) null, 12291);
            this.lambda$Fn65 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 62 ? lambda64() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 63 ? lambda65(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 62) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 63) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda64() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncompare$Mnci, this.f164s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda65(Object rest, Object start1, Object end1) {
            frame30 frame30;
            new frame30();
            frame30 frame302 = frame30;
            frame302.staticLink = this;
            frame30 frame303 = frame302;
            frame303.rest = rest;
            frame303.start1 = start1;
            frame303.end1 = end1;
            return call_with_values.callWithValues(frame303.lambda$Fn66, frame303.lambda$Fn67);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame30 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn66;
        final ModuleMethod lambda$Fn67;
        Object rest;
        Object start1;
        frame29 staticLink;

        public frame30() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 60, (Object) null, 0);
            this.lambda$Fn66 = moduleMethod;
            new ModuleMethod(this, 61, (Object) null, 8194);
            this.lambda$Fn67 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 60 ? lambda66() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 61 ? lambda67(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 60) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 61) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda66() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare$Mnci, this.staticLink.f165s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda67(Object start2, Object end2) {
            return srfi13.$PcStringCompareCi(this.staticLink.f164s1, this.start1, this.end1, this.staticLink.f165s2, start2, end2, this.staticLink.proc$Ls, this.staticLink.proc$Eq, this.staticLink.proc$Gr);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame31 extends ModuleBody {
        final ModuleMethod lambda$Fn68;
        final ModuleMethod lambda$Fn69;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f167s1;

        /* renamed from: s2 */
        Object f168s2;

        public frame31() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 66, (Object) null, 0);
            this.lambda$Fn68 = moduleMethod;
            new ModuleMethod(this, 67, (Object) null, 12291);
            this.lambda$Fn69 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 66 ? lambda68() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 67 ? lambda69(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 66) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 67) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda68() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Eq, this.f167s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda69(Object rest, Object start1, Object end1) {
            frame32 frame32;
            new frame32();
            frame32 frame322 = frame32;
            frame322.staticLink = this;
            frame32 frame323 = frame322;
            frame323.rest = rest;
            frame323.start1 = start1;
            frame323.end1 = end1;
            return call_with_values.callWithValues(frame323.lambda$Fn70, frame323.lambda$Fn71);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame32 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn70;
        final ModuleMethod lambda$Fn71;
        Object rest;
        Object start1;
        frame31 staticLink;

        public frame32() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 64, (Object) null, 0);
            this.lambda$Fn70 = moduleMethod;
            new ModuleMethod(this, 65, (Object) null, 8194);
            this.lambda$Fn71 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 64 ? lambda70() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 65 ? lambda71(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 64) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 65) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda70() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Eq, this.staticLink.f168s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda71(Object obj, Object obj2) {
            Throwable th;
            Object obj3;
            boolean z;
            Throwable th2;
            Object start2 = obj;
            Object end2 = obj2;
            Object apply2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(end2, start2));
            Object obj4 = apply2;
            try {
                boolean x = ((Boolean) apply2).booleanValue();
                if (x) {
                    boolean x2 = this.staticLink.f167s1 == this.staticLink.f168s2;
                    if (x2) {
                        Object apply22 = Scheme.numEqu.apply2(this.start1, start2);
                        Object obj5 = apply22;
                        try {
                            z = ((Boolean) apply22).booleanValue();
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th3 = th2;
                            new WrongType(classCastException, "x", -2, obj5);
                            throw th3;
                        }
                    } else {
                        z = x2;
                    }
                    boolean x3 = z;
                    obj3 = x3 ? x3 ? Boolean.TRUE : Boolean.FALSE : srfi13.$PcStringCompare(this.staticLink.f167s1, this.start1, this.end1, this.staticLink.f168s2, start2, end2, srfi13.lambda$Fn72, misc.values, srfi13.lambda$Fn73);
                } else {
                    obj3 = x ? Boolean.TRUE : Boolean.FALSE;
                }
                return obj3;
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "x", -2, obj4);
                throw th4;
            }
        }

        static Boolean lambda72(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }

        static Boolean lambda73(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object string$Eq$V(Object s1, Object s2, Object[] argsArray) {
        frame31 frame312;
        new frame31();
        frame31 frame313 = frame312;
        frame313.f167s1 = s1;
        frame313.f168s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame313.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame313.lambda$Fn68, frame313.lambda$Fn69);
    }

    /* compiled from: srfi13.scm */
    public class frame33 extends ModuleBody {
        final ModuleMethod lambda$Fn74;
        final ModuleMethod lambda$Fn75;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f169s1;

        /* renamed from: s2 */
        Object f170s2;

        public frame33() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 70, (Object) null, 0);
            this.lambda$Fn74 = moduleMethod;
            new ModuleMethod(this, 71, (Object) null, 12291);
            this.lambda$Fn75 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 70 ? lambda74() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 71 ? lambda75(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 70) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 71) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda74() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls$Gr, this.f169s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda75(Object rest, Object start1, Object end1) {
            frame34 frame34;
            new frame34();
            frame34 frame342 = frame34;
            frame342.staticLink = this;
            frame34 frame343 = frame342;
            frame343.rest = rest;
            frame343.start1 = start1;
            frame343.end1 = end1;
            return call_with_values.callWithValues(frame343.lambda$Fn76, frame343.lambda$Fn77);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame34 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn76;
        final ModuleMethod lambda$Fn77;
        Object rest;
        Object start1;
        frame33 staticLink;

        public frame34() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 68, (Object) null, 0);
            this.lambda$Fn76 = moduleMethod;
            new ModuleMethod(this, 69, (Object) null, 8194);
            this.lambda$Fn77 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 68 ? lambda76() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 69 ? lambda77(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 68) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 69) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda76() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Gr, this.staticLink.f170s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda77(Object obj, Object obj2) {
            Throwable th;
            boolean z;
            Object $PcStringCompare;
            Throwable th2;
            Object start2 = obj;
            Object end2 = obj2;
            Object apply2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(end2, start2));
            Object obj3 = apply2;
            try {
                boolean x = ((apply2 != Boolean.FALSE ? 1 : 0) + 1) & true;
                if (x) {
                    $PcStringCompare = x ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    boolean x2 = this.staticLink.f169s1 == this.staticLink.f170s2;
                    if (x2) {
                        Object apply22 = Scheme.numEqu.apply2(this.start1, start2);
                        Object obj4 = apply22;
                        try {
                            z = apply22 != Boolean.FALSE;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th3 = th2;
                            new WrongType(classCastException, "x", -2, obj4);
                            throw th3;
                        }
                    } else {
                        z = x2;
                    }
                    boolean x3 = (z + true) & true;
                    $PcStringCompare = x3 ? srfi13.$PcStringCompare(this.staticLink.f169s1, this.start1, this.end1, this.staticLink.f170s2, start2, end2, misc.values, srfi13.lambda$Fn78, misc.values) : x3 ? Boolean.TRUE : Boolean.FALSE;
                }
                return $PcStringCompare;
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "x", -2, obj3);
                throw th4;
            }
        }

        static Boolean lambda78(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object string$Ls$Gr$V(Object s1, Object s2, Object[] argsArray) {
        frame33 frame332;
        new frame33();
        frame33 frame333 = frame332;
        frame333.f169s1 = s1;
        frame333.f170s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame333.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame333.lambda$Fn74, frame333.lambda$Fn75);
    }

    /* compiled from: srfi13.scm */
    public class frame35 extends ModuleBody {
        final ModuleMethod lambda$Fn79;
        final ModuleMethod lambda$Fn80;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f171s1;

        /* renamed from: s2 */
        Object f172s2;

        public frame35() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 74, (Object) null, 0);
            this.lambda$Fn79 = moduleMethod;
            new ModuleMethod(this, 75, (Object) null, 12291);
            this.lambda$Fn80 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 74 ? lambda79() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 75 ? lambda80(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 74) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 75) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda79() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls, this.f171s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda80(Object rest, Object start1, Object end1) {
            frame36 frame36;
            new frame36();
            frame36 frame362 = frame36;
            frame362.staticLink = this;
            frame36 frame363 = frame362;
            frame363.rest = rest;
            frame363.start1 = start1;
            frame363.end1 = end1;
            return call_with_values.callWithValues(frame363.lambda$Fn81, frame363.lambda$Fn82);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame36 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn81;
        final ModuleMethod lambda$Fn82;
        Object rest;
        Object start1;
        frame35 staticLink;

        public frame36() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 72, (Object) null, 0);
            this.lambda$Fn81 = moduleMethod;
            new ModuleMethod(this, 73, (Object) null, 8194);
            this.lambda$Fn82 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 72 ? lambda81() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 73 ? lambda82(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 72) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 73) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda81() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls, this.staticLink.f172s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda82(Object obj, Object obj2) {
            Object $PcStringCompare;
            Object start2 = obj;
            Object end2 = obj2;
            boolean x = this.staticLink.f171s1 == this.staticLink.f172s2;
            if (!x ? !x : Scheme.numEqu.apply2(this.start1, start2) == Boolean.FALSE) {
                $PcStringCompare = srfi13.$PcStringCompare(this.staticLink.f171s1, this.start1, this.end1, this.staticLink.f172s2, start2, end2, misc.values, srfi13.lambda$Fn83, srfi13.lambda$Fn84);
            } else {
                $PcStringCompare = Scheme.numLss.apply2(this.end1, end2);
            }
            return $PcStringCompare;
        }

        static Boolean lambda83(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }

        static Boolean lambda84(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object string$Ls$V(Object s1, Object s2, Object[] argsArray) {
        frame35 frame352;
        new frame35();
        frame35 frame353 = frame352;
        frame353.f171s1 = s1;
        frame353.f172s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame353.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame353.lambda$Fn79, frame353.lambda$Fn80);
    }

    /* compiled from: srfi13.scm */
    public class frame37 extends ModuleBody {
        final ModuleMethod lambda$Fn85;
        final ModuleMethod lambda$Fn86;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f173s1;

        /* renamed from: s2 */
        Object f174s2;

        public frame37() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 78, (Object) null, 0);
            this.lambda$Fn85 = moduleMethod;
            new ModuleMethod(this, 79, (Object) null, 12291);
            this.lambda$Fn86 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 78 ? lambda85() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 79 ? lambda86(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 78) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 79) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda85() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Gr, this.f173s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda86(Object rest, Object start1, Object end1) {
            frame38 frame38;
            new frame38();
            frame38 frame382 = frame38;
            frame382.staticLink = this;
            frame38 frame383 = frame382;
            frame383.rest = rest;
            frame383.start1 = start1;
            frame383.end1 = end1;
            return call_with_values.callWithValues(frame383.lambda$Fn87, frame383.lambda$Fn88);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame38 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn87;
        final ModuleMethod lambda$Fn88;
        Object rest;
        Object start1;
        frame37 staticLink;

        public frame38() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 76, (Object) null, 0);
            this.lambda$Fn87 = moduleMethod;
            new ModuleMethod(this, 77, (Object) null, 8194);
            this.lambda$Fn88 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 76 ? lambda87() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 77 ? lambda88(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 76) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 77) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda87() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr, this.staticLink.f174s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda88(Object obj, Object obj2) {
            Object $PcStringCompare;
            Object start2 = obj;
            Object end2 = obj2;
            boolean x = this.staticLink.f173s1 == this.staticLink.f174s2;
            if (!x ? !x : Scheme.numEqu.apply2(this.start1, start2) == Boolean.FALSE) {
                $PcStringCompare = srfi13.$PcStringCompare(this.staticLink.f173s1, this.start1, this.end1, this.staticLink.f174s2, start2, end2, srfi13.lambda$Fn89, srfi13.lambda$Fn90, misc.values);
            } else {
                $PcStringCompare = Scheme.numGrt.apply2(this.end1, end2);
            }
            return $PcStringCompare;
        }

        static Boolean lambda89(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }

        static Boolean lambda90(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object string$Gr$V(Object s1, Object s2, Object[] argsArray) {
        frame37 frame372;
        new frame37();
        frame37 frame373 = frame372;
        frame373.f173s1 = s1;
        frame373.f174s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame373.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame373.lambda$Fn85, frame373.lambda$Fn86);
    }

    /* compiled from: srfi13.scm */
    public class frame39 extends ModuleBody {
        final ModuleMethod lambda$Fn91;
        final ModuleMethod lambda$Fn92;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f175s1;

        /* renamed from: s2 */
        Object f176s2;

        public frame39() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 82, (Object) null, 0);
            this.lambda$Fn91 = moduleMethod;
            new ModuleMethod(this, 83, (Object) null, 12291);
            this.lambda$Fn92 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 82 ? lambda91() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 83 ? lambda92(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 82) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 83) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda91() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls$Eq, this.f175s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda92(Object rest, Object start1, Object end1) {
            frame40 frame40;
            new frame40();
            frame40 frame402 = frame40;
            frame402.staticLink = this;
            frame40 frame403 = frame402;
            frame403.rest = rest;
            frame403.start1 = start1;
            frame403.end1 = end1;
            return call_with_values.callWithValues(frame403.lambda$Fn93, frame403.lambda$Fn94);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame40 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn93;
        final ModuleMethod lambda$Fn94;
        Object rest;
        Object start1;
        frame39 staticLink;

        public frame40() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 80, (Object) null, 0);
            this.lambda$Fn93 = moduleMethod;
            new ModuleMethod(this, 81, (Object) null, 8194);
            this.lambda$Fn94 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 80 ? lambda93() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 81 ? lambda94(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 80) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 81) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda93() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Eq, this.staticLink.f176s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda94(Object obj, Object obj2) {
            Object $PcStringCompare;
            Object start2 = obj;
            Object end2 = obj2;
            boolean x = this.staticLink.f175s1 == this.staticLink.f176s2;
            if (!x ? !x : Scheme.numEqu.apply2(this.start1, start2) == Boolean.FALSE) {
                $PcStringCompare = srfi13.$PcStringCompare(this.staticLink.f175s1, this.start1, this.end1, this.staticLink.f176s2, start2, end2, misc.values, misc.values, srfi13.lambda$Fn95);
            } else {
                $PcStringCompare = Scheme.numLEq.apply2(this.end1, end2);
            }
            return $PcStringCompare;
        }

        static Boolean lambda95(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object string$Ls$Eq$V(Object s1, Object s2, Object[] argsArray) {
        frame39 frame392;
        new frame39();
        frame39 frame393 = frame392;
        frame393.f175s1 = s1;
        frame393.f176s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame393.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame393.lambda$Fn91, frame393.lambda$Fn92);
    }

    /* compiled from: srfi13.scm */
    public class frame41 extends ModuleBody {
        final ModuleMethod lambda$Fn96;
        final ModuleMethod lambda$Fn97;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f178s1;

        /* renamed from: s2 */
        Object f179s2;

        public frame41() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 86, (Object) null, 0);
            this.lambda$Fn96 = moduleMethod;
            new ModuleMethod(this, 87, (Object) null, 12291);
            this.lambda$Fn97 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 86 ? lambda96() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 87 ? lambda97(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 86) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 87) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda96() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Gr$Eq, this.f178s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda97(Object rest, Object start1, Object end1) {
            frame42 frame42;
            new frame42();
            frame42 frame422 = frame42;
            frame422.staticLink = this;
            frame42 frame423 = frame422;
            frame423.rest = rest;
            frame423.start1 = start1;
            frame423.end1 = end1;
            return call_with_values.callWithValues(frame423.lambda$Fn98, frame423.lambda$Fn99);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame42 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn98;
        final ModuleMethod lambda$Fn99;
        Object rest;
        Object start1;
        frame41 staticLink;

        public frame42() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 84, (Object) null, 0);
            this.lambda$Fn98 = moduleMethod;
            new ModuleMethod(this, 85, (Object) null, 8194);
            this.lambda$Fn99 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 84 ? lambda98() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 85 ? lambda99(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 84) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 85) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda98() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr$Eq, this.staticLink.f179s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda99(Object obj, Object obj2) {
            Object $PcStringCompare;
            Object start2 = obj;
            Object end2 = obj2;
            boolean x = this.staticLink.f178s1 == this.staticLink.f179s2;
            if (!x ? !x : Scheme.numEqu.apply2(this.start1, start2) == Boolean.FALSE) {
                $PcStringCompare = srfi13.$PcStringCompare(this.staticLink.f178s1, this.start1, this.end1, this.staticLink.f179s2, start2, end2, srfi13.lambda$Fn100, misc.values, misc.values);
            } else {
                $PcStringCompare = Scheme.numGEq.apply2(this.end1, end2);
            }
            return $PcStringCompare;
        }

        static Boolean lambda100(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object string$Gr$Eq$V(Object s1, Object s2, Object[] argsArray) {
        frame41 frame412;
        new frame41();
        frame41 frame413 = frame412;
        frame413.f178s1 = s1;
        frame413.f179s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame413.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame413.lambda$Fn96, frame413.lambda$Fn97);
    }

    /* compiled from: srfi13.scm */
    public class frame43 extends ModuleBody {
        final ModuleMethod lambda$Fn101;
        final ModuleMethod lambda$Fn102;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f180s1;

        /* renamed from: s2 */
        Object f181s2;

        public frame43() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 90, (Object) null, 0);
            this.lambda$Fn101 = moduleMethod;
            new ModuleMethod(this, 91, (Object) null, 12291);
            this.lambda$Fn102 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 90 ? lambda101() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 91 ? lambda102(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 90) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 91) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda101() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Eq, this.f180s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda102(Object rest, Object start1, Object end1) {
            frame44 frame44;
            new frame44();
            frame44 frame442 = frame44;
            frame442.staticLink = this;
            frame44 frame443 = frame442;
            frame443.rest = rest;
            frame443.start1 = start1;
            frame443.end1 = end1;
            return call_with_values.callWithValues(frame443.lambda$Fn103, frame443.lambda$Fn104);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame44 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn103;
        final ModuleMethod lambda$Fn104;
        Object rest;
        Object start1;
        frame43 staticLink;

        public frame44() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 88, (Object) null, 0);
            this.lambda$Fn103 = moduleMethod;
            new ModuleMethod(this, 89, (Object) null, 8194);
            this.lambda$Fn104 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 88 ? lambda103() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 89 ? lambda104(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 88) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 89) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda103() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Eq, this.staticLink.f181s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda104(Object obj, Object obj2) {
            Throwable th;
            Object obj3;
            boolean z;
            Throwable th2;
            Object start2 = obj;
            Object end2 = obj2;
            Object apply2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(end2, start2));
            Object obj4 = apply2;
            try {
                boolean x = ((Boolean) apply2).booleanValue();
                if (x) {
                    boolean x2 = this.staticLink.f180s1 == this.staticLink.f181s2;
                    if (x2) {
                        Object apply22 = Scheme.numEqu.apply2(this.start1, start2);
                        Object obj5 = apply22;
                        try {
                            z = ((Boolean) apply22).booleanValue();
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th3 = th2;
                            new WrongType(classCastException, "x", -2, obj5);
                            throw th3;
                        }
                    } else {
                        z = x2;
                    }
                    boolean x3 = z;
                    obj3 = x3 ? x3 ? Boolean.TRUE : Boolean.FALSE : srfi13.$PcStringCompareCi(this.staticLink.f180s1, this.start1, this.end1, this.staticLink.f181s2, start2, end2, srfi13.lambda$Fn105, misc.values, srfi13.lambda$Fn106);
                } else {
                    obj3 = x ? Boolean.TRUE : Boolean.FALSE;
                }
                return obj3;
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "x", -2, obj4);
                throw th4;
            }
        }

        static Boolean lambda105(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }

        static Boolean lambda106(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Eq$V(Object s1, Object s2, Object[] argsArray) {
        frame43 frame432;
        new frame43();
        frame43 frame433 = frame432;
        frame433.f180s1 = s1;
        frame433.f181s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame433.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame433.lambda$Fn101, frame433.lambda$Fn102);
    }

    /* compiled from: srfi13.scm */
    public class frame45 extends ModuleBody {
        final ModuleMethod lambda$Fn107;
        final ModuleMethod lambda$Fn108;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f182s1;

        /* renamed from: s2 */
        Object f183s2;

        public frame45() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 94, (Object) null, 0);
            this.lambda$Fn107 = moduleMethod;
            new ModuleMethod(this, 95, (Object) null, 12291);
            this.lambda$Fn108 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 94 ? lambda107() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 95 ? lambda108(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 94) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 95) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda107() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls$Gr, this.f182s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda108(Object rest, Object start1, Object end1) {
            frame46 frame46;
            new frame46();
            frame46 frame462 = frame46;
            frame462.staticLink = this;
            frame46 frame463 = frame462;
            frame463.rest = rest;
            frame463.start1 = start1;
            frame463.end1 = end1;
            return call_with_values.callWithValues(frame463.lambda$Fn109, frame463.lambda$Fn110);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame46 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn109;
        final ModuleMethod lambda$Fn110;
        Object rest;
        Object start1;
        frame45 staticLink;

        public frame46() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 92, (Object) null, 0);
            this.lambda$Fn109 = moduleMethod;
            new ModuleMethod(this, 93, (Object) null, 8194);
            this.lambda$Fn110 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 92 ? lambda109() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 93 ? lambda110(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 92) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 93) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda109() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Gr, this.staticLink.f183s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda110(Object obj, Object obj2) {
            Throwable th;
            boolean z;
            Object $PcStringCompareCi;
            Throwable th2;
            Object start2 = obj;
            Object end2 = obj2;
            Object apply2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(end2, start2));
            Object obj3 = apply2;
            try {
                boolean x = ((apply2 != Boolean.FALSE ? 1 : 0) + 1) & true;
                if (x) {
                    $PcStringCompareCi = x ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    boolean x2 = this.staticLink.f182s1 == this.staticLink.f183s2;
                    if (x2) {
                        Object apply22 = Scheme.numEqu.apply2(this.start1, start2);
                        Object obj4 = apply22;
                        try {
                            z = apply22 != Boolean.FALSE;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th3 = th2;
                            new WrongType(classCastException, "x", -2, obj4);
                            throw th3;
                        }
                    } else {
                        z = x2;
                    }
                    boolean x3 = (z + true) & true;
                    $PcStringCompareCi = x3 ? srfi13.$PcStringCompareCi(this.staticLink.f182s1, this.start1, this.end1, this.staticLink.f183s2, start2, end2, misc.values, srfi13.lambda$Fn111, misc.values) : x3 ? Boolean.TRUE : Boolean.FALSE;
                }
                return $PcStringCompareCi;
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "x", -2, obj3);
                throw th4;
            }
        }

        static Boolean lambda111(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Ls$Gr$V(Object s1, Object s2, Object[] argsArray) {
        frame45 frame452;
        new frame45();
        frame45 frame453 = frame452;
        frame453.f182s1 = s1;
        frame453.f183s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame453.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame453.lambda$Fn107, frame453.lambda$Fn108);
    }

    /* compiled from: srfi13.scm */
    public class frame47 extends ModuleBody {
        final ModuleMethod lambda$Fn112;
        final ModuleMethod lambda$Fn113;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f184s1;

        /* renamed from: s2 */
        Object f185s2;

        public frame47() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 98, (Object) null, 0);
            this.lambda$Fn112 = moduleMethod;
            new ModuleMethod(this, 99, (Object) null, 12291);
            this.lambda$Fn113 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 98 ? lambda112() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 99 ? lambda113(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 98) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 99) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda112() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls, this.f184s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda113(Object rest, Object start1, Object end1) {
            frame48 frame48;
            new frame48();
            frame48 frame482 = frame48;
            frame482.staticLink = this;
            frame48 frame483 = frame482;
            frame483.rest = rest;
            frame483.start1 = start1;
            frame483.end1 = end1;
            return call_with_values.callWithValues(frame483.lambda$Fn114, frame483.lambda$Fn115);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame48 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn114;
        final ModuleMethod lambda$Fn115;
        Object rest;
        Object start1;
        frame47 staticLink;

        public frame48() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 96, (Object) null, 0);
            this.lambda$Fn114 = moduleMethod;
            new ModuleMethod(this, 97, (Object) null, 8194);
            this.lambda$Fn115 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 96 ? lambda114() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 97 ? lambda115(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 96) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 97) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda114() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls, this.staticLink.f185s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda115(Object obj, Object obj2) {
            Object $PcStringCompareCi;
            Object start2 = obj;
            Object end2 = obj2;
            boolean x = this.staticLink.f184s1 == this.staticLink.f185s2;
            if (!x ? !x : Scheme.numEqu.apply2(this.start1, start2) == Boolean.FALSE) {
                $PcStringCompareCi = srfi13.$PcStringCompareCi(this.staticLink.f184s1, this.start1, this.end1, this.staticLink.f185s2, start2, end2, misc.values, srfi13.lambda$Fn116, srfi13.lambda$Fn117);
            } else {
                $PcStringCompareCi = Scheme.numLss.apply2(this.end1, end2);
            }
            return $PcStringCompareCi;
        }

        static Boolean lambda116(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }

        static Boolean lambda117(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Ls$V(Object s1, Object s2, Object[] argsArray) {
        frame47 frame472;
        new frame47();
        frame47 frame473 = frame472;
        frame473.f184s1 = s1;
        frame473.f185s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame473.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame473.lambda$Fn112, frame473.lambda$Fn113);
    }

    /* compiled from: srfi13.scm */
    public class frame49 extends ModuleBody {
        final ModuleMethod lambda$Fn118;
        final ModuleMethod lambda$Fn119;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f186s1;

        /* renamed from: s2 */
        Object f187s2;

        public frame49() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 102, (Object) null, 0);
            this.lambda$Fn118 = moduleMethod;
            new ModuleMethod(this, 103, (Object) null, 12291);
            this.lambda$Fn119 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 102 ? lambda118() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 103 ? lambda119(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 102) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 103) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda118() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Gr, this.f186s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda119(Object rest, Object start1, Object end1) {
            frame50 frame50;
            new frame50();
            frame50 frame502 = frame50;
            frame502.staticLink = this;
            frame50 frame503 = frame502;
            frame503.rest = rest;
            frame503.start1 = start1;
            frame503.end1 = end1;
            return call_with_values.callWithValues(frame503.lambda$Fn120, frame503.lambda$Fn121);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame50 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn120;
        final ModuleMethod lambda$Fn121;
        Object rest;
        Object start1;
        frame49 staticLink;

        public frame50() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 100, (Object) null, 0);
            this.lambda$Fn120 = moduleMethod;
            new ModuleMethod(this, 101, (Object) null, 8194);
            this.lambda$Fn121 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 100 ? lambda120() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 101 ? lambda121(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 100) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 101) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda120() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr, this.staticLink.f187s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda121(Object obj, Object obj2) {
            Object $PcStringCompareCi;
            Object start2 = obj;
            Object end2 = obj2;
            boolean x = this.staticLink.f186s1 == this.staticLink.f187s2;
            if (!x ? !x : Scheme.numEqu.apply2(this.start1, start2) == Boolean.FALSE) {
                $PcStringCompareCi = srfi13.$PcStringCompareCi(this.staticLink.f186s1, this.start1, this.end1, this.staticLink.f187s2, start2, end2, srfi13.lambda$Fn122, srfi13.lambda$Fn123, misc.values);
            } else {
                $PcStringCompareCi = Scheme.numGrt.apply2(this.end1, end2);
            }
            return $PcStringCompareCi;
        }

        static Boolean lambda122(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }

        static Boolean lambda123(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Gr$V(Object s1, Object s2, Object[] argsArray) {
        frame49 frame492;
        new frame49();
        frame49 frame493 = frame492;
        frame493.f186s1 = s1;
        frame493.f187s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame493.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame493.lambda$Fn118, frame493.lambda$Fn119);
    }

    /* compiled from: srfi13.scm */
    public class frame51 extends ModuleBody {
        final ModuleMethod lambda$Fn124;
        final ModuleMethod lambda$Fn125;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f189s1;

        /* renamed from: s2 */
        Object f190s2;

        public frame51() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 106, (Object) null, 0);
            this.lambda$Fn124 = moduleMethod;
            new ModuleMethod(this, 107, (Object) null, 12291);
            this.lambda$Fn125 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 106 ? lambda124() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 107 ? lambda125(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 106) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 107) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda124() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls$Eq, this.f189s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda125(Object rest, Object start1, Object end1) {
            frame52 frame52;
            new frame52();
            frame52 frame522 = frame52;
            frame522.staticLink = this;
            frame52 frame523 = frame522;
            frame523.rest = rest;
            frame523.start1 = start1;
            frame523.end1 = end1;
            return call_with_values.callWithValues(frame523.lambda$Fn126, frame523.lambda$Fn127);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame52 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn126;
        final ModuleMethod lambda$Fn127;
        Object rest;
        Object start1;
        frame51 staticLink;

        public frame52() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 104, (Object) null, 0);
            this.lambda$Fn126 = moduleMethod;
            new ModuleMethod(this, 105, (Object) null, 8194);
            this.lambda$Fn127 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 104 ? lambda126() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 105 ? lambda127(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 104) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 105) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda126() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Eq, this.staticLink.f190s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda127(Object obj, Object obj2) {
            Object $PcStringCompareCi;
            Object start2 = obj;
            Object end2 = obj2;
            boolean x = this.staticLink.f189s1 == this.staticLink.f190s2;
            if (!x ? !x : Scheme.numEqu.apply2(this.start1, start2) == Boolean.FALSE) {
                $PcStringCompareCi = srfi13.$PcStringCompareCi(this.staticLink.f189s1, this.start1, this.end1, this.staticLink.f190s2, start2, end2, misc.values, misc.values, srfi13.lambda$Fn128);
            } else {
                $PcStringCompareCi = Scheme.numLEq.apply2(this.end1, end2);
            }
            return $PcStringCompareCi;
        }

        static Boolean lambda128(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Ls$Eq$V(Object s1, Object s2, Object[] argsArray) {
        frame51 frame512;
        new frame51();
        frame51 frame513 = frame512;
        frame513.f189s1 = s1;
        frame513.f190s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame513.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame513.lambda$Fn124, frame513.lambda$Fn125);
    }

    /* compiled from: srfi13.scm */
    public class frame53 extends ModuleBody {
        final ModuleMethod lambda$Fn129;
        final ModuleMethod lambda$Fn130;
        LList maybe$Mnstarts$Plends;

        /* renamed from: s1 */
        Object f191s1;

        /* renamed from: s2 */
        Object f192s2;

        public frame53() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 110, (Object) null, 0);
            this.lambda$Fn129 = moduleMethod;
            new ModuleMethod(this, 111, (Object) null, 12291);
            this.lambda$Fn130 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 110 ? lambda129() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 111 ? lambda130(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 110) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 111) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda129() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Gr$Eq, this.f191s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda130(Object rest, Object start1, Object end1) {
            frame54 frame54;
            new frame54();
            frame54 frame542 = frame54;
            frame542.staticLink = this;
            frame54 frame543 = frame542;
            frame543.rest = rest;
            frame543.start1 = start1;
            frame543.end1 = end1;
            return call_with_values.callWithValues(frame543.lambda$Fn131, frame543.lambda$Fn132);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame54 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn131;
        final ModuleMethod lambda$Fn132;
        Object rest;
        Object start1;
        frame53 staticLink;

        public frame54() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 108, (Object) null, 0);
            this.lambda$Fn131 = moduleMethod;
            new ModuleMethod(this, 109, (Object) null, 8194);
            this.lambda$Fn132 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 108 ? lambda131() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 109 ? lambda132(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 108) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 109) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda131() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr$Eq, this.staticLink.f192s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda132(Object obj, Object obj2) {
            Object $PcStringCompareCi;
            Object start2 = obj;
            Object end2 = obj2;
            boolean x = this.staticLink.f191s1 == this.staticLink.f192s2;
            if (!x ? !x : Scheme.numEqu.apply2(this.start1, start2) == Boolean.FALSE) {
                $PcStringCompareCi = srfi13.$PcStringCompareCi(this.staticLink.f191s1, this.start1, this.end1, this.staticLink.f192s2, start2, end2, srfi13.lambda$Fn133, misc.values, misc.values);
            } else {
                $PcStringCompareCi = Scheme.numGEq.apply2(this.end1, end2);
            }
            return $PcStringCompareCi;
        }

        static Boolean lambda133(Object obj) {
            Object obj2 = obj;
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Gr$Eq$V(Object s1, Object s2, Object[] argsArray) {
        frame53 frame532;
        new frame53();
        frame53 frame533 = frame532;
        frame533.f191s1 = s1;
        frame533.f192s2 = s2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame533.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame533.lambda$Fn129, frame533.lambda$Fn130);
    }

    public static Object $PcStringHash(Object obj, Object char$To$Int, Object obj2, Object obj3, Object obj4) {
        frame55 frame552;
        Object obj5;
        Throwable th;
        Throwable th2;
        Object s = obj;
        Object bound = obj2;
        Object start = obj3;
        Object end = obj4;
        new frame55();
        frame55 frame553 = frame552;
        frame553.char$Mn$Grint = char$To$Int;
        Object obj6 = Lit5;
        while (true) {
            obj5 = obj6;
            if (Scheme.numGEq.apply2(obj5, bound) != Boolean.FALSE) {
                break;
            }
            obj6 = AddOp.$Pl.apply2(obj5, obj5);
        }
        Object mask = AddOp.$Mn.apply2(obj5, Lit1);
        Object obj7 = start;
        Object obj8 = Lit0;
        while (true) {
            Object obj9 = obj8;
            Object i = obj7;
            frame55 frame554 = frame553;
            if (Scheme.numGEq.apply2(i, end) != Boolean.FALSE) {
                return DivideOp.modulo.apply2(obj9, bound);
            }
            obj7 = AddOp.$Pl.apply2(i, Lit1);
            Object obj10 = i;
            Object obj11 = s;
            Object obj12 = obj11;
            try {
                CharSequence charSequence = (CharSequence) obj11;
                Object obj13 = obj10;
                Object obj14 = obj13;
                try {
                    obj8 = BitwiseOp.and.apply2(mask, AddOp.$Pl.apply2(MultiplyOp.$St.apply2(Lit6, obj9), Scheme.applyToArgs.apply2(frame553.char$Mn$Grint, Char.make(strings.stringRef(charSequence, ((Number) obj13).intValue())))));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "string-ref", 2, obj14);
                    throw th3;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "string-ref", 1, obj12);
                throw th4;
            }
        }
    }

    public static Object stringHash$V(Object s, Object[] argsArray) {
        frame56 frame562;
        Object obj;
        Throwable th;
        Object obj2;
        new frame56();
        frame56 frame563 = frame562;
        frame563.f193s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList maybe$Mnbound$Plstart$Plend = makeList;
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        try {
            Object obj3 = loc$let$Mnoptionals$St.get();
            LList lList2 = maybe$Mnbound$Plstart$Plend;
            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
            ApplyToArgs applyToArgs3 = Scheme.applyToArgs;
            try {
                Object obj4 = loc$bound.get();
                IntNum intNum = Lit7;
                try {
                    boolean x = numbers.isInteger(loc$bound.get());
                    if (x) {
                        try {
                            boolean x2 = numbers.isExact(loc$bound.get());
                            if (x2) {
                                try {
                                    obj = Scheme.numLEq.apply2(Lit0, loc$bound.get());
                                } catch (UnboundLocationException e) {
                                    UnboundLocationException unboundLocationException = e;
                                    UnboundLocationException unboundLocationException2 = unboundLocationException;
                                    unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 909, 19);
                                    throw unboundLocationException2;
                                }
                            } else {
                                obj = x2 ? Boolean.TRUE : Boolean.FALSE;
                            }
                        } catch (UnboundLocationException e2) {
                            UnboundLocationException unboundLocationException3 = e2;
                            UnboundLocationException unboundLocationException4 = unboundLocationException3;
                            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_PERMISSION_DENIED, 21);
                            throw unboundLocationException4;
                        }
                    } else {
                        obj = x ? Boolean.TRUE : Boolean.FALSE;
                    }
                    try {
                        Object apply2 = applyToArgs2.apply2(applyToArgs3.apply3(obj4, intNum, obj), loc$rest.get());
                        try {
                            Object obj5 = loc$bound.get();
                            Object obj6 = obj5;
                            try {
                                if (numbers.isZero((Number) obj5)) {
                                    obj2 = Lit8;
                                } else {
                                    try {
                                        obj2 = loc$bound.get();
                                    } catch (UnboundLocationException e3) {
                                        UnboundLocationException unboundLocationException5 = e3;
                                        UnboundLocationException unboundLocationException6 = unboundLocationException5;
                                        unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 911, 18);
                                        throw unboundLocationException6;
                                    }
                                }
                                frame563.bound = obj2;
                                return applyToArgs.apply4(obj3, lList2, apply2, call_with_values.callWithValues(frame563.lambda$Fn134, frame563.lambda$Fn135));
                            } catch (ClassCastException e4) {
                                ClassCastException classCastException = e4;
                                Throwable th2 = th;
                                new WrongType(classCastException, "zero?", 1, obj6);
                                throw th2;
                            }
                        } catch (UnboundLocationException e5) {
                            UnboundLocationException unboundLocationException7 = e5;
                            UnboundLocationException unboundLocationException8 = unboundLocationException7;
                            unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 911, 29);
                            throw unboundLocationException8;
                        }
                    } catch (UnboundLocationException e6) {
                        UnboundLocationException unboundLocationException9 = e6;
                        UnboundLocationException unboundLocationException10 = unboundLocationException9;
                        unboundLocationException9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 910, 7);
                        throw unboundLocationException10;
                    }
                } catch (UnboundLocationException e7) {
                    UnboundLocationException unboundLocationException11 = e7;
                    UnboundLocationException unboundLocationException12 = unboundLocationException11;
                    unboundLocationException11.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 907, 72);
                    throw unboundLocationException12;
                }
            } catch (UnboundLocationException e8) {
                UnboundLocationException unboundLocationException13 = e8;
                UnboundLocationException unboundLocationException14 = unboundLocationException13;
                unboundLocationException13.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 907, 42);
                throw unboundLocationException14;
            }
        } catch (UnboundLocationException e9) {
            UnboundLocationException unboundLocationException15 = e9;
            UnboundLocationException unboundLocationException16 = unboundLocationException15;
            unboundLocationException15.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 907, 3);
            throw unboundLocationException16;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame56 extends ModuleBody {
        Object bound;
        final ModuleMethod lambda$Fn134;
        final ModuleMethod lambda$Fn135;

        /* renamed from: s */
        Object f193s;

        public frame56() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 112, (Object) null, 0);
            this.lambda$Fn134 = moduleMethod;
            new ModuleMethod(this, 113, (Object) null, 8194);
            this.lambda$Fn135 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 112 ? lambda134() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 113 ? lambda135(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 112) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 113) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda134() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnhash, this.f193s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 912, 55);
                throw unboundLocationException2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda135(Object start, Object end) {
            return srfi13.$PcStringHash(this.f193s, characters.char$Mn$Grinteger, this.bound, start, end);
        }
    }

    public static Object stringHashCi$V(Object s, Object[] argsArray) {
        frame57 frame572;
        Object obj;
        Throwable th;
        Object obj2;
        new frame57();
        frame57 frame573 = frame572;
        frame573.f194s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList maybe$Mnbound$Plstart$Plend = makeList;
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        try {
            Object obj3 = loc$let$Mnoptionals$St.get();
            LList lList2 = maybe$Mnbound$Plstart$Plend;
            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
            ApplyToArgs applyToArgs3 = Scheme.applyToArgs;
            try {
                Object obj4 = loc$bound.get();
                IntNum intNum = Lit9;
                try {
                    boolean x = numbers.isInteger(loc$bound.get());
                    if (x) {
                        try {
                            boolean x2 = numbers.isExact(loc$bound.get());
                            if (x2) {
                                try {
                                    obj = Scheme.numLEq.apply2(Lit0, loc$bound.get());
                                } catch (UnboundLocationException e) {
                                    UnboundLocationException unboundLocationException = e;
                                    UnboundLocationException unboundLocationException2 = unboundLocationException;
                                    unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 918, 19);
                                    throw unboundLocationException2;
                                }
                            } else {
                                obj = x2 ? Boolean.TRUE : Boolean.FALSE;
                            }
                        } catch (UnboundLocationException e2) {
                            UnboundLocationException unboundLocationException3 = e2;
                            UnboundLocationException unboundLocationException4 = unboundLocationException3;
                            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 917, 21);
                            throw unboundLocationException4;
                        }
                    } else {
                        obj = x ? Boolean.TRUE : Boolean.FALSE;
                    }
                    try {
                        Object apply2 = applyToArgs2.apply2(applyToArgs3.apply3(obj4, intNum, obj), loc$rest.get());
                        try {
                            Object obj5 = loc$bound.get();
                            Object obj6 = obj5;
                            try {
                                if (numbers.isZero((Number) obj5)) {
                                    obj2 = Lit10;
                                } else {
                                    try {
                                        obj2 = loc$bound.get();
                                    } catch (UnboundLocationException e3) {
                                        UnboundLocationException unboundLocationException5 = e3;
                                        UnboundLocationException unboundLocationException6 = unboundLocationException5;
                                        unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 920, 18);
                                        throw unboundLocationException6;
                                    }
                                }
                                frame573.bound = obj2;
                                return applyToArgs.apply4(obj3, lList2, apply2, call_with_values.callWithValues(frame573.lambda$Fn136, frame573.lambda$Fn137));
                            } catch (ClassCastException e4) {
                                ClassCastException classCastException = e4;
                                Throwable th2 = th;
                                new WrongType(classCastException, "zero?", 1, obj6);
                                throw th2;
                            }
                        } catch (UnboundLocationException e5) {
                            UnboundLocationException unboundLocationException7 = e5;
                            UnboundLocationException unboundLocationException8 = unboundLocationException7;
                            unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 920, 29);
                            throw unboundLocationException8;
                        }
                    } catch (UnboundLocationException e6) {
                        UnboundLocationException unboundLocationException9 = e6;
                        UnboundLocationException unboundLocationException10 = unboundLocationException9;
                        unboundLocationException9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 919, 7);
                        throw unboundLocationException10;
                    }
                } catch (UnboundLocationException e7) {
                    UnboundLocationException unboundLocationException11 = e7;
                    UnboundLocationException unboundLocationException12 = unboundLocationException11;
                    unboundLocationException11.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 916, 72);
                    throw unboundLocationException12;
                }
            } catch (UnboundLocationException e8) {
                UnboundLocationException unboundLocationException13 = e8;
                UnboundLocationException unboundLocationException14 = unboundLocationException13;
                unboundLocationException13.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 916, 42);
                throw unboundLocationException14;
            }
        } catch (UnboundLocationException e9) {
            UnboundLocationException unboundLocationException15 = e9;
            UnboundLocationException unboundLocationException16 = unboundLocationException15;
            unboundLocationException15.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 916, 3);
            throw unboundLocationException16;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame57 extends ModuleBody {
        Object bound;
        final ModuleMethod lambda$Fn136;
        final ModuleMethod lambda$Fn137;

        /* renamed from: s */
        Object f194s;

        public frame57() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 114, (Object) null, 0);
            this.lambda$Fn136 = moduleMethod;
            new ModuleMethod(this, 115, (Object) null, 8194);
            this.lambda$Fn137 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 114 ? lambda136() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 115 ? lambda137(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 114) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 115) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda136() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnhash$Mnci, this.f194s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 921, 58);
                throw unboundLocationException2;
            }
        }

        static int lambda138(Object c) {
            Throwable th;
            Object obj = c;
            Object obj2 = obj;
            try {
                return characters.char$To$Integer(unicode.charDowncase((Char) obj));
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "char-downcase", 1, obj2);
                throw th2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda137(Object start, Object end) {
            return srfi13.$PcStringHash(this.f194s, srfi13.lambda$Fn138, this.bound, start, end);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame58 extends ModuleBody {
        final ModuleMethod lambda$Fn139;
        final ModuleMethod lambda$Fn140;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f195s;

        public frame58() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 116, (Object) null, 0);
            this.lambda$Fn139 = moduleMethod;
            new ModuleMethod(this, 117, (Object) null, 8194);
            this.lambda$Fn140 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 116 ? lambda139() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 117 ? lambda140(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 116) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 117) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda139() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnupcase, this.f195s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda140(Object start, Object end) {
            return srfi13.$PcStringMap(unicode.char$Mnupcase, this.f195s, start, end);
        }
    }

    public static Object stringUpcase$V(Object s, Object[] argsArray) {
        frame58 frame582;
        new frame58();
        frame58 frame583 = frame582;
        frame583.f195s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame583.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame583.lambda$Fn139, frame583.lambda$Fn140);
    }

    /* compiled from: srfi13.scm */
    public class frame59 extends ModuleBody {
        final ModuleMethod lambda$Fn141;
        final ModuleMethod lambda$Fn142;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f196s;

        public frame59() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 118, (Object) null, 0);
            this.lambda$Fn141 = moduleMethod;
            new ModuleMethod(this, 119, (Object) null, 8194);
            this.lambda$Fn142 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 118 ? lambda141() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 119 ? lambda142(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 118) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 119) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda141() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnupcase$Ex, this.f196s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda142(Object start, Object end) {
            return srfi13.$PcStringMap$Ex(unicode.char$Mnupcase, this.f196s, start, end);
        }
    }

    public static Object stringUpcase$Ex$V(Object s, Object[] argsArray) {
        frame59 frame592;
        new frame59();
        frame59 frame593 = frame592;
        frame593.f196s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame593.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame593.lambda$Fn141, frame593.lambda$Fn142);
    }

    /* compiled from: srfi13.scm */
    public class frame60 extends ModuleBody {
        final ModuleMethod lambda$Fn143;
        final ModuleMethod lambda$Fn144;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f198s;

        public frame60() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 120, (Object) null, 0);
            this.lambda$Fn143 = moduleMethod;
            new ModuleMethod(this, 121, (Object) null, 8194);
            this.lambda$Fn144 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 120 ? lambda143() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 121 ? lambda144(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 120) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 121) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda143() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndowncase, this.f198s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda144(Object start, Object end) {
            return srfi13.$PcStringMap(unicode.char$Mndowncase, this.f198s, start, end);
        }
    }

    public static Object stringDowncase$V(Object s, Object[] argsArray) {
        frame60 frame602;
        new frame60();
        frame60 frame603 = frame602;
        frame603.f198s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame603.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame603.lambda$Fn143, frame603.lambda$Fn144);
    }

    /* compiled from: srfi13.scm */
    public class frame61 extends ModuleBody {
        final ModuleMethod lambda$Fn145;
        final ModuleMethod lambda$Fn146;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f199s;

        public frame61() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 122, (Object) null, 0);
            this.lambda$Fn145 = moduleMethod;
            new ModuleMethod(this, 123, (Object) null, 8194);
            this.lambda$Fn146 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 122 ? lambda145() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 123 ? lambda146(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 122) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 123) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda145() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndowncase$Ex, this.f199s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda146(Object start, Object end) {
            return srfi13.$PcStringMap$Ex(unicode.char$Mndowncase, this.f199s, start, end);
        }
    }

    public static Object stringDowncase$Ex$V(Object s, Object[] argsArray) {
        frame61 frame612;
        new frame61();
        frame61 frame613 = frame612;
        frame613.f199s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame613.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame613.lambda$Fn145, frame613.lambda$Fn146);
    }

    public static Object $PcStringTitlecase$Ex(Object obj, Object start, Object obj2) {
        Object s;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object s2 = obj;
        Object end = obj2;
        Object obj3 = start;
        while (true) {
            Object i = obj3;
            Object obj4 = s2;
            try {
                Object obj5 = loc$char$Mncased$Qu.get();
                Object[] objArr = new Object[2];
                objArr[0] = i;
                Object[] objArr2 = objArr;
                objArr2[1] = end;
                Object temp = stringIndex$V(obj4, obj5, objArr2);
                if (temp == Boolean.FALSE) {
                    s = Values.empty;
                    break;
                }
                Object obj6 = s2;
                Object obj7 = obj6;
                try {
                    CharSeq charSeq = (CharSeq) obj6;
                    Object obj8 = temp;
                    Object obj9 = obj8;
                    try {
                        int intValue = ((Number) obj8).intValue();
                        Object obj10 = s2;
                        Object obj11 = obj10;
                        try {
                            CharSequence charSequence = (CharSequence) obj10;
                            Object obj12 = temp;
                            Object obj13 = obj12;
                            try {
                                Char charTitlecase = unicode.charTitlecase(Char.make(strings.stringRef(charSequence, ((Number) obj12).intValue())));
                                Char charR = charTitlecase;
                                try {
                                    strings.stringSet$Ex(charSeq, intValue, charTitlecase.charValue());
                                    Object i1 = AddOp.$Pl.apply2(temp, Lit1);
                                    Object obj14 = s2;
                                    try {
                                        Object obj15 = loc$char$Mncased$Qu.get();
                                        Object[] objArr3 = new Object[2];
                                        objArr3[0] = i1;
                                        Object[] objArr4 = objArr3;
                                        objArr4[1] = end;
                                        Object temp2 = stringSkip$V(obj14, obj15, objArr4);
                                        if (temp2 == Boolean.FALSE) {
                                            Object[] objArr5 = new Object[2];
                                            objArr5[0] = i1;
                                            Object[] objArr6 = objArr5;
                                            objArr6[1] = end;
                                            s = stringDowncase$Ex$V(s2, objArr6);
                                            break;
                                        }
                                        Object[] objArr7 = new Object[2];
                                        objArr7[0] = i1;
                                        Object[] objArr8 = objArr7;
                                        objArr8[1] = temp2;
                                        Object stringDowncase$Ex$V = stringDowncase$Ex$V(s2, objArr8);
                                        obj3 = AddOp.$Pl.apply2(temp2, Lit1);
                                    } catch (UnboundLocationException e) {
                                        UnboundLocationException unboundLocationException = e;
                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 959, 31);
                                        throw unboundLocationException2;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException = e2;
                                    Throwable th6 = th5;
                                    new WrongType(classCastException, "string-set!", 3, (Object) charR);
                                    throw th6;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException2 = e3;
                                Throwable th7 = th4;
                                new WrongType(classCastException2, "string-ref", 2, obj13);
                                throw th7;
                            }
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException3 = e4;
                            Throwable th8 = th3;
                            new WrongType(classCastException3, "string-ref", 1, obj11);
                            throw th8;
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException4 = e5;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "string-set!", 2, obj9);
                        throw th9;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException5 = e6;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "string-set!", 1, obj7);
                    throw th10;
                }
            } catch (UnboundLocationException e7) {
                UnboundLocationException unboundLocationException3 = e7;
                UnboundLocationException unboundLocationException4 = unboundLocationException3;
                unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 955, 28);
                throw unboundLocationException4;
            }
        }
        return s;
    }

    /* compiled from: srfi13.scm */
    public class frame62 extends ModuleBody {
        final ModuleMethod lambda$Fn147;
        final ModuleMethod lambda$Fn148;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f200s;

        public frame62() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 124, (Object) null, 0);
            this.lambda$Fn147 = moduleMethod;
            new ModuleMethod(this, 125, (Object) null, 8194);
            this.lambda$Fn148 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 124 ? lambda147() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 125 ? lambda148(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 124) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 125) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda147() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, this.f200s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda148(Object start, Object end) {
            return srfi13.$PcStringTitlecase$Ex(this.f200s, start, end);
        }
    }

    public static Object stringTitlecase$Ex$V(Object s, Object[] argsArray) {
        frame62 frame622;
        new frame62();
        frame62 frame623 = frame622;
        frame623.f200s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame623.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame623.lambda$Fn147, frame623.lambda$Fn148);
    }

    /* compiled from: srfi13.scm */
    public class frame63 extends ModuleBody {
        final ModuleMethod lambda$Fn149;
        final ModuleMethod lambda$Fn150;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f201s;

        public frame63() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 126, (Object) null, 0);
            this.lambda$Fn149 = moduleMethod;
            new ModuleMethod(this, 127, (Object) null, 8194);
            this.lambda$Fn150 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 126 ? lambda149() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 127 ? lambda150(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 126) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 127) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda149() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, this.f201s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda150(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object start = obj;
            Object end = obj2;
            Object obj3 = this.f201s;
            Object obj4 = obj3;
            try {
                CharSequence charSequence = (CharSequence) obj3;
                Object obj5 = start;
                Object obj6 = obj5;
                try {
                    int intValue = ((Number) obj5).intValue();
                    Object obj7 = end;
                    Object obj8 = obj7;
                    try {
                        CharSequence ans = strings.substring(charSequence, intValue, ((Number) obj7).intValue());
                        Object $PcStringTitlecase$Ex = srfi13.$PcStringTitlecase$Ex(ans, srfi13.Lit0, AddOp.$Mn.apply2(end, start));
                        return ans;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th4 = th3;
                        new WrongType(classCastException, "substring", 3, obj8);
                        throw th4;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "substring", 2, obj6);
                    throw th5;
                }
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th6 = th;
                new WrongType(classCastException3, "substring", 1, obj4);
                throw th6;
            }
        }
    }

    public static Object stringTitlecase$V(Object s, Object[] argsArray) {
        frame63 frame632;
        new frame63();
        frame63 frame633 = frame632;
        frame633.f201s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame633.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame633.lambda$Fn149, frame633.lambda$Fn150);
    }

    public static Object stringTake(Object s, Object n) {
        frame64 frame642;
        Throwable th;
        Throwable th2;
        new frame64();
        frame64 frame643 = frame642;
        frame643.f203s = s;
        frame643.f202n = n;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), strings.string$Qu, frame643.f203s, string$Mntake);
            try {
                Object apply42 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), frame643.lambda$Fn151, frame643.f202n, string$Mntake);
                Object obj = frame643.f203s;
                Object obj2 = obj;
                try {
                    CharSequence charSequence = (CharSequence) obj;
                    Object obj3 = frame643.f202n;
                    Object obj4 = obj3;
                    try {
                        return $PcSubstring$SlShared(charSequence, 0, ((Number) obj3).intValue());
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "%substring/shared", 2, obj4);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "%substring/shared", 0, obj2);
                    throw th4;
                }
            } catch (UnboundLocationException e3) {
                UnboundLocationException unboundLocationException = e3;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 996, 3);
                throw unboundLocationException2;
            }
        } catch (UnboundLocationException e4) {
            UnboundLocationException unboundLocationException3 = e4;
            UnboundLocationException unboundLocationException4 = unboundLocationException3;
            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 995, 3);
            throw unboundLocationException4;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame64 extends ModuleBody {
        final ModuleMethod lambda$Fn151;

        /* renamed from: n */
        Object f202n;

        /* renamed from: s */
        Object f203s;

        public frame64() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 128, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:996");
            this.lambda$Fn151 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 128) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda151(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda151(Object obj) {
            boolean z;
            Throwable th;
            Object obj2 = obj;
            boolean x = numbers.isInteger(this.f202n);
            if (x) {
                boolean x2 = numbers.isExact(this.f202n);
                if (x2) {
                    NumberCompare numberCompare = Scheme.numLEq;
                    IntNum intNum = srfi13.Lit0;
                    Object obj3 = this.f202n;
                    Object obj4 = this.f203s;
                    Object obj5 = obj4;
                    try {
                        z = ((Boolean) numberCompare.apply3(intNum, obj3, Integer.valueOf(strings.stringLength((CharSequence) obj4)))).booleanValue();
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "string-length", 1, obj5);
                        throw th2;
                    }
                } else {
                    z = x2;
                }
            } else {
                z = x;
            }
            return z;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 128) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object stringTakeRight(Object obj, Object n) {
        frame65 frame652;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Object s = obj;
        new frame65();
        frame65 frame653 = frame652;
        frame653.f204n = n;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), strings.string$Qu, s, string$Mntake$Mnright);
            Object obj2 = s;
            Object obj3 = obj2;
            try {
                frame653.len = strings.stringLength((CharSequence) obj2);
                try {
                    Object apply42 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), frame653.lambda$Fn152, frame653.f204n, string$Mntake$Mnright);
                    Object obj4 = s;
                    Object obj5 = obj4;
                    try {
                        CharSequence charSequence = (CharSequence) obj4;
                        Object apply2 = AddOp.$Mn.apply2(Integer.valueOf(frame653.len), frame653.f204n);
                        Object obj6 = apply2;
                        try {
                            return $PcSubstring$SlShared(charSequence, ((Number) apply2).intValue(), frame653.len);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th4 = th3;
                            new WrongType(classCastException, "%substring/shared", 1, obj6);
                            throw th4;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th2;
                        new WrongType(classCastException2, "%substring/shared", 0, obj5);
                        throw th5;
                    }
                } catch (UnboundLocationException e3) {
                    UnboundLocationException unboundLocationException = e3;
                    UnboundLocationException unboundLocationException2 = unboundLocationException;
                    unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1004, 5);
                    throw unboundLocationException2;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException3 = e4;
                Throwable th6 = th;
                new WrongType(classCastException3, "string-length", 1, obj3);
                throw th6;
            }
        } catch (UnboundLocationException e5) {
            UnboundLocationException unboundLocationException3 = e5;
            UnboundLocationException unboundLocationException4 = unboundLocationException3;
            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1002, 3);
            throw unboundLocationException4;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame65 extends ModuleBody {
        final ModuleMethod lambda$Fn152;
        int len;

        /* renamed from: n */
        Object f204n;

        public frame65() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 129, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1004");
            this.lambda$Fn152 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 129) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda152(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda152(Object obj) {
            boolean z;
            Object obj2 = obj;
            boolean x = numbers.isInteger(this.f204n);
            if (x) {
                boolean x2 = numbers.isExact(this.f204n);
                z = x2 ? ((Boolean) Scheme.numLEq.apply3(srfi13.Lit0, this.f204n, Integer.valueOf(this.len))).booleanValue() : x2;
            } else {
                z = x;
            }
            return z;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 129) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object stringDrop(CharSequence charSequence, Object n) {
        frame66 frame662;
        Throwable th;
        CharSequence s = charSequence;
        new frame66();
        frame66 frame663 = frame662;
        frame663.f205n = n;
        frame663.len = strings.stringLength(s);
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), frame663.lambda$Fn153, frame663.f205n, string$Mndrop);
            CharSequence charSequence2 = s;
            Object obj = frame663.f205n;
            Object obj2 = obj;
            try {
                return $PcSubstring$SlShared(charSequence2, ((Number) obj).intValue(), frame663.len);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "%substring/shared", 1, obj2);
                throw th2;
            }
        } catch (UnboundLocationException e2) {
            UnboundLocationException unboundLocationException = e2;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", PointerIconCompat.TYPE_ALIAS, 5);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame66 extends ModuleBody {
        final ModuleMethod lambda$Fn153;
        int len;

        /* renamed from: n */
        Object f205n;

        public frame66() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 130, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1010");
            this.lambda$Fn153 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 130) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda153(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda153(Object obj) {
            boolean z;
            Object obj2 = obj;
            boolean x = numbers.isInteger(this.f205n);
            if (x) {
                boolean x2 = numbers.isExact(this.f205n);
                z = x2 ? ((Boolean) Scheme.numLEq.apply3(srfi13.Lit0, this.f205n, Integer.valueOf(this.len))).booleanValue() : x2;
            } else {
                z = x;
            }
            return z;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 130) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object stringDropRight(CharSequence charSequence, Object n) {
        frame67 frame672;
        Throwable th;
        CharSequence s = charSequence;
        new frame67();
        frame67 frame673 = frame672;
        frame673.f206n = n;
        frame673.len = strings.stringLength(s);
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), frame673.lambda$Fn154, frame673.f206n, string$Mndrop$Mnright);
            CharSequence charSequence2 = s;
            Object apply2 = AddOp.$Mn.apply2(Integer.valueOf(frame673.len), frame673.f206n);
            Object obj = apply2;
            try {
                return $PcSubstring$SlShared(charSequence2, 0, ((Number) apply2).intValue());
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "%substring/shared", 2, obj);
                throw th2;
            }
        } catch (UnboundLocationException e2) {
            UnboundLocationException unboundLocationException = e2;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, 5);
            throw unboundLocationException2;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 218:
                try {
                    return stringTabulate(obj3, ((Number) obj4).intValue());
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "string-tabulate", 2, obj4);
                    throw th4;
                }
            case 280:
                return stringTake(obj3, obj4);
            case 281:
                return stringTakeRight(obj3, obj4);
            case 282:
                try {
                    return stringDrop((CharSequence) obj3, obj4);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "string-drop", 1, obj3);
                    throw th5;
                }
            case 283:
                try {
                    return stringDropRight((CharSequence) obj3, obj4);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "string-drop-right", 1, obj3);
                    throw th6;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame67 extends ModuleBody {
        final ModuleMethod lambda$Fn154;
        int len;

        /* renamed from: n */
        Object f206n;

        public frame67() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 131, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1016");
            this.lambda$Fn154 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 131) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda154(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda154(Object obj) {
            boolean z;
            Object obj2 = obj;
            boolean x = numbers.isInteger(this.f206n);
            if (x) {
                boolean x2 = numbers.isExact(this.f206n);
                z = x2 ? ((Boolean) Scheme.numLEq.apply3(srfi13.Lit0, this.f206n, Integer.valueOf(this.len))).booleanValue() : x2;
            } else {
                z = x;
            }
            return z;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 131) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object stringTrim$V(Object s, Object[] argsArray) {
        frame68 frame682;
        new frame68();
        frame68 frame683 = frame682;
        frame683.f207s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList criterion$Plstart$Plend = makeList;
        try {
            try {
                try {
                    try {
                        return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), criterion$Plstart$Plend, Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply2(loc$criterion.get(), GetNamedPart.getNamedPart.apply2(loc$char$Mnset.get(), Lit11)), loc$rest.get()), call_with_values.callWithValues(frame683.lambda$Fn155, frame683.lambda$Fn156));
                    } catch (UnboundLocationException e) {
                        UnboundLocationException unboundLocationException = e;
                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 72);
                        throw unboundLocationException2;
                    }
                } catch (UnboundLocationException e2) {
                    UnboundLocationException unboundLocationException3 = e2;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 51);
                    throw unboundLocationException4;
                }
            } catch (UnboundLocationException e3) {
                UnboundLocationException unboundLocationException5 = e3;
                UnboundLocationException unboundLocationException6 = unboundLocationException5;
                unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 40);
                throw unboundLocationException6;
            }
        } catch (UnboundLocationException e4) {
            UnboundLocationException unboundLocationException7 = e4;
            UnboundLocationException unboundLocationException8 = unboundLocationException7;
            unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 3);
            throw unboundLocationException8;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame68 extends ModuleBody {
        final ModuleMethod lambda$Fn155;
        final ModuleMethod lambda$Fn156;

        /* renamed from: s */
        Object f207s;

        public frame68() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 132, (Object) null, 0);
            this.lambda$Fn155 = moduleMethod;
            new ModuleMethod(this, 133, (Object) null, 8194);
            this.lambda$Fn156 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 132 ? lambda155() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 133 ? lambda156(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 132) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 133) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda155() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntrim, this.f207s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1023, 53);
                throw unboundLocationException2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda156(Object obj, Object obj2) {
            Object obj3;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object start = obj;
            Object end = obj2;
            Object obj4 = this.f207s;
            try {
                Object obj5 = srfi13.loc$criterion.get();
                Object[] objArr = new Object[2];
                objArr[0] = start;
                Object[] objArr2 = objArr;
                objArr2[1] = end;
                Object temp = srfi13.stringSkip$V(obj4, obj5, objArr2);
                if (temp != Boolean.FALSE) {
                    Object obj6 = this.f207s;
                    Object obj7 = obj6;
                    try {
                        CharSequence charSequence = (CharSequence) obj6;
                        Object obj8 = temp;
                        Object obj9 = obj8;
                        try {
                            int intValue = ((Number) obj8).intValue();
                            Object obj10 = end;
                            Object obj11 = obj10;
                            try {
                                obj3 = srfi13.$PcSubstring$SlShared(charSequence, intValue, ((Number) obj10).intValue());
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th4 = th3;
                                new WrongType(classCastException, "%substring/shared", 2, obj11);
                                throw th4;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th5 = th2;
                            new WrongType(classCastException2, "%substring/shared", 1, obj9);
                            throw th5;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th6 = th;
                        new WrongType(classCastException3, "%substring/shared", 0, obj7);
                        throw th6;
                    }
                } else {
                    obj3 = "";
                }
                return obj3;
            } catch (UnboundLocationException e4) {
                UnboundLocationException unboundLocationException = e4;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1024, 29);
                throw unboundLocationException2;
            }
        }
    }

    public static Object stringTrimRight$V(Object s, Object[] argsArray) {
        frame69 frame692;
        new frame69();
        frame69 frame693 = frame692;
        frame693.f208s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList criterion$Plstart$Plend = makeList;
        try {
            try {
                try {
                    try {
                        return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), criterion$Plstart$Plend, Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply2(loc$criterion.get(), GetNamedPart.getNamedPart.apply2(loc$char$Mnset.get(), Lit11)), loc$rest.get()), call_with_values.callWithValues(frame693.lambda$Fn157, frame693.lambda$Fn158));
                    } catch (UnboundLocationException e) {
                        UnboundLocationException unboundLocationException = e;
                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 72);
                        throw unboundLocationException2;
                    }
                } catch (UnboundLocationException e2) {
                    UnboundLocationException unboundLocationException3 = e2;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 51);
                    throw unboundLocationException4;
                }
            } catch (UnboundLocationException e3) {
                UnboundLocationException unboundLocationException5 = e3;
                UnboundLocationException unboundLocationException6 = unboundLocationException5;
                unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 40);
                throw unboundLocationException6;
            }
        } catch (UnboundLocationException e4) {
            UnboundLocationException unboundLocationException7 = e4;
            UnboundLocationException unboundLocationException8 = unboundLocationException7;
            unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 3);
            throw unboundLocationException8;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame69 extends ModuleBody {
        final ModuleMethod lambda$Fn157;
        final ModuleMethod lambda$Fn158;

        /* renamed from: s */
        Object f208s;

        public frame69() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 134, (Object) null, 0);
            this.lambda$Fn157 = moduleMethod;
            new ModuleMethod(this, 135, (Object) null, 8194);
            this.lambda$Fn158 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 134 ? lambda157() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 135 ? lambda158(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 134) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 135) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda157() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntrim$Mnright, this.f208s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1030, 59);
                throw unboundLocationException2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda158(Object obj, Object obj2) {
            Object obj3;
            Throwable th;
            Throwable th2;
            Object start = obj;
            Object end = obj2;
            Object obj4 = this.f208s;
            try {
                Object obj5 = srfi13.loc$criterion.get();
                Object[] objArr = new Object[2];
                objArr[0] = start;
                Object[] objArr2 = objArr;
                objArr2[1] = end;
                Object temp = srfi13.stringSkipRight$V(obj4, obj5, objArr2);
                if (temp != Boolean.FALSE) {
                    Object obj6 = this.f208s;
                    Object obj7 = obj6;
                    try {
                        CharSequence charSequence = (CharSequence) obj6;
                        Object apply2 = AddOp.$Pl.apply2(srfi13.Lit1, temp);
                        Object obj8 = apply2;
                        try {
                            obj3 = srfi13.$PcSubstring$SlShared(charSequence, 0, ((Number) apply2).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th3 = th2;
                            new WrongType(classCastException, "%substring/shared", 2, obj8);
                            throw th3;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th4 = th;
                        new WrongType(classCastException2, "%substring/shared", 0, obj7);
                        throw th4;
                    }
                } else {
                    obj3 = "";
                }
                return obj3;
            } catch (UnboundLocationException e3) {
                UnboundLocationException unboundLocationException = e3;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1031, 35);
                throw unboundLocationException2;
            }
        }
    }

    public static Object stringTrimBoth$V(Object s, Object[] argsArray) {
        frame70 frame702;
        new frame70();
        frame70 frame703 = frame702;
        frame703.f210s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList criterion$Plstart$Plend = makeList;
        try {
            try {
                try {
                    try {
                        return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), criterion$Plstart$Plend, Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply2(loc$criterion.get(), GetNamedPart.getNamedPart.apply2(loc$char$Mnset.get(), Lit11)), loc$rest.get()), call_with_values.callWithValues(frame703.lambda$Fn159, frame703.lambda$Fn160));
                    } catch (UnboundLocationException e) {
                        UnboundLocationException unboundLocationException = e;
                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 72);
                        throw unboundLocationException2;
                    }
                } catch (UnboundLocationException e2) {
                    UnboundLocationException unboundLocationException3 = e2;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 51);
                    throw unboundLocationException4;
                }
            } catch (UnboundLocationException e3) {
                UnboundLocationException unboundLocationException5 = e3;
                UnboundLocationException unboundLocationException6 = unboundLocationException5;
                unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 40);
                throw unboundLocationException6;
            }
        } catch (UnboundLocationException e4) {
            UnboundLocationException unboundLocationException7 = e4;
            UnboundLocationException unboundLocationException8 = unboundLocationException7;
            unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 3);
            throw unboundLocationException8;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame70 extends ModuleBody {
        final ModuleMethod lambda$Fn159;
        final ModuleMethod lambda$Fn160;

        /* renamed from: s */
        Object f210s;

        public frame70() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 136, (Object) null, 0);
            this.lambda$Fn159 = moduleMethod;
            new ModuleMethod(this, 137, (Object) null, 8194);
            this.lambda$Fn160 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 136 ? lambda159() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 137 ? lambda160(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 136) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 137) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda159() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntrim$Mnboth, this.f210s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1037, 58);
                throw unboundLocationException2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda160(Object obj, Object obj2) {
            Object obj3;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object start = obj;
            Object end = obj2;
            Object obj4 = this.f210s;
            try {
                Object obj5 = srfi13.loc$criterion.get();
                Object[] objArr = new Object[2];
                objArr[0] = start;
                Object[] objArr2 = objArr;
                objArr2[1] = end;
                Object temp = srfi13.stringSkip$V(obj4, obj5, objArr2);
                if (temp != Boolean.FALSE) {
                    Object obj6 = this.f210s;
                    Object obj7 = obj6;
                    try {
                        CharSequence charSequence = (CharSequence) obj6;
                        Object obj8 = temp;
                        Object obj9 = obj8;
                        try {
                            int intValue = ((Number) obj8).intValue();
                            AddOp addOp = AddOp.$Pl;
                            IntNum intNum = srfi13.Lit1;
                            Object obj10 = this.f210s;
                            try {
                                Object obj11 = srfi13.loc$criterion.get();
                                Object[] objArr3 = new Object[2];
                                objArr3[0] = temp;
                                Object[] objArr4 = objArr3;
                                objArr4[1] = end;
                                Object apply2 = addOp.apply2(intNum, srfi13.stringSkipRight$V(obj10, obj11, objArr4));
                                Object obj12 = apply2;
                                try {
                                    obj3 = srfi13.$PcSubstring$SlShared(charSequence, intValue, ((Number) apply2).intValue());
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th4 = th3;
                                    new WrongType(classCastException, "%substring/shared", 2, obj12);
                                    throw th4;
                                }
                            } catch (UnboundLocationException e2) {
                                UnboundLocationException unboundLocationException = e2;
                                UnboundLocationException unboundLocationException2 = unboundLocationException;
                                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1040, 58);
                                throw unboundLocationException2;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException2 = e3;
                            Throwable th5 = th2;
                            new WrongType(classCastException2, "%substring/shared", 1, obj9);
                            throw th5;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException3 = e4;
                        Throwable th6 = th;
                        new WrongType(classCastException3, "%substring/shared", 0, obj7);
                        throw th6;
                    }
                } else {
                    obj3 = "";
                }
                return obj3;
            } catch (UnboundLocationException e5) {
                UnboundLocationException unboundLocationException3 = e5;
                UnboundLocationException unboundLocationException4 = unboundLocationException3;
                unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1038, 29);
                throw unboundLocationException4;
            }
        }
    }

    public static Object stringPadRight$V(Object s, Object n, Object[] argsArray) {
        frame71 frame712;
        new frame71();
        frame71 frame713 = frame712;
        frame713.f212s = s;
        frame713.f211n = n;
        LList char$Plstart$Plend = LList.makeList(argsArray, 0);
        LList lList = char$Plstart$Plend;
        try {
            try {
                return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), char$Plstart$Plend, Scheme.applyToArgs.apply2(Invoke.make.apply3(LangPrimType.charType, Lit12, characters.isChar(LangPrimType.charType) ? Boolean.TRUE : Boolean.FALSE), loc$rest.get()), call_with_values.callWithValues(frame713.lambda$Fn161, frame713.lambda$Fn162));
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1045, 63);
                throw unboundLocationException2;
            }
        } catch (UnboundLocationException e2) {
            UnboundLocationException unboundLocationException3 = e2;
            UnboundLocationException unboundLocationException4 = unboundLocationException3;
            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1045, 3);
            throw unboundLocationException4;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame71 extends ModuleBody {
        final ModuleMethod lambda$Fn161;
        final ModuleMethod lambda$Fn162;

        /* renamed from: n */
        Object f211n;

        /* renamed from: s */
        Object f212s;

        public frame71() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 138, (Object) null, 0);
            this.lambda$Fn161 = moduleMethod;
            new ModuleMethod(this, 139, (Object) null, 8194);
            this.lambda$Fn162 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 138 ? lambda161() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 139 ? lambda162(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 138) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 139) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda161() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnpad$Mnright, this.f212s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1046, 58);
                throw unboundLocationException2;
            }
        }

        static boolean lambda163(Object obj) {
            boolean z;
            Object n = obj;
            boolean x = numbers.isInteger(n);
            if (x) {
                boolean x2 = numbers.isExact(n);
                z = x2 ? ((Boolean) Scheme.numLEq.apply2(srfi13.Lit0, n)).booleanValue() : x2;
            } else {
                z = x;
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public Object lambda162(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object obj3;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Object start = obj;
            Object end = obj2;
            try {
                Object apply4 = Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), srfi13.lambda$Fn163, this.f211n, srfi13.string$Mnpad$Mnright);
                if (Scheme.numLEq.apply2(this.f211n, AddOp.$Mn.apply2(end, start)) != Boolean.FALSE) {
                    Object obj4 = this.f212s;
                    Object obj5 = obj4;
                    try {
                        CharSequence charSequence = (CharSequence) obj4;
                        Object obj6 = start;
                        Object obj7 = obj6;
                        try {
                            int intValue = ((Number) obj6).intValue();
                            Object apply2 = AddOp.$Pl.apply2(start, this.f211n);
                            Object obj8 = apply2;
                            try {
                                obj3 = srfi13.$PcSubstring$SlShared(charSequence, intValue, ((Number) apply2).intValue());
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th8 = th7;
                                new WrongType(classCastException, "%substring/shared", 2, obj8);
                                throw th8;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th9 = th6;
                            new WrongType(classCastException2, "%substring/shared", 1, obj7);
                            throw th9;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th10 = th5;
                        new WrongType(classCastException3, "%substring/shared", 0, obj5);
                        throw th10;
                    }
                } else {
                    Object obj9 = this.f211n;
                    Object obj10 = obj9;
                    try {
                        CharSequence ans = strings.makeString(((Number) obj9).intValue(), LangPrimType.charType);
                        CharSequence charSequence2 = ans;
                        Object obj11 = this.f212s;
                        Object obj12 = obj11;
                        try {
                            CharSequence charSequence3 = (CharSequence) obj11;
                            Object obj13 = start;
                            Object obj14 = obj13;
                            try {
                                int intValue2 = ((Number) obj13).intValue();
                                Object obj15 = end;
                                Object obj16 = obj15;
                                try {
                                    Object $PcStringCopy$Ex = srfi13.$PcStringCopy$Ex(charSequence2, 0, charSequence3, intValue2, ((Number) obj15).intValue());
                                    obj3 = ans;
                                } catch (ClassCastException e4) {
                                    ClassCastException classCastException4 = e4;
                                    Throwable th11 = th4;
                                    new WrongType(classCastException4, "%string-copy!", 4, obj16);
                                    throw th11;
                                }
                            } catch (ClassCastException e5) {
                                ClassCastException classCastException5 = e5;
                                Throwable th12 = th3;
                                new WrongType(classCastException5, "%string-copy!", 3, obj14);
                                throw th12;
                            }
                        } catch (ClassCastException e6) {
                            ClassCastException classCastException6 = e6;
                            Throwable th13 = th2;
                            new WrongType(classCastException6, "%string-copy!", 2, obj12);
                            throw th13;
                        }
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th14 = th;
                        new WrongType(classCastException7, "make-string", 1, obj10);
                        throw th14;
                    }
                }
                return obj3;
            } catch (UnboundLocationException e8) {
                UnboundLocationException unboundLocationException = e8;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1047, 7);
                throw unboundLocationException2;
            }
        }
    }

    public static Object stringPad$V(Object s, Object n, Object[] argsArray) {
        frame72 frame722;
        new frame72();
        frame72 frame723 = frame722;
        frame723.f214s = s;
        frame723.f213n = n;
        LList char$Plstart$Plend = LList.makeList(argsArray, 0);
        LList lList = char$Plstart$Plend;
        try {
            try {
                return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), char$Plstart$Plend, Scheme.applyToArgs.apply2(Invoke.make.apply3(LangPrimType.charType, Lit12, characters.isChar(LangPrimType.charType) ? Boolean.TRUE : Boolean.FALSE), loc$rest.get()), call_with_values.callWithValues(frame723.lambda$Fn164, frame723.lambda$Fn165));
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1057, 63);
                throw unboundLocationException2;
            }
        } catch (UnboundLocationException e2) {
            UnboundLocationException unboundLocationException3 = e2;
            UnboundLocationException unboundLocationException4 = unboundLocationException3;
            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1057, 3);
            throw unboundLocationException4;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame72 extends ModuleBody {
        final ModuleMethod lambda$Fn164;
        final ModuleMethod lambda$Fn165;

        /* renamed from: n */
        Object f213n;

        /* renamed from: s */
        Object f214s;

        public frame72() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 140, (Object) null, 0);
            this.lambda$Fn164 = moduleMethod;
            new ModuleMethod(this, 141, (Object) null, 8194);
            this.lambda$Fn165 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 140 ? lambda164() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 141 ? lambda165(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 140) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 141) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda164() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnpad, this.f214s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1058, 52);
                throw unboundLocationException2;
            }
        }

        static boolean lambda166(Object obj) {
            boolean z;
            Object n = obj;
            boolean x = numbers.isInteger(n);
            if (x) {
                boolean x2 = numbers.isExact(n);
                z = x2 ? ((Boolean) Scheme.numLEq.apply2(srfi13.Lit0, n)).booleanValue() : x2;
            } else {
                z = x;
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public Object lambda165(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Object obj3;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Object start = obj;
            Object end = obj2;
            try {
                Object apply4 = Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), srfi13.lambda$Fn166, this.f213n, srfi13.string$Mnpad);
                Object len = AddOp.$Mn.apply2(end, start);
                if (Scheme.numLEq.apply2(this.f213n, len) != Boolean.FALSE) {
                    Object obj4 = this.f214s;
                    Object obj5 = obj4;
                    try {
                        CharSequence charSequence = (CharSequence) obj4;
                        Object apply2 = AddOp.$Mn.apply2(end, this.f213n);
                        Object obj6 = apply2;
                        try {
                            int intValue = ((Number) apply2).intValue();
                            Object obj7 = end;
                            Object obj8 = obj7;
                            try {
                                obj3 = srfi13.$PcSubstring$SlShared(charSequence, intValue, ((Number) obj7).intValue());
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th9 = th8;
                                new WrongType(classCastException, "%substring/shared", 2, obj8);
                                throw th9;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th10 = th7;
                            new WrongType(classCastException2, "%substring/shared", 1, obj6);
                            throw th10;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th11 = th6;
                        new WrongType(classCastException3, "%substring/shared", 0, obj5);
                        throw th11;
                    }
                } else {
                    Object obj9 = this.f213n;
                    Object obj10 = obj9;
                    try {
                        CharSequence ans = strings.makeString(((Number) obj9).intValue(), LangPrimType.charType);
                        CharSequence charSequence2 = ans;
                        Object apply22 = AddOp.$Mn.apply2(this.f213n, len);
                        Object obj11 = apply22;
                        try {
                            int intValue2 = ((Number) apply22).intValue();
                            Object obj12 = this.f214s;
                            Object obj13 = obj12;
                            try {
                                CharSequence charSequence3 = (CharSequence) obj12;
                                Object obj14 = start;
                                Object obj15 = obj14;
                                try {
                                    int intValue3 = ((Number) obj14).intValue();
                                    Object obj16 = end;
                                    Object obj17 = obj16;
                                    try {
                                        Object $PcStringCopy$Ex = srfi13.$PcStringCopy$Ex(charSequence2, intValue2, charSequence3, intValue3, ((Number) obj16).intValue());
                                        obj3 = ans;
                                    } catch (ClassCastException e4) {
                                        ClassCastException classCastException4 = e4;
                                        Throwable th12 = th5;
                                        new WrongType(classCastException4, "%string-copy!", 4, obj17);
                                        throw th12;
                                    }
                                } catch (ClassCastException e5) {
                                    ClassCastException classCastException5 = e5;
                                    Throwable th13 = th4;
                                    new WrongType(classCastException5, "%string-copy!", 3, obj15);
                                    throw th13;
                                }
                            } catch (ClassCastException e6) {
                                ClassCastException classCastException6 = e6;
                                Throwable th14 = th3;
                                new WrongType(classCastException6, "%string-copy!", 2, obj13);
                                throw th14;
                            }
                        } catch (ClassCastException e7) {
                            ClassCastException classCastException7 = e7;
                            Throwable th15 = th2;
                            new WrongType(classCastException7, "%string-copy!", 1, obj11);
                            throw th15;
                        }
                    } catch (ClassCastException e8) {
                        ClassCastException classCastException8 = e8;
                        Throwable th16 = th;
                        new WrongType(classCastException8, "make-string", 1, obj10);
                        throw th16;
                    }
                }
                return obj3;
            } catch (UnboundLocationException e9) {
                UnboundLocationException unboundLocationException = e9;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1059, 7);
                throw unboundLocationException2;
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame73 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn167;
        final ModuleMethod lambda$Fn168;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f215s;

        public frame73() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 145, (Object) null, 0);
            this.lambda$Fn167 = moduleMethod;
            new ModuleMethod(this, 146, (Object) null, 8194);
            this.lambda$Fn168 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 145 ? lambda167() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 146 ? lambda168(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 145) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 146) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda167() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndelete, this.f215s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda168(Object obj, Object obj2) {
            frame74 frame74;
            Object error$V;
            Throwable th;
            CharSequence charSequence;
            Throwable th2;
            Throwable th3;
            Object start = obj;
            Object end = obj2;
            new frame74();
            frame74 frame742 = frame74;
            frame742.staticLink = this;
            frame74 frame743 = frame742;
            if (misc.isProcedure(this.criterion)) {
                Object slen = AddOp.$Mn.apply2(end, start);
                Object obj3 = slen;
                Object obj4 = obj3;
                try {
                    frame743.temp = strings.makeString(((Number) obj3).intValue());
                    ModuleMethod moduleMethod = frame743.lambda$Fn169;
                    IntNum intNum = srfi13.Lit0;
                    Object obj5 = this.f215s;
                    Object[] objArr = new Object[2];
                    objArr[0] = start;
                    Object[] objArr2 = objArr;
                    objArr2[1] = end;
                    Object ans$Mnlen = srfi13.stringFold$V(moduleMethod, intNum, obj5, objArr2);
                    if (Scheme.numEqu.apply2(ans$Mnlen, slen) != Boolean.FALSE) {
                        charSequence = frame743.temp;
                    } else {
                        Object obj6 = ans$Mnlen;
                        Object obj7 = obj6;
                        try {
                            charSequence = strings.substring(frame743.temp, 0, ((Number) obj6).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th4 = th3;
                            new WrongType(classCastException, "substring", 3, obj7);
                            throw th4;
                        }
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "make-string", 1, obj4);
                    throw th5;
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        error$V = this.criterion;
                    } else if (characters.isChar(this.criterion)) {
                        try {
                            error$V = Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset.get(), this.criterion);
                        } catch (UnboundLocationException e3) {
                            UnboundLocationException unboundLocationException = e3;
                            UnboundLocationException unboundLocationException2 = unboundLocationException;
                            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1097, 26);
                            throw unboundLocationException2;
                        }
                    } else {
                        error$V = misc.error$V("string-delete criterion not predicate, char or char-set", new Object[]{this.criterion});
                    }
                    frame743.cset = error$V;
                    ModuleMethod moduleMethod2 = frame743.lambda$Fn170;
                    IntNum intNum2 = srfi13.Lit0;
                    Object obj8 = this.f215s;
                    Object[] objArr3 = new Object[2];
                    objArr3[0] = start;
                    Object[] objArr4 = objArr3;
                    objArr4[1] = end;
                    Object stringFold$V = srfi13.stringFold$V(moduleMethod2, intNum2, obj8, objArr4);
                    Object obj9 = stringFold$V;
                    try {
                        frame743.ans = strings.makeString(((Number) stringFold$V).intValue());
                        ModuleMethod moduleMethod3 = frame743.lambda$Fn171;
                        IntNum intNum3 = srfi13.Lit0;
                        Object obj10 = this.f215s;
                        Object[] objArr5 = new Object[2];
                        objArr5[0] = start;
                        Object[] objArr6 = objArr5;
                        objArr6[1] = end;
                        Object stringFold$V2 = srfi13.stringFold$V(moduleMethod3, intNum3, obj10, objArr6);
                        charSequence = frame743.ans;
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException3 = e4;
                        Throwable th6 = th;
                        new WrongType(classCastException3, "make-string", 1, obj9);
                        throw th6;
                    }
                } catch (UnboundLocationException e5) {
                    UnboundLocationException unboundLocationException3 = e5;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1096, 22);
                    throw unboundLocationException4;
                }
            }
            return charSequence;
        }
    }

    public static Object stringDelete$V(Object criterion, Object s, Object[] argsArray) {
        frame73 frame732;
        new frame73();
        frame73 frame733 = frame732;
        frame733.criterion = criterion;
        frame733.f215s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame733.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame733.lambda$Fn167, frame733.lambda$Fn168);
    }

    /* compiled from: srfi13.scm */
    public class frame74 extends ModuleBody {
        CharSequence ans;
        Object cset;
        final ModuleMethod lambda$Fn169;
        final ModuleMethod lambda$Fn170;
        final ModuleMethod lambda$Fn171;
        frame73 staticLink;
        CharSequence temp;

        public frame74() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            new ModuleMethod(this, 142, (Object) null, 8194);
            ModuleMethod moduleMethod4 = moduleMethod;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1089");
            this.lambda$Fn169 = moduleMethod4;
            new ModuleMethod(this, 143, (Object) null, 8194);
            ModuleMethod moduleMethod5 = moduleMethod2;
            moduleMethod5.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1099");
            this.lambda$Fn170 = moduleMethod5;
            new ModuleMethod(this, 144, (Object) null, 8194);
            ModuleMethod moduleMethod6 = moduleMethod3;
            moduleMethod6.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1104");
            this.lambda$Fn171 = moduleMethod6;
        }

        /* access modifiers changed from: package-private */
        public Object lambda169(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object apply2;
            Object c = obj;
            Object i = obj2;
            if (Scheme.applyToArgs.apply2(this.staticLink.criterion, c) != Boolean.FALSE) {
                apply2 = i;
            } else {
                CharSequence charSequence = this.temp;
                CharSequence charSequence2 = charSequence;
                try {
                    CharSeq charSeq = (CharSeq) charSequence;
                    Object obj3 = i;
                    Object obj4 = obj3;
                    try {
                        int intValue = ((Number) obj3).intValue();
                        Object obj5 = c;
                        Object obj6 = obj5;
                        try {
                            strings.stringSet$Ex(charSeq, intValue, ((Char) obj5).charValue());
                            apply2 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th4 = th3;
                            new WrongType(classCastException, "string-set!", 3, obj6);
                            throw th4;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th2;
                        new WrongType(classCastException2, "string-set!", 2, obj4);
                        throw th5;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "string-set!", 1, (Object) charSequence2);
                    throw th6;
                }
            }
            return apply2;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 142:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 143:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 144:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda170(Object obj, Object obj2) {
            Object apply2;
            Object c = obj;
            Object i = obj2;
            try {
                if (Scheme.applyToArgs.apply3(srfi13.loc$char$Mnset$Mncontains$Qu.get(), this.cset, c) != Boolean.FALSE) {
                    apply2 = i;
                } else {
                    apply2 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                }
                return apply2;
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1099, 45);
                throw unboundLocationException2;
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            switch (moduleMethod2.selector) {
                case 142:
                    return lambda169(obj3, obj4);
                case 143:
                    return lambda170(obj3, obj4);
                case 144:
                    return lambda171(obj3, obj4);
                default:
                    return super.apply2(moduleMethod2, obj3, obj4);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda171(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object apply2;
            Object c = obj;
            Object i = obj2;
            try {
                if (Scheme.applyToArgs.apply3(srfi13.loc$char$Mnset$Mncontains$Qu.get(), this.cset, c) != Boolean.FALSE) {
                    apply2 = i;
                } else {
                    CharSequence charSequence = this.ans;
                    CharSequence charSequence2 = charSequence;
                    try {
                        CharSeq charSeq = (CharSeq) charSequence;
                        Object obj3 = i;
                        Object obj4 = obj3;
                        try {
                            int intValue = ((Number) obj3).intValue();
                            Object obj5 = c;
                            Object obj6 = obj5;
                            try {
                                strings.stringSet$Ex(charSeq, intValue, ((Char) obj5).charValue());
                                apply2 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th4 = th3;
                                new WrongType(classCastException, "string-set!", 3, obj6);
                                throw th4;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th5 = th2;
                            new WrongType(classCastException2, "string-set!", 2, obj4);
                            throw th5;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th6 = th;
                        new WrongType(classCastException3, "string-set!", 1, (Object) charSequence2);
                        throw th6;
                    }
                }
                return apply2;
            } catch (UnboundLocationException e4) {
                UnboundLocationException unboundLocationException = e4;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_WEB_UNABLE_TO_POST_OR_PUT_FILE, 35);
                throw unboundLocationException2;
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame75 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn172;
        final ModuleMethod lambda$Fn173;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f216s;

        public frame75() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 150, (Object) null, 0);
            this.lambda$Fn172 = moduleMethod;
            new ModuleMethod(this, 151, (Object) null, 8194);
            this.lambda$Fn173 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 150 ? lambda172() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 151 ? lambda173(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 150) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 151) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda172() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfilter, this.f216s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda173(Object obj, Object obj2) {
            frame76 frame76;
            Object error$V;
            Throwable th;
            CharSequence charSequence;
            Throwable th2;
            Throwable th3;
            Object start = obj;
            Object end = obj2;
            new frame76();
            frame76 frame762 = frame76;
            frame762.staticLink = this;
            frame76 frame763 = frame762;
            if (misc.isProcedure(this.criterion)) {
                Object slen = AddOp.$Mn.apply2(end, start);
                Object obj3 = slen;
                Object obj4 = obj3;
                try {
                    frame763.temp = strings.makeString(((Number) obj3).intValue());
                    ModuleMethod moduleMethod = frame763.lambda$Fn174;
                    IntNum intNum = srfi13.Lit0;
                    Object obj5 = this.f216s;
                    Object[] objArr = new Object[2];
                    objArr[0] = start;
                    Object[] objArr2 = objArr;
                    objArr2[1] = end;
                    Object ans$Mnlen = srfi13.stringFold$V(moduleMethod, intNum, obj5, objArr2);
                    if (Scheme.numEqu.apply2(ans$Mnlen, slen) != Boolean.FALSE) {
                        charSequence = frame763.temp;
                    } else {
                        Object obj6 = ans$Mnlen;
                        Object obj7 = obj6;
                        try {
                            charSequence = strings.substring(frame763.temp, 0, ((Number) obj6).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th4 = th3;
                            new WrongType(classCastException, "substring", 3, obj7);
                            throw th4;
                        }
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "make-string", 1, obj4);
                    throw th5;
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        error$V = this.criterion;
                    } else if (characters.isChar(this.criterion)) {
                        try {
                            error$V = Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset.get(), this.criterion);
                        } catch (UnboundLocationException e3) {
                            UnboundLocationException unboundLocationException = e3;
                            UnboundLocationException unboundLocationException2 = unboundLocationException;
                            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1125, 26);
                            throw unboundLocationException2;
                        }
                    } else {
                        error$V = misc.error$V("string-delete criterion not predicate, char or char-set", new Object[]{this.criterion});
                    }
                    frame763.cset = error$V;
                    ModuleMethod moduleMethod2 = frame763.lambda$Fn175;
                    IntNum intNum2 = srfi13.Lit0;
                    Object obj8 = this.f216s;
                    Object[] objArr3 = new Object[2];
                    objArr3[0] = start;
                    Object[] objArr4 = objArr3;
                    objArr4[1] = end;
                    Object stringFold$V = srfi13.stringFold$V(moduleMethod2, intNum2, obj8, objArr4);
                    Object obj9 = stringFold$V;
                    try {
                        frame763.ans = strings.makeString(((Number) stringFold$V).intValue());
                        ModuleMethod moduleMethod3 = frame763.lambda$Fn176;
                        IntNum intNum3 = srfi13.Lit0;
                        Object obj10 = this.f216s;
                        Object[] objArr5 = new Object[2];
                        objArr5[0] = start;
                        Object[] objArr6 = objArr5;
                        objArr6[1] = end;
                        Object stringFold$V2 = srfi13.stringFold$V(moduleMethod3, intNum3, obj10, objArr6);
                        charSequence = frame763.ans;
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException3 = e4;
                        Throwable th6 = th;
                        new WrongType(classCastException3, "make-string", 1, obj9);
                        throw th6;
                    }
                } catch (UnboundLocationException e5) {
                    UnboundLocationException unboundLocationException3 = e5;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1124, 22);
                    throw unboundLocationException4;
                }
            }
            return charSequence;
        }
    }

    public static Object stringFilter$V(Object criterion, Object s, Object[] argsArray) {
        frame75 frame752;
        new frame75();
        frame75 frame753 = frame752;
        frame753.criterion = criterion;
        frame753.f216s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame753.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame753.lambda$Fn172, frame753.lambda$Fn173);
    }

    /* compiled from: srfi13.scm */
    public class frame76 extends ModuleBody {
        CharSequence ans;
        Object cset;
        final ModuleMethod lambda$Fn174;
        final ModuleMethod lambda$Fn175;
        final ModuleMethod lambda$Fn176;
        frame75 staticLink;
        CharSequence temp;

        public frame76() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            new ModuleMethod(this, 147, (Object) null, 8194);
            ModuleMethod moduleMethod4 = moduleMethod;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1116");
            this.lambda$Fn174 = moduleMethod4;
            new ModuleMethod(this, 148, (Object) null, 8194);
            ModuleMethod moduleMethod5 = moduleMethod2;
            moduleMethod5.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1128");
            this.lambda$Fn175 = moduleMethod5;
            new ModuleMethod(this, 149, (Object) null, 8194);
            ModuleMethod moduleMethod6 = moduleMethod3;
            moduleMethod6.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1133");
            this.lambda$Fn176 = moduleMethod6;
        }

        /* access modifiers changed from: package-private */
        public Object lambda174(Object obj, Object obj2) {
            Object obj3;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object c = obj;
            Object i = obj2;
            if (Scheme.applyToArgs.apply2(this.staticLink.criterion, c) != Boolean.FALSE) {
                CharSequence charSequence = this.temp;
                CharSequence charSequence2 = charSequence;
                try {
                    CharSeq charSeq = (CharSeq) charSequence;
                    Object obj4 = i;
                    Object obj5 = obj4;
                    try {
                        int intValue = ((Number) obj4).intValue();
                        Object obj6 = c;
                        Object obj7 = obj6;
                        try {
                            strings.stringSet$Ex(charSeq, intValue, ((Char) obj6).charValue());
                            obj3 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th4 = th3;
                            new WrongType(classCastException, "string-set!", 3, obj7);
                            throw th4;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th2;
                        new WrongType(classCastException2, "string-set!", 2, obj5);
                        throw th5;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "string-set!", 1, (Object) charSequence2);
                    throw th6;
                }
            } else {
                obj3 = i;
            }
            return obj3;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 147:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 148:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 149:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda175(Object obj, Object obj2) {
            Object obj3;
            Object c = obj;
            Object i = obj2;
            try {
                if (Scheme.applyToArgs.apply3(srfi13.loc$char$Mnset$Mncontains$Qu.get(), this.cset, c) != Boolean.FALSE) {
                    obj3 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                } else {
                    obj3 = i;
                }
                return obj3;
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1128, 45);
                throw unboundLocationException2;
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            switch (moduleMethod2.selector) {
                case 147:
                    return lambda174(obj3, obj4);
                case 148:
                    return lambda175(obj3, obj4);
                case 149:
                    return lambda176(obj3, obj4);
                default:
                    return super.apply2(moduleMethod2, obj3, obj4);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda176(Object obj, Object obj2) {
            Object obj3;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object c = obj;
            Object i = obj2;
            try {
                if (Scheme.applyToArgs.apply3(srfi13.loc$char$Mnset$Mncontains$Qu.get(), this.cset, c) != Boolean.FALSE) {
                    CharSequence charSequence = this.ans;
                    CharSequence charSequence2 = charSequence;
                    try {
                        CharSeq charSeq = (CharSeq) charSequence;
                        Object obj4 = i;
                        Object obj5 = obj4;
                        try {
                            int intValue = ((Number) obj4).intValue();
                            Object obj6 = c;
                            Object obj7 = obj6;
                            try {
                                strings.stringSet$Ex(charSeq, intValue, ((Char) obj6).charValue());
                                obj3 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th4 = th3;
                                new WrongType(classCastException, "string-set!", 3, obj7);
                                throw th4;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th5 = th2;
                            new WrongType(classCastException2, "string-set!", 2, obj5);
                            throw th5;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th6 = th;
                        new WrongType(classCastException3, "string-set!", 1, (Object) charSequence2);
                        throw th6;
                    }
                } else {
                    obj3 = i;
                }
                return obj3;
            } catch (UnboundLocationException e4) {
                UnboundLocationException unboundLocationException = e4;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1133, 35);
                throw unboundLocationException2;
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame77 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn177;
        final ModuleMethod lambda$Fn178;
        LList maybe$Mnstart$Plend;
        Object str;

        public frame77() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 152, (Object) null, 0);
            this.lambda$Fn177 = moduleMethod;
            new ModuleMethod(this, 153, (Object) null, 8194);
            this.lambda$Fn178 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 152 ? lambda177() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 153 ? lambda178(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 152) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 153) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda177() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnindex, this.str, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda178(Object obj, Object obj2) {
            Object error$V;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Object start = obj;
            Object end = obj2;
            if (characters.isChar(this.criterion)) {
                Object obj3 = start;
                while (true) {
                    Object i = obj3;
                    Object apply2 = Scheme.numLss.apply2(i, end);
                    Object obj4 = apply2;
                    try {
                        boolean x = ((Boolean) apply2).booleanValue();
                        if (x) {
                            Object obj5 = this.criterion;
                            Object obj6 = obj5;
                            try {
                                Char charR = (Char) obj5;
                                Object obj7 = this.str;
                                Object obj8 = obj7;
                                try {
                                    CharSequence charSequence = (CharSequence) obj7;
                                    Object obj9 = i;
                                    Object obj10 = obj9;
                                    try {
                                        if (characters.isChar$Eq(charR, Char.make(strings.stringRef(charSequence, ((Number) obj9).intValue())))) {
                                            error$V = i;
                                            break;
                                        }
                                        obj3 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th11 = th10;
                                        new WrongType(classCastException, "string-ref", 2, obj10);
                                        throw th11;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th12 = th9;
                                    new WrongType(classCastException2, "string-ref", 1, obj8);
                                    throw th12;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th13 = th8;
                                new WrongType(classCastException3, "char=?", 1, obj6);
                                throw th13;
                            }
                        } else {
                            error$V = x ? Boolean.TRUE : Boolean.FALSE;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th14 = th7;
                        new WrongType(classCastException4, "x", -2, obj4);
                        throw th14;
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object obj11 = start;
                        while (true) {
                            Object i2 = obj11;
                            Object apply22 = Scheme.numLss.apply2(i2, end);
                            Object obj12 = apply22;
                            try {
                                boolean x2 = ((Boolean) apply22).booleanValue();
                                if (x2) {
                                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                    try {
                                        Object obj13 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                        Object obj14 = this.criterion;
                                        Object obj15 = this.str;
                                        Object obj16 = obj15;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj15;
                                            Object obj17 = i2;
                                            Object obj18 = obj17;
                                            try {
                                                if (applyToArgs.apply3(obj13, obj14, Char.make(strings.stringRef(charSequence2, ((Number) obj17).intValue()))) != Boolean.FALSE) {
                                                    error$V = i2;
                                                    break;
                                                }
                                                obj11 = AddOp.$Pl.apply2(i2, srfi13.Lit1);
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException5 = e5;
                                                Throwable th15 = th6;
                                                new WrongType(classCastException5, "string-ref", 2, obj18);
                                                throw th15;
                                            }
                                        } catch (ClassCastException e6) {
                                            ClassCastException classCastException6 = e6;
                                            Throwable th16 = th5;
                                            new WrongType(classCastException6, "string-ref", 1, obj16);
                                            throw th16;
                                        }
                                    } catch (UnboundLocationException e7) {
                                        UnboundLocationException unboundLocationException = e7;
                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1162, 9);
                                        throw unboundLocationException2;
                                    }
                                } else {
                                    error$V = x2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e8) {
                                ClassCastException classCastException7 = e8;
                                Throwable th17 = th4;
                                new WrongType(classCastException7, "x", -2, obj12);
                                throw th17;
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object obj19 = start;
                        while (true) {
                            Object i3 = obj19;
                            Object apply23 = Scheme.numLss.apply2(i3, end);
                            Object obj20 = apply23;
                            try {
                                boolean x3 = ((Boolean) apply23).booleanValue();
                                if (x3) {
                                    ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                                    Object obj21 = this.criterion;
                                    Object obj22 = this.str;
                                    Object obj23 = obj22;
                                    try {
                                        CharSequence charSequence3 = (CharSequence) obj22;
                                        Object obj24 = i3;
                                        Object obj25 = obj24;
                                        try {
                                            if (applyToArgs2.apply2(obj21, Char.make(strings.stringRef(charSequence3, ((Number) obj24).intValue()))) != Boolean.FALSE) {
                                                error$V = i3;
                                                break;
                                            }
                                            obj19 = AddOp.$Pl.apply2(i3, srfi13.Lit1);
                                        } catch (ClassCastException e9) {
                                            ClassCastException classCastException8 = e9;
                                            Throwable th18 = th3;
                                            new WrongType(classCastException8, "string-ref", 2, obj25);
                                            throw th18;
                                        }
                                    } catch (ClassCastException e10) {
                                        ClassCastException classCastException9 = e10;
                                        Throwable th19 = th2;
                                        new WrongType(classCastException9, "string-ref", 1, obj23);
                                        throw th19;
                                    }
                                } else {
                                    error$V = x3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e11) {
                                ClassCastException classCastException10 = e11;
                                Throwable th20 = th;
                                new WrongType(classCastException10, "x", -2, obj20);
                                throw th20;
                            }
                        }
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = srfi13.string$Mnindex;
                        Object[] objArr2 = objArr;
                        objArr2[1] = this.criterion;
                        error$V = misc.error$V("Second param is neither char-set, char, or predicate procedure.", objArr2);
                    }
                } catch (UnboundLocationException e12) {
                    UnboundLocationException unboundLocationException3 = e12;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1159, 5);
                    throw unboundLocationException4;
                }
            }
            return error$V;
        }
    }

    public static Object stringIndex$V(Object str, Object criterion, Object[] argsArray) {
        frame77 frame772;
        new frame77();
        frame77 frame773 = frame772;
        frame773.str = str;
        frame773.criterion = criterion;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame773.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame773.lambda$Fn177, frame773.lambda$Fn178);
    }

    /* compiled from: srfi13.scm */
    public class frame78 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn179;
        final ModuleMethod lambda$Fn180;
        LList maybe$Mnstart$Plend;
        Object str;

        public frame78() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 154, (Object) null, 0);
            this.lambda$Fn179 = moduleMethod;
            new ModuleMethod(this, 155, (Object) null, 8194);
            this.lambda$Fn180 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 154 ? lambda179() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 155 ? lambda180(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 154) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 155) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda179() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnindex$Mnright, this.str, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda180(Object obj, Object obj2) {
            Object error$V;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Object start = obj;
            Object end = obj2;
            if (characters.isChar(this.criterion)) {
                Object apply2 = AddOp.$Mn.apply2(end, srfi13.Lit1);
                while (true) {
                    Object i = apply2;
                    Object apply22 = Scheme.numGEq.apply2(i, start);
                    Object obj3 = apply22;
                    try {
                        boolean x = ((Boolean) apply22).booleanValue();
                        if (x) {
                            Object obj4 = this.criterion;
                            Object obj5 = obj4;
                            try {
                                Char charR = (Char) obj4;
                                Object obj6 = this.str;
                                Object obj7 = obj6;
                                try {
                                    CharSequence charSequence = (CharSequence) obj6;
                                    Object obj8 = i;
                                    Object obj9 = obj8;
                                    try {
                                        if (characters.isChar$Eq(charR, Char.make(strings.stringRef(charSequence, ((Number) obj8).intValue())))) {
                                            error$V = i;
                                            break;
                                        }
                                        apply2 = AddOp.$Mn.apply2(i, srfi13.Lit1);
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th11 = th10;
                                        new WrongType(classCastException, "string-ref", 2, obj9);
                                        throw th11;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th12 = th9;
                                    new WrongType(classCastException2, "string-ref", 1, obj7);
                                    throw th12;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th13 = th8;
                                new WrongType(classCastException3, "char=?", 1, obj5);
                                throw th13;
                            }
                        } else {
                            error$V = x ? Boolean.TRUE : Boolean.FALSE;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th14 = th7;
                        new WrongType(classCastException4, "x", -2, obj3);
                        throw th14;
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object apply23 = AddOp.$Mn.apply2(end, srfi13.Lit1);
                        while (true) {
                            Object i2 = apply23;
                            Object apply24 = Scheme.numGEq.apply2(i2, start);
                            Object obj10 = apply24;
                            try {
                                boolean x2 = ((Boolean) apply24).booleanValue();
                                if (x2) {
                                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                    try {
                                        Object obj11 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                        Object obj12 = this.criterion;
                                        Object obj13 = this.str;
                                        Object obj14 = obj13;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj13;
                                            Object obj15 = i2;
                                            Object obj16 = obj15;
                                            try {
                                                if (applyToArgs.apply3(obj11, obj12, Char.make(strings.stringRef(charSequence2, ((Number) obj15).intValue()))) != Boolean.FALSE) {
                                                    error$V = i2;
                                                    break;
                                                }
                                                apply23 = AddOp.$Mn.apply2(i2, srfi13.Lit1);
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException5 = e5;
                                                Throwable th15 = th6;
                                                new WrongType(classCastException5, "string-ref", 2, obj16);
                                                throw th15;
                                            }
                                        } catch (ClassCastException e6) {
                                            ClassCastException classCastException6 = e6;
                                            Throwable th16 = th5;
                                            new WrongType(classCastException6, "string-ref", 1, obj14);
                                            throw th16;
                                        }
                                    } catch (UnboundLocationException e7) {
                                        UnboundLocationException unboundLocationException = e7;
                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1182, 9);
                                        throw unboundLocationException2;
                                    }
                                } else {
                                    error$V = x2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e8) {
                                ClassCastException classCastException7 = e8;
                                Throwable th17 = th4;
                                new WrongType(classCastException7, "x", -2, obj10);
                                throw th17;
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object apply25 = AddOp.$Mn.apply2(end, srfi13.Lit1);
                        while (true) {
                            Object i3 = apply25;
                            Object apply26 = Scheme.numGEq.apply2(i3, start);
                            Object obj17 = apply26;
                            try {
                                boolean x3 = ((Boolean) apply26).booleanValue();
                                if (x3) {
                                    ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                                    Object obj18 = this.criterion;
                                    Object obj19 = this.str;
                                    Object obj20 = obj19;
                                    try {
                                        CharSequence charSequence3 = (CharSequence) obj19;
                                        Object obj21 = i3;
                                        Object obj22 = obj21;
                                        try {
                                            if (applyToArgs2.apply2(obj18, Char.make(strings.stringRef(charSequence3, ((Number) obj21).intValue()))) != Boolean.FALSE) {
                                                error$V = i3;
                                                break;
                                            }
                                            apply25 = AddOp.$Mn.apply2(i3, srfi13.Lit1);
                                        } catch (ClassCastException e9) {
                                            ClassCastException classCastException8 = e9;
                                            Throwable th18 = th3;
                                            new WrongType(classCastException8, "string-ref", 2, obj22);
                                            throw th18;
                                        }
                                    } catch (ClassCastException e10) {
                                        ClassCastException classCastException9 = e10;
                                        Throwable th19 = th2;
                                        new WrongType(classCastException9, "string-ref", 1, obj20);
                                        throw th19;
                                    }
                                } else {
                                    error$V = x3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e11) {
                                ClassCastException classCastException10 = e11;
                                Throwable th20 = th;
                                new WrongType(classCastException10, "x", -2, obj17);
                                throw th20;
                            }
                        }
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = srfi13.string$Mnindex$Mnright;
                        Object[] objArr2 = objArr;
                        objArr2[1] = this.criterion;
                        error$V = misc.error$V("Second param is neither char-set, char, or predicate procedure.", objArr2);
                    }
                } catch (UnboundLocationException e12) {
                    UnboundLocationException unboundLocationException3 = e12;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1179, 5);
                    throw unboundLocationException4;
                }
            }
            return error$V;
        }
    }

    public static Object stringIndexRight$V(Object str, Object criterion, Object[] argsArray) {
        frame78 frame782;
        new frame78();
        frame78 frame783 = frame782;
        frame783.str = str;
        frame783.criterion = criterion;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame783.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame783.lambda$Fn179, frame783.lambda$Fn180);
    }

    /* compiled from: srfi13.scm */
    public class frame79 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn181;
        final ModuleMethod lambda$Fn182;
        LList maybe$Mnstart$Plend;
        Object str;

        public frame79() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 156, (Object) null, 0);
            this.lambda$Fn181 = moduleMethod;
            new ModuleMethod(this, 157, (Object) null, 8194);
            this.lambda$Fn182 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 156 ? lambda181() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 157 ? lambda182(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 156) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 157) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda181() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip, this.str, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda182(Object obj, Object obj2) {
            Object error$V;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Object start = obj;
            Object end = obj2;
            if (characters.isChar(this.criterion)) {
                Object obj3 = start;
                while (true) {
                    Object i = obj3;
                    Object apply2 = Scheme.numLss.apply2(i, end);
                    Object obj4 = apply2;
                    try {
                        boolean x = ((Boolean) apply2).booleanValue();
                        if (x) {
                            Object obj5 = this.criterion;
                            Object obj6 = obj5;
                            try {
                                Char charR = (Char) obj5;
                                Object obj7 = this.str;
                                Object obj8 = obj7;
                                try {
                                    CharSequence charSequence = (CharSequence) obj7;
                                    Object obj9 = i;
                                    Object obj10 = obj9;
                                    try {
                                        if (!characters.isChar$Eq(charR, Char.make(strings.stringRef(charSequence, ((Number) obj9).intValue())))) {
                                            error$V = i;
                                            break;
                                        }
                                        obj3 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th11 = th10;
                                        new WrongType(classCastException, "string-ref", 2, obj10);
                                        throw th11;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th12 = th9;
                                    new WrongType(classCastException2, "string-ref", 1, obj8);
                                    throw th12;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th13 = th8;
                                new WrongType(classCastException3, "char=?", 1, obj6);
                                throw th13;
                            }
                        } else {
                            error$V = x ? Boolean.TRUE : Boolean.FALSE;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th14 = th7;
                        new WrongType(classCastException4, "x", -2, obj4);
                        throw th14;
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object obj11 = start;
                        while (true) {
                            Object i2 = obj11;
                            Object apply22 = Scheme.numLss.apply2(i2, end);
                            Object obj12 = apply22;
                            try {
                                boolean x2 = ((Boolean) apply22).booleanValue();
                                if (x2) {
                                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                    try {
                                        Object obj13 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                        Object obj14 = this.criterion;
                                        Object obj15 = this.str;
                                        Object obj16 = obj15;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj15;
                                            Object obj17 = i2;
                                            Object obj18 = obj17;
                                            try {
                                                if (applyToArgs.apply3(obj13, obj14, Char.make(strings.stringRef(charSequence2, ((Number) obj17).intValue()))) == Boolean.FALSE) {
                                                    error$V = i2;
                                                    break;
                                                }
                                                obj11 = AddOp.$Pl.apply2(i2, srfi13.Lit1);
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException5 = e5;
                                                Throwable th15 = th6;
                                                new WrongType(classCastException5, "string-ref", 2, obj18);
                                                throw th15;
                                            }
                                        } catch (ClassCastException e6) {
                                            ClassCastException classCastException6 = e6;
                                            Throwable th16 = th5;
                                            new WrongType(classCastException6, "string-ref", 1, obj16);
                                            throw th16;
                                        }
                                    } catch (UnboundLocationException e7) {
                                        UnboundLocationException unboundLocationException = e7;
                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1203, 9);
                                        throw unboundLocationException2;
                                    }
                                } else {
                                    error$V = x2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e8) {
                                ClassCastException classCastException7 = e8;
                                Throwable th17 = th4;
                                new WrongType(classCastException7, "x", -2, obj12);
                                throw th17;
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object obj19 = start;
                        while (true) {
                            Object i3 = obj19;
                            Object apply23 = Scheme.numLss.apply2(i3, end);
                            Object obj20 = apply23;
                            try {
                                boolean x3 = ((Boolean) apply23).booleanValue();
                                if (x3) {
                                    ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                                    Object obj21 = this.criterion;
                                    Object obj22 = this.str;
                                    Object obj23 = obj22;
                                    try {
                                        CharSequence charSequence3 = (CharSequence) obj22;
                                        Object obj24 = i3;
                                        Object obj25 = obj24;
                                        try {
                                            if (applyToArgs2.apply2(obj21, Char.make(strings.stringRef(charSequence3, ((Number) obj24).intValue()))) == Boolean.FALSE) {
                                                error$V = i3;
                                                break;
                                            }
                                            obj19 = AddOp.$Pl.apply2(i3, srfi13.Lit1);
                                        } catch (ClassCastException e9) {
                                            ClassCastException classCastException8 = e9;
                                            Throwable th18 = th3;
                                            new WrongType(classCastException8, "string-ref", 2, obj25);
                                            throw th18;
                                        }
                                    } catch (ClassCastException e10) {
                                        ClassCastException classCastException9 = e10;
                                        Throwable th19 = th2;
                                        new WrongType(classCastException9, "string-ref", 1, obj23);
                                        throw th19;
                                    }
                                } else {
                                    error$V = x3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e11) {
                                ClassCastException classCastException10 = e11;
                                Throwable th20 = th;
                                new WrongType(classCastException10, "x", -2, obj20);
                                throw th20;
                            }
                        }
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = srfi13.string$Mnskip;
                        Object[] objArr2 = objArr;
                        objArr2[1] = this.criterion;
                        error$V = misc.error$V("Second param is neither char-set, char, or predicate procedure.", objArr2);
                    }
                } catch (UnboundLocationException e12) {
                    UnboundLocationException unboundLocationException3 = e12;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1200, 5);
                    throw unboundLocationException4;
                }
            }
            return error$V;
        }
    }

    public static Object stringSkip$V(Object str, Object criterion, Object[] argsArray) {
        frame79 frame792;
        new frame79();
        frame79 frame793 = frame792;
        frame793.str = str;
        frame793.criterion = criterion;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame793.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame793.lambda$Fn181, frame793.lambda$Fn182);
    }

    /* compiled from: srfi13.scm */
    public class frame80 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn183;
        final ModuleMethod lambda$Fn184;
        LList maybe$Mnstart$Plend;
        Object str;

        public frame80() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 158, (Object) null, 0);
            this.lambda$Fn183 = moduleMethod;
            new ModuleMethod(this, 159, (Object) null, 8194);
            this.lambda$Fn184 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 158 ? lambda183() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 159 ? lambda184(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 158) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 159) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda183() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip$Mnright, this.str, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda184(Object obj, Object obj2) {
            Object error$V;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Object start = obj;
            Object end = obj2;
            if (characters.isChar(this.criterion)) {
                Object apply2 = AddOp.$Mn.apply2(end, srfi13.Lit1);
                while (true) {
                    Object i = apply2;
                    Object apply22 = Scheme.numGEq.apply2(i, start);
                    Object obj3 = apply22;
                    try {
                        boolean x = ((Boolean) apply22).booleanValue();
                        if (x) {
                            Object obj4 = this.criterion;
                            Object obj5 = obj4;
                            try {
                                Char charR = (Char) obj4;
                                Object obj6 = this.str;
                                Object obj7 = obj6;
                                try {
                                    CharSequence charSequence = (CharSequence) obj6;
                                    Object obj8 = i;
                                    Object obj9 = obj8;
                                    try {
                                        if (!characters.isChar$Eq(charR, Char.make(strings.stringRef(charSequence, ((Number) obj8).intValue())))) {
                                            error$V = i;
                                            break;
                                        }
                                        apply2 = AddOp.$Mn.apply2(i, srfi13.Lit1);
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th11 = th10;
                                        new WrongType(classCastException, "string-ref", 2, obj9);
                                        throw th11;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th12 = th9;
                                    new WrongType(classCastException2, "string-ref", 1, obj7);
                                    throw th12;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th13 = th8;
                                new WrongType(classCastException3, "char=?", 1, obj5);
                                throw th13;
                            }
                        } else {
                            error$V = x ? Boolean.TRUE : Boolean.FALSE;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th14 = th7;
                        new WrongType(classCastException4, "x", -2, obj3);
                        throw th14;
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object apply23 = AddOp.$Mn.apply2(end, srfi13.Lit1);
                        while (true) {
                            Object i2 = apply23;
                            Object apply24 = Scheme.numGEq.apply2(i2, start);
                            Object obj10 = apply24;
                            try {
                                boolean x2 = ((Boolean) apply24).booleanValue();
                                if (x2) {
                                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                    try {
                                        Object obj11 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                        Object obj12 = this.criterion;
                                        Object obj13 = this.str;
                                        Object obj14 = obj13;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj13;
                                            Object obj15 = i2;
                                            Object obj16 = obj15;
                                            try {
                                                if (applyToArgs.apply3(obj11, obj12, Char.make(strings.stringRef(charSequence2, ((Number) obj15).intValue()))) == Boolean.FALSE) {
                                                    error$V = i2;
                                                    break;
                                                }
                                                apply23 = AddOp.$Mn.apply2(i2, srfi13.Lit1);
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException5 = e5;
                                                Throwable th15 = th6;
                                                new WrongType(classCastException5, "string-ref", 2, obj16);
                                                throw th15;
                                            }
                                        } catch (ClassCastException e6) {
                                            ClassCastException classCastException6 = e6;
                                            Throwable th16 = th5;
                                            new WrongType(classCastException6, "string-ref", 1, obj14);
                                            throw th16;
                                        }
                                    } catch (UnboundLocationException e7) {
                                        UnboundLocationException unboundLocationException = e7;
                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1225, 9);
                                        throw unboundLocationException2;
                                    }
                                } else {
                                    error$V = x2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e8) {
                                ClassCastException classCastException7 = e8;
                                Throwable th17 = th4;
                                new WrongType(classCastException7, "x", -2, obj10);
                                throw th17;
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object apply25 = AddOp.$Mn.apply2(end, srfi13.Lit1);
                        while (true) {
                            Object i3 = apply25;
                            Object apply26 = Scheme.numGEq.apply2(i3, start);
                            Object obj17 = apply26;
                            try {
                                boolean x3 = ((Boolean) apply26).booleanValue();
                                if (x3) {
                                    ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                                    Object obj18 = this.criterion;
                                    Object obj19 = this.str;
                                    Object obj20 = obj19;
                                    try {
                                        CharSequence charSequence3 = (CharSequence) obj19;
                                        Object obj21 = i3;
                                        Object obj22 = obj21;
                                        try {
                                            if (applyToArgs2.apply2(obj18, Char.make(strings.stringRef(charSequence3, ((Number) obj21).intValue()))) == Boolean.FALSE) {
                                                error$V = i3;
                                                break;
                                            }
                                            apply25 = AddOp.$Mn.apply2(i3, srfi13.Lit1);
                                        } catch (ClassCastException e9) {
                                            ClassCastException classCastException8 = e9;
                                            Throwable th18 = th3;
                                            new WrongType(classCastException8, "string-ref", 2, obj22);
                                            throw th18;
                                        }
                                    } catch (ClassCastException e10) {
                                        ClassCastException classCastException9 = e10;
                                        Throwable th19 = th2;
                                        new WrongType(classCastException9, "string-ref", 1, obj20);
                                        throw th19;
                                    }
                                } else {
                                    error$V = x3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e11) {
                                ClassCastException classCastException10 = e11;
                                Throwable th20 = th;
                                new WrongType(classCastException10, "x", -2, obj17);
                                throw th20;
                            }
                        }
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = srfi13.string$Mnskip$Mnright;
                        Object[] objArr2 = objArr;
                        objArr2[1] = this.criterion;
                        error$V = misc.error$V("CRITERION param is neither char-set or char.", objArr2);
                    }
                } catch (UnboundLocationException e12) {
                    UnboundLocationException unboundLocationException3 = e12;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1222, 5);
                    throw unboundLocationException4;
                }
            }
            return error$V;
        }
    }

    public static Object stringSkipRight$V(Object str, Object criterion, Object[] argsArray) {
        frame80 frame802;
        new frame80();
        frame80 frame803 = frame802;
        frame803.str = str;
        frame803.criterion = criterion;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame803.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame803.lambda$Fn183, frame803.lambda$Fn184);
    }

    /* compiled from: srfi13.scm */
    public class frame81 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn185;
        final ModuleMethod lambda$Fn186;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f218s;

        public frame81() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, (Object) null, 0);
            this.lambda$Fn185 = moduleMethod;
            new ModuleMethod(this, 161, (Object) null, 8194);
            this.lambda$Fn186 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 160 ? lambda185() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 161 ? lambda186(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 160) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 161) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda185() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncount, this.f218s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda186(Object obj, Object obj2) {
            Object error$V;
            Object count;
            Throwable th;
            Throwable th2;
            Object count2;
            Throwable th3;
            Throwable th4;
            Object count3;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Object start = obj;
            Object end = obj2;
            if (characters.isChar(this.criterion)) {
                Object obj3 = start;
                Object obj4 = srfi13.Lit0;
                while (true) {
                    count3 = obj4;
                    Object i = obj3;
                    if (Scheme.numGEq.apply2(i, end) != Boolean.FALSE) {
                        break;
                    }
                    obj3 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                    Object obj5 = this.criterion;
                    Object obj6 = obj5;
                    try {
                        Char charR = (Char) obj5;
                        Object obj7 = this.f218s;
                        Object obj8 = obj7;
                        try {
                            CharSequence charSequence = (CharSequence) obj7;
                            Object obj9 = i;
                            Object obj10 = obj9;
                            try {
                                if (characters.isChar$Eq(charR, Char.make(strings.stringRef(charSequence, ((Number) obj9).intValue())))) {
                                    obj4 = AddOp.$Pl.apply2(count3, srfi13.Lit1);
                                } else {
                                    obj4 = count3;
                                }
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th8 = th7;
                                new WrongType(classCastException, "string-ref", 2, obj10);
                                throw th8;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th9 = th6;
                            new WrongType(classCastException2, "string-ref", 1, obj8);
                            throw th9;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th10 = th5;
                        new WrongType(classCastException3, "char=?", 1, obj6);
                        throw th10;
                    }
                }
                error$V = count3;
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object obj11 = start;
                        Object obj12 = srfi13.Lit0;
                        while (true) {
                            count2 = obj12;
                            Object i2 = obj11;
                            if (Scheme.numGEq.apply2(i2, end) != Boolean.FALSE) {
                                break;
                            }
                            obj11 = AddOp.$Pl.apply2(i2, srfi13.Lit1);
                            ApplyToArgs applyToArgs = Scheme.applyToArgs;
                            try {
                                Object obj13 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                Object obj14 = this.criterion;
                                Object obj15 = this.f218s;
                                Object obj16 = obj15;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj15;
                                    Object obj17 = i2;
                                    Object obj18 = obj17;
                                    try {
                                        if (applyToArgs.apply3(obj13, obj14, Char.make(strings.stringRef(charSequence2, ((Number) obj17).intValue()))) != Boolean.FALSE) {
                                            obj12 = AddOp.$Pl.apply2(count2, srfi13.Lit1);
                                        } else {
                                            obj12 = count2;
                                        }
                                    } catch (ClassCastException e4) {
                                        ClassCastException classCastException4 = e4;
                                        Throwable th11 = th4;
                                        new WrongType(classCastException4, "string-ref", 2, obj18);
                                        throw th11;
                                    }
                                } catch (ClassCastException e5) {
                                    ClassCastException classCastException5 = e5;
                                    Throwable th12 = th3;
                                    new WrongType(classCastException5, "string-ref", 1, obj16);
                                    throw th12;
                                }
                            } catch (UnboundLocationException e6) {
                                UnboundLocationException unboundLocationException = e6;
                                UnboundLocationException unboundLocationException2 = unboundLocationException;
                                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1248, 16);
                                throw unboundLocationException2;
                            }
                        }
                        error$V = count2;
                    } else if (misc.isProcedure(this.criterion)) {
                        Object obj19 = start;
                        Object obj20 = srfi13.Lit0;
                        while (true) {
                            count = obj20;
                            Object i3 = obj19;
                            if (Scheme.numGEq.apply2(i3, end) != Boolean.FALSE) {
                                break;
                            }
                            obj19 = AddOp.$Pl.apply2(i3, srfi13.Lit1);
                            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                            Object obj21 = this.criterion;
                            Object obj22 = this.f218s;
                            Object obj23 = obj22;
                            try {
                                CharSequence charSequence3 = (CharSequence) obj22;
                                Object obj24 = i3;
                                Object obj25 = obj24;
                                try {
                                    obj20 = applyToArgs2.apply2(obj21, Char.make(strings.stringRef(charSequence3, ((Number) obj24).intValue()))) != Boolean.FALSE ? AddOp.$Pl.apply2(count, srfi13.Lit1) : count;
                                } catch (ClassCastException e7) {
                                    ClassCastException classCastException6 = e7;
                                    Throwable th13 = th2;
                                    new WrongType(classCastException6, "string-ref", 2, obj25);
                                    throw th13;
                                }
                            } catch (ClassCastException e8) {
                                ClassCastException classCastException7 = e8;
                                Throwable th14 = th;
                                new WrongType(classCastException7, "string-ref", 1, obj23);
                                throw th14;
                            }
                        }
                        error$V = count;
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = srfi13.string$Mncount;
                        Object[] objArr2 = objArr;
                        objArr2[1] = this.criterion;
                        error$V = misc.error$V("CRITERION param is neither char-set or char.", objArr2);
                    }
                } catch (UnboundLocationException e9) {
                    UnboundLocationException unboundLocationException3 = e9;
                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1246, 5);
                    throw unboundLocationException4;
                }
            }
            return error$V;
        }
    }

    public static Object stringCount$V(Object s, Object criterion, Object[] argsArray) {
        frame81 frame812;
        new frame81();
        frame81 frame813 = frame812;
        frame813.f218s = s;
        frame813.criterion = criterion;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame813.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame813.lambda$Fn185, frame813.lambda$Fn186);
    }

    public static Object stringFill$Ex$V(Object s, Object obj, Object[] argsArray) {
        frame82 frame822;
        new frame82();
        frame82 frame823 = frame822;
        frame823.f219s = s;
        frame823.f608char = obj;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame823.maybe$Mnstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), characters.char$Qu, frame823.f608char, string$Mnfill$Ex);
            return call_with_values.callWithValues(frame823.lambda$Fn187, frame823.lambda$Fn188);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1270, 3);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame82 extends ModuleBody {

        /* renamed from: char  reason: not valid java name */
        Object f608char;
        final ModuleMethod lambda$Fn187;
        final ModuleMethod lambda$Fn188;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f219s;

        public frame82() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 162, (Object) null, 0);
            this.lambda$Fn187 = moduleMethod;
            new ModuleMethod(this, 163, (Object) null, 8194);
            this.lambda$Fn188 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 162 ? lambda187() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 163 ? lambda188(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 162) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 163) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda187() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfill$Ex, this.f219s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda188(Object obj, Object end) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Object start = obj;
            Object apply2 = AddOp.$Mn.apply2(end, srfi13.Lit1);
            while (true) {
                Object i = apply2;
                if (Scheme.numLss.apply2(i, start) != Boolean.FALSE) {
                    return Values.empty;
                }
                Object obj2 = this.f219s;
                Object obj3 = obj2;
                try {
                    CharSeq charSeq = (CharSeq) obj2;
                    Object obj4 = i;
                    Object obj5 = obj4;
                    try {
                        int intValue = ((Number) obj4).intValue();
                        Object obj6 = this.f608char;
                        Object obj7 = obj6;
                        try {
                            strings.stringSet$Ex(charSeq, intValue, ((Char) obj6).charValue());
                            apply2 = AddOp.$Mn.apply2(i, srfi13.Lit1);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th4 = th3;
                            new WrongType(classCastException, "string-set!", 3, obj7);
                            throw th4;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th2;
                        new WrongType(classCastException2, "string-set!", 2, obj5);
                        throw th5;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "string-set!", 1, obj3);
                    throw th6;
                }
            }
        }
    }

    public static Object stringCopy$Ex(Object obj, int i, CharSequence charSequence, int i2, int i3) {
        Throwable th;
        Throwable th2;
        Object to = obj;
        int tstart = i;
        CharSequence from = charSequence;
        int fstart = i2;
        int fend = i3;
        Object $PcCheckBounds = $PcCheckBounds(string$Mncopy$Ex, from, fstart, fend);
        Object obj2 = to;
        Object obj3 = obj2;
        try {
            Object $PcCheckSubstringSpec = $PcCheckSubstringSpec(string$Mncopy$Ex, (CharSequence) obj2, tstart, tstart + (fend - fstart));
            Object obj4 = to;
            Object obj5 = obj4;
            try {
                return $PcStringCopy$Ex((CharSequence) obj4, tstart, from, fstart, fend);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "%string-copy!", 0, obj5);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "%check-substring-spec", 1, obj3);
            throw th4;
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE:
                return stringParseStart$PlEnd(obj4, obj5, obj6);
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION:
                return stringParseFinalStart$PlEnd(obj4, obj5, obj6);
            case 197:
                return isSubstringSpecOk(obj4, obj5, obj6) ? Boolean.TRUE : Boolean.FALSE;
            case 201:
                try {
                    try {
                        try {
                            return $PcSubstring$SlShared((CharSequence) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th6 = th5;
                            new WrongType(classCastException, "%substring/shared", 3, obj6);
                            throw th6;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th7 = th4;
                        new WrongType(classCastException2, "%substring/shared", 2, obj5);
                        throw th7;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th8 = th3;
                    new WrongType(classCastException3, "%substring/shared", 1, obj4);
                    throw th8;
                }
            case 277:
                return $PcStringTitlecase$Ex(obj4, obj5, obj6);
            case 299:
                try {
                    try {
                        return stringCopy$Ex(obj4, ((Number) obj5).intValue(), (CharSequence) obj6);
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "string-copy!", 3, obj6);
                        throw th9;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "string-copy!", 2, obj5);
                    throw th10;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static Object $PcStringCopy$Ex(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3) {
        Values values;
        Throwable th;
        Throwable th2;
        CharSequence to = charSequence;
        int tstart = i;
        CharSequence from = charSequence2;
        int fstart = i2;
        int fend = i3;
        if (fstart > tstart) {
            int j = tstart;
            int i4 = fstart;
            while (i4 < fend) {
                CharSequence charSequence3 = to;
                CharSequence charSequence4 = charSequence3;
                try {
                    strings.stringSet$Ex((CharSeq) charSequence3, j, strings.stringRef(from, i4));
                    j++;
                    i4++;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "string-set!", 1, (Object) charSequence4);
                    throw th3;
                }
            }
            values = Values.empty;
        } else {
            int j2 = -1 + tstart + (fend - fstart);
            int i5 = fend - 1;
            while (i5 >= fstart) {
                CharSequence charSequence5 = to;
                CharSequence charSequence6 = charSequence5;
                try {
                    strings.stringSet$Ex((CharSeq) charSequence5, j2, strings.stringRef(from, i5));
                    j2--;
                    i5--;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-set!", 1, (Object) charSequence6);
                    throw th4;
                }
            }
            values = Values.empty;
        }
        return values;
    }

    /* compiled from: srfi13.scm */
    public class frame83 extends ModuleBody {
        final ModuleMethod lambda$Fn189;
        final ModuleMethod lambda$Fn190;
        LList maybe$Mnstarts$Plends;
        Object pattern;
        Object text;

        public frame83() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 166, (Object) null, 0);
            this.lambda$Fn189 = moduleMethod;
            new ModuleMethod(this, 167, (Object) null, 12291);
            this.lambda$Fn190 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 166 ? lambda189() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 167 ? lambda190(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 166) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 167) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda189() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncontains, this.text, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda190(Object rest, Object tStart, Object tEnd) {
            frame84 frame84;
            new frame84();
            frame84 frame842 = frame84;
            frame842.staticLink = this;
            frame84 frame843 = frame842;
            frame843.rest = rest;
            frame843.t$Mnstart = tStart;
            frame843.t$Mnend = tEnd;
            return call_with_values.callWithValues(frame843.lambda$Fn191, frame843.lambda$Fn192);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame84 extends ModuleBody {
        final ModuleMethod lambda$Fn191;
        final ModuleMethod lambda$Fn192;
        Object rest;
        frame83 staticLink;
        Object t$Mnend;
        Object t$Mnstart;

        public frame84() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 164, (Object) null, 0);
            this.lambda$Fn191 = moduleMethod;
            new ModuleMethod(this, 165, (Object) null, 8194);
            this.lambda$Fn192 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 164 ? lambda191() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 165 ? lambda192(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 164) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 165) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda191() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains, this.staticLink.pattern, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda192(Object p$Mnstart, Object p$Mnend) {
            return srfi13.$PcKmpSearch(this.staticLink.pattern, this.staticLink.text, characters.char$Eq$Qu, p$Mnstart, p$Mnend, this.t$Mnstart, this.t$Mnend);
        }
    }

    public static Object stringContains$V(Object text, Object pattern, Object[] argsArray) {
        frame83 frame832;
        new frame83();
        frame83 frame833 = frame832;
        frame833.text = text;
        frame833.pattern = pattern;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame833.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame833.lambda$Fn189, frame833.lambda$Fn190);
    }

    /* compiled from: srfi13.scm */
    public class frame85 extends ModuleBody {
        final ModuleMethod lambda$Fn193;
        final ModuleMethod lambda$Fn194;
        LList maybe$Mnstarts$Plends;
        Object pattern;
        Object text;

        public frame85() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 170, (Object) null, 0);
            this.lambda$Fn193 = moduleMethod;
            new ModuleMethod(this, 171, (Object) null, 12291);
            this.lambda$Fn194 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 170 ? lambda193() : super.apply0(moduleMethod2);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 171 ? lambda194(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 170) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 171) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda193() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncontains$Mnci, this.text, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda194(Object rest, Object tStart, Object tEnd) {
            frame86 frame86;
            new frame86();
            frame86 frame862 = frame86;
            frame862.staticLink = this;
            frame86 frame863 = frame862;
            frame863.rest = rest;
            frame863.t$Mnstart = tStart;
            frame863.t$Mnend = tEnd;
            return call_with_values.callWithValues(frame863.lambda$Fn195, frame863.lambda$Fn196);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame86 extends ModuleBody {
        final ModuleMethod lambda$Fn195;
        final ModuleMethod lambda$Fn196;
        Object rest;
        frame85 staticLink;
        Object t$Mnend;
        Object t$Mnstart;

        public frame86() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 168, (Object) null, 0);
            this.lambda$Fn195 = moduleMethod;
            new ModuleMethod(this, 169, (Object) null, 8194);
            this.lambda$Fn196 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 168 ? lambda195() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 169 ? lambda196(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 168) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 169) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda195() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains$Mnci, this.staticLink.pattern, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda196(Object p$Mnstart, Object p$Mnend) {
            return srfi13.$PcKmpSearch(this.staticLink.pattern, this.staticLink.text, unicode.char$Mnci$Eq$Qu, p$Mnstart, p$Mnend, this.t$Mnstart, this.t$Mnend);
        }
    }

    public static Object stringContainsCi$V(Object text, Object pattern, Object[] argsArray) {
        frame85 frame852;
        new frame85();
        frame85 frame853 = frame852;
        frame853.text = text;
        frame853.pattern = pattern;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame853.maybe$Mnstarts$Plends = makeList;
        return call_with_values.callWithValues(frame853.lambda$Fn193, frame853.lambda$Fn194);
    }

    public static Object $PcKmpSearch(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object t$Mnend) {
        Object pattern;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Object pattern2 = obj;
        Object text = obj2;
        Object c$Eq = obj3;
        Object p$Mnstart = obj4;
        Object p$Mnend = obj5;
        Object t$Mnstart = obj6;
        Object apply2 = AddOp.$Mn.apply2(p$Mnend, p$Mnstart);
        Object[] objArr = new Object[3];
        objArr[0] = c$Eq;
        Object[] objArr2 = objArr;
        objArr2[1] = p$Mnstart;
        Object[] objArr3 = objArr2;
        objArr3[2] = p$Mnend;
        Object rv = makeKmpRestartVector$V(pattern2, objArr3);
        Object plen = apply2;
        Object obj7 = t$Mnstart;
        Number number = Lit0;
        Object apply22 = AddOp.$Mn.apply2(t$Mnend, t$Mnstart);
        Object obj8 = plen;
        while (true) {
            Object obj9 = obj8;
            Object obj10 = apply22;
            Number number2 = number;
            Object ti = obj7;
            if (Scheme.numEqu.apply2(number2, plen) != Boolean.FALSE) {
                pattern = AddOp.$Mn.apply2(ti, plen);
                break;
            }
            Object apply23 = Scheme.numLEq.apply2(obj9, obj10);
            Object obj11 = apply23;
            try {
                boolean x = ((Boolean) apply23).booleanValue();
                if (x) {
                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                    Object obj12 = c$Eq;
                    Object obj13 = text;
                    Object obj14 = obj13;
                    try {
                        CharSequence charSequence = (CharSequence) obj13;
                        Object obj15 = ti;
                        Object obj16 = obj15;
                        try {
                            Char make = Char.make(strings.stringRef(charSequence, ((Number) obj15).intValue()));
                            Object obj17 = pattern2;
                            Object obj18 = obj17;
                            try {
                                CharSequence charSequence2 = (CharSequence) obj17;
                                Object apply24 = AddOp.$Pl.apply2(p$Mnstart, number2);
                                Object obj19 = apply24;
                                try {
                                    if (applyToArgs.apply3(obj12, make, Char.make(strings.stringRef(charSequence2, ((Number) apply24).intValue()))) != Boolean.FALSE) {
                                        obj7 = AddOp.$Pl.apply2(Lit1, ti);
                                        number = AddOp.$Pl.apply2(Lit1, number2);
                                        apply22 = AddOp.$Mn.apply2(obj10, Lit1);
                                        obj8 = AddOp.$Mn.apply2(obj9, Lit1);
                                    } else {
                                        Object obj20 = rv;
                                        Object obj21 = obj20;
                                        try {
                                            FVector fVector = (FVector) obj20;
                                            Number number3 = number2;
                                            Number number4 = number3;
                                            try {
                                                Object pi = vectors.vectorRef(fVector, number3.intValue());
                                                if (Scheme.numEqu.apply2(pi, Lit13) != Boolean.FALSE) {
                                                    obj7 = AddOp.$Pl.apply2(ti, Lit1);
                                                    number = Lit0;
                                                    apply22 = AddOp.$Mn.apply2(obj10, Lit1);
                                                    obj8 = plen;
                                                } else {
                                                    obj7 = ti;
                                                    number = pi;
                                                    apply22 = obj10;
                                                    obj8 = AddOp.$Mn.apply2(plen, pi);
                                                }
                                            } catch (ClassCastException e) {
                                                ClassCastException classCastException = e;
                                                Throwable th8 = th7;
                                                new WrongType(classCastException, "vector-ref", 2, (Object) number4);
                                                throw th8;
                                            }
                                        } catch (ClassCastException e2) {
                                            ClassCastException classCastException2 = e2;
                                            Throwable th9 = th6;
                                            new WrongType(classCastException2, "vector-ref", 1, obj21);
                                            throw th9;
                                        }
                                    }
                                } catch (ClassCastException e3) {
                                    ClassCastException classCastException3 = e3;
                                    Throwable th10 = th5;
                                    new WrongType(classCastException3, "string-ref", 2, obj19);
                                    throw th10;
                                }
                            } catch (ClassCastException e4) {
                                ClassCastException classCastException4 = e4;
                                Throwable th11 = th4;
                                new WrongType(classCastException4, "string-ref", 1, obj18);
                                throw th11;
                            }
                        } catch (ClassCastException e5) {
                            ClassCastException classCastException5 = e5;
                            Throwable th12 = th3;
                            new WrongType(classCastException5, "string-ref", 2, obj16);
                            throw th12;
                        }
                    } catch (ClassCastException e6) {
                        ClassCastException classCastException6 = e6;
                        Throwable th13 = th2;
                        new WrongType(classCastException6, "string-ref", 1, obj14);
                        throw th13;
                    }
                } else {
                    pattern = x ? Boolean.TRUE : Boolean.FALSE;
                }
            } catch (ClassCastException e7) {
                ClassCastException classCastException7 = e7;
                Throwable th14 = th;
                new WrongType(classCastException7, "x", -2, obj11);
                throw th14;
            }
        }
        return pattern;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v31, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v37, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v39, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v52, resolved type: gnu.math.IntNum} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object makeKmpRestartVector$V(java.lang.Object r30, java.lang.Object[] r31) {
        /*
            r2 = r30
            r3 = r31
            gnu.kawa.slib.srfi13$frame87 r17 = new gnu.kawa.slib.srfi13$frame87
            r28 = r17
            r17 = r28
            r18 = r28
            r18.<init>()
            r5 = r17
            r17 = r5
            r18 = r2
            r0 = r18
            r1 = r17
            r1.pattern = r0
            r17 = r3
            r18 = 0
            gnu.lists.LList r17 = gnu.lists.LList.makeList(r17, r18)
            r28 = r17
            r17 = r28
            r18 = r28
            r6 = r18
            r4 = r17
            gnu.kawa.functions.ApplyToArgs r17 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r18 = loc$let$Mnoptionals$St
            java.lang.Object r18 = r18.get()     // Catch:{ UnboundLocationException -> 0x028c }
            r19 = r4
            gnu.kawa.functions.ApplyToArgs r20 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r21 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r22 = loc$c$Eq
            java.lang.Object r22 = r22.get()     // Catch:{ UnboundLocationException -> 0x029e }
            gnu.expr.ModuleMethod r23 = kawa.lib.characters.char$Eq$Qu
            gnu.mapping.Location r24 = loc$c$Eq
            java.lang.Object r24 = r24.get()     // Catch:{ UnboundLocationException -> 0x02b0 }
            boolean r24 = kawa.lib.misc.isProcedure(r24)
            if (r24 == 0) goto L_0x01ac
            java.lang.Boolean r24 = java.lang.Boolean.TRUE
        L_0x0051:
            java.lang.Object r21 = r21.apply3(r22, r23, r24)
            gnu.kawa.functions.ApplyToArgs r22 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r23 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r24 = loc$start
            java.lang.Object r24 = r24.get()     // Catch:{ UnboundLocationException -> 0x02c2 }
            gnu.mapping.Location r25 = loc$end
            java.lang.Object r25 = r25.get()     // Catch:{ UnboundLocationException -> 0x02d4 }
            java.lang.Object r23 = r23.apply2(r24, r25)
            r24 = r5
            r0 = r24
            gnu.expr.ModuleMethod r0 = r0.lambda$Fn197
            r24 = r0
            java.lang.Object r22 = r22.apply2(r23, r24)
            java.lang.Object r20 = r20.apply2(r21, r22)
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Mn
            gnu.mapping.Location r22 = loc$end
            java.lang.Object r22 = r22.get()     // Catch:{ UnboundLocationException -> 0x02e6 }
            gnu.mapping.Location r23 = loc$start
            java.lang.Object r23 = r23.get()     // Catch:{ UnboundLocationException -> 0x02f8 }
            java.lang.Object r21 = r21.apply2(r22, r23)
            r6 = r21
            r21 = r6
            r28 = r21
            r21 = r28
            r22 = r28
            r8 = r22
            java.lang.Number r21 = (java.lang.Number) r21     // Catch:{ ClassCastException -> 0x030a }
            int r21 = r21.intValue()     // Catch:{ ClassCastException -> 0x030a }
            gnu.math.IntNum r22 = Lit13
            gnu.lists.FVector r21 = kawa.lib.vectors.makeVector(r21, r22)
            r7 = r21
            gnu.kawa.functions.NumberCompare r21 = kawa.standard.Scheme.numGrt
            r22 = r6
            gnu.math.IntNum r23 = Lit0
            java.lang.Object r21 = r21.apply2(r22, r23)
            java.lang.Boolean r22 = java.lang.Boolean.FALSE
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0283
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Mn
            r22 = r6
            gnu.math.IntNum r23 = Lit1
            java.lang.Object r21 = r21.apply2(r22, r23)
            r22 = r5
            r0 = r22
            java.lang.Object r0 = r0.pattern
            r22 = r0
            r28 = r22
            r22 = r28
            r23 = r28
            r10 = r23
            java.lang.CharSequence r22 = (java.lang.CharSequence) r22     // Catch:{ ClassCastException -> 0x032a }
            gnu.mapping.Location r23 = loc$start
            java.lang.Object r23 = r23.get()     // Catch:{ UnboundLocationException -> 0x034a }
            r28 = r23
            r23 = r28
            r24 = r28
            r10 = r24
            java.lang.Number r23 = (java.lang.Number) r23     // Catch:{ ClassCastException -> 0x035c }
            int r23 = r23.intValue()     // Catch:{ ClassCastException -> 0x035c }
            char r22 = kawa.lib.strings.stringRef(r22, r23)
            r9 = r22
            r8 = r21
            gnu.math.IntNum r21 = Lit0
            gnu.math.IntNum r22 = Lit13
            gnu.mapping.Location r23 = loc$start
            java.lang.Object r23 = r23.get()     // Catch:{ UnboundLocationException -> 0x037c }
            r12 = r23
            r11 = r22
            r10 = r21
        L_0x00ff:
            gnu.kawa.functions.NumberCompare r21 = kawa.standard.Scheme.numLss
            r22 = r10
            r23 = r8
            java.lang.Object r21 = r21.apply2(r22, r23)
            java.lang.Boolean r22 = java.lang.Boolean.FALSE
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0283
            r21 = r11
            r13 = r21
        L_0x0115:
            gnu.kawa.functions.NumberCompare r21 = kawa.standard.Scheme.numEqu
            r22 = r13
            gnu.math.IntNum r23 = Lit13
            java.lang.Object r21 = r21.apply2(r22, r23)
            java.lang.Boolean r22 = java.lang.Boolean.FALSE
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x01b0
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r22 = Lit1
            r23 = r10
            java.lang.Object r21 = r21.apply2(r22, r23)
            r14 = r21
            gnu.kawa.functions.ApplyToArgs r21 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r22 = loc$c$Eq
            java.lang.Object r22 = r22.get()     // Catch:{ UnboundLocationException -> 0x038e }
            r23 = r5
            r0 = r23
            java.lang.Object r0 = r0.pattern
            r23 = r0
            r28 = r23
            r23 = r28
            r24 = r28
            r15 = r24
            java.lang.CharSequence r23 = (java.lang.CharSequence) r23     // Catch:{ ClassCastException -> 0x03a0 }
            gnu.kawa.functions.AddOp r24 = gnu.kawa.functions.AddOp.$Pl
            r25 = r12
            gnu.math.IntNum r26 = Lit1
            java.lang.Object r24 = r24.apply2(r25, r26)
            r28 = r24
            r24 = r28
            r25 = r28
            r15 = r25
            java.lang.Number r24 = (java.lang.Number) r24     // Catch:{ ClassCastException -> 0x03c0 }
            int r24 = r24.intValue()     // Catch:{ ClassCastException -> 0x03c0 }
            char r23 = kawa.lib.strings.stringRef(r23, r24)
            gnu.text.Char r23 = gnu.text.Char.make(r23)
            r24 = r9
            gnu.text.Char r24 = gnu.text.Char.make(r24)
            java.lang.Object r21 = r21.apply3(r22, r23, r24)
            java.lang.Boolean r22 = java.lang.Boolean.FALSE
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0196
            r21 = r7
            r22 = r14
            r28 = r22
            r22 = r28
            r23 = r28
            r15 = r23
            java.lang.Number r22 = (java.lang.Number) r22     // Catch:{ ClassCastException -> 0x03e0 }
            int r22 = r22.intValue()     // Catch:{ ClassCastException -> 0x03e0 }
            gnu.math.IntNum r23 = Lit0
            kawa.lib.vectors.vectorSet$Ex(r21, r22, r23)
        L_0x0196:
            r21 = r14
            gnu.math.IntNum r22 = Lit0
            gnu.kawa.functions.AddOp r23 = gnu.kawa.functions.AddOp.$Pl
            r24 = r12
            gnu.math.IntNum r25 = Lit1
            java.lang.Object r23 = r23.apply2(r24, r25)
            r12 = r23
            r11 = r22
            r10 = r21
            goto L_0x00ff
        L_0x01ac:
            java.lang.Boolean r24 = java.lang.Boolean.FALSE
            goto L_0x0051
        L_0x01b0:
            gnu.kawa.functions.ApplyToArgs r21 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r22 = loc$c$Eq
            java.lang.Object r22 = r22.get()     // Catch:{ UnboundLocationException -> 0x0400 }
            r23 = r5
            r0 = r23
            java.lang.Object r0 = r0.pattern
            r23 = r0
            r28 = r23
            r23 = r28
            r24 = r28
            r14 = r24
            java.lang.CharSequence r23 = (java.lang.CharSequence) r23     // Catch:{ ClassCastException -> 0x0412 }
            r24 = r12
            r28 = r24
            r24 = r28
            r25 = r28
            r14 = r25
            java.lang.Number r24 = (java.lang.Number) r24     // Catch:{ ClassCastException -> 0x0432 }
            int r24 = r24.intValue()     // Catch:{ ClassCastException -> 0x0432 }
            char r23 = kawa.lib.strings.stringRef(r23, r24)
            gnu.text.Char r23 = gnu.text.Char.make(r23)
            r24 = r5
            r0 = r24
            java.lang.Object r0 = r0.pattern
            r24 = r0
            r28 = r24
            r24 = r28
            r25 = r28
            r14 = r25
            java.lang.CharSequence r24 = (java.lang.CharSequence) r24     // Catch:{ ClassCastException -> 0x0452 }
            gnu.kawa.functions.AddOp r25 = gnu.kawa.functions.AddOp.$Pl
            r26 = r13
            gnu.mapping.Location r27 = loc$start
            java.lang.Object r27 = r27.get()     // Catch:{ UnboundLocationException -> 0x0472 }
            java.lang.Object r25 = r25.apply2(r26, r27)
            r28 = r25
            r25 = r28
            r26 = r28
            r14 = r26
            java.lang.Number r25 = (java.lang.Number) r25     // Catch:{ ClassCastException -> 0x0484 }
            int r25 = r25.intValue()     // Catch:{ ClassCastException -> 0x0484 }
            char r24 = kawa.lib.strings.stringRef(r24, r25)
            gnu.text.Char r24 = gnu.text.Char.make(r24)
            java.lang.Object r21 = r21.apply3(r22, r23, r24)
            java.lang.Boolean r22 = java.lang.Boolean.FALSE
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0269
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r22 = Lit1
            r23 = r10
            java.lang.Object r21 = r21.apply2(r22, r23)
            r14 = r21
            gnu.kawa.functions.AddOp r21 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r22 = Lit1
            r23 = r13
            java.lang.Object r21 = r21.apply2(r22, r23)
            r15 = r21
            r21 = r7
            r22 = r14
            r28 = r22
            r22 = r28
            r23 = r28
            r16 = r23
            java.lang.Number r22 = (java.lang.Number) r22     // Catch:{ ClassCastException -> 0x04a4 }
            int r22 = r22.intValue()     // Catch:{ ClassCastException -> 0x04a4 }
            r23 = r15
            kawa.lib.vectors.vectorSet$Ex(r21, r22, r23)
            r21 = r14
            r22 = r15
            gnu.kawa.functions.AddOp r23 = gnu.kawa.functions.AddOp.$Pl
            r24 = r12
            gnu.math.IntNum r25 = Lit1
            java.lang.Object r23 = r23.apply2(r24, r25)
            r12 = r23
            r11 = r22
            r10 = r21
            goto L_0x00ff
        L_0x0269:
            r21 = r7
            r22 = r13
            r28 = r22
            r22 = r28
            r23 = r28
            r14 = r23
            java.lang.Number r22 = (java.lang.Number) r22     // Catch:{ ClassCastException -> 0x04c4 }
            int r22 = r22.intValue()     // Catch:{ ClassCastException -> 0x04c4 }
            java.lang.Object r21 = kawa.lib.vectors.vectorRef(r21, r22)
            r13 = r21
            goto L_0x0115
        L_0x0283:
            r21 = r7
            java.lang.Object r17 = r17.apply4(r18, r19, r20, r21)
            r2 = r17
            return r2
        L_0x028c:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1397(0x575, float:1.958E-42)
            r21 = 3
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x029e:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1398(0x576, float:1.959E-42)
            r21 = 20
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x02b0:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1398(0x576, float:1.959E-42)
            r21 = 43
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x02c2:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1399(0x577, float:1.96E-42)
            r21 = 7
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x02d4:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1399(0x577, float:1.96E-42)
            r21 = 14
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x02e6:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1402(0x57a, float:1.965E-42)
            r21 = 22
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x02f8:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1402(0x57a, float:1.965E-42)
            r21 = 26
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x030a:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "make-vector"
            r21 = 1
            r22 = r8
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x032a:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "string-ref"
            r21 = 1
            r22 = r10
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x034a:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1406(0x57e, float:1.97E-42)
            r21 = 27
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x035c:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "string-ref"
            r21 = 2
            r22 = r10
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x037c:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1410(0x582, float:1.976E-42)
            r21 = 6
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x038e:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1418(0x58a, float:1.987E-42)
            r21 = 18
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x03a0:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "string-ref"
            r21 = 1
            r22 = r15
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x03c0:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "string-ref"
            r21 = 2
            r22 = r15
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x03e0:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "vector-set!"
            r21 = 2
            r22 = r15
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x0400:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1422(0x58e, float:1.993E-42)
            r21 = 7
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x0412:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "string-ref"
            r21 = 1
            r22 = r14
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x0432:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "string-ref"
            r21 = 2
            r22 = r14
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x0452:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "string-ref"
            r21 = 1
            r22 = r14
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x0472:
            r17 = move-exception
            r28 = r17
            r17 = r28
            r18 = r28
            java.lang.String r19 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r20 = 1422(0x58e, float:1.993E-42)
            r21 = 59
            r18.setLine(r19, r20, r21)
            throw r17
        L_0x0484:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "string-ref"
            r21 = 2
            r22 = r14
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x04a4:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "vector-set!"
            r21 = 2
            r22 = r16
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        L_0x04c4:
            r17 = move-exception
            gnu.mapping.WrongType r18 = new gnu.mapping.WrongType
            r28 = r17
            r29 = r18
            r17 = r29
            r18 = r28
            r19 = r29
            r28 = r18
            r29 = r19
            r18 = r29
            r19 = r28
            java.lang.String r20 = "vector-ref"
            r21 = 2
            r22 = r14
            r18.<init>((java.lang.ClassCastException) r19, (java.lang.String) r20, (int) r21, (java.lang.Object) r22)
            throw r17
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.makeKmpRestartVector$V(java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    /* compiled from: srfi13.scm */
    public class frame87 extends ModuleBody {
        final ModuleMethod lambda$Fn197;
        Object pattern;

        public frame87() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 172, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1399");
            this.lambda$Fn197 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 172) {
                return lambda197(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda197(Object args) {
            return srfi13.stringParseStart$PlEnd(srfi13.make$Mnkmp$Mnrestart$Mnvector, this.pattern, args);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 172) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object kmpStep(Object obj, Object obj2, Object obj3, Object i, Object obj4, Object obj5) {
        Throwable th;
        Throwable th2;
        Object pat;
        Throwable th3;
        Throwable th4;
        Object pat2 = obj;
        Object rv = obj2;
        Object c = obj3;
        Object c$Eq = obj4;
        Object p$Mnstart = obj5;
        Object obj6 = i;
        while (true) {
            Object i2 = obj6;
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            Object obj7 = c$Eq;
            Object obj8 = c;
            Object obj9 = pat2;
            Object obj10 = obj9;
            try {
                CharSequence charSequence = (CharSequence) obj9;
                Object apply2 = AddOp.$Pl.apply2(i2, p$Mnstart);
                Object obj11 = apply2;
                try {
                    if (applyToArgs.apply3(obj7, obj8, Char.make(strings.stringRef(charSequence, ((Number) apply2).intValue()))) != Boolean.FALSE) {
                        pat = AddOp.$Pl.apply2(i2, Lit1);
                        break;
                    }
                    Object obj12 = rv;
                    Object obj13 = obj12;
                    try {
                        FVector fVector = (FVector) obj12;
                        Object obj14 = i2;
                        Object obj15 = obj14;
                        try {
                            Object i3 = vectors.vectorRef(fVector, ((Number) obj14).intValue());
                            if (Scheme.numEqu.apply2(i3, Lit13) != Boolean.FALSE) {
                                pat = Lit0;
                                break;
                            }
                            obj6 = i3;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "vector-ref", 2, obj15);
                            throw th5;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "vector-ref", 1, obj13);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "string-ref", 2, obj11);
                    throw th7;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th8 = th;
                new WrongType(classCastException4, "string-ref", 1, obj10);
                throw th8;
            }
        }
        return pat;
    }

    public static Object stringKmpPartialSearch$V(Object obj, Object obj2, Object s, Object obj3, Object[] argsArray) {
        frame88 frame882;
        Object obj4;
        Throwable th;
        Object apply1;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Object pat = obj;
        Object rv = obj2;
        Object i = obj3;
        new frame88();
        frame88 frame883 = frame882;
        frame883.f220s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList c$Eq$Plp$Mnstart$Pls$Mnstart$Pls$Mnend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), vectors.vector$Qu, rv, string$Mnkmp$Mnpartial$Mnsearch);
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            try {
                Object obj5 = loc$let$Mnoptionals$St.get();
                LList lList2 = c$Eq$Plp$Mnstart$Pls$Mnstart$Pls$Mnend;
                ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                try {
                    try {
                        Object apply3 = Scheme.applyToArgs.apply3(loc$c$Eq.get(), characters.char$Eq$Qu, misc.isProcedure(loc$c$Eq.get()) ? Boolean.TRUE : Boolean.FALSE);
                        ApplyToArgs applyToArgs3 = Scheme.applyToArgs;
                        try {
                            Object obj6 = loc$p$Mnstart.get();
                            IntNum intNum = Lit0;
                            try {
                                boolean x = numbers.isInteger(loc$p$Mnstart.get());
                                if (x) {
                                    try {
                                        boolean x2 = numbers.isExact(loc$p$Mnstart.get());
                                        if (x2) {
                                            try {
                                                obj4 = Scheme.numLEq.apply2(Lit0, loc$p$Mnstart.get());
                                            } catch (UnboundLocationException e) {
                                                UnboundLocationException unboundLocationException = e;
                                                UnboundLocationException unboundLocationException2 = unboundLocationException;
                                                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1467, 64);
                                                throw unboundLocationException2;
                                            }
                                        } else {
                                            obj4 = x2 ? Boolean.TRUE : Boolean.FALSE;
                                        }
                                    } catch (UnboundLocationException e2) {
                                        UnboundLocationException unboundLocationException3 = e2;
                                        UnboundLocationException unboundLocationException4 = unboundLocationException3;
                                        unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1467, 49);
                                        throw unboundLocationException4;
                                    }
                                } else {
                                    obj4 = x ? Boolean.TRUE : Boolean.FALSE;
                                }
                                try {
                                    try {
                                        Object apply32 = applyToArgs2.apply3(apply3, applyToArgs3.apply3(obj6, intNum, obj4), Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply2(loc$s$Mnstart.get(), loc$s$Mnend.get()), frame883.lambda$Fn198));
                                        Object obj7 = rv;
                                        Object obj8 = obj7;
                                        try {
                                            frame883.patlen = vectors.vectorLength((FVector) obj7);
                                            try {
                                                Object apply42 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), frame883.lambda$Fn199, i, string$Mnkmp$Mnpartial$Mnsearch);
                                                try {
                                                    Object obj9 = loc$s$Mnstart.get();
                                                    Object obj10 = i;
                                                    while (true) {
                                                        Object obj11 = obj10;
                                                        Object si = obj9;
                                                        if (Scheme.numEqu.apply2(obj11, Integer.valueOf(frame883.patlen)) != Boolean.FALSE) {
                                                            apply1 = AddOp.$Mn.apply1(si);
                                                            break;
                                                        }
                                                        try {
                                                            if (Scheme.numEqu.apply2(si, loc$s$Mnend.get()) != Boolean.FALSE) {
                                                                apply1 = obj11;
                                                                break;
                                                            }
                                                            Object obj12 = frame883.f220s;
                                                            Object obj13 = obj12;
                                                            try {
                                                                CharSequence charSequence = (CharSequence) obj12;
                                                                Object obj14 = si;
                                                                Object obj15 = obj14;
                                                                try {
                                                                    char c = strings.stringRef(charSequence, ((Number) obj14).intValue());
                                                                    obj9 = AddOp.$Pl.apply2(si, Lit1);
                                                                    Object obj16 = obj11;
                                                                    while (true) {
                                                                        Object vi = obj16;
                                                                        ApplyToArgs applyToArgs4 = Scheme.applyToArgs;
                                                                        try {
                                                                            Object obj17 = loc$c$Eq.get();
                                                                            Char make = Char.make(c);
                                                                            Object obj18 = pat;
                                                                            Object obj19 = obj18;
                                                                            try {
                                                                                CharSequence charSequence2 = (CharSequence) obj18;
                                                                                try {
                                                                                    Object apply2 = AddOp.$Pl.apply2(vi, loc$p$Mnstart.get());
                                                                                    Object obj20 = apply2;
                                                                                    try {
                                                                                        if (applyToArgs4.apply3(obj17, make, Char.make(strings.stringRef(charSequence2, ((Number) apply2).intValue()))) != Boolean.FALSE) {
                                                                                            obj10 = AddOp.$Pl.apply2(vi, Lit1);
                                                                                            break;
                                                                                        }
                                                                                        Object obj21 = rv;
                                                                                        Object obj22 = obj21;
                                                                                        try {
                                                                                            FVector fVector = (FVector) obj21;
                                                                                            Object obj23 = vi;
                                                                                            Object obj24 = obj23;
                                                                                            try {
                                                                                                Object vi2 = vectors.vectorRef(fVector, ((Number) obj23).intValue());
                                                                                                if (Scheme.numEqu.apply2(vi2, Lit13) != Boolean.FALSE) {
                                                                                                    obj10 = Lit0;
                                                                                                    break;
                                                                                                }
                                                                                                obj16 = vi2;
                                                                                            } catch (ClassCastException e3) {
                                                                                                ClassCastException classCastException = e3;
                                                                                                Throwable th8 = th7;
                                                                                                new WrongType(classCastException, "vector-ref", 2, obj24);
                                                                                                throw th8;
                                                                                            }
                                                                                        } catch (ClassCastException e4) {
                                                                                            ClassCastException classCastException2 = e4;
                                                                                            Throwable th9 = th6;
                                                                                            new WrongType(classCastException2, "vector-ref", 1, obj22);
                                                                                            throw th9;
                                                                                        }
                                                                                    } catch (ClassCastException e5) {
                                                                                        ClassCastException classCastException3 = e5;
                                                                                        Throwable th10 = th5;
                                                                                        new WrongType(classCastException3, "string-ref", 2, obj20);
                                                                                        throw th10;
                                                                                    }
                                                                                } catch (UnboundLocationException e6) {
                                                                                    UnboundLocationException unboundLocationException5 = e6;
                                                                                    UnboundLocationException unboundLocationException6 = unboundLocationException5;
                                                                                    unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1484, 42);
                                                                                    throw unboundLocationException6;
                                                                                }
                                                                            } catch (ClassCastException e7) {
                                                                                ClassCastException classCastException4 = e7;
                                                                                Throwable th11 = th4;
                                                                                new WrongType(classCastException4, "string-ref", 1, obj19);
                                                                                throw th11;
                                                                            }
                                                                        } catch (UnboundLocationException e8) {
                                                                            UnboundLocationException unboundLocationException7 = e8;
                                                                            UnboundLocationException unboundLocationException8 = unboundLocationException7;
                                                                            unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1484, 14);
                                                                            throw unboundLocationException8;
                                                                        }
                                                                    }
                                                                } catch (ClassCastException e9) {
                                                                    ClassCastException classCastException5 = e9;
                                                                    Throwable th12 = th3;
                                                                    new WrongType(classCastException5, "string-ref", 2, obj15);
                                                                    throw th12;
                                                                }
                                                            } catch (ClassCastException e10) {
                                                                ClassCastException classCastException6 = e10;
                                                                Throwable th13 = th2;
                                                                new WrongType(classCastException6, "string-ref", 1, obj13);
                                                                throw th13;
                                                            }
                                                        } catch (UnboundLocationException e11) {
                                                            UnboundLocationException unboundLocationException9 = e11;
                                                            UnboundLocationException unboundLocationException10 = unboundLocationException9;
                                                            unboundLocationException9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1479, 15);
                                                            throw unboundLocationException10;
                                                        }
                                                    }
                                                    return applyToArgs.apply4(obj5, lList2, apply32, apply1);
                                                } catch (UnboundLocationException e12) {
                                                    UnboundLocationException unboundLocationException11 = e12;
                                                    UnboundLocationException unboundLocationException12 = unboundLocationException11;
                                                    unboundLocationException11.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1476, 7);
                                                    throw unboundLocationException12;
                                                }
                                            } catch (UnboundLocationException e13) {
                                                UnboundLocationException unboundLocationException13 = e13;
                                                UnboundLocationException unboundLocationException14 = unboundLocationException13;
                                                unboundLocationException13.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1472, 7);
                                                throw unboundLocationException14;
                                            }
                                        } catch (ClassCastException e14) {
                                            ClassCastException classCastException7 = e14;
                                            Throwable th14 = th;
                                            new WrongType(classCastException7, "vector-length", 1, obj8);
                                            throw th14;
                                        }
                                    } catch (UnboundLocationException e15) {
                                        UnboundLocationException unboundLocationException15 = e15;
                                        UnboundLocationException unboundLocationException16 = unboundLocationException15;
                                        unboundLocationException15.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1468, 16);
                                        throw unboundLocationException16;
                                    }
                                } catch (UnboundLocationException e16) {
                                    UnboundLocationException unboundLocationException17 = e16;
                                    UnboundLocationException unboundLocationException18 = unboundLocationException17;
                                    unboundLocationException17.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1468, 7);
                                    throw unboundLocationException18;
                                }
                            } catch (UnboundLocationException e17) {
                                UnboundLocationException unboundLocationException19 = e17;
                                UnboundLocationException unboundLocationException20 = unboundLocationException19;
                                unboundLocationException19.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1467, 32);
                                throw unboundLocationException20;
                            }
                        } catch (UnboundLocationException e18) {
                            UnboundLocationException unboundLocationException21 = e18;
                            UnboundLocationException unboundLocationException22 = unboundLocationException21;
                            unboundLocationException21.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1467, 6);
                            throw unboundLocationException22;
                        }
                    } catch (UnboundLocationException e19) {
                        UnboundLocationException unboundLocationException23 = e19;
                        UnboundLocationException unboundLocationException24 = unboundLocationException23;
                        unboundLocationException23.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1466, 34);
                        throw unboundLocationException24;
                    }
                } catch (UnboundLocationException e20) {
                    UnboundLocationException unboundLocationException25 = e20;
                    UnboundLocationException unboundLocationException26 = unboundLocationException25;
                    unboundLocationException25.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1466, 6);
                    throw unboundLocationException26;
                }
            } catch (UnboundLocationException e21) {
                UnboundLocationException unboundLocationException27 = e21;
                UnboundLocationException unboundLocationException28 = unboundLocationException27;
                unboundLocationException27.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1465, 3);
                throw unboundLocationException28;
            }
        } catch (UnboundLocationException e22) {
            UnboundLocationException unboundLocationException29 = e22;
            UnboundLocationException unboundLocationException30 = unboundLocationException29;
            unboundLocationException29.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1464, 3);
            throw unboundLocationException30;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame88 extends ModuleBody {
        final ModuleMethod lambda$Fn198;
        final ModuleMethod lambda$Fn199;
        int patlen;

        /* renamed from: s */
        Object f220s;

        public frame88() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 173, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1468");
            this.lambda$Fn198 = moduleMethod3;
            new ModuleMethod(this, 174, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1472");
            this.lambda$Fn199 = moduleMethod4;
        }

        /* access modifiers changed from: package-private */
        public Object lambda198(Object args) {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnkmp$Mnpartial$Mnsearch, this.f220s, args);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 173:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 174:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod2, obj2, callContext2);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            switch (moduleMethod2.selector) {
                case 173:
                    return lambda198(obj2);
                case 174:
                    return lambda199(obj2) ? Boolean.TRUE : Boolean.FALSE;
                default:
                    return super.apply1(moduleMethod2, obj2);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lambda199(Object obj) {
            boolean z;
            Throwable th;
            Object i = obj;
            boolean x = numbers.isInteger(i);
            if (x) {
                boolean x2 = numbers.isExact(i);
                if (x2) {
                    Object apply2 = Scheme.numLEq.apply2(srfi13.Lit0, i);
                    Object obj2 = apply2;
                    try {
                        boolean x3 = ((Boolean) apply2).booleanValue();
                        z = x3 ? ((Boolean) Scheme.numLss.apply2(i, Integer.valueOf(this.patlen))).booleanValue() : x3;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "x", -2, obj2);
                        throw th2;
                    }
                } else {
                    z = x2;
                }
            } else {
                z = x;
            }
            return z;
        }
    }

    public static boolean isStringNull(Object s) {
        Throwable th;
        Object obj = s;
        Object obj2 = obj;
        try {
            return numbers.isZero(Integer.valueOf(strings.stringLength((CharSequence) obj)));
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "string-length", 1, obj2);
            throw th2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame89 extends ModuleBody {
        final ModuleMethod lambda$Fn200;
        final ModuleMethod lambda$Fn201;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f221s;

        public frame89() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 175, (Object) null, 0);
            this.lambda$Fn200 = moduleMethod;
            new ModuleMethod(this, 176, (Object) null, 8194);
            this.lambda$Fn201 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 175 ? lambda200() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 176 ? lambda201(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 175) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 176) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda200() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse, this.f221s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda201(Object obj, Object end) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Object start = obj;
            Object len = AddOp.$Mn.apply2(end, start);
            Object obj2 = len;
            Object obj3 = obj2;
            try {
                CharSequence ans = strings.makeString(((Number) obj2).intValue());
                Object obj4 = start;
                Object apply2 = AddOp.$Mn.apply2(len, srfi13.Lit1);
                while (true) {
                    Object obj5 = apply2;
                    Object i = obj4;
                    if (Scheme.numLss.apply2(obj5, srfi13.Lit0) != Boolean.FALSE) {
                        return ans;
                    }
                    CharSequence charSequence = ans;
                    CharSequence charSequence2 = charSequence;
                    try {
                        CharSeq charSeq = (CharSeq) charSequence;
                        Object obj6 = obj5;
                        Object obj7 = obj6;
                        try {
                            int intValue = ((Number) obj6).intValue();
                            Object obj8 = this.f221s;
                            Object obj9 = obj8;
                            try {
                                CharSequence charSequence3 = (CharSequence) obj8;
                                Object obj10 = i;
                                Object obj11 = obj10;
                                try {
                                    strings.stringSet$Ex(charSeq, intValue, strings.stringRef(charSequence3, ((Number) obj10).intValue()));
                                    obj4 = AddOp.$Pl.apply2(i, srfi13.Lit1);
                                    apply2 = AddOp.$Mn.apply2(obj5, srfi13.Lit1);
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th6 = th5;
                                    new WrongType(classCastException, "string-ref", 2, obj11);
                                    throw th6;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th7 = th4;
                                new WrongType(classCastException2, "string-ref", 1, obj9);
                                throw th7;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th8 = th3;
                            new WrongType(classCastException3, "string-set!", 2, obj7);
                            throw th8;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "string-set!", 1, (Object) charSequence2);
                        throw th9;
                    }
                }
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th10 = th;
                new WrongType(classCastException5, "make-string", 1, obj3);
                throw th10;
            }
        }
    }

    public static Object stringReverse$V(Object s, Object[] argsArray) {
        frame89 frame892;
        new frame89();
        frame89 frame893 = frame892;
        frame893.f221s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame893.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame893.lambda$Fn200, frame893.lambda$Fn201);
    }

    /* compiled from: srfi13.scm */
    public class frame90 extends ModuleBody {
        final ModuleMethod lambda$Fn202;
        final ModuleMethod lambda$Fn203;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f223s;

        public frame90() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 177, (Object) null, 0);
            this.lambda$Fn202 = moduleMethod;
            new ModuleMethod(this, 178, (Object) null, 8194);
            this.lambda$Fn203 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 177 ? lambda202() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 178 ? lambda203(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 177) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 178) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda202() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse$Ex, this.f223s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda203(Object start, Object end) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Object apply2 = AddOp.$Mn.apply2(end, srfi13.Lit1);
            Object obj = start;
            while (true) {
                Object obj2 = obj;
                Object i = apply2;
                if (Scheme.numLEq.apply2(i, obj2) != Boolean.FALSE) {
                    return Values.empty;
                }
                Object obj3 = this.f223s;
                Object obj4 = obj3;
                try {
                    CharSequence charSequence = (CharSequence) obj3;
                    Object obj5 = i;
                    Object obj6 = obj5;
                    try {
                        char ci = strings.stringRef(charSequence, ((Number) obj5).intValue());
                        Object obj7 = this.f223s;
                        Object obj8 = obj7;
                        try {
                            CharSeq charSeq = (CharSeq) obj7;
                            Object obj9 = i;
                            Object obj10 = obj9;
                            try {
                                int intValue = ((Number) obj9).intValue();
                                Object obj11 = this.f223s;
                                Object obj12 = obj11;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj11;
                                    Object obj13 = obj2;
                                    Object obj14 = obj13;
                                    try {
                                        strings.stringSet$Ex(charSeq, intValue, strings.stringRef(charSequence2, ((Number) obj13).intValue()));
                                        Object obj15 = this.f223s;
                                        Object obj16 = obj15;
                                        try {
                                            CharSeq charSeq2 = (CharSeq) obj15;
                                            Object obj17 = obj2;
                                            Object obj18 = obj17;
                                            try {
                                                strings.stringSet$Ex(charSeq2, ((Number) obj17).intValue(), ci);
                                                apply2 = AddOp.$Mn.apply2(i, srfi13.Lit1);
                                                obj = AddOp.$Pl.apply2(obj2, srfi13.Lit1);
                                            } catch (ClassCastException e) {
                                                ClassCastException classCastException = e;
                                                Throwable th9 = th8;
                                                new WrongType(classCastException, "string-set!", 2, obj18);
                                                throw th9;
                                            }
                                        } catch (ClassCastException e2) {
                                            ClassCastException classCastException2 = e2;
                                            Throwable th10 = th7;
                                            new WrongType(classCastException2, "string-set!", 1, obj16);
                                            throw th10;
                                        }
                                    } catch (ClassCastException e3) {
                                        ClassCastException classCastException3 = e3;
                                        Throwable th11 = th6;
                                        new WrongType(classCastException3, "string-ref", 2, obj14);
                                        throw th11;
                                    }
                                } catch (ClassCastException e4) {
                                    ClassCastException classCastException4 = e4;
                                    Throwable th12 = th5;
                                    new WrongType(classCastException4, "string-ref", 1, obj12);
                                    throw th12;
                                }
                            } catch (ClassCastException e5) {
                                ClassCastException classCastException5 = e5;
                                Throwable th13 = th4;
                                new WrongType(classCastException5, "string-set!", 2, obj10);
                                throw th13;
                            }
                        } catch (ClassCastException e6) {
                            ClassCastException classCastException6 = e6;
                            Throwable th14 = th3;
                            new WrongType(classCastException6, "string-set!", 1, obj8);
                            throw th14;
                        }
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th15 = th2;
                        new WrongType(classCastException7, "string-ref", 2, obj6);
                        throw th15;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th;
                    new WrongType(classCastException8, "string-ref", 1, obj4);
                    throw th16;
                }
            }
        }
    }

    public static Object stringReverse$Ex$V(Object s, Object[] argsArray) {
        frame90 frame902;
        new frame90();
        frame90 frame903 = frame902;
        frame903.f223s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame903.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame903.lambda$Fn202, frame903.lambda$Fn203);
    }

    public static CharSequence reverseList$To$String(Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object clist = obj;
        Object obj2 = clist;
        Object obj3 = obj2;
        try {
            int len = C1245lists.length((LList) obj2);
            CharSequence s = strings.makeString(len);
            Object valueOf = Integer.valueOf(len - 1);
            Object obj4 = clist;
            while (true) {
                Object obj5 = obj4;
                Object obj6 = valueOf;
                if (!C1245lists.isPair(obj5)) {
                    return s;
                }
                CharSequence charSequence = s;
                CharSequence charSequence2 = charSequence;
                try {
                    CharSeq charSeq = (CharSeq) charSequence;
                    Object obj7 = obj6;
                    Object obj8 = obj7;
                    try {
                        int intValue = ((Number) obj7).intValue();
                        Object apply1 = C1245lists.car.apply1(obj5);
                        Object obj9 = apply1;
                        try {
                            strings.stringSet$Ex(charSeq, intValue, ((Char) apply1).charValue());
                            valueOf = AddOp.$Mn.apply2(obj6, Lit1);
                            obj4 = C1245lists.cdr.apply1(obj5);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th5 = th4;
                            new WrongType(classCastException, "string-set!", 3, obj9);
                            throw th5;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "string-set!", 2, obj8);
                        throw th6;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "string-set!", 1, (Object) charSequence2);
                    throw th7;
                }
            }
        } catch (ClassCastException e4) {
            ClassCastException classCastException4 = e4;
            Throwable th8 = th;
            new WrongType(classCastException4, "length", 1, obj3);
            throw th8;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame91 extends ModuleBody {
        final ModuleMethod lambda$Fn204;
        final ModuleMethod lambda$Fn205;
        LList maybe$Mnstart$Plend;

        /* renamed from: s */
        Object f224s;

        public frame91() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 179, (Object) null, 0);
            this.lambda$Fn204 = moduleMethod;
            new ModuleMethod(this, 180, (Object) null, 8194);
            this.lambda$Fn205 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 179 ? lambda204() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 180 ? lambda205(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 179) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 180) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda204() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mn$Grlist, this.f224s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda205(Object obj, Object end) {
            Throwable th;
            Throwable th2;
            Object start = obj;
            Object apply2 = AddOp.$Mn.apply2(end, srfi13.Lit1);
            Object obj2 = LList.Empty;
            while (true) {
                Object obj3 = obj2;
                Object i = apply2;
                if (Scheme.numLss.apply2(i, start) != Boolean.FALSE) {
                    return obj3;
                }
                apply2 = AddOp.$Mn.apply2(i, srfi13.Lit1);
                Object obj4 = this.f224s;
                Object obj5 = obj4;
                try {
                    CharSequence charSequence = (CharSequence) obj4;
                    Object obj6 = i;
                    Object obj7 = obj6;
                    try {
                        obj2 = C1245lists.cons(Char.make(strings.stringRef(charSequence, ((Number) obj6).intValue())), obj3);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "string-ref", 2, obj7);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "string-ref", 1, obj5);
                    throw th4;
                }
            }
        }
    }

    public static Object string$To$List$V(Object s, Object[] argsArray) {
        frame91 frame912;
        new frame91();
        frame91 frame913 = frame912;
        frame913.f224s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame913.maybe$Mnstart$Plend = makeList;
        return call_with_values.callWithValues(frame913.lambda$Fn204, frame913.lambda$Fn205);
    }

    public static Object stringAppend$SlShared$V(Object[] argsArray) {
        LList strings = LList.makeList(argsArray, 0);
        LList lList = strings;
        return stringConcatenate$SlShared(strings);
    }

    public static Object stringConcatenate$SlShared(Object strings) {
        Boolean bool;
        Number number;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Object obj;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Object obj2 = strings;
        Number number2 = Lit0;
        Boolean bool2 = Boolean.FALSE;
        while (true) {
            bool = bool2;
            number = number2;
            Object strings2 = obj2;
            if (!C1245lists.isPair(strings2)) {
                break;
            }
            Object string = C1245lists.car.apply1(strings2);
            Object tail = C1245lists.cdr.apply1(strings2);
            Object obj3 = string;
            Object obj4 = obj3;
            try {
                int slen = strings.stringLength((CharSequence) obj3);
                if (numbers.isZero(Integer.valueOf(slen))) {
                    obj2 = tail;
                    number2 = number;
                    bool2 = bool;
                } else {
                    obj2 = tail;
                    number2 = AddOp.$Pl.apply2(number, Integer.valueOf(slen));
                    bool2 = bool != Boolean.FALSE ? bool : strings2;
                }
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th8 = th7;
                new WrongType(classCastException, "string-length", 1, obj4);
                throw th8;
            }
        }
        Number number3 = number;
        Number number4 = number3;
        try {
            if (numbers.isZero(number3)) {
                obj = "";
            } else {
                NumberCompare numberCompare = Scheme.numEqu;
                Number number5 = number;
                Object apply1 = C1245lists.car.apply1(bool);
                Object obj5 = apply1;
                try {
                    if (numberCompare.apply2(number5, Integer.valueOf(strings.stringLength((CharSequence) apply1))) != Boolean.FALSE) {
                        obj = C1245lists.car.apply1(bool);
                    } else {
                        Number number6 = number;
                        Number number7 = number6;
                        try {
                            CharSequence ans = strings.makeString(number6.intValue());
                            Object obj6 = bool;
                            Object obj7 = Lit0;
                            while (true) {
                                Object obj8 = obj7;
                                Object strings3 = obj6;
                                if (!C1245lists.isPair(strings3)) {
                                    break;
                                }
                                Object s = C1245lists.car.apply1(strings3);
                                Object obj9 = s;
                                Object obj10 = obj9;
                                try {
                                    int slen2 = strings.stringLength((CharSequence) obj9);
                                    CharSequence charSequence = ans;
                                    Object obj11 = obj8;
                                    Object obj12 = obj11;
                                    try {
                                        int intValue = ((Number) obj11).intValue();
                                        Object obj13 = s;
                                        Object obj14 = obj13;
                                        try {
                                            Object $PcStringCopy$Ex = $PcStringCopy$Ex(charSequence, intValue, (CharSequence) obj13, 0, slen2);
                                            obj6 = C1245lists.cdr.apply1(strings3);
                                            obj7 = AddOp.$Pl.apply2(obj8, Integer.valueOf(slen2));
                                        } catch (ClassCastException e2) {
                                            ClassCastException classCastException2 = e2;
                                            Throwable th9 = th6;
                                            new WrongType(classCastException2, "%string-copy!", 2, obj14);
                                            throw th9;
                                        }
                                    } catch (ClassCastException e3) {
                                        ClassCastException classCastException3 = e3;
                                        Throwable th10 = th5;
                                        new WrongType(classCastException3, "%string-copy!", 1, obj12);
                                        throw th10;
                                    }
                                } catch (ClassCastException e4) {
                                    ClassCastException classCastException4 = e4;
                                    Throwable th11 = th4;
                                    new WrongType(classCastException4, "string-length", 1, obj10);
                                    throw th11;
                                }
                            }
                            obj = ans;
                        } catch (ClassCastException e5) {
                            ClassCastException classCastException5 = e5;
                            Throwable th12 = th3;
                            new WrongType(classCastException5, "make-string", 1, (Object) number7);
                            throw th12;
                        }
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th13 = th2;
                    new WrongType(classCastException6, "string-length", 1, obj5);
                    throw th13;
                }
            }
            return obj;
        } catch (ClassCastException e7) {
            ClassCastException classCastException7 = e7;
            Throwable th14 = th;
            new WrongType(classCastException7, "zero?", 1, (Object) number4);
            throw th14;
        }
    }

    public static CharSequence stringConcatenate(Object obj) {
        Object i;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object strings = obj;
        Object obj2 = strings;
        Object obj3 = Lit0;
        while (true) {
            i = obj3;
            Object strings2 = obj2;
            if (!C1245lists.isPair(strings2)) {
                break;
            }
            obj2 = C1245lists.cdr.apply1(strings2);
            AddOp addOp = AddOp.$Pl;
            Object obj4 = i;
            Object apply1 = C1245lists.car.apply1(strings2);
            Object obj5 = apply1;
            try {
                obj3 = addOp.apply2(obj4, Integer.valueOf(strings.stringLength((CharSequence) apply1)));
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th6 = th5;
                new WrongType(classCastException, "string-length", 1, obj5);
                throw th6;
            }
        }
        Object obj6 = i;
        Object obj7 = obj6;
        try {
            CharSequence ans = strings.makeString(((Number) obj6).intValue());
            Object obj8 = Lit0;
            Object obj9 = strings;
            while (true) {
                Object obj10 = obj9;
                Object obj11 = obj8;
                if (!C1245lists.isPair(obj10)) {
                    return ans;
                }
                Object s = C1245lists.car.apply1(obj10);
                Object obj12 = s;
                Object obj13 = obj12;
                try {
                    int slen = strings.stringLength((CharSequence) obj12);
                    CharSequence charSequence = ans;
                    Object obj14 = obj11;
                    Object obj15 = obj14;
                    try {
                        int intValue = ((Number) obj14).intValue();
                        Object obj16 = s;
                        Object obj17 = obj16;
                        try {
                            Object $PcStringCopy$Ex = $PcStringCopy$Ex(charSequence, intValue, (CharSequence) obj16, 0, slen);
                            obj8 = AddOp.$Pl.apply2(obj11, Integer.valueOf(slen));
                            obj9 = C1245lists.cdr.apply1(obj10);
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th7 = th4;
                            new WrongType(classCastException2, "%string-copy!", 2, obj17);
                            throw th7;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th8 = th3;
                        new WrongType(classCastException3, "%string-copy!", 1, obj15);
                        throw th8;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "string-length", 1, obj13);
                    throw th9;
                }
            }
        } catch (ClassCastException e5) {
            ClassCastException classCastException5 = e5;
            Throwable th10 = th;
            new WrongType(classCastException5, "make-string", 1, obj7);
            throw th10;
        }
    }

    public static Object stringConcatenateReverse$V(Object obj, Object[] argsArray) {
        Throwable th;
        Object obj2;
        Throwable th2;
        Throwable th3;
        Object string$Mnlist = obj;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList maybe$Mnfinal$Plend = makeList;
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        try {
            Object obj3 = loc$let$Mnoptionals$St.get();
            LList lList2 = maybe$Mnfinal$Plend;
            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
            try {
                try {
                    Object apply3 = Scheme.applyToArgs.apply3(loc$final.get(), "", strings.isString(loc$final.get()) ? Boolean.TRUE : Boolean.FALSE);
                    ApplyToArgs applyToArgs3 = Scheme.applyToArgs;
                    try {
                        Object obj4 = loc$end.get();
                        try {
                            Object obj5 = loc$final.get();
                            Object obj6 = obj5;
                            try {
                                Integer valueOf = Integer.valueOf(strings.stringLength((CharSequence) obj5));
                                try {
                                    boolean x = numbers.isInteger(loc$end.get());
                                    if (x) {
                                        try {
                                            boolean x2 = numbers.isExact(loc$end.get());
                                            if (x2) {
                                                NumberCompare numberCompare = Scheme.numLEq;
                                                IntNum intNum = Lit0;
                                                try {
                                                    Object obj7 = loc$end.get();
                                                    try {
                                                        Object obj8 = loc$final.get();
                                                        Object obj9 = obj8;
                                                        try {
                                                            obj2 = numberCompare.apply3(intNum, obj7, Integer.valueOf(strings.stringLength((CharSequence) obj8)));
                                                        } catch (ClassCastException e) {
                                                            ClassCastException classCastException = e;
                                                            Throwable th4 = th3;
                                                            new WrongType(classCastException, "string-length", 1, obj9);
                                                            throw th4;
                                                        }
                                                    } catch (UnboundLocationException e2) {
                                                        UnboundLocationException unboundLocationException = e2;
                                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1621, 36);
                                                        throw unboundLocationException2;
                                                    }
                                                } catch (UnboundLocationException e3) {
                                                    UnboundLocationException unboundLocationException3 = e3;
                                                    UnboundLocationException unboundLocationException4 = unboundLocationException3;
                                                    unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1621, 17);
                                                    throw unboundLocationException4;
                                                }
                                            } else {
                                                obj2 = x2 ? Boolean.TRUE : Boolean.FALSE;
                                            }
                                        } catch (UnboundLocationException e4) {
                                            UnboundLocationException unboundLocationException5 = e4;
                                            UnboundLocationException unboundLocationException6 = unboundLocationException5;
                                            unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1620, 19);
                                            throw unboundLocationException6;
                                        }
                                    } else {
                                        obj2 = x ? Boolean.TRUE : Boolean.FALSE;
                                    }
                                    Object apply2 = applyToArgs2.apply2(apply3, applyToArgs3.apply3(obj4, valueOf, obj2));
                                    Object obj10 = Lit0;
                                    Object obj11 = string$Mnlist;
                                    while (true) {
                                        Object obj12 = obj11;
                                        Object len = obj10;
                                        if (C1245lists.isPair(obj12)) {
                                            AddOp addOp = AddOp.$Pl;
                                            Object obj13 = len;
                                            Object apply1 = C1245lists.car.apply1(obj12);
                                            Object obj14 = apply1;
                                            try {
                                                obj10 = addOp.apply2(obj13, Integer.valueOf(strings.stringLength((CharSequence) apply1)));
                                                obj11 = C1245lists.cdr.apply1(obj12);
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException2 = e5;
                                                Throwable th5 = th2;
                                                new WrongType(classCastException2, "string-length", 1, obj14);
                                                throw th5;
                                            }
                                        } else {
                                            try {
                                                try {
                                                    return applyToArgs.apply4(obj3, lList2, apply2, $PcFinishStringConcatenateReverse(len, string$Mnlist, loc$final.get(), loc$end.get()));
                                                } catch (UnboundLocationException e6) {
                                                    UnboundLocationException unboundLocationException7 = e6;
                                                    UnboundLocationException unboundLocationException8 = unboundLocationException7;
                                                    unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1627, 65);
                                                    throw unboundLocationException8;
                                                }
                                            } catch (UnboundLocationException e7) {
                                                UnboundLocationException unboundLocationException9 = e7;
                                                UnboundLocationException unboundLocationException10 = unboundLocationException9;
                                                unboundLocationException9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1627, 59);
                                                throw unboundLocationException10;
                                            }
                                        }
                                    }
                                } catch (UnboundLocationException e8) {
                                    UnboundLocationException unboundLocationException11 = e8;
                                    UnboundLocationException unboundLocationException12 = unboundLocationException11;
                                    unboundLocationException11.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1619, 21);
                                    throw unboundLocationException12;
                                }
                            } catch (ClassCastException e9) {
                                ClassCastException classCastException3 = e9;
                                Throwable th6 = th;
                                new WrongType(classCastException3, "string-length", 1, obj6);
                                throw th6;
                            }
                        } catch (UnboundLocationException e10) {
                            UnboundLocationException unboundLocationException13 = e10;
                            UnboundLocationException unboundLocationException14 = unboundLocationException13;
                            unboundLocationException13.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1618, 28);
                            throw unboundLocationException14;
                        }
                    } catch (UnboundLocationException e11) {
                        UnboundLocationException unboundLocationException15 = e11;
                        UnboundLocationException unboundLocationException16 = unboundLocationException15;
                        unboundLocationException15.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1618, 8);
                        throw unboundLocationException16;
                    }
                } catch (UnboundLocationException e12) {
                    UnboundLocationException unboundLocationException17 = e12;
                    UnboundLocationException unboundLocationException18 = unboundLocationException17;
                    unboundLocationException17.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1617, 55);
                    throw unboundLocationException18;
                }
            } catch (UnboundLocationException e13) {
                UnboundLocationException unboundLocationException19 = e13;
                UnboundLocationException unboundLocationException20 = unboundLocationException19;
                unboundLocationException19.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1617, 36);
                throw unboundLocationException20;
            }
        } catch (UnboundLocationException e14) {
            UnboundLocationException unboundLocationException21 = e14;
            UnboundLocationException unboundLocationException22 = unboundLocationException21;
            unboundLocationException21.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1617, 3);
            throw unboundLocationException22;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0327, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0328, code lost:
        r21 = r8;
        r8 = r21;
        r21.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1652, 56);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0338, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0339, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x033a, code lost:
        r21 = r8;
        r8 = r21;
        r21.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1652, 62);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x034a, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x017b, code lost:
        if (r12.apply2(r13, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r21))) != java.lang.Boolean.FALSE) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0186, code lost:
        if (r6 != false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0196, code lost:
        r12 = $PcFinishStringConcatenateReverse(r3, r4, loc$final.get(), loc$end.get());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringConcatenateReverse$SlShared$V(java.lang.Object r23, java.lang.Object[] r24) {
        /*
            r0 = r23
            r1 = r24
            r8 = r1
            r9 = 0
            gnu.lists.LList r8 = gnu.lists.LList.makeList(r8, r9)
            r21 = r8
            r8 = r21
            r9 = r21
            r3 = r9
            r2 = r8
            gnu.kawa.functions.ApplyToArgs r8 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r9 = loc$let$Mnoptionals$St
            java.lang.Object r9 = r9.get()     // Catch:{ UnboundLocationException -> 0x019c }
            r10 = r2
            gnu.kawa.functions.ApplyToArgs r11 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r12 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r13 = loc$final
            java.lang.Object r13 = r13.get()     // Catch:{ UnboundLocationException -> 0x01ad }
            java.lang.String r14 = ""
            gnu.mapping.Location r15 = loc$final
            java.lang.Object r15 = r15.get()     // Catch:{ UnboundLocationException -> 0x01bf }
            boolean r15 = kawa.lib.strings.isString(r15)
            if (r15 == 0) goto L_0x00f4
            java.lang.Boolean r15 = java.lang.Boolean.TRUE
        L_0x0036:
            java.lang.Object r12 = r12.apply3(r13, r14, r15)
            gnu.kawa.functions.ApplyToArgs r13 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r14 = loc$end
            java.lang.Object r14 = r14.get()     // Catch:{ UnboundLocationException -> 0x01d1 }
            gnu.mapping.Location r15 = loc$final
            java.lang.Object r15 = r15.get()     // Catch:{ UnboundLocationException -> 0x01e3 }
            r21 = r15
            r15 = r21
            r16 = r21
            r3 = r16
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ ClassCastException -> 0x01f5 }
            int r15 = kawa.lib.strings.stringLength(r15)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            gnu.mapping.Location r16 = loc$end
            java.lang.Object r16 = r16.get()     // Catch:{ UnboundLocationException -> 0x0213 }
            boolean r16 = kawa.lib.numbers.isInteger(r16)
            r3 = r16
            r16 = r3
            if (r16 == 0) goto L_0x0102
            gnu.mapping.Location r16 = loc$end
            java.lang.Object r16 = r16.get()     // Catch:{ UnboundLocationException -> 0x0225 }
            boolean r16 = kawa.lib.numbers.isExact(r16)
            r4 = r16
            r16 = r4
            if (r16 == 0) goto L_0x00f8
            gnu.kawa.functions.NumberCompare r16 = kawa.standard.Scheme.numLEq
            gnu.math.IntNum r17 = Lit0
            gnu.mapping.Location r18 = loc$end
            java.lang.Object r18 = r18.get()     // Catch:{ UnboundLocationException -> 0x0237 }
            gnu.mapping.Location r19 = loc$final
            java.lang.Object r19 = r19.get()     // Catch:{ UnboundLocationException -> 0x0249 }
            r21 = r19
            r19 = r21
            r20 = r21
            r5 = r20
            java.lang.CharSequence r19 = (java.lang.CharSequence) r19     // Catch:{ ClassCastException -> 0x025b }
            int r19 = kawa.lib.strings.stringLength(r19)
            java.lang.Integer r19 = java.lang.Integer.valueOf(r19)
            java.lang.Object r16 = r16.apply3(r17, r18, r19)
        L_0x00a0:
            java.lang.Object r13 = r13.apply3(r14, r15, r16)
            java.lang.Object r11 = r11.apply2(r12, r13)
            gnu.math.IntNum r12 = Lit0
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            r14 = r0
            r5 = r14
            r4 = r13
            r3 = r12
        L_0x00b0:
            r12 = r5
            boolean r12 = kawa.lib.C1245lists.isPair(r12)
            if (r12 == 0) goto L_0x010e
            gnu.expr.GenericProc r12 = kawa.lib.C1245lists.car
            r13 = r5
            java.lang.Object r12 = r12.apply1(r13)
            r21 = r12
            r12 = r21
            r13 = r21
            r7 = r13
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12     // Catch:{ ClassCastException -> 0x0279 }
            int r12 = kawa.lib.strings.stringLength(r12)
            r6 = r12
            gnu.kawa.functions.AddOp r12 = gnu.kawa.functions.AddOp.$Pl
            r13 = r3
            r14 = r6
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            java.lang.Object r12 = r12.apply2(r13, r14)
            r13 = r4
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
            if (r13 != r14) goto L_0x00e8
            r13 = r6
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            boolean r13 = kawa.lib.numbers.isZero(r13)
            if (r13 == 0) goto L_0x010c
        L_0x00e8:
            r13 = r4
        L_0x00e9:
            gnu.expr.GenericProc r14 = kawa.lib.C1245lists.cdr
            r15 = r5
            java.lang.Object r14 = r14.apply1(r15)
            r5 = r14
            r4 = r13
            r3 = r12
            goto L_0x00b0
        L_0x00f4:
            java.lang.Boolean r15 = java.lang.Boolean.FALSE
            goto L_0x0036
        L_0x00f8:
            r16 = r4
            if (r16 == 0) goto L_0x00ff
            java.lang.Boolean r16 = java.lang.Boolean.TRUE
            goto L_0x00a0
        L_0x00ff:
            java.lang.Boolean r16 = java.lang.Boolean.FALSE
            goto L_0x00a0
        L_0x0102:
            r16 = r3
            if (r16 == 0) goto L_0x0109
            java.lang.Boolean r16 = java.lang.Boolean.TRUE
            goto L_0x00a0
        L_0x0109:
            java.lang.Boolean r16 = java.lang.Boolean.FALSE
            goto L_0x00a0
        L_0x010c:
            r13 = r5
            goto L_0x00e9
        L_0x010e:
            r12 = r3
            r21 = r12
            r12 = r21
            r13 = r21
            r6 = r13
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ ClassCastException -> 0x0297 }
            boolean r12 = kawa.lib.numbers.isZero(r12)
            if (r12 == 0) goto L_0x0143
            gnu.mapping.Location r12 = loc$final
            java.lang.Object r12 = r12.get()     // Catch:{ UnboundLocationException -> 0x02b5 }
            gnu.math.IntNum r13 = Lit0
            r14 = 1
            java.lang.Object[] r14 = new java.lang.Object[r14]
            r21 = r14
            r14 = r21
            r15 = r21
            r16 = 0
            gnu.mapping.Location r17 = loc$end
            java.lang.Object r17 = r17.get()     // Catch:{ UnboundLocationException -> 0x02c7 }
            r15[r16] = r17
            java.lang.Object r12 = substring$SlShared$V(r12, r13, r14)
        L_0x013d:
            java.lang.Object r8 = r8.apply4(r9, r10, r11, r12)
            r0 = r8
            return r0
        L_0x0143:
            gnu.mapping.Location r12 = loc$end
            java.lang.Object r12 = r12.get()     // Catch:{ UnboundLocationException -> 0x02d9 }
            r21 = r12
            r12 = r21
            r13 = r21
            r7 = r13
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ ClassCastException -> 0x02eb }
            boolean r12 = kawa.lib.numbers.isZero(r12)
            r6 = r12
            r12 = r6
            if (r12 == 0) goto L_0x0185
            gnu.kawa.functions.NumberCompare r12 = kawa.standard.Scheme.numEqu
            r13 = r3
            gnu.expr.GenericProc r14 = kawa.lib.C1245lists.car
            r15 = r4
            java.lang.Object r14 = r14.apply1(r15)
            r21 = r14
            r14 = r21
            r15 = r21
            r7 = r15
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14     // Catch:{ ClassCastException -> 0x0309 }
            int r14 = kawa.lib.strings.stringLength(r14)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            java.lang.Object r12 = r12.apply2(r13, r14)
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
            if (r12 == r13) goto L_0x0189
        L_0x017d:
            gnu.expr.GenericProc r12 = kawa.lib.C1245lists.car
            r13 = r4
            java.lang.Object r12 = r12.apply1(r13)
            goto L_0x013d
        L_0x0185:
            r12 = r6
            if (r12 == 0) goto L_0x0189
            goto L_0x017d
        L_0x0189:
            r12 = r3
            r13 = r4
            gnu.mapping.Location r14 = loc$final
            java.lang.Object r14 = r14.get()     // Catch:{ UnboundLocationException -> 0x0327 }
            gnu.mapping.Location r15 = loc$end
            java.lang.Object r15 = r15.get()     // Catch:{ UnboundLocationException -> 0x0339 }
            java.lang.Object r12 = $PcFinishStringConcatenateReverse(r12, r13, r14, r15)
            goto L_0x013d
        L_0x019c:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1630(0x65e, float:2.284E-42)
            r12 = 3
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x01ad:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1630(0x65e, float:2.284E-42)
            r12 = 36
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x01bf:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1630(0x65e, float:2.284E-42)
            r12 = 55
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x01d1:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1631(0x65f, float:2.286E-42)
            r12 = 8
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x01e3:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1631(0x65f, float:2.286E-42)
            r12 = 28
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x01f5:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r21 = r8
            r22 = r9
            r8 = r22
            r9 = r21
            r10 = r22
            r21 = r9
            r22 = r10
            r9 = r22
            r10 = r21
            java.lang.String r11 = "string-length"
            r12 = 1
            r13 = r3
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x0213:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1632(0x660, float:2.287E-42)
            r12 = 21
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x0225:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1633(0x661, float:2.288E-42)
            r12 = 19
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x0237:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1634(0x662, float:2.29E-42)
            r12 = 17
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x0249:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1634(0x662, float:2.29E-42)
            r12 = 36
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x025b:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r21 = r8
            r22 = r9
            r8 = r22
            r9 = r21
            r10 = r22
            r21 = r9
            r22 = r10
            r9 = r22
            r10 = r21
            java.lang.String r11 = "string-length"
            r12 = 1
            r13 = r5
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x0279:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r21 = r8
            r22 = r9
            r8 = r22
            r9 = r21
            r10 = r22
            r21 = r9
            r22 = r10
            r9 = r22
            r10 = r21
            java.lang.String r11 = "string-length"
            r12 = 1
            r13 = r7
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x0297:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r21 = r8
            r22 = r9
            r8 = r22
            r9 = r21
            r10 = r22
            r21 = r9
            r22 = r10
            r9 = r22
            r10 = r21
            java.lang.String r11 = "zero?"
            r12 = 1
            r13 = r6
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x02b5:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1645(0x66d, float:2.305E-42)
            r12 = 41
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x02c7:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1645(0x66d, float:2.305E-42)
            r12 = 49
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x02d9:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1649(0x671, float:2.311E-42)
            r12 = 16
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x02eb:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r21 = r8
            r22 = r9
            r8 = r22
            r9 = r21
            r10 = r22
            r21 = r9
            r22 = r10
            r9 = r22
            r10 = r21
            java.lang.String r11 = "zero?"
            r12 = 1
            r13 = r7
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x0309:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r21 = r8
            r22 = r9
            r8 = r22
            r9 = r21
            r10 = r22
            r21 = r9
            r22 = r10
            r9 = r22
            r10 = r21
            java.lang.String r11 = "string-length"
            r12 = 1
            r13 = r7
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        L_0x0327:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1652(0x674, float:2.315E-42)
            r12 = 56
            r9.setLine(r10, r11, r12)
            throw r8
        L_0x0339:
            r8 = move-exception
            r21 = r8
            r8 = r21
            r9 = r21
            java.lang.String r10 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r11 = 1652(0x674, float:2.315E-42)
            r12 = 62
            r9.setLine(r10, r11, r12)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringConcatenateReverse$SlShared$V(java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public static Object $PcFinishStringConcatenateReverse(Object obj, Object obj2, Object obj3, Object obj4) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Object len = obj;
        Object string$Mnlist = obj2;
        Object obj5 = obj3;
        Object end = obj4;
        Object apply2 = AddOp.$Pl.apply2(end, len);
        Object obj6 = apply2;
        try {
            Object ans = strings.makeString(((Number) apply2).intValue());
            CharSequence charSequence = ans;
            Object obj7 = len;
            Object obj8 = obj7;
            try {
                int intValue = ((Number) obj7).intValue();
                Object obj9 = obj5;
                Object obj10 = obj9;
                try {
                    CharSequence charSequence2 = (CharSequence) obj9;
                    Object obj11 = end;
                    Object obj12 = obj11;
                    try {
                        Object $PcStringCopy$Ex = $PcStringCopy$Ex(charSequence, intValue, charSequence2, 0, ((Number) obj11).intValue());
                        Object obj13 = len;
                        Object obj14 = string$Mnlist;
                        while (true) {
                            Object obj15 = obj14;
                            Object i = obj13;
                            if (!C1245lists.isPair(obj15)) {
                                return ans;
                            }
                            Object s = C1245lists.car.apply1(obj15);
                            Object lis = C1245lists.cdr.apply1(obj15);
                            Object obj16 = s;
                            Object obj17 = obj16;
                            try {
                                int slen = strings.stringLength((CharSequence) obj16);
                                Object i2 = AddOp.$Mn.apply2(i, Integer.valueOf(slen));
                                CharSequence charSequence3 = ans;
                                Object obj18 = i2;
                                Object obj19 = obj18;
                                try {
                                    int intValue2 = ((Number) obj18).intValue();
                                    Object obj20 = s;
                                    Object obj21 = obj20;
                                    try {
                                        Object $PcStringCopy$Ex2 = $PcStringCopy$Ex(charSequence3, intValue2, (CharSequence) obj20, 0, slen);
                                        obj13 = i2;
                                        obj14 = lis;
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th8 = th7;
                                        new WrongType(classCastException, "%string-copy!", 2, obj21);
                                        throw th8;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th9 = th6;
                                    new WrongType(classCastException2, "%string-copy!", 1, obj19);
                                    throw th9;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th10 = th5;
                                new WrongType(classCastException3, "string-length", 1, obj17);
                                throw th10;
                            }
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th11 = th4;
                        new WrongType(classCastException4, "%string-copy!", 4, obj12);
                        throw th11;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th12 = th3;
                    new WrongType(classCastException5, "%string-copy!", 2, obj10);
                    throw th12;
                }
            } catch (ClassCastException e6) {
                ClassCastException classCastException6 = e6;
                Throwable th13 = th2;
                new WrongType(classCastException6, "%string-copy!", 1, obj8);
                throw th13;
            }
        } catch (ClassCastException e7) {
            ClassCastException classCastException7 = e7;
            Throwable th14 = th;
            new WrongType(classCastException7, "make-string", 1, obj6);
            throw th14;
        }
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        switch (moduleMethod2.selector) {
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN:
                try {
                    try {
                        try {
                            return $PcCheckBounds(obj5, (CharSequence) obj6, ((Number) obj7).intValue(), ((Number) obj8).intValue());
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th7 = th6;
                            new WrongType(classCastException, "%check-bounds", 4, obj8);
                            throw th7;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th8 = th5;
                        new WrongType(classCastException2, "%check-bounds", 3, obj7);
                        throw th8;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th9 = th4;
                    new WrongType(classCastException3, "%check-bounds", 2, obj6);
                    throw th9;
                }
            case 198:
                return checkSubstringSpec(obj5, obj6, obj7, obj8);
            case HttpStatus.SC_NO_CONTENT /*204*/:
                return $PcStringMap(obj5, obj6, obj7, obj8);
            case HttpStatus.SC_PARTIAL_CONTENT /*206*/:
                return $PcStringMap$Ex(obj5, obj6, obj7, obj8);
            case 299:
                try {
                    try {
                        try {
                            return stringCopy$Ex(obj5, ((Number) obj6).intValue(), (CharSequence) obj7, ((Number) obj8).intValue());
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th10 = th3;
                            new WrongType(classCastException4, "string-copy!", 4, obj8);
                            throw th10;
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th11 = th2;
                        new WrongType(classCastException5, "string-copy!", 3, obj7);
                        throw th11;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th12 = th;
                    new WrongType(classCastException6, "string-copy!", 2, obj6);
                    throw th12;
                }
            case 319:
                return $PcFinishStringConcatenateReverse(obj5, obj6, obj7, obj8);
            default:
                return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }
    }

    public static Object stringReplace$V(Object s1, Object s2, Object start1, Object end1, Object[] argsArray) {
        frame92 frame922;
        new frame92();
        frame92 frame923 = frame922;
        frame923.f225s1 = s1;
        frame923.f226s2 = s2;
        frame923.start1 = start1;
        frame923.end1 = end1;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame923.maybe$Mnstart$Plend = makeList;
        Object checkSubstringSpec = checkSubstringSpec(string$Mnreplace, frame923.f225s1, frame923.start1, frame923.end1);
        return call_with_values.callWithValues(frame923.lambda$Fn206, frame923.lambda$Fn207);
    }

    /* compiled from: srfi13.scm */
    public class frame92 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn206;
        final ModuleMethod lambda$Fn207;
        LList maybe$Mnstart$Plend;

        /* renamed from: s1 */
        Object f225s1;

        /* renamed from: s2 */
        Object f226s2;
        Object start1;

        public frame92() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 181, (Object) null, 0);
            this.lambda$Fn206 = moduleMethod;
            new ModuleMethod(this, 182, (Object) null, 8194);
            this.lambda$Fn207 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 181 ? lambda206() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 182 ? lambda207(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 181) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 182) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda206() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreplace, this.f226s2, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda207(Object obj, Object obj2) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Throwable th11;
            Object start2 = obj;
            Object end2 = obj2;
            Object obj3 = this.f225s1;
            Object obj4 = obj3;
            try {
                int slen1 = strings.stringLength((CharSequence) obj3);
                Object sublen2 = AddOp.$Mn.apply2(end2, start2);
                Object apply2 = AddOp.$Pl.apply2(AddOp.$Mn.apply2(Integer.valueOf(slen1), AddOp.$Mn.apply2(this.end1, this.start1)), sublen2);
                Object obj5 = apply2;
                try {
                    CharSequence ans = strings.makeString(((Number) apply2).intValue());
                    CharSequence charSequence = ans;
                    Object obj6 = this.f225s1;
                    Object obj7 = obj6;
                    try {
                        CharSequence charSequence2 = (CharSequence) obj6;
                        Object obj8 = this.start1;
                        Object obj9 = obj8;
                        try {
                            Object $PcStringCopy$Ex = srfi13.$PcStringCopy$Ex(charSequence, 0, charSequence2, 0, ((Number) obj8).intValue());
                            CharSequence charSequence3 = ans;
                            Object obj10 = this.start1;
                            Object obj11 = obj10;
                            try {
                                int intValue = ((Number) obj10).intValue();
                                Object obj12 = this.f226s2;
                                Object obj13 = obj12;
                                try {
                                    CharSequence charSequence4 = (CharSequence) obj12;
                                    Object obj14 = start2;
                                    Object obj15 = obj14;
                                    try {
                                        int intValue2 = ((Number) obj14).intValue();
                                        Object obj16 = end2;
                                        Object obj17 = obj16;
                                        try {
                                            Object $PcStringCopy$Ex2 = srfi13.$PcStringCopy$Ex(charSequence3, intValue, charSequence4, intValue2, ((Number) obj16).intValue());
                                            CharSequence charSequence5 = ans;
                                            Object apply22 = AddOp.$Pl.apply2(this.start1, sublen2);
                                            Object obj18 = apply22;
                                            try {
                                                int intValue3 = ((Number) apply22).intValue();
                                                Object obj19 = this.f225s1;
                                                Object obj20 = obj19;
                                                try {
                                                    CharSequence charSequence6 = (CharSequence) obj19;
                                                    Object obj21 = this.end1;
                                                    Object obj22 = obj21;
                                                    try {
                                                        Object $PcStringCopy$Ex3 = srfi13.$PcStringCopy$Ex(charSequence5, intValue3, charSequence6, ((Number) obj21).intValue(), slen1);
                                                        return ans;
                                                    } catch (ClassCastException e) {
                                                        ClassCastException classCastException = e;
                                                        Throwable th12 = th11;
                                                        new WrongType(classCastException, "%string-copy!", 3, obj22);
                                                        throw th12;
                                                    }
                                                } catch (ClassCastException e2) {
                                                    ClassCastException classCastException2 = e2;
                                                    Throwable th13 = th10;
                                                    new WrongType(classCastException2, "%string-copy!", 2, obj20);
                                                    throw th13;
                                                }
                                            } catch (ClassCastException e3) {
                                                ClassCastException classCastException3 = e3;
                                                Throwable th14 = th9;
                                                new WrongType(classCastException3, "%string-copy!", 1, obj18);
                                                throw th14;
                                            }
                                        } catch (ClassCastException e4) {
                                            ClassCastException classCastException4 = e4;
                                            Throwable th15 = th8;
                                            new WrongType(classCastException4, "%string-copy!", 4, obj17);
                                            throw th15;
                                        }
                                    } catch (ClassCastException e5) {
                                        ClassCastException classCastException5 = e5;
                                        Throwable th16 = th7;
                                        new WrongType(classCastException5, "%string-copy!", 3, obj15);
                                        throw th16;
                                    }
                                } catch (ClassCastException e6) {
                                    ClassCastException classCastException6 = e6;
                                    Throwable th17 = th6;
                                    new WrongType(classCastException6, "%string-copy!", 2, obj13);
                                    throw th17;
                                }
                            } catch (ClassCastException e7) {
                                ClassCastException classCastException7 = e7;
                                Throwable th18 = th5;
                                new WrongType(classCastException7, "%string-copy!", 1, obj11);
                                throw th18;
                            }
                        } catch (ClassCastException e8) {
                            ClassCastException classCastException8 = e8;
                            Throwable th19 = th4;
                            new WrongType(classCastException8, "%string-copy!", 4, obj9);
                            throw th19;
                        }
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th20 = th3;
                        new WrongType(classCastException9, "%string-copy!", 2, obj7);
                        throw th20;
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th21 = th2;
                    new WrongType(classCastException10, "make-string", 1, obj5);
                    throw th21;
                }
            } catch (ClassCastException e11) {
                ClassCastException classCastException11 = e11;
                Throwable th22 = th;
                new WrongType(classCastException11, "string-length", 1, obj4);
                throw th22;
            }
        }
    }

    public static Object stringTokenize$V(Object s, Object[] argsArray) {
        frame93 frame932;
        new frame93();
        frame93 frame933 = frame932;
        frame933.f227s = s;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList token$Mnchars$Plstart$Plend = makeList;
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), token$Mnchars$Plstart$Plend, Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply3(loc$token$Mnchars.get(), GetNamedPart.getNamedPart.apply2(loc$char$Mnset.get(), Lit14), Scheme.applyToArgs.apply2(loc$char$Mnset$Qu.get(), loc$token$Mnchars.get())), loc$rest.get()), call_with_values.callWithValues(frame933.lambda$Fn208, frame933.lambda$Fn209));
                            } catch (UnboundLocationException e) {
                                UnboundLocationException unboundLocationException = e;
                                UnboundLocationException unboundLocationException2 = unboundLocationException;
                                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 75);
                                throw unboundLocationException2;
                            }
                        } catch (UnboundLocationException e2) {
                            UnboundLocationException unboundLocationException3 = e2;
                            UnboundLocationException unboundLocationException4 = unboundLocationException3;
                            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 61);
                            throw unboundLocationException4;
                        }
                    } catch (UnboundLocationException e3) {
                        UnboundLocationException unboundLocationException5 = e3;
                        UnboundLocationException unboundLocationException6 = unboundLocationException5;
                        unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 50);
                        throw unboundLocationException6;
                    }
                } catch (UnboundLocationException e4) {
                    UnboundLocationException unboundLocationException7 = e4;
                    UnboundLocationException unboundLocationException8 = unboundLocationException7;
                    unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 33);
                    throw unboundLocationException8;
                }
            } catch (UnboundLocationException e5) {
                UnboundLocationException unboundLocationException9 = e5;
                UnboundLocationException unboundLocationException10 = unboundLocationException9;
                unboundLocationException9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 20);
                throw unboundLocationException10;
            }
        } catch (UnboundLocationException e6) {
            UnboundLocationException unboundLocationException11 = e6;
            UnboundLocationException unboundLocationException12 = unboundLocationException11;
            unboundLocationException11.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1694, 3);
            throw unboundLocationException12;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame93 extends ModuleBody {
        final ModuleMethod lambda$Fn208;
        final ModuleMethod lambda$Fn209;

        /* renamed from: s */
        Object f227s;

        public frame93() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 183, (Object) null, 0);
            this.lambda$Fn208 = moduleMethod;
            new ModuleMethod(this, 184, (Object) null, 8194);
            this.lambda$Fn209 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 183 ? lambda208() : super.apply0(moduleMethod2);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 184 ? lambda209(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 183) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 184) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda208() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntokenize, this.f227s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1696, 57);
                throw unboundLocationException2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda209(Object obj, Object end) {
            Throwable th;
            Object obj2;
            LList lList;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Object start = obj;
            Object obj3 = end;
            LList lList2 = LList.Empty;
            while (true) {
                LList lList3 = lList2;
                Object i = obj3;
                Object apply2 = Scheme.numLss.apply2(start, i);
                Object obj4 = apply2;
                try {
                    boolean x = ((Boolean) apply2).booleanValue();
                    if (x) {
                        Object obj5 = this.f227s;
                        try {
                            Object obj6 = srfi13.loc$token$Mnchars.get();
                            Object[] objArr = new Object[2];
                            objArr[0] = start;
                            Object[] objArr2 = objArr;
                            objArr2[1] = i;
                            obj2 = srfi13.stringIndexRight$V(obj5, obj6, objArr2);
                        } catch (UnboundLocationException e) {
                            UnboundLocationException unboundLocationException = e;
                            UnboundLocationException unboundLocationException2 = unboundLocationException;
                            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1698, 48);
                            throw unboundLocationException2;
                        }
                    } else {
                        obj2 = x ? Boolean.TRUE : Boolean.FALSE;
                    }
                    Object temp = obj2;
                    if (temp == Boolean.FALSE) {
                        lList = lList3;
                        break;
                    }
                    Object tend = AddOp.$Pl.apply2(srfi13.Lit1, temp);
                    Object obj7 = this.f227s;
                    try {
                        Object obj8 = srfi13.loc$token$Mnchars.get();
                        Object[] objArr3 = new Object[2];
                        objArr3[0] = start;
                        Object[] objArr4 = objArr3;
                        objArr4[1] = temp;
                        Object temp2 = srfi13.stringSkipRight$V(obj7, obj8, objArr4);
                        if (temp2 != Boolean.FALSE) {
                            obj3 = temp2;
                            Object obj9 = this.f227s;
                            Object obj10 = obj9;
                            try {
                                CharSequence charSequence = (CharSequence) obj9;
                                Object apply22 = AddOp.$Pl.apply2(srfi13.Lit1, temp2);
                                Object obj11 = apply22;
                                try {
                                    int intValue = ((Number) apply22).intValue();
                                    Object obj12 = tend;
                                    Object obj13 = obj12;
                                    try {
                                        lList2 = C1245lists.cons(strings.substring(charSequence, intValue, ((Number) obj12).intValue()), lList3);
                                    } catch (ClassCastException e2) {
                                        ClassCastException classCastException = e2;
                                        Throwable th8 = th7;
                                        new WrongType(classCastException, "substring", 3, obj13);
                                        throw th8;
                                    }
                                } catch (ClassCastException e3) {
                                    ClassCastException classCastException2 = e3;
                                    Throwable th9 = th6;
                                    new WrongType(classCastException2, "substring", 2, obj11);
                                    throw th9;
                                }
                            } catch (ClassCastException e4) {
                                ClassCastException classCastException3 = e4;
                                Throwable th10 = th5;
                                new WrongType(classCastException3, "substring", 1, obj10);
                                throw th10;
                            }
                        } else {
                            Object obj14 = this.f227s;
                            Object obj15 = obj14;
                            try {
                                CharSequence charSequence2 = (CharSequence) obj14;
                                Object obj16 = start;
                                Object obj17 = obj16;
                                try {
                                    int intValue2 = ((Number) obj16).intValue();
                                    Object obj18 = tend;
                                    Object obj19 = obj18;
                                    try {
                                        lList = C1245lists.cons(strings.substring(charSequence2, intValue2, ((Number) obj18).intValue()), lList3);
                                        break;
                                    } catch (ClassCastException e5) {
                                        ClassCastException classCastException4 = e5;
                                        Throwable th11 = th4;
                                        new WrongType(classCastException4, "substring", 3, obj19);
                                        throw th11;
                                    }
                                } catch (ClassCastException e6) {
                                    ClassCastException classCastException5 = e6;
                                    Throwable th12 = th3;
                                    new WrongType(classCastException5, "substring", 2, obj17);
                                    throw th12;
                                }
                            } catch (ClassCastException e7) {
                                ClassCastException classCastException6 = e7;
                                Throwable th13 = th2;
                                new WrongType(classCastException6, "substring", 1, obj15);
                                throw th13;
                            }
                        }
                    } catch (UnboundLocationException e8) {
                        UnboundLocationException unboundLocationException3 = e8;
                        UnboundLocationException unboundLocationException4 = unboundLocationException3;
                        unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_BAD_VALUE_FOR_TEXT_RECEIVING, 34);
                        throw unboundLocationException4;
                    }
                } catch (ClassCastException e9) {
                    ClassCastException classCastException7 = e9;
                    Throwable th14 = th;
                    new WrongType(classCastException7, "x", -2, obj4);
                    throw th14;
                }
            }
            return lList;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame94 extends ModuleBody {
        Object from;
        final ModuleMethod lambda$Fn211;
        final ModuleMethod lambda$Fn212;
        final ModuleMethod lambda$Fn213;
        final ModuleMethod lambda$Fn214;
        final ModuleMethod lambda$Fn215;
        LList maybe$Mnto$Plstart$Plend;

        /* renamed from: s */
        Object f228s;

        public frame94() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            ModuleMethod moduleMethod4;
            ModuleMethod moduleMethod5;
            new ModuleMethod(this, 185, (Object) null, 0);
            this.lambda$Fn212 = moduleMethod;
            new ModuleMethod(this, 186, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod6 = moduleMethod2;
            moduleMethod6.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1744");
            this.lambda$Fn214 = moduleMethod6;
            new ModuleMethod(this, 187, (Object) null, 8194);
            this.lambda$Fn213 = moduleMethod3;
            new ModuleMethod(this, 188, (Object) null, 0);
            this.lambda$Fn211 = moduleMethod4;
            new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG, (Object) null, 12291);
            ModuleMethod moduleMethod7 = moduleMethod5;
            moduleMethod7.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1740");
            this.lambda$Fn215 = moduleMethod7;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            switch (moduleMethod2.selector) {
                case 185:
                    return lambda212();
                case 188:
                    return lambda211();
                default:
                    return super.apply0(moduleMethod2);
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 187 ? lambda213(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 185:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 188:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod2, callContext2);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 187) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        static boolean lambda210(Object obj) {
            Object val = obj;
            boolean x = numbers.isInteger(val);
            return x ? numbers.isExact(val) : x;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            if (moduleMethod2.selector == 189) {
                return lambda215(obj4, obj5, obj6);
            }
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        /* access modifiers changed from: package-private */
        public Object lambda215(Object obj, Object obj2, Object obj3) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Object obj4;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Throwable th11;
            Object to = obj;
            Object start = obj2;
            Object end = obj3;
            Object apply2 = AddOp.$Mn.apply2(end, start);
            Object anslen = AddOp.$Mn.apply2(to, this.from);
            Object slen = apply2;
            Object obj5 = anslen;
            Object obj6 = obj5;
            try {
                if (numbers.isZero((Number) obj5)) {
                    obj4 = "";
                } else {
                    Object obj7 = slen;
                    Object obj8 = obj7;
                    try {
                        if (numbers.isZero((Number) obj7)) {
                            Object[] objArr = new Object[6];
                            objArr[0] = srfi13.xsubstring;
                            Object[] objArr2 = objArr;
                            objArr2[1] = this.f228s;
                            Object[] objArr3 = objArr2;
                            objArr3[2] = this.from;
                            Object[] objArr4 = objArr3;
                            objArr4[3] = to;
                            Object[] objArr5 = objArr4;
                            objArr5[4] = start;
                            Object[] objArr6 = objArr5;
                            objArr6[5] = end;
                            obj4 = misc.error$V("Cannot replicate empty (sub)string", objArr6);
                        } else if (Scheme.numEqu.apply2(srfi13.Lit1, slen) != Boolean.FALSE) {
                            Object obj9 = anslen;
                            Object obj10 = obj9;
                            try {
                                int intValue = ((Number) obj9).intValue();
                                Object obj11 = this.f228s;
                                Object obj12 = obj11;
                                try {
                                    CharSequence charSequence = (CharSequence) obj11;
                                    Object obj13 = start;
                                    Object obj14 = obj13;
                                    try {
                                        obj4 = strings.makeString(intValue, Char.make(strings.stringRef(charSequence, ((Number) obj13).intValue())));
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th12 = th11;
                                        new WrongType(classCastException, "string-ref", 2, obj14);
                                        throw th12;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th13 = th10;
                                    new WrongType(classCastException2, "string-ref", 1, obj12);
                                    throw th13;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th14 = th9;
                                new WrongType(classCastException3, "make-string", 1, obj10);
                                throw th14;
                            }
                        } else {
                            Object apply22 = DivideOp.$Sl.apply2(this.from, slen);
                            Object obj15 = apply22;
                            try {
                                double doubleValue = numbers.floor(LangObjType.coerceRealNum(apply22)).doubleValue();
                                Object apply23 = DivideOp.$Sl.apply2(to, slen);
                                Object obj16 = apply23;
                                try {
                                    if (doubleValue == numbers.floor(LangObjType.coerceRealNum(apply23)).doubleValue()) {
                                        Object obj17 = this.f228s;
                                        Object obj18 = obj17;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj17;
                                            Object apply24 = AddOp.$Pl.apply2(start, DivideOp.modulo.apply2(this.from, slen));
                                            Object obj19 = apply24;
                                            try {
                                                int intValue2 = ((Number) apply24).intValue();
                                                Object apply25 = AddOp.$Pl.apply2(start, DivideOp.modulo.apply2(to, slen));
                                                Object obj20 = apply25;
                                                try {
                                                    obj4 = strings.substring(charSequence2, intValue2, ((Number) apply25).intValue());
                                                } catch (ClassCastException e4) {
                                                    ClassCastException classCastException4 = e4;
                                                    Throwable th15 = th8;
                                                    new WrongType(classCastException4, "substring", 3, obj20);
                                                    throw th15;
                                                }
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException5 = e5;
                                                Throwable th16 = th7;
                                                new WrongType(classCastException5, "substring", 2, obj19);
                                                throw th16;
                                            }
                                        } catch (ClassCastException e6) {
                                            ClassCastException classCastException6 = e6;
                                            Throwable th17 = th6;
                                            new WrongType(classCastException6, "substring", 1, obj18);
                                            throw th17;
                                        }
                                    } else {
                                        Object obj21 = anslen;
                                        Object obj22 = obj21;
                                        try {
                                            CharSequence ans = strings.makeString(((Number) obj21).intValue());
                                            Object $PcMultispanRepcopy$Ex = srfi13.$PcMultispanRepcopy$Ex(ans, srfi13.Lit0, this.f228s, this.from, to, start, end);
                                            obj4 = ans;
                                        } catch (ClassCastException e7) {
                                            ClassCastException classCastException7 = e7;
                                            Throwable th18 = th5;
                                            new WrongType(classCastException7, "make-string", 1, obj22);
                                            throw th18;
                                        }
                                    }
                                } catch (ClassCastException e8) {
                                    ClassCastException classCastException8 = e8;
                                    Throwable th19 = th4;
                                    new WrongType(classCastException8, "floor", 1, obj16);
                                    throw th19;
                                }
                            } catch (ClassCastException e9) {
                                ClassCastException classCastException9 = e9;
                                Throwable th20 = th3;
                                new WrongType(classCastException9, "floor", 1, obj15);
                                throw th20;
                            }
                        }
                    } catch (ClassCastException e10) {
                        ClassCastException classCastException10 = e10;
                        Throwable th21 = th2;
                        new WrongType(classCastException10, "zero?", 1, obj8);
                        throw th21;
                    }
                }
                return obj4;
            } catch (ClassCastException e11) {
                ClassCastException classCastException11 = e11;
                Throwable th22 = th;
                new WrongType(classCastException11, "zero?", 1, obj6);
                throw th22;
            }
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 189) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda211() {
            Throwable th;
            Object values;
            if (C1245lists.isPair(this.maybe$Mnto$Plstart$Plend)) {
                values = call_with_values.callWithValues(this.lambda$Fn212, this.lambda$Fn213);
            } else {
                try {
                    Object apply4 = Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), strings.string$Qu, this.f228s, srfi13.xsubstring);
                    Object obj = apply4;
                    try {
                        int slen = strings.stringLength((CharSequence) apply4);
                        Object[] objArr = new Object[3];
                        objArr[0] = AddOp.$Pl.apply2(this.from, Integer.valueOf(slen));
                        Object[] objArr2 = objArr;
                        objArr2[1] = srfi13.Lit0;
                        Object[] objArr3 = objArr2;
                        objArr3[2] = Integer.valueOf(slen);
                        values = misc.values(objArr3);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "string-length", 1, obj);
                        throw th2;
                    }
                } catch (UnboundLocationException e2) {
                    UnboundLocationException unboundLocationException = e2;
                    UnboundLocationException unboundLocationException2 = unboundLocationException;
                    unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1749, 36);
                    throw unboundLocationException2;
                }
            }
            return values;
        }

        /* access modifiers changed from: package-private */
        public Object lambda212() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.xsubstring, this.f228s, C1245lists.cdr.apply1(this.maybe$Mnto$Plstart$Plend));
        }

        /* access modifiers changed from: package-private */
        public Object lambda213(Object obj, Object obj2) {
            Object start = obj;
            Object end = obj2;
            Object to = C1245lists.car.apply1(this.maybe$Mnto$Plstart$Plend);
            try {
                Object apply4 = Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), this.lambda$Fn214, to, srfi13.xsubstring);
                Object[] objArr = new Object[3];
                objArr[0] = to;
                Object[] objArr2 = objArr;
                objArr2[1] = start;
                Object[] objArr3 = objArr2;
                objArr3[2] = end;
                return misc.values(objArr3);
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1744, 6);
                throw unboundLocationException2;
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 186) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda214(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda214(Object obj) {
            boolean z;
            Object val = obj;
            boolean x = numbers.isInteger(val);
            if (x) {
                boolean x2 = numbers.isExact(val);
                z = x2 ? ((Boolean) Scheme.numLEq.apply2(this.from, val)).booleanValue() : x2;
            } else {
                z = x;
            }
            return z;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 186) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object xsubstring$V(Object s, Object from, Object[] argsArray) {
        frame94 frame942;
        new frame94();
        frame94 frame943 = frame942;
        frame943.f228s = s;
        frame943.from = from;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame943.maybe$Mnto$Plstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), lambda$Fn210, frame943.from, xsubstring);
            return call_with_values.callWithValues(frame943.lambda$Fn211, frame943.lambda$Fn215);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1738, 3);
            throw unboundLocationException2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame95 extends ModuleBody {
        final ModuleMethod lambda$Fn217;
        final ModuleMethod lambda$Fn218;
        final ModuleMethod lambda$Fn219;
        final ModuleMethod lambda$Fn221;
        LList maybe$Mnsto$Plstart$Plend;

        /* renamed from: s */
        Object f229s;
        Object sfrom;
        Object target;
        Object tstart;

        public frame95() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            ModuleMethod moduleMethod4;
            new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, (Object) null, 0);
            this.lambda$Fn218 = moduleMethod;
            new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, (Object) null, 8194);
            this.lambda$Fn219 = moduleMethod2;
            new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, (Object) null, 0);
            this.lambda$Fn217 = moduleMethod3;
            new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP, (Object) null, 12291);
            ModuleMethod moduleMethod5 = moduleMethod4;
            moduleMethod5.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1781");
            this.lambda$Fn221 = moduleMethod5;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            switch (moduleMethod2.selector) {
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK:
                    return lambda218();
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE:
                    return lambda217();
                default:
                    return super.apply0(moduleMethod2);
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            return moduleMethod2.selector == 191 ? lambda219(obj3, obj4) : super.apply2(moduleMethod2, obj3, obj4);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod2, callContext2);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 191) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }

        static boolean lambda216(Object obj) {
            Object val = obj;
            boolean x = numbers.isInteger(val);
            return x ? numbers.isExact(val) : x;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            if (moduleMethod2.selector == 193) {
                return lambda221(obj4, obj5, obj6);
            }
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        /* access modifiers changed from: package-private */
        public Object lambda221(Object obj, Object obj2, Object obj3) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Object $PcMultispanRepcopy$Ex;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Throwable th11;
            Object sto = obj;
            Object start = obj2;
            Object end = obj3;
            Object tocopy = AddOp.$Mn.apply2(sto, this.sfrom);
            Object tend = AddOp.$Pl.apply2(this.tstart, tocopy);
            Object slen = AddOp.$Mn.apply2(end, start);
            Object checkSubstringSpec = srfi13.checkSubstringSpec(srfi13.string$Mnxcopy$Ex, this.target, this.tstart, tend);
            Object obj4 = tocopy;
            Object obj5 = obj4;
            try {
                boolean x = numbers.isZero((Number) obj4);
                if (x) {
                    $PcMultispanRepcopy$Ex = x ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object obj6 = slen;
                    Object obj7 = obj6;
                    try {
                        if (numbers.isZero((Number) obj6)) {
                            Object[] objArr = new Object[8];
                            objArr[0] = srfi13.string$Mnxcopy$Ex;
                            Object[] objArr2 = objArr;
                            objArr2[1] = this.target;
                            Object[] objArr3 = objArr2;
                            objArr3[2] = this.tstart;
                            Object[] objArr4 = objArr3;
                            objArr4[3] = this.f229s;
                            Object[] objArr5 = objArr4;
                            objArr5[4] = this.sfrom;
                            Object[] objArr6 = objArr5;
                            objArr6[5] = sto;
                            Object[] objArr7 = objArr6;
                            objArr7[6] = start;
                            Object[] objArr8 = objArr7;
                            objArr8[7] = end;
                            $PcMultispanRepcopy$Ex = misc.error$V("Cannot replicate empty (sub)string", objArr8);
                        } else if (Scheme.numEqu.apply2(srfi13.Lit1, slen) != Boolean.FALSE) {
                            Object obj8 = this.target;
                            Object obj9 = this.f229s;
                            Object obj10 = obj9;
                            try {
                                CharSequence charSequence = (CharSequence) obj9;
                                Object obj11 = start;
                                Object obj12 = obj11;
                                try {
                                    Char make = Char.make(strings.stringRef(charSequence, ((Number) obj11).intValue()));
                                    Object[] objArr9 = new Object[2];
                                    objArr9[0] = this.tstart;
                                    Object[] objArr10 = objArr9;
                                    objArr10[1] = tend;
                                    $PcMultispanRepcopy$Ex = srfi13.stringFill$Ex$V(obj8, make, objArr10);
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th12 = th11;
                                    new WrongType(classCastException, "string-ref", 2, obj12);
                                    throw th12;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th13 = th10;
                                new WrongType(classCastException2, "string-ref", 1, obj10);
                                throw th13;
                            }
                        } else {
                            Object apply2 = DivideOp.$Sl.apply2(this.sfrom, slen);
                            Object obj13 = apply2;
                            try {
                                double doubleValue = numbers.floor(LangObjType.coerceRealNum(apply2)).doubleValue();
                                Object apply22 = DivideOp.$Sl.apply2(sto, slen);
                                Object obj14 = apply22;
                                try {
                                    if (doubleValue == numbers.floor(LangObjType.coerceRealNum(apply22)).doubleValue()) {
                                        Object obj15 = this.target;
                                        Object obj16 = obj15;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj15;
                                            Object obj17 = this.tstart;
                                            Object obj18 = obj17;
                                            try {
                                                int intValue = ((Number) obj17).intValue();
                                                Object obj19 = this.f229s;
                                                Object obj20 = obj19;
                                                try {
                                                    CharSequence charSequence3 = (CharSequence) obj19;
                                                    Object apply23 = AddOp.$Pl.apply2(start, DivideOp.modulo.apply2(this.sfrom, slen));
                                                    Object obj21 = apply23;
                                                    try {
                                                        int intValue2 = ((Number) apply23).intValue();
                                                        Object apply24 = AddOp.$Pl.apply2(start, DivideOp.modulo.apply2(sto, slen));
                                                        Object obj22 = apply24;
                                                        try {
                                                            $PcMultispanRepcopy$Ex = srfi13.$PcStringCopy$Ex(charSequence2, intValue, charSequence3, intValue2, ((Number) apply24).intValue());
                                                        } catch (ClassCastException e3) {
                                                            ClassCastException classCastException3 = e3;
                                                            Throwable th14 = th9;
                                                            new WrongType(classCastException3, "%string-copy!", 4, obj22);
                                                            throw th14;
                                                        }
                                                    } catch (ClassCastException e4) {
                                                        ClassCastException classCastException4 = e4;
                                                        Throwable th15 = th8;
                                                        new WrongType(classCastException4, "%string-copy!", 3, obj21);
                                                        throw th15;
                                                    }
                                                } catch (ClassCastException e5) {
                                                    ClassCastException classCastException5 = e5;
                                                    Throwable th16 = th7;
                                                    new WrongType(classCastException5, "%string-copy!", 2, obj20);
                                                    throw th16;
                                                }
                                            } catch (ClassCastException e6) {
                                                ClassCastException classCastException6 = e6;
                                                Throwable th17 = th6;
                                                new WrongType(classCastException6, "%string-copy!", 1, obj18);
                                                throw th17;
                                            }
                                        } catch (ClassCastException e7) {
                                            ClassCastException classCastException7 = e7;
                                            Throwable th18 = th5;
                                            new WrongType(classCastException7, "%string-copy!", 0, obj16);
                                            throw th18;
                                        }
                                    } else {
                                        $PcMultispanRepcopy$Ex = srfi13.$PcMultispanRepcopy$Ex(this.target, this.tstart, this.f229s, this.sfrom, sto, start, end);
                                    }
                                } catch (ClassCastException e8) {
                                    ClassCastException classCastException8 = e8;
                                    Throwable th19 = th4;
                                    new WrongType(classCastException8, "floor", 1, obj14);
                                    throw th19;
                                }
                            } catch (ClassCastException e9) {
                                ClassCastException classCastException9 = e9;
                                Throwable th20 = th3;
                                new WrongType(classCastException9, "floor", 1, obj13);
                                throw th20;
                            }
                        }
                    } catch (ClassCastException e10) {
                        ClassCastException classCastException10 = e10;
                        Throwable th21 = th2;
                        new WrongType(classCastException10, "zero?", 1, obj7);
                        throw th21;
                    }
                }
                return $PcMultispanRepcopy$Ex;
            } catch (ClassCastException e11) {
                ClassCastException classCastException11 = e11;
                Throwable th22 = th;
                new WrongType(classCastException11, "zero?", 1, obj5);
                throw th22;
            }
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 193) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda217() {
            Throwable th;
            Object values;
            if (C1245lists.isPair(this.maybe$Mnsto$Plstart$Plend)) {
                values = call_with_values.callWithValues(this.lambda$Fn218, this.lambda$Fn219);
            } else {
                Object obj = this.f229s;
                Object obj2 = obj;
                try {
                    int slen = strings.stringLength((CharSequence) obj);
                    Object[] objArr = new Object[3];
                    objArr[0] = AddOp.$Pl.apply2(this.sfrom, Integer.valueOf(slen));
                    Object[] objArr2 = objArr;
                    objArr2[1] = srfi13.Lit0;
                    Object[] objArr3 = objArr2;
                    objArr3[2] = Integer.valueOf(slen);
                    values = misc.values(objArr3);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "string-length", 1, obj2);
                    throw th2;
                }
            }
            return values;
        }

        /* access modifiers changed from: package-private */
        public Object lambda218() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnxcopy$Ex, this.f229s, C1245lists.cdr.apply1(this.maybe$Mnsto$Plstart$Plend));
        }

        /* access modifiers changed from: package-private */
        public Object lambda219(Object obj, Object obj2) {
            Object start = obj;
            Object end = obj2;
            Object sto = C1245lists.car.apply1(this.maybe$Mnsto$Plstart$Plend);
            try {
                Object apply4 = Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), srfi13.lambda$Fn220, sto, srfi13.string$Mnxcopy$Ex);
                Object[] objArr = new Object[3];
                objArr[0] = sto;
                Object[] objArr2 = objArr;
                objArr2[1] = start;
                Object[] objArr3 = objArr2;
                objArr3[2] = end;
                return misc.values(objArr3);
            } catch (UnboundLocationException e) {
                UnboundLocationException unboundLocationException = e;
                UnboundLocationException unboundLocationException2 = unboundLocationException;
                unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1785, 6);
                throw unboundLocationException2;
            }
        }

        static boolean lambda220(Object obj) {
            Object val = obj;
            boolean x = numbers.isInteger(val);
            return x ? numbers.isExact(val) : x;
        }
    }

    public static Object stringXcopy$Ex$V(Object target, Object tstart, Object s, Object sfrom, Object[] argsArray) {
        frame95 frame952;
        new frame95();
        frame95 frame953 = frame952;
        frame953.target = target;
        frame953.tstart = tstart;
        frame953.f229s = s;
        frame953.sfrom = sfrom;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame953.maybe$Mnsto$Plstart$Plend = makeList;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), lambda$Fn216, frame953.sfrom, string$Mnxcopy$Ex);
            return call_with_values.callWithValues(frame953.lambda$Fn217, frame953.lambda$Fn221);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1779, 3);
            throw unboundLocationException2;
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 199:
                return frame1.lambda5(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 209:
                return lambda17(obj2);
            case 211:
                return lambda18(obj2);
            case 217:
                return lambda27(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 239:
                return frame32.lambda72(obj2);
            case 240:
                return frame32.lambda73(obj2);
            case LispEscapeFormat.ESCAPE_ALL:
                return frame34.lambda78(obj2);
            case 244:
                return frame36.lambda83(obj2);
            case 245:
                return frame36.lambda84(obj2);
            case 247:
                return frame38.lambda89(obj2);
            case ComponentConstants.LISTVIEW_PREFERRED_WIDTH:
                return frame38.lambda90(obj2);
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                return frame40.lambda95(obj2);
            case Telnet.WONT /*252*/:
                return frame42.lambda100(obj2);
            case Telnet.DONT /*254*/:
                return frame44.lambda105(obj2);
            case 255:
                return frame44.lambda106(obj2);
            case InputDeviceCompat.SOURCE_KEYBOARD:
                return frame46.lambda111(obj2);
            case 259:
                return frame48.lambda116(obj2);
            case 260:
                return frame48.lambda117(obj2);
            case 262:
                return frame50.lambda122(obj2);
            case 263:
                return frame50.lambda123(obj2);
            case 265:
                return frame52.lambda128(obj2);
            case 267:
                return frame54.lambda133(obj2);
            case 271:
                return Integer.valueOf(frame57.lambda138(obj2));
            case 287:
                return frame71.lambda163(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 289:
                return frame72.lambda166(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED:
                return isStringNull(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED:
                return reverseList$To$String(obj2);
            case ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH:
                return stringConcatenate$SlShared(obj2);
            case 316:
                return stringConcatenate(obj2);
            case 322:
                return frame94.lambda210(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 324:
                return frame95.lambda216(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 325:
                return frame95.lambda220(obj2) ? Boolean.TRUE : Boolean.FALSE;
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static Object $PcMultispanRepcopy$Ex(Object obj, Object obj2, Object obj3, Object obj4, Object sto, Object obj5, Object obj6) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        Throwable th14;
        Throwable th15;
        Throwable th16;
        Object target = obj;
        Object tstart = obj2;
        Object s = obj3;
        Object sfrom = obj4;
        Object start = obj5;
        Object end = obj6;
        Object slen = AddOp.$Mn.apply2(end, start);
        Object i0 = AddOp.$Pl.apply2(start, DivideOp.modulo.apply2(sfrom, slen));
        Object total$Mnchars = AddOp.$Mn.apply2(sto, sfrom);
        Object obj7 = target;
        Object obj8 = obj7;
        try {
            CharSequence charSequence = (CharSequence) obj7;
            Object obj9 = tstart;
            Object obj10 = obj9;
            try {
                int intValue = ((Number) obj9).intValue();
                Object obj11 = s;
                Object obj12 = obj11;
                try {
                    CharSequence charSequence2 = (CharSequence) obj11;
                    Object obj13 = i0;
                    Object obj14 = obj13;
                    try {
                        int intValue2 = ((Number) obj13).intValue();
                        Object obj15 = end;
                        Object obj16 = obj15;
                        try {
                            Object $PcStringCopy$Ex = $PcStringCopy$Ex(charSequence, intValue, charSequence2, intValue2, ((Number) obj15).intValue());
                            Object ncopied = AddOp.$Mn.apply2(end, i0);
                            Object nspans = DivideOp.quotient.apply2(AddOp.$Mn.apply2(total$Mnchars, ncopied), slen);
                            Object apply2 = AddOp.$Pl.apply2(tstart, ncopied);
                            Object obj17 = nspans;
                            while (true) {
                                Object obj18 = obj17;
                                Object i = apply2;
                                Object obj19 = obj18;
                                Object obj20 = obj19;
                                try {
                                    if (!numbers.isZero((Number) obj19)) {
                                        Object obj21 = target;
                                        Object obj22 = obj21;
                                        try {
                                            CharSequence charSequence3 = (CharSequence) obj21;
                                            Object obj23 = i;
                                            Object obj24 = obj23;
                                            try {
                                                int intValue3 = ((Number) obj23).intValue();
                                                Object obj25 = s;
                                                Object obj26 = obj25;
                                                try {
                                                    CharSequence charSequence4 = (CharSequence) obj25;
                                                    Object obj27 = start;
                                                    Object obj28 = obj27;
                                                    try {
                                                        int intValue4 = ((Number) obj27).intValue();
                                                        Object obj29 = end;
                                                        Object obj30 = obj29;
                                                        try {
                                                            Object $PcStringCopy$Ex2 = $PcStringCopy$Ex(charSequence3, intValue3, charSequence4, intValue4, ((Number) obj29).intValue());
                                                            apply2 = AddOp.$Pl.apply2(i, slen);
                                                            obj17 = AddOp.$Mn.apply2(obj18, Lit1);
                                                        } catch (ClassCastException e) {
                                                            ClassCastException classCastException = e;
                                                            Throwable th17 = th16;
                                                            new WrongType(classCastException, "%string-copy!", 4, obj30);
                                                            throw th17;
                                                        }
                                                    } catch (ClassCastException e2) {
                                                        ClassCastException classCastException2 = e2;
                                                        Throwable th18 = th15;
                                                        new WrongType(classCastException2, "%string-copy!", 3, obj28);
                                                        throw th18;
                                                    }
                                                } catch (ClassCastException e3) {
                                                    ClassCastException classCastException3 = e3;
                                                    Throwable th19 = th14;
                                                    new WrongType(classCastException3, "%string-copy!", 2, obj26);
                                                    throw th19;
                                                }
                                            } catch (ClassCastException e4) {
                                                ClassCastException classCastException4 = e4;
                                                Throwable th20 = th13;
                                                new WrongType(classCastException4, "%string-copy!", 1, obj24);
                                                throw th20;
                                            }
                                        } catch (ClassCastException e5) {
                                            ClassCastException classCastException5 = e5;
                                            Throwable th21 = th12;
                                            new WrongType(classCastException5, "%string-copy!", 0, obj22);
                                            throw th21;
                                        }
                                    } else {
                                        Object obj31 = target;
                                        Object obj32 = obj31;
                                        try {
                                            CharSequence charSequence5 = (CharSequence) obj31;
                                            Object obj33 = i;
                                            Object obj34 = obj33;
                                            try {
                                                int intValue5 = ((Number) obj33).intValue();
                                                Object obj35 = s;
                                                Object obj36 = obj35;
                                                try {
                                                    CharSequence charSequence6 = (CharSequence) obj35;
                                                    Object obj37 = start;
                                                    Object obj38 = obj37;
                                                    try {
                                                        int intValue6 = ((Number) obj37).intValue();
                                                        Object apply22 = AddOp.$Pl.apply2(start, AddOp.$Mn.apply2(total$Mnchars, AddOp.$Mn.apply2(i, tstart)));
                                                        Object obj39 = apply22;
                                                        try {
                                                            return $PcStringCopy$Ex(charSequence5, intValue5, charSequence6, intValue6, ((Number) apply22).intValue());
                                                        } catch (ClassCastException e6) {
                                                            ClassCastException classCastException6 = e6;
                                                            Throwable th22 = th11;
                                                            new WrongType(classCastException6, "%string-copy!", 4, obj39);
                                                            throw th22;
                                                        }
                                                    } catch (ClassCastException e7) {
                                                        ClassCastException classCastException7 = e7;
                                                        Throwable th23 = th10;
                                                        new WrongType(classCastException7, "%string-copy!", 3, obj38);
                                                        throw th23;
                                                    }
                                                } catch (ClassCastException e8) {
                                                    ClassCastException classCastException8 = e8;
                                                    Throwable th24 = th9;
                                                    new WrongType(classCastException8, "%string-copy!", 2, obj36);
                                                    throw th24;
                                                }
                                            } catch (ClassCastException e9) {
                                                ClassCastException classCastException9 = e9;
                                                Throwable th25 = th8;
                                                new WrongType(classCastException9, "%string-copy!", 1, obj34);
                                                throw th25;
                                            }
                                        } catch (ClassCastException e10) {
                                            ClassCastException classCastException10 = e10;
                                            Throwable th26 = th7;
                                            new WrongType(classCastException10, "%string-copy!", 0, obj32);
                                            throw th26;
                                        }
                                    }
                                } catch (ClassCastException e11) {
                                    ClassCastException classCastException11 = e11;
                                    Throwable th27 = th6;
                                    new WrongType(classCastException11, "zero?", 1, obj20);
                                    throw th27;
                                }
                            }
                        } catch (ClassCastException e12) {
                            ClassCastException classCastException12 = e12;
                            Throwable th28 = th5;
                            new WrongType(classCastException12, "%string-copy!", 4, obj16);
                            throw th28;
                        }
                    } catch (ClassCastException e13) {
                        ClassCastException classCastException13 = e13;
                        Throwable th29 = th4;
                        new WrongType(classCastException13, "%string-copy!", 3, obj14);
                        throw th29;
                    }
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th30 = th3;
                    new WrongType(classCastException14, "%string-copy!", 2, obj12);
                    throw th30;
                }
            } catch (ClassCastException e15) {
                ClassCastException classCastException15 = e15;
                Throwable th31 = th2;
                new WrongType(classCastException15, "%string-copy!", 1, obj10);
                throw th31;
            }
        } catch (ClassCastException e16) {
            ClassCastException classCastException16 = e16;
            Throwable th32 = th;
            new WrongType(classCastException16, "%string-copy!", 0, obj8);
            throw th32;
        }
    }

    public static Object stringJoin$V(Object obj, Object[] argsArray) {
        Object obj2;
        Object error$V;
        Object strings = obj;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList delim$Plgrammar = makeList;
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        try {
            Object obj3 = loc$let$Mnoptionals$St.get();
            LList lList2 = delim$Plgrammar;
            try {
                try {
                    try {
                        Object apply2 = Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply3(loc$delim.get(), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, strings.isString(loc$delim.get()) ? Boolean.TRUE : Boolean.FALSE), Scheme.applyToArgs.apply2(loc$grammar.get(), Lit15));
                        if (C1245lists.isPair(strings)) {
                            try {
                                Object tmp = loc$grammar.get();
                                Object x = Scheme.isEqv.apply2(tmp, Lit15);
                                if (x == Boolean.FALSE ? Scheme.isEqv.apply2(tmp, Lit16) != Boolean.FALSE : x != Boolean.FALSE) {
                                    error$V = C1245lists.cons(C1245lists.car.apply1(strings), lambda222buildit(C1245lists.cdr.apply1(strings), LList.Empty));
                                } else if (Scheme.isEqv.apply2(tmp, Lit17) != Boolean.FALSE) {
                                    error$V = lambda222buildit(strings, LList.Empty);
                                } else if (Scheme.isEqv.apply2(tmp, Lit18) != Boolean.FALSE) {
                                    try {
                                        error$V = C1245lists.cons(C1245lists.car.apply1(strings), lambda222buildit(C1245lists.cdr.apply1(strings), LList.list1(loc$delim.get())));
                                    } catch (UnboundLocationException e) {
                                        UnboundLocationException unboundLocationException = e;
                                        UnboundLocationException unboundLocationException2 = unboundLocationException;
                                        unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1870, 53);
                                        throw unboundLocationException2;
                                    }
                                } else {
                                    Object[] objArr = new Object[2];
                                    Object[] objArr2 = objArr;
                                    try {
                                        objArr[0] = loc$grammar.get();
                                        Object[] objArr3 = objArr2;
                                        objArr3[1] = string$Mnjoin;
                                        error$V = misc.error$V("Illegal join grammar", objArr3);
                                    } catch (UnboundLocationException e2) {
                                        UnboundLocationException unboundLocationException3 = e2;
                                        UnboundLocationException unboundLocationException4 = unboundLocationException3;
                                        unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1873, 9);
                                        throw unboundLocationException4;
                                    }
                                }
                                obj2 = stringConcatenate(error$V);
                            } catch (UnboundLocationException e3) {
                                UnboundLocationException unboundLocationException5 = e3;
                                UnboundLocationException unboundLocationException6 = unboundLocationException5;
                                unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1862, 14);
                                throw unboundLocationException6;
                            }
                        } else if (!C1245lists.isNull(strings)) {
                            Object[] objArr4 = new Object[2];
                            objArr4[0] = strings;
                            Object[] objArr5 = objArr4;
                            objArr5[1] = string$Mnjoin;
                            obj2 = misc.error$V("STRINGS parameter not list.", objArr5);
                        } else {
                            try {
                                if (loc$grammar.get() == Lit16) {
                                    obj2 = misc.error$V("Empty list cannot be joined with STRICT-INFIX grammar.", new Object[]{string$Mnjoin});
                                } else {
                                    obj2 = "";
                                }
                            } catch (UnboundLocationException e4) {
                                UnboundLocationException unboundLocationException7 = e4;
                                UnboundLocationException unboundLocationException8 = unboundLocationException7;
                                unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1880, 13);
                                throw unboundLocationException8;
                            }
                        }
                        return applyToArgs.apply4(obj3, lList2, apply2, obj2);
                    } catch (UnboundLocationException e5) {
                        UnboundLocationException unboundLocationException9 = e5;
                        UnboundLocationException unboundLocationException10 = unboundLocationException9;
                        unboundLocationException9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1853, 6);
                        throw unboundLocationException10;
                    }
                } catch (UnboundLocationException e6) {
                    UnboundLocationException unboundLocationException11 = e6;
                    UnboundLocationException unboundLocationException12 = unboundLocationException11;
                    unboundLocationException11.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 54);
                    throw unboundLocationException12;
                }
            } catch (UnboundLocationException e7) {
                UnboundLocationException unboundLocationException13 = e7;
                UnboundLocationException unboundLocationException14 = unboundLocationException13;
                unboundLocationException13.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 34);
                throw unboundLocationException14;
            }
        } catch (UnboundLocationException e8) {
            UnboundLocationException unboundLocationException15 = e8;
            UnboundLocationException unboundLocationException16 = unboundLocationException15;
            unboundLocationException15.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 3);
            throw unboundLocationException16;
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Object stringCopy$Ex;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        Throwable th14;
        Throwable th15;
        Throwable th16;
        Throwable th17;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 200:
                Object obj = objArr2[0];
                Object obj2 = objArr2[1];
                int length = objArr2.length - 2;
                Object[] objArr3 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return substring$SlShared$V(obj, obj2, objArr3);
                    }
                    Object[] objArr4 = objArr3;
                    objArr3 = objArr4;
                    objArr4[length] = objArr2[length + 2];
                }
            case 202:
                Object obj3 = objArr2[0];
                int length2 = objArr2.length - 1;
                Object[] objArr5 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return stringCopy$V(obj3, objArr5);
                    }
                    Object[] objArr6 = objArr5;
                    objArr5 = objArr6;
                    objArr6[length2] = objArr2[length2 + 1];
                }
            case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION /*203*/:
                Object obj4 = objArr2[0];
                Object obj5 = objArr2[1];
                int length3 = objArr2.length - 2;
                Object[] objArr7 = new Object[length3];
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        return stringMap$V(obj4, obj5, objArr7);
                    }
                    Object[] objArr8 = objArr7;
                    objArr7 = objArr8;
                    objArr8[length3] = objArr2[length3 + 2];
                }
            case HttpStatus.SC_RESET_CONTENT /*205*/:
                Object obj6 = objArr2[0];
                Object obj7 = objArr2[1];
                int length4 = objArr2.length - 2;
                Object[] objArr9 = new Object[length4];
                while (true) {
                    length4--;
                    if (length4 < 0) {
                        return stringMap$Ex$V(obj6, obj7, objArr9);
                    }
                    Object[] objArr10 = objArr9;
                    objArr9 = objArr10;
                    objArr10[length4] = objArr2[length4 + 2];
                }
            case HttpStatus.SC_MULTI_STATUS /*207*/:
                Object obj8 = objArr2[0];
                Object obj9 = objArr2[1];
                Object obj10 = objArr2[2];
                int length5 = objArr2.length - 3;
                Object[] objArr11 = new Object[length5];
                while (true) {
                    length5--;
                    if (length5 < 0) {
                        return stringFold$V(obj8, obj9, obj10, objArr11);
                    }
                    Object[] objArr12 = objArr11;
                    objArr11 = objArr12;
                    objArr12[length5] = objArr2[length5 + 3];
                }
            case 208:
                Object obj11 = objArr2[0];
                Object obj12 = objArr2[1];
                Object obj13 = objArr2[2];
                int length6 = objArr2.length - 3;
                Object[] objArr13 = new Object[length6];
                while (true) {
                    length6--;
                    if (length6 < 0) {
                        return stringFoldRight$V(obj11, obj12, obj13, objArr13);
                    }
                    Object[] objArr14 = objArr13;
                    objArr13 = objArr14;
                    objArr14[length6] = objArr2[length6 + 3];
                }
            case 210:
                Object obj14 = objArr2[0];
                Object obj15 = objArr2[1];
                Object obj16 = objArr2[2];
                Object obj17 = objArr2[3];
                int length7 = objArr2.length - 4;
                Object[] objArr15 = new Object[length7];
                while (true) {
                    length7--;
                    if (length7 < 0) {
                        return stringUnfold$V(obj14, obj15, obj16, obj17, objArr15);
                    }
                    Object[] objArr16 = objArr15;
                    objArr15 = objArr16;
                    objArr16[length7] = objArr2[length7 + 4];
                }
            case 212:
                Object obj18 = objArr2[0];
                Object obj19 = objArr2[1];
                Object obj20 = objArr2[2];
                Object obj21 = objArr2[3];
                int length8 = objArr2.length - 4;
                Object[] objArr17 = new Object[length8];
                while (true) {
                    length8--;
                    if (length8 < 0) {
                        return stringUnfoldRight$V(obj18, obj19, obj20, obj21, objArr17);
                    }
                    Object[] objArr18 = objArr17;
                    objArr17 = objArr18;
                    objArr18[length8] = objArr2[length8 + 4];
                }
            case 213:
                Object obj22 = objArr2[0];
                Object obj23 = objArr2[1];
                int length9 = objArr2.length - 2;
                Object[] objArr19 = new Object[length9];
                while (true) {
                    length9--;
                    if (length9 < 0) {
                        return stringForEach$V(obj22, obj23, objArr19);
                    }
                    Object[] objArr20 = objArr19;
                    objArr19 = objArr20;
                    objArr20[length9] = objArr2[length9 + 2];
                }
            case 214:
                Object obj24 = objArr2[0];
                Object obj25 = objArr2[1];
                int length10 = objArr2.length - 2;
                Object[] objArr21 = new Object[length10];
                while (true) {
                    length10--;
                    if (length10 < 0) {
                        return stringForEachIndex$V(obj24, obj25, objArr21);
                    }
                    Object[] objArr22 = objArr21;
                    objArr21 = objArr22;
                    objArr22[length10] = objArr2[length10 + 2];
                }
            case 215:
                Object obj26 = objArr2[0];
                Object obj27 = objArr2[1];
                int length11 = objArr2.length - 2;
                Object[] objArr23 = new Object[length11];
                while (true) {
                    length11--;
                    if (length11 < 0) {
                        return stringEvery$V(obj26, obj27, objArr23);
                    }
                    Object[] objArr24 = objArr23;
                    objArr23 = objArr24;
                    objArr24[length11] = objArr2[length11 + 2];
                }
            case 216:
                Object obj28 = objArr2[0];
                Object obj29 = objArr2[1];
                int length12 = objArr2.length - 2;
                Object[] objArr25 = new Object[length12];
                while (true) {
                    length12--;
                    if (length12 < 0) {
                        return stringAny$V(obj28, obj29, objArr25);
                    }
                    Object[] objArr26 = objArr25;
                    objArr25 = objArr26;
                    objArr26[length12] = objArr2[length12 + 2];
                }
            case 219:
                return $PcStringPrefixLength(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 220:
                return $PcStringSuffixLength(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 221:
                Object obj30 = objArr2[0];
                Object obj31 = objArr2[1];
                Object obj32 = obj31;
                try {
                    int intValue = ((Number) obj31).intValue();
                    Object obj33 = objArr2[2];
                    Object obj34 = obj33;
                    try {
                        int intValue2 = ((Number) obj33).intValue();
                        Object obj35 = objArr2[3];
                        Object obj36 = objArr2[4];
                        Object obj37 = obj36;
                        try {
                            int intValue3 = ((Number) obj36).intValue();
                            Object obj38 = objArr2[5];
                            Object obj39 = obj38;
                            try {
                                return Integer.valueOf($PcStringPrefixLengthCi(obj30, intValue, intValue2, obj35, intValue3, ((Number) obj38).intValue()));
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th18 = th17;
                                new WrongType(classCastException, "%string-prefix-length-ci", 6, obj39);
                                throw th18;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th19 = th16;
                            new WrongType(classCastException2, "%string-prefix-length-ci", 5, obj37);
                            throw th19;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th20 = th15;
                        new WrongType(classCastException3, "%string-prefix-length-ci", 3, obj34);
                        throw th20;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th21 = th14;
                    new WrongType(classCastException4, "%string-prefix-length-ci", 2, obj32);
                    throw th21;
                }
            case 222:
                Object obj40 = objArr2[0];
                Object obj41 = objArr2[1];
                Object obj42 = obj41;
                try {
                    int intValue4 = ((Number) obj41).intValue();
                    Object obj43 = objArr2[2];
                    Object obj44 = obj43;
                    try {
                        int intValue5 = ((Number) obj43).intValue();
                        Object obj45 = objArr2[3];
                        Object obj46 = objArr2[4];
                        Object obj47 = obj46;
                        try {
                            int intValue6 = ((Number) obj46).intValue();
                            Object obj48 = objArr2[5];
                            Object obj49 = obj48;
                            try {
                                return Integer.valueOf($PcStringSuffixLengthCi(obj40, intValue4, intValue5, obj45, intValue6, ((Number) obj48).intValue()));
                            } catch (ClassCastException e5) {
                                ClassCastException classCastException5 = e5;
                                Throwable th22 = th13;
                                new WrongType(classCastException5, "%string-suffix-length-ci", 6, obj49);
                                throw th22;
                            }
                        } catch (ClassCastException e6) {
                            ClassCastException classCastException6 = e6;
                            Throwable th23 = th12;
                            new WrongType(classCastException6, "%string-suffix-length-ci", 5, obj47);
                            throw th23;
                        }
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th24 = th11;
                        new WrongType(classCastException7, "%string-suffix-length-ci", 3, obj44);
                        throw th24;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th25 = th10;
                    new WrongType(classCastException8, "%string-suffix-length-ci", 2, obj42);
                    throw th25;
                }
            case 223:
                Object obj50 = objArr2[0];
                Object obj51 = objArr2[1];
                int length13 = objArr2.length - 2;
                Object[] objArr27 = new Object[length13];
                while (true) {
                    length13--;
                    if (length13 < 0) {
                        return stringPrefixLength$V(obj50, obj51, objArr27);
                    }
                    Object[] objArr28 = objArr27;
                    objArr27 = objArr28;
                    objArr28[length13] = objArr2[length13 + 2];
                }
            case YaVersion.YOUNG_ANDROID_VERSION:
                Object obj52 = objArr2[0];
                Object obj53 = objArr2[1];
                int length14 = objArr2.length - 2;
                Object[] objArr29 = new Object[length14];
                while (true) {
                    length14--;
                    if (length14 < 0) {
                        return stringSuffixLength$V(obj52, obj53, objArr29);
                    }
                    Object[] objArr30 = objArr29;
                    objArr29 = objArr30;
                    objArr30[length14] = objArr2[length14 + 2];
                }
            case 225:
                Object obj54 = objArr2[0];
                Object obj55 = objArr2[1];
                int length15 = objArr2.length - 2;
                Object[] objArr31 = new Object[length15];
                while (true) {
                    length15--;
                    if (length15 < 0) {
                        return stringPrefixLengthCi$V(obj54, obj55, objArr31);
                    }
                    Object[] objArr32 = objArr31;
                    objArr31 = objArr32;
                    objArr32[length15] = objArr2[length15 + 2];
                }
            case 226:
                Object obj56 = objArr2[0];
                Object obj57 = objArr2[1];
                int length16 = objArr2.length - 2;
                Object[] objArr33 = new Object[length16];
                while (true) {
                    length16--;
                    if (length16 < 0) {
                        return stringSuffixLengthCi$V(obj56, obj57, objArr33);
                    }
                    Object[] objArr34 = objArr33;
                    objArr33 = objArr34;
                    objArr34[length16] = objArr2[length16 + 2];
                }
            case 227:
                Object obj58 = objArr2[0];
                Object obj59 = objArr2[1];
                int length17 = objArr2.length - 2;
                Object[] objArr35 = new Object[length17];
                while (true) {
                    length17--;
                    if (length17 < 0) {
                        return isStringPrefix$V(obj58, obj59, objArr35);
                    }
                    Object[] objArr36 = objArr35;
                    objArr35 = objArr36;
                    objArr36[length17] = objArr2[length17 + 2];
                }
            case 228:
                Object obj60 = objArr2[0];
                Object obj61 = objArr2[1];
                int length18 = objArr2.length - 2;
                Object[] objArr37 = new Object[length18];
                while (true) {
                    length18--;
                    if (length18 < 0) {
                        return isStringSuffix$V(obj60, obj61, objArr37);
                    }
                    Object[] objArr38 = objArr37;
                    objArr37 = objArr38;
                    objArr38[length18] = objArr2[length18 + 2];
                }
            case 229:
                Object obj62 = objArr2[0];
                Object obj63 = objArr2[1];
                int length19 = objArr2.length - 2;
                Object[] objArr39 = new Object[length19];
                while (true) {
                    length19--;
                    if (length19 < 0) {
                        return isStringPrefixCi$V(obj62, obj63, objArr39);
                    }
                    Object[] objArr40 = objArr39;
                    objArr39 = objArr40;
                    objArr40[length19] = objArr2[length19 + 2];
                }
            case 230:
                Object obj64 = objArr2[0];
                Object obj65 = objArr2[1];
                int length20 = objArr2.length - 2;
                Object[] objArr41 = new Object[length20];
                while (true) {
                    length20--;
                    if (length20 < 0) {
                        return isStringSuffixCi$V(obj64, obj65, objArr41);
                    }
                    Object[] objArr42 = objArr41;
                    objArr41 = objArr42;
                    objArr42[length20] = objArr2[length20 + 2];
                }
            case 231:
                return $PcStringPrefix$Qu(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 232:
                return $PcStringSuffix$Qu(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 233:
                return $PcStringPrefixCi$Qu(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 234:
                return $PcStringSuffixCi$Qu(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 235:
                return $PcStringCompare(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7], objArr2[8]);
            case 236:
                return $PcStringCompareCi(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7], objArr2[8]);
            case 237:
                Object obj66 = objArr2[0];
                Object obj67 = objArr2[1];
                Object obj68 = objArr2[2];
                Object obj69 = objArr2[3];
                Object obj70 = objArr2[4];
                int length21 = objArr2.length - 5;
                Object[] objArr43 = new Object[length21];
                while (true) {
                    length21--;
                    if (length21 < 0) {
                        return stringCompare$V(obj66, obj67, obj68, obj69, obj70, objArr43);
                    }
                    Object[] objArr44 = objArr43;
                    objArr43 = objArr44;
                    objArr44[length21] = objArr2[length21 + 5];
                }
            case 238:
                Object obj71 = objArr2[0];
                Object obj72 = objArr2[1];
                Object obj73 = objArr2[2];
                Object obj74 = objArr2[3];
                Object obj75 = objArr2[4];
                int length22 = objArr2.length - 5;
                Object[] objArr45 = new Object[length22];
                while (true) {
                    length22--;
                    if (length22 < 0) {
                        return stringCompareCi$V(obj71, obj72, obj73, obj74, obj75, objArr45);
                    }
                    Object[] objArr46 = objArr45;
                    objArr45 = objArr46;
                    objArr46[length22] = objArr2[length22 + 5];
                }
            case LispEscapeFormat.ESCAPE_NORMAL:
                Object obj76 = objArr2[0];
                Object obj77 = objArr2[1];
                int length23 = objArr2.length - 2;
                Object[] objArr47 = new Object[length23];
                while (true) {
                    length23--;
                    if (length23 < 0) {
                        return string$Eq$V(obj76, obj77, objArr47);
                    }
                    Object[] objArr48 = objArr47;
                    objArr47 = objArr48;
                    objArr48[length23] = objArr2[length23 + 2];
                }
            case 243:
                Object obj78 = objArr2[0];
                Object obj79 = objArr2[1];
                int length24 = objArr2.length - 2;
                Object[] objArr49 = new Object[length24];
                while (true) {
                    length24--;
                    if (length24 < 0) {
                        return string$Ls$Gr$V(obj78, obj79, objArr49);
                    }
                    Object[] objArr50 = objArr49;
                    objArr49 = objArr50;
                    objArr50[length24] = objArr2[length24 + 2];
                }
            case 246:
                Object obj80 = objArr2[0];
                Object obj81 = objArr2[1];
                int length25 = objArr2.length - 2;
                Object[] objArr51 = new Object[length25];
                while (true) {
                    length25--;
                    if (length25 < 0) {
                        return string$Ls$V(obj80, obj81, objArr51);
                    }
                    Object[] objArr52 = objArr51;
                    objArr51 = objArr52;
                    objArr52[length25] = objArr2[length25 + 2];
                }
            case 249:
                Object obj82 = objArr2[0];
                Object obj83 = objArr2[1];
                int length26 = objArr2.length - 2;
                Object[] objArr53 = new Object[length26];
                while (true) {
                    length26--;
                    if (length26 < 0) {
                        return string$Gr$V(obj82, obj83, objArr53);
                    }
                    Object[] objArr54 = objArr53;
                    objArr53 = objArr54;
                    objArr54[length26] = objArr2[length26 + 2];
                }
            case Telnet.WILL /*251*/:
                Object obj84 = objArr2[0];
                Object obj85 = objArr2[1];
                int length27 = objArr2.length - 2;
                Object[] objArr55 = new Object[length27];
                while (true) {
                    length27--;
                    if (length27 < 0) {
                        return string$Ls$Eq$V(obj84, obj85, objArr55);
                    }
                    Object[] objArr56 = objArr55;
                    objArr55 = objArr56;
                    objArr56[length27] = objArr2[length27 + 2];
                }
            case Telnet.f261DO /*253*/:
                Object obj86 = objArr2[0];
                Object obj87 = objArr2[1];
                int length28 = objArr2.length - 2;
                Object[] objArr57 = new Object[length28];
                while (true) {
                    length28--;
                    if (length28 < 0) {
                        return string$Gr$Eq$V(obj86, obj87, objArr57);
                    }
                    Object[] objArr58 = objArr57;
                    objArr57 = objArr58;
                    objArr58[length28] = objArr2[length28 + 2];
                }
            case 256:
                Object obj88 = objArr2[0];
                Object obj89 = objArr2[1];
                int length29 = objArr2.length - 2;
                Object[] objArr59 = new Object[length29];
                while (true) {
                    length29--;
                    if (length29 < 0) {
                        return stringCi$Eq$V(obj88, obj89, objArr59);
                    }
                    Object[] objArr60 = objArr59;
                    objArr59 = objArr60;
                    objArr60[length29] = objArr2[length29 + 2];
                }
            case 258:
                Object obj90 = objArr2[0];
                Object obj91 = objArr2[1];
                int length30 = objArr2.length - 2;
                Object[] objArr61 = new Object[length30];
                while (true) {
                    length30--;
                    if (length30 < 0) {
                        return stringCi$Ls$Gr$V(obj90, obj91, objArr61);
                    }
                    Object[] objArr62 = objArr61;
                    objArr61 = objArr62;
                    objArr62[length30] = objArr2[length30 + 2];
                }
            case 261:
                Object obj92 = objArr2[0];
                Object obj93 = objArr2[1];
                int length31 = objArr2.length - 2;
                Object[] objArr63 = new Object[length31];
                while (true) {
                    length31--;
                    if (length31 < 0) {
                        return stringCi$Ls$V(obj92, obj93, objArr63);
                    }
                    Object[] objArr64 = objArr63;
                    objArr63 = objArr64;
                    objArr64[length31] = objArr2[length31 + 2];
                }
            case 264:
                Object obj94 = objArr2[0];
                Object obj95 = objArr2[1];
                int length32 = objArr2.length - 2;
                Object[] objArr65 = new Object[length32];
                while (true) {
                    length32--;
                    if (length32 < 0) {
                        return stringCi$Gr$V(obj94, obj95, objArr65);
                    }
                    Object[] objArr66 = objArr65;
                    objArr65 = objArr66;
                    objArr66[length32] = objArr2[length32 + 2];
                }
            case 266:
                Object obj96 = objArr2[0];
                Object obj97 = objArr2[1];
                int length33 = objArr2.length - 2;
                Object[] objArr67 = new Object[length33];
                while (true) {
                    length33--;
                    if (length33 < 0) {
                        return stringCi$Ls$Eq$V(obj96, obj97, objArr67);
                    }
                    Object[] objArr68 = objArr67;
                    objArr67 = objArr68;
                    objArr68[length33] = objArr2[length33 + 2];
                }
            case 268:
                Object obj98 = objArr2[0];
                Object obj99 = objArr2[1];
                int length34 = objArr2.length - 2;
                Object[] objArr69 = new Object[length34];
                while (true) {
                    length34--;
                    if (length34 < 0) {
                        return stringCi$Gr$Eq$V(obj98, obj99, objArr69);
                    }
                    Object[] objArr70 = objArr69;
                    objArr69 = objArr70;
                    objArr70[length34] = objArr2[length34 + 2];
                }
            case 269:
                return $PcStringHash(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4]);
            case 270:
                Object obj100 = objArr2[0];
                int length35 = objArr2.length - 1;
                Object[] objArr71 = new Object[length35];
                while (true) {
                    length35--;
                    if (length35 < 0) {
                        return stringHash$V(obj100, objArr71);
                    }
                    Object[] objArr72 = objArr71;
                    objArr71 = objArr72;
                    objArr72[length35] = objArr2[length35 + 1];
                }
            case 272:
                Object obj101 = objArr2[0];
                int length36 = objArr2.length - 1;
                Object[] objArr73 = new Object[length36];
                while (true) {
                    length36--;
                    if (length36 < 0) {
                        return stringHashCi$V(obj101, objArr73);
                    }
                    Object[] objArr74 = objArr73;
                    objArr73 = objArr74;
                    objArr74[length36] = objArr2[length36 + 1];
                }
            case 273:
                Object obj102 = objArr2[0];
                int length37 = objArr2.length - 1;
                Object[] objArr75 = new Object[length37];
                while (true) {
                    length37--;
                    if (length37 < 0) {
                        return stringUpcase$V(obj102, objArr75);
                    }
                    Object[] objArr76 = objArr75;
                    objArr75 = objArr76;
                    objArr76[length37] = objArr2[length37 + 1];
                }
            case 274:
                Object obj103 = objArr2[0];
                int length38 = objArr2.length - 1;
                Object[] objArr77 = new Object[length38];
                while (true) {
                    length38--;
                    if (length38 < 0) {
                        return stringUpcase$Ex$V(obj103, objArr77);
                    }
                    Object[] objArr78 = objArr77;
                    objArr77 = objArr78;
                    objArr78[length38] = objArr2[length38 + 1];
                }
            case 275:
                Object obj104 = objArr2[0];
                int length39 = objArr2.length - 1;
                Object[] objArr79 = new Object[length39];
                while (true) {
                    length39--;
                    if (length39 < 0) {
                        return stringDowncase$V(obj104, objArr79);
                    }
                    Object[] objArr80 = objArr79;
                    objArr79 = objArr80;
                    objArr80[length39] = objArr2[length39 + 1];
                }
            case 276:
                Object obj105 = objArr2[0];
                int length40 = objArr2.length - 1;
                Object[] objArr81 = new Object[length40];
                while (true) {
                    length40--;
                    if (length40 < 0) {
                        return stringDowncase$Ex$V(obj105, objArr81);
                    }
                    Object[] objArr82 = objArr81;
                    objArr81 = objArr82;
                    objArr82[length40] = objArr2[length40 + 1];
                }
            case 278:
                Object obj106 = objArr2[0];
                int length41 = objArr2.length - 1;
                Object[] objArr83 = new Object[length41];
                while (true) {
                    length41--;
                    if (length41 < 0) {
                        return stringTitlecase$Ex$V(obj106, objArr83);
                    }
                    Object[] objArr84 = objArr83;
                    objArr83 = objArr84;
                    objArr84[length41] = objArr2[length41 + 1];
                }
            case 279:
                Object obj107 = objArr2[0];
                int length42 = objArr2.length - 1;
                Object[] objArr85 = new Object[length42];
                while (true) {
                    length42--;
                    if (length42 < 0) {
                        return stringTitlecase$V(obj107, objArr85);
                    }
                    Object[] objArr86 = objArr85;
                    objArr85 = objArr86;
                    objArr86[length42] = objArr2[length42 + 1];
                }
            case 284:
                Object obj108 = objArr2[0];
                int length43 = objArr2.length - 1;
                Object[] objArr87 = new Object[length43];
                while (true) {
                    length43--;
                    if (length43 < 0) {
                        return stringTrim$V(obj108, objArr87);
                    }
                    Object[] objArr88 = objArr87;
                    objArr87 = objArr88;
                    objArr88[length43] = objArr2[length43 + 1];
                }
            case 285:
                Object obj109 = objArr2[0];
                int length44 = objArr2.length - 1;
                Object[] objArr89 = new Object[length44];
                while (true) {
                    length44--;
                    if (length44 < 0) {
                        return stringTrimRight$V(obj109, objArr89);
                    }
                    Object[] objArr90 = objArr89;
                    objArr89 = objArr90;
                    objArr90[length44] = objArr2[length44 + 1];
                }
            case 286:
                Object obj110 = objArr2[0];
                int length45 = objArr2.length - 1;
                Object[] objArr91 = new Object[length45];
                while (true) {
                    length45--;
                    if (length45 < 0) {
                        return stringTrimBoth$V(obj110, objArr91);
                    }
                    Object[] objArr92 = objArr91;
                    objArr91 = objArr92;
                    objArr92[length45] = objArr2[length45 + 1];
                }
            case 288:
                Object obj111 = objArr2[0];
                Object obj112 = objArr2[1];
                int length46 = objArr2.length - 2;
                Object[] objArr93 = new Object[length46];
                while (true) {
                    length46--;
                    if (length46 < 0) {
                        return stringPadRight$V(obj111, obj112, objArr93);
                    }
                    Object[] objArr94 = objArr93;
                    objArr93 = objArr94;
                    objArr94[length46] = objArr2[length46 + 2];
                }
            case 290:
                Object obj113 = objArr2[0];
                Object obj114 = objArr2[1];
                int length47 = objArr2.length - 2;
                Object[] objArr95 = new Object[length47];
                while (true) {
                    length47--;
                    if (length47 < 0) {
                        return stringPad$V(obj113, obj114, objArr95);
                    }
                    Object[] objArr96 = objArr95;
                    objArr95 = objArr96;
                    objArr96[length47] = objArr2[length47 + 2];
                }
            case 291:
                Object obj115 = objArr2[0];
                Object obj116 = objArr2[1];
                int length48 = objArr2.length - 2;
                Object[] objArr97 = new Object[length48];
                while (true) {
                    length48--;
                    if (length48 < 0) {
                        return stringDelete$V(obj115, obj116, objArr97);
                    }
                    Object[] objArr98 = objArr97;
                    objArr97 = objArr98;
                    objArr98[length48] = objArr2[length48 + 2];
                }
            case 292:
                Object obj117 = objArr2[0];
                Object obj118 = objArr2[1];
                int length49 = objArr2.length - 2;
                Object[] objArr99 = new Object[length49];
                while (true) {
                    length49--;
                    if (length49 < 0) {
                        return stringFilter$V(obj117, obj118, objArr99);
                    }
                    Object[] objArr100 = objArr99;
                    objArr99 = objArr100;
                    objArr100[length49] = objArr2[length49 + 2];
                }
            case 293:
                Object obj119 = objArr2[0];
                Object obj120 = objArr2[1];
                int length50 = objArr2.length - 2;
                Object[] objArr101 = new Object[length50];
                while (true) {
                    length50--;
                    if (length50 < 0) {
                        return stringIndex$V(obj119, obj120, objArr101);
                    }
                    Object[] objArr102 = objArr101;
                    objArr101 = objArr102;
                    objArr102[length50] = objArr2[length50 + 2];
                }
            case 294:
                Object obj121 = objArr2[0];
                Object obj122 = objArr2[1];
                int length51 = objArr2.length - 2;
                Object[] objArr103 = new Object[length51];
                while (true) {
                    length51--;
                    if (length51 < 0) {
                        return stringIndexRight$V(obj121, obj122, objArr103);
                    }
                    Object[] objArr104 = objArr103;
                    objArr103 = objArr104;
                    objArr104[length51] = objArr2[length51 + 2];
                }
            case 295:
                Object obj123 = objArr2[0];
                Object obj124 = objArr2[1];
                int length52 = objArr2.length - 2;
                Object[] objArr105 = new Object[length52];
                while (true) {
                    length52--;
                    if (length52 < 0) {
                        return stringSkip$V(obj123, obj124, objArr105);
                    }
                    Object[] objArr106 = objArr105;
                    objArr105 = objArr106;
                    objArr106[length52] = objArr2[length52 + 2];
                }
            case 296:
                Object obj125 = objArr2[0];
                Object obj126 = objArr2[1];
                int length53 = objArr2.length - 2;
                Object[] objArr107 = new Object[length53];
                while (true) {
                    length53--;
                    if (length53 < 0) {
                        return stringSkipRight$V(obj125, obj126, objArr107);
                    }
                    Object[] objArr108 = objArr107;
                    objArr107 = objArr108;
                    objArr108[length53] = objArr2[length53 + 2];
                }
            case 297:
                Object obj127 = objArr2[0];
                Object obj128 = objArr2[1];
                int length54 = objArr2.length - 2;
                Object[] objArr109 = new Object[length54];
                while (true) {
                    length54--;
                    if (length54 < 0) {
                        return stringCount$V(obj127, obj128, objArr109);
                    }
                    Object[] objArr110 = objArr109;
                    objArr109 = objArr110;
                    objArr110[length54] = objArr2[length54 + 2];
                }
            case 298:
                Object obj129 = objArr2[0];
                Object obj130 = objArr2[1];
                int length55 = objArr2.length - 2;
                Object[] objArr111 = new Object[length55];
                while (true) {
                    length55--;
                    if (length55 < 0) {
                        return stringFill$Ex$V(obj129, obj130, objArr111);
                    }
                    Object[] objArr112 = objArr111;
                    objArr111 = objArr112;
                    objArr112[length55] = objArr2[length55 + 2];
                }
            case 299:
                int length56 = objArr2.length - 3;
                Object obj131 = objArr2[0];
                Object obj132 = objArr2[1];
                Object obj133 = obj132;
                try {
                    int intValue7 = ((Number) obj132).intValue();
                    Object obj134 = objArr2[2];
                    Object obj135 = obj134;
                    try {
                        CharSequence charSequence = (CharSequence) obj134;
                        if (length56 <= 0) {
                            stringCopy$Ex = stringCopy$Ex(obj131, intValue7, charSequence);
                        } else {
                            int i = length56 - 1;
                            Object obj136 = objArr2[3];
                            Object obj137 = obj136;
                            try {
                                int intValue8 = ((Number) obj136).intValue();
                                if (i <= 0) {
                                    stringCopy$Ex = stringCopy$Ex(obj131, intValue7, charSequence, intValue8);
                                } else {
                                    int i2 = i - 1;
                                    Object obj138 = objArr2[4];
                                    Object obj139 = obj138;
                                    try {
                                        stringCopy$Ex = stringCopy$Ex(obj131, intValue7, charSequence, intValue8, ((Number) obj138).intValue());
                                    } catch (ClassCastException e9) {
                                        ClassCastException classCastException9 = e9;
                                        Throwable th26 = th9;
                                        new WrongType(classCastException9, "string-copy!", 5, obj139);
                                        throw th26;
                                    }
                                }
                            } catch (ClassCastException e10) {
                                ClassCastException classCastException10 = e10;
                                Throwable th27 = th8;
                                new WrongType(classCastException10, "string-copy!", 4, obj137);
                                throw th27;
                            }
                        }
                        return stringCopy$Ex;
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th28 = th7;
                        new WrongType(classCastException11, "string-copy!", 3, obj135);
                        throw th28;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th29 = th6;
                    new WrongType(classCastException12, "string-copy!", 2, obj133);
                    throw th29;
                }
            case 302:
                Object obj140 = objArr2[0];
                Object obj141 = obj140;
                try {
                    CharSequence charSequence2 = (CharSequence) obj140;
                    Object obj142 = objArr2[1];
                    Object obj143 = obj142;
                    try {
                        int intValue9 = ((Number) obj142).intValue();
                        Object obj144 = objArr2[2];
                        Object obj145 = obj144;
                        try {
                            CharSequence charSequence3 = (CharSequence) obj144;
                            Object obj146 = objArr2[3];
                            Object obj147 = obj146;
                            try {
                                int intValue10 = ((Number) obj146).intValue();
                                Object obj148 = objArr2[4];
                                Object obj149 = obj148;
                                try {
                                    return $PcStringCopy$Ex(charSequence2, intValue9, charSequence3, intValue10, ((Number) obj148).intValue());
                                } catch (ClassCastException e13) {
                                    ClassCastException classCastException13 = e13;
                                    Throwable th30 = th5;
                                    new WrongType(classCastException13, "%string-copy!", 5, obj149);
                                    throw th30;
                                }
                            } catch (ClassCastException e14) {
                                ClassCastException classCastException14 = e14;
                                Throwable th31 = th4;
                                new WrongType(classCastException14, "%string-copy!", 4, obj147);
                                throw th31;
                            }
                        } catch (ClassCastException e15) {
                            ClassCastException classCastException15 = e15;
                            Throwable th32 = th3;
                            new WrongType(classCastException15, "%string-copy!", 3, obj145);
                            throw th32;
                        }
                    } catch (ClassCastException e16) {
                        ClassCastException classCastException16 = e16;
                        Throwable th33 = th2;
                        new WrongType(classCastException16, "%string-copy!", 2, obj143);
                        throw th33;
                    }
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th34 = th;
                    new WrongType(classCastException17, "%string-copy!", 1, obj141);
                    throw th34;
                }
            case 303:
                Object obj150 = objArr2[0];
                Object obj151 = objArr2[1];
                int length57 = objArr2.length - 2;
                Object[] objArr113 = new Object[length57];
                while (true) {
                    length57--;
                    if (length57 < 0) {
                        return stringContains$V(obj150, obj151, objArr113);
                    }
                    Object[] objArr114 = objArr113;
                    objArr113 = objArr114;
                    objArr114[length57] = objArr2[length57 + 2];
                }
            case 304:
                Object obj152 = objArr2[0];
                Object obj153 = objArr2[1];
                int length58 = objArr2.length - 2;
                Object[] objArr115 = new Object[length58];
                while (true) {
                    length58--;
                    if (length58 < 0) {
                        return stringContainsCi$V(obj152, obj153, objArr115);
                    }
                    Object[] objArr116 = objArr115;
                    objArr115 = objArr116;
                    objArr116[length58] = objArr2[length58 + 2];
                }
            case 305:
                return $PcKmpSearch(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6]);
            case ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED:
                Object obj154 = objArr2[0];
                int length59 = objArr2.length - 1;
                Object[] objArr117 = new Object[length59];
                while (true) {
                    length59--;
                    if (length59 < 0) {
                        return makeKmpRestartVector$V(obj154, objArr117);
                    }
                    Object[] objArr118 = objArr117;
                    objArr117 = objArr118;
                    objArr118[length59] = objArr2[length59 + 1];
                }
            case 307:
                return kmpStep(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED:
                Object obj155 = objArr2[0];
                Object obj156 = objArr2[1];
                Object obj157 = objArr2[2];
                Object obj158 = objArr2[3];
                int length60 = objArr2.length - 4;
                Object[] objArr119 = new Object[length60];
                while (true) {
                    length60--;
                    if (length60 < 0) {
                        return stringKmpPartialSearch$V(obj155, obj156, obj157, obj158, objArr119);
                    }
                    Object[] objArr120 = objArr119;
                    objArr119 = objArr120;
                    objArr120[length60] = objArr2[length60 + 4];
                }
            case ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED:
                Object obj159 = objArr2[0];
                int length61 = objArr2.length - 1;
                Object[] objArr121 = new Object[length61];
                while (true) {
                    length61--;
                    if (length61 < 0) {
                        return stringReverse$V(obj159, objArr121);
                    }
                    Object[] objArr122 = objArr121;
                    objArr121 = objArr122;
                    objArr122[length61] = objArr2[length61 + 1];
                }
            case ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED:
                Object obj160 = objArr2[0];
                int length62 = objArr2.length - 1;
                Object[] objArr123 = new Object[length62];
                while (true) {
                    length62--;
                    if (length62 < 0) {
                        return stringReverse$Ex$V(obj160, objArr123);
                    }
                    Object[] objArr124 = objArr123;
                    objArr123 = objArr124;
                    objArr124[length62] = objArr2[length62 + 1];
                }
            case ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED:
                Object obj161 = objArr2[0];
                int length63 = objArr2.length - 1;
                Object[] objArr125 = new Object[length63];
                while (true) {
                    length63--;
                    if (length63 < 0) {
                        return string$To$List$V(obj161, objArr125);
                    }
                    Object[] objArr126 = objArr125;
                    objArr125 = objArr126;
                    objArr126[length63] = objArr2[length63 + 1];
                }
            case ErrorMessages.ERROR_TWITTER_SEARCH_FAILED:
                return stringAppend$SlShared$V(objArr2);
            case 317:
                Object obj162 = objArr2[0];
                int length64 = objArr2.length - 1;
                Object[] objArr127 = new Object[length64];
                while (true) {
                    length64--;
                    if (length64 < 0) {
                        return stringConcatenateReverse$V(obj162, objArr127);
                    }
                    Object[] objArr128 = objArr127;
                    objArr127 = objArr128;
                    objArr128[length64] = objArr2[length64 + 1];
                }
            case 318:
                Object obj163 = objArr2[0];
                int length65 = objArr2.length - 1;
                Object[] objArr129 = new Object[length65];
                while (true) {
                    length65--;
                    if (length65 < 0) {
                        return stringConcatenateReverse$SlShared$V(obj163, objArr129);
                    }
                    Object[] objArr130 = objArr129;
                    objArr129 = objArr130;
                    objArr130[length65] = objArr2[length65 + 1];
                }
            case ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION:
                Object obj164 = objArr2[0];
                Object obj165 = objArr2[1];
                Object obj166 = objArr2[2];
                Object obj167 = objArr2[3];
                int length66 = objArr2.length - 4;
                Object[] objArr131 = new Object[length66];
                while (true) {
                    length66--;
                    if (length66 < 0) {
                        return stringReplace$V(obj164, obj165, obj166, obj167, objArr131);
                    }
                    Object[] objArr132 = objArr131;
                    objArr131 = objArr132;
                    objArr132[length66] = objArr2[length66 + 4];
                }
            case 321:
                Object obj168 = objArr2[0];
                int length67 = objArr2.length - 1;
                Object[] objArr133 = new Object[length67];
                while (true) {
                    length67--;
                    if (length67 < 0) {
                        return stringTokenize$V(obj168, objArr133);
                    }
                    Object[] objArr134 = objArr133;
                    objArr133 = objArr134;
                    objArr134[length67] = objArr2[length67 + 1];
                }
            case 323:
                Object obj169 = objArr2[0];
                Object obj170 = objArr2[1];
                int length68 = objArr2.length - 2;
                Object[] objArr135 = new Object[length68];
                while (true) {
                    length68--;
                    if (length68 < 0) {
                        return xsubstring$V(obj169, obj170, objArr135);
                    }
                    Object[] objArr136 = objArr135;
                    objArr135 = objArr136;
                    objArr136[length68] = objArr2[length68 + 2];
                }
            case 326:
                Object obj171 = objArr2[0];
                Object obj172 = objArr2[1];
                Object obj173 = objArr2[2];
                Object obj174 = objArr2[3];
                int length69 = objArr2.length - 4;
                Object[] objArr137 = new Object[length69];
                while (true) {
                    length69--;
                    if (length69 < 0) {
                        return stringXcopy$Ex$V(obj171, obj172, obj173, obj174, objArr137);
                    }
                    Object[] objArr138 = objArr137;
                    objArr137 = objArr138;
                    objArr138[length69] = objArr2[length69 + 4];
                }
            case 327:
                return $PcMultispanRepcopy$Ex(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6]);
            case 328:
                Object obj175 = objArr2[0];
                int length70 = objArr2.length - 1;
                Object[] objArr139 = new Object[length70];
                while (true) {
                    length70--;
                    if (length70 < 0) {
                        return stringJoin$V(obj175, objArr139);
                    }
                    Object[] objArr140 = objArr139;
                    objArr139 = objArr140;
                    objArr140[length70] = objArr2[length70 + 1];
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    public static Object lambda222buildit(Object lis, Object obj) {
        frame96 frame962;
        new frame96();
        frame96 frame963 = frame962;
        frame963.f609final = obj;
        return frame963.lambda223recur(lis);
    }

    /* compiled from: srfi13.scm */
    public class frame96 extends ModuleBody {

        /* renamed from: final  reason: not valid java name */
        Object f609final;

        public frame96() {
        }

        public Object lambda223recur(Object obj) {
            Object obj2;
            Object lis = obj;
            if (C1245lists.isPair(lis)) {
                try {
                    obj2 = C1245lists.cons(srfi13.loc$delim.get(), C1245lists.cons(C1245lists.car.apply1(lis), lambda223recur(C1245lists.cdr.apply1(lis))));
                } catch (UnboundLocationException e) {
                    UnboundLocationException unboundLocationException = e;
                    UnboundLocationException unboundLocationException2 = unboundLocationException;
                    unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1857, 13);
                    throw unboundLocationException2;
                }
            } else {
                obj2 = this.f609final;
            }
            return obj2;
        }
    }
}
