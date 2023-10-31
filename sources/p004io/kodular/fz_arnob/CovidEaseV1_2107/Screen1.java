package p004io.kodular.fz_arnob.CovidEaseV1_2107;

import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.app.NotificationCompat;
import android.support.p000v4.view.InputDeviceCompat;
import android.support.p003v7.widget.helper.ItemTouchHelper;
import com.LukeGackle.JSONTools;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.ActivityStarter;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.CheckBox;
import com.google.appinventor.components.runtime.Clock;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.FirebaseDB;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.GoogleMap;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.KodularBottomNavigation;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListView;
import com.google.appinventor.components.runtime.LocationSensor;
import com.google.appinventor.components.runtime.MakeroidAnimationUtilities;
import com.google.appinventor.components.runtime.MakeroidCircularProgress;
import com.google.appinventor.components.runtime.Network;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.Package;
import com.google.appinventor.components.runtime.SpaceView;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.TinyDB;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.WebViewer;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.RuntimeErrorAlert;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import com.google.youngandroid.C1227runtime;
import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import kawa.Telnet;
import kawa.lang.Promise;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.require;
import org.shaded.apache.http.HttpStatus;

/* renamed from: io.kodular.fz_arnob.CovidEaseV1_2107.Screen1 */
/* compiled from: Screen1.yail */
public class Screen1 extends Form implements Runnable {
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final PairWithPosition Lit100 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118658), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118650);
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final PairWithPosition Lit104 = PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118881);
    static final IntNum Lit105 = IntNum.make(3);
    static final PairWithPosition Lit106 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118918), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118910);
    static final SimpleSymbol Lit107;
    static final PairWithPosition Lit108 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 114820), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 114815);
    static final PairWithPosition Lit109 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 114926), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 114921);
    static final SimpleSymbol Lit11;
    static final PairWithPosition Lit110 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115336), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115330);
    static final PairWithPosition Lit111 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115366);
    static final PairWithPosition Lit112 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115766), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115760);
    static final PairWithPosition Lit113 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115777);
    static final PairWithPosition Lit114 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115813), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115807);
    static final PairWithPosition Lit115 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115843);
    static final PairWithPosition Lit116 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115955);
    static final PairWithPosition Lit117 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115982), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115976);
    static final PairWithPosition Lit118 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116010), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116005);
    static final PairWithPosition Lit119 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116144), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116138);
    static final SimpleSymbol Lit12;
    static final PairWithPosition Lit120 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116345), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116339);
    static final PairWithPosition Lit121 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116363), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116357);
    static final PairWithPosition Lit122 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116615), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116610);
    static final PairWithPosition Lit123 = PairWithPosition.make(Lit48, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116638);
    static final PairWithPosition Lit124 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116912), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116906);
    static final PairWithPosition Lit125 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116923);
    static final PairWithPosition Lit126 = PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116952);
    static final PairWithPosition Lit127 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116989), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116981);
    static final PairWithPosition Lit128 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117185), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117179);
    static final PairWithPosition Lit129 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117215);
    static final SimpleSymbol Lit13;
    static final PairWithPosition Lit130 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117615), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117609);
    static final PairWithPosition Lit131 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117626);
    static final PairWithPosition Lit132 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117662), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117656);
    static final PairWithPosition Lit133 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117692);
    static final PairWithPosition Lit134 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117804);
    static final PairWithPosition Lit135 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117831), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117825);
    static final PairWithPosition Lit136 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117859), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117854);
    static final PairWithPosition Lit137 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117993), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117987);
    static final PairWithPosition Lit138 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118194), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118188);
    static final PairWithPosition Lit139 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118212), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118206);
    static final SimpleSymbol Lit14;
    static final PairWithPosition Lit140 = PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118621);
    static final PairWithPosition Lit141 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118658), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118650);
    static final PairWithPosition Lit142 = PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118881);
    static final PairWithPosition Lit143 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118918), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118910);
    static final SimpleSymbol Lit144;
    static final SimpleSymbol Lit145;
    static final IntNum Lit146;
    static final SimpleSymbol Lit147;
    static final SimpleSymbol Lit148;
    static final IntNum Lit149;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit150;
    static final SimpleSymbol Lit151;
    static final SimpleSymbol Lit152;
    static final SimpleSymbol Lit153;
    static final SimpleSymbol Lit154;
    static final IntNum Lit155;
    static final SimpleSymbol Lit156;
    static final SimpleSymbol Lit157;
    static final SimpleSymbol Lit158;
    static final IntNum Lit159;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit160;
    static final IntNum Lit161;
    static final SimpleSymbol Lit162;
    static final SimpleSymbol Lit163;
    static final SimpleSymbol Lit164;
    static final SimpleSymbol Lit165;
    static final SimpleSymbol Lit166;
    static final SimpleSymbol Lit167;
    static final SimpleSymbol Lit168;
    static final PairWithPosition Lit169 = PairWithPosition.make(Lit944, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, PairWithPosition.make(Lit48, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 204951), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 204946), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 204941), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 204936), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 204925);
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit170;
    static final SimpleSymbol Lit171;
    static final PairWithPosition Lit172 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205099), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205094);
    static final SimpleSymbol Lit173;
    static final SimpleSymbol Lit174;
    static final IntNum Lit175 = IntNum.make(10);
    static final SimpleSymbol Lit176;
    static final SimpleSymbol Lit177;
    static final PairWithPosition Lit178 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205272), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205267), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205259);
    static final PairWithPosition Lit179 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205376), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205371), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205363);
    static final SimpleSymbol Lit18;
    static final PairWithPosition Lit180 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205482), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205477), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205469);
    static final IntNum Lit181 = IntNum.make(4);
    static final PairWithPosition Lit182 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205581), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205576), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 205568);
    static final SimpleSymbol Lit183;
    static final SimpleSymbol Lit184;
    static final SimpleSymbol Lit185;
    static final SimpleSymbol Lit186;
    static final SimpleSymbol Lit187;
    static final SimpleSymbol Lit188;
    static final SimpleSymbol Lit189;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit190;
    static final SimpleSymbol Lit191;
    static final PairWithPosition Lit192 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 206348), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 206342);
    static final SimpleSymbol Lit193;
    static final SimpleSymbol Lit194;
    static final SimpleSymbol Lit195;
    static final SimpleSymbol Lit196;
    static final SimpleSymbol Lit197;
    static final SimpleSymbol Lit198;
    static final SimpleSymbol Lit199;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit200;
    static final SimpleSymbol Lit201;
    static final SimpleSymbol Lit202;
    static final PairWithPosition Lit203 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 221360), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 221355);
    static final SimpleSymbol Lit204;
    static final SimpleSymbol Lit205;
    static final PairWithPosition Lit206 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 221662), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 221657);
    static final SimpleSymbol Lit207;
    static final PairWithPosition Lit208 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 221955), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 221950);
    static final SimpleSymbol Lit209;
    static final IntNum Lit21 = IntNum.make(60);
    static final SimpleSymbol Lit210;
    static final SimpleSymbol Lit211;
    static final SimpleSymbol Lit212;
    static final SimpleSymbol Lit213;
    static final SimpleSymbol Lit214;
    static final PairWithPosition Lit215 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 223025), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 223020);
    static final PairWithPosition Lit216 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 223199), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 223194);
    static final PairWithPosition Lit217 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 223377), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 223372);
    static final SimpleSymbol Lit218;
    static final PairWithPosition Lit219 = PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 223463);
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit220;
    static final SimpleSymbol Lit221;
    static final SimpleSymbol Lit222;
    static final FString Lit223;
    static final SimpleSymbol Lit224;
    static final SimpleSymbol Lit225;
    static final SimpleSymbol Lit226;
    static final IntNum Lit227;
    static final SimpleSymbol Lit228;
    static final IntNum Lit229 = IntNum.make(-1075);
    static final PairWithPosition Lit23 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102525), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102521), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102517), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102513), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102508);
    static final SimpleSymbol Lit230;
    static final FString Lit231;
    static final FString Lit232;
    static final SimpleSymbol Lit233;
    static final IntNum Lit234 = IntNum.make(16777215);
    static final SimpleSymbol Lit235;
    static final IntNum Lit236 = IntNum.make(-2);
    static final FString Lit237;
    static final FString Lit238;
    static final SimpleSymbol Lit239;
    static final PairWithPosition Lit24 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102525), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102521), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102517), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102513), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 102508);
    static final FString Lit240;
    static final FString Lit241;
    static final SimpleSymbol Lit242;
    static final SimpleSymbol Lit243;
    static final IntNum Lit244 = IntNum.make(20);
    static final SimpleSymbol Lit245;
    static final IntNum Lit246 = IntNum.make(6);
    static final SimpleSymbol Lit247;
    static final IntNum Lit248 = IntNum.make(30);
    static final SimpleSymbol Lit249;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit250;
    static final IntNum Lit251;
    static final FString Lit252;
    static final SimpleSymbol Lit253;
    static final PairWithPosition Lit254 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 499829), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 499824);
    static final SimpleSymbol Lit255;
    static final DFloNum Lit256 = DFloNum.make(23.760707d);
    static final DFloNum Lit257 = DFloNum.make(90.391571d);
    static final IntNum Lit258 = IntNum.make(13);
    static final PairWithPosition Lit259 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 499951), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 499944), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 499936);
    static final SimpleSymbol Lit26;
    static final PairWithPosition Lit260 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 500132), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 500127);
    static final SimpleSymbol Lit261;
    static final SimpleSymbol Lit262;
    static final SimpleSymbol Lit263;
    static final SimpleSymbol Lit264;
    static final SimpleSymbol Lit265;
    static final SimpleSymbol Lit266;
    static final SimpleSymbol Lit267;
    static final FString Lit268;
    static final SimpleSymbol Lit269;
    static final SimpleSymbol Lit27;
    static final IntNum Lit270 = IntNum.make(50);
    static final SimpleSymbol Lit271;
    static final FString Lit272;
    static final FString Lit273;
    static final SimpleSymbol Lit274;
    static final IntNum Lit275 = IntNum.make(-1003);
    static final FString Lit276;
    static final FString Lit277;
    static final IntNum Lit278 = IntNum.make(16);
    static final IntNum Lit279;
    static final SimpleSymbol Lit28;
    static final FString Lit280;
    static final FString Lit281;
    static final SimpleSymbol Lit282;
    static final IntNum Lit283 = IntNum.make(-1003);
    static final FString Lit284;
    static final FString Lit285;
    static final SimpleSymbol Lit286;
    static final FString Lit287;
    static final FString Lit288;
    static final SimpleSymbol Lit289;
    static final PairWithPosition Lit29 = PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 106632);
    static final IntNum Lit290;
    static final IntNum Lit291 = IntNum.make(14);
    static final SimpleSymbol Lit292;
    static final FString Lit293;
    static final SimpleSymbol Lit294;
    static final SimpleSymbol Lit295;
    static final FString Lit296;
    static final IntNum Lit297;
    static final FString Lit298;
    static final PairWithPosition Lit299 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 831605), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 831600);
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit30;
    static final DFloNum Lit300 = DFloNum.make(23.760707d);
    static final DFloNum Lit301 = DFloNum.make(90.391571d);
    static final PairWithPosition Lit302 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 831727), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 831720), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 831712);
    static final SimpleSymbol Lit303;
    static final SimpleSymbol Lit304;
    static final SimpleSymbol Lit305;
    static final PairWithPosition Lit306 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 832182), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 832177);
    static final SimpleSymbol Lit307;
    static final PairWithPosition Lit308 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 832567), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 832562);
    static final SimpleSymbol Lit309;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit310;
    static final FString Lit311;
    static final SimpleSymbol Lit312;
    static final IntNum Lit313 = IntNum.make(15);
    static final FString Lit314;
    static final FString Lit315;
    static final IntNum Lit316 = IntNum.make(16777215);
    static final FString Lit317;
    static final FString Lit318;
    static final SimpleSymbol Lit319;
    static final SimpleSymbol Lit32;
    static final IntNum Lit320;
    static final FString Lit321;
    static final FString Lit322;
    static final SimpleSymbol Lit323;
    static final IntNum Lit324;
    static final FString Lit325;
    static final FString Lit326;
    static final SimpleSymbol Lit327;
    static final IntNum Lit328;
    static final FString Lit329;
    static final PairWithPosition Lit33 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 106928);
    static final FString Lit330;
    static final SimpleSymbol Lit331;
    static final IntNum Lit332 = IntNum.make(-1015);
    static final FString Lit333;
    static final FString Lit334;
    static final SimpleSymbol Lit335;
    static final SimpleSymbol Lit336;
    static final IntNum Lit337;
    static final FString Lit338;
    static final FString Lit339;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit340;
    static final IntNum Lit341;
    static final FString Lit342;
    static final FString Lit343;
    static final SimpleSymbol Lit344;
    static final IntNum Lit345 = IntNum.make(-1015);
    static final FString Lit346;
    static final FString Lit347;
    static final SimpleSymbol Lit348;
    static final IntNum Lit349;
    static final IntNum Lit35 = IntNum.make(1);
    static final FString Lit350;
    static final FString Lit351;
    static final SimpleSymbol Lit352;
    static final IntNum Lit353;
    static final FString Lit354;
    static final FString Lit355;
    static final SimpleSymbol Lit356;
    static final IntNum Lit357 = IntNum.make(-1015);
    static final FString Lit358;
    static final FString Lit359;
    static final PairWithPosition Lit36 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107021), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107013);
    static final SimpleSymbol Lit360;
    static final IntNum Lit361;
    static final FString Lit362;
    static final FString Lit363;
    static final SimpleSymbol Lit364;
    static final IntNum Lit365;
    static final FString Lit366;
    static final FString Lit367;
    static final SimpleSymbol Lit368;
    static final FString Lit369;
    static final SimpleSymbol Lit37;
    static final FString Lit370;
    static final SimpleSymbol Lit371;
    static final IntNum Lit372;
    static final FString Lit373;
    static final SimpleSymbol Lit374;
    static final FString Lit375;
    static final SimpleSymbol Lit376;
    static final FString Lit377;
    static final FString Lit378;
    static final IntNum Lit379;
    static final PairWithPosition Lit38 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107190), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107184);
    static final FString Lit380;
    static final FString Lit381;
    static final SimpleSymbol Lit382;
    static final SimpleSymbol Lit383;
    static final IntNum Lit384;
    static final FString Lit385;
    static final FString Lit386;
    static final SimpleSymbol Lit387;
    static final SimpleSymbol Lit388;
    static final SimpleSymbol Lit389;
    static final PairWithPosition Lit39 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107230), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107225);
    static final FString Lit390;
    static final SimpleSymbol Lit391;
    static final PairWithPosition Lit392 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 1880208), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 1880200);
    static final IntNum Lit393 = IntNum.make(100);
    static final PairWithPosition Lit394 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 1880305), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 1880297);
    static final PairWithPosition Lit395 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 1880325), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 1880320);
    static final PairWithPosition Lit396 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 1880569), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 1880564);
    static final SimpleSymbol Lit397;
    static final SimpleSymbol Lit398;
    static final FString Lit399;
    static final SimpleSymbol Lit4;
    static final PairWithPosition Lit40 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107371), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107365);
    static final SimpleSymbol Lit400;
    static final IntNum Lit401 = IntNum.make(16777215);
    static final FString Lit402;
    static final SimpleSymbol Lit403;
    static final SimpleSymbol Lit404;
    static final FString Lit405;
    static final SimpleSymbol Lit406;
    static final IntNum Lit407 = IntNum.make(17);
    static final IntNum Lit408;
    static final FString Lit409;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit410;
    static final FString Lit411;
    static final SimpleSymbol Lit412;
    static final IntNum Lit413 = IntNum.make(5);
    static final FString Lit414;
    static final FString Lit415;
    static final IntNum Lit416;
    static final FString Lit417;
    static final FString Lit418;
    static final SimpleSymbol Lit419;
    static final PairWithPosition Lit42 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107656), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107650);
    static final IntNum Lit420 = IntNum.make(-1040);
    static final IntNum Lit421 = IntNum.make(-1070);
    static final FString Lit422;
    static final FString Lit423;
    static final SimpleSymbol Lit424;
    static final IntNum Lit425 = IntNum.make(-1002);
    static final FString Lit426;
    static final FString Lit427;
    static final SimpleSymbol Lit428;
    static final IntNum Lit429 = IntNum.make(24);
    static final PairWithPosition Lit43 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107686);
    static final IntNum Lit430;
    static final FString Lit431;
    static final FString Lit432;
    static final SimpleSymbol Lit433;
    static final IntNum Lit434 = IntNum.make(-1015);
    static final FString Lit435;
    static final FString Lit436;
    static final SimpleSymbol Lit437;
    static final IntNum Lit438;
    static final IntNum Lit439 = IntNum.make(40);
    static final PairWithPosition Lit44 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107778), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107773);
    static final FString Lit440;
    static final FString Lit441;
    static final SimpleSymbol Lit442;
    static final IntNum Lit443 = IntNum.make(-1004);
    static final FString Lit444;
    static final FString Lit445;
    static final SimpleSymbol Lit446;
    static final IntNum Lit447 = IntNum.make(16777215);
    static final IntNum Lit448 = IntNum.make(-1070);
    static final FString Lit449;
    static final PairWithPosition Lit45 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107954), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107948);
    static final FString Lit450;
    static final IntNum Lit451;
    static final IntNum Lit452 = IntNum.make(-1002);
    static final FString Lit453;
    static final FString Lit454;
    static final FString Lit455;
    static final FString Lit456;
    static final FString Lit457;
    static final FString Lit458;
    static final SimpleSymbol Lit459;
    static final SimpleSymbol Lit46;
    static final IntNum Lit460 = IntNum.make(16777215);
    static final IntNum Lit461 = IntNum.make(-1080);
    static final FString Lit462;
    static final FString Lit463;
    static final SimpleSymbol Lit464;
    static final IntNum Lit465 = IntNum.make(16777215);
    static final FString Lit466;
    static final FString Lit467;
    static final SimpleSymbol Lit468;
    static final IntNum Lit469;
    static final SimpleSymbol Lit47;
    static final FString Lit470;
    static final FString Lit471;
    static final SimpleSymbol Lit472;
    static final IntNum Lit473;
    static final FString Lit474;
    static final FString Lit475;
    static final SimpleSymbol Lit476;
    static final FString Lit477;
    static final FString Lit478;
    static final SimpleSymbol Lit479;
    static final SimpleSymbol Lit48;
    static final IntNum Lit480;
    static final FString Lit481;
    static final FString Lit482;
    static final FString Lit483;
    static final PairWithPosition Lit484 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 2916496), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 2916488);
    static final PairWithPosition Lit485 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 2916593), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 2916585);
    static final PairWithPosition Lit486 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 2916613), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 2916608);
    static final PairWithPosition Lit487 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 2916952), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 2916947);
    static final SimpleSymbol Lit488;
    static final FString Lit489;
    static final PairWithPosition Lit49 = PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 106632);
    static final IntNum Lit490;
    static final FString Lit491;
    static final FString Lit492;
    static final SimpleSymbol Lit493;
    static final IntNum Lit494 = IntNum.make(16777215);
    static final IntNum Lit495 = IntNum.make(-1080);
    static final FString Lit496;
    static final FString Lit497;
    static final SimpleSymbol Lit498;
    static final IntNum Lit499 = IntNum.make(16777215);
    static final IntNum Lit5 = IntNum.make(0);
    static final PairWithPosition Lit50 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 106928);
    static final FString Lit500;
    static final FString Lit501;
    static final SimpleSymbol Lit502;
    static final IntNum Lit503;
    static final FString Lit504;
    static final FString Lit505;
    static final SimpleSymbol Lit506;
    static final IntNum Lit507;
    static final FString Lit508;
    static final FString Lit509;
    static final PairWithPosition Lit51 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107021), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107013);
    static final SimpleSymbol Lit510;
    static final FString Lit511;
    static final FString Lit512;
    static final SimpleSymbol Lit513;
    static final IntNum Lit514;
    static final FString Lit515;
    static final FString Lit516;
    static final FString Lit517;
    static final PairWithPosition Lit518 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3293328), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3293320);
    static final PairWithPosition Lit519 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3293425), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3293417);
    static final PairWithPosition Lit52 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107190), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107184);
    static final PairWithPosition Lit520 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3293445), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3293440);
    static final PairWithPosition Lit521 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3293787), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3293782);
    static final SimpleSymbol Lit522;
    static final FString Lit523;
    static final IntNum Lit524;
    static final FString Lit525;
    static final FString Lit526;
    static final SimpleSymbol Lit527;
    static final IntNum Lit528 = IntNum.make(16777215);
    static final IntNum Lit529 = IntNum.make(-1080);
    static final PairWithPosition Lit53 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107230), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107225);
    static final FString Lit530;
    static final FString Lit531;
    static final SimpleSymbol Lit532;
    static final IntNum Lit533 = IntNum.make(16777215);
    static final FString Lit534;
    static final FString Lit535;
    static final SimpleSymbol Lit536;
    static final IntNum Lit537;
    static final FString Lit538;
    static final FString Lit539;
    static final PairWithPosition Lit54 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107371), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107365);
    static final SimpleSymbol Lit540;
    static final IntNum Lit541;
    static final FString Lit542;
    static final FString Lit543;
    static final SimpleSymbol Lit544;
    static final FString Lit545;
    static final FString Lit546;
    static final SimpleSymbol Lit547;
    static final IntNum Lit548;
    static final FString Lit549;
    static final PairWithPosition Lit55 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107656), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107650);
    static final FString Lit550;
    static final FString Lit551;
    static final PairWithPosition Lit552 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3670160), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3670152);
    static final PairWithPosition Lit553 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3670257), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3670249);
    static final PairWithPosition Lit554 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3670277), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3670272);
    static final PairWithPosition Lit555 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3670619), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3670614);
    static final SimpleSymbol Lit556;
    static final FString Lit557;
    static final FString Lit558;
    static final FString Lit559;
    static final PairWithPosition Lit56 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107686);
    static final SimpleSymbol Lit560;
    static final FString Lit561;
    static final FString Lit562;
    static final SimpleSymbol Lit563;
    static final SimpleSymbol Lit564;
    static final IntNum Lit565;
    static final IntNum Lit566;
    static final FString Lit567;
    static final PairWithPosition Lit568 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3821658), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3821653);
    static final SimpleSymbol Lit569;
    static final PairWithPosition Lit57 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107778), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107773);
    static final SimpleSymbol Lit570;
    static final SimpleSymbol Lit571;
    static final SimpleSymbol Lit572;
    static final PairWithPosition Lit573 = PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3821936);
    static final SimpleSymbol Lit574;
    static final SimpleSymbol Lit575;
    static final PairWithPosition Lit576 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3829850), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3829845);
    static final PairWithPosition Lit577 = PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 3830128);
    static final SimpleSymbol Lit578;
    static final SimpleSymbol Lit579;
    static final PairWithPosition Lit58 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107954), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 107948);
    static final FString Lit580;
    static final IntNum Lit581 = IntNum.make(16843009);
    static final FString Lit582;
    static final FString Lit583;
    static final SimpleSymbol Lit584;
    static final FString Lit585;
    static final FString Lit586;
    static final IntNum Lit587;
    static final FString Lit588;
    static final FString Lit589;
    static final SimpleSymbol Lit59;
    static final IntNum Lit590;
    static final SimpleSymbol Lit591;
    static final IntNum Lit592;
    static final SimpleSymbol Lit593;
    static final SimpleSymbol Lit594;
    static final SimpleSymbol Lit595;
    static final DFloNum Lit596 = DFloNum.make(0.8d);
    static final SimpleSymbol Lit597;
    static final FString Lit598;
    static final PairWithPosition Lit599 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4059535), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4059530);
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit600;
    static final PairWithPosition Lit601 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4059898), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4059892);
    static final PairWithPosition Lit602 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4059934), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4059928);
    static final PairWithPosition Lit603 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4059964);
    static final SimpleSymbol Lit604;
    static final PairWithPosition Lit605 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060143), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060137);
    static final PairWithPosition Lit606 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060243), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060237);
    static final IntNum Lit607 = IntNum.make(25);
    static final IntNum Lit608 = IntNum.make((int) FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK);
    static final IntNum Lit609 = IntNum.make(240);
    static final SimpleSymbol Lit61;
    static final PairWithPosition Lit610 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit48, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060329), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060322), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060315), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060308), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060301), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060294), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060287), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060279);
    static final PairWithPosition Lit611 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060490), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060484);
    static final PairWithPosition Lit612 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060590), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060584);
    static final IntNum Lit613 = IntNum.make(18);
    static final PairWithPosition Lit614 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060620), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060613), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4060605);
    static final SimpleSymbol Lit615;
    static final SimpleSymbol Lit616;
    static final FString Lit617;
    static final IntNum Lit618;
    static final IntNum Lit619;
    static final PairWithPosition Lit62 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 114820), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 114815);
    static final FString Lit620;
    static final SimpleSymbol Lit621;
    static final PairWithPosition Lit622 = PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4161697);
    static final SimpleSymbol Lit623;
    static final FString Lit624;
    static final IntNum Lit625;
    static final FString Lit626;
    static final FString Lit627;
    static final FString Lit628;
    static final FString Lit629;
    static final PairWithPosition Lit63 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 114926), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 114921);
    static final FString Lit630;
    static final FString Lit631;
    static final SimpleSymbol Lit632;
    static final IntNum Lit633;
    static final FString Lit634;
    static final FString Lit635;
    static final SimpleSymbol Lit636;
    static final FString Lit637;
    static final FString Lit638;
    static final SimpleSymbol Lit639;
    static final PairWithPosition Lit64 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115336), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115330);
    static final SimpleSymbol Lit640;
    static final FString Lit641;
    static final PairWithPosition Lit642 = PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4448393);
    static final PairWithPosition Lit643 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4448694), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4448689);
    static final SimpleSymbol Lit644;
    static final PairWithPosition Lit645 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449285), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449277);
    static final PairWithPosition Lit646 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449311), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449303);
    static final PairWithPosition Lit647 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449333), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449327);
    static final PairWithPosition Lit648 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449369), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449363);
    static final PairWithPosition Lit649 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449406), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449400);
    static final PairWithPosition Lit65 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115366);
    static final SimpleSymbol Lit650;
    static final SimpleSymbol Lit651;
    static final SimpleSymbol Lit652;
    static final PairWithPosition Lit653 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449644);
    static final PairWithPosition Lit654 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449956), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449948);
    static final PairWithPosition Lit655 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449982), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449974);
    static final PairWithPosition Lit656 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450004), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4449998);
    static final PairWithPosition Lit657 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450040), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450034);
    static final PairWithPosition Lit658 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450082), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450077), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450071);
    static final SimpleSymbol Lit659;
    static final PairWithPosition Lit66 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115766), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115760);
    static final PairWithPosition Lit660 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450508), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450500);
    static final PairWithPosition Lit661 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450534), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450526);
    static final PairWithPosition Lit662 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450556), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450550);
    static final PairWithPosition Lit663 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450592), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450586);
    static final PairWithPosition Lit664 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450633), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4450627);
    static final SimpleSymbol Lit665;
    static final SimpleSymbol Lit666;
    static final SimpleSymbol Lit667;
    static final PairWithPosition Lit668 = PairWithPosition.make(Lit48, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4456531);
    static final SimpleSymbol Lit669;
    static final PairWithPosition Lit67 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115777);
    static final SimpleSymbol Lit670;
    static final PairWithPosition Lit671 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4464730), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4464725);
    static final PairWithPosition Lit672 = PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4465006);
    static final SimpleSymbol Lit673;
    static final SimpleSymbol Lit674;
    static final FString Lit675;
    static final SimpleSymbol Lit676;
    static final IntNum Lit677 = IntNum.make(16777215);
    static final IntNum Lit678 = IntNum.make(220);
    static final SimpleSymbol Lit679;
    static final PairWithPosition Lit68 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115813), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115807);
    static final FString Lit680;
    static final FString Lit681;
    static final SimpleSymbol Lit682;
    static final IntNum Lit683 = IntNum.make(16777215);
    static final FString Lit684;
    static final FString Lit685;
    static final IntNum Lit686;
    static final SimpleSymbol Lit687;
    static final SimpleSymbol Lit688;
    static final IntNum Lit689 = IntNum.make(180);
    static final PairWithPosition Lit69 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115843);
    static final IntNum Lit690;
    static final FString Lit691;
    static final PairWithPosition Lit692 = PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4698336);
    static final SimpleSymbol Lit693;
    static final SimpleSymbol Lit694;
    static final SimpleSymbol Lit695;
    static final PairWithPosition Lit696 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4698844);
    static final SimpleSymbol Lit697;
    static final PairWithPosition Lit698 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4698949);
    static final PairWithPosition Lit699 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4698972), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4698964);
    static final SimpleSymbol Lit7;
    static final PairWithPosition Lit70 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115955);
    static final PairWithPosition Lit700 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699124);
    static final SimpleSymbol Lit701;
    static final PairWithPosition Lit702 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699225);
    static final PairWithPosition Lit703 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699248), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699240);
    static final IntNum Lit704 = IntNum.make(1000);
    static final PairWithPosition Lit705 = PairWithPosition.make(Lit944, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699304), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699297), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699290), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699283), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699272);
    static final PairWithPosition Lit706 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699634);
    static final PairWithPosition Lit707 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699735);
    static final PairWithPosition Lit708 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699758), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699750);
    static final PairWithPosition Lit709 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4699910);
    static final PairWithPosition Lit71 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115982), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 115976);
    static final PairWithPosition Lit710 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4700015);
    static final PairWithPosition Lit711 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4700038), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4700030);
    static final PairWithPosition Lit712 = PairWithPosition.make(Lit944, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4700094), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4700087), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4700080), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4700073), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4700062);
    static final SimpleSymbol Lit713;
    static final FString Lit714;
    static final IntNum Lit715 = IntNum.make(16777215);
    static final FString Lit716;
    static final FString Lit717;
    static final SimpleSymbol Lit718;
    static final SimpleSymbol Lit719;
    static final PairWithPosition Lit72 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116010), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116005);
    static final IntNum Lit720;
    static final SimpleSymbol Lit721;
    static final IntNum Lit722;
    static final FString Lit723;
    static final PairWithPosition Lit724 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4804819), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4804812), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4804806);
    static final PairWithPosition Lit725 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4804988), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4804981), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4804975);
    static final SimpleSymbol Lit726;
    static final SimpleSymbol Lit727;
    static final FString Lit728;
    static final SimpleSymbol Lit729;
    static final PairWithPosition Lit73 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116144), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116138);
    static final IntNum Lit730;
    static final IntNum Lit731;
    static final FString Lit732;
    static final PairWithPosition Lit733 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4866260), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4866253), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4866247);
    static final PairWithPosition Lit734 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4866429), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4866422), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4866416);
    static final SimpleSymbol Lit735;
    static final FString Lit736;
    static final SimpleSymbol Lit737;
    static final IntNum Lit738;
    static final IntNum Lit739;
    static final PairWithPosition Lit74 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116345), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116339);
    static final FString Lit740;
    static final PairWithPosition Lit741 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4927698), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4927691), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4927685);
    static final PairWithPosition Lit742 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4927867), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4927860), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4927854);
    static final SimpleSymbol Lit743;
    static final FString Lit744;
    static final SimpleSymbol Lit745;
    static final IntNum Lit746;
    static final IntNum Lit747;
    static final FString Lit748;
    static final PairWithPosition Lit749 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4989138), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4989131), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4989125);
    static final PairWithPosition Lit75 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116363), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116357);
    static final PairWithPosition Lit750 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4989307), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4989300), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 4989294);
    static final SimpleSymbol Lit751;
    static final FString Lit752;
    static final SimpleSymbol Lit753;
    static final IntNum Lit754;
    static final IntNum Lit755;
    static final FString Lit756;
    static final PairWithPosition Lit757 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5050579), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5050572), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5050566);
    static final PairWithPosition Lit758 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5050748), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5050741), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5050735);
    static final SimpleSymbol Lit759;
    static final PairWithPosition Lit76 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116615), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116610);
    static final FString Lit760;
    static final SimpleSymbol Lit761;
    static final FString Lit762;
    static final FString Lit763;
    static final FString Lit764;
    static final FString Lit765;
    static final FString Lit766;
    static final FString Lit767;
    static final IntNum Lit768;
    static final IntNum Lit769 = IntNum.make((int) FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN);
    static final PairWithPosition Lit77 = PairWithPosition.make(Lit48, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116638);
    static final FString Lit770;
    static final FString Lit771;
    static final SimpleSymbol Lit772;
    static final IntNum Lit773;
    static final FString Lit774;
    static final SimpleSymbol Lit775;
    static final FString Lit776;
    static final SimpleSymbol Lit777;
    static final IntNum Lit778 = IntNum.make(16843009);
    static final FString Lit779;
    static final PairWithPosition Lit78 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116912), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116906);
    static final FString Lit780;
    static final IntNum Lit781;
    static final DFloNum Lit782 = DFloNum.make((double) 15);
    static final FString Lit783;
    static final SimpleSymbol Lit784;
    static final FString Lit785;
    static final SimpleSymbol Lit786;
    static final IntNum Lit787 = IntNum.make(12);
    static final FString Lit788;
    static final FString Lit789;
    static final PairWithPosition Lit79 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116923);
    static final IntNum Lit790 = IntNum.make(56656011);
    static final IntNum Lit791 = IntNum.make(1969316193);
    static final DFloNum Lit792 = DFloNum.make(0.8d);
    static final SimpleSymbol Lit793;
    static final FString Lit794;
    static final FString Lit795;
    static final SimpleSymbol Lit796;
    static final IntNum Lit797 = IntNum.make(31010112);
    static final FString Lit798;
    static final PairWithPosition Lit799 = PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5673123);
    static final SimpleSymbol Lit8;
    static final PairWithPosition Lit80 = PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116952);
    static final SimpleSymbol Lit800;
    static final FString Lit801;
    static final IntNum Lit802;
    static final SimpleSymbol Lit803;
    static final IntNum Lit804;
    static final SimpleSymbol Lit805;
    static final IntNum Lit806;
    static final FString Lit807;
    static final SimpleSymbol Lit808;
    static final PairWithPosition Lit809 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5726307), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5726302);
    static final PairWithPosition Lit81 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116989), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 116981);
    static final PairWithPosition Lit810 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5726779), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5726774);
    static final SimpleSymbol Lit811;
    static final PairWithPosition Lit812 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5726906);
    static final PairWithPosition Lit813 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5727368), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5727363);
    static final PairWithPosition Lit814 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5727490);
    static final PairWithPosition Lit815 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5727945), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5727940);
    static final PairWithPosition Lit816 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5728067);
    static final SimpleSymbol Lit817;
    static final SimpleSymbol Lit818;
    static final FString Lit819;
    static final PairWithPosition Lit82 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117185), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117179);
    static final IntNum Lit820;
    static final SimpleSymbol Lit821;
    static final SimpleSymbol Lit822;
    static final FString Lit823;
    static final FString Lit824;
    static final FString Lit825;
    static final FString Lit826;
    static final SimpleSymbol Lit827;
    static final FString Lit828;
    static final FString Lit829;
    static final PairWithPosition Lit83 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117215);
    static final FString Lit830;
    static final FString Lit831;
    static final SimpleSymbol Lit832;
    static final SimpleSymbol Lit833;
    static final SimpleSymbol Lit834;
    static final SimpleSymbol Lit835;
    static final FString Lit836;
    static final SimpleSymbol Lit837;
    static final PairWithPosition Lit838 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894243), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894238);
    static final SimpleSymbol Lit839;
    static final PairWithPosition Lit84 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117615), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117609);
    static final PairWithPosition Lit840 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894353), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894348);
    static final PairWithPosition Lit841 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894755), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894749);
    static final PairWithPosition Lit842 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894874), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894869);
    static final PairWithPosition Lit843 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894979), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5894974);
    static final PairWithPosition Lit844 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895092), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895087);
    static final SimpleSymbol Lit845;
    static final PairWithPosition Lit846 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895417), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895412), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895406);
    static final PairWithPosition Lit847 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895447), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895441);
    static final PairWithPosition Lit848 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895630), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895625), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895619);
    static final PairWithPosition Lit849 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895660), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895654);
    static final PairWithPosition Lit85 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117626);
    static final PairWithPosition Lit850 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895845), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895840), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895834);
    static final PairWithPosition Lit851 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895875), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5895869);
    static final PairWithPosition Lit852 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896069), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896063);
    static final PairWithPosition Lit853 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896192), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896184);
    static final PairWithPosition Lit854 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896319), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896313);
    static final PairWithPosition Lit855 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896461), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896455);
    static final PairWithPosition Lit856 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896581);
    static final PairWithPosition Lit857 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896673), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896668);
    static final PairWithPosition Lit858 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896849), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896843);
    static final PairWithPosition Lit859 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5897002), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5896997);
    static final IntNum Lit86 = IntNum.make(2);
    static final IntNum Lit860 = IntNum.make(90);
    static final SimpleSymbol Lit861;
    static final SimpleSymbol Lit862;
    static final PairWithPosition Lit863 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5897291), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5897285);
    static final PairWithPosition Lit864 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5897310), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5897305);
    static final SimpleSymbol Lit865;
    static final PairWithPosition Lit866 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5897668), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5897662);
    static final PairWithPosition Lit867 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898183), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898177);
    static final PairWithPosition Lit868 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898324), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898318);
    static final PairWithPosition Lit869 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898452), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898446);
    static final PairWithPosition Lit87 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117662), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117656);
    static final PairWithPosition Lit870;
    static final PairWithPosition Lit871 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898725), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898720);
    static final SimpleSymbol Lit872;
    static final SimpleSymbol Lit873;
    static final FString Lit874;
    static final FString Lit875;
    static final FString Lit876;
    static final SimpleSymbol Lit877;
    static final SimpleSymbol Lit878;
    static final FString Lit879;
    static final PairWithPosition Lit88 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117692);
    static final PairWithPosition Lit880 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5967975), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5967970);
    static final SimpleSymbol Lit881;
    static final SimpleSymbol Lit882;
    static final SimpleSymbol Lit883;
    static final FString Lit884;
    static final FString Lit885;
    static final FString Lit886;
    static final FString Lit887;
    static final PairWithPosition Lit888 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6041709), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6041704);
    static final SimpleSymbol Lit889;
    static final PairWithPosition Lit89 = PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117804);
    static final PairWithPosition Lit890 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6041963);
    static final PairWithPosition Lit891 = PairWithPosition.make(Lit944, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6042068);
    static final PairWithPosition Lit892 = PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6042091), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6042083);
    static final IntNum Lit893 = IntNum.make((int) HttpStatus.SC_INTERNAL_SERVER_ERROR);
    static final PairWithPosition Lit894 = PairWithPosition.make(Lit944, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6042136), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6042129), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6042122), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6042111);
    static final SimpleSymbol Lit895;
    static final FString Lit896;
    static final FString Lit897;
    static final SimpleSymbol Lit898;
    static final SimpleSymbol Lit899;
    static final SimpleSymbol Lit9;
    static final PairWithPosition Lit90 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117831), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117825);
    static final PairWithPosition Lit900;
    static final SimpleSymbol Lit901;
    static final PairWithPosition Lit902 = PairWithPosition.make(Lit48, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095128);
    static final SimpleSymbol Lit903;
    static final SimpleSymbol Lit904;
    static final PairWithPosition Lit905 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095309), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095304);
    static final PairWithPosition Lit906;
    static final DFloNum Lit907 = DFloNum.make(23.760707d);
    static final DFloNum Lit908 = DFloNum.make(90.391571d);
    static final PairWithPosition Lit909;
    static final PairWithPosition Lit91 = PairWithPosition.make(Lit943, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117859), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117854);
    static final SimpleSymbol Lit910;
    static final FString Lit911;
    static final SimpleSymbol Lit912;
    static final SimpleSymbol Lit913;
    static final FString Lit914;
    static final FString Lit915;
    static final FString Lit916;
    static final FString Lit917;
    static final FString Lit918;
    static final SimpleSymbol Lit919;
    static final PairWithPosition Lit92 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117993), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 117987);
    static final FString Lit920;
    static final FString Lit921;
    static final FString Lit922;
    static final SimpleSymbol Lit923;
    static final IntNum Lit924 = IntNum.make((int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
    static final FString Lit925;
    static final FString Lit926;
    static final FString Lit927;
    static final SimpleSymbol Lit928;
    static final SimpleSymbol Lit929;
    static final PairWithPosition Lit93 = PairWithPosition.make(Lit107, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118194), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118188);
    static final SimpleSymbol Lit930;
    static final SimpleSymbol Lit931;
    static final SimpleSymbol Lit932;
    static final SimpleSymbol Lit933;
    static final SimpleSymbol Lit934;
    static final SimpleSymbol Lit935;
    static final SimpleSymbol Lit936;
    static final SimpleSymbol Lit937;
    static final SimpleSymbol Lit938;
    static final SimpleSymbol Lit939;
    static final PairWithPosition Lit94 = PairWithPosition.make(Lit103, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118212), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118206);
    static final SimpleSymbol Lit940;
    static final SimpleSymbol Lit941;
    static final SimpleSymbol Lit942;
    static final SimpleSymbol Lit943;
    static final SimpleSymbol Lit944;
    static final SimpleSymbol Lit95;
    static final SimpleSymbol Lit96;
    static final SimpleSymbol Lit97;
    static final SimpleSymbol Lit98;
    static final PairWithPosition Lit99 = PairWithPosition.make(Lit103, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 118621);
    public static Screen1 Screen1;
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn100 = null;
    static final ModuleMethod lambda$Fn101 = null;
    static final ModuleMethod lambda$Fn102 = null;
    static final ModuleMethod lambda$Fn103 = null;
    static final ModuleMethod lambda$Fn104 = null;
    static final ModuleMethod lambda$Fn105 = null;
    static final ModuleMethod lambda$Fn106 = null;
    static final ModuleMethod lambda$Fn107 = null;
    static final ModuleMethod lambda$Fn108 = null;
    static final ModuleMethod lambda$Fn109 = null;
    static final ModuleMethod lambda$Fn11 = null;
    static final ModuleMethod lambda$Fn110 = null;
    static final ModuleMethod lambda$Fn111 = null;
    static final ModuleMethod lambda$Fn112 = null;
    static final ModuleMethod lambda$Fn113 = null;
    static final ModuleMethod lambda$Fn114 = null;
    static final ModuleMethod lambda$Fn115 = null;
    static final ModuleMethod lambda$Fn116 = null;
    static final ModuleMethod lambda$Fn117 = null;
    static final ModuleMethod lambda$Fn118 = null;
    static final ModuleMethod lambda$Fn119 = null;
    static final ModuleMethod lambda$Fn12 = null;
    static final ModuleMethod lambda$Fn120 = null;
    static final ModuleMethod lambda$Fn121 = null;
    static final ModuleMethod lambda$Fn122 = null;
    static final ModuleMethod lambda$Fn123 = null;
    static final ModuleMethod lambda$Fn124 = null;
    static final ModuleMethod lambda$Fn125 = null;
    static final ModuleMethod lambda$Fn126 = null;
    static final ModuleMethod lambda$Fn127 = null;
    static final ModuleMethod lambda$Fn128 = null;
    static final ModuleMethod lambda$Fn129 = null;
    static final ModuleMethod lambda$Fn13 = null;
    static final ModuleMethod lambda$Fn130 = null;
    static final ModuleMethod lambda$Fn131 = null;
    static final ModuleMethod lambda$Fn132 = null;
    static final ModuleMethod lambda$Fn133 = null;
    static final ModuleMethod lambda$Fn134 = null;
    static final ModuleMethod lambda$Fn135 = null;
    static final ModuleMethod lambda$Fn136 = null;
    static final ModuleMethod lambda$Fn137 = null;
    static final ModuleMethod lambda$Fn138 = null;
    static final ModuleMethod lambda$Fn139 = null;
    static final ModuleMethod lambda$Fn14 = null;
    static final ModuleMethod lambda$Fn140 = null;
    static final ModuleMethod lambda$Fn141 = null;
    static final ModuleMethod lambda$Fn142 = null;
    static final ModuleMethod lambda$Fn143 = null;
    static final ModuleMethod lambda$Fn144 = null;
    static final ModuleMethod lambda$Fn145 = null;
    static final ModuleMethod lambda$Fn146 = null;
    static final ModuleMethod lambda$Fn147 = null;
    static final ModuleMethod lambda$Fn148 = null;
    static final ModuleMethod lambda$Fn149 = null;
    static final ModuleMethod lambda$Fn15 = null;
    static final ModuleMethod lambda$Fn150 = null;
    static final ModuleMethod lambda$Fn151 = null;
    static final ModuleMethod lambda$Fn152 = null;
    static final ModuleMethod lambda$Fn153 = null;
    static final ModuleMethod lambda$Fn154 = null;
    static final ModuleMethod lambda$Fn155 = null;
    static final ModuleMethod lambda$Fn156 = null;
    static final ModuleMethod lambda$Fn157 = null;
    static final ModuleMethod lambda$Fn158 = null;
    static final ModuleMethod lambda$Fn159 = null;
    static final ModuleMethod lambda$Fn16 = null;
    static final ModuleMethod lambda$Fn160 = null;
    static final ModuleMethod lambda$Fn161 = null;
    static final ModuleMethod lambda$Fn162 = null;
    static final ModuleMethod lambda$Fn163 = null;
    static final ModuleMethod lambda$Fn164 = null;
    static final ModuleMethod lambda$Fn165 = null;
    static final ModuleMethod lambda$Fn166 = null;
    static final ModuleMethod lambda$Fn167 = null;
    static final ModuleMethod lambda$Fn168 = null;
    static final ModuleMethod lambda$Fn169 = null;
    static final ModuleMethod lambda$Fn17 = null;
    static final ModuleMethod lambda$Fn170 = null;
    static final ModuleMethod lambda$Fn171 = null;
    static final ModuleMethod lambda$Fn172 = null;
    static final ModuleMethod lambda$Fn173 = null;
    static final ModuleMethod lambda$Fn174 = null;
    static final ModuleMethod lambda$Fn175 = null;
    static final ModuleMethod lambda$Fn176 = null;
    static final ModuleMethod lambda$Fn177 = null;
    static final ModuleMethod lambda$Fn178 = null;
    static final ModuleMethod lambda$Fn179 = null;
    static final ModuleMethod lambda$Fn18 = null;
    static final ModuleMethod lambda$Fn180 = null;
    static final ModuleMethod lambda$Fn181 = null;
    static final ModuleMethod lambda$Fn182 = null;
    static final ModuleMethod lambda$Fn183 = null;
    static final ModuleMethod lambda$Fn184 = null;
    static final ModuleMethod lambda$Fn185 = null;
    static final ModuleMethod lambda$Fn186 = null;
    static final ModuleMethod lambda$Fn187 = null;
    static final ModuleMethod lambda$Fn188 = null;
    static final ModuleMethod lambda$Fn189 = null;
    static final ModuleMethod lambda$Fn19 = null;
    static final ModuleMethod lambda$Fn190 = null;
    static final ModuleMethod lambda$Fn191 = null;
    static final ModuleMethod lambda$Fn192 = null;
    static final ModuleMethod lambda$Fn193 = null;
    static final ModuleMethod lambda$Fn194 = null;
    static final ModuleMethod lambda$Fn195 = null;
    static final ModuleMethod lambda$Fn196 = null;
    static final ModuleMethod lambda$Fn197 = null;
    static final ModuleMethod lambda$Fn198 = null;
    static final ModuleMethod lambda$Fn199 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn20 = null;
    static final ModuleMethod lambda$Fn200 = null;
    static final ModuleMethod lambda$Fn201 = null;
    static final ModuleMethod lambda$Fn202 = null;
    static final ModuleMethod lambda$Fn203 = null;
    static final ModuleMethod lambda$Fn204 = null;
    static final ModuleMethod lambda$Fn205 = null;
    static final ModuleMethod lambda$Fn206 = null;
    static final ModuleMethod lambda$Fn207 = null;
    static final ModuleMethod lambda$Fn208 = null;
    static final ModuleMethod lambda$Fn209 = null;
    static final ModuleMethod lambda$Fn210 = null;
    static final ModuleMethod lambda$Fn211 = null;
    static final ModuleMethod lambda$Fn212 = null;
    static final ModuleMethod lambda$Fn213 = null;
    static final ModuleMethod lambda$Fn214 = null;
    static final ModuleMethod lambda$Fn215 = null;
    static final ModuleMethod lambda$Fn216 = null;
    static final ModuleMethod lambda$Fn217 = null;
    static final ModuleMethod lambda$Fn218 = null;
    static final ModuleMethod lambda$Fn219 = null;
    static final ModuleMethod lambda$Fn220 = null;
    static final ModuleMethod lambda$Fn221 = null;
    static final ModuleMethod lambda$Fn222 = null;
    static final ModuleMethod lambda$Fn223 = null;
    static final ModuleMethod lambda$Fn224 = null;
    static final ModuleMethod lambda$Fn225 = null;
    static final ModuleMethod lambda$Fn226 = null;
    static final ModuleMethod lambda$Fn227 = null;
    static final ModuleMethod lambda$Fn228 = null;
    static final ModuleMethod lambda$Fn229 = null;
    static final ModuleMethod lambda$Fn230 = null;
    static final ModuleMethod lambda$Fn231 = null;
    static final ModuleMethod lambda$Fn232 = null;
    static final ModuleMethod lambda$Fn233 = null;
    static final ModuleMethod lambda$Fn234 = null;
    static final ModuleMethod lambda$Fn235 = null;
    static final ModuleMethod lambda$Fn236 = null;
    static final ModuleMethod lambda$Fn237 = null;
    static final ModuleMethod lambda$Fn238 = null;
    static final ModuleMethod lambda$Fn239 = null;
    static final ModuleMethod lambda$Fn24 = null;
    static final ModuleMethod lambda$Fn240 = null;
    static final ModuleMethod lambda$Fn241 = null;
    static final ModuleMethod lambda$Fn242 = null;
    static final ModuleMethod lambda$Fn243 = null;
    static final ModuleMethod lambda$Fn244 = null;
    static final ModuleMethod lambda$Fn245 = null;
    static final ModuleMethod lambda$Fn246 = null;
    static final ModuleMethod lambda$Fn247 = null;
    static final ModuleMethod lambda$Fn248 = null;
    static final ModuleMethod lambda$Fn249 = null;
    static final ModuleMethod lambda$Fn25 = null;
    static final ModuleMethod lambda$Fn250 = null;
    static final ModuleMethod lambda$Fn251 = null;
    static final ModuleMethod lambda$Fn252 = null;
    static final ModuleMethod lambda$Fn253 = null;
    static final ModuleMethod lambda$Fn254 = null;
    static final ModuleMethod lambda$Fn255 = null;
    static final ModuleMethod lambda$Fn256 = null;
    static final ModuleMethod lambda$Fn257 = null;
    static final ModuleMethod lambda$Fn258 = null;
    static final ModuleMethod lambda$Fn259 = null;
    static final ModuleMethod lambda$Fn260 = null;
    static final ModuleMethod lambda$Fn261 = null;
    static final ModuleMethod lambda$Fn262 = null;
    static final ModuleMethod lambda$Fn263 = null;
    static final ModuleMethod lambda$Fn264 = null;
    static final ModuleMethod lambda$Fn265 = null;
    static final ModuleMethod lambda$Fn266 = null;
    static final ModuleMethod lambda$Fn267 = null;
    static final ModuleMethod lambda$Fn268 = null;
    static final ModuleMethod lambda$Fn269 = null;
    static final ModuleMethod lambda$Fn272 = null;
    static final ModuleMethod lambda$Fn273 = null;
    static final ModuleMethod lambda$Fn274 = null;
    static final ModuleMethod lambda$Fn275 = null;
    static final ModuleMethod lambda$Fn276 = null;
    static final ModuleMethod lambda$Fn277 = null;
    static final ModuleMethod lambda$Fn278 = null;
    static final ModuleMethod lambda$Fn279 = null;
    static final ModuleMethod lambda$Fn280 = null;
    static final ModuleMethod lambda$Fn281 = null;
    static final ModuleMethod lambda$Fn282 = null;
    static final ModuleMethod lambda$Fn283 = null;
    static final ModuleMethod lambda$Fn284 = null;
    static final ModuleMethod lambda$Fn285 = null;
    static final ModuleMethod lambda$Fn286 = null;
    static final ModuleMethod lambda$Fn29 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn30 = null;
    static final ModuleMethod lambda$Fn31 = null;
    static final ModuleMethod lambda$Fn32 = null;
    static final ModuleMethod lambda$Fn36 = null;
    static final ModuleMethod lambda$Fn37 = null;
    static final ModuleMethod lambda$Fn38 = null;
    static final ModuleMethod lambda$Fn39 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn40 = null;
    static final ModuleMethod lambda$Fn44 = null;
    static final ModuleMethod lambda$Fn45 = null;
    static final ModuleMethod lambda$Fn46 = null;
    static final ModuleMethod lambda$Fn47 = null;
    static final ModuleMethod lambda$Fn48 = null;
    static final ModuleMethod lambda$Fn49 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn50 = null;
    static final ModuleMethod lambda$Fn51 = null;
    static final ModuleMethod lambda$Fn52 = null;
    static final ModuleMethod lambda$Fn53 = null;
    static final ModuleMethod lambda$Fn54 = null;
    static final ModuleMethod lambda$Fn55 = null;
    static final ModuleMethod lambda$Fn56 = null;
    static final ModuleMethod lambda$Fn57 = null;
    static final ModuleMethod lambda$Fn58 = null;
    static final ModuleMethod lambda$Fn59 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn60 = null;
    static final ModuleMethod lambda$Fn61 = null;
    static final ModuleMethod lambda$Fn62 = null;
    static final ModuleMethod lambda$Fn63 = null;
    static final ModuleMethod lambda$Fn64 = null;
    static final ModuleMethod lambda$Fn65 = null;
    static final ModuleMethod lambda$Fn66 = null;
    static final ModuleMethod lambda$Fn67 = null;
    static final ModuleMethod lambda$Fn68 = null;
    static final ModuleMethod lambda$Fn69 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn70 = null;
    static final ModuleMethod lambda$Fn71 = null;
    static final ModuleMethod lambda$Fn72 = null;
    static final ModuleMethod lambda$Fn73 = null;
    static final ModuleMethod lambda$Fn74 = null;
    static final ModuleMethod lambda$Fn75 = null;
    static final ModuleMethod lambda$Fn76 = null;
    static final ModuleMethod lambda$Fn77 = null;
    static final ModuleMethod lambda$Fn78 = null;
    static final ModuleMethod lambda$Fn79 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn80 = null;
    static final ModuleMethod lambda$Fn81 = null;
    static final ModuleMethod lambda$Fn82 = null;
    static final ModuleMethod lambda$Fn83 = null;
    static final ModuleMethod lambda$Fn84 = null;
    static final ModuleMethod lambda$Fn85 = null;
    static final ModuleMethod lambda$Fn86 = null;
    static final ModuleMethod lambda$Fn87 = null;
    static final ModuleMethod lambda$Fn88 = null;
    static final ModuleMethod lambda$Fn89 = null;
    static final ModuleMethod lambda$Fn9 = null;
    static final ModuleMethod lambda$Fn90 = null;
    static final ModuleMethod lambda$Fn91 = null;
    static final ModuleMethod lambda$Fn92 = null;
    static final ModuleMethod lambda$Fn93 = null;
    static final ModuleMethod lambda$Fn94 = null;
    static final ModuleMethod lambda$Fn95 = null;
    static final ModuleMethod lambda$Fn96 = null;
    static final ModuleMethod lambda$Fn97 = null;
    static final ModuleMethod lambda$Fn98 = null;
    static final ModuleMethod lambda$Fn99 = null;
    static final ModuleMethod proc$Fn21 = null;
    static final ModuleMethod proc$Fn22 = null;
    static final ModuleMethod proc$Fn23 = null;
    static final ModuleMethod proc$Fn26 = null;
    static final ModuleMethod proc$Fn27 = null;
    static final ModuleMethod proc$Fn28 = null;
    static final ModuleMethod proc$Fn33 = null;
    static final ModuleMethod proc$Fn34 = null;
    static final ModuleMethod proc$Fn41 = null;
    static final ModuleMethod proc$Fn42 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public VerticalScrollArrangement About;
    public ActivityStarter Activity_Starter1;
    public MakeroidAnimationUtilities Animation;

    /* renamed from: At */
    public Image f256At;
    public Button Button1;
    public final ModuleMethod Button1$Click;
    public MakeroidCircularProgress Circular_Progress1;
    public MakeroidCircularProgress Circular_Progress2;
    public MakeroidCircularProgress Circular_Progress3;
    public MakeroidCircularProgress Circular_Progress4;
    public MakeroidCircularProgress Circular_Progress4_copy;
    public Label CovidEase;
    public VerticalArrangement Details;
    public ListView Details_LIST;
    public Notifier DialoguePOP;
    public WebViewer EmWebView;
    public final ModuleMethod EmWebView$ProgressChanged;
    public VerticalArrangement EmergencyPG;
    public HorizontalArrangement Filter;
    public FirebaseDB Firebase_Database1;
    public final ModuleMethod Firebase_Database1$GotValue;
    public MakeroidAnimationUtilities Floater;
    public GoogleMap Gmap;
    public final ModuleMethod Gmap$MapIsReady;
    public final ModuleMethod Gmap$OnMapClick;
    public final ModuleMethod Gmap$OnMarkerClick;
    public Label HideMarkerPop;
    public final ModuleMethod HideMarkerPop$Click;
    public VerticalArrangement HomePG;
    public HorizontalArrangement Horizontal_Arrangement1;
    public HorizontalArrangement Horizontal_Arrangement2;
    public HorizontalArrangement Horizontal_Arrangement2_copy;
    public HorizontalArrangement Horizontal_Arrangement3;
    public HorizontalArrangement Horizontal_Arrangement4;
    public Image Image1;
    public Image Image2;
    public Image Image2_copy;
    public Image Image2_copy1;
    public WebViewer InWebView;
    public final ModuleMethod InWebView$ProgressChanged;
    public VerticalArrangement InfoPG;
    public Network Internet;
    public WebViewer IntroWeb_Viewer;
    public final ModuleMethod IntroWeb_Viewer$ProgressChanged;
    public JSONTools JSONTools1;
    public Clock LC_CLK;
    public Label Label1;
    public Label Label10;
    public final ModuleMethod Label10$Click;
    public Label Label11;
    public final ModuleMethod Label11$Click;
    public Label Label2;
    public Label Label2_copy;
    public Label Label3;
    public Label Label4;
    public Label Label5;
    public Label Label6;
    public Label Label7;
    public Label Label7_copy;
    public Label Label8;
    public Label Label8_copy;
    public Label Label9;
    public VerticalArrangement LoadPG;
    public HorizontalArrangement Loading;
    public LocationSensor Location_Sensor1;
    public VerticalArrangement MainPG;
    public HorizontalArrangement MapContainer;
    public VerticalArrangement MarkerPOP;
    public VerticalScrollArrangement MarkerPOPinside;
    public WebViewer NeWebView;
    public final ModuleMethod NeWebView$ProgressChanged;
    public VerticalArrangement NewsPG;
    public VerticalArrangement POPLayout;
    public Button POPbutton;
    public final ModuleMethod POPbutton$Click;
    public Label POPtitle;
    public Package Package_Utilities1;
    public Button Phn_btn;
    public final ModuleMethod Phn_btn$Click;
    public HorizontalArrangement SCquPOP;
    public final ModuleMethod Screen1$BackPressed;
    public final ModuleMethod Screen1$Initialize;
    public final ModuleMethod Screen1$KeyboardVisiblityChanged;
    public TextBox Search;
    public final ModuleMethod Search$GotFocus;
    public final ModuleMethod Search$OnTextChanged;
    public HorizontalArrangement SearchBar;
    public Label SearchQury;

    /* renamed from: Sp */
    public Label f257Sp;
    public SpaceView Space1;
    public SpaceView Space10;
    public SpaceView Space11;
    public SpaceView Space12;
    public SpaceView Space4;
    public SpaceView Space6;
    public SpaceView Space6_copy;
    public SpaceView Space7;
    public SpaceView Space8;
    public SpaceView Space8_copy;
    public SpaceView Space9;
    public SpaceView Space9_copy;
    public SpaceView Space9_copy_copy;
    public KodularBottomNavigation Tabs;
    public final ModuleMethod Tabs$ItemSelected;
    public TextBox Text_Box1;
    public TinyDB Tiny_DB1;
    public VerticalArrangement Vertical_Arrangement1;
    public VerticalArrangement Vertical_Arrangement2;
    public final ModuleMethod Vertical_Arrangement2$Click;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public HorizontalArrangement bar_prog;
    public Button cancel2;
    public final ModuleMethod cancel2$Click;
    public LList components$Mnto$Mncreate;
    public VerticalArrangement dialouge;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
    public Label down;
    public final ModuleMethod down$Click;
    public HorizontalArrangement em_load;
    public VerticalArrangement em_por;
    public SpaceView end;
    public LList events$Mnto$Mnregister;
    public VerticalArrangement filter_txt;
    public Button flip;
    public final ModuleMethod flip$Click;
    public Clock float_ckl1;
    public final ModuleMethod float_ckl1$Timer;
    public Clock float_clk;
    public final ModuleMethod float_clk$Timer;
    public LList form$Mndo$Mnafter$Mncreation;
    public Environment form$Mnenvironment;
    public Symbol form$Mnname$Mnsymbol;
    public final ModuleMethod get$Mnsimple$Mnname;
    public Environment global$Mnvar$Mnenvironment;
    public LList global$Mnvars$Mnto$Mncreate;
    public HorizontalArrangement ifo_load;
    public VerticalArrangement info_por;
    public VerticalArrangement intro;
    public MakeroidCircularProgress intro_por;
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public Clock keyboard_timer;
    public final ModuleMethod keyboard_timer$Timer;
    public Clock location_clk;
    public final ModuleMethod location_clk$Timer;
    public CheckBox lock;
    public final ModuleMethod lock$Changed;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public HorizontalArrangement news_load;
    public VerticalArrangement news_pro;

    /* renamed from: no */
    public CheckBox f258no;
    public final ModuleMethod no$Changed;
    public Label null_loader;
    public final ModuleMethod process$Mnexception;
    public HorizontalArrangement progress;
    public VerticalArrangement scname_div;
    public ListView scname_list;
    public final ModuleMethod scname_list$AfterPicking;
    public Label scrolllist;
    public final ModuleMethod scrolllist$Click;
    public final ModuleMethod send$Mnerror;
    public CheckBox test;
    public final ModuleMethod test$Changed;

    /* renamed from: up */
    public CheckBox f259up;
    public final ModuleMethod up$Changed;
    public Web webIcon;
    public CheckBox yes;
    public final ModuleMethod yes$Changed;

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
        FString fString;
        FString fString2;
        FString fString3;
        SimpleSymbol simpleSymbol18;
        FString fString4;
        FString fString5;
        FString fString6;
        SimpleSymbol simpleSymbol19;
        FString fString7;
        FString fString8;
        FString fString9;
        FString fString10;
        FString fString11;
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        FString fString12;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        SimpleSymbol simpleSymbol24;
        SimpleSymbol simpleSymbol25;
        SimpleSymbol simpleSymbol26;
        SimpleSymbol simpleSymbol27;
        SimpleSymbol simpleSymbol28;
        SimpleSymbol simpleSymbol29;
        SimpleSymbol simpleSymbol30;
        FString fString13;
        FString fString14;
        SimpleSymbol simpleSymbol31;
        SimpleSymbol simpleSymbol32;
        FString fString15;
        FString fString16;
        FString fString17;
        FString fString18;
        SimpleSymbol simpleSymbol33;
        SimpleSymbol simpleSymbol34;
        SimpleSymbol simpleSymbol35;
        FString fString19;
        SimpleSymbol simpleSymbol36;
        SimpleSymbol simpleSymbol37;
        FString fString20;
        FString fString21;
        FString fString22;
        SimpleSymbol simpleSymbol38;
        SimpleSymbol simpleSymbol39;
        SimpleSymbol simpleSymbol40;
        SimpleSymbol simpleSymbol41;
        SimpleSymbol simpleSymbol42;
        SimpleSymbol simpleSymbol43;
        SimpleSymbol simpleSymbol44;
        SimpleSymbol simpleSymbol45;
        SimpleSymbol simpleSymbol46;
        FString fString23;
        SimpleSymbol simpleSymbol47;
        SimpleSymbol simpleSymbol48;
        SimpleSymbol simpleSymbol49;
        SimpleSymbol simpleSymbol50;
        FString fString24;
        FString fString25;
        FString fString26;
        FString fString27;
        SimpleSymbol simpleSymbol51;
        FString fString28;
        FString fString29;
        FString fString30;
        FString fString31;
        SimpleSymbol simpleSymbol52;
        SimpleSymbol simpleSymbol53;
        FString fString32;
        SimpleSymbol simpleSymbol54;
        SimpleSymbol simpleSymbol55;
        SimpleSymbol simpleSymbol56;
        SimpleSymbol simpleSymbol57;
        FString fString33;
        SimpleSymbol simpleSymbol58;
        SimpleSymbol simpleSymbol59;
        FString fString34;
        SimpleSymbol simpleSymbol60;
        FString fString35;
        SimpleSymbol simpleSymbol61;
        FString fString36;
        FString fString37;
        SimpleSymbol simpleSymbol62;
        FString fString38;
        FString fString39;
        SimpleSymbol simpleSymbol63;
        FString fString40;
        SimpleSymbol simpleSymbol64;
        FString fString41;
        FString fString42;
        FString fString43;
        SimpleSymbol simpleSymbol65;
        FString fString44;
        SimpleSymbol simpleSymbol66;
        FString fString45;
        SimpleSymbol simpleSymbol67;
        FString fString46;
        FString fString47;
        FString fString48;
        FString fString49;
        FString fString50;
        FString fString51;
        FString fString52;
        FString fString53;
        SimpleSymbol simpleSymbol68;
        FString fString54;
        SimpleSymbol simpleSymbol69;
        FString fString55;
        SimpleSymbol simpleSymbol70;
        FString fString56;
        SimpleSymbol simpleSymbol71;
        FString fString57;
        SimpleSymbol simpleSymbol72;
        FString fString58;
        SimpleSymbol simpleSymbol73;
        FString fString59;
        SimpleSymbol simpleSymbol74;
        FString fString60;
        SimpleSymbol simpleSymbol75;
        FString fString61;
        SimpleSymbol simpleSymbol76;
        FString fString62;
        SimpleSymbol simpleSymbol77;
        SimpleSymbol simpleSymbol78;
        FString fString63;
        SimpleSymbol simpleSymbol79;
        SimpleSymbol simpleSymbol80;
        SimpleSymbol simpleSymbol81;
        FString fString64;
        FString fString65;
        FString fString66;
        SimpleSymbol simpleSymbol82;
        SimpleSymbol simpleSymbol83;
        SimpleSymbol simpleSymbol84;
        SimpleSymbol simpleSymbol85;
        SimpleSymbol simpleSymbol86;
        SimpleSymbol simpleSymbol87;
        FString fString67;
        SimpleSymbol simpleSymbol88;
        SimpleSymbol simpleSymbol89;
        FString fString68;
        FString fString69;
        SimpleSymbol simpleSymbol90;
        FString fString70;
        FString fString71;
        SimpleSymbol simpleSymbol91;
        SimpleSymbol simpleSymbol92;
        FString fString72;
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
        FString fString73;
        SimpleSymbol simpleSymbol105;
        SimpleSymbol simpleSymbol106;
        FString fString74;
        FString fString75;
        SimpleSymbol simpleSymbol107;
        FString fString76;
        FString fString77;
        SimpleSymbol simpleSymbol108;
        FString fString78;
        FString fString79;
        FString fString80;
        FString fString81;
        FString fString82;
        FString fString83;
        FString fString84;
        SimpleSymbol simpleSymbol109;
        SimpleSymbol simpleSymbol110;
        FString fString85;
        FString fString86;
        SimpleSymbol simpleSymbol111;
        SimpleSymbol simpleSymbol112;
        SimpleSymbol simpleSymbol113;
        SimpleSymbol simpleSymbol114;
        FString fString87;
        SimpleSymbol simpleSymbol115;
        SimpleSymbol simpleSymbol116;
        SimpleSymbol simpleSymbol117;
        SimpleSymbol simpleSymbol118;
        SimpleSymbol simpleSymbol119;
        FString fString88;
        FString fString89;
        FString fString90;
        FString fString91;
        SimpleSymbol simpleSymbol120;
        FString fString92;
        FString fString93;
        FString fString94;
        SimpleSymbol simpleSymbol121;
        SimpleSymbol simpleSymbol122;
        SimpleSymbol simpleSymbol123;
        SimpleSymbol simpleSymbol124;
        SimpleSymbol simpleSymbol125;
        SimpleSymbol simpleSymbol126;
        SimpleSymbol simpleSymbol127;
        SimpleSymbol simpleSymbol128;
        FString fString95;
        SimpleSymbol simpleSymbol129;
        SimpleSymbol simpleSymbol130;
        FString fString96;
        FString fString97;
        SimpleSymbol simpleSymbol131;
        FString fString98;
        FString fString99;
        FString fString100;
        SimpleSymbol simpleSymbol132;
        FString fString101;
        FString fString102;
        FString fString103;
        SimpleSymbol simpleSymbol133;
        FString fString104;
        FString fString105;
        SimpleSymbol simpleSymbol134;
        FString fString106;
        FString fString107;
        SimpleSymbol simpleSymbol135;
        FString fString108;
        FString fString109;
        SimpleSymbol simpleSymbol136;
        FString fString110;
        FString fString111;
        SimpleSymbol simpleSymbol137;
        FString fString112;
        FString fString113;
        SimpleSymbol simpleSymbol138;
        FString fString114;
        FString fString115;
        FString fString116;
        SimpleSymbol simpleSymbol139;
        FString fString117;
        FString fString118;
        FString fString119;
        SimpleSymbol simpleSymbol140;
        FString fString120;
        FString fString121;
        SimpleSymbol simpleSymbol141;
        FString fString122;
        FString fString123;
        SimpleSymbol simpleSymbol142;
        FString fString124;
        FString fString125;
        SimpleSymbol simpleSymbol143;
        FString fString126;
        FString fString127;
        SimpleSymbol simpleSymbol144;
        FString fString128;
        FString fString129;
        SimpleSymbol simpleSymbol145;
        FString fString130;
        FString fString131;
        FString fString132;
        SimpleSymbol simpleSymbol146;
        FString fString133;
        FString fString134;
        FString fString135;
        SimpleSymbol simpleSymbol147;
        FString fString136;
        FString fString137;
        SimpleSymbol simpleSymbol148;
        FString fString138;
        FString fString139;
        SimpleSymbol simpleSymbol149;
        FString fString140;
        FString fString141;
        SimpleSymbol simpleSymbol150;
        FString fString142;
        FString fString143;
        SimpleSymbol simpleSymbol151;
        FString fString144;
        FString fString145;
        SimpleSymbol simpleSymbol152;
        FString fString146;
        FString fString147;
        FString fString148;
        FString fString149;
        FString fString150;
        FString fString151;
        FString fString152;
        FString fString153;
        SimpleSymbol simpleSymbol153;
        FString fString154;
        FString fString155;
        SimpleSymbol simpleSymbol154;
        FString fString156;
        FString fString157;
        SimpleSymbol simpleSymbol155;
        FString fString158;
        FString fString159;
        SimpleSymbol simpleSymbol156;
        FString fString160;
        FString fString161;
        SimpleSymbol simpleSymbol157;
        FString fString162;
        FString fString163;
        SimpleSymbol simpleSymbol158;
        FString fString164;
        FString fString165;
        SimpleSymbol simpleSymbol159;
        FString fString166;
        FString fString167;
        FString fString168;
        FString fString169;
        SimpleSymbol simpleSymbol160;
        FString fString170;
        SimpleSymbol simpleSymbol161;
        FString fString171;
        SimpleSymbol simpleSymbol162;
        FString fString172;
        SimpleSymbol simpleSymbol163;
        SimpleSymbol simpleSymbol164;
        FString fString173;
        SimpleSymbol simpleSymbol165;
        FString fString174;
        SimpleSymbol simpleSymbol166;
        SimpleSymbol simpleSymbol167;
        SimpleSymbol simpleSymbol168;
        FString fString175;
        SimpleSymbol simpleSymbol169;
        SimpleSymbol simpleSymbol170;
        SimpleSymbol simpleSymbol171;
        FString fString176;
        FString fString177;
        SimpleSymbol simpleSymbol172;
        SimpleSymbol simpleSymbol173;
        FString fString178;
        FString fString179;
        FString fString180;
        FString fString181;
        SimpleSymbol simpleSymbol174;
        FString fString182;
        SimpleSymbol simpleSymbol175;
        FString fString183;
        SimpleSymbol simpleSymbol176;
        FString fString184;
        FString fString185;
        SimpleSymbol simpleSymbol177;
        FString fString186;
        FString fString187;
        SimpleSymbol simpleSymbol178;
        FString fString188;
        FString fString189;
        SimpleSymbol simpleSymbol179;
        FString fString190;
        FString fString191;
        SimpleSymbol simpleSymbol180;
        FString fString192;
        FString fString193;
        SimpleSymbol simpleSymbol181;
        FString fString194;
        FString fString195;
        SimpleSymbol simpleSymbol182;
        FString fString196;
        FString fString197;
        SimpleSymbol simpleSymbol183;
        FString fString198;
        FString fString199;
        SimpleSymbol simpleSymbol184;
        FString fString200;
        FString fString201;
        SimpleSymbol simpleSymbol185;
        SimpleSymbol simpleSymbol186;
        FString fString202;
        FString fString203;
        SimpleSymbol simpleSymbol187;
        FString fString204;
        FString fString205;
        SimpleSymbol simpleSymbol188;
        FString fString206;
        FString fString207;
        SimpleSymbol simpleSymbol189;
        FString fString208;
        FString fString209;
        SimpleSymbol simpleSymbol190;
        FString fString210;
        FString fString211;
        FString fString212;
        FString fString213;
        SimpleSymbol simpleSymbol191;
        FString fString214;
        SimpleSymbol simpleSymbol192;
        SimpleSymbol simpleSymbol193;
        SimpleSymbol simpleSymbol194;
        SimpleSymbol simpleSymbol195;
        SimpleSymbol simpleSymbol196;
        SimpleSymbol simpleSymbol197;
        FString fString215;
        FString fString216;
        SimpleSymbol simpleSymbol198;
        SimpleSymbol simpleSymbol199;
        FString fString217;
        SimpleSymbol simpleSymbol200;
        SimpleSymbol simpleSymbol201;
        FString fString218;
        FString fString219;
        SimpleSymbol simpleSymbol202;
        FString fString220;
        FString fString221;
        SimpleSymbol simpleSymbol203;
        FString fString222;
        FString fString223;
        FString fString224;
        FString fString225;
        SimpleSymbol simpleSymbol204;
        FString fString226;
        FString fString227;
        SimpleSymbol simpleSymbol205;
        SimpleSymbol simpleSymbol206;
        FString fString228;
        SimpleSymbol simpleSymbol207;
        SimpleSymbol simpleSymbol208;
        SimpleSymbol simpleSymbol209;
        SimpleSymbol simpleSymbol210;
        SimpleSymbol simpleSymbol211;
        SimpleSymbol simpleSymbol212;
        SimpleSymbol simpleSymbol213;
        SimpleSymbol simpleSymbol214;
        SimpleSymbol simpleSymbol215;
        FString fString229;
        SimpleSymbol simpleSymbol216;
        SimpleSymbol simpleSymbol217;
        SimpleSymbol simpleSymbol218;
        SimpleSymbol simpleSymbol219;
        SimpleSymbol simpleSymbol220;
        SimpleSymbol simpleSymbol221;
        FString fString230;
        FString fString231;
        SimpleSymbol simpleSymbol222;
        FString fString232;
        FString fString233;
        SimpleSymbol simpleSymbol223;
        SimpleSymbol simpleSymbol224;
        FString fString234;
        FString fString235;
        SimpleSymbol simpleSymbol225;
        SimpleSymbol simpleSymbol226;
        SimpleSymbol simpleSymbol227;
        SimpleSymbol simpleSymbol228;
        SimpleSymbol simpleSymbol229;
        FString fString236;
        SimpleSymbol simpleSymbol230;
        SimpleSymbol simpleSymbol231;
        SimpleSymbol simpleSymbol232;
        SimpleSymbol simpleSymbol233;
        SimpleSymbol simpleSymbol234;
        SimpleSymbol simpleSymbol235;
        SimpleSymbol simpleSymbol236;
        SimpleSymbol simpleSymbol237;
        SimpleSymbol simpleSymbol238;
        SimpleSymbol simpleSymbol239;
        SimpleSymbol simpleSymbol240;
        SimpleSymbol simpleSymbol241;
        SimpleSymbol simpleSymbol242;
        SimpleSymbol simpleSymbol243;
        SimpleSymbol simpleSymbol244;
        SimpleSymbol simpleSymbol245;
        SimpleSymbol simpleSymbol246;
        SimpleSymbol simpleSymbol247;
        SimpleSymbol simpleSymbol248;
        SimpleSymbol simpleSymbol249;
        SimpleSymbol simpleSymbol250;
        SimpleSymbol simpleSymbol251;
        SimpleSymbol simpleSymbol252;
        SimpleSymbol simpleSymbol253;
        SimpleSymbol simpleSymbol254;
        SimpleSymbol simpleSymbol255;
        SimpleSymbol simpleSymbol256;
        SimpleSymbol simpleSymbol257;
        SimpleSymbol simpleSymbol258;
        SimpleSymbol simpleSymbol259;
        SimpleSymbol simpleSymbol260;
        SimpleSymbol simpleSymbol261;
        SimpleSymbol simpleSymbol262;
        SimpleSymbol simpleSymbol263;
        SimpleSymbol simpleSymbol264;
        SimpleSymbol simpleSymbol265;
        SimpleSymbol simpleSymbol266;
        SimpleSymbol simpleSymbol267;
        SimpleSymbol simpleSymbol268;
        SimpleSymbol simpleSymbol269;
        SimpleSymbol simpleSymbol270;
        SimpleSymbol simpleSymbol271;
        SimpleSymbol simpleSymbol272;
        SimpleSymbol simpleSymbol273;
        SimpleSymbol simpleSymbol274;
        SimpleSymbol simpleSymbol275;
        SimpleSymbol simpleSymbol276;
        SimpleSymbol simpleSymbol277;
        SimpleSymbol simpleSymbol278;
        SimpleSymbol simpleSymbol279;
        SimpleSymbol simpleSymbol280;
        SimpleSymbol simpleSymbol281;
        SimpleSymbol simpleSymbol282;
        SimpleSymbol simpleSymbol283;
        SimpleSymbol simpleSymbol284;
        SimpleSymbol simpleSymbol285;
        SimpleSymbol simpleSymbol286;
        SimpleSymbol simpleSymbol287;
        SimpleSymbol simpleSymbol288;
        SimpleSymbol simpleSymbol289;
        SimpleSymbol simpleSymbol290;
        SimpleSymbol simpleSymbol291;
        SimpleSymbol simpleSymbol292;
        SimpleSymbol simpleSymbol293;
        SimpleSymbol simpleSymbol294;
        SimpleSymbol simpleSymbol295;
        SimpleSymbol simpleSymbol296;
        SimpleSymbol simpleSymbol297;
        SimpleSymbol simpleSymbol298;
        SimpleSymbol simpleSymbol299;
        SimpleSymbol simpleSymbol300;
        SimpleSymbol simpleSymbol301;
        SimpleSymbol simpleSymbol302;
        SimpleSymbol simpleSymbol303;
        SimpleSymbol simpleSymbol304;
        SimpleSymbol simpleSymbol305;
        SimpleSymbol simpleSymbol306;
        SimpleSymbol simpleSymbol307;
        SimpleSymbol simpleSymbol308;
        SimpleSymbol simpleSymbol309;
        SimpleSymbol simpleSymbol310;
        SimpleSymbol simpleSymbol311;
        SimpleSymbol simpleSymbol312;
        SimpleSymbol simpleSymbol313;
        SimpleSymbol simpleSymbol314;
        SimpleSymbol simpleSymbol315;
        SimpleSymbol simpleSymbol316;
        SimpleSymbol simpleSymbol317;
        SimpleSymbol simpleSymbol318;
        SimpleSymbol simpleSymbol319;
        SimpleSymbol simpleSymbol320;
        SimpleSymbol simpleSymbol321;
        SimpleSymbol simpleSymbol322;
        SimpleSymbol simpleSymbol323;
        SimpleSymbol simpleSymbol324;
        SimpleSymbol simpleSymbol325;
        SimpleSymbol simpleSymbol326;
        SimpleSymbol simpleSymbol327;
        SimpleSymbol simpleSymbol328;
        new SimpleSymbol("component");
        Lit944 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("any");
        Lit943 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("proc");
        Lit942 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("lookup-handler");
        Lit941 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("dispatchGenericEvent");
        Lit940 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("dispatchEvent");
        Lit939 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("send-error");
        Lit938 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("add-to-form-do-after-creation");
        Lit937 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("add-to-global-vars");
        Lit936 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("add-to-components");
        Lit935 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("add-to-events");
        Lit934 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("add-to-global-var-environment");
        Lit933 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("is-bound-in-form-environment");
        Lit932 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("lookup-in-form-environment");
        Lit931 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("add-to-form-environment");
        Lit930 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("android-log-form");
        Lit929 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("get-simple-name");
        Lit928 = (SimpleSymbol) simpleSymbol17.readResolve();
        new FString("com.google.appinventor.components.runtime.TinyDB");
        Lit927 = fString;
        new FString("com.google.appinventor.components.runtime.TinyDB");
        Lit926 = fString2;
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit925 = fString3;
        new SimpleSymbol("LC_CLK");
        Lit923 = (SimpleSymbol) simpleSymbol18.readResolve();
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit922 = fString4;
        new FString("com.google.appinventor.components.runtime.ActivityStarter");
        Lit921 = fString5;
        new FString("com.google.appinventor.components.runtime.ActivityStarter");
        Lit920 = fString6;
        new SimpleSymbol("keyboard_timer$Timer");
        Lit919 = (SimpleSymbol) simpleSymbol19.readResolve();
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit918 = fString7;
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit917 = fString8;
        new FString("com.google.appinventor.components.runtime.Package");
        Lit916 = fString9;
        new FString("com.google.appinventor.components.runtime.Package");
        Lit915 = fString10;
        new FString("com.google.appinventor.components.runtime.LocationSensor");
        Lit914 = fString11;
        new SimpleSymbol("TimeInterval");
        Lit913 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("DistanceInterval");
        Lit912 = (SimpleSymbol) simpleSymbol21.readResolve();
        new FString("com.google.appinventor.components.runtime.LocationSensor");
        Lit911 = fString12;
        new SimpleSymbol("location_clk$Timer");
        Lit910 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("number");
        SimpleSymbol simpleSymbol329 = (SimpleSymbol) simpleSymbol23.readResolve();
        Lit147 = simpleSymbol329;
        Lit909 = PairWithPosition.make(simpleSymbol329, PairWithPosition.make(Lit147, PairWithPosition.make(Lit147, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095450), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095443), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095435);
        new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN);
        SimpleSymbol simpleSymbol330 = (SimpleSymbol) simpleSymbol24.readResolve();
        Lit48 = simpleSymbol330;
        Lit906 = PairWithPosition.make(simpleSymbol330, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095321);
        new SimpleSymbol("ProviderName");
        Lit904 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("Location_Sensor1");
        Lit903 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("EnableMyLocation");
        Lit901 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT);
        SimpleSymbol simpleSymbol331 = (SimpleSymbol) simpleSymbol28.readResolve();
        Lit107 = simpleSymbol331;
        Lit900 = PairWithPosition.make(simpleSymbol331, PairWithPosition.make(Lit107, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095040), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 6095034);
        new SimpleSymbol("isPermissionGranted");
        Lit899 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("Package_Utilities1");
        Lit898 = (SimpleSymbol) simpleSymbol30.readResolve();
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit897 = fString13;
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit896 = fString14;
        new SimpleSymbol("float_ckl1$Timer");
        Lit895 = (SimpleSymbol) simpleSymbol31.readResolve();
        new SimpleSymbol("BounceHorizontal");
        Lit889 = (SimpleSymbol) simpleSymbol32.readResolve();
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit887 = fString15;
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit886 = fString16;
        new FString("com.google.appinventor.components.runtime.MakeroidAnimationUtilities");
        Lit885 = fString17;
        new FString("com.google.appinventor.components.runtime.MakeroidAnimationUtilities");
        Lit884 = fString18;
        new SimpleSymbol("Timer");
        Lit883 = (SimpleSymbol) simpleSymbol33.readResolve();
        new SimpleSymbol("float_clk$Timer");
        Lit882 = (SimpleSymbol) simpleSymbol34.readResolve();
        new SimpleSymbol("float_ckl1");
        Lit881 = (SimpleSymbol) simpleSymbol35.readResolve();
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit879 = fString19;
        new SimpleSymbol("TimerInterval");
        Lit878 = (SimpleSymbol) simpleSymbol36.readResolve();
        new SimpleSymbol("TimerAlwaysFires");
        Lit877 = (SimpleSymbol) simpleSymbol37.readResolve();
        new FString("com.google.appinventor.components.runtime.Clock");
        Lit876 = fString20;
        new FString("com.google.appinventor.components.runtime.Web");
        Lit875 = fString21;
        new FString("com.google.appinventor.components.runtime.Web");
        Lit874 = fString22;
        new SimpleSymbol("GotValue");
        Lit873 = (SimpleSymbol) simpleSymbol38.readResolve();
        new SimpleSymbol("Firebase_Database1$GotValue");
        Lit872 = (SimpleSymbol) simpleSymbol39.readResolve();
        new SimpleSymbol("list");
        SimpleSymbol simpleSymbol332 = (SimpleSymbol) simpleSymbol40.readResolve();
        Lit103 = simpleSymbol332;
        Lit870 = PairWithPosition.make(simpleSymbol332, PairWithPosition.make(Lit943, LList.Empty, "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898595), "/tmp/1591550764810_0.2393343196279506-0/youngandroidproject/../src/io/kodular/fz_arnob/CovidEaseV1_2107/Screen1.yail", 5898589);
        new SimpleSymbol("StoreValue");
        Lit865 = (SimpleSymbol) simpleSymbol41.readResolve();
        new SimpleSymbol("Namespace");
        Lit862 = (SimpleSymbol) simpleSymbol42.readResolve();
        new SimpleSymbol("Tiny_DB1");
        Lit861 = (SimpleSymbol) simpleSymbol43.readResolve();
        new SimpleSymbol("$number");
        Lit845 = (SimpleSymbol) simpleSymbol44.readResolve();
        new SimpleSymbol("$value");
        Lit839 = (SimpleSymbol) simpleSymbol45.readResolve();
        new SimpleSymbol("$tag");
        Lit837 = (SimpleSymbol) simpleSymbol46.readResolve();
        new FString("com.google.appinventor.components.runtime.FirebaseDB");
        Lit836 = fString23;
        new SimpleSymbol("FirebaseURL");
        Lit835 = (SimpleSymbol) simpleSymbol47.readResolve();
        new SimpleSymbol("FirebaseToken");
        Lit834 = (SimpleSymbol) simpleSymbol48.readResolve();
        new SimpleSymbol("DeveloperBucket");
        Lit833 = (SimpleSymbol) simpleSymbol49.readResolve();
        new SimpleSymbol("DefaultURL");
        Lit832 = (SimpleSymbol) simpleSymbol50.readResolve();
        new FString("com.google.appinventor.components.runtime.FirebaseDB");
        Lit831 = fString24;
        new FString("com.LukeGackle.JSONTools");
        Lit830 = fString25;
        new FString("com.LukeGackle.JSONTools");
        Lit829 = fString26;
        new FString("com.google.appinventor.components.runtime.MakeroidAnimationUtilities");
        Lit828 = fString27;
        new SimpleSymbol("Animation");
        Lit827 = (SimpleSymbol) simpleSymbol51.readResolve();
        new FString("com.google.appinventor.components.runtime.MakeroidAnimationUtilities");
        Lit826 = fString28;
        new FString("com.google.appinventor.components.runtime.Network");
        Lit825 = fString29;
        new FString("com.google.appinventor.components.runtime.Network");
        Lit824 = fString30;
        new FString("com.google.appinventor.components.runtime.Notifier");
        Lit823 = fString31;
        new SimpleSymbol("UseBackgroundColor");
        Lit822 = (SimpleSymbol) simpleSymbol52.readResolve();
        new SimpleSymbol("Linkify");
        Lit821 = (SimpleSymbol) simpleSymbol53.readResolve();
        int[] iArr = new int[2];
        iArr[0] = -2544062;
        Lit820 = IntNum.make(iArr);
        new FString("com.google.appinventor.components.runtime.Notifier");
        Lit819 = fString32;
        new SimpleSymbol("ItemSelected");
        Lit818 = (SimpleSymbol) simpleSymbol54.readResolve();
        new SimpleSymbol("Tabs$ItemSelected");
        Lit817 = (SimpleSymbol) simpleSymbol55.readResolve();
        new SimpleSymbol("GoToUrl");
        Lit811 = (SimpleSymbol) simpleSymbol56.readResolve();
        new SimpleSymbol("$title");
        Lit808 = (SimpleSymbol) simpleSymbol57.readResolve();
        new FString("com.google.appinventor.components.runtime.KodularBottomNavigation");
        Lit807 = fString33;
        int[] iArr2 = new int[2];
        iArr2[0] = -17974856;
        Lit806 = IntNum.make(iArr2);
        new SimpleSymbol("UnselectedColor");
        Lit805 = (SimpleSymbol) simpleSymbol58.readResolve();
        int[] iArr3 = new int[2];
        iArr3[0] = -1;
        Lit804 = IntNum.make(iArr3);
        new SimpleSymbol("SelectedColor");
        Lit803 = (SimpleSymbol) simpleSymbol59.readResolve();
        int[] iArr4 = new int[2];
        iArr4[0] = -2544062;
        Lit802 = IntNum.make(iArr4);
        new FString("com.google.appinventor.components.runtime.KodularBottomNavigation");
        Lit801 = fString34;
        new SimpleSymbol("scrolllist$Click");
        Lit800 = (SimpleSymbol) simpleSymbol60.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit798 = fString35;
        new SimpleSymbol("scrolllist");
        Lit796 = (SimpleSymbol) simpleSymbol61.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit795 = fString36;
        new FString("com.google.appinventor.components.runtime.ListView");
        Lit794 = fString37;
        new SimpleSymbol("ShowScrollbar");
        Lit793 = (SimpleSymbol) simpleSymbol62.readResolve();
        new FString("com.google.appinventor.components.runtime.ListView");
        Lit789 = fString38;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit788 = fString39;
        new SimpleSymbol("Space4");
        Lit786 = (SimpleSymbol) simpleSymbol63.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit785 = fString40;
        new SimpleSymbol("Phn_btn$Click");
        Lit784 = (SimpleSymbol) simpleSymbol64.readResolve();
        new FString("com.google.appinventor.components.runtime.Button");
        Lit783 = fString41;
        int[] iArr5 = new int[2];
        iArr5[0] = -2544062;
        Lit781 = IntNum.make(iArr5);
        new FString("com.google.appinventor.components.runtime.Button");
        Lit780 = fString42;
        new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
        Lit779 = fString43;
        new SimpleSymbol("MarkerPOPinside");
        Lit777 = (SimpleSymbol) simpleSymbol65.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
        Lit776 = fString44;
        new SimpleSymbol("HideMarkerPop$Click");
        Lit775 = (SimpleSymbol) simpleSymbol66.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit774 = fString45;
        int[] iArr6 = new int[2];
        iArr6[0] = -2544062;
        Lit773 = IntNum.make(iArr6);
        new SimpleSymbol("HideMarkerPop");
        Lit772 = (SimpleSymbol) simpleSymbol67.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit771 = fString46;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit770 = fString47;
        int[] iArr7 = new int[2];
        iArr7[0] = -7969;
        Lit768 = IntNum.make(iArr7);
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit767 = fString48;
        new FString("com.google.appinventor.components.runtime.TextBox");
        Lit766 = fString49;
        new FString("com.google.appinventor.components.runtime.TextBox");
        Lit765 = fString50;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit764 = fString51;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit763 = fString52;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit762 = fString53;
        new SimpleSymbol("Space10");
        Lit761 = (SimpleSymbol) simpleSymbol68.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit760 = fString54;
        new SimpleSymbol("lock$Changed");
        Lit759 = (SimpleSymbol) simpleSymbol69.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit756 = fString55;
        int[] iArr8 = new int[2];
        iArr8[0] = -17324757;
        Lit755 = IntNum.make(iArr8);
        int[] iArr9 = new int[2];
        iArr9[0] = -17324757;
        Lit754 = IntNum.make(iArr9);
        new SimpleSymbol("lock");
        Lit753 = (SimpleSymbol) simpleSymbol70.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit752 = fString56;
        new SimpleSymbol("up$Changed");
        Lit751 = (SimpleSymbol) simpleSymbol71.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit748 = fString57;
        int[] iArr10 = new int[2];
        iArr10[0] = -22004508;
        Lit747 = IntNum.make(iArr10);
        int[] iArr11 = new int[2];
        iArr11[0] = -22004508;
        Lit746 = IntNum.make(iArr11);
        new SimpleSymbol("up");
        Lit745 = (SimpleSymbol) simpleSymbol72.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit744 = fString58;
        new SimpleSymbol("no$Changed");
        Lit743 = (SimpleSymbol) simpleSymbol73.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit740 = fString59;
        int[] iArr12 = new int[2];
        iArr12[0] = -28850372;
        Lit739 = IntNum.make(iArr12);
        int[] iArr13 = new int[2];
        iArr13[0] = -12528331;
        Lit738 = IntNum.make(iArr13);
        new SimpleSymbol("no");
        Lit737 = (SimpleSymbol) simpleSymbol74.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit736 = fString60;
        new SimpleSymbol("test$Changed");
        Lit735 = (SimpleSymbol) simpleSymbol75.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit732 = fString61;
        int[] iArr14 = new int[2];
        iArr14[0] = -32077317;
        Lit731 = IntNum.make(iArr14);
        int[] iArr15 = new int[2];
        iArr15[0] = -32077317;
        Lit730 = IntNum.make(iArr15);
        new SimpleSymbol("test");
        Lit729 = (SimpleSymbol) simpleSymbol76.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit728 = fString62;
        new SimpleSymbol("Changed");
        Lit727 = (SimpleSymbol) simpleSymbol77.readResolve();
        new SimpleSymbol("yes$Changed");
        Lit726 = (SimpleSymbol) simpleSymbol78.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit723 = fString63;
        int[] iArr16 = new int[2];
        iArr16[0] = -324582;
        Lit722 = IntNum.make(iArr16);
        new SimpleSymbol("Checked");
        Lit721 = (SimpleSymbol) simpleSymbol79.readResolve();
        int[] iArr17 = new int[2];
        iArr17[0] = -324582;
        Lit720 = IntNum.make(iArr17);
        new SimpleSymbol("CheckboxColor");
        Lit719 = (SimpleSymbol) simpleSymbol80.readResolve();
        new SimpleSymbol("yes");
        Lit718 = (SimpleSymbol) simpleSymbol81.readResolve();
        new FString("com.google.appinventor.components.runtime.CheckBox");
        Lit717 = fString64;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit716 = fString65;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit714 = fString66;
        new SimpleSymbol("flip$Click");
        Lit713 = (SimpleSymbol) simpleSymbol82.readResolve();
        new SimpleSymbol("end");
        Lit701 = (SimpleSymbol) simpleSymbol83.readResolve();
        new SimpleSymbol("Details");
        Lit697 = (SimpleSymbol) simpleSymbol84.readResolve();
        new SimpleSymbol("GetXPosition");
        Lit695 = (SimpleSymbol) simpleSymbol85.readResolve();
        new SimpleSymbol("OvershootHorizontal");
        Lit694 = (SimpleSymbol) simpleSymbol86.readResolve();
        new SimpleSymbol("Floater");
        Lit693 = (SimpleSymbol) simpleSymbol87.readResolve();
        new FString("com.google.appinventor.components.runtime.Button");
        Lit691 = fString67;
        int[] iArr18 = new int[2];
        iArr18[0] = -2544062;
        Lit690 = IntNum.make(iArr18);
        new SimpleSymbol("RotationAngle");
        Lit688 = (SimpleSymbol) simpleSymbol88.readResolve();
        new SimpleSymbol("BorderShadow");
        Lit687 = (SimpleSymbol) simpleSymbol89.readResolve();
        int[] iArr19 = new int[2];
        iArr19[0] = -7969;
        Lit686 = IntNum.make(iArr19);
        new FString("com.google.appinventor.components.runtime.Button");
        Lit685 = fString68;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit684 = fString69;
        new SimpleSymbol("filter_txt");
        Lit682 = (SimpleSymbol) simpleSymbol90.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit681 = fString70;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit680 = fString71;
        new SimpleSymbol("Image");
        Lit679 = (SimpleSymbol) simpleSymbol91.readResolve();
        new SimpleSymbol("Filter");
        Lit676 = (SimpleSymbol) simpleSymbol92.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit675 = fString72;
        new SimpleSymbol("OnMapClick");
        Lit674 = (SimpleSymbol) simpleSymbol93.readResolve();
        new SimpleSymbol("Gmap$OnMapClick");
        Lit673 = (SimpleSymbol) simpleSymbol94.readResolve();
        new SimpleSymbol("MapIsReady");
        Lit670 = (SimpleSymbol) simpleSymbol95.readResolve();
        new SimpleSymbol("Gmap$MapIsReady");
        Lit669 = (SimpleSymbol) simpleSymbol96.readResolve();
        new SimpleSymbol("EnableMapClickListener");
        Lit667 = (SimpleSymbol) simpleSymbol97.readResolve();
        new SimpleSymbol("OnMarkerClick");
        Lit666 = (SimpleSymbol) simpleSymbol98.readResolve();
        new SimpleSymbol("Gmap$OnMarkerClick");
        Lit665 = (SimpleSymbol) simpleSymbol99.readResolve();
        new SimpleSymbol("Details_LIST");
        Lit659 = (SimpleSymbol) simpleSymbol100.readResolve();
        new SimpleSymbol("HtmlTextDecode");
        Lit652 = (SimpleSymbol) simpleSymbol101.readResolve();
        new SimpleSymbol("webIcon");
        Lit651 = (SimpleSymbol) simpleSymbol102.readResolve();
        new SimpleSymbol("Phn_btn");
        Lit650 = (SimpleSymbol) simpleSymbol103.readResolve();
        new SimpleSymbol("$markerId");
        Lit644 = (SimpleSymbol) simpleSymbol104.readResolve();
        new FString("com.google.appinventor.components.runtime.GoogleMap");
        Lit641 = fString73;
        new SimpleSymbol("Theme");
        Lit640 = (SimpleSymbol) simpleSymbol105.readResolve();
        new SimpleSymbol("CameraZoomLevel");
        Lit639 = (SimpleSymbol) simpleSymbol106.readResolve();
        new FString("com.google.appinventor.components.runtime.GoogleMap");
        Lit638 = fString74;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit637 = fString75;
        new SimpleSymbol("MapContainer");
        Lit636 = (SimpleSymbol) simpleSymbol107.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit635 = fString76;
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit634 = fString77;
        int[] iArr20 = new int[2];
        iArr20[0] = -2544062;
        Lit633 = IntNum.make(iArr20);
        new SimpleSymbol("Circular_Progress2");
        Lit632 = (SimpleSymbol) simpleSymbol108.readResolve();
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit631 = fString78;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit630 = fString79;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit629 = fString80;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit628 = fString81;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit627 = fString82;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit626 = fString83;
        int[] iArr21 = new int[2];
        iArr21[0] = -2544062;
        Lit625 = IntNum.make(iArr21);
        new FString("com.google.appinventor.components.runtime.Label");
        Lit624 = fString84;
        new SimpleSymbol("down$Click");
        Lit623 = (SimpleSymbol) simpleSymbol109.readResolve();
        new SimpleSymbol("ScrollToPosition");
        Lit621 = (SimpleSymbol) simpleSymbol110.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit620 = fString85;
        int[] iArr22 = new int[2];
        iArr22[0] = -2544062;
        Lit619 = IntNum.make(iArr22);
        int[] iArr23 = new int[2];
        iArr23[0] = -2544062;
        Lit618 = IntNum.make(iArr23);
        new FString("com.google.appinventor.components.runtime.Label");
        Lit617 = fString86;
        new SimpleSymbol("AfterPicking");
        Lit616 = (SimpleSymbol) simpleSymbol111.readResolve();
        new SimpleSymbol("scname_list$AfterPicking");
        Lit615 = (SimpleSymbol) simpleSymbol112.readResolve();
        new SimpleSymbol("AddCircle");
        Lit604 = (SimpleSymbol) simpleSymbol113.readResolve();
        new SimpleSymbol("SelectionIndex");
        Lit600 = (SimpleSymbol) simpleSymbol114.readResolve();
        new FString("com.google.appinventor.components.runtime.ListView");
        Lit598 = fString87;
        new SimpleSymbol("TextSize");
        Lit597 = (SimpleSymbol) simpleSymbol115.readResolve();
        new SimpleSymbol("ScrollingSpeed");
        Lit595 = (SimpleSymbol) simpleSymbol116.readResolve();
        new SimpleSymbol("ItemHeight");
        Lit594 = (SimpleSymbol) simpleSymbol117.readResolve();
        new SimpleSymbol("DividerHeight");
        Lit593 = (SimpleSymbol) simpleSymbol118.readResolve();
        int[] iArr24 = new int[2];
        iArr24[0] = -21322589;
        Lit592 = IntNum.make(iArr24);
        new SimpleSymbol("DividerColor");
        Lit591 = (SimpleSymbol) simpleSymbol119.readResolve();
        int[] iArr25 = new int[2];
        iArr25[0] = -7969;
        Lit590 = IntNum.make(iArr25);
        new FString("com.google.appinventor.components.runtime.ListView");
        Lit589 = fString88;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit588 = fString89;
        int[] iArr26 = new int[2];
        iArr26[0] = -2544062;
        Lit587 = IntNum.make(iArr26);
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit586 = fString90;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit585 = fString91;
        new SimpleSymbol("Vertical_Arrangement1");
        Lit584 = (SimpleSymbol) simpleSymbol120.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit583 = fString92;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit582 = fString93;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit580 = fString94;
        new SimpleSymbol("OnTextChanged");
        Lit579 = (SimpleSymbol) simpleSymbol121.readResolve();
        new SimpleSymbol("Search$OnTextChanged");
        Lit578 = (SimpleSymbol) simpleSymbol122.readResolve();
        new SimpleSymbol("GotFocus");
        Lit575 = (SimpleSymbol) simpleSymbol123.readResolve();
        new SimpleSymbol("Search$GotFocus");
        Lit574 = (SimpleSymbol) simpleSymbol124.readResolve();
        new SimpleSymbol("RemoveCircle");
        Lit572 = (SimpleSymbol) simpleSymbol125.readResolve();
        new SimpleSymbol("null_loader");
        Lit571 = (SimpleSymbol) simpleSymbol126.readResolve();
        new SimpleSymbol("ButtonClick");
        Lit570 = (SimpleSymbol) simpleSymbol127.readResolve();
        new SimpleSymbol("flip");
        Lit569 = (SimpleSymbol) simpleSymbol128.readResolve();
        new FString("com.google.appinventor.components.runtime.TextBox");
        Lit567 = fString95;
        int[] iArr27 = new int[2];
        iArr27[0] = -2544062;
        Lit566 = IntNum.make(iArr27);
        int[] iArr28 = new int[2];
        iArr28[0] = -6381922;
        Lit565 = IntNum.make(iArr28);
        new SimpleSymbol("HintColor");
        Lit564 = (SimpleSymbol) simpleSymbol129.readResolve();
        new SimpleSymbol("Hint");
        Lit563 = (SimpleSymbol) simpleSymbol130.readResolve();
        new FString("com.google.appinventor.components.runtime.TextBox");
        Lit562 = fString96;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit561 = fString97;
        new SimpleSymbol("SearchBar");
        Lit560 = (SimpleSymbol) simpleSymbol131.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit559 = fString98;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit558 = fString99;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit557 = fString100;
        new SimpleSymbol("NeWebView$ProgressChanged");
        Lit556 = (SimpleSymbol) simpleSymbol132.readResolve();
        new FString("com.google.appinventor.components.runtime.WebViewer");
        Lit551 = fString101;
        new FString("com.google.appinventor.components.runtime.WebViewer");
        Lit550 = fString102;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit549 = fString103;
        int[] iArr29 = new int[2];
        iArr29[0] = -1;
        Lit548 = IntNum.make(iArr29);
        new SimpleSymbol("news_load");
        Lit547 = (SimpleSymbol) simpleSymbol133.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit546 = fString104;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit545 = fString105;
        new SimpleSymbol("Space7");
        Lit544 = (SimpleSymbol) simpleSymbol134.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit543 = fString106;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit542 = fString107;
        int[] iArr30 = new int[2];
        iArr30[0] = -17974856;
        Lit541 = IntNum.make(iArr30);
        new SimpleSymbol("Label1");
        Lit540 = (SimpleSymbol) simpleSymbol135.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit539 = fString108;
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit538 = fString109;
        int[] iArr31 = new int[2];
        iArr31[0] = -17582147;
        Lit537 = IntNum.make(iArr31);
        new SimpleSymbol("Circular_Progress3");
        Lit536 = (SimpleSymbol) simpleSymbol136.readResolve();
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit535 = fString110;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit534 = fString111;
        new SimpleSymbol("Horizontal_Arrangement1");
        Lit532 = (SimpleSymbol) simpleSymbol137.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit531 = fString112;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit530 = fString113;
        new SimpleSymbol("news_pro");
        Lit527 = (SimpleSymbol) simpleSymbol138.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit526 = fString114;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit525 = fString115;
        int[] iArr32 = new int[2];
        iArr32[0] = -2544062;
        Lit524 = IntNum.make(iArr32);
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit523 = fString116;
        new SimpleSymbol("InWebView$ProgressChanged");
        Lit522 = (SimpleSymbol) simpleSymbol139.readResolve();
        new FString("com.google.appinventor.components.runtime.WebViewer");
        Lit517 = fString117;
        new FString("com.google.appinventor.components.runtime.WebViewer");
        Lit516 = fString118;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit515 = fString119;
        int[] iArr33 = new int[2];
        iArr33[0] = -19374998;
        Lit514 = IntNum.make(iArr33);
        new SimpleSymbol("ifo_load");
        Lit513 = (SimpleSymbol) simpleSymbol140.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit512 = fString120;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit511 = fString121;
        new SimpleSymbol("Space8");
        Lit510 = (SimpleSymbol) simpleSymbol141.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit509 = fString122;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit508 = fString123;
        int[] iArr34 = new int[2];
        iArr34[0] = -19374998;
        Lit507 = IntNum.make(iArr34);
        new SimpleSymbol("Label2");
        Lit506 = (SimpleSymbol) simpleSymbol142.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit505 = fString124;
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit504 = fString125;
        int[] iArr35 = new int[2];
        iArr35[0] = -19374998;
        Lit503 = IntNum.make(iArr35);
        new SimpleSymbol("Circular_Progress4");
        Lit502 = (SimpleSymbol) simpleSymbol143.readResolve();
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit501 = fString126;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit500 = fString127;
        new SimpleSymbol("Horizontal_Arrangement2");
        Lit498 = (SimpleSymbol) simpleSymbol144.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit497 = fString128;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit496 = fString129;
        new SimpleSymbol("info_por");
        Lit493 = (SimpleSymbol) simpleSymbol145.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit492 = fString130;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit491 = fString131;
        int[] iArr36 = new int[2];
        iArr36[0] = -7969;
        Lit490 = IntNum.make(iArr36);
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit489 = fString132;
        new SimpleSymbol("EmWebView$ProgressChanged");
        Lit488 = (SimpleSymbol) simpleSymbol146.readResolve();
        new FString("com.google.appinventor.components.runtime.WebViewer");
        Lit483 = fString133;
        new FString("com.google.appinventor.components.runtime.WebViewer");
        Lit482 = fString134;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit481 = fString135;
        int[] iArr37 = new int[2];
        iArr37[0] = -2544062;
        Lit480 = IntNum.make(iArr37);
        new SimpleSymbol("em_load");
        Lit479 = (SimpleSymbol) simpleSymbol147.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit478 = fString136;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit477 = fString137;
        new SimpleSymbol("Space8_copy");
        Lit476 = (SimpleSymbol) simpleSymbol148.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit475 = fString138;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit474 = fString139;
        int[] iArr38 = new int[2];
        iArr38[0] = -2544062;
        Lit473 = IntNum.make(iArr38);
        new SimpleSymbol("Label2_copy");
        Lit472 = (SimpleSymbol) simpleSymbol149.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit471 = fString140;
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit470 = fString141;
        int[] iArr39 = new int[2];
        iArr39[0] = -2544062;
        Lit469 = IntNum.make(iArr39);
        new SimpleSymbol("Circular_Progress4_copy");
        Lit468 = (SimpleSymbol) simpleSymbol150.readResolve();
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit467 = fString142;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit466 = fString143;
        new SimpleSymbol("Horizontal_Arrangement2_copy");
        Lit464 = (SimpleSymbol) simpleSymbol151.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit463 = fString144;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit462 = fString145;
        new SimpleSymbol("em_por");
        Lit459 = (SimpleSymbol) simpleSymbol152.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit458 = fString146;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit457 = fString147;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit456 = fString148;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit455 = fString149;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit454 = fString150;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit453 = fString151;
        int[] iArr40 = new int[2];
        iArr40[0] = -7969;
        Lit451 = IntNum.make(iArr40);
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit450 = fString152;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit449 = fString153;
        new SimpleSymbol("bar_prog");
        Lit446 = (SimpleSymbol) simpleSymbol153.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit445 = fString154;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit444 = fString155;
        new SimpleSymbol("Space6_copy");
        Lit442 = (SimpleSymbol) simpleSymbol154.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit441 = fString156;
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit440 = fString157;
        int[] iArr41 = new int[2];
        iArr41[0] = -16850202;
        Lit438 = IntNum.make(iArr41);
        new SimpleSymbol("Circular_Progress1");
        Lit437 = (SimpleSymbol) simpleSymbol155.readResolve();
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit436 = fString158;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit435 = fString159;
        new SimpleSymbol("Space1");
        Lit433 = (SimpleSymbol) simpleSymbol156.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit432 = fString160;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit431 = fString161;
        int[] iArr42 = new int[2];
        iArr42[0] = -1;
        Lit430 = IntNum.make(iArr42);
        new SimpleSymbol("CovidEase");
        Lit428 = (SimpleSymbol) simpleSymbol157.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit427 = fString162;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit426 = fString163;
        new SimpleSymbol("Space6");
        Lit424 = (SimpleSymbol) simpleSymbol158.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit423 = fString164;
        new FString("com.google.appinventor.components.runtime.Image");
        Lit422 = fString165;
        new SimpleSymbol("Image1");
        Lit419 = (SimpleSymbol) simpleSymbol159.readResolve();
        new FString("com.google.appinventor.components.runtime.Image");
        Lit418 = fString166;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit417 = fString167;
        int[] iArr43 = new int[2];
        iArr43[0] = -2544062;
        Lit416 = IntNum.make(iArr43);
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit415 = fString168;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit414 = fString169;
        new SimpleSymbol("Space12");
        Lit412 = (SimpleSymbol) simpleSymbol160.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit411 = fString170;
        new SimpleSymbol("Label11$Click");
        Lit410 = (SimpleSymbol) simpleSymbol161.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit409 = fString171;
        int[] iArr44 = new int[2];
        iArr44[0] = -1;
        Lit408 = IntNum.make(iArr44);
        new SimpleSymbol("Label11");
        Lit406 = (SimpleSymbol) simpleSymbol162.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit405 = fString172;
        new SimpleSymbol("Vertical_Arrangement2$Click");
        Lit404 = (SimpleSymbol) simpleSymbol163.readResolve();
        new SimpleSymbol("GoHome");
        Lit403 = (SimpleSymbol) simpleSymbol164.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit402 = fString173;
        new SimpleSymbol("Vertical_Arrangement2");
        Lit400 = (SimpleSymbol) simpleSymbol165.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit399 = fString174;
        new SimpleSymbol("ProgressChanged");
        Lit398 = (SimpleSymbol) simpleSymbol166.readResolve();
        new SimpleSymbol("IntroWeb_Viewer$ProgressChanged");
        Lit397 = (SimpleSymbol) simpleSymbol167.readResolve();
        new SimpleSymbol("$progress");
        Lit391 = (SimpleSymbol) simpleSymbol168.readResolve();
        new FString("com.google.appinventor.components.runtime.WebViewer");
        Lit390 = fString175;
        new SimpleSymbol("ZoomEnabled");
        Lit389 = (SimpleSymbol) simpleSymbol169.readResolve();
        new SimpleSymbol("Scrollbar");
        Lit388 = (SimpleSymbol) simpleSymbol170.readResolve();
        new SimpleSymbol("HomeUrl");
        Lit387 = (SimpleSymbol) simpleSymbol171.readResolve();
        new FString("com.google.appinventor.components.runtime.WebViewer");
        Lit386 = fString176;
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit385 = fString177;
        int[] iArr45 = new int[2];
        iArr45[0] = -1;
        Lit384 = IntNum.make(iArr45);
        new SimpleSymbol("Color");
        Lit383 = (SimpleSymbol) simpleSymbol172.readResolve();
        new SimpleSymbol("intro_por");
        Lit382 = (SimpleSymbol) simpleSymbol173.readResolve();
        new FString("com.google.appinventor.components.runtime.MakeroidCircularProgress");
        Lit381 = fString178;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit380 = fString179;
        int[] iArr46 = new int[2];
        iArr46[0] = -2544062;
        Lit379 = IntNum.make(iArr46);
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit378 = fString180;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit377 = fString181;
        new SimpleSymbol("Sp");
        Lit376 = (SimpleSymbol) simpleSymbol174.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit375 = fString182;
        new SimpleSymbol("cancel2$Click");
        Lit374 = (SimpleSymbol) simpleSymbol175.readResolve();
        new FString("com.google.appinventor.components.runtime.Button");
        Lit373 = fString183;
        int[] iArr47 = new int[2];
        iArr47[0] = -2544062;
        Lit372 = IntNum.make(iArr47);
        new SimpleSymbol("cancel2");
        Lit371 = (SimpleSymbol) simpleSymbol176.readResolve();
        new FString("com.google.appinventor.components.runtime.Button");
        Lit370 = fString184;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit369 = fString185;
        new SimpleSymbol("Space11");
        Lit368 = (SimpleSymbol) simpleSymbol177.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit367 = fString186;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit366 = fString187;
        int[] iArr48 = new int[2];
        iArr48[0] = -10395295;
        Lit365 = IntNum.make(iArr48);
        new SimpleSymbol("Label7");
        Lit364 = (SimpleSymbol) simpleSymbol178.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit363 = fString188;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit362 = fString189;
        int[] iArr49 = new int[2];
        iArr49[0] = -10395295;
        Lit361 = IntNum.make(iArr49);
        new SimpleSymbol("Label8");
        Lit360 = (SimpleSymbol) simpleSymbol179.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit359 = fString190;
        new FString("com.google.appinventor.components.runtime.Image");
        Lit358 = fString191;
        new SimpleSymbol("Image2_copy1");
        Lit356 = (SimpleSymbol) simpleSymbol180.readResolve();
        new FString("com.google.appinventor.components.runtime.Image");
        Lit355 = fString192;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit354 = fString193;
        int[] iArr50 = new int[2];
        iArr50[0] = -10395295;
        Lit353 = IntNum.make(iArr50);
        new SimpleSymbol("Label7_copy");
        Lit352 = (SimpleSymbol) simpleSymbol181.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit351 = fString194;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit350 = fString195;
        int[] iArr51 = new int[2];
        iArr51[0] = -10395295;
        Lit349 = IntNum.make(iArr51);
        new SimpleSymbol("Label8_copy");
        Lit348 = (SimpleSymbol) simpleSymbol182.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit347 = fString196;
        new FString("com.google.appinventor.components.runtime.Image");
        Lit346 = fString197;
        new SimpleSymbol("Image2");
        Lit344 = (SimpleSymbol) simpleSymbol183.readResolve();
        new FString("com.google.appinventor.components.runtime.Image");
        Lit343 = fString198;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit342 = fString199;
        int[] iArr52 = new int[2];
        iArr52[0] = -10395295;
        Lit341 = IntNum.make(iArr52);
        new SimpleSymbol("Label6");
        Lit340 = (SimpleSymbol) simpleSymbol184.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit339 = fString200;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit338 = fString201;
        int[] iArr53 = new int[2];
        iArr53[0] = -10395295;
        Lit337 = IntNum.make(iArr53);
        new SimpleSymbol("FontBold");
        Lit336 = (SimpleSymbol) simpleSymbol185.readResolve();
        new SimpleSymbol("Label9");
        Lit335 = (SimpleSymbol) simpleSymbol186.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit334 = fString202;
        new FString("com.google.appinventor.components.runtime.Image");
        Lit333 = fString203;
        new SimpleSymbol("Image2_copy");
        Lit331 = (SimpleSymbol) simpleSymbol187.readResolve();
        new FString("com.google.appinventor.components.runtime.Image");
        Lit330 = fString204;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit329 = fString205;
        int[] iArr54 = new int[2];
        iArr54[0] = -19374998;
        Lit328 = IntNum.make(iArr54);
        new SimpleSymbol("Label5");
        Lit327 = (SimpleSymbol) simpleSymbol188.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit326 = fString206;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit325 = fString207;
        int[] iArr55 = new int[2];
        iArr55[0] = -10395295;
        Lit324 = IntNum.make(iArr55);
        new SimpleSymbol("Label4");
        Lit323 = (SimpleSymbol) simpleSymbol189.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit322 = fString208;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit321 = fString209;
        int[] iArr56 = new int[2];
        iArr56[0] = -2544062;
        Lit320 = IntNum.make(iArr56);
        new SimpleSymbol("Label3");
        Lit319 = (SimpleSymbol) simpleSymbol190.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit318 = fString210;
        new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
        Lit317 = fString211;
        new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
        Lit315 = fString212;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit314 = fString213;
        new SimpleSymbol("Space9_copy_copy");
        Lit312 = (SimpleSymbol) simpleSymbol191.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit311 = fString214;
        new SimpleSymbol("POPbutton$Click");
        Lit310 = (SimpleSymbol) simpleSymbol192.readResolve();
        new SimpleSymbol("DataUri");
        Lit309 = (SimpleSymbol) simpleSymbol193.readResolve();
        new SimpleSymbol("intro");
        Lit307 = (SimpleSymbol) simpleSymbol194.readResolve();
        new SimpleSymbol("StartActivity");
        Lit305 = (SimpleSymbol) simpleSymbol195.readResolve();
        new SimpleSymbol("Action");
        Lit304 = (SimpleSymbol) simpleSymbol196.readResolve();
        new SimpleSymbol("Activity_Starter1");
        Lit303 = (SimpleSymbol) simpleSymbol197.readResolve();
        new FString("com.google.appinventor.components.runtime.Button");
        Lit298 = fString215;
        int[] iArr57 = new int[2];
        iArr57[0] = -2544062;
        Lit297 = IntNum.make(iArr57);
        new FString("com.google.appinventor.components.runtime.Button");
        Lit296 = fString216;
        new SimpleSymbol("Button1$Click");
        Lit295 = (SimpleSymbol) simpleSymbol198.readResolve();
        new SimpleSymbol("About");
        Lit294 = (SimpleSymbol) simpleSymbol199.readResolve();
        new FString("com.google.appinventor.components.runtime.Button");
        Lit293 = fString217;
        new SimpleSymbol("Shape");
        Lit292 = (SimpleSymbol) simpleSymbol200.readResolve();
        int[] iArr58 = new int[2];
        iArr58[0] = -21985402;
        Lit290 = IntNum.make(iArr58);
        new SimpleSymbol("Button1");
        Lit289 = (SimpleSymbol) simpleSymbol201.readResolve();
        new FString("com.google.appinventor.components.runtime.Button");
        Lit288 = fString218;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit287 = fString219;
        new SimpleSymbol("Horizontal_Arrangement3");
        Lit286 = (SimpleSymbol) simpleSymbol202.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit285 = fString220;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit284 = fString221;
        new SimpleSymbol("Space9_copy");
        Lit282 = (SimpleSymbol) simpleSymbol203.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit281 = fString222;
        new FString("com.google.appinventor.components.runtime.Label");
        Lit280 = fString223;
        int[] iArr59 = new int[2];
        iArr59[0] = -30791126;
        Lit279 = IntNum.make(iArr59);
        new FString("com.google.appinventor.components.runtime.Label");
        Lit277 = fString224;
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit276 = fString225;
        new SimpleSymbol("Space9");
        Lit274 = (SimpleSymbol) simpleSymbol204.readResolve();
        new FString("com.google.appinventor.components.runtime.SpaceView");
        Lit273 = fString226;
        new FString("com.google.appinventor.components.runtime.Image");
        Lit272 = fString227;
        new SimpleSymbol("Picture");
        Lit271 = (SimpleSymbol) simpleSymbol205.readResolve();
        new SimpleSymbol("At");
        Lit269 = (SimpleSymbol) simpleSymbol206.readResolve();
        new FString("com.google.appinventor.components.runtime.Image");
        Lit268 = fString228;
        new SimpleSymbol("Click");
        Lit267 = (SimpleSymbol) simpleSymbol207.readResolve();
        new SimpleSymbol("Label10$Click");
        Lit266 = (SimpleSymbol) simpleSymbol208.readResolve();
        new SimpleSymbol("DismissCustomDialog");
        Lit265 = (SimpleSymbol) simpleSymbol209.readResolve();
        new SimpleSymbol("location_clk");
        Lit264 = (SimpleSymbol) simpleSymbol210.readResolve();
        new SimpleSymbol("float_clk");
        Lit263 = (SimpleSymbol) simpleSymbol211.readResolve();
        new SimpleSymbol("MainPG");
        Lit262 = (SimpleSymbol) simpleSymbol212.readResolve();
        new SimpleSymbol("LoadPG");
        Lit261 = (SimpleSymbol) simpleSymbol213.readResolve();
        new SimpleSymbol("MoveCamera");
        Lit255 = (SimpleSymbol) simpleSymbol214.readResolve();
        new SimpleSymbol("POPbutton");
        Lit253 = (SimpleSymbol) simpleSymbol215.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit252 = fString229;
        int[] iArr60 = new int[2];
        iArr60[0] = -2544062;
        Lit251 = IntNum.make(iArr60);
        new SimpleSymbol("TextColor");
        Lit250 = (SimpleSymbol) simpleSymbol216.readResolve();
        new SimpleSymbol("TextAlignment");
        Lit249 = (SimpleSymbol) simpleSymbol217.readResolve();
        new SimpleSymbol("HTMLFormat");
        Lit247 = (SimpleSymbol) simpleSymbol218.readResolve();
        new SimpleSymbol("FontTypeface");
        Lit245 = (SimpleSymbol) simpleSymbol219.readResolve();
        new SimpleSymbol("FontSize");
        Lit243 = (SimpleSymbol) simpleSymbol220.readResolve();
        new SimpleSymbol("Clickable");
        Lit242 = (SimpleSymbol) simpleSymbol221.readResolve();
        new FString("com.google.appinventor.components.runtime.Label");
        Lit241 = fString230;
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit240 = fString231;
        new SimpleSymbol("Horizontal_Arrangement4");
        Lit239 = (SimpleSymbol) simpleSymbol222.readResolve();
        new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
        Lit238 = fString232;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit237 = fString233;
        new SimpleSymbol("Height");
        Lit235 = (SimpleSymbol) simpleSymbol223.readResolve();
        new SimpleSymbol("dialouge");
        Lit233 = (SimpleSymbol) simpleSymbol224.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit232 = fString234;
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit231 = fString235;
        new SimpleSymbol("isCard");
        Lit230 = (SimpleSymbol) simpleSymbol225.readResolve();
        new SimpleSymbol("Width");
        Lit228 = (SimpleSymbol) simpleSymbol226.readResolve();
        int[] iArr61 = new int[2];
        iArr61[0] = -18225691;
        Lit227 = IntNum.make(iArr61);
        new SimpleSymbol("BackgroundColor");
        Lit226 = (SimpleSymbol) simpleSymbol227.readResolve();
        new SimpleSymbol("AlignVertical");
        Lit225 = (SimpleSymbol) simpleSymbol228.readResolve();
        new SimpleSymbol("AlignHorizontal");
        Lit224 = (SimpleSymbol) simpleSymbol229.readResolve();
        new FString("com.google.appinventor.components.runtime.VerticalArrangement");
        Lit223 = fString236;
        new SimpleSymbol("BackPressed");
        Lit222 = (SimpleSymbol) simpleSymbol230.readResolve();
        new SimpleSymbol("Screen1$BackPressed");
        Lit221 = (SimpleSymbol) simpleSymbol231.readResolve();
        new SimpleSymbol("HideKeyboard");
        Lit220 = (SimpleSymbol) simpleSymbol232.readResolve();
        new SimpleSymbol("SelectItem");
        Lit218 = (SimpleSymbol) simpleSymbol233.readResolve();
        new SimpleSymbol("Label10");
        Lit214 = (SimpleSymbol) simpleSymbol234.readResolve();
        new SimpleSymbol("POPtitle");
        Lit213 = (SimpleSymbol) simpleSymbol235.readResolve();
        new SimpleSymbol("MarkerPOP");
        Lit212 = (SimpleSymbol) simpleSymbol236.readResolve();
        new SimpleSymbol("RequestFocus");
        Lit211 = (SimpleSymbol) simpleSymbol237.readResolve();
        new SimpleSymbol("Text_Box1");
        Lit210 = (SimpleSymbol) simpleSymbol238.readResolve();
        new SimpleSymbol("HomePG");
        Lit209 = (SimpleSymbol) simpleSymbol239.readResolve();
        new SimpleSymbol("InfoPG");
        Lit207 = (SimpleSymbol) simpleSymbol240.readResolve();
        new SimpleSymbol("EmergencyPG");
        Lit205 = (SimpleSymbol) simpleSymbol241.readResolve();
        new SimpleSymbol("GoBack");
        Lit204 = (SimpleSymbol) simpleSymbol242.readResolve();
        new SimpleSymbol("CurrentPageTitle");
        Lit202 = (SimpleSymbol) simpleSymbol243.readResolve();
        new SimpleSymbol("NewsPG");
        Lit201 = (SimpleSymbol) simpleSymbol244.readResolve();
        new SimpleSymbol("KeyboardVisiblityChanged");
        Lit200 = (SimpleSymbol) simpleSymbol245.readResolve();
        new SimpleSymbol("Screen1$KeyboardVisiblityChanged");
        Lit199 = (SimpleSymbol) simpleSymbol246.readResolve();
        new SimpleSymbol("TimerEnabled");
        Lit198 = (SimpleSymbol) simpleSymbol247.readResolve();
        new SimpleSymbol("keyboard_timer");
        Lit197 = (SimpleSymbol) simpleSymbol248.readResolve();
        new SimpleSymbol("$isKeyboardVisible");
        Lit196 = (SimpleSymbol) simpleSymbol249.readResolve();
        new SimpleSymbol("Initialize");
        Lit195 = (SimpleSymbol) simpleSymbol250.readResolve();
        new SimpleSymbol("Screen1$Initialize");
        Lit194 = (SimpleSymbol) simpleSymbol251.readResolve();
        new SimpleSymbol("ShowCustomDialog");
        Lit193 = (SimpleSymbol) simpleSymbol252.readResolve();
        new SimpleSymbol("GetValue");
        Lit191 = (SimpleSymbol) simpleSymbol253.readResolve();
        new SimpleSymbol("ProjectBucket");
        Lit190 = (SimpleSymbol) simpleSymbol254.readResolve();
        new SimpleSymbol("Firebase_Database1");
        Lit189 = (SimpleSymbol) simpleSymbol255.readResolve();
        new SimpleSymbol("ClearCookies");
        Lit188 = (SimpleSymbol) simpleSymbol256.readResolve();
        new SimpleSymbol("IntroWeb_Viewer");
        Lit187 = (SimpleSymbol) simpleSymbol257.readResolve();
        new SimpleSymbol("EmWebView");
        Lit186 = (SimpleSymbol) simpleSymbol258.readResolve();
        new SimpleSymbol("InWebView");
        Lit185 = (SimpleSymbol) simpleSymbol259.readResolve();
        new SimpleSymbol("ClearCaches");
        Lit184 = (SimpleSymbol) simpleSymbol260.readResolve();
        new SimpleSymbol("NeWebView");
        Lit183 = (SimpleSymbol) simpleSymbol261.readResolve();
        new SimpleSymbol("AddItem");
        Lit177 = (SimpleSymbol) simpleSymbol262.readResolve();
        new SimpleSymbol("Tabs");
        Lit176 = (SimpleSymbol) simpleSymbol263.readResolve();
        new SimpleSymbol("WidthPercent");
        Lit174 = (SimpleSymbol) simpleSymbol264.readResolve();
        new SimpleSymbol(NotificationCompat.CATEGORY_PROGRESS);
        Lit173 = (SimpleSymbol) simpleSymbol265.readResolve();
        new SimpleSymbol("IsConnected");
        Lit171 = (SimpleSymbol) simpleSymbol266.readResolve();
        new SimpleSymbol("Internet");
        Lit170 = (SimpleSymbol) simpleSymbol267.readResolve();
        new SimpleSymbol("POPLayout");
        Lit168 = (SimpleSymbol) simpleSymbol268.readResolve();
        new SimpleSymbol("CreateCustomDialog");
        Lit167 = (SimpleSymbol) simpleSymbol269.readResolve();
        new SimpleSymbol("DialoguePOP");
        Lit166 = (SimpleSymbol) simpleSymbol270.readResolve();
        new SimpleSymbol("VersionName");
        Lit165 = (SimpleSymbol) simpleSymbol271.readResolve();
        new SimpleSymbol("TitleVisible");
        Lit164 = (SimpleSymbol) simpleSymbol272.readResolve();
        new SimpleSymbol("Title");
        Lit163 = (SimpleSymbol) simpleSymbol273.readResolve();
        new SimpleSymbol("ScreenOrientation");
        Lit162 = (SimpleSymbol) simpleSymbol274.readResolve();
        int[] iArr62 = new int[2];
        iArr62[0] = -24960724;
        Lit161 = IntNum.make(iArr62);
        new SimpleSymbol("PrimaryColorDark");
        Lit160 = (SimpleSymbol) simpleSymbol275.readResolve();
        int[] iArr63 = new int[2];
        iArr63[0] = -24960724;
        Lit159 = IntNum.make(iArr63);
        new SimpleSymbol("PrimaryColor");
        Lit158 = (SimpleSymbol) simpleSymbol276.readResolve();
        new SimpleSymbol("PackageName");
        Lit157 = (SimpleSymbol) simpleSymbol277.readResolve();
        new SimpleSymbol("OpenScreenAnimation");
        Lit156 = (SimpleSymbol) simpleSymbol278.readResolve();
        int[] iArr64 = new int[2];
        iArr64[0] = -2544062;
        Lit155 = IntNum.make(iArr64);
        new SimpleSymbol("NavigationBarColor");
        Lit154 = (SimpleSymbol) simpleSymbol279.readResolve();
        new SimpleSymbol("Icon");
        Lit153 = (SimpleSymbol) simpleSymbol280.readResolve();
        new SimpleSymbol("CloseScreenAnimation");
        Lit152 = (SimpleSymbol) simpleSymbol281.readResolve();
        new SimpleSymbol("AppName");
        Lit151 = (SimpleSymbol) simpleSymbol282.readResolve();
        new SimpleSymbol("AppId");
        Lit150 = (SimpleSymbol) simpleSymbol283.readResolve();
        int[] iArr65 = new int[2];
        iArr65[0] = -16777216;
        Lit149 = IntNum.make(iArr65);
        new SimpleSymbol("AccentColor");
        Lit148 = (SimpleSymbol) simpleSymbol284.readResolve();
        int[] iArr66 = new int[2];
        iArr66[0] = -1;
        Lit146 = IntNum.make(iArr66);
        new SimpleSymbol("AboutScreenBackgroundColor");
        Lit145 = (SimpleSymbol) simpleSymbol285.readResolve();
        new SimpleSymbol("AboutScreen");
        Lit144 = (SimpleSymbol) simpleSymbol286.readResolve();
        new SimpleSymbol("Elements");
        Lit102 = (SimpleSymbol) simpleSymbol287.readResolve();
        new SimpleSymbol("scname_list");
        Lit101 = (SimpleSymbol) simpleSymbol288.readResolve();
        new SimpleSymbol("SCquPOP");
        Lit98 = (SimpleSymbol) simpleSymbol289.readResolve();
        new SimpleSymbol("down");
        Lit97 = (SimpleSymbol) simpleSymbol290.readResolve();
        new SimpleSymbol("SearchQury");
        Lit96 = (SimpleSymbol) simpleSymbol291.readResolve();
        new SimpleSymbol("scname_div");
        Lit95 = (SimpleSymbol) simpleSymbol292.readResolve();
        new SimpleSymbol("Text");
        Lit61 = (SimpleSymbol) simpleSymbol293.readResolve();
        new SimpleSymbol("Search");
        Lit60 = (SimpleSymbol) simpleSymbol294.readResolve();
        new SimpleSymbol("p$search");
        Lit59 = (SimpleSymbol) simpleSymbol295.readResolve();
        new SimpleSymbol("Visible");
        Lit47 = (SimpleSymbol) simpleSymbol296.readResolve();
        new SimpleSymbol("Loading");
        Lit46 = (SimpleSymbol) simpleSymbol297.readResolve();
        new SimpleSymbol("AddMarkersFromJson");
        Lit41 = (SimpleSymbol) simpleSymbol298.readResolve();
        new SimpleSymbol("GetStringInArray");
        Lit37 = (SimpleSymbol) simpleSymbol299.readResolve();
        new SimpleSymbol("g$c2");
        Lit34 = (SimpleSymbol) simpleSymbol300.readResolve();
        new SimpleSymbol("ParseJSON");
        Lit32 = (SimpleSymbol) simpleSymbol301.readResolve();
        new SimpleSymbol("JSONTools1");
        Lit31 = (SimpleSymbol) simpleSymbol302.readResolve();
        new SimpleSymbol("GetAllMarkerID");
        Lit30 = (SimpleSymbol) simpleSymbol303.readResolve();
        new SimpleSymbol("$item");
        Lit28 = (SimpleSymbol) simpleSymbol304.readResolve();
        new SimpleSymbol("RemoveMarker");
        Lit27 = (SimpleSymbol) simpleSymbol305.readResolve();
        new SimpleSymbol("Gmap");
        Lit26 = (SimpleSymbol) simpleSymbol306.readResolve();
        new SimpleSymbol("p$Marker_reset");
        Lit25 = (SimpleSymbol) simpleSymbol307.readResolve();
        new SimpleSymbol("g$filter_code");
        Lit22 = (SimpleSymbol) simpleSymbol308.readResolve();
        new SimpleSymbol("g$prog");
        Lit20 = (SimpleSymbol) simpleSymbol309.readResolve();
        new SimpleSymbol("g$circle_id");
        Lit19 = (SimpleSymbol) simpleSymbol310.readResolve();
        new SimpleSymbol("g$json_filer");
        Lit18 = (SimpleSymbol) simpleSymbol311.readResolve();
        new SimpleSymbol("g$scname");
        Lit17 = (SimpleSymbol) simpleSymbol312.readResolve();
        new SimpleSymbol("g$details");
        Lit16 = (SimpleSymbol) simpleSymbol313.readResolve();
        new SimpleSymbol("g$phone");
        Lit15 = (SimpleSymbol) simpleSymbol314.readResolve();
        new SimpleSymbol("g$scindex");
        Lit14 = (SimpleSymbol) simpleSymbol315.readResolve();
        new SimpleSymbol("g$json");
        Lit13 = (SimpleSymbol) simpleSymbol316.readResolve();
        new SimpleSymbol("g$c1");
        Lit12 = (SimpleSymbol) simpleSymbol317.readResolve();
        new SimpleSymbol("g$back");
        Lit11 = (SimpleSymbol) simpleSymbol318.readResolve();
        new SimpleSymbol("g$back3");
        Lit10 = (SimpleSymbol) simpleSymbol319.readResolve();
        new SimpleSymbol("g$id1");
        Lit9 = (SimpleSymbol) simpleSymbol320.readResolve();
        new SimpleSymbol("g$back2");
        Lit8 = (SimpleSymbol) simpleSymbol321.readResolve();
        new SimpleSymbol("g$check");
        Lit7 = (SimpleSymbol) simpleSymbol322.readResolve();
        new SimpleSymbol("g$counter");
        Lit6 = (SimpleSymbol) simpleSymbol323.readResolve();
        new SimpleSymbol("g$count");
        Lit4 = (SimpleSymbol) simpleSymbol324.readResolve();
        new SimpleSymbol("g$flclk");
        Lit3 = (SimpleSymbol) simpleSymbol325.readResolve();
        new SimpleSymbol("*the-null-value*");
        Lit2 = (SimpleSymbol) simpleSymbol326.readResolve();
        new SimpleSymbol("getMessage");
        Lit1 = (SimpleSymbol) simpleSymbol327.readResolve();
        new SimpleSymbol("Screen1");
        Lit0 = (SimpleSymbol) simpleSymbol328.readResolve();
    }

    public Screen1() {
        ModuleMethod moduleMethod;
        frame frame3;
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
        ModuleMethod moduleMethod134;
        ModuleMethod moduleMethod135;
        ModuleMethod moduleMethod136;
        ModuleMethod moduleMethod137;
        ModuleMethod moduleMethod138;
        ModuleMethod moduleMethod139;
        ModuleMethod moduleMethod140;
        ModuleMethod moduleMethod141;
        ModuleMethod moduleMethod142;
        ModuleMethod moduleMethod143;
        ModuleMethod moduleMethod144;
        ModuleMethod moduleMethod145;
        ModuleMethod moduleMethod146;
        ModuleMethod moduleMethod147;
        ModuleMethod moduleMethod148;
        ModuleMethod moduleMethod149;
        ModuleMethod moduleMethod150;
        ModuleMethod moduleMethod151;
        ModuleMethod moduleMethod152;
        ModuleMethod moduleMethod153;
        ModuleMethod moduleMethod154;
        ModuleMethod moduleMethod155;
        ModuleMethod moduleMethod156;
        ModuleMethod moduleMethod157;
        ModuleMethod moduleMethod158;
        ModuleMethod moduleMethod159;
        ModuleMethod moduleMethod160;
        ModuleMethod moduleMethod161;
        ModuleMethod moduleMethod162;
        ModuleMethod moduleMethod163;
        ModuleMethod moduleMethod164;
        ModuleMethod moduleMethod165;
        ModuleMethod moduleMethod166;
        ModuleMethod moduleMethod167;
        ModuleMethod moduleMethod168;
        ModuleMethod moduleMethod169;
        ModuleMethod moduleMethod170;
        ModuleMethod moduleMethod171;
        ModuleMethod moduleMethod172;
        ModuleMethod moduleMethod173;
        ModuleMethod moduleMethod174;
        ModuleMethod moduleMethod175;
        ModuleMethod moduleMethod176;
        ModuleMethod moduleMethod177;
        ModuleMethod moduleMethod178;
        ModuleMethod moduleMethod179;
        ModuleMethod moduleMethod180;
        ModuleMethod moduleMethod181;
        ModuleMethod moduleMethod182;
        ModuleMethod moduleMethod183;
        ModuleMethod moduleMethod184;
        ModuleMethod moduleMethod185;
        ModuleMethod moduleMethod186;
        ModuleMethod moduleMethod187;
        ModuleMethod moduleMethod188;
        ModuleMethod moduleMethod189;
        ModuleMethod moduleMethod190;
        ModuleMethod moduleMethod191;
        ModuleMethod moduleMethod192;
        ModuleMethod moduleMethod193;
        ModuleMethod moduleMethod194;
        ModuleMethod moduleMethod195;
        ModuleMethod moduleMethod196;
        ModuleMethod moduleMethod197;
        ModuleMethod moduleMethod198;
        ModuleMethod moduleMethod199;
        ModuleMethod moduleMethod200;
        ModuleMethod moduleMethod201;
        ModuleMethod moduleMethod202;
        ModuleMethod moduleMethod203;
        ModuleMethod moduleMethod204;
        ModuleMethod moduleMethod205;
        ModuleMethod moduleMethod206;
        ModuleMethod moduleMethod207;
        ModuleMethod moduleMethod208;
        ModuleMethod moduleMethod209;
        ModuleMethod moduleMethod210;
        ModuleMethod moduleMethod211;
        ModuleMethod moduleMethod212;
        ModuleMethod moduleMethod213;
        ModuleMethod moduleMethod214;
        ModuleMethod moduleMethod215;
        ModuleMethod moduleMethod216;
        ModuleMethod moduleMethod217;
        ModuleMethod moduleMethod218;
        ModuleMethod moduleMethod219;
        ModuleMethod moduleMethod220;
        ModuleMethod moduleMethod221;
        ModuleMethod moduleMethod222;
        ModuleMethod moduleMethod223;
        ModuleMethod moduleMethod224;
        ModuleMethod moduleMethod225;
        ModuleMethod moduleMethod226;
        ModuleMethod moduleMethod227;
        ModuleMethod moduleMethod228;
        ModuleMethod moduleMethod229;
        ModuleMethod moduleMethod230;
        ModuleMethod moduleMethod231;
        ModuleMethod moduleMethod232;
        ModuleMethod moduleMethod233;
        ModuleMethod moduleMethod234;
        ModuleMethod moduleMethod235;
        ModuleMethod moduleMethod236;
        ModuleMethod moduleMethod237;
        ModuleMethod moduleMethod238;
        ModuleMethod moduleMethod239;
        ModuleMethod moduleMethod240;
        ModuleMethod moduleMethod241;
        ModuleMethod moduleMethod242;
        ModuleMethod moduleMethod243;
        ModuleMethod moduleMethod244;
        ModuleMethod moduleMethod245;
        ModuleMethod moduleMethod246;
        ModuleMethod moduleMethod247;
        ModuleMethod moduleMethod248;
        ModuleMethod moduleMethod249;
        ModuleMethod moduleMethod250;
        ModuleMethod moduleMethod251;
        ModuleMethod moduleMethod252;
        ModuleMethod moduleMethod253;
        ModuleMethod moduleMethod254;
        ModuleMethod moduleMethod255;
        ModuleMethod moduleMethod256;
        ModuleMethod moduleMethod257;
        ModuleMethod moduleMethod258;
        ModuleMethod moduleMethod259;
        ModuleMethod moduleMethod260;
        ModuleMethod moduleMethod261;
        ModuleMethod moduleMethod262;
        ModuleMethod moduleMethod263;
        ModuleMethod moduleMethod264;
        ModuleMethod moduleMethod265;
        ModuleMethod moduleMethod266;
        ModuleMethod moduleMethod267;
        ModuleMethod moduleMethod268;
        ModuleMethod moduleMethod269;
        ModuleMethod moduleMethod270;
        ModuleMethod moduleMethod271;
        ModuleMethod moduleMethod272;
        ModuleMethod moduleMethod273;
        ModuleMethod moduleMethod274;
        ModuleMethod moduleMethod275;
        ModuleMethod moduleMethod276;
        ModuleMethod moduleMethod277;
        ModuleMethod moduleMethod278;
        ModuleMethod moduleMethod279;
        ModuleMethod moduleMethod280;
        ModuleMethod moduleMethod281;
        ModuleMethod moduleMethod282;
        ModuleMethod moduleMethod283;
        ModuleMethod moduleMethod284;
        ModuleMethod moduleMethod285;
        ModuleMethod moduleMethod286;
        ModuleMethod moduleMethod287;
        ModuleMethod moduleMethod288;
        ModuleMethod moduleMethod289;
        ModuleMethod moduleMethod290;
        ModuleMethod moduleMethod291;
        ModuleMethod moduleMethod292;
        ModuleMethod moduleMethod293;
        ModuleMethod moduleMethod294;
        ModuleMethod moduleMethod295;
        ModuleMethod moduleMethod296;
        ModuleMethod moduleMethod297;
        ModuleMethod moduleMethod298;
        ModuleMethod moduleMethod299;
        ModuleMethod moduleMethod300;
        ModuleMethod moduleMethod301;
        ModuleMethod moduleMethod302;
        ModuleMethod moduleMethod303;
        ModuleMethod moduleMethod304;
        ModuleMethod moduleMethod305;
        ModuleMethod moduleMethod306;
        ModuleMethod moduleMethod307;
        ModuleMethod moduleMethod308;
        ModuleMethod moduleMethod309;
        ModuleMethod moduleMethod310;
        ModuleMethod moduleMethod311;
        ModuleMethod moduleMethod312;
        ModuleMethod moduleMethod313;
        ModuleMethod moduleMethod314;
        ModuleMethod moduleMethod315;
        ModuleMethod moduleMethod316;
        ModuleMethod moduleMethod317;
        ModuleMethod moduleMethod318;
        ModuleMethod moduleMethod319;
        ModuleMethod moduleMethod320;
        ModuleMethod moduleMethod321;
        ModuleMethod moduleMethod322;
        ModuleMethod moduleMethod323;
        ModuleMethod moduleMethod324;
        ModuleMethod moduleMethod325;
        ModuleMethod moduleMethod326;
        ModuleMethod moduleMethod327;
        ModuleMethod moduleMethod328;
        ModuleMethod moduleMethod329;
        ModuleMethod moduleMethod330;
        ModuleMethod moduleMethod331;
        ModuleMethod moduleMethod332;
        ModuleMethod moduleMethod333;
        ModuleInfo.register(this);
        ModuleMethod moduleMethod334 = moduleMethod;
        new frame();
        frame frame4 = frame3;
        frame4.$main = this;
        frame frame5 = frame4;
        new ModuleMethod(frame5, 5, Lit928, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.get$Mnsimple$Mnname = moduleMethod334;
        new ModuleMethod(frame5, 6, Lit929, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = moduleMethod2;
        new ModuleMethod(frame5, 7, Lit930, 8194);
        this.add$Mnto$Mnform$Mnenvironment = moduleMethod3;
        new ModuleMethod(frame5, 8, Lit931, 8193);
        this.lookup$Mnin$Mnform$Mnenvironment = moduleMethod4;
        new ModuleMethod(frame5, 10, Lit932, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = moduleMethod5;
        new ModuleMethod(frame5, 11, Lit933, 8194);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = moduleMethod6;
        new ModuleMethod(frame5, 12, Lit934, 8194);
        this.add$Mnto$Mnevents = moduleMethod7;
        new ModuleMethod(frame5, 13, Lit935, 16388);
        this.add$Mnto$Mncomponents = moduleMethod8;
        new ModuleMethod(frame5, 14, Lit936, 8194);
        this.add$Mnto$Mnglobal$Mnvars = moduleMethod9;
        new ModuleMethod(frame5, 15, Lit937, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = moduleMethod10;
        new ModuleMethod(frame5, 16, Lit938, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = moduleMethod11;
        new ModuleMethod(frame5, 17, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = moduleMethod12;
        new ModuleMethod(frame5, 18, Lit939, 16388);
        this.dispatchEvent = moduleMethod13;
        new ModuleMethod(frame5, 19, Lit940, 16388);
        this.dispatchGenericEvent = moduleMethod14;
        new ModuleMethod(frame5, 20, Lit941, 8194);
        this.lookup$Mnhandler = moduleMethod15;
        new ModuleMethod(frame5, 21, (Object) null, 0);
        ModuleMethod moduleMethod335 = moduleMethod16;
        moduleMethod335.setProperty("source-location", "/tmp/runtime3919887220254105238.scm:615");
        lambda$Fn1 = moduleMethod335;
        new ModuleMethod(frame5, 22, "$define", 0);
        this.$define = moduleMethod17;
        new ModuleMethod(frame5, 23, (Object) null, 0);
        lambda$Fn2 = moduleMethod18;
        new ModuleMethod(frame5, 24, (Object) null, 0);
        lambda$Fn3 = moduleMethod19;
        new ModuleMethod(frame5, 25, (Object) null, 0);
        lambda$Fn4 = moduleMethod20;
        new ModuleMethod(frame5, 26, (Object) null, 0);
        lambda$Fn5 = moduleMethod21;
        new ModuleMethod(frame5, 27, (Object) null, 0);
        lambda$Fn6 = moduleMethod22;
        new ModuleMethod(frame5, 28, (Object) null, 0);
        lambda$Fn7 = moduleMethod23;
        new ModuleMethod(frame5, 29, (Object) null, 0);
        lambda$Fn8 = moduleMethod24;
        new ModuleMethod(frame5, 30, (Object) null, 0);
        lambda$Fn9 = moduleMethod25;
        new ModuleMethod(frame5, 31, (Object) null, 0);
        lambda$Fn10 = moduleMethod26;
        new ModuleMethod(frame5, 32, (Object) null, 0);
        lambda$Fn11 = moduleMethod27;
        new ModuleMethod(frame5, 33, (Object) null, 0);
        lambda$Fn12 = moduleMethod28;
        new ModuleMethod(frame5, 34, (Object) null, 0);
        lambda$Fn13 = moduleMethod29;
        new ModuleMethod(frame5, 35, (Object) null, 0);
        lambda$Fn14 = moduleMethod30;
        new ModuleMethod(frame5, 36, (Object) null, 0);
        lambda$Fn15 = moduleMethod31;
        new ModuleMethod(frame5, 37, (Object) null, 0);
        lambda$Fn16 = moduleMethod32;
        new ModuleMethod(frame5, 38, (Object) null, 0);
        lambda$Fn17 = moduleMethod33;
        new ModuleMethod(frame5, 39, (Object) null, 0);
        lambda$Fn18 = moduleMethod34;
        new ModuleMethod(frame5, 40, (Object) null, 0);
        lambda$Fn19 = moduleMethod35;
        new ModuleMethod(frame5, 41, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn21 = moduleMethod36;
        new ModuleMethod(frame5, 42, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn22 = moduleMethod37;
        new ModuleMethod(frame5, 43, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn23 = moduleMethod38;
        new ModuleMethod(frame5, 44, (Object) null, 0);
        lambda$Fn20 = moduleMethod39;
        new ModuleMethod(frame5, 45, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn26 = moduleMethod40;
        new ModuleMethod(frame5, 46, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn27 = moduleMethod41;
        new ModuleMethod(frame5, 47, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn28 = moduleMethod42;
        new ModuleMethod(frame5, 48, (Object) null, 0);
        lambda$Fn25 = moduleMethod43;
        new ModuleMethod(frame5, 49, (Object) null, 0);
        lambda$Fn24 = moduleMethod44;
        new ModuleMethod(frame5, 50, (Object) null, 0);
        lambda$Fn29 = moduleMethod45;
        new ModuleMethod(frame5, 51, (Object) null, 0);
        lambda$Fn31 = moduleMethod46;
        new ModuleMethod(frame5, 52, (Object) null, 0);
        lambda$Fn32 = moduleMethod47;
        new ModuleMethod(frame5, 53, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn33 = moduleMethod48;
        new ModuleMethod(frame5, 54, (Object) null, 0);
        lambda$Fn36 = moduleMethod49;
        new ModuleMethod(frame5, 55, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn34 = moduleMethod50;
        new ModuleMethod(frame5, 56, (Object) null, 0);
        lambda$Fn30 = moduleMethod51;
        new ModuleMethod(frame5, 57, (Object) null, 0);
        lambda$Fn39 = moduleMethod52;
        new ModuleMethod(frame5, 58, (Object) null, 0);
        lambda$Fn40 = moduleMethod53;
        new ModuleMethod(frame5, 59, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn41 = moduleMethod54;
        new ModuleMethod(frame5, 60, (Object) null, 0);
        lambda$Fn44 = moduleMethod55;
        new ModuleMethod(frame5, 61, Lit942, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proc$Fn42 = moduleMethod56;
        new ModuleMethod(frame5, 62, (Object) null, 0);
        lambda$Fn38 = moduleMethod57;
        new ModuleMethod(frame5, 63, (Object) null, 0);
        lambda$Fn37 = moduleMethod58;
        new ModuleMethod(frame5, 64, (Object) null, 0);
        lambda$Fn45 = moduleMethod59;
        new ModuleMethod(frame5, 65, Lit194, 0);
        this.Screen1$Initialize = moduleMethod60;
        new ModuleMethod(frame5, 66, Lit199, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.Screen1$KeyboardVisiblityChanged = moduleMethod61;
        new ModuleMethod(frame5, 67, (Object) null, 0);
        lambda$Fn46 = moduleMethod62;
        new ModuleMethod(frame5, 68, (Object) null, 0);
        lambda$Fn47 = moduleMethod63;
        new ModuleMethod(frame5, 69, (Object) null, 0);
        lambda$Fn48 = moduleMethod64;
        new ModuleMethod(frame5, 70, (Object) null, 0);
        lambda$Fn49 = moduleMethod65;
        new ModuleMethod(frame5, 71, (Object) null, 0);
        lambda$Fn50 = moduleMethod66;
        new ModuleMethod(frame5, 72, (Object) null, 0);
        lambda$Fn51 = moduleMethod67;
        new ModuleMethod(frame5, 73, (Object) null, 0);
        lambda$Fn54 = moduleMethod68;
        new ModuleMethod(frame5, 74, (Object) null, 0);
        lambda$Fn55 = moduleMethod69;
        new ModuleMethod(frame5, 75, (Object) null, 0);
        lambda$Fn53 = moduleMethod70;
        new ModuleMethod(frame5, 76, (Object) null, 0);
        lambda$Fn57 = moduleMethod71;
        new ModuleMethod(frame5, 77, (Object) null, 0);
        lambda$Fn58 = moduleMethod72;
        new ModuleMethod(frame5, 78, (Object) null, 0);
        lambda$Fn56 = moduleMethod73;
        new ModuleMethod(frame5, 79, (Object) null, 0);
        lambda$Fn52 = moduleMethod74;
        new ModuleMethod(frame5, 80, (Object) null, 0);
        lambda$Fn60 = moduleMethod75;
        new ModuleMethod(frame5, 81, (Object) null, 0);
        lambda$Fn61 = moduleMethod76;
        new ModuleMethod(frame5, 82, (Object) null, 0);
        lambda$Fn59 = moduleMethod77;
        new ModuleMethod(frame5, 83, Lit221, 0);
        this.Screen1$BackPressed = moduleMethod78;
        new ModuleMethod(frame5, 84, (Object) null, 0);
        lambda$Fn62 = moduleMethod79;
        new ModuleMethod(frame5, 85, (Object) null, 0);
        lambda$Fn63 = moduleMethod80;
        new ModuleMethod(frame5, 86, (Object) null, 0);
        lambda$Fn64 = moduleMethod81;
        new ModuleMethod(frame5, 87, (Object) null, 0);
        lambda$Fn65 = moduleMethod82;
        new ModuleMethod(frame5, 88, (Object) null, 0);
        lambda$Fn66 = moduleMethod83;
        new ModuleMethod(frame5, 89, (Object) null, 0);
        lambda$Fn67 = moduleMethod84;
        new ModuleMethod(frame5, 90, (Object) null, 0);
        lambda$Fn68 = moduleMethod85;
        new ModuleMethod(frame5, 91, (Object) null, 0);
        lambda$Fn69 = moduleMethod86;
        new ModuleMethod(frame5, 92, Lit266, 0);
        this.Label10$Click = moduleMethod87;
        new ModuleMethod(frame5, 93, (Object) null, 0);
        lambda$Fn70 = moduleMethod88;
        new ModuleMethod(frame5, 94, (Object) null, 0);
        lambda$Fn71 = moduleMethod89;
        new ModuleMethod(frame5, 95, (Object) null, 0);
        lambda$Fn72 = moduleMethod90;
        new ModuleMethod(frame5, 96, (Object) null, 0);
        lambda$Fn73 = moduleMethod91;
        new ModuleMethod(frame5, 97, (Object) null, 0);
        lambda$Fn74 = moduleMethod92;
        new ModuleMethod(frame5, 98, (Object) null, 0);
        lambda$Fn75 = moduleMethod93;
        new ModuleMethod(frame5, 99, (Object) null, 0);
        lambda$Fn76 = moduleMethod94;
        new ModuleMethod(frame5, 100, (Object) null, 0);
        lambda$Fn77 = moduleMethod95;
        new ModuleMethod(frame5, 101, (Object) null, 0);
        lambda$Fn78 = moduleMethod96;
        new ModuleMethod(frame5, 102, (Object) null, 0);
        lambda$Fn79 = moduleMethod97;
        new ModuleMethod(frame5, 103, (Object) null, 0);
        lambda$Fn80 = moduleMethod98;
        new ModuleMethod(frame5, 104, (Object) null, 0);
        lambda$Fn81 = moduleMethod99;
        new ModuleMethod(frame5, 105, Lit295, 0);
        this.Button1$Click = moduleMethod100;
        new ModuleMethod(frame5, 106, (Object) null, 0);
        lambda$Fn82 = moduleMethod101;
        new ModuleMethod(frame5, 107, (Object) null, 0);
        lambda$Fn83 = moduleMethod102;
        new ModuleMethod(frame5, 108, Lit310, 0);
        this.POPbutton$Click = moduleMethod103;
        new ModuleMethod(frame5, 109, (Object) null, 0);
        lambda$Fn84 = moduleMethod104;
        new ModuleMethod(frame5, 110, (Object) null, 0);
        lambda$Fn85 = moduleMethod105;
        new ModuleMethod(frame5, 111, (Object) null, 0);
        lambda$Fn86 = moduleMethod106;
        new ModuleMethod(frame5, 112, (Object) null, 0);
        lambda$Fn87 = moduleMethod107;
        new ModuleMethod(frame5, 113, (Object) null, 0);
        lambda$Fn88 = moduleMethod108;
        new ModuleMethod(frame5, 114, (Object) null, 0);
        lambda$Fn89 = moduleMethod109;
        new ModuleMethod(frame5, 115, (Object) null, 0);
        lambda$Fn90 = moduleMethod110;
        new ModuleMethod(frame5, 116, (Object) null, 0);
        lambda$Fn91 = moduleMethod111;
        new ModuleMethod(frame5, 117, (Object) null, 0);
        lambda$Fn92 = moduleMethod112;
        new ModuleMethod(frame5, 118, (Object) null, 0);
        lambda$Fn93 = moduleMethod113;
        new ModuleMethod(frame5, 119, (Object) null, 0);
        lambda$Fn94 = moduleMethod114;
        new ModuleMethod(frame5, 120, (Object) null, 0);
        lambda$Fn95 = moduleMethod115;
        new ModuleMethod(frame5, 121, (Object) null, 0);
        lambda$Fn96 = moduleMethod116;
        new ModuleMethod(frame5, 122, (Object) null, 0);
        lambda$Fn97 = moduleMethod117;
        new ModuleMethod(frame5, 123, (Object) null, 0);
        lambda$Fn98 = moduleMethod118;
        new ModuleMethod(frame5, 124, (Object) null, 0);
        lambda$Fn99 = moduleMethod119;
        new ModuleMethod(frame5, 125, (Object) null, 0);
        lambda$Fn100 = moduleMethod120;
        new ModuleMethod(frame5, 126, (Object) null, 0);
        lambda$Fn101 = moduleMethod121;
        new ModuleMethod(frame5, 127, (Object) null, 0);
        lambda$Fn102 = moduleMethod122;
        new ModuleMethod(frame5, 128, (Object) null, 0);
        lambda$Fn103 = moduleMethod123;
        new ModuleMethod(frame5, 129, (Object) null, 0);
        lambda$Fn104 = moduleMethod124;
        new ModuleMethod(frame5, 130, (Object) null, 0);
        lambda$Fn105 = moduleMethod125;
        new ModuleMethod(frame5, 131, (Object) null, 0);
        lambda$Fn106 = moduleMethod126;
        new ModuleMethod(frame5, 132, (Object) null, 0);
        lambda$Fn107 = moduleMethod127;
        new ModuleMethod(frame5, 133, (Object) null, 0);
        lambda$Fn108 = moduleMethod128;
        new ModuleMethod(frame5, 134, (Object) null, 0);
        lambda$Fn109 = moduleMethod129;
        new ModuleMethod(frame5, 135, (Object) null, 0);
        lambda$Fn110 = moduleMethod130;
        new ModuleMethod(frame5, 136, (Object) null, 0);
        lambda$Fn111 = moduleMethod131;
        new ModuleMethod(frame5, 137, (Object) null, 0);
        lambda$Fn112 = moduleMethod132;
        new ModuleMethod(frame5, 138, (Object) null, 0);
        lambda$Fn113 = moduleMethod133;
        new ModuleMethod(frame5, 139, (Object) null, 0);
        lambda$Fn114 = moduleMethod134;
        new ModuleMethod(frame5, 140, (Object) null, 0);
        lambda$Fn115 = moduleMethod135;
        new ModuleMethod(frame5, 141, Lit374, 0);
        this.cancel2$Click = moduleMethod136;
        new ModuleMethod(frame5, 142, (Object) null, 0);
        lambda$Fn116 = moduleMethod137;
        new ModuleMethod(frame5, 143, (Object) null, 0);
        lambda$Fn117 = moduleMethod138;
        new ModuleMethod(frame5, 144, (Object) null, 0);
        lambda$Fn118 = moduleMethod139;
        new ModuleMethod(frame5, 145, (Object) null, 0);
        lambda$Fn119 = moduleMethod140;
        new ModuleMethod(frame5, 146, (Object) null, 0);
        lambda$Fn120 = moduleMethod141;
        new ModuleMethod(frame5, 147, (Object) null, 0);
        lambda$Fn121 = moduleMethod142;
        new ModuleMethod(frame5, 148, (Object) null, 0);
        lambda$Fn122 = moduleMethod143;
        new ModuleMethod(frame5, 149, (Object) null, 0);
        lambda$Fn123 = moduleMethod144;
        new ModuleMethod(frame5, 150, Lit397, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.IntroWeb_Viewer$ProgressChanged = moduleMethod145;
        new ModuleMethod(frame5, 151, (Object) null, 0);
        lambda$Fn124 = moduleMethod146;
        new ModuleMethod(frame5, 152, (Object) null, 0);
        lambda$Fn125 = moduleMethod147;
        new ModuleMethod(frame5, 153, Lit404, 0);
        this.Vertical_Arrangement2$Click = moduleMethod148;
        new ModuleMethod(frame5, 154, (Object) null, 0);
        lambda$Fn126 = moduleMethod149;
        new ModuleMethod(frame5, 155, (Object) null, 0);
        lambda$Fn127 = moduleMethod150;
        new ModuleMethod(frame5, 156, Lit410, 0);
        this.Label11$Click = moduleMethod151;
        new ModuleMethod(frame5, 157, (Object) null, 0);
        lambda$Fn128 = moduleMethod152;
        new ModuleMethod(frame5, 158, (Object) null, 0);
        lambda$Fn129 = moduleMethod153;
        new ModuleMethod(frame5, 159, (Object) null, 0);
        lambda$Fn130 = moduleMethod154;
        new ModuleMethod(frame5, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, (Object) null, 0);
        lambda$Fn131 = moduleMethod155;
        new ModuleMethod(frame5, 161, (Object) null, 0);
        lambda$Fn132 = moduleMethod156;
        new ModuleMethod(frame5, 162, (Object) null, 0);
        lambda$Fn133 = moduleMethod157;
        new ModuleMethod(frame5, 163, (Object) null, 0);
        lambda$Fn134 = moduleMethod158;
        new ModuleMethod(frame5, 164, (Object) null, 0);
        lambda$Fn135 = moduleMethod159;
        new ModuleMethod(frame5, 165, (Object) null, 0);
        lambda$Fn136 = moduleMethod160;
        new ModuleMethod(frame5, 166, (Object) null, 0);
        lambda$Fn137 = moduleMethod161;
        new ModuleMethod(frame5, 167, (Object) null, 0);
        lambda$Fn138 = moduleMethod162;
        new ModuleMethod(frame5, 168, (Object) null, 0);
        lambda$Fn139 = moduleMethod163;
        new ModuleMethod(frame5, 169, (Object) null, 0);
        lambda$Fn140 = moduleMethod164;
        new ModuleMethod(frame5, 170, (Object) null, 0);
        lambda$Fn141 = moduleMethod165;
        new ModuleMethod(frame5, 171, (Object) null, 0);
        lambda$Fn142 = moduleMethod166;
        new ModuleMethod(frame5, 172, (Object) null, 0);
        lambda$Fn143 = moduleMethod167;
        new ModuleMethod(frame5, 173, (Object) null, 0);
        lambda$Fn144 = moduleMethod168;
        new ModuleMethod(frame5, 174, (Object) null, 0);
        lambda$Fn145 = moduleMethod169;
        new ModuleMethod(frame5, 175, (Object) null, 0);
        lambda$Fn146 = moduleMethod170;
        new ModuleMethod(frame5, 176, (Object) null, 0);
        lambda$Fn147 = moduleMethod171;
        new ModuleMethod(frame5, 177, (Object) null, 0);
        lambda$Fn148 = moduleMethod172;
        new ModuleMethod(frame5, 178, (Object) null, 0);
        lambda$Fn149 = moduleMethod173;
        new ModuleMethod(frame5, 179, (Object) null, 0);
        lambda$Fn150 = moduleMethod174;
        new ModuleMethod(frame5, 180, (Object) null, 0);
        lambda$Fn151 = moduleMethod175;
        new ModuleMethod(frame5, 181, (Object) null, 0);
        lambda$Fn152 = moduleMethod176;
        new ModuleMethod(frame5, 182, (Object) null, 0);
        lambda$Fn153 = moduleMethod177;
        new ModuleMethod(frame5, 183, (Object) null, 0);
        lambda$Fn154 = moduleMethod178;
        new ModuleMethod(frame5, 184, (Object) null, 0);
        lambda$Fn155 = moduleMethod179;
        new ModuleMethod(frame5, 185, (Object) null, 0);
        lambda$Fn156 = moduleMethod180;
        new ModuleMethod(frame5, 186, (Object) null, 0);
        lambda$Fn157 = moduleMethod181;
        new ModuleMethod(frame5, 187, (Object) null, 0);
        lambda$Fn158 = moduleMethod182;
        new ModuleMethod(frame5, 188, (Object) null, 0);
        lambda$Fn159 = moduleMethod183;
        new ModuleMethod(frame5, FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG, (Object) null, 0);
        lambda$Fn160 = moduleMethod184;
        new ModuleMethod(frame5, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, (Object) null, 0);
        lambda$Fn161 = moduleMethod185;
        new ModuleMethod(frame5, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, (Object) null, 0);
        lambda$Fn162 = moduleMethod186;
        new ModuleMethod(frame5, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, (Object) null, 0);
        lambda$Fn163 = moduleMethod187;
        new ModuleMethod(frame5, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP, (Object) null, 0);
        lambda$Fn164 = moduleMethod188;
        new ModuleMethod(frame5, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, (Object) null, 0);
        lambda$Fn165 = moduleMethod189;
        new ModuleMethod(frame5, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, Lit488, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.EmWebView$ProgressChanged = moduleMethod190;
        new ModuleMethod(frame5, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, (Object) null, 0);
        lambda$Fn166 = moduleMethod191;
        new ModuleMethod(frame5, 197, (Object) null, 0);
        lambda$Fn167 = moduleMethod192;
        new ModuleMethod(frame5, 198, (Object) null, 0);
        lambda$Fn168 = moduleMethod193;
        new ModuleMethod(frame5, 199, (Object) null, 0);
        lambda$Fn169 = moduleMethod194;
        new ModuleMethod(frame5, 200, (Object) null, 0);
        lambda$Fn170 = moduleMethod195;
        new ModuleMethod(frame5, 201, (Object) null, 0);
        lambda$Fn171 = moduleMethod196;
        new ModuleMethod(frame5, 202, (Object) null, 0);
        lambda$Fn172 = moduleMethod197;
        new ModuleMethod(frame5, HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, (Object) null, 0);
        lambda$Fn173 = moduleMethod198;
        new ModuleMethod(frame5, HttpStatus.SC_NO_CONTENT, (Object) null, 0);
        lambda$Fn174 = moduleMethod199;
        new ModuleMethod(frame5, HttpStatus.SC_RESET_CONTENT, (Object) null, 0);
        lambda$Fn175 = moduleMethod200;
        new ModuleMethod(frame5, HttpStatus.SC_PARTIAL_CONTENT, (Object) null, 0);
        lambda$Fn176 = moduleMethod201;
        new ModuleMethod(frame5, HttpStatus.SC_MULTI_STATUS, (Object) null, 0);
        lambda$Fn177 = moduleMethod202;
        new ModuleMethod(frame5, 208, (Object) null, 0);
        lambda$Fn178 = moduleMethod203;
        new ModuleMethod(frame5, 209, (Object) null, 0);
        lambda$Fn179 = moduleMethod204;
        new ModuleMethod(frame5, 210, (Object) null, 0);
        lambda$Fn180 = moduleMethod205;
        new ModuleMethod(frame5, 211, (Object) null, 0);
        lambda$Fn181 = moduleMethod206;
        new ModuleMethod(frame5, 212, Lit522, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.InWebView$ProgressChanged = moduleMethod207;
        new ModuleMethod(frame5, 213, (Object) null, 0);
        lambda$Fn182 = moduleMethod208;
        new ModuleMethod(frame5, 214, (Object) null, 0);
        lambda$Fn183 = moduleMethod209;
        new ModuleMethod(frame5, 215, (Object) null, 0);
        lambda$Fn184 = moduleMethod210;
        new ModuleMethod(frame5, 216, (Object) null, 0);
        lambda$Fn185 = moduleMethod211;
        new ModuleMethod(frame5, 217, (Object) null, 0);
        lambda$Fn186 = moduleMethod212;
        new ModuleMethod(frame5, 218, (Object) null, 0);
        lambda$Fn187 = moduleMethod213;
        new ModuleMethod(frame5, 219, (Object) null, 0);
        lambda$Fn188 = moduleMethod214;
        new ModuleMethod(frame5, 220, (Object) null, 0);
        lambda$Fn189 = moduleMethod215;
        new ModuleMethod(frame5, 221, (Object) null, 0);
        lambda$Fn190 = moduleMethod216;
        new ModuleMethod(frame5, 222, (Object) null, 0);
        lambda$Fn191 = moduleMethod217;
        new ModuleMethod(frame5, 223, (Object) null, 0);
        lambda$Fn192 = moduleMethod218;
        new ModuleMethod(frame5, YaVersion.YOUNG_ANDROID_VERSION, (Object) null, 0);
        lambda$Fn193 = moduleMethod219;
        new ModuleMethod(frame5, 225, (Object) null, 0);
        lambda$Fn194 = moduleMethod220;
        new ModuleMethod(frame5, 226, (Object) null, 0);
        lambda$Fn195 = moduleMethod221;
        new ModuleMethod(frame5, 227, (Object) null, 0);
        lambda$Fn196 = moduleMethod222;
        new ModuleMethod(frame5, 228, (Object) null, 0);
        lambda$Fn197 = moduleMethod223;
        new ModuleMethod(frame5, 229, Lit556, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.NeWebView$ProgressChanged = moduleMethod224;
        new ModuleMethod(frame5, 230, (Object) null, 0);
        lambda$Fn198 = moduleMethod225;
        new ModuleMethod(frame5, 231, (Object) null, 0);
        lambda$Fn199 = moduleMethod226;
        new ModuleMethod(frame5, 232, (Object) null, 0);
        lambda$Fn200 = moduleMethod227;
        new ModuleMethod(frame5, 233, (Object) null, 0);
        lambda$Fn201 = moduleMethod228;
        new ModuleMethod(frame5, 234, (Object) null, 0);
        lambda$Fn202 = moduleMethod229;
        new ModuleMethod(frame5, 235, (Object) null, 0);
        lambda$Fn203 = moduleMethod230;
        new ModuleMethod(frame5, 236, Lit574, 0);
        this.Search$GotFocus = moduleMethod231;
        new ModuleMethod(frame5, 237, Lit578, 0);
        this.Search$OnTextChanged = moduleMethod232;
        new ModuleMethod(frame5, 238, (Object) null, 0);
        lambda$Fn204 = moduleMethod233;
        new ModuleMethod(frame5, 239, (Object) null, 0);
        lambda$Fn205 = moduleMethod234;
        new ModuleMethod(frame5, 240, (Object) null, 0);
        lambda$Fn206 = moduleMethod235;
        new ModuleMethod(frame5, LispEscapeFormat.ESCAPE_NORMAL, (Object) null, 0);
        lambda$Fn207 = moduleMethod236;
        new ModuleMethod(frame5, LispEscapeFormat.ESCAPE_ALL, (Object) null, 0);
        lambda$Fn208 = moduleMethod237;
        new ModuleMethod(frame5, 243, (Object) null, 0);
        lambda$Fn209 = moduleMethod238;
        new ModuleMethod(frame5, 244, (Object) null, 0);
        lambda$Fn210 = moduleMethod239;
        new ModuleMethod(frame5, 245, (Object) null, 0);
        lambda$Fn211 = moduleMethod240;
        new ModuleMethod(frame5, 246, Lit615, 0);
        this.scname_list$AfterPicking = moduleMethod241;
        new ModuleMethod(frame5, 247, (Object) null, 0);
        lambda$Fn212 = moduleMethod242;
        new ModuleMethod(frame5, ComponentConstants.LISTVIEW_PREFERRED_WIDTH, (Object) null, 0);
        lambda$Fn213 = moduleMethod243;
        new ModuleMethod(frame5, 249, Lit623, 0);
        this.down$Click = moduleMethod244;
        new ModuleMethod(frame5, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, (Object) null, 0);
        lambda$Fn214 = moduleMethod245;
        new ModuleMethod(frame5, Telnet.WILL, (Object) null, 0);
        lambda$Fn215 = moduleMethod246;
        new ModuleMethod(frame5, Telnet.WONT, (Object) null, 0);
        lambda$Fn216 = moduleMethod247;
        new ModuleMethod(frame5, Telnet.f261DO, (Object) null, 0);
        lambda$Fn217 = moduleMethod248;
        new ModuleMethod(frame5, Telnet.DONT, (Object) null, 0);
        lambda$Fn218 = moduleMethod249;
        new ModuleMethod(frame5, 255, (Object) null, 0);
        lambda$Fn219 = moduleMethod250;
        new ModuleMethod(frame5, 256, (Object) null, 0);
        lambda$Fn220 = moduleMethod251;
        new ModuleMethod(frame5, InputDeviceCompat.SOURCE_KEYBOARD, (Object) null, 0);
        lambda$Fn221 = moduleMethod252;
        new ModuleMethod(frame5, 258, (Object) null, 0);
        lambda$Fn222 = moduleMethod253;
        new ModuleMethod(frame5, 259, (Object) null, 0);
        lambda$Fn223 = moduleMethod254;
        new ModuleMethod(frame5, 260, (Object) null, 0);
        lambda$Fn224 = moduleMethod255;
        new ModuleMethod(frame5, 261, (Object) null, 0);
        lambda$Fn225 = moduleMethod256;
        new ModuleMethod(frame5, 262, Lit665, 12291);
        this.Gmap$OnMarkerClick = moduleMethod257;
        new ModuleMethod(frame5, 263, Lit669, 0);
        this.Gmap$MapIsReady = moduleMethod258;
        new ModuleMethod(frame5, 264, Lit673, 8194);
        this.Gmap$OnMapClick = moduleMethod259;
        new ModuleMethod(frame5, 265, (Object) null, 0);
        lambda$Fn226 = moduleMethod260;
        new ModuleMethod(frame5, 266, (Object) null, 0);
        lambda$Fn227 = moduleMethod261;
        new ModuleMethod(frame5, 267, (Object) null, 0);
        lambda$Fn228 = moduleMethod262;
        new ModuleMethod(frame5, 268, (Object) null, 0);
        lambda$Fn229 = moduleMethod263;
        new ModuleMethod(frame5, 269, (Object) null, 0);
        lambda$Fn230 = moduleMethod264;
        new ModuleMethod(frame5, 270, (Object) null, 0);
        lambda$Fn231 = moduleMethod265;
        new ModuleMethod(frame5, 271, Lit713, 0);
        this.flip$Click = moduleMethod266;
        new ModuleMethod(frame5, 272, (Object) null, 0);
        lambda$Fn232 = moduleMethod267;
        new ModuleMethod(frame5, 273, (Object) null, 0);
        lambda$Fn233 = moduleMethod268;
        new ModuleMethod(frame5, 274, (Object) null, 0);
        lambda$Fn234 = moduleMethod269;
        new ModuleMethod(frame5, 275, (Object) null, 0);
        lambda$Fn235 = moduleMethod270;
        new ModuleMethod(frame5, 276, Lit726, 0);
        this.yes$Changed = moduleMethod271;
        new ModuleMethod(frame5, 277, (Object) null, 0);
        lambda$Fn236 = moduleMethod272;
        new ModuleMethod(frame5, 278, (Object) null, 0);
        lambda$Fn237 = moduleMethod273;
        new ModuleMethod(frame5, 279, Lit735, 0);
        this.test$Changed = moduleMethod274;
        new ModuleMethod(frame5, 280, (Object) null, 0);
        lambda$Fn238 = moduleMethod275;
        new ModuleMethod(frame5, 281, (Object) null, 0);
        lambda$Fn239 = moduleMethod276;
        new ModuleMethod(frame5, 282, Lit743, 0);
        this.no$Changed = moduleMethod277;
        new ModuleMethod(frame5, 283, (Object) null, 0);
        lambda$Fn240 = moduleMethod278;
        new ModuleMethod(frame5, 284, (Object) null, 0);
        lambda$Fn241 = moduleMethod279;
        new ModuleMethod(frame5, 285, Lit751, 0);
        this.up$Changed = moduleMethod280;
        new ModuleMethod(frame5, 286, (Object) null, 0);
        lambda$Fn242 = moduleMethod281;
        new ModuleMethod(frame5, 287, (Object) null, 0);
        lambda$Fn243 = moduleMethod282;
        new ModuleMethod(frame5, 288, Lit759, 0);
        this.lock$Changed = moduleMethod283;
        new ModuleMethod(frame5, 289, (Object) null, 0);
        lambda$Fn244 = moduleMethod284;
        new ModuleMethod(frame5, 290, (Object) null, 0);
        lambda$Fn245 = moduleMethod285;
        new ModuleMethod(frame5, 291, (Object) null, 0);
        lambda$Fn246 = moduleMethod286;
        new ModuleMethod(frame5, 292, (Object) null, 0);
        lambda$Fn247 = moduleMethod287;
        new ModuleMethod(frame5, 293, (Object) null, 0);
        lambda$Fn248 = moduleMethod288;
        new ModuleMethod(frame5, 294, (Object) null, 0);
        lambda$Fn249 = moduleMethod289;
        new ModuleMethod(frame5, 295, (Object) null, 0);
        lambda$Fn250 = moduleMethod290;
        new ModuleMethod(frame5, 296, (Object) null, 0);
        lambda$Fn251 = moduleMethod291;
        new ModuleMethod(frame5, 297, (Object) null, 0);
        lambda$Fn252 = moduleMethod292;
        new ModuleMethod(frame5, 298, (Object) null, 0);
        lambda$Fn253 = moduleMethod293;
        new ModuleMethod(frame5, 299, Lit775, 0);
        this.HideMarkerPop$Click = moduleMethod294;
        new ModuleMethod(frame5, HttpStatus.SC_MULTIPLE_CHOICES, (Object) null, 0);
        lambda$Fn254 = moduleMethod295;
        new ModuleMethod(frame5, 301, (Object) null, 0);
        lambda$Fn255 = moduleMethod296;
        new ModuleMethod(frame5, 302, (Object) null, 0);
        lambda$Fn256 = moduleMethod297;
        new ModuleMethod(frame5, 303, (Object) null, 0);
        lambda$Fn257 = moduleMethod298;
        new ModuleMethod(frame5, 304, Lit784, 0);
        this.Phn_btn$Click = moduleMethod299;
        new ModuleMethod(frame5, 305, (Object) null, 0);
        lambda$Fn258 = moduleMethod300;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED, (Object) null, 0);
        lambda$Fn259 = moduleMethod301;
        new ModuleMethod(frame5, 307, (Object) null, 0);
        lambda$Fn260 = moduleMethod302;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED, (Object) null, 0);
        lambda$Fn261 = moduleMethod303;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED, (Object) null, 0);
        lambda$Fn262 = moduleMethod304;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED, (Object) null, 0);
        lambda$Fn263 = moduleMethod305;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED, Lit800, 0);
        this.scrolllist$Click = moduleMethod306;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED, (Object) null, 0);
        lambda$Fn264 = moduleMethod307;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED, (Object) null, 0);
        lambda$Fn265 = moduleMethod308;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_SEARCH_FAILED, Lit817, 8194);
        this.Tabs$ItemSelected = moduleMethod309;
        new ModuleMethod(frame5, ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH, (Object) null, 0);
        lambda$Fn266 = moduleMethod310;
        new ModuleMethod(frame5, 316, (Object) null, 0);
        lambda$Fn267 = moduleMethod311;
        new ModuleMethod(frame5, 317, (Object) null, 0);
        lambda$Fn268 = moduleMethod312;
        new ModuleMethod(frame5, 318, (Object) null, 0);
        lambda$Fn269 = moduleMethod313;
        new ModuleMethod(frame5, 319, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn272 = moduleMethod314;
        new ModuleMethod(frame5, ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION, Lit872, 8194);
        this.Firebase_Database1$GotValue = moduleMethod315;
        new ModuleMethod(frame5, 321, (Object) null, 0);
        lambda$Fn273 = moduleMethod316;
        new ModuleMethod(frame5, 322, (Object) null, 0);
        lambda$Fn274 = moduleMethod317;
        new ModuleMethod(frame5, 323, Lit882, 0);
        this.float_clk$Timer = moduleMethod318;
        new ModuleMethod(frame5, 324, (Object) null, 0);
        lambda$Fn275 = moduleMethod319;
        new ModuleMethod(frame5, 325, (Object) null, 0);
        lambda$Fn276 = moduleMethod320;
        new ModuleMethod(frame5, 326, Lit895, 0);
        this.float_ckl1$Timer = moduleMethod321;
        new ModuleMethod(frame5, 327, (Object) null, 0);
        lambda$Fn277 = moduleMethod322;
        new ModuleMethod(frame5, 328, (Object) null, 0);
        lambda$Fn278 = moduleMethod323;
        new ModuleMethod(frame5, 329, Lit910, 0);
        this.location_clk$Timer = moduleMethod324;
        new ModuleMethod(frame5, 330, (Object) null, 0);
        lambda$Fn279 = moduleMethod325;
        new ModuleMethod(frame5, 331, (Object) null, 0);
        lambda$Fn280 = moduleMethod326;
        new ModuleMethod(frame5, 332, (Object) null, 0);
        lambda$Fn281 = moduleMethod327;
        new ModuleMethod(frame5, 333, (Object) null, 0);
        lambda$Fn282 = moduleMethod328;
        new ModuleMethod(frame5, 334, Lit919, 0);
        this.keyboard_timer$Timer = moduleMethod329;
        new ModuleMethod(frame5, 335, (Object) null, 0);
        lambda$Fn283 = moduleMethod330;
        new ModuleMethod(frame5, 336, (Object) null, 0);
        lambda$Fn284 = moduleMethod331;
        new ModuleMethod(frame5, 337, (Object) null, 0);
        lambda$Fn285 = moduleMethod332;
        new ModuleMethod(frame5, 338, (Object) null, 0);
        lambda$Fn286 = moduleMethod333;
    }

    static Boolean lambda10() {
        return Boolean.TRUE;
    }

    static Boolean lambda3() {
        return Boolean.TRUE;
    }

    static Boolean lambda6() {
        return Boolean.TRUE;
    }

    static Boolean lambda7() {
        return Boolean.TRUE;
    }

    static Boolean lambda9() {
        return Boolean.TRUE;
    }

    public Object lookupInFormEnvironment(Symbol symbol) {
        return lookupInFormEnvironment(symbol, Boolean.FALSE);
    }

    public void run() {
        Throwable th;
        CallContext instance = CallContext.getInstance();
        Consumer consumer = instance.consumer;
        instance.consumer = VoidConsumer.instance;
        try {
            run(instance);
            th = null;
        } catch (Throwable th2) {
            th = th2;
        }
        ModuleBody.runCleanup(instance, th, consumer);
    }

    public final void run(CallContext $ctx) {
        Throwable th;
        String obj;
        Throwable th2;
        Object obj2;
        Consumer $result = $ctx.consumer;
        Object find = require.find("com.google.youngandroid.runtime");
        Object obj3 = find;
        try {
            ((Runnable) find).run();
            this.$Stdebug$Mnform$St = Boolean.FALSE;
            this.form$Mnenvironment = Environment.make(misc.symbol$To$String(Lit0));
            Object[] objArr = new Object[2];
            objArr[0] = misc.symbol$To$String(Lit0);
            Object[] objArr2 = objArr;
            objArr2[1] = "-global-vars";
            FString stringAppend = strings.stringAppend(objArr2);
            FString fString = stringAppend;
            if (stringAppend == null) {
                obj = null;
            } else {
                obj = fString.toString();
            }
            this.global$Mnvar$Mnenvironment = Environment.make(obj);
            Screen1 = null;
            this.form$Mnname$Mnsymbol = Lit0;
            this.events$Mnto$Mnregister = LList.Empty;
            this.components$Mnto$Mncreate = LList.Empty;
            this.global$Mnvars$Mnto$Mncreate = LList.Empty;
            this.form$Mndo$Mnafter$Mncreation = LList.Empty;
            Object find2 = require.find("com.google.youngandroid.runtime");
            Object obj4 = find2;
            try {
                ((Runnable) find2).run();
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit3, Boolean.TRUE), $result);
                } else {
                    addToGlobalVars(Lit3, lambda$Fn2);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit4, Lit5), $result);
                } else {
                    addToGlobalVars(Lit4, lambda$Fn3);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit6, Lit5), $result);
                } else {
                    addToGlobalVars(Lit6, lambda$Fn4);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit7, Boolean.TRUE), $result);
                } else {
                    addToGlobalVars(Lit7, lambda$Fn5);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit8, Boolean.TRUE), $result);
                } else {
                    addToGlobalVars(Lit8, lambda$Fn6);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit9, Lit5), $result);
                } else {
                    addToGlobalVars(Lit9, lambda$Fn7);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit10, Boolean.TRUE), $result);
                } else {
                    addToGlobalVars(Lit10, lambda$Fn8);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit11, Boolean.TRUE), $result);
                } else {
                    addToGlobalVars(Lit11, lambda$Fn9);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit12, Lit5), $result);
                } else {
                    addToGlobalVars(Lit12, lambda$Fn10);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit13, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list")), $result);
                } else {
                    addToGlobalVars(Lit13, lambda$Fn11);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit14, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list")), $result);
                } else {
                    addToGlobalVars(Lit14, lambda$Fn12);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit15, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list")), $result);
                } else {
                    addToGlobalVars(Lit15, lambda$Fn13);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit16, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list")), $result);
                } else {
                    addToGlobalVars(Lit16, lambda$Fn14);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit17, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list")), $result);
                } else {
                    addToGlobalVars(Lit17, lambda$Fn15);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit18, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list")), $result);
                } else {
                    addToGlobalVars(Lit18, lambda$Fn16);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit19, Lit5), $result);
                } else {
                    addToGlobalVars(Lit19, lambda$Fn17);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit20, Lit21), $result);
                } else {
                    addToGlobalVars(Lit20, lambda$Fn18);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    SimpleSymbol simpleSymbol = Lit22;
                    ModuleMethod moduleMethod = C1227runtime.make$Mnyail$Mnlist;
                    Pair list1 = LList.list1("350");
                    Pair chain4 = LList.chain4(list1, "210", "140", "280", "40");
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(simpleSymbol, C1227runtime.callYailPrimitive(moduleMethod, list1, Lit23, "make a list")), $result);
                } else {
                    addToGlobalVars(Lit22, lambda$Fn19);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit25, lambda$Fn20), $result);
                } else {
                    addToGlobalVars(Lit25, lambda$Fn24);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit34, Lit5), $result);
                } else {
                    addToGlobalVars(Lit34, lambda$Fn29);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit59, lambda$Fn30), $result);
                } else {
                    addToGlobalVars(Lit59, lambda$Fn37);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit144, "CovidEase 1.2107", Lit107);
                    Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit145, Lit146, Lit147);
                    Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit148, Lit149, Lit147);
                    Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit150, "6662989908279296", Lit107);
                    Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit151, "CovidEase", Lit107);
                    Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit152, "fade", Lit107);
                    Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit153, "logo1.png", Lit107);
                    Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit154, Lit155, Lit147);
                    Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit156, "zoom", Lit107);
                    Object andCoerceProperty$Ex10 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit157, "com.covidease.lab", Lit107);
                    Object andCoerceProperty$Ex11 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit158, Lit159, Lit147);
                    Object andCoerceProperty$Ex12 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit160, Lit161, Lit147);
                    Object andCoerceProperty$Ex13 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit162, "portrait", Lit107);
                    Object andCoerceProperty$Ex14 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit163, "Home", Lit107);
                    Object andCoerceProperty$Ex15 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit164, Boolean.FALSE, Lit48);
                    Values.writeValues(C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit165, "1.2107", Lit107), $result);
                } else {
                    new Promise(lambda$Fn45);
                    addToFormDoAfterCreation(obj2);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment = C1227runtime.addToCurrentFormEnvironment(Lit194, this.Screen1$Initialize);
                } else {
                    addToFormEnvironment(Lit194, this.Screen1$Initialize);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Screen1", "Initialize");
                } else {
                    addToEvents(Lit0, Lit195);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment2 = C1227runtime.addToCurrentFormEnvironment(Lit199, this.Screen1$KeyboardVisiblityChanged);
                } else {
                    addToFormEnvironment(Lit199, this.Screen1$KeyboardVisiblityChanged);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Screen1", "KeyboardVisiblityChanged");
                } else {
                    addToEvents(Lit0, Lit200);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment3 = C1227runtime.addToCurrentFormEnvironment(Lit221, this.Screen1$BackPressed);
                } else {
                    addToFormEnvironment(Lit221, this.Screen1$BackPressed);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Screen1", "BackPressed");
                } else {
                    addToEvents(Lit0, Lit222);
                }
                this.POPLayout = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit223, Lit168, lambda$Fn62), $result);
                } else {
                    addToComponents(Lit0, Lit231, Lit168, lambda$Fn63);
                }
                this.dialouge = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit168, Lit232, Lit233, lambda$Fn64), $result);
                } else {
                    addToComponents(Lit168, Lit237, Lit233, lambda$Fn65);
                }
                this.Horizontal_Arrangement4 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit233, Lit238, Lit239, lambda$Fn66), $result);
                } else {
                    addToComponents(Lit233, Lit240, Lit239, lambda$Fn67);
                }
                this.Label10 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit239, Lit241, Lit214, lambda$Fn68), $result);
                } else {
                    addToComponents(Lit239, Lit252, Lit214, lambda$Fn69);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment4 = C1227runtime.addToCurrentFormEnvironment(Lit266, this.Label10$Click);
                } else {
                    addToFormEnvironment(Lit266, this.Label10$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Label10", "Click");
                } else {
                    addToEvents(Lit214, Lit267);
                }
                this.f256At = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit233, Lit268, Lit269, lambda$Fn70), $result);
                } else {
                    addToComponents(Lit233, Lit272, Lit269, lambda$Fn71);
                }
                this.Space9 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit233, Lit273, Lit274, lambda$Fn72), $result);
                } else {
                    addToComponents(Lit233, Lit276, Lit274, lambda$Fn73);
                }
                this.POPtitle = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit233, Lit277, Lit213, lambda$Fn74), $result);
                } else {
                    addToComponents(Lit233, Lit280, Lit213, lambda$Fn75);
                }
                this.Space9_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit233, Lit281, Lit282, lambda$Fn76), $result);
                } else {
                    addToComponents(Lit233, Lit284, Lit282, lambda$Fn77);
                }
                this.Horizontal_Arrangement3 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit233, Lit285, Lit286, lambda$Fn78), $result);
                } else {
                    addToComponents(Lit233, Lit287, Lit286, lambda$Fn79);
                }
                this.Button1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit286, Lit288, Lit289, lambda$Fn80), $result);
                } else {
                    addToComponents(Lit286, Lit293, Lit289, lambda$Fn81);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment5 = C1227runtime.addToCurrentFormEnvironment(Lit295, this.Button1$Click);
                } else {
                    addToFormEnvironment(Lit295, this.Button1$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Button1", "Click");
                } else {
                    addToEvents(Lit289, Lit267);
                }
                this.POPbutton = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit286, Lit296, Lit253, lambda$Fn82), $result);
                } else {
                    addToComponents(Lit286, Lit298, Lit253, lambda$Fn83);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment6 = C1227runtime.addToCurrentFormEnvironment(Lit310, this.POPbutton$Click);
                } else {
                    addToFormEnvironment(Lit310, this.POPbutton$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "POPbutton", "Click");
                } else {
                    addToEvents(Lit253, Lit267);
                }
                this.Space9_copy_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit233, Lit311, Lit312, lambda$Fn84), $result);
                } else {
                    addToComponents(Lit233, Lit314, Lit312, lambda$Fn85);
                }
                this.About = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit168, Lit315, Lit294, lambda$Fn86), $result);
                } else {
                    addToComponents(Lit168, Lit317, Lit294, lambda$Fn87);
                }
                this.Label3 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit318, Lit319, lambda$Fn88), $result);
                } else {
                    addToComponents(Lit294, Lit321, Lit319, lambda$Fn89);
                }
                this.Label4 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit322, Lit323, lambda$Fn90), $result);
                } else {
                    addToComponents(Lit294, Lit325, Lit323, lambda$Fn91);
                }
                this.Label5 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit326, Lit327, lambda$Fn92), $result);
                } else {
                    addToComponents(Lit294, Lit329, Lit327, lambda$Fn93);
                }
                this.Image2_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit330, Lit331, lambda$Fn94), $result);
                } else {
                    addToComponents(Lit294, Lit333, Lit331, lambda$Fn95);
                }
                this.Label9 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit334, Lit335, lambda$Fn96), $result);
                } else {
                    addToComponents(Lit294, Lit338, Lit335, lambda$Fn97);
                }
                this.Label6 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit339, Lit340, lambda$Fn98), $result);
                } else {
                    addToComponents(Lit294, Lit342, Lit340, lambda$Fn99);
                }
                this.Image2 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit343, Lit344, lambda$Fn100), $result);
                } else {
                    addToComponents(Lit294, Lit346, Lit344, lambda$Fn101);
                }
                this.Label8_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit347, Lit348, lambda$Fn102), $result);
                } else {
                    addToComponents(Lit294, Lit350, Lit348, lambda$Fn103);
                }
                this.Label7_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit351, Lit352, lambda$Fn104), $result);
                } else {
                    addToComponents(Lit294, Lit354, Lit352, lambda$Fn105);
                }
                this.Image2_copy1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit355, Lit356, lambda$Fn106), $result);
                } else {
                    addToComponents(Lit294, Lit358, Lit356, lambda$Fn107);
                }
                this.Label8 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit359, Lit360, lambda$Fn108), $result);
                } else {
                    addToComponents(Lit294, Lit362, Lit360, lambda$Fn109);
                }
                this.Label7 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit363, Lit364, lambda$Fn110), $result);
                } else {
                    addToComponents(Lit294, Lit366, Lit364, lambda$Fn111);
                }
                this.Space11 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit367, Lit368, lambda$Fn112), $result);
                } else {
                    addToComponents(Lit294, Lit369, Lit368, lambda$Fn113);
                }
                this.cancel2 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit294, Lit370, Lit371, lambda$Fn114), $result);
                } else {
                    addToComponents(Lit294, Lit373, Lit371, lambda$Fn115);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment7 = C1227runtime.addToCurrentFormEnvironment(Lit374, this.cancel2$Click);
                } else {
                    addToFormEnvironment(Lit374, this.cancel2$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "cancel2", "Click");
                } else {
                    addToEvents(Lit371, Lit267);
                }
                this.f257Sp = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit168, Lit375, Lit376, lambda$Fn116), $result);
                } else {
                    addToComponents(Lit168, Lit377, Lit376, lambda$Fn117);
                }
                this.intro = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit378, Lit307, lambda$Fn118), $result);
                } else {
                    addToComponents(Lit0, Lit380, Lit307, lambda$Fn119);
                }
                this.intro_por = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit307, Lit381, Lit382, lambda$Fn120), $result);
                } else {
                    addToComponents(Lit307, Lit385, Lit382, lambda$Fn121);
                }
                this.IntroWeb_Viewer = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit307, Lit386, Lit187, lambda$Fn122), $result);
                } else {
                    addToComponents(Lit307, Lit390, Lit187, lambda$Fn123);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment8 = C1227runtime.addToCurrentFormEnvironment(Lit397, this.IntroWeb_Viewer$ProgressChanged);
                } else {
                    addToFormEnvironment(Lit397, this.IntroWeb_Viewer$ProgressChanged);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "IntroWeb_Viewer", "ProgressChanged");
                } else {
                    addToEvents(Lit187, Lit398);
                }
                this.Vertical_Arrangement2 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit307, Lit399, Lit400, lambda$Fn124), $result);
                } else {
                    addToComponents(Lit307, Lit402, Lit400, lambda$Fn125);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment9 = C1227runtime.addToCurrentFormEnvironment(Lit404, this.Vertical_Arrangement2$Click);
                } else {
                    addToFormEnvironment(Lit404, this.Vertical_Arrangement2$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Vertical_Arrangement2", "Click");
                } else {
                    addToEvents(Lit400, Lit267);
                }
                this.Label11 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit400, Lit405, Lit406, lambda$Fn126), $result);
                } else {
                    addToComponents(Lit400, Lit409, Lit406, lambda$Fn127);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment10 = C1227runtime.addToCurrentFormEnvironment(Lit410, this.Label11$Click);
                } else {
                    addToFormEnvironment(Lit410, this.Label11$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Label11", "Click");
                } else {
                    addToEvents(Lit406, Lit267);
                }
                this.Space12 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit307, Lit411, Lit412, lambda$Fn128), $result);
                } else {
                    addToComponents(Lit307, Lit414, Lit412, lambda$Fn129);
                }
                this.LoadPG = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit415, Lit261, lambda$Fn130), $result);
                } else {
                    addToComponents(Lit0, Lit417, Lit261, lambda$Fn131);
                }
                this.Image1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit261, Lit418, Lit419, lambda$Fn132), $result);
                } else {
                    addToComponents(Lit261, Lit422, Lit419, lambda$Fn133);
                }
                this.Space6 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit261, Lit423, Lit424, lambda$Fn134), $result);
                } else {
                    addToComponents(Lit261, Lit426, Lit424, lambda$Fn135);
                }
                this.CovidEase = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit261, Lit427, Lit428, lambda$Fn136), $result);
                } else {
                    addToComponents(Lit261, Lit431, Lit428, lambda$Fn137);
                }
                this.Space1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit261, Lit432, Lit433, lambda$Fn138), $result);
                } else {
                    addToComponents(Lit261, Lit435, Lit433, lambda$Fn139);
                }
                this.Circular_Progress1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit261, Lit436, Lit437, lambda$Fn140), $result);
                } else {
                    addToComponents(Lit261, Lit440, Lit437, lambda$Fn141);
                }
                this.Space6_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit261, Lit441, Lit442, lambda$Fn142), $result);
                } else {
                    addToComponents(Lit261, Lit444, Lit442, lambda$Fn143);
                }
                this.bar_prog = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit261, Lit445, Lit446, lambda$Fn144), $result);
                } else {
                    addToComponents(Lit261, Lit449, Lit446, lambda$Fn145);
                }
                this.progress = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit446, Lit450, Lit173, lambda$Fn146), $result);
                } else {
                    addToComponents(Lit446, Lit453, Lit173, lambda$Fn147);
                }
                this.MainPG = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit454, Lit262, lambda$Fn148), $result);
                } else {
                    addToComponents(Lit0, Lit455, Lit262, lambda$Fn149);
                }
                this.EmergencyPG = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit262, Lit456, Lit205, lambda$Fn150), $result);
                } else {
                    addToComponents(Lit262, Lit457, Lit205, lambda$Fn151);
                }
                this.em_por = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit205, Lit458, Lit459, lambda$Fn152), $result);
                } else {
                    addToComponents(Lit205, Lit462, Lit459, lambda$Fn153);
                }
                this.Horizontal_Arrangement2_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit459, Lit463, Lit464, lambda$Fn154), $result);
                } else {
                    addToComponents(Lit459, Lit466, Lit464, lambda$Fn155);
                }
                this.Circular_Progress4_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit464, Lit467, Lit468, lambda$Fn156), $result);
                } else {
                    addToComponents(Lit464, Lit470, Lit468, lambda$Fn157);
                }
                this.Label2_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit464, Lit471, Lit472, lambda$Fn158), $result);
                } else {
                    addToComponents(Lit464, Lit474, Lit472, lambda$Fn159);
                }
                this.Space8_copy = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit459, Lit475, Lit476, lambda$Fn160), $result);
                } else {
                    addToComponents(Lit459, Lit477, Lit476, lambda$Fn161);
                }
                this.em_load = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit459, Lit478, Lit479, lambda$Fn162), $result);
                } else {
                    addToComponents(Lit459, Lit481, Lit479, lambda$Fn163);
                }
                this.EmWebView = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit205, Lit482, Lit186, lambda$Fn164), $result);
                } else {
                    addToComponents(Lit205, Lit483, Lit186, lambda$Fn165);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment11 = C1227runtime.addToCurrentFormEnvironment(Lit488, this.EmWebView$ProgressChanged);
                } else {
                    addToFormEnvironment(Lit488, this.EmWebView$ProgressChanged);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "EmWebView", "ProgressChanged");
                } else {
                    addToEvents(Lit186, Lit398);
                }
                this.InfoPG = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit262, Lit489, Lit207, lambda$Fn166), $result);
                } else {
                    addToComponents(Lit262, Lit491, Lit207, lambda$Fn167);
                }
                this.info_por = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit207, Lit492, Lit493, lambda$Fn168), $result);
                } else {
                    addToComponents(Lit207, Lit496, Lit493, lambda$Fn169);
                }
                this.Horizontal_Arrangement2 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit493, Lit497, Lit498, lambda$Fn170), $result);
                } else {
                    addToComponents(Lit493, Lit500, Lit498, lambda$Fn171);
                }
                this.Circular_Progress4 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit498, Lit501, Lit502, lambda$Fn172), $result);
                } else {
                    addToComponents(Lit498, Lit504, Lit502, lambda$Fn173);
                }
                this.Label2 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit498, Lit505, Lit506, lambda$Fn174), $result);
                } else {
                    addToComponents(Lit498, Lit508, Lit506, lambda$Fn175);
                }
                this.Space8 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit493, Lit509, Lit510, lambda$Fn176), $result);
                } else {
                    addToComponents(Lit493, Lit511, Lit510, lambda$Fn177);
                }
                this.ifo_load = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit493, Lit512, Lit513, lambda$Fn178), $result);
                } else {
                    addToComponents(Lit493, Lit515, Lit513, lambda$Fn179);
                }
                this.InWebView = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit207, Lit516, Lit185, lambda$Fn180), $result);
                } else {
                    addToComponents(Lit207, Lit517, Lit185, lambda$Fn181);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment12 = C1227runtime.addToCurrentFormEnvironment(Lit522, this.InWebView$ProgressChanged);
                } else {
                    addToFormEnvironment(Lit522, this.InWebView$ProgressChanged);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "InWebView", "ProgressChanged");
                } else {
                    addToEvents(Lit185, Lit398);
                }
                this.NewsPG = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit262, Lit523, Lit201, lambda$Fn182), $result);
                } else {
                    addToComponents(Lit262, Lit525, Lit201, lambda$Fn183);
                }
                this.news_pro = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit201, Lit526, Lit527, lambda$Fn184), $result);
                } else {
                    addToComponents(Lit201, Lit530, Lit527, lambda$Fn185);
                }
                this.Horizontal_Arrangement1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit527, Lit531, Lit532, lambda$Fn186), $result);
                } else {
                    addToComponents(Lit527, Lit534, Lit532, lambda$Fn187);
                }
                this.Circular_Progress3 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit532, Lit535, Lit536, lambda$Fn188), $result);
                } else {
                    addToComponents(Lit532, Lit538, Lit536, lambda$Fn189);
                }
                this.Label1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit532, Lit539, Lit540, lambda$Fn190), $result);
                } else {
                    addToComponents(Lit532, Lit542, Lit540, lambda$Fn191);
                }
                this.Space7 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit527, Lit543, Lit544, lambda$Fn192), $result);
                } else {
                    addToComponents(Lit527, Lit545, Lit544, lambda$Fn193);
                }
                this.news_load = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit527, Lit546, Lit547, lambda$Fn194), $result);
                } else {
                    addToComponents(Lit527, Lit549, Lit547, lambda$Fn195);
                }
                this.NeWebView = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit201, Lit550, Lit183, lambda$Fn196), $result);
                } else {
                    addToComponents(Lit201, Lit551, Lit183, lambda$Fn197);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment13 = C1227runtime.addToCurrentFormEnvironment(Lit556, this.NeWebView$ProgressChanged);
                } else {
                    addToFormEnvironment(Lit556, this.NeWebView$ProgressChanged);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "NeWebView", "ProgressChanged");
                } else {
                    addToEvents(Lit183, Lit398);
                }
                this.HomePG = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit262, Lit557, Lit209, lambda$Fn198), $result);
                } else {
                    addToComponents(Lit262, Lit558, Lit209, lambda$Fn199);
                }
                this.SearchBar = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit209, Lit559, Lit560, lambda$Fn200), $result);
                } else {
                    addToComponents(Lit209, Lit561, Lit560, lambda$Fn201);
                }
                this.Search = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit560, Lit562, Lit60, lambda$Fn202), $result);
                } else {
                    addToComponents(Lit560, Lit567, Lit60, lambda$Fn203);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment14 = C1227runtime.addToCurrentFormEnvironment(Lit574, this.Search$GotFocus);
                } else {
                    addToFormEnvironment(Lit574, this.Search$GotFocus);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Search", "GotFocus");
                } else {
                    addToEvents(Lit60, Lit575);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment15 = C1227runtime.addToCurrentFormEnvironment(Lit578, this.Search$OnTextChanged);
                } else {
                    addToFormEnvironment(Lit578, this.Search$OnTextChanged);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Search", "OnTextChanged");
                } else {
                    addToEvents(Lit60, Lit579);
                }
                this.SCquPOP = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit209, Lit580, Lit98, lambda$Fn204), $result);
                } else {
                    addToComponents(Lit209, Lit582, Lit98, lambda$Fn205);
                }
                this.Vertical_Arrangement1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit98, Lit583, Lit584, lambda$Fn206), $result);
                } else {
                    addToComponents(Lit98, Lit585, Lit584, lambda$Fn207);
                }
                this.scname_div = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit584, Lit586, Lit95, lambda$Fn208), $result);
                } else {
                    addToComponents(Lit584, Lit588, Lit95, lambda$Fn209);
                }
                this.scname_list = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit95, Lit589, Lit101, lambda$Fn210), $result);
                } else {
                    addToComponents(Lit95, Lit598, Lit101, lambda$Fn211);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment16 = C1227runtime.addToCurrentFormEnvironment(Lit615, this.scname_list$AfterPicking);
                } else {
                    addToFormEnvironment(Lit615, this.scname_list$AfterPicking);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "scname_list", "AfterPicking");
                } else {
                    addToEvents(Lit101, Lit616);
                }
                this.down = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit584, Lit617, Lit97, lambda$Fn212), $result);
                } else {
                    addToComponents(Lit584, Lit620, Lit97, lambda$Fn213);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment17 = C1227runtime.addToCurrentFormEnvironment(Lit623, this.down$Click);
                } else {
                    addToFormEnvironment(Lit623, this.down$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "down", "Click");
                } else {
                    addToEvents(Lit97, Lit267);
                }
                this.SearchQury = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit584, Lit624, Lit96, lambda$Fn214), $result);
                } else {
                    addToComponents(Lit584, Lit626, Lit96, lambda$Fn215);
                }
                this.null_loader = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit209, Lit627, Lit571, lambda$Fn216), $result);
                } else {
                    addToComponents(Lit209, Lit628, Lit571, lambda$Fn217);
                }
                this.Loading = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit209, Lit629, Lit46, lambda$Fn218), $result);
                } else {
                    addToComponents(Lit209, Lit630, Lit46, lambda$Fn219);
                }
                this.Circular_Progress2 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit46, Lit631, Lit632, lambda$Fn220), $result);
                } else {
                    addToComponents(Lit46, Lit634, Lit632, lambda$Fn221);
                }
                this.MapContainer = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit209, Lit635, Lit636, lambda$Fn222), $result);
                } else {
                    addToComponents(Lit209, Lit637, Lit636, lambda$Fn223);
                }
                this.Gmap = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit636, Lit638, Lit26, lambda$Fn224), $result);
                } else {
                    addToComponents(Lit636, Lit641, Lit26, lambda$Fn225);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment18 = C1227runtime.addToCurrentFormEnvironment(Lit665, this.Gmap$OnMarkerClick);
                } else {
                    addToFormEnvironment(Lit665, this.Gmap$OnMarkerClick);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Gmap", "OnMarkerClick");
                } else {
                    addToEvents(Lit26, Lit666);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment19 = C1227runtime.addToCurrentFormEnvironment(Lit669, this.Gmap$MapIsReady);
                } else {
                    addToFormEnvironment(Lit669, this.Gmap$MapIsReady);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Gmap", "MapIsReady");
                } else {
                    addToEvents(Lit26, Lit670);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment20 = C1227runtime.addToCurrentFormEnvironment(Lit673, this.Gmap$OnMapClick);
                } else {
                    addToFormEnvironment(Lit673, this.Gmap$OnMapClick);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Gmap", "OnMapClick");
                } else {
                    addToEvents(Lit26, Lit674);
                }
                this.Filter = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit636, Lit675, Lit676, lambda$Fn226), $result);
                } else {
                    addToComponents(Lit636, Lit680, Lit676, lambda$Fn227);
                }
                this.filter_txt = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit676, Lit681, Lit682, lambda$Fn228), $result);
                } else {
                    addToComponents(Lit676, Lit684, Lit682, lambda$Fn229);
                }
                this.flip = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit682, Lit685, Lit569, lambda$Fn230), $result);
                } else {
                    addToComponents(Lit682, Lit691, Lit569, lambda$Fn231);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment21 = C1227runtime.addToCurrentFormEnvironment(Lit713, this.flip$Click);
                } else {
                    addToFormEnvironment(Lit713, this.flip$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "flip", "Click");
                } else {
                    addToEvents(Lit569, Lit267);
                }
                this.Details = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit676, Lit714, Lit697, lambda$Fn232), $result);
                } else {
                    addToComponents(Lit676, Lit716, Lit697, lambda$Fn233);
                }
                this.yes = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit697, Lit717, Lit718, lambda$Fn234), $result);
                } else {
                    addToComponents(Lit697, Lit723, Lit718, lambda$Fn235);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment22 = C1227runtime.addToCurrentFormEnvironment(Lit726, this.yes$Changed);
                } else {
                    addToFormEnvironment(Lit726, this.yes$Changed);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "yes", "Changed");
                } else {
                    addToEvents(Lit718, Lit727);
                }
                this.test = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit697, Lit728, Lit729, lambda$Fn236), $result);
                } else {
                    addToComponents(Lit697, Lit732, Lit729, lambda$Fn237);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment23 = C1227runtime.addToCurrentFormEnvironment(Lit735, this.test$Changed);
                } else {
                    addToFormEnvironment(Lit735, this.test$Changed);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "test", "Changed");
                } else {
                    addToEvents(Lit729, Lit727);
                }
                this.f258no = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit697, Lit736, Lit737, lambda$Fn238), $result);
                } else {
                    addToComponents(Lit697, Lit740, Lit737, lambda$Fn239);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment24 = C1227runtime.addToCurrentFormEnvironment(Lit743, this.no$Changed);
                } else {
                    addToFormEnvironment(Lit743, this.no$Changed);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "no", "Changed");
                } else {
                    addToEvents(Lit737, Lit727);
                }
                this.f259up = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit697, Lit744, Lit745, lambda$Fn240), $result);
                } else {
                    addToComponents(Lit697, Lit748, Lit745, lambda$Fn241);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment25 = C1227runtime.addToCurrentFormEnvironment(Lit751, this.up$Changed);
                } else {
                    addToFormEnvironment(Lit751, this.up$Changed);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "up", "Changed");
                } else {
                    addToEvents(Lit745, Lit727);
                }
                this.lock = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit697, Lit752, Lit753, lambda$Fn242), $result);
                } else {
                    addToComponents(Lit697, Lit756, Lit753, lambda$Fn243);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment26 = C1227runtime.addToCurrentFormEnvironment(Lit759, this.lock$Changed);
                } else {
                    addToFormEnvironment(Lit759, this.lock$Changed);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "lock", "Changed");
                } else {
                    addToEvents(Lit753, Lit727);
                }
                this.Space10 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit676, Lit760, Lit761, lambda$Fn244), $result);
                } else {
                    addToComponents(Lit676, Lit762, Lit761, lambda$Fn245);
                }
                this.end = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit676, Lit763, Lit701, lambda$Fn246), $result);
                } else {
                    addToComponents(Lit676, Lit764, Lit701, lambda$Fn247);
                }
                this.Text_Box1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit636, Lit765, Lit210, lambda$Fn248), $result);
                } else {
                    addToComponents(Lit636, Lit766, Lit210, lambda$Fn249);
                }
                this.MarkerPOP = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit209, Lit767, Lit212, lambda$Fn250), $result);
                } else {
                    addToComponents(Lit209, Lit770, Lit212, lambda$Fn251);
                }
                this.HideMarkerPop = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit212, Lit771, Lit772, lambda$Fn252), $result);
                } else {
                    addToComponents(Lit212, Lit774, Lit772, lambda$Fn253);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment27 = C1227runtime.addToCurrentFormEnvironment(Lit775, this.HideMarkerPop$Click);
                } else {
                    addToFormEnvironment(Lit775, this.HideMarkerPop$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "HideMarkerPop", "Click");
                } else {
                    addToEvents(Lit772, Lit267);
                }
                this.MarkerPOPinside = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit212, Lit776, Lit777, lambda$Fn254), $result);
                } else {
                    addToComponents(Lit212, Lit779, Lit777, lambda$Fn255);
                }
                this.Phn_btn = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit777, Lit780, Lit650, lambda$Fn256), $result);
                } else {
                    addToComponents(Lit777, Lit783, Lit650, lambda$Fn257);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment28 = C1227runtime.addToCurrentFormEnvironment(Lit784, this.Phn_btn$Click);
                } else {
                    addToFormEnvironment(Lit784, this.Phn_btn$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Phn_btn", "Click");
                } else {
                    addToEvents(Lit650, Lit267);
                }
                this.Space4 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit777, Lit785, Lit786, lambda$Fn258), $result);
                } else {
                    addToComponents(Lit777, Lit788, Lit786, lambda$Fn259);
                }
                this.Details_LIST = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit777, Lit789, Lit659, lambda$Fn260), $result);
                } else {
                    addToComponents(Lit777, Lit794, Lit659, lambda$Fn261);
                }
                this.scrolllist = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit777, Lit795, Lit796, lambda$Fn262), $result);
                } else {
                    addToComponents(Lit777, Lit798, Lit796, lambda$Fn263);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment29 = C1227runtime.addToCurrentFormEnvironment(Lit800, this.scrolllist$Click);
                } else {
                    addToFormEnvironment(Lit800, this.scrolllist$Click);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "scrolllist", "Click");
                } else {
                    addToEvents(Lit796, Lit267);
                }
                this.Tabs = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit262, Lit801, Lit176, lambda$Fn264), $result);
                } else {
                    addToComponents(Lit262, Lit807, Lit176, lambda$Fn265);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment30 = C1227runtime.addToCurrentFormEnvironment(Lit817, this.Tabs$ItemSelected);
                } else {
                    addToFormEnvironment(Lit817, this.Tabs$ItemSelected);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Tabs", "ItemSelected");
                } else {
                    addToEvents(Lit176, Lit818);
                }
                this.DialoguePOP = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit819, Lit166, lambda$Fn266), $result);
                } else {
                    addToComponents(Lit0, Lit823, Lit166, lambda$Fn267);
                }
                this.Internet = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit824, Lit170, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit825, Lit170, Boolean.FALSE);
                }
                this.Animation = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit826, Lit827, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit828, Lit827, Boolean.FALSE);
                }
                this.JSONTools1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit829, Lit31, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit830, Lit31, Boolean.FALSE);
                }
                this.Firebase_Database1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit831, Lit189, lambda$Fn268), $result);
                } else {
                    addToComponents(Lit0, Lit836, Lit189, lambda$Fn269);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment31 = C1227runtime.addToCurrentFormEnvironment(Lit872, this.Firebase_Database1$GotValue);
                } else {
                    addToFormEnvironment(Lit872, this.Firebase_Database1$GotValue);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "Firebase_Database1", "GotValue");
                } else {
                    addToEvents(Lit189, Lit873);
                }
                this.webIcon = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit874, Lit651, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit875, Lit651, Boolean.FALSE);
                }
                this.float_clk = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit876, Lit263, lambda$Fn273), $result);
                } else {
                    addToComponents(Lit0, Lit879, Lit263, lambda$Fn274);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment32 = C1227runtime.addToCurrentFormEnvironment(Lit882, this.float_clk$Timer);
                } else {
                    addToFormEnvironment(Lit882, this.float_clk$Timer);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "float_clk", "Timer");
                } else {
                    addToEvents(Lit263, Lit883);
                }
                this.Floater = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit884, Lit693, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit885, Lit693, Boolean.FALSE);
                }
                this.float_ckl1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit886, Lit881, lambda$Fn275), $result);
                } else {
                    addToComponents(Lit0, Lit887, Lit881, lambda$Fn276);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment33 = C1227runtime.addToCurrentFormEnvironment(Lit895, this.float_ckl1$Timer);
                } else {
                    addToFormEnvironment(Lit895, this.float_ckl1$Timer);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "float_ckl1", "Timer");
                } else {
                    addToEvents(Lit881, Lit883);
                }
                this.location_clk = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit896, Lit264, lambda$Fn277), $result);
                } else {
                    addToComponents(Lit0, Lit897, Lit264, lambda$Fn278);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment34 = C1227runtime.addToCurrentFormEnvironment(Lit910, this.location_clk$Timer);
                } else {
                    addToFormEnvironment(Lit910, this.location_clk$Timer);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "location_clk", "Timer");
                } else {
                    addToEvents(Lit264, Lit883);
                }
                this.Location_Sensor1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit911, Lit903, lambda$Fn279), $result);
                } else {
                    addToComponents(Lit0, Lit914, Lit903, lambda$Fn280);
                }
                this.Package_Utilities1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit915, Lit898, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit916, Lit898, Boolean.FALSE);
                }
                this.keyboard_timer = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit917, Lit197, lambda$Fn281), $result);
                } else {
                    addToComponents(Lit0, Lit918, Lit197, lambda$Fn282);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Object addToCurrentFormEnvironment35 = C1227runtime.addToCurrentFormEnvironment(Lit919, this.keyboard_timer$Timer);
                } else {
                    addToFormEnvironment(Lit919, this.keyboard_timer$Timer);
                }
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C1227runtime.$Stthis$Mnform$St, "keyboard_timer", "Timer");
                } else {
                    addToEvents(Lit197, Lit883);
                }
                this.Activity_Starter1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit920, Lit303, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit921, Lit303, Boolean.FALSE);
                }
                this.LC_CLK = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit922, Lit923, lambda$Fn283), $result);
                } else {
                    addToComponents(Lit0, Lit925, Lit923, lambda$Fn284);
                }
                this.Tiny_DB1 = null;
                if (C1227runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C1227runtime.addComponentWithinRepl(Lit0, Lit926, Lit861, lambda$Fn285), $result);
                } else {
                    addToComponents(Lit0, Lit927, Lit861, lambda$Fn286);
                }
                C1227runtime.initRuntime();
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "java.lang.Runnable.run()", 1, obj4);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "java.lang.Runnable.run()", 1, obj3);
            throw th4;
        }
    }

    static IntNum lambda4() {
        return Lit5;
    }

    static IntNum lambda5() {
        return Lit5;
    }

    static IntNum lambda8() {
        return Lit5;
    }

    static IntNum lambda11() {
        return Lit5;
    }

    static Object lambda12() {
        return C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list");
    }

    static Object lambda13() {
        return C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list");
    }

    static Object lambda14() {
        return C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list");
    }

    static Object lambda15() {
        return C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list");
    }

    static Object lambda16() {
        return C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list");
    }

    static Object lambda17() {
        return C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list");
    }

    static IntNum lambda18() {
        return Lit5;
    }

    static IntNum lambda19() {
        return Lit21;
    }

    static Object lambda20() {
        ModuleMethod moduleMethod = C1227runtime.make$Mnyail$Mnlist;
        Pair list1 = LList.list1("350");
        Pair chain4 = LList.chain4(list1, "210", "140", "280", "40");
        return C1227runtime.callYailPrimitive(moduleMethod, list1, Lit24, "make a list");
    }

    static Object lambda21() {
        ModuleMethod moduleMethod = proc$Fn21;
        Object yailForEach = C1227runtime.yailForEach(proc$Fn21, C1227runtime.callComponentMethod(Lit26, Lit30, LList.Empty, LList.Empty));
        Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit18, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list"));
        Procedure proc = proc$Fn22;
        Object yailForEach2 = C1227runtime.yailForEach(proc$Fn22, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        Object addGlobalVarToCurrentFormEnvironment2 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit34, Lit5);
        Object addGlobalVarToCurrentFormEnvironment3 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit7, Boolean.TRUE);
        Procedure proc2 = proc$Fn23;
        Object yailForEach3 = C1227runtime.yailForEach(proc$Fn23, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        return C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.FALSE, Lit48);
    }

    public static Object lambda22proc(Object obj) {
        Object obj2;
        Object $item = obj;
        SimpleSymbol simpleSymbol = Lit26;
        SimpleSymbol simpleSymbol2 = Lit27;
        if ($item instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit28);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj2 = $item;
        }
        return C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(obj2), Lit29);
    }

    public static Object lambda23proc(Object obj) {
        Object obj2;
        Object $item = obj;
        SimpleSymbol simpleSymbol = Lit31;
        SimpleSymbol simpleSymbol2 = Lit32;
        if ($item instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit28);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj2 = $item;
        }
        Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(obj2), Lit33);
        Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit34, C1227runtime.callYailPrimitive(AddOp.$Pl, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit34, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit35), Lit36, "+"));
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnmember$Qu, LList.list2(C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2(PropertyTypeConstants.PROPERTY_TYPE_COLOR, Lit35), Lit38), C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit39, "is in list?") != Boolean.FALSE ? C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit34, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit40, "add items to list") : Values.empty;
    }

    public static Object lambda24proc(Object obj) {
        Object obj2;
        Object $item;
        Object $item2 = obj;
        SimpleSymbol simpleSymbol = Lit26;
        SimpleSymbol simpleSymbol2 = Lit41;
        ModuleMethod moduleMethod = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        if ($item2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit28);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj2 = $item2;
        }
        Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(C1227runtime.callYailPrimitive(moduleMethod, LList.list2(lookupGlobalVarInCurrentFormEnvironment, obj2), Lit42, "select list item")), Lit43);
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit7, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Boolean.TRUE), Lit44, "=") != Boolean.FALSE) {
            Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit9, C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.callComponentMethod(Lit26, Lit30, LList.Empty, LList.Empty), Lit35), Lit45, "select list item"));
            $item = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit7, Boolean.FALSE);
        } else {
            $item = Values.empty;
        }
        return $item;
    }

    static Procedure lambda25() {
        return lambda$Fn25;
    }

    static Object lambda26() {
        ModuleMethod moduleMethod = proc$Fn26;
        Object yailForEach = C1227runtime.yailForEach(proc$Fn26, C1227runtime.callComponentMethod(Lit26, Lit30, LList.Empty, LList.Empty));
        Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit18, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list"));
        Procedure proc = proc$Fn27;
        Object yailForEach2 = C1227runtime.yailForEach(proc$Fn27, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        Object addGlobalVarToCurrentFormEnvironment2 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit34, Lit5);
        Object addGlobalVarToCurrentFormEnvironment3 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit7, Boolean.TRUE);
        Procedure proc2 = proc$Fn28;
        Object yailForEach3 = C1227runtime.yailForEach(proc$Fn28, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        return C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.FALSE, Lit48);
    }

    public static Object lambda27proc(Object obj) {
        Object obj2;
        Object $item = obj;
        SimpleSymbol simpleSymbol = Lit26;
        SimpleSymbol simpleSymbol2 = Lit27;
        if ($item instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit28);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj2 = $item;
        }
        return C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(obj2), Lit49);
    }

    public static Object lambda28proc(Object obj) {
        Object obj2;
        Object $item = obj;
        SimpleSymbol simpleSymbol = Lit31;
        SimpleSymbol simpleSymbol2 = Lit32;
        if ($item instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit28);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj2 = $item;
        }
        Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(obj2), Lit50);
        Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit34, C1227runtime.callYailPrimitive(AddOp.$Pl, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit34, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit35), Lit51, "+"));
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnmember$Qu, LList.list2(C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2(PropertyTypeConstants.PROPERTY_TYPE_COLOR, Lit35), Lit52), C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit53, "is in list?") != Boolean.FALSE ? C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit34, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit54, "add items to list") : Values.empty;
    }

    public static Object lambda29proc(Object obj) {
        Object obj2;
        Object $item;
        Object $item2 = obj;
        SimpleSymbol simpleSymbol = Lit26;
        SimpleSymbol simpleSymbol2 = Lit41;
        ModuleMethod moduleMethod = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        if ($item2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit28);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj2 = $item2;
        }
        Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(C1227runtime.callYailPrimitive(moduleMethod, LList.list2(lookupGlobalVarInCurrentFormEnvironment, obj2), Lit55, "select list item")), Lit56);
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit7, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Boolean.TRUE), Lit57, "=") != Boolean.FALSE) {
            Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit9, C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.callComponentMethod(Lit26, Lit30, LList.Empty, LList.Empty), Lit35), Lit58, "select list item"));
            $item = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit7, Boolean.FALSE);
        } else {
            $item = Values.empty;
        }
        return $item;
    }

    /* renamed from: io.kodular.fz_arnob.CovidEaseV1_2107.Screen1$frame */
    /* compiled from: Screen1.yail */
    public class frame extends ModuleBody {
        Screen1 $main;

        public frame() {
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            return moduleMethod2.selector == 262 ? this.$main.Gmap$OnMarkerClick(obj4, obj5, obj6) : super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 262) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            switch (moduleMethod2.selector) {
                case 21:
                    return Screen1.lambda2();
                case 22:
                    this.$main.$define();
                    return Values.empty;
                case 23:
                    return Screen1.lambda3();
                case 24:
                    return Screen1.lambda4();
                case 25:
                    return Screen1.lambda5();
                case 26:
                    return Screen1.lambda6();
                case 27:
                    return Screen1.lambda7();
                case 28:
                    return Screen1.lambda8();
                case 29:
                    return Screen1.lambda9();
                case 30:
                    return Screen1.lambda10();
                case 31:
                    return Screen1.lambda11();
                case 32:
                    return Screen1.lambda12();
                case 33:
                    return Screen1.lambda13();
                case 34:
                    return Screen1.lambda14();
                case 35:
                    return Screen1.lambda15();
                case 36:
                    return Screen1.lambda16();
                case 37:
                    return Screen1.lambda17();
                case 38:
                    return Screen1.lambda18();
                case 39:
                    return Screen1.lambda19();
                case 40:
                    return Screen1.lambda20();
                case 44:
                    return Screen1.lambda21();
                case 48:
                    return Screen1.lambda26();
                case 49:
                    return Screen1.lambda25();
                case 50:
                    return Screen1.lambda30();
                case 51:
                    return Screen1.lambda32();
                case 52:
                    return Screen1.lambda33();
                case 54:
                    return frame0.lambda37();
                case 56:
                    return Screen1.lambda31();
                case 57:
                    return Screen1.lambda40();
                case 58:
                    return Screen1.lambda41();
                case 60:
                    return frame1.lambda45();
                case 62:
                    return Screen1.lambda39();
                case 63:
                    return Screen1.lambda38();
                case 64:
                    return Screen1.lambda46();
                case 65:
                    return this.$main.Screen1$Initialize();
                case 67:
                    return Screen1.lambda47();
                case 68:
                    return Screen1.lambda48();
                case 69:
                    return Screen1.lambda49();
                case 70:
                    return Screen1.lambda50();
                case 71:
                    return Screen1.lambda51();
                case 72:
                    return Screen1.lambda52();
                case 73:
                    return Screen1.lambda55();
                case 74:
                    return Screen1.lambda56();
                case 75:
                    return Screen1.lambda54();
                case 76:
                    return Screen1.lambda58();
                case 77:
                    return Screen1.lambda59();
                case 78:
                    return Screen1.lambda57();
                case 79:
                    return Screen1.lambda53();
                case 80:
                    return Screen1.lambda61();
                case 81:
                    return Screen1.lambda62();
                case 82:
                    return Screen1.lambda60();
                case 83:
                    return this.$main.Screen1$BackPressed();
                case 84:
                    return Screen1.lambda63();
                case 85:
                    return Screen1.lambda64();
                case 86:
                    return Screen1.lambda65();
                case 87:
                    return Screen1.lambda66();
                case 88:
                    return Screen1.lambda67();
                case 89:
                    return Screen1.lambda68();
                case 90:
                    return Screen1.lambda69();
                case 91:
                    return Screen1.lambda70();
                case 92:
                    return this.$main.Label10$Click();
                case 93:
                    return Screen1.lambda71();
                case 94:
                    return Screen1.lambda72();
                case 95:
                    return Screen1.lambda73();
                case 96:
                    return Screen1.lambda74();
                case 97:
                    return Screen1.lambda75();
                case 98:
                    return Screen1.lambda76();
                case 99:
                    return Screen1.lambda77();
                case 100:
                    return Screen1.lambda78();
                case 101:
                    return Screen1.lambda79();
                case 102:
                    return Screen1.lambda80();
                case 103:
                    return Screen1.lambda81();
                case 104:
                    return Screen1.lambda82();
                case 105:
                    return this.$main.Button1$Click();
                case 106:
                    return Screen1.lambda83();
                case 107:
                    return Screen1.lambda84();
                case 108:
                    return this.$main.POPbutton$Click();
                case 109:
                    return Screen1.lambda85();
                case 110:
                    return Screen1.lambda86();
                case 111:
                    return Screen1.lambda87();
                case 112:
                    return Screen1.lambda88();
                case 113:
                    return Screen1.lambda89();
                case 114:
                    return Screen1.lambda90();
                case 115:
                    return Screen1.lambda91();
                case 116:
                    return Screen1.lambda92();
                case 117:
                    return Screen1.lambda93();
                case 118:
                    return Screen1.lambda94();
                case 119:
                    return Screen1.lambda95();
                case 120:
                    return Screen1.lambda96();
                case 121:
                    return Screen1.lambda97();
                case 122:
                    return Screen1.lambda98();
                case 123:
                    return Screen1.lambda99();
                case 124:
                    return Screen1.lambda100();
                case 125:
                    return Screen1.lambda101();
                case 126:
                    return Screen1.lambda102();
                case 127:
                    return Screen1.lambda103();
                case 128:
                    return Screen1.lambda104();
                case 129:
                    return Screen1.lambda105();
                case 130:
                    return Screen1.lambda106();
                case 131:
                    return Screen1.lambda107();
                case 132:
                    return Screen1.lambda108();
                case 133:
                    return Screen1.lambda109();
                case 134:
                    return Screen1.lambda110();
                case 135:
                    return Screen1.lambda111();
                case 136:
                    return Screen1.lambda112();
                case 137:
                    return Screen1.lambda113();
                case 138:
                    return Screen1.lambda114();
                case 139:
                    return Screen1.lambda115();
                case 140:
                    return Screen1.lambda116();
                case 141:
                    return this.$main.cancel2$Click();
                case 142:
                    return Screen1.lambda117();
                case 143:
                    return Screen1.lambda118();
                case 144:
                    return Screen1.lambda119();
                case 145:
                    return Screen1.lambda120();
                case 146:
                    return Screen1.lambda121();
                case 147:
                    return Screen1.lambda122();
                case 148:
                    return Screen1.lambda123();
                case 149:
                    return Screen1.lambda124();
                case 151:
                    return Screen1.lambda125();
                case 152:
                    return Screen1.lambda126();
                case 153:
                    return this.$main.Vertical_Arrangement2$Click();
                case 154:
                    return Screen1.lambda127();
                case 155:
                    return Screen1.lambda128();
                case 156:
                    return this.$main.Label11$Click();
                case 157:
                    return Screen1.lambda129();
                case 158:
                    return Screen1.lambda130();
                case 159:
                    return Screen1.lambda131();
                case ComponentConstants.TEXTBOX_PREFERRED_WIDTH:
                    return Screen1.lambda132();
                case 161:
                    return Screen1.lambda133();
                case 162:
                    return Screen1.lambda134();
                case 163:
                    return Screen1.lambda135();
                case 164:
                    return Screen1.lambda136();
                case 165:
                    return Screen1.lambda137();
                case 166:
                    return Screen1.lambda138();
                case 167:
                    return Screen1.lambda139();
                case 168:
                    return Screen1.lambda140();
                case 169:
                    return Screen1.lambda141();
                case 170:
                    return Screen1.lambda142();
                case 171:
                    return Screen1.lambda143();
                case 172:
                    return Screen1.lambda144();
                case 173:
                    return Screen1.lambda145();
                case 174:
                    return Screen1.lambda146();
                case 175:
                    return Screen1.lambda147();
                case 176:
                    return Screen1.lambda148();
                case 177:
                    return Screen1.lambda149();
                case 178:
                    return Screen1.lambda150();
                case 179:
                    return Screen1.lambda151();
                case 180:
                    return Screen1.lambda152();
                case 181:
                    return Screen1.lambda153();
                case 182:
                    return Screen1.lambda154();
                case 183:
                    return Screen1.lambda155();
                case 184:
                    return Screen1.lambda156();
                case 185:
                    return Screen1.lambda157();
                case 186:
                    return Screen1.lambda158();
                case 187:
                    return Screen1.lambda159();
                case 188:
                    return Screen1.lambda160();
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG:
                    return Screen1.lambda161();
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK:
                    return Screen1.lambda162();
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY:
                    return Screen1.lambda163();
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE:
                    return Screen1.lambda164();
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP:
                    return Screen1.lambda165();
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE:
                    return Screen1.lambda166();
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION:
                    return Screen1.lambda167();
                case 197:
                    return Screen1.lambda168();
                case 198:
                    return Screen1.lambda169();
                case 199:
                    return Screen1.lambda170();
                case 200:
                    return Screen1.lambda171();
                case 201:
                    return Screen1.lambda172();
                case 202:
                    return Screen1.lambda173();
                case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION /*203*/:
                    return Screen1.lambda174();
                case HttpStatus.SC_NO_CONTENT /*204*/:
                    return Screen1.lambda175();
                case HttpStatus.SC_RESET_CONTENT /*205*/:
                    return Screen1.lambda176();
                case HttpStatus.SC_PARTIAL_CONTENT /*206*/:
                    return Screen1.lambda177();
                case HttpStatus.SC_MULTI_STATUS /*207*/:
                    return Screen1.lambda178();
                case 208:
                    return Screen1.lambda179();
                case 209:
                    return Screen1.lambda180();
                case 210:
                    return Screen1.lambda181();
                case 211:
                    return Screen1.lambda182();
                case 213:
                    return Screen1.lambda183();
                case 214:
                    return Screen1.lambda184();
                case 215:
                    return Screen1.lambda185();
                case 216:
                    return Screen1.lambda186();
                case 217:
                    return Screen1.lambda187();
                case 218:
                    return Screen1.lambda188();
                case 219:
                    return Screen1.lambda189();
                case 220:
                    return Screen1.lambda190();
                case 221:
                    return Screen1.lambda191();
                case 222:
                    return Screen1.lambda192();
                case 223:
                    return Screen1.lambda193();
                case YaVersion.YOUNG_ANDROID_VERSION:
                    return Screen1.lambda194();
                case 225:
                    return Screen1.lambda195();
                case 226:
                    return Screen1.lambda196();
                case 227:
                    return Screen1.lambda197();
                case 228:
                    return Screen1.lambda198();
                case 230:
                    return Screen1.lambda199();
                case 231:
                    return Screen1.lambda200();
                case 232:
                    return Screen1.lambda201();
                case 233:
                    return Screen1.lambda202();
                case 234:
                    return Screen1.lambda203();
                case 235:
                    return Screen1.lambda204();
                case 236:
                    return this.$main.Search$GotFocus();
                case 237:
                    return this.$main.Search$OnTextChanged();
                case 238:
                    return Screen1.lambda205();
                case 239:
                    return Screen1.lambda206();
                case 240:
                    return Screen1.lambda207();
                case LispEscapeFormat.ESCAPE_NORMAL:
                    return Screen1.lambda208();
                case LispEscapeFormat.ESCAPE_ALL:
                    return Screen1.lambda209();
                case 243:
                    return Screen1.lambda210();
                case 244:
                    return Screen1.lambda211();
                case 245:
                    return Screen1.lambda212();
                case 246:
                    return this.$main.scname_list$AfterPicking();
                case 247:
                    return Screen1.lambda213();
                case ComponentConstants.LISTVIEW_PREFERRED_WIDTH:
                    return Screen1.lambda214();
                case 249:
                    return this.$main.down$Click();
                case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                    return Screen1.lambda215();
                case Telnet.WILL:
                    return Screen1.lambda216();
                case Telnet.WONT:
                    return Screen1.lambda217();
                case Telnet.f261DO:
                    return Screen1.lambda218();
                case Telnet.DONT:
                    return Screen1.lambda219();
                case 255:
                    return Screen1.lambda220();
                case 256:
                    return Screen1.lambda221();
                case InputDeviceCompat.SOURCE_KEYBOARD:
                    return Screen1.lambda222();
                case 258:
                    return Screen1.lambda223();
                case 259:
                    return Screen1.lambda224();
                case 260:
                    return Screen1.lambda225();
                case 261:
                    return Screen1.lambda226();
                case 263:
                    return this.$main.Gmap$MapIsReady();
                case 265:
                    return Screen1.lambda227();
                case 266:
                    return Screen1.lambda228();
                case 267:
                    return Screen1.lambda229();
                case 268:
                    return Screen1.lambda230();
                case 269:
                    return Screen1.lambda231();
                case 270:
                    return Screen1.lambda232();
                case 271:
                    return this.$main.flip$Click();
                case 272:
                    return Screen1.lambda233();
                case 273:
                    return Screen1.lambda234();
                case 274:
                    return Screen1.lambda235();
                case 275:
                    return Screen1.lambda236();
                case 276:
                    return this.$main.yes$Changed();
                case 277:
                    return Screen1.lambda237();
                case 278:
                    return Screen1.lambda238();
                case 279:
                    return this.$main.test$Changed();
                case 280:
                    return Screen1.lambda239();
                case 281:
                    return Screen1.lambda240();
                case 282:
                    return this.$main.no$Changed();
                case 283:
                    return Screen1.lambda241();
                case 284:
                    return Screen1.lambda242();
                case 285:
                    return this.$main.up$Changed();
                case 286:
                    return Screen1.lambda243();
                case 287:
                    return Screen1.lambda244();
                case 288:
                    return this.$main.lock$Changed();
                case 289:
                    return Screen1.lambda245();
                case 290:
                    return Screen1.lambda246();
                case 291:
                    return Screen1.lambda247();
                case 292:
                    return Screen1.lambda248();
                case 293:
                    return Screen1.lambda249();
                case 294:
                    return Screen1.lambda250();
                case 295:
                    return Screen1.lambda251();
                case 296:
                    return Screen1.lambda252();
                case 297:
                    return Screen1.lambda253();
                case 298:
                    return Screen1.lambda254();
                case 299:
                    return this.$main.HideMarkerPop$Click();
                case HttpStatus.SC_MULTIPLE_CHOICES /*300*/:
                    return Screen1.lambda255();
                case 301:
                    return Screen1.lambda256();
                case 302:
                    return Screen1.lambda257();
                case 303:
                    return Screen1.lambda258();
                case 304:
                    return this.$main.Phn_btn$Click();
                case 305:
                    return Screen1.lambda259();
                case ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED:
                    return Screen1.lambda260();
                case 307:
                    return Screen1.lambda261();
                case ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED:
                    return Screen1.lambda262();
                case ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED:
                    return Screen1.lambda263();
                case ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED:
                    return Screen1.lambda264();
                case ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED:
                    return this.$main.scrolllist$Click();
                case ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED:
                    return Screen1.lambda265();
                case ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED:
                    return Screen1.lambda266();
                case ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH:
                    return Screen1.lambda267();
                case 316:
                    return Screen1.lambda268();
                case 317:
                    return Screen1.lambda269();
                case 318:
                    return Screen1.lambda270();
                case 321:
                    return Screen1.lambda274();
                case 322:
                    return Screen1.lambda275();
                case 323:
                    return this.$main.float_clk$Timer();
                case 324:
                    return Screen1.lambda276();
                case 325:
                    return Screen1.lambda277();
                case 326:
                    return this.$main.float_ckl1$Timer();
                case 327:
                    return Screen1.lambda278();
                case 328:
                    return Screen1.lambda279();
                case 329:
                    return this.$main.location_clk$Timer();
                case 330:
                    return Screen1.lambda280();
                case 331:
                    return Screen1.lambda281();
                case 332:
                    return Screen1.lambda282();
                case 333:
                    return Screen1.lambda283();
                case 334:
                    return this.$main.keyboard_timer$Timer();
                case 335:
                    return Screen1.lambda284();
                case 336:
                    return Screen1.lambda285();
                case 337:
                    return Screen1.lambda286();
                case 338:
                    return Screen1.lambda287();
                default:
                    return super.apply0(moduleMethod2);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 21:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 22:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 23:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 24:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 25:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 26:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 27:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 28:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 29:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 30:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 31:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 32:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 33:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 34:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 35:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 36:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 37:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 38:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 39:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 40:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 44:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 48:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 49:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 50:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 51:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 52:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 54:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 56:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 57:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 58:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 60:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 62:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 63:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 64:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 65:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 67:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 68:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 69:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 70:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 71:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 72:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 73:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 74:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 75:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 76:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 77:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 78:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 79:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 80:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 81:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 82:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 83:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 84:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 85:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 86:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 87:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 88:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 89:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 90:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 91:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 92:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 93:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 94:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 95:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 96:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 97:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 98:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 99:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 100:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 101:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 102:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 103:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 104:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 105:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 106:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 107:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 108:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 109:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 110:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 111:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 112:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 113:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 114:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 115:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 116:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 117:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 118:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 119:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 120:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 121:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 122:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 123:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 124:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 125:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 126:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 127:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 128:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 129:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 130:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 131:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 132:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 133:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 134:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 135:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 136:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 137:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 138:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 139:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 140:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 141:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 142:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 143:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 144:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 145:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 146:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 147:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 148:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 149:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 151:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 152:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 153:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 154:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 155:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 156:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 157:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 158:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 159:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ComponentConstants.TEXTBOX_PREFERRED_WIDTH:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 161:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 162:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 163:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 164:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 165:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 166:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 167:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 168:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 169:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 170:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 171:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 172:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 173:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 174:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 175:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 176:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 177:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 178:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 179:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 180:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 181:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 182:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 183:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 184:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 185:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 186:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 187:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 188:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 197:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 198:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 199:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 200:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 201:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 202:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION /*203*/:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case HttpStatus.SC_NO_CONTENT /*204*/:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case HttpStatus.SC_RESET_CONTENT /*205*/:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case HttpStatus.SC_PARTIAL_CONTENT /*206*/:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case HttpStatus.SC_MULTI_STATUS /*207*/:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 208:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 209:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 210:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 211:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 213:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 214:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 215:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 216:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 217:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 218:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 219:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 220:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 221:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 222:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 223:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case YaVersion.YOUNG_ANDROID_VERSION:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 225:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 226:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 227:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 228:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 230:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 231:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 232:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 233:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 234:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 235:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 236:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 237:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 238:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 239:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 240:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case LispEscapeFormat.ESCAPE_NORMAL:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case LispEscapeFormat.ESCAPE_ALL:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 243:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 244:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 245:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 246:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 247:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ComponentConstants.LISTVIEW_PREFERRED_WIDTH:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 249:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case Telnet.WILL:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case Telnet.WONT:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case Telnet.f261DO:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case Telnet.DONT:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 255:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 256:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case InputDeviceCompat.SOURCE_KEYBOARD:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 258:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 259:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 260:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 261:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 263:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 265:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 266:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 267:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 268:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 269:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 270:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 271:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 272:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 273:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 274:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 275:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 276:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 277:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 278:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 279:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 280:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 281:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 282:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 283:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 284:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 285:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 286:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 287:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 288:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 289:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 290:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 291:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 292:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 293:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 294:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 295:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 296:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 297:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 298:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 299:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case HttpStatus.SC_MULTIPLE_CHOICES /*300*/:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 301:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 302:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 303:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 304:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 305:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 307:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 316:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 317:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 318:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 321:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 322:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 323:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 324:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 325:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 326:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 327:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 328:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 329:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 330:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 331:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 332:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 333:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 334:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 335:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 336:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 337:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 338:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod2, callContext2);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 5:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 6:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 8:
                    CallContext callContext3 = callContext2;
                    Object obj3 = obj2;
                    Object obj4 = obj3;
                    if (!(obj3 instanceof Symbol)) {
                        return -786431;
                    }
                    callContext3.value1 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 10:
                    CallContext callContext4 = callContext2;
                    Object obj5 = obj2;
                    Object obj6 = obj5;
                    if (!(obj5 instanceof Symbol)) {
                        return -786431;
                    }
                    callContext4.value1 = obj6;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 15:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 16:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 17:
                    CallContext callContext5 = callContext2;
                    Object obj7 = obj2;
                    Object obj8 = obj7;
                    if (!(obj7 instanceof Screen1)) {
                        return -786431;
                    }
                    callContext5.value1 = obj8;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 41:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 42:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 43:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 45:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 46:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 47:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 53:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 55:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 59:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 61:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 66:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 150:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 212:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 229:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 319:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod2, obj2, callContext2);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 7:
                    CallContext callContext3 = callContext2;
                    Object obj5 = obj3;
                    Object obj6 = obj5;
                    if (!(obj5 instanceof Symbol)) {
                        return -786431;
                    }
                    callContext3.value1 = obj6;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 8:
                    CallContext callContext4 = callContext2;
                    Object obj7 = obj3;
                    Object obj8 = obj7;
                    if (!(obj7 instanceof Symbol)) {
                        return -786431;
                    }
                    callContext4.value1 = obj8;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 11:
                    CallContext callContext5 = callContext2;
                    Object obj9 = obj3;
                    Object obj10 = obj9;
                    if (!(obj9 instanceof Symbol)) {
                        return -786431;
                    }
                    callContext5.value1 = obj10;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 12:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 14:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 20:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 264:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case ErrorMessages.ERROR_TWITTER_SEARCH_FAILED:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj5 = obj;
            Object obj6 = obj2;
            Object obj7 = obj3;
            Object obj8 = obj4;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 13:
                    callContext2.value1 = obj5;
                    callContext2.value2 = obj6;
                    callContext2.value3 = obj7;
                    callContext2.value4 = obj8;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 4;
                    return 0;
                case 18:
                    CallContext callContext3 = callContext2;
                    Object obj9 = obj5;
                    Object obj10 = obj9;
                    if (!(obj9 instanceof Screen1)) {
                        return -786431;
                    }
                    callContext3.value1 = obj10;
                    CallContext callContext4 = callContext2;
                    Object obj11 = obj6;
                    Object obj12 = obj11;
                    if (!(obj11 instanceof Component)) {
                        return -786430;
                    }
                    callContext4.value2 = obj12;
                    CallContext callContext5 = callContext2;
                    Object obj13 = obj7;
                    Object obj14 = obj13;
                    if (!(obj13 instanceof String)) {
                        return -786429;
                    }
                    callContext5.value3 = obj14;
                    CallContext callContext6 = callContext2;
                    Object obj15 = obj8;
                    Object obj16 = obj15;
                    if (!(obj15 instanceof String)) {
                        return -786428;
                    }
                    callContext6.value4 = obj16;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 4;
                    return 0;
                case 19:
                    CallContext callContext7 = callContext2;
                    Object obj17 = obj5;
                    Object obj18 = obj17;
                    if (!(obj17 instanceof Screen1)) {
                        return -786431;
                    }
                    callContext7.value1 = obj18;
                    CallContext callContext8 = callContext2;
                    Object obj19 = obj6;
                    Object obj20 = obj19;
                    if (!(obj19 instanceof Component)) {
                        return -786430;
                    }
                    callContext8.value2 = obj20;
                    CallContext callContext9 = callContext2;
                    Object obj21 = obj7;
                    Object obj22 = obj21;
                    if (!(obj21 instanceof String)) {
                        return -786429;
                    }
                    callContext9.value3 = obj22;
                    CallContext callContext10 = callContext2;
                    Object obj23 = obj8;
                    Object obj24 = obj23;
                    Object obj25 = obj23;
                    if (1 == 0) {
                        return -786428;
                    }
                    callContext10.value4 = obj24;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 4;
                    return 0;
                default:
                    return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Throwable th;
            Throwable th2;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            switch (moduleMethod2.selector) {
                case 5:
                    return this.$main.getSimpleName(obj2);
                case 6:
                    this.$main.androidLogForm(obj2);
                    return Values.empty;
                case 8:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj2);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "lookup-in-form-environment", 1, obj2);
                        throw th3;
                    }
                case 10:
                    try {
                        return this.$main.isBoundInFormEnvironment((Symbol) obj2) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th4 = th;
                        new WrongType(classCastException2, "is-bound-in-form-environment", 1, obj2);
                        throw th4;
                    }
                case 15:
                    this.$main.addToFormDoAfterCreation(obj2);
                    return Values.empty;
                case 16:
                    this.$main.sendError(obj2);
                    return Values.empty;
                case 17:
                    this.$main.processException(obj2);
                    return Values.empty;
                case 41:
                    return Screen1.lambda22proc(obj2);
                case 42:
                    return Screen1.lambda23proc(obj2);
                case 43:
                    return Screen1.lambda24proc(obj2);
                case 45:
                    return Screen1.lambda27proc(obj2);
                case 46:
                    return Screen1.lambda28proc(obj2);
                case 47:
                    return Screen1.lambda29proc(obj2);
                case 53:
                    return Screen1.lambda34proc(obj2);
                case 55:
                    return Screen1.lambda35proc(obj2);
                case 59:
                    return Screen1.lambda42proc(obj2);
                case 61:
                    return Screen1.lambda43proc(obj2);
                case 66:
                    return this.$main.Screen1$KeyboardVisiblityChanged(obj2);
                case 150:
                    return this.$main.IntroWeb_Viewer$ProgressChanged(obj2);
                case FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN:
                    return this.$main.EmWebView$ProgressChanged(obj2);
                case 212:
                    return this.$main.InWebView$ProgressChanged(obj2);
                case 229:
                    return this.$main.NeWebView$ProgressChanged(obj2);
                case 319:
                    return frame2.lambda273(obj2);
                default:
                    return super.apply1(moduleMethod2, obj2);
            }
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Throwable th8;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj5 = obj;
            Object obj6 = obj2;
            Object obj7 = obj3;
            Object obj8 = obj4;
            switch (moduleMethod2.selector) {
                case 13:
                    this.$main.addToComponents(obj5, obj6, obj7, obj8);
                    return Values.empty;
                case 18:
                    try {
                        try {
                            try {
                                try {
                                    return this.$main.dispatchEvent((Component) obj5, (String) obj6, (String) obj7, (Object[]) obj8) ? Boolean.TRUE : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th9 = th8;
                                    new WrongType(classCastException, "dispatchEvent", 4, obj8);
                                    throw th9;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th10 = th7;
                                new WrongType(classCastException2, "dispatchEvent", 3, obj7);
                                throw th10;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th11 = th6;
                            new WrongType(classCastException3, "dispatchEvent", 2, obj6);
                            throw th11;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th12 = th5;
                        new WrongType(classCastException4, "dispatchEvent", 1, obj5);
                        throw th12;
                    }
                case 19:
                    try {
                        try {
                            try {
                                try {
                                    this.$main.dispatchGenericEvent((Component) obj5, (String) obj6, obj7 != Boolean.FALSE, (Object[]) obj8);
                                    return Values.empty;
                                } catch (ClassCastException e5) {
                                    ClassCastException classCastException5 = e5;
                                    Throwable th13 = th4;
                                    new WrongType(classCastException5, "dispatchGenericEvent", 4, obj8);
                                    throw th13;
                                }
                            } catch (ClassCastException e6) {
                                ClassCastException classCastException6 = e6;
                                Throwable th14 = th3;
                                new WrongType(classCastException6, "dispatchGenericEvent", 3, obj7);
                                throw th14;
                            }
                        } catch (ClassCastException e7) {
                            ClassCastException classCastException7 = e7;
                            Throwable th15 = th2;
                            new WrongType(classCastException7, "dispatchGenericEvent", 2, obj6);
                            throw th15;
                        }
                    } catch (ClassCastException e8) {
                        ClassCastException classCastException8 = e8;
                        Throwable th16 = th;
                        new WrongType(classCastException8, "dispatchGenericEvent", 1, obj5);
                        throw th16;
                    }
                default:
                    return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
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
                case 7:
                    try {
                        this.$main.addToFormEnvironment((Symbol) obj3, obj4);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th4 = th3;
                        new WrongType(classCastException, "add-to-form-environment", 1, obj3);
                        throw th4;
                    }
                case 8:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj3, obj4);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th2;
                        new WrongType(classCastException2, "lookup-in-form-environment", 1, obj3);
                        throw th5;
                    }
                case 11:
                    try {
                        this.$main.addToGlobalVarEnvironment((Symbol) obj3, obj4);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th6 = th;
                        new WrongType(classCastException3, "add-to-global-var-environment", 1, obj3);
                        throw th6;
                    }
                case 12:
                    this.$main.addToEvents(obj3, obj4);
                    return Values.empty;
                case 14:
                    this.$main.addToGlobalVars(obj3, obj4);
                    return Values.empty;
                case 20:
                    return this.$main.lookupHandler(obj3, obj4);
                case 264:
                    return this.$main.Gmap$OnMapClick(obj3, obj4);
                case ErrorMessages.ERROR_TWITTER_SEARCH_FAILED:
                    return this.$main.Tabs$ItemSelected(obj3, obj4);
                case ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION:
                    return this.$main.Firebase_Database1$GotValue(obj3, obj4);
                default:
                    return super.apply2(moduleMethod2, obj3, obj4);
            }
        }
    }

    /* renamed from: io.kodular.fz_arnob.CovidEaseV1_2107.Screen1$frame0 */
    /* compiled from: Screen1.yail */
    public class frame0 extends ModuleBody {
        Object $item;
        final ModuleMethod lambda$Fn35;

        public frame0() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, 0);
            this.lambda$Fn35 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 1 ? lambda36() : super.apply0(moduleMethod2);
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

        static Object lambda37() {
            return C1227runtime.callYailPrimitive(Scheme.numGrt, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnlength, LList.list1(C1227runtime.callYailPrimitive(C1227runtime.string$Mnsplit$Mnat$Mnspaces, LList.list1(C1227runtime.callComponentMethod(Screen1.Lit31, Screen1.Lit37, LList.list2("title", Screen1.Lit35), Screen1.Lit78)), Screen1.Lit79, "split at spaces")), Screen1.Lit80, "length of list"), Screen1.Lit35), Screen1.Lit81, ">");
        }

        /* access modifiers changed from: package-private */
        public Object lambda36() {
            Object obj;
            ModuleMethod moduleMethod = C1227runtime.yail$Mnnot;
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnlist$Mnmember$Qu;
            if (this.$item instanceof Package) {
                Object[] objArr = new Object[3];
                objArr[0] = "The variable ";
                Object[] objArr2 = objArr;
                objArr2[1] = C1227runtime.getDisplayRepresentation(Screen1.Lit28);
                Object[] objArr3 = objArr2;
                objArr3[2] = " is not bound in the current context";
                obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
            } else {
                obj = this.$item;
            }
            return C1227runtime.callYailPrimitive(moduleMethod, LList.list1(C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(obj, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Screen1.Lit14, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Screen1.Lit76, "is in list?")), Screen1.Lit77, "not");
        }
    }

    /* renamed from: io.kodular.fz_arnob.CovidEaseV1_2107.Screen1$frame1 */
    /* compiled from: Screen1.yail */
    public class frame1 extends ModuleBody {
        Object $item;
        final ModuleMethod lambda$Fn43;

        public frame1() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 2, (Object) null, 0);
            this.lambda$Fn43 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 2 ? lambda44() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 2) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        static Object lambda45() {
            return C1227runtime.callYailPrimitive(Scheme.numGrt, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnlength, LList.list1(C1227runtime.callYailPrimitive(C1227runtime.string$Mnsplit$Mnat$Mnspaces, LList.list1(C1227runtime.callComponentMethod(Screen1.Lit31, Screen1.Lit37, LList.list2("title", Screen1.Lit35), Screen1.Lit124)), Screen1.Lit125, "split at spaces")), Screen1.Lit126, "length of list"), Screen1.Lit35), Screen1.Lit127, ">");
        }

        /* access modifiers changed from: package-private */
        public Object lambda44() {
            Object obj;
            ModuleMethod moduleMethod = C1227runtime.yail$Mnnot;
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnlist$Mnmember$Qu;
            if (this.$item instanceof Package) {
                Object[] objArr = new Object[3];
                objArr[0] = "The variable ";
                Object[] objArr2 = objArr;
                objArr2[1] = C1227runtime.getDisplayRepresentation(Screen1.Lit28);
                Object[] objArr3 = objArr2;
                objArr3[2] = " is not bound in the current context";
                obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
            } else {
                obj = this.$item;
            }
            return C1227runtime.callYailPrimitive(moduleMethod, LList.list1(C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(obj, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Screen1.Lit14, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Screen1.Lit122, "is in list?")), Screen1.Lit123, "not");
        }
    }

    static IntNum lambda30() {
        return Lit5;
    }

    static Object lambda31() {
        Object andCoerceProperty$Ex;
        Object[] objArr = new Object[2];
        objArr[0] = lambda$Fn31;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn32;
        if (C1227runtime.processAndDelayed$V(objArr2) != Boolean.FALSE) {
            Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit17, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list"));
            Object addGlobalVarToCurrentFormEnvironment2 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit14, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list"));
            ModuleMethod moduleMethod = proc$Fn33;
            Object yailForEach = C1227runtime.yailForEach(proc$Fn33, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
            Procedure proc = proc$Fn34;
            Object yailForEach2 = C1227runtime.yailForEach(proc$Fn34, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.TRUE, Lit48);
            if (C1227runtime.callYailPrimitive(Scheme.numGrt, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnlength, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit99, "length of list"), Lit5), Lit100, ">") != Boolean.FALSE) {
                Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit102, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit103);
                if (C1227runtime.callYailPrimitive(Scheme.numGrt, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnlength, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit104, "length of list"), Lit105), Lit106, ">") != Boolean.FALSE) {
                    Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit47, Boolean.TRUE, Lit48);
                }
                andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit47, Boolean.TRUE, Lit48);
            } else {
                Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit47, Boolean.TRUE, Lit48);
                andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit61, "No Match found: ", Lit107);
            }
        } else {
            andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
        }
        return andCoerceProperty$Ex;
    }

    static Object lambda32() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnnot$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit60, Lit61), ""), Lit62, "=");
    }

    static Object lambda33() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit46, Lit47), Boolean.FALSE), Lit63, "=");
    }

    public static Object lambda34proc(Object obj) {
        Object obj2;
        Object $item;
        Object obj3;
        Object $item2 = obj;
        SimpleSymbol simpleSymbol = Lit31;
        SimpleSymbol simpleSymbol2 = Lit32;
        ModuleMethod moduleMethod = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        if ($item2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit28);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj2 = $item2;
        }
        Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(C1227runtime.callYailPrimitive(moduleMethod, LList.list2(lookupGlobalVarInCurrentFormEnvironment, obj2), Lit64, "select list item")), Lit65);
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnstarts$Mnat, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnto$Mnlower$Mncase, LList.list1(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnsplit$Mnat$Mnspaces, LList.list1(C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("title", Lit35), Lit66)), Lit67, "split at spaces"), Lit35), Lit68, "select list item")), Lit69, "downcase"), C1227runtime.callYailPrimitive(C1227runtime.string$Mnto$Mnlower$Mncase, LList.list1(C1227runtime.getProperty$1(Lit60, Lit61)), Lit70, "downcase")), Lit71, "starts at"), Lit35), Lit72, "=") != Boolean.FALSE) {
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
            Object lookupGlobalVarInCurrentFormEnvironment2 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit14, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
            if ($item2 instanceof Package) {
                Object[] objArr4 = new Object[3];
                objArr4[0] = "The variable ";
                Object[] objArr5 = objArr4;
                objArr5[1] = C1227runtime.getDisplayRepresentation(Lit28);
                Object[] objArr6 = objArr5;
                objArr6[2] = " is not bound in the current context";
                obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
            } else {
                obj3 = $item2;
            }
            Object callYailPrimitive = C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(lookupGlobalVarInCurrentFormEnvironment2, obj3), Lit73, "add items to list");
            $item = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("title", Lit35), Lit74)), Lit75, "add items to list");
        } else {
            $item = Values.empty;
        }
        return $item;
    }

    public static Object lambda35proc(Object $item) {
        frame0 frame02;
        Object $item2;
        Object obj;
        Object obj2;
        new frame0();
        frame0 frame03 = frame02;
        frame03.$item = $item;
        Object[] objArr = new Object[2];
        objArr[0] = frame03.lambda$Fn35;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn36;
        if (C1227runtime.processAndDelayed$V(objArr2) != Boolean.FALSE) {
            SimpleSymbol simpleSymbol = Lit31;
            SimpleSymbol simpleSymbol2 = Lit32;
            ModuleMethod moduleMethod = C1227runtime.yail$Mnlist$Mnget$Mnitem;
            Object lookupGlobalVarInCurrentFormEnvironment = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
            if (frame03.$item instanceof Package) {
                Object[] objArr3 = new Object[3];
                objArr3[0] = "The variable ";
                Object[] objArr4 = objArr3;
                objArr4[1] = C1227runtime.getDisplayRepresentation(Lit28);
                Object[] objArr5 = objArr4;
                objArr5[2] = " is not bound in the current context";
                obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr5), "Unbound Variable");
            } else {
                obj = frame03.$item;
            }
            Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(C1227runtime.callYailPrimitive(moduleMethod, LList.list2(lookupGlobalVarInCurrentFormEnvironment, obj), Lit82, "select list item")), Lit83);
            if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnstarts$Mnat, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnto$Mnlower$Mncase, LList.list1(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnsplit$Mnat$Mnspaces, LList.list1(C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("title", Lit35), Lit84)), Lit85, "split at spaces"), Lit86), Lit87, "select list item")), Lit88, "downcase"), C1227runtime.callYailPrimitive(C1227runtime.string$Mnto$Mnlower$Mncase, LList.list1(C1227runtime.getProperty$1(Lit60, Lit61)), Lit89, "downcase")), Lit90, "starts at"), Lit35), Lit91, "=") != Boolean.FALSE) {
                ModuleMethod moduleMethod2 = C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
                Object lookupGlobalVarInCurrentFormEnvironment2 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit14, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
                if (frame03.$item instanceof Package) {
                    Object[] objArr6 = new Object[3];
                    objArr6[0] = "The variable ";
                    Object[] objArr7 = objArr6;
                    objArr7[1] = C1227runtime.getDisplayRepresentation(Lit28);
                    Object[] objArr8 = objArr7;
                    objArr8[2] = " is not bound in the current context";
                    obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr8), "Unbound Variable");
                } else {
                    obj2 = frame03.$item;
                }
                Object callYailPrimitive = C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(lookupGlobalVarInCurrentFormEnvironment2, obj2), Lit92, "add items to list");
                $item2 = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("title", Lit35), Lit93)), Lit94, "add items to list");
            } else {
                $item2 = Values.empty;
            }
        } else {
            $item2 = Values.empty;
        }
        return $item2;
    }

    static Procedure lambda38() {
        return lambda$Fn38;
    }

    static Object lambda39() {
        Object andCoerceProperty$Ex;
        Object[] objArr = new Object[2];
        objArr[0] = lambda$Fn39;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn40;
        if (C1227runtime.processAndDelayed$V(objArr2) != Boolean.FALSE) {
            Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit17, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list"));
            Object addGlobalVarToCurrentFormEnvironment2 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit14, C1227runtime.callYailPrimitive(C1227runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list"));
            ModuleMethod moduleMethod = proc$Fn41;
            Object yailForEach = C1227runtime.yailForEach(proc$Fn41, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
            Procedure proc = proc$Fn42;
            Object yailForEach2 = C1227runtime.yailForEach(proc$Fn42, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.TRUE, Lit48);
            if (C1227runtime.callYailPrimitive(Scheme.numGrt, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnlength, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit140, "length of list"), Lit5), Lit141, ">") != Boolean.FALSE) {
                Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit102, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit103);
                if (C1227runtime.callYailPrimitive(Scheme.numGrt, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnlength, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit142, "length of list"), Lit105), Lit143, ">") != Boolean.FALSE) {
                    Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit47, Boolean.TRUE, Lit48);
                }
                andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit47, Boolean.TRUE, Lit48);
            } else {
                Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit47, Boolean.TRUE, Lit48);
                andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit61, "No Match found: ", Lit107);
            }
        } else {
            andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
        }
        return andCoerceProperty$Ex;
    }

    static Object lambda40() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnnot$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit60, Lit61), ""), Lit108, "=");
    }

    static Object lambda41() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit46, Lit47), Boolean.FALSE), Lit109, "=");
    }

    public static Object lambda42proc(Object obj) {
        Object obj2;
        Object $item;
        Object obj3;
        Object $item2 = obj;
        SimpleSymbol simpleSymbol = Lit31;
        SimpleSymbol simpleSymbol2 = Lit32;
        ModuleMethod moduleMethod = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        if ($item2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit28);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj2 = $item2;
        }
        Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(C1227runtime.callYailPrimitive(moduleMethod, LList.list2(lookupGlobalVarInCurrentFormEnvironment, obj2), Lit110, "select list item")), Lit111);
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnstarts$Mnat, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnto$Mnlower$Mncase, LList.list1(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnsplit$Mnat$Mnspaces, LList.list1(C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("title", Lit35), Lit112)), Lit113, "split at spaces"), Lit35), Lit114, "select list item")), Lit115, "downcase"), C1227runtime.callYailPrimitive(C1227runtime.string$Mnto$Mnlower$Mncase, LList.list1(C1227runtime.getProperty$1(Lit60, Lit61)), Lit116, "downcase")), Lit117, "starts at"), Lit35), Lit118, "=") != Boolean.FALSE) {
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
            Object lookupGlobalVarInCurrentFormEnvironment2 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit14, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
            if ($item2 instanceof Package) {
                Object[] objArr4 = new Object[3];
                objArr4[0] = "The variable ";
                Object[] objArr5 = objArr4;
                objArr5[1] = C1227runtime.getDisplayRepresentation(Lit28);
                Object[] objArr6 = objArr5;
                objArr6[2] = " is not bound in the current context";
                obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
            } else {
                obj3 = $item2;
            }
            Object callYailPrimitive = C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(lookupGlobalVarInCurrentFormEnvironment2, obj3), Lit119, "add items to list");
            $item = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("title", Lit35), Lit120)), Lit121, "add items to list");
        } else {
            $item = Values.empty;
        }
        return $item;
    }

    public static Object lambda43proc(Object $item) {
        frame1 frame12;
        Object $item2;
        Object obj;
        Object obj2;
        new frame1();
        frame1 frame13 = frame12;
        frame13.$item = $item;
        Object[] objArr = new Object[2];
        objArr[0] = frame13.lambda$Fn43;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn44;
        if (C1227runtime.processAndDelayed$V(objArr2) != Boolean.FALSE) {
            SimpleSymbol simpleSymbol = Lit31;
            SimpleSymbol simpleSymbol2 = Lit32;
            ModuleMethod moduleMethod = C1227runtime.yail$Mnlist$Mnget$Mnitem;
            Object lookupGlobalVarInCurrentFormEnvironment = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
            if (frame13.$item instanceof Package) {
                Object[] objArr3 = new Object[3];
                objArr3[0] = "The variable ";
                Object[] objArr4 = objArr3;
                objArr4[1] = C1227runtime.getDisplayRepresentation(Lit28);
                Object[] objArr5 = objArr4;
                objArr5[2] = " is not bound in the current context";
                obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr5), "Unbound Variable");
            } else {
                obj = frame13.$item;
            }
            Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list1(C1227runtime.callYailPrimitive(moduleMethod, LList.list2(lookupGlobalVarInCurrentFormEnvironment, obj), Lit128, "select list item")), Lit129);
            if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnstarts$Mnat, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnto$Mnlower$Mncase, LList.list1(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.callYailPrimitive(C1227runtime.string$Mnsplit$Mnat$Mnspaces, LList.list1(C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("title", Lit35), Lit130)), Lit131, "split at spaces"), Lit86), Lit132, "select list item")), Lit133, "downcase"), C1227runtime.callYailPrimitive(C1227runtime.string$Mnto$Mnlower$Mncase, LList.list1(C1227runtime.getProperty$1(Lit60, Lit61)), Lit134, "downcase")), Lit135, "starts at"), Lit35), Lit136, "=") != Boolean.FALSE) {
                ModuleMethod moduleMethod2 = C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
                Object lookupGlobalVarInCurrentFormEnvironment2 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit14, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
                if (frame13.$item instanceof Package) {
                    Object[] objArr6 = new Object[3];
                    objArr6[0] = "The variable ";
                    Object[] objArr7 = objArr6;
                    objArr7[1] = C1227runtime.getDisplayRepresentation(Lit28);
                    Object[] objArr8 = objArr7;
                    objArr8[2] = " is not bound in the current context";
                    obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr8), "Unbound Variable");
                } else {
                    obj2 = frame13.$item;
                }
                Object callYailPrimitive = C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(lookupGlobalVarInCurrentFormEnvironment2, obj2), Lit137, "add items to list");
                $item2 = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit17, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("title", Lit35), Lit138)), Lit139, "add items to list");
            } else {
                $item2 = Values.empty;
            }
        } else {
            $item2 = Values.empty;
        }
        return $item2;
    }

    static Object lambda46() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit144, "CovidEase 1.2107", Lit107);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit145, Lit146, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit148, Lit149, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit150, "6662989908279296", Lit107);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit151, "CovidEase", Lit107);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit152, "fade", Lit107);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit153, "logo1.png", Lit107);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit154, Lit155, Lit147);
        Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit156, "zoom", Lit107);
        Object andCoerceProperty$Ex10 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit157, "com.covidease.lab", Lit107);
        Object andCoerceProperty$Ex11 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit158, Lit159, Lit147);
        Object andCoerceProperty$Ex12 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit160, Lit161, Lit147);
        Object andCoerceProperty$Ex13 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit162, "portrait", Lit107);
        Object andCoerceProperty$Ex14 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit163, "Home", Lit107);
        Object andCoerceProperty$Ex15 = C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit164, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit0, Lit165, "1.2107", Lit107);
    }

    public Object Screen1$Initialize() {
        Object callComponentMethod;
        C1227runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit166;
        SimpleSymbol simpleSymbol2 = Lit167;
        Pair list1 = LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit168));
        Pair chain4 = LList.chain4(list1, "", "", "", Boolean.FALSE);
        Object callComponentMethod2 = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, list1, Lit169);
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.callComponentMethod(Lit170, Lit171, LList.Empty, LList.Empty), Boolean.TRUE), Lit172, "=") != Boolean.FALSE) {
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit174, Lit175, Lit147);
            Object callComponentMethod3 = C1227runtime.callComponentMethod(Lit176, Lit177, LList.list3(Lit35, "Home", "ho.png"), Lit178);
            Object callComponentMethod4 = C1227runtime.callComponentMethod(Lit176, Lit177, LList.list3(Lit86, "Emergency", "em.png"), Lit179);
            Object callComponentMethod5 = C1227runtime.callComponentMethod(Lit176, Lit177, LList.list3(Lit105, "Information", "in.png"), Lit180);
            Object callComponentMethod6 = C1227runtime.callComponentMethod(Lit176, Lit177, LList.list3(Lit181, "News", "ne.png"), Lit182);
            Object callComponentMethod7 = C1227runtime.callComponentMethod(Lit183, Lit184, LList.Empty, LList.Empty);
            Object callComponentMethod8 = C1227runtime.callComponentMethod(Lit185, Lit184, LList.Empty, LList.Empty);
            Object callComponentMethod9 = C1227runtime.callComponentMethod(Lit186, Lit184, LList.Empty, LList.Empty);
            Object callComponentMethod10 = C1227runtime.callComponentMethod(Lit187, Lit184, LList.Empty, LList.Empty);
            Object callComponentMethod11 = C1227runtime.callComponentMethod(Lit183, Lit188, LList.Empty, LList.Empty);
            Object callComponentMethod12 = C1227runtime.callComponentMethod(Lit185, Lit188, LList.Empty, LList.Empty);
            Object callComponentMethod13 = C1227runtime.callComponentMethod(Lit186, Lit188, LList.Empty, LList.Empty);
            Object callComponentMethod14 = C1227runtime.callComponentMethod(Lit187, Lit188, LList.Empty, LList.Empty);
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit190, "", Lit107);
            callComponentMethod = C1227runtime.callComponentMethod(Lit189, Lit191, LList.list2("update", "false"), Lit192);
        } else {
            Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit47, Boolean.TRUE, Lit48);
            callComponentMethod = C1227runtime.callComponentMethod(Lit166, Lit193, LList.Empty, LList.Empty);
        }
        return callComponentMethod;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x003e, code lost:
        if (com.google.youngandroid.C1227runtime.signalRuntimeError(kawa.lib.strings.stringAppend(r7), "Unbound Variable") != java.lang.Boolean.FALSE) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0051, code lost:
        if (r2 == java.lang.Boolean.FALSE) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0053, code lost:
        r3 = com.google.youngandroid.C1227runtime.setAndCoerceProperty$Ex(Lit197, Lit198, java.lang.Boolean.TRUE, Lit48);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object Screen1$KeyboardVisiblityChanged(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r3 = r1
            java.lang.Object r3 = com.google.youngandroid.C1227runtime.sanitizeComponentData(r3)
            r2 = r3
            com.google.youngandroid.C1227runtime.setThisForm()
            r3 = r2
            boolean r3 = r3 instanceof java.lang.Package
            if (r3 == 0) goto L_0x004e
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 0
            java.lang.String r6 = "The variable "
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 1
            gnu.mapping.SimpleSymbol r6 = Lit196
            java.lang.Object r6 = com.google.youngandroid.C1227runtime.getDisplayRepresentation(r6)
            r4[r5] = r6
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 2
            java.lang.String r6 = " is not bound in the current context"
            r4[r5] = r6
            gnu.lists.FString r3 = kawa.lib.strings.stringAppend(r3)
            java.lang.String r4 = "Unbound Variable"
            java.lang.Object r3 = com.google.youngandroid.C1227runtime.signalRuntimeError(r3, r4)
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            if (r3 == r4) goto L_0x0053
        L_0x0040:
            gnu.mapping.SimpleSymbol r3 = Lit176
            gnu.mapping.SimpleSymbol r4 = Lit47
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            gnu.mapping.SimpleSymbol r6 = Lit48
            java.lang.Object r3 = com.google.youngandroid.C1227runtime.setAndCoerceProperty$Ex(r3, r4, r5, r6)
        L_0x004c:
            r0 = r3
            return r0
        L_0x004e:
            r3 = r2
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            if (r3 != r4) goto L_0x0040
        L_0x0053:
            gnu.mapping.SimpleSymbol r3 = Lit197
            gnu.mapping.SimpleSymbol r4 = Lit198
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            gnu.mapping.SimpleSymbol r6 = Lit48
            java.lang.Object r3 = com.google.youngandroid.C1227runtime.setAndCoerceProperty$Ex(r3, r4, r5, r6)
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.kodular.fz_arnob.CovidEaseV1_2107.Screen1.Screen1$KeyboardVisiblityChanged(java.lang.Object):java.lang.Object");
    }

    public Object Screen1$BackPressed() {
        Object obj;
        C1227runtime.setThisForm();
        Object[] objArr = new Object[2];
        objArr[0] = lambda$Fn46;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn47;
        if (C1227runtime.processAndDelayed$V(objArr2) != Boolean.FALSE) {
            obj = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit11, C1227runtime.$Stthe$Mnnull$Mnvalue$St) != Boolean.FALSE ? C1227runtime.callComponentMethod(Lit183, Lit204, LList.Empty, LList.Empty) : Values.empty;
        } else {
            Object[] objArr3 = new Object[2];
            objArr3[0] = lambda$Fn48;
            Object[] objArr4 = objArr3;
            objArr4[1] = lambda$Fn49;
            if (C1227runtime.processAndDelayed$V(objArr4) != Boolean.FALSE) {
                obj = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit10, C1227runtime.$Stthe$Mnnull$Mnvalue$St) != Boolean.FALSE ? C1227runtime.callComponentMethod(Lit185, Lit204, LList.Empty, LList.Empty) : Values.empty;
            } else {
                Object[] objArr5 = new Object[2];
                objArr5[0] = lambda$Fn50;
                Object[] objArr6 = objArr5;
                objArr6[1] = lambda$Fn51;
                if (C1227runtime.processAndDelayed$V(objArr6) != Boolean.FALSE) {
                    obj = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit8, C1227runtime.$Stthe$Mnnull$Mnvalue$St) != Boolean.FALSE ? C1227runtime.callComponentMethod(Lit185, Lit204, LList.Empty, LList.Empty) : Values.empty;
                } else if (C1227runtime.getProperty$1(Lit209, Lit47) == Boolean.FALSE) {
                    Object[] objArr7 = new Object[2];
                    objArr7[0] = lambda$Fn52;
                    Object[] objArr8 = objArr7;
                    objArr8[1] = lambda$Fn59;
                    if (C1227runtime.processOrDelayed$V(objArr8) != Boolean.FALSE) {
                        Object callComponentMethod = C1227runtime.callComponentMethod(Lit176, Lit218, LList.list1(Lit35), Lit219);
                        Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit0, Lit220, LList.Empty, LList.Empty);
                        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit47, Boolean.TRUE, Lit48);
                        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit47, Boolean.FALSE, Lit48);
                        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit47, Boolean.FALSE, Lit48);
                        obj = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Boolean.FALSE, Lit48);
                    } else {
                        obj = Values.empty;
                    }
                } else if (C1227runtime.getProperty$1(Lit98, Lit47) != Boolean.FALSE) {
                    Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit47, Boolean.FALSE, Lit48);
                    Object callComponentMethod3 = C1227runtime.callComponentMethod(Lit210, Lit211, LList.Empty, LList.Empty);
                    Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit47, Boolean.TRUE, Lit48);
                    obj = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
                } else if (C1227runtime.getProperty$1(Lit212, Lit47) != Boolean.FALSE) {
                    obj = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.FALSE, Lit48);
                } else {
                    Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit61, "Do you want to close <br /> the application?", Lit107);
                    Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit47, Boolean.TRUE, Lit48);
                    Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit47, Boolean.TRUE, Lit48);
                    obj = C1227runtime.callComponentMethod(Lit166, Lit193, LList.Empty, LList.Empty);
                }
            }
        }
        return obj;
    }

    static Object lambda47() {
        return C1227runtime.getProperty$1(Lit201, Lit47);
    }

    static Object lambda48() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnnot$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit183, Lit202), "News Portal"), Lit203, "=");
    }

    static Object lambda49() {
        return C1227runtime.getProperty$1(Lit205, Lit47);
    }

    static Object lambda50() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnnot$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit186, Lit202), "Emergency"), Lit206, "=");
    }

    static Object lambda51() {
        return C1227runtime.getProperty$1(Lit207, Lit47);
    }

    static Object lambda52() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnnot$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit185, Lit202), "Info"), Lit208, "=");
    }

    static Object lambda53() {
        Object[] objArr = new Object[2];
        objArr[0] = lambda$Fn53;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn56;
        return C1227runtime.processOrDelayed$V(objArr2);
    }

    static Object lambda54() {
        Object[] objArr = new Object[2];
        objArr[0] = lambda$Fn54;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn55;
        return C1227runtime.processAndDelayed$V(objArr2);
    }

    static Object lambda55() {
        return C1227runtime.getProperty$1(Lit207, Lit47);
    }

    static Object lambda56() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit185, Lit202), "Info"), Lit215, "=");
    }

    static Object lambda57() {
        Object[] objArr = new Object[2];
        objArr[0] = lambda$Fn57;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn58;
        return C1227runtime.processAndDelayed$V(objArr2);
    }

    static Object lambda58() {
        return C1227runtime.getProperty$1(Lit201, Lit47);
    }

    static Object lambda59() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit183, Lit202), "News Portal"), Lit216, "=");
    }

    static Object lambda60() {
        Object[] objArr = new Object[2];
        objArr[0] = lambda$Fn60;
        Object[] objArr2 = objArr;
        objArr2[1] = lambda$Fn61;
        return C1227runtime.processAndDelayed$V(objArr2);
    }

    static Object lambda61() {
        return C1227runtime.getProperty$1(Lit205, Lit47);
    }

    static Object lambda62() {
        return C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit186, Lit202), "Emergency"), Lit217, "=");
    }

    static Object lambda63() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit226, Lit227, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit228, Lit229, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit47, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit230, Boolean.TRUE, Lit48);
    }

    static Object lambda64() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit226, Lit227, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit228, Lit229, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit47, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit230, Boolean.TRUE, Lit48);
    }

    static Object lambda65() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit226, Lit234, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit228, Lit236, Lit147);
    }

    static Object lambda66() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit226, Lit234, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit228, Lit236, Lit147);
    }

    static Object lambda67() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit239, Lit224, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit239, Lit228, Lit236, Lit147);
    }

    static Object lambda68() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit239, Lit224, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit239, Lit228, Lit236, Lit147);
    }

    static Object lambda69() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit243, Lit244, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit235, Lit248, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit61, "&#xf00d;", Lit107);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit249, Lit86, Lit147);
        Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit250, Lit251, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda70() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit243, Lit244, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit235, Lit248, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit61, "&#xf00d;", Lit107);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit249, Lit86, Lit147);
        Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit250, Lit251, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit47, Boolean.FALSE, Lit48);
    }

    public Object Label10$Click() {
        C1227runtime.setThisForm();
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit253, Lit61), "Open Settings"), Lit254, "=") != Boolean.FALSE) {
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit26, Lit255, LList.list3(Lit256, Lit257, Lit258), Lit259);
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Close App", Lit107);
        }
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit253, Lit61), "Watch Video"), Lit260, "=") != Boolean.FALSE) {
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit198, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit198, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Close App", Lit107);
        }
        Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit166, Lit265, LList.Empty, LList.Empty);
        return C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda71() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit269, Lit235, Lit270, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit269, Lit228, Lit270, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit269, Lit271, "at.png", Lit107);
    }

    static Object lambda72() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit269, Lit235, Lit270, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit269, Lit228, Lit270, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit269, Lit271, "at.png", Lit107);
    }

    static Object lambda73() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit274, Lit235, Lit275, Lit147);
    }

    static Object lambda74() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit274, Lit235, Lit275, Lit147);
    }

    static Object lambda75() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit61, "Network Connection Lost", Lit107);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit250, Lit279, Lit147);
    }

    static Object lambda76() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit61, "Network Connection Lost", Lit107);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit250, Lit279, Lit147);
    }

    static Object lambda77() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit282, Lit235, Lit283, Lit147);
    }

    static Object lambda78() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit282, Lit235, Lit283, Lit147);
    }

    static Object lambda79() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit286, Lit224, Lit105, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit286, Lit228, Lit236, Lit147);
    }

    static Object lambda80() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit286, Lit224, Lit105, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit286, Lit228, Lit236, Lit147);
    }

    static Object lambda81() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit289, Lit226, Lit290, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit289, Lit243, Lit291, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit289, Lit292, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit289, Lit61, "About Us", Lit107);
    }

    static Object lambda82() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit289, Lit226, Lit290, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit289, Lit243, Lit291, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit289, Lit292, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit289, Lit61, "About Us", Lit107);
    }

    public Object Button1$Click() {
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit47, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit47, Boolean.TRUE, Lit48);
    }

    static Object lambda83() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit226, Lit297, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit243, Lit291, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit292, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Close App", Lit107);
    }

    static Object lambda84() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit226, Lit297, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit243, Lit291, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit292, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Close App", Lit107);
    }

    public Object POPbutton$Click() {
        C1227runtime.setThisForm();
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit253, Lit61), "Open Settings"), Lit299, "=") != Boolean.FALSE) {
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit26, Lit255, LList.list3(Lit300, Lit301, Lit258), Lit302);
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit303, Lit304, "android.settings.LOCATION_SOURCE_SETTINGS", Lit107);
            Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit303, Lit305, LList.Empty, LList.Empty);
            Object callComponentMethod3 = C1227runtime.callComponentMethod(Lit166, Lit265, LList.Empty, LList.Empty);
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Close App", Lit107);
        } else if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit253, Lit61), "Watch Video"), Lit306, "=") != Boolean.FALSE) {
            Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit47, Boolean.TRUE, Lit48);
            Object callComponentMethod4 = C1227runtime.callComponentMethod(Lit166, Lit265, LList.Empty, LList.Empty);
            Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Close App", Lit107);
        } else if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit253, Lit61), "Update"), Lit308, "=") != Boolean.FALSE) {
            Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit303, Lit304, "android.intent.action.VIEW", Lit107);
            Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit303, Lit309, "https://play.google.com/store/apps/details?id=com.covidease.lab", Lit107);
            Object callComponentMethod5 = C1227runtime.callComponentMethod(Lit303, Lit305, LList.Empty, LList.Empty);
            Object callYailPrimitive = C1227runtime.callYailPrimitive(C1227runtime.close$Mnapplication, LList.Empty, LList.Empty, "close application");
        } else {
            Object callYailPrimitive2 = C1227runtime.callYailPrimitive(C1227runtime.close$Mnapplication, LList.Empty, LList.Empty, "close application");
        }
        return C1227runtime.callComponentMethod(Lit166, Lit265, LList.Empty, LList.Empty);
    }

    static Object lambda85() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit312, Lit235, Lit313, Lit147);
    }

    static Object lambda86() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit312, Lit235, Lit313, Lit147);
    }

    static Object lambda87() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit226, Lit316, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda88() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit226, Lit316, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda89() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit243, Lit244, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit61, "Ease Lab", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit250, Lit320, Lit147);
    }

    static Object lambda90() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit243, Lit244, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit61, "Ease Lab", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit319, Lit250, Lit320, Lit147);
    }

    static Object lambda91() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit61, "This application focuses on the purpose helping people of Bangladesh to fight with the pandemic Covid-19. ", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit250, Lit324, Lit147);
    }

    static Object lambda92() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit61, "This application focuses on the purpose helping people of Bangladesh to fight with the pandemic Covid-19. ", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit323, Lit250, Lit324, Lit147);
    }

    static Object lambda93() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit61, "Developers Information:", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit250, Lit328, Lit147);
    }

    static Object lambda94() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit61, "Developers Information:", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit327, Lit250, Lit328, Lit147);
    }

    static Object lambda95() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit331, Lit235, Lit332, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit331, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit331, Lit271, "27993432_2023981687844049_835188855666251974_o.png", Lit107);
    }

    static Object lambda96() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit331, Lit235, Lit332, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit331, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit331, Lit271, "27993432_2023981687844049_835188855666251974_o.png", Lit107);
    }

    static Object lambda97() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit336, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit61, "Farhan Zaman Arnob", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit250, Lit337, Lit147);
    }

    static Object lambda98() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit336, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit61, "Farhan Zaman Arnob", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit335, Lit250, Lit337, Lit147);
    }

    static Object lambda100() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit61, "Design Implement, Back-End, UX, Database Processing", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit250, Lit341, Lit147);
    }

    static Object lambda99() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit61, "Design Implement, Back-End, UX, Database Processing", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit340, Lit250, Lit341, Lit147);
    }

    static Object lambda101() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit344, Lit235, Lit345, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit344, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit344, Lit271, "27993432_2023981687844049_83518885566625197_o.png", Lit107);
    }

    static Object lambda102() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit344, Lit235, Lit345, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit344, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit344, Lit271, "27993432_2023981687844049_83518885566625197_o.png", Lit107);
    }

    static Object lambda103() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit336, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit61, "Iftekhar Alam Tousif", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit250, Lit349, Lit147);
    }

    static Object lambda104() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit336, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit61, "Iftekhar Alam Tousif", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit348, Lit250, Lit349, Lit147);
    }

    static Object lambda105() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit61, "Debugging, UI, Data Sourcing, Videography", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit250, Lit353, Lit147);
    }

    static Object lambda106() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit61, "Debugging, UI, Data Sourcing, Videography", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit352, Lit250, Lit353, Lit147);
    }

    static Object lambda107() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit356, Lit235, Lit357, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit356, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit356, Lit271, "fg.png", Lit107);
    }

    static Object lambda108() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit356, Lit235, Lit357, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit356, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit356, Lit271, "fg.png", Lit107);
    }

    static Object lambda109() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit336, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit61, "Faiaz Abrar", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit250, Lit361, Lit147);
    }

    static Object lambda110() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit336, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit61, "Faiaz Abrar", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit360, Lit250, Lit361, Lit147);
    }

    static Object lambda111() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit61, "Data Entry", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit250, Lit365, Lit147);
    }

    static Object lambda112() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit61, "Data Entry", Lit107);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit364, Lit250, Lit365, Lit147);
    }

    static Object lambda113() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit368, Lit235, Lit244, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit368, Lit228, Lit236, Lit147);
    }

    static Object lambda114() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit368, Lit235, Lit244, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit368, Lit228, Lit236, Lit147);
    }

    static Object lambda115() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit371, Lit226, Lit372, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit371, Lit243, Lit291, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit371, Lit292, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit371, Lit61, "Back", Lit107);
    }

    static Object lambda116() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit371, Lit226, Lit372, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit371, Lit243, Lit291, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit371, Lit292, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit371, Lit61, "Back", Lit107);
    }

    public Object cancel2$Click() {
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit294, Lit47, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit233, Lit47, Boolean.TRUE, Lit48);
    }

    static Object lambda117() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit376, Lit243, Lit313, Lit147);
    }

    static Object lambda118() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit376, Lit243, Lit313, Lit147);
    }

    static Object lambda119() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit226, Lit379, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda120() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit226, Lit379, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda121() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit382, Lit383, Lit384, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit382, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda122() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit382, Lit383, Lit384, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit382, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda123() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit187, Lit387, "http://covidease.epizy.com/vdo.html", Lit107);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit187, Lit388, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit187, Lit389, Boolean.FALSE, Lit48);
    }

    static Object lambda124() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit187, Lit387, "http://covidease.epizy.com/vdo.html", Lit107);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit187, Lit388, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit187, Lit389, Boolean.FALSE, Lit48);
    }

    public Object IntroWeb_Viewer$ProgressChanged(Object $progress) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object $progress2 = C1227runtime.sanitizeComponentData($progress);
        C1227runtime.setThisForm();
        ModuleMethod moduleMethod = C1227runtime.yail$Mnequal$Qu;
        NumberCompare numberCompare = Scheme.numGEq;
        if ($progress2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit391);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj = $progress2;
        }
        Object callYailPrimitive = C1227runtime.callYailPrimitive(numberCompare, LList.list2(obj, Lit35), Lit392, ">=");
        NumberCompare numberCompare2 = Scheme.numLss;
        if ($progress2 instanceof Package) {
            Object[] objArr4 = new Object[3];
            objArr4[0] = "The variable ";
            Object[] objArr5 = objArr4;
            objArr5[1] = C1227runtime.getDisplayRepresentation(Lit391);
            Object[] objArr6 = objArr5;
            objArr6[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
        } else {
            obj2 = $progress2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod, LList.list2(callYailPrimitive, C1227runtime.callYailPrimitive(numberCompare2, LList.list2(obj2, Lit393), Lit394, "<")), Lit395, "=") != Boolean.FALSE) {
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit382, Lit47, Boolean.TRUE, Lit48);
            obj4 = C1227runtime.setAndCoerceProperty$Ex(Lit187, Lit47, Boolean.FALSE, Lit48);
        } else {
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnequal$Qu;
            if ($progress2 instanceof Package) {
                Object[] objArr7 = new Object[3];
                objArr7[0] = "The variable ";
                Object[] objArr8 = objArr7;
                objArr8[1] = C1227runtime.getDisplayRepresentation(Lit391);
                Object[] objArr9 = objArr8;
                objArr9[2] = " is not bound in the current context";
                obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr9), "Unbound Variable");
            } else {
                obj3 = $progress2;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(obj3, Lit393), Lit396, "=") != Boolean.FALSE) {
                Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit382, Lit47, Boolean.FALSE, Lit48);
                obj4 = C1227runtime.setAndCoerceProperty$Ex(Lit187, Lit47, Boolean.TRUE, Lit48);
            } else {
                obj4 = Values.empty;
            }
        }
        return obj4;
    }

    static Object lambda125() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit226, Lit401, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit235, Lit21, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit228, Lit236, Lit147);
    }

    static Object lambda126() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit226, Lit401, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit235, Lit21, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit400, Lit228, Lit236, Lit147);
    }

    public Object Vertical_Arrangement2$Click() {
        C1227runtime.setThisForm();
        Object callComponentMethod = C1227runtime.callComponentMethod(Lit187, Lit403, LList.Empty, LList.Empty);
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit47, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit47, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit198, Boolean.TRUE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit198, Boolean.TRUE, Lit48);
    }

    static Object lambda127() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit243, Lit407, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit61, "&#xf04e; Skip to Menu", Lit107);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit250, Lit408, Lit147);
    }

    static Object lambda128() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit243, Lit407, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit61, "&#xf04e; Skip to Menu", Lit107);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit406, Lit250, Lit408, Lit147);
    }

    public Object Label11$Click() {
        C1227runtime.setThisForm();
        Object callComponentMethod = C1227runtime.callComponentMethod(Lit187, Lit403, LList.Empty, LList.Empty);
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit307, Lit47, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit47, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit198, Boolean.TRUE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit198, Boolean.TRUE, Lit48);
    }

    static Object lambda129() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit412, Lit235, Lit413, Lit147);
    }

    static Object lambda130() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit412, Lit235, Lit413, Lit147);
    }

    static Object lambda131() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit226, Lit416, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit228, Lit236, Lit147);
    }

    static Object lambda132() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit226, Lit416, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit228, Lit236, Lit147);
    }

    static Object lambda133() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit419, Lit235, Lit420, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit419, Lit228, Lit421, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit419, Lit271, "logo1.png", Lit107);
    }

    static Object lambda134() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit419, Lit235, Lit420, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit419, Lit228, Lit421, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit419, Lit271, "logo1.png", Lit107);
    }

    static Object lambda135() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit424, Lit235, Lit425, Lit147);
    }

    static Object lambda136() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit424, Lit235, Lit425, Lit147);
    }

    static Object lambda137() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit336, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit243, Lit429, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit245, Lit105, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit61, "CovidEase", Lit107);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit250, Lit430, Lit147);
    }

    static Object lambda138() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit336, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit243, Lit429, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit245, Lit105, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit61, "CovidEase", Lit107);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit428, Lit250, Lit430, Lit147);
    }

    static Object lambda139() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit433, Lit235, Lit434, Lit147);
    }

    static Object lambda140() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit433, Lit235, Lit434, Lit147);
    }

    static Object lambda141() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit437, Lit383, Lit438, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit437, Lit235, Lit439, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit437, Lit228, Lit439, Lit147);
    }

    static Object lambda142() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit437, Lit383, Lit438, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit437, Lit235, Lit439, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit437, Lit228, Lit439, Lit147);
    }

    static Object lambda143() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit442, Lit235, Lit443, Lit147);
    }

    static Object lambda144() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit442, Lit235, Lit443, Lit147);
    }

    static Object lambda145() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit446, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit446, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit446, Lit226, Lit447, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit446, Lit228, Lit448, Lit147);
    }

    static Object lambda146() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit446, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit446, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit446, Lit226, Lit447, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit446, Lit228, Lit448, Lit147);
    }

    static Object lambda147() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit226, Lit451, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit235, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit228, Lit452, Lit147);
    }

    static Object lambda148() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit226, Lit451, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit235, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit228, Lit452, Lit147);
    }

    static Object lambda149() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda150() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda151() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda152() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda153() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit226, Lit460, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit228, Lit461, Lit147);
    }

    static Object lambda154() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit226, Lit460, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit228, Lit461, Lit147);
    }

    static Object lambda155() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit464, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit464, Lit225, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit464, Lit226, Lit465, Lit147);
    }

    static Object lambda156() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit464, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit464, Lit225, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit464, Lit226, Lit465, Lit147);
    }

    static Object lambda157() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit468, Lit383, Lit469, Lit147);
    }

    static Object lambda158() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit468, Lit383, Lit469, Lit147);
    }

    static Object lambda159() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit472, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit472, Lit61, "Getting latest data", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit472, Lit250, Lit473, Lit147);
    }

    static Object lambda160() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit472, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit472, Lit61, "Getting latest data", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit472, Lit250, Lit473, Lit147);
    }

    static Object lambda161() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit476, Lit235, Lit439, Lit147);
    }

    static Object lambda162() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit476, Lit235, Lit439, Lit147);
    }

    static Object lambda163() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit479, Lit226, Lit480, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit479, Lit235, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit479, Lit228, Lit5, Lit147);
    }

    static Object lambda164() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit479, Lit226, Lit480, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit479, Lit235, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit479, Lit228, Lit5, Lit147);
    }

    static Object lambda165() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit387, "http://covidease.epizy.com/emergency.php", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit389, Boolean.FALSE, Lit48);
    }

    static Object lambda166() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit387, "http://covidease.epizy.com/emergency.php", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit389, Boolean.FALSE, Lit48);
    }

    public Object EmWebView$ProgressChanged(Object $progress) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object $progress2 = C1227runtime.sanitizeComponentData($progress);
        C1227runtime.setThisForm();
        ModuleMethod moduleMethod = C1227runtime.yail$Mnequal$Qu;
        NumberCompare numberCompare = Scheme.numGEq;
        if ($progress2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit391);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj = $progress2;
        }
        Object callYailPrimitive = C1227runtime.callYailPrimitive(numberCompare, LList.list2(obj, Lit35), Lit484, ">=");
        NumberCompare numberCompare2 = Scheme.numLss;
        if ($progress2 instanceof Package) {
            Object[] objArr4 = new Object[3];
            objArr4[0] = "The variable ";
            Object[] objArr5 = objArr4;
            objArr5[1] = C1227runtime.getDisplayRepresentation(Lit391);
            Object[] objArr6 = objArr5;
            objArr6[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
        } else {
            obj2 = $progress2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod, LList.list2(callYailPrimitive, C1227runtime.callYailPrimitive(numberCompare2, LList.list2(obj2, Lit393), Lit485, "<")), Lit486, "=") != Boolean.FALSE) {
            Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit10, Boolean.FALSE);
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit47, Boolean.FALSE, Lit48);
            SimpleSymbol simpleSymbol = Lit479;
            SimpleSymbol simpleSymbol2 = Lit174;
            if ($progress2 instanceof Package) {
                Object[] objArr7 = new Object[3];
                objArr7[0] = "The variable ";
                Object[] objArr8 = objArr7;
                objArr8[1] = C1227runtime.getDisplayRepresentation(Lit391);
                Object[] objArr9 = objArr8;
                objArr9[2] = " is not bound in the current context";
                obj5 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr9), "Unbound Variable");
            } else {
                obj5 = $progress2;
            }
            obj4 = C1227runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, obj5, Lit147);
        } else {
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnequal$Qu;
            if ($progress2 instanceof Package) {
                Object[] objArr10 = new Object[3];
                objArr10[0] = "The variable ";
                Object[] objArr11 = objArr10;
                objArr11[1] = C1227runtime.getDisplayRepresentation(Lit391);
                Object[] objArr12 = objArr11;
                objArr12[2] = " is not bound in the current context";
                obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr12), "Unbound Variable");
            } else {
                obj3 = $progress2;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(obj3, Lit393), Lit487, "=") != Boolean.FALSE) {
                Object addGlobalVarToCurrentFormEnvironment2 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit10, Boolean.TRUE);
                Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit459, Lit47, Boolean.FALSE, Lit48);
                obj4 = C1227runtime.setAndCoerceProperty$Ex(Lit186, Lit47, Boolean.TRUE, Lit48);
            } else {
                obj4 = Values.empty;
            }
        }
        return obj4;
    }

    static Object lambda167() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit226, Lit490, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda168() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit226, Lit490, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda169() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit226, Lit494, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit228, Lit495, Lit147);
    }

    static Object lambda170() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit226, Lit494, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit228, Lit495, Lit147);
    }

    static Object lambda171() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit498, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit498, Lit225, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit498, Lit226, Lit499, Lit147);
    }

    static Object lambda172() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit498, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit498, Lit225, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit498, Lit226, Lit499, Lit147);
    }

    static Object lambda173() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit502, Lit383, Lit503, Lit147);
    }

    static Object lambda174() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit502, Lit383, Lit503, Lit147);
    }

    static Object lambda175() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit506, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit506, Lit61, "Getting latest data", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit506, Lit250, Lit507, Lit147);
    }

    static Object lambda176() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit506, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit506, Lit61, "Getting latest data", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit506, Lit250, Lit507, Lit147);
    }

    static Object lambda177() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit510, Lit235, Lit439, Lit147);
    }

    static Object lambda178() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit510, Lit235, Lit439, Lit147);
    }

    static Object lambda179() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit513, Lit226, Lit514, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit513, Lit235, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit513, Lit228, Lit5, Lit147);
    }

    static Object lambda180() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit513, Lit226, Lit514, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit513, Lit235, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit513, Lit228, Lit5, Lit147);
    }

    static Object lambda181() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit387, "http://covidease.epizy.com/info.php", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit389, Boolean.FALSE, Lit48);
    }

    static Object lambda182() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit387, "http://covidease.epizy.com/info.php", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit389, Boolean.FALSE, Lit48);
    }

    public Object InWebView$ProgressChanged(Object $progress) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object $progress2 = C1227runtime.sanitizeComponentData($progress);
        C1227runtime.setThisForm();
        ModuleMethod moduleMethod = C1227runtime.yail$Mnequal$Qu;
        NumberCompare numberCompare = Scheme.numGEq;
        if ($progress2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit391);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj = $progress2;
        }
        Object callYailPrimitive = C1227runtime.callYailPrimitive(numberCompare, LList.list2(obj, Lit35), Lit518, ">=");
        NumberCompare numberCompare2 = Scheme.numLss;
        if ($progress2 instanceof Package) {
            Object[] objArr4 = new Object[3];
            objArr4[0] = "The variable ";
            Object[] objArr5 = objArr4;
            objArr5[1] = C1227runtime.getDisplayRepresentation(Lit391);
            Object[] objArr6 = objArr5;
            objArr6[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
        } else {
            obj2 = $progress2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod, LList.list2(callYailPrimitive, C1227runtime.callYailPrimitive(numberCompare2, LList.list2(obj2, Lit393), Lit519, "<")), Lit520, "=") != Boolean.FALSE) {
            Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit8, Boolean.FALSE);
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit47, Boolean.FALSE, Lit48);
            SimpleSymbol simpleSymbol = Lit513;
            SimpleSymbol simpleSymbol2 = Lit174;
            if ($progress2 instanceof Package) {
                Object[] objArr7 = new Object[3];
                objArr7[0] = "The variable ";
                Object[] objArr8 = objArr7;
                objArr8[1] = C1227runtime.getDisplayRepresentation(Lit391);
                Object[] objArr9 = objArr8;
                objArr9[2] = " is not bound in the current context";
                obj5 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr9), "Unbound Variable");
            } else {
                obj5 = $progress2;
            }
            obj4 = C1227runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, obj5, Lit147);
        } else {
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnequal$Qu;
            if ($progress2 instanceof Package) {
                Object[] objArr10 = new Object[3];
                objArr10[0] = "The variable ";
                Object[] objArr11 = objArr10;
                objArr11[1] = C1227runtime.getDisplayRepresentation(Lit391);
                Object[] objArr12 = objArr11;
                objArr12[2] = " is not bound in the current context";
                obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr12), "Unbound Variable");
            } else {
                obj3 = $progress2;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(obj3, Lit393), Lit521, "=") != Boolean.FALSE) {
                Object addGlobalVarToCurrentFormEnvironment2 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit8, Boolean.TRUE);
                Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit493, Lit47, Boolean.FALSE, Lit48);
                obj4 = C1227runtime.setAndCoerceProperty$Ex(Lit185, Lit47, Boolean.TRUE, Lit48);
            } else {
                obj4 = Values.empty;
            }
        }
        return obj4;
    }

    static Object lambda183() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit226, Lit524, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda184() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit226, Lit524, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda185() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit226, Lit528, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit228, Lit529, Lit147);
    }

    static Object lambda186() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit226, Lit528, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit228, Lit529, Lit147);
    }

    static Object lambda187() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit532, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit532, Lit225, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit532, Lit226, Lit533, Lit147);
    }

    static Object lambda188() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit532, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit532, Lit225, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit532, Lit226, Lit533, Lit147);
    }

    static Object lambda189() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit536, Lit383, Lit537, Lit147);
    }

    static Object lambda190() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit536, Lit383, Lit537, Lit147);
    }

    static Object lambda191() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit540, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit540, Lit61, "Getting latest data", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit540, Lit250, Lit541, Lit147);
    }

    static Object lambda192() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit540, Lit243, Lit313, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit540, Lit61, "Getting latest data", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit540, Lit250, Lit541, Lit147);
    }

    static Object lambda193() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit544, Lit235, Lit439, Lit147);
    }

    static Object lambda194() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit544, Lit235, Lit439, Lit147);
    }

    static Object lambda195() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit547, Lit226, Lit548, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit547, Lit235, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit547, Lit228, Lit5, Lit147);
    }

    static Object lambda196() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit547, Lit226, Lit548, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit547, Lit235, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit547, Lit228, Lit5, Lit147);
    }

    static Object lambda197() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit387, "http://covidease.epizy.com/news.php", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit389, Boolean.FALSE, Lit48);
    }

    static Object lambda198() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit387, "http://covidease.epizy.com/news.php", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit389, Boolean.FALSE, Lit48);
    }

    public Object NeWebView$ProgressChanged(Object $progress) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object $progress2 = C1227runtime.sanitizeComponentData($progress);
        C1227runtime.setThisForm();
        ModuleMethod moduleMethod = C1227runtime.yail$Mnequal$Qu;
        NumberCompare numberCompare = Scheme.numGEq;
        if ($progress2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit391);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj = $progress2;
        }
        Object callYailPrimitive = C1227runtime.callYailPrimitive(numberCompare, LList.list2(obj, Lit35), Lit552, ">=");
        NumberCompare numberCompare2 = Scheme.numLss;
        if ($progress2 instanceof Package) {
            Object[] objArr4 = new Object[3];
            objArr4[0] = "The variable ";
            Object[] objArr5 = objArr4;
            objArr5[1] = C1227runtime.getDisplayRepresentation(Lit391);
            Object[] objArr6 = objArr5;
            objArr6[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
        } else {
            obj2 = $progress2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod, LList.list2(callYailPrimitive, C1227runtime.callYailPrimitive(numberCompare2, LList.list2(obj2, Lit393), Lit553, "<")), Lit554, "=") != Boolean.FALSE) {
            Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit11, Boolean.FALSE);
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit47, Boolean.FALSE, Lit48);
            SimpleSymbol simpleSymbol = Lit547;
            SimpleSymbol simpleSymbol2 = Lit174;
            if ($progress2 instanceof Package) {
                Object[] objArr7 = new Object[3];
                objArr7[0] = "The variable ";
                Object[] objArr8 = objArr7;
                objArr8[1] = C1227runtime.getDisplayRepresentation(Lit391);
                Object[] objArr9 = objArr8;
                objArr9[2] = " is not bound in the current context";
                obj5 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr9), "Unbound Variable");
            } else {
                obj5 = $progress2;
            }
            obj4 = C1227runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, obj5, Lit147);
        } else {
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnequal$Qu;
            if ($progress2 instanceof Package) {
                Object[] objArr10 = new Object[3];
                objArr10[0] = "The variable ";
                Object[] objArr11 = objArr10;
                objArr11[1] = C1227runtime.getDisplayRepresentation(Lit391);
                Object[] objArr12 = objArr11;
                objArr12[2] = " is not bound in the current context";
                obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr12), "Unbound Variable");
            } else {
                obj3 = $progress2;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(obj3, Lit393), Lit555, "=") != Boolean.FALSE) {
                Object addGlobalVarToCurrentFormEnvironment2 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit11, Boolean.TRUE);
                Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit527, Lit47, Boolean.FALSE, Lit48);
                obj4 = C1227runtime.setAndCoerceProperty$Ex(Lit183, Lit47, Boolean.TRUE, Lit48);
            } else {
                obj4 = Values.empty;
            }
        }
        return obj4;
    }

    static Object lambda199() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit228, Lit236, Lit147);
    }

    static Object lambda200() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit228, Lit236, Lit147);
    }

    static Object lambda201() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit560, Lit228, Lit236, Lit147);
    }

    static Object lambda202() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit560, Lit228, Lit236, Lit147);
    }

    static Object lambda203() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit235, Lit270, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit563, "Seach for hospitals or services...", Lit107);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit564, Lit565, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit250, Lit566, Lit147);
    }

    static Object lambda204() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit235, Lit270, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit563, "Seach for hospitals or services...", Lit107);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit564, Lit565, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit250, Lit566, Lit147);
    }

    public Object Search$GotFocus() {
        C1227runtime.setThisForm();
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Boolean.FALSE), Lit568, "=") != Boolean.FALSE) {
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit569, Lit570, LList.Empty, LList.Empty);
        }
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit61, C1227runtime.callComponentMethod(Lit26, Lit572, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit19, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit573), Lit107);
        return Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit59, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
    }

    public Object Search$OnTextChanged() {
        C1227runtime.setThisForm();
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Boolean.FALSE), Lit576, "=") != Boolean.FALSE) {
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit569, Lit570, LList.Empty, LList.Empty);
        }
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit61, C1227runtime.callComponentMethod(Lit26, Lit572, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit19, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit577), Lit107);
        return Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit59, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
    }

    static Object lambda205() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit226, Lit581, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda206() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit226, Lit581, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda207() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit584, Lit228, Lit236, Lit147);
    }

    static Object lambda208() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit584, Lit228, Lit236, Lit147);
    }

    static Object lambda209() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit226, Lit587, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda210() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit226, Lit587, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit95, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda211() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit226, Lit590, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit591, Lit592, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit593, Lit35, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit594, Lit413, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit595, Lit596, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit597, Lit258, Lit147);
    }

    static Object lambda212() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit226, Lit590, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit591, Lit592, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit593, Lit35, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit594, Lit413, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit595, Lit596, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit597, Lit258, Lit147);
    }

    public Object scname_list$AfterPicking() {
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit47, Boolean.FALSE, Lit48);
        Object callComponentMethod = C1227runtime.callComponentMethod(Lit210, Lit211, LList.Empty, LList.Empty);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit60, Lit47, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
        Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit60, Lit220, LList.Empty, LList.Empty);
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Boolean.FALSE), Lit599, "=") != Boolean.FALSE) {
            Object callComponentMethod3 = C1227runtime.callComponentMethod(Lit569, Lit570, LList.Empty, LList.Empty);
        }
        Object callComponentMethod4 = C1227runtime.callComponentMethod(Lit31, Lit32, LList.list1(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit14, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.getProperty$1(Lit101, Lit600)), Lit601, "select list item")), Lit602, "select list item")), Lit603);
        SimpleSymbol simpleSymbol = Lit19;
        SimpleSymbol simpleSymbol2 = Lit26;
        SimpleSymbol simpleSymbol3 = Lit604;
        Pair list1 = LList.list1(C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("lat", Lit35), Lit605));
        Pair chain1 = LList.chain1(LList.chain1(LList.chain1(LList.chain4(list1, C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("lng", Lit35), Lit606), Lit607, Lit270, Lit608), Lit86), Lit609), Boolean.FALSE);
        Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(simpleSymbol, C1227runtime.callComponentMethod(simpleSymbol2, simpleSymbol3, list1, Lit610));
        return C1227runtime.callComponentMethod(Lit26, Lit255, LList.list3(C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("lat", Lit35), Lit611), C1227runtime.callComponentMethod(Lit31, Lit37, LList.list2("lng", Lit35), Lit612), Lit613), Lit614);
    }

    static Object lambda213() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit226, Lit618, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit243, Lit35, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit249, Lit35, Lit147);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit250, Lit619, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda214() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit226, Lit618, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit243, Lit35, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit249, Lit35, Lit147);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit250, Lit619, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit97, Lit47, Boolean.FALSE, Lit48);
    }

    public Object down$Click() {
        C1227runtime.setThisForm();
        return C1227runtime.setAndCoerceProperty$Ex(Lit101, Lit621, C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnlength, LList.list1(C1227runtime.getProperty$1(Lit101, Lit102)), Lit622, "length of list"), Lit147);
    }

    static Object lambda215() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit249, Lit35, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit250, Lit625, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda216() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit249, Lit35, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit250, Lit625, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit96, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda217() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit242, Boolean.TRUE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda218() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit242, Boolean.TRUE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda219() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda220() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda221() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit632, Lit383, Lit633, Lit147);
    }

    static Object lambda222() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit632, Lit383, Lit633, Lit147);
    }

    static Object lambda223() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit636, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit636, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit636, Lit228, Lit236, Lit147);
    }

    static Object lambda224() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit636, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit636, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit636, Lit228, Lit236, Lit147);
    }

    static Object lambda225() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit639, Lit278, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit640, "silver", Lit107);
    }

    static Object lambda226() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit639, Lit278, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit640, "silver", Lit107);
    }

    public Object Gmap$OnMarkerClick(Object $markerId, Object $latitude, Object $longitude) {
        Object obj;
        Object obj2;
        Object obj3;
        Object sanitizeComponentData = C1227runtime.sanitizeComponentData($markerId);
        Object sanitizeComponentData2 = C1227runtime.sanitizeComponentData($latitude);
        Object sanitizeComponentData3 = C1227runtime.sanitizeComponentData($longitude);
        Object $markerId2 = sanitizeComponentData;
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit61, C1227runtime.callComponentMethod(Lit26, Lit572, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit19, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit642), Lit107);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
        Object callComponentMethod = C1227runtime.callComponentMethod(Lit210, Lit211, LList.Empty, LList.Empty);
        Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit0, Lit220, LList.Empty, LList.Empty);
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Boolean.FALSE), Lit643, "=") != Boolean.FALSE) {
            Object callComponentMethod3 = C1227runtime.callComponentMethod(Lit569, Lit570, LList.Empty, LList.Empty);
        }
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit303, Lit304, "android.intent.action.DIAL", Lit107);
        SimpleSymbol simpleSymbol = Lit303;
        SimpleSymbol simpleSymbol2 = Lit309;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        ModuleMethod moduleMethod2 = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit15, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        ModuleMethod moduleMethod3 = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment2 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        AddOp addOp = AddOp.$Pl;
        AddOp addOp2 = AddOp.$Mn;
        if ($markerId2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit644);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj = $markerId2;
        }
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, C1227runtime.callYailPrimitive(moduleMethod, LList.list2("tel:", C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(lookupGlobalVarInCurrentFormEnvironment, C1227runtime.callYailPrimitive(moduleMethod3, LList.list2(lookupGlobalVarInCurrentFormEnvironment2, C1227runtime.callYailPrimitive(addOp, LList.list2(C1227runtime.callYailPrimitive(addOp2, LList.list2(obj, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit9, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit645, "-"), Lit35), Lit646, "+")), Lit647, "select list item")), Lit648, "select list item")), Lit649, "join"), Lit107);
        SimpleSymbol simpleSymbol3 = Lit650;
        SimpleSymbol simpleSymbol4 = Lit61;
        ModuleMethod moduleMethod4 = strings.string$Mnappend;
        Object callComponentMethod4 = C1227runtime.callComponentMethod(Lit651, Lit652, LList.list1("<span style=\"font-size: 16px;\">&#xf095;</span>"), Lit653);
        ModuleMethod moduleMethod5 = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment3 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit15, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        ModuleMethod moduleMethod6 = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment4 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        AddOp addOp3 = AddOp.$Pl;
        AddOp addOp4 = AddOp.$Mn;
        if ($markerId2 instanceof Package) {
            Object[] objArr4 = new Object[3];
            objArr4[0] = "The variable ";
            Object[] objArr5 = objArr4;
            objArr5[1] = C1227runtime.getDisplayRepresentation(Lit644);
            Object[] objArr6 = objArr5;
            objArr6[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
        } else {
            obj2 = $markerId2;
        }
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(simpleSymbol3, simpleSymbol4, C1227runtime.callYailPrimitive(moduleMethod4, LList.list3(callComponentMethod4, " +", C1227runtime.callYailPrimitive(moduleMethod5, LList.list2(lookupGlobalVarInCurrentFormEnvironment3, C1227runtime.callYailPrimitive(moduleMethod6, LList.list2(lookupGlobalVarInCurrentFormEnvironment4, C1227runtime.callYailPrimitive(addOp3, LList.list2(C1227runtime.callYailPrimitive(addOp4, LList.list2(obj2, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit9, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit654, "-"), Lit35), Lit655, "+")), Lit656, "select list item")), Lit657, "select list item")), Lit658, "join"), Lit107);
        SimpleSymbol simpleSymbol5 = Lit659;
        SimpleSymbol simpleSymbol6 = Lit102;
        ModuleMethod moduleMethod7 = C1227runtime.string$Mnsplit;
        ModuleMethod moduleMethod8 = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment5 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit16, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        ModuleMethod moduleMethod9 = C1227runtime.yail$Mnlist$Mnget$Mnitem;
        Object lookupGlobalVarInCurrentFormEnvironment6 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
        AddOp addOp5 = AddOp.$Pl;
        AddOp addOp6 = AddOp.$Mn;
        if ($markerId2 instanceof Package) {
            Object[] objArr7 = new Object[3];
            objArr7[0] = "The variable ";
            Object[] objArr8 = objArr7;
            objArr8[1] = C1227runtime.getDisplayRepresentation(Lit644);
            Object[] objArr9 = objArr8;
            objArr9[2] = " is not bound in the current context";
            obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr9), "Unbound Variable");
        } else {
            obj3 = $markerId2;
        }
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(simpleSymbol5, simpleSymbol6, C1227runtime.callYailPrimitive(moduleMethod7, LList.list2(C1227runtime.callYailPrimitive(moduleMethod8, LList.list2(lookupGlobalVarInCurrentFormEnvironment5, C1227runtime.callYailPrimitive(moduleMethod9, LList.list2(lookupGlobalVarInCurrentFormEnvironment6, C1227runtime.callYailPrimitive(addOp5, LList.list2(C1227runtime.callYailPrimitive(addOp6, LList.list2(obj3, C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit9, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit660, "-"), Lit35), Lit661, "+")), Lit662, "select list item")), Lit663, "select list item"), "$$"), Lit664, "split"), Lit103);
        return C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.TRUE, Lit48);
    }

    public Object Gmap$MapIsReady() {
        C1227runtime.setThisForm();
        return C1227runtime.callComponentMethod(Lit26, Lit667, LList.list1(Boolean.TRUE), Lit668);
    }

    public Object Gmap$OnMapClick(Object $lat, Object $lng) {
        Object sanitizeComponentData = C1227runtime.sanitizeComponentData($lat);
        Object sanitizeComponentData2 = C1227runtime.sanitizeComponentData($lng);
        C1227runtime.setThisForm();
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Boolean.FALSE), Lit671, "=") != Boolean.FALSE) {
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit569, Lit570, LList.Empty, LList.Empty);
        }
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit61, C1227runtime.callComponentMethod(Lit26, Lit572, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit19, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit672), Lit107);
        Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit210, Lit211, LList.Empty, LList.Empty);
        Object callComponentMethod3 = C1227runtime.callComponentMethod(Lit60, Lit220, LList.Empty, LList.Empty);
        return C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda227() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit226, Lit677, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit228, Lit678, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit679, "baki.png", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda228() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit226, Lit677, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit228, Lit678, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit679, "baki.png", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda229() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit226, Lit683, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit228, Lit439, Lit147);
    }

    static Object lambda230() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit225, Lit86, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit226, Lit683, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit682, Lit228, Lit439, Lit147);
    }

    static Object lambda231() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit226, Lit686, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit687, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit243, Lit291, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit688, Lit689, Lit147);
        Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit292, Lit35, Lit147);
        Object andCoerceProperty$Ex10 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit61, "&#xf054;", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit250, Lit690, Lit147);
    }

    static Object lambda232() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit226, Lit686, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit687, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit243, Lit291, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit688, Lit689, Lit147);
        Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit292, Lit35, Lit147);
        Object andCoerceProperty$Ex10 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit61, "&#xf054;", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit250, Lit690, Lit147);
    }

    public Object flip$Click() {
        Object addGlobalVarToCurrentFormEnvironment;
        C1227runtime.setThisForm();
        if (C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C1227runtime.$Stthe$Mnnull$Mnvalue$St) != Boolean.FALSE) {
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit571, Lit61, C1227runtime.callComponentMethod(Lit26, Lit572, LList.list1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit19, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit692), Lit107);
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit210, Lit211, LList.Empty, LList.Empty);
            Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit0, Lit220, LList.Empty, LList.Empty);
            Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit61, "&#xf053;", Lit107);
            SimpleSymbol simpleSymbol = Lit693;
            SimpleSymbol simpleSymbol2 = Lit694;
            Pair list1 = LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit676));
            Pair chain4 = LList.chain4(list1, C1227runtime.callYailPrimitive(AddOp.$Mn, LList.list2(C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit682)), Lit696), C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit697)), Lit698)), Lit699, "-"), C1227runtime.callYailPrimitive(AddOp.$Mn, LList.list2(C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit682)), Lit700), C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit701)), Lit702)), Lit703, "-"), Lit704, Lit35);
            Object callComponentMethod3 = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, list1, Lit705);
            addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit3, Boolean.FALSE);
        } else {
            Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit569, Lit61, "&#xf054;", Lit107);
            SimpleSymbol simpleSymbol3 = Lit693;
            SimpleSymbol simpleSymbol4 = Lit694;
            Pair list12 = LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit676));
            Pair chain42 = LList.chain4(list12, C1227runtime.callYailPrimitive(AddOp.$Mn, LList.list2(C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit682)), Lit706), C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit701)), Lit707)), Lit708, "-"), C1227runtime.callYailPrimitive(AddOp.$Mn, LList.list2(C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit682)), Lit709), C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit697)), Lit710)), Lit711, "-"), Lit704, Lit35);
            Object callComponentMethod4 = C1227runtime.callComponentMethod(simpleSymbol3, simpleSymbol4, list12, Lit712);
            addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit3, Boolean.TRUE);
        }
        return addGlobalVarToCurrentFormEnvironment;
    }

    static Object lambda233() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit697, Lit226, Lit715, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit697, Lit228, Lit236, Lit147);
    }

    static Object lambda234() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit697, Lit226, Lit715, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit697, Lit228, Lit236, Lit147);
    }

    static Object lambda235() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit719, Lit720, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit61, "Services for Corona Treatment", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit250, Lit722, Lit147);
    }

    static Object lambda236() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit719, Lit720, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit61, "Services for Corona Treatment", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit718, Lit250, Lit722, Lit147);
    }

    public Object yes$Changed() {
        Object apply1;
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.TRUE, Lit48);
        if (C1227runtime.getProperty$1(Lit718, Lit721) != Boolean.FALSE) {
            Object callYailPrimitive = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit35, "350"), Lit724, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        } else {
            Object callYailPrimitive2 = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit35, "0"), Lit725, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        }
        return apply1;
    }

    static Object lambda237() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit729, Lit719, Lit730, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit729, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit729, Lit61, "Services for Corona Testing ", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit729, Lit250, Lit731, Lit147);
    }

    static Object lambda238() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit729, Lit719, Lit730, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit729, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit729, Lit61, "Services for Corona Testing ", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit729, Lit250, Lit731, Lit147);
    }

    public Object test$Changed() {
        Object apply1;
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.TRUE, Lit48);
        if (C1227runtime.getProperty$1(Lit729, Lit721) != Boolean.FALSE) {
            Object callYailPrimitive = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit86, "210"), Lit733, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        } else {
            Object callYailPrimitive2 = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit86, "0"), Lit734, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        }
        return apply1;
    }

    static Object lambda239() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit737, Lit719, Lit738, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit737, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit737, Lit61, "Services not for Corona Treatment", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit737, Lit250, Lit739, Lit147);
    }

    static Object lambda240() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit737, Lit719, Lit738, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit737, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit737, Lit61, "Services not for Corona Treatment", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit737, Lit250, Lit739, Lit147);
    }

    public Object no$Changed() {
        Object apply1;
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.TRUE, Lit48);
        if (C1227runtime.getProperty$1(Lit737, Lit721) != Boolean.FALSE) {
            Object callYailPrimitive = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit105, "140"), Lit741, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        } else {
            Object callYailPrimitive2 = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit105, "0"), Lit742, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        }
        return apply1;
    }

    static Object lambda241() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit745, Lit719, Lit746, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit745, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit745, Lit61, "Under Construction For Corona Treatment", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit745, Lit250, Lit747, Lit147);
    }

    static Object lambda242() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit745, Lit719, Lit746, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit745, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit745, Lit61, "Under Construction For Corona Treatment", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit745, Lit250, Lit747, Lit147);
    }

    public Object up$Changed() {
        Object apply1;
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.TRUE, Lit48);
        if (C1227runtime.getProperty$1(Lit745, Lit721) != Boolean.FALSE) {
            Object callYailPrimitive = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit181, "280"), Lit749, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        } else {
            Object callYailPrimitive2 = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit181, "0"), Lit750, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        }
        return apply1;
    }

    static Object lambda243() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit753, Lit719, Lit754, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit753, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit753, Lit61, "Hospitals under Lock down", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit753, Lit250, Lit755, Lit147);
    }

    static Object lambda244() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit753, Lit719, Lit754, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit753, Lit721, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit753, Lit61, "Hospitals under Lock down", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit753, Lit250, Lit755, Lit147);
    }

    public Object lock$Changed() {
        Object apply1;
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Boolean.TRUE, Lit48);
        if (C1227runtime.getProperty$1(Lit753, Lit721) != Boolean.FALSE) {
            Object callYailPrimitive = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit413, "40"), Lit757, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        } else {
            Object callYailPrimitive2 = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnset$Mnitem$Ex, LList.list3(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit413, "0"), Lit758, "replace list item");
            apply1 = Scheme.applyToArgs.apply1(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit25, C1227runtime.$Stthe$Mnnull$Mnvalue$St));
        }
        return apply1;
    }

    static Object lambda245() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit761, Lit228, Lit413, Lit147);
    }

    static Object lambda246() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit761, Lit228, Lit413, Lit147);
    }

    static Object lambda247() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit701, Lit228, Lit175, Lit147);
    }

    static Object lambda248() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit701, Lit228, Lit175, Lit147);
    }

    static Object lambda249() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit210, Lit563, "Hint for Text_Box1", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit210, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda250() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit210, Lit563, "Hint for Text_Box1", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit210, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda251() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit224, Lit86, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit226, Lit768, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit235, Lit769, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda252() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit224, Lit86, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit226, Lit768, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit235, Lit769, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit228, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda253() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit61, "&#xf00d;", Lit107);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit249, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit250, Lit773, Lit147);
    }

    static Object lambda254() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit61, "&#xf00d;", Lit107);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit249, Lit86, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit772, Lit250, Lit773, Lit147);
    }

    public Object HideMarkerPop$Click() {
        C1227runtime.setThisForm();
        return C1227runtime.setAndCoerceProperty$Ex(Lit212, Lit47, Boolean.FALSE, Lit48);
    }

    static Object lambda255() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit226, Lit778, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit228, Lit236, Lit147);
    }

    static Object lambda256() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit224, Lit105, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit226, Lit778, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit235, Lit236, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit777, Lit228, Lit236, Lit147);
    }

    static Object lambda257() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit226, Lit781, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit243, Lit782, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit688, Lit5, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit292, Lit35, Lit147);
    }

    static Object lambda258() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit226, Lit781, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit243, Lit782, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit688, Lit5, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit650, Lit292, Lit35, Lit147);
    }

    public Object Phn_btn$Click() {
        C1227runtime.setThisForm();
        return C1227runtime.callComponentMethod(Lit303, Lit305, LList.Empty, LList.Empty);
    }

    static Object lambda259() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit786, Lit235, Lit787, Lit147);
    }

    static Object lambda260() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit786, Lit235, Lit787, Lit147);
    }

    static Object lambda261() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit226, Lit790, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit591, Lit791, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit593, Lit5, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit594, Lit246, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit595, Lit792, Lit147);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit793, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit597, Lit291, Lit147);
    }

    static Object lambda262() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit226, Lit790, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit591, Lit791, Lit147);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit593, Lit5, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit235, Lit236, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit594, Lit246, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit595, Lit792, Lit147);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit793, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit249, Lit35, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit597, Lit291, Lit147);
    }

    static Object lambda263() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit226, Lit797, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit235, Lit607, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit61, "&#xf078;", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit249, Lit35, Lit147);
    }

    static Object lambda264() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit226, Lit797, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit242, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit243, Lit278, Lit147);
        Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit245, Lit246, Lit147);
        Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit247, Boolean.TRUE, Lit48);
        Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit235, Lit607, Lit147);
        Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit228, Lit236, Lit147);
        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit61, "&#xf078;", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit796, Lit249, Lit35, Lit147);
    }

    public Object scrolllist$Click() {
        C1227runtime.setThisForm();
        return C1227runtime.setAndCoerceProperty$Ex(Lit659, Lit621, C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnlength, LList.list1(C1227runtime.getProperty$1(Lit659, Lit102)), Lit799, "length of list"), Lit147);
    }

    static Object lambda265() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit176, Lit226, Lit802, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit176, Lit803, Lit804, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit176, Lit805, Lit806, Lit147);
    }

    static Object lambda266() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit176, Lit226, Lit802, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit176, Lit803, Lit804, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit176, Lit805, Lit806, Lit147);
    }

    public Object Tabs$ItemSelected(Object $id, Object $title) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object sanitizeComponentData = C1227runtime.sanitizeComponentData($id);
        Object $title2 = C1227runtime.sanitizeComponentData($title);
        C1227runtime.setThisForm();
        ModuleMethod moduleMethod = C1227runtime.yail$Mnequal$Qu;
        if ($title2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit808);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj = $title2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod, LList.list2(obj, "Home"), Lit809, "=") != Boolean.FALSE) {
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit0, Lit220, LList.Empty, LList.Empty);
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Boolean.FALSE, Lit48);
        }
        ModuleMethod moduleMethod2 = C1227runtime.yail$Mnequal$Qu;
        if ($title2 instanceof Package) {
            Object[] objArr4 = new Object[3];
            objArr4[0] = "The variable ";
            Object[] objArr5 = objArr4;
            objArr5[1] = C1227runtime.getDisplayRepresentation(Lit808);
            Object[] objArr6 = objArr5;
            objArr6[2] = " is not bound in the current context";
            obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
        } else {
            obj2 = $title2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(obj2, "Emergency"), Lit810, "=") != Boolean.FALSE) {
            Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit186, Lit811, LList.list1("http://covidease.epizy.com/emergency.php"), Lit812);
            Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
            Object callComponentMethod3 = C1227runtime.callComponentMethod(Lit0, Lit220, LList.Empty, LList.Empty);
            Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex10 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Boolean.FALSE, Lit48);
        }
        ModuleMethod moduleMethod3 = C1227runtime.yail$Mnequal$Qu;
        if ($title2 instanceof Package) {
            Object[] objArr7 = new Object[3];
            objArr7[0] = "The variable ";
            Object[] objArr8 = objArr7;
            objArr8[1] = C1227runtime.getDisplayRepresentation(Lit808);
            Object[] objArr9 = objArr8;
            objArr9[2] = " is not bound in the current context";
            obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr9), "Unbound Variable");
        } else {
            obj3 = $title2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod3, LList.list2(obj3, "Information"), Lit813, "=") != Boolean.FALSE) {
            Object callComponentMethod4 = C1227runtime.callComponentMethod(Lit185, Lit811, LList.list1("http://covidease.epizy.com/info.php"), Lit814);
            Object andCoerceProperty$Ex11 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
            Object callComponentMethod5 = C1227runtime.callComponentMethod(Lit0, Lit220, LList.Empty, LList.Empty);
            Object andCoerceProperty$Ex12 = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex13 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex14 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex15 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Boolean.FALSE, Lit48);
        }
        ModuleMethod moduleMethod4 = C1227runtime.yail$Mnequal$Qu;
        if ($title2 instanceof Package) {
            Object[] objArr10 = new Object[3];
            objArr10[0] = "The variable ";
            Object[] objArr11 = objArr10;
            objArr11[1] = C1227runtime.getDisplayRepresentation(Lit808);
            Object[] objArr12 = objArr11;
            objArr12[2] = " is not bound in the current context";
            obj4 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr12), "Unbound Variable");
        } else {
            obj4 = $title2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod4, LList.list2(obj4, "News"), Lit815, "=") != Boolean.FALSE) {
            Object callComponentMethod6 = C1227runtime.callComponentMethod(Lit183, Lit811, LList.list1("http://covidease.epizy.com/news.php"), Lit816);
            Object andCoerceProperty$Ex16 = C1227runtime.setAndCoerceProperty$Ex(Lit98, Lit47, Boolean.FALSE, Lit48);
            Object callComponentMethod7 = C1227runtime.callComponentMethod(Lit0, Lit220, LList.Empty, LList.Empty);
            Object andCoerceProperty$Ex17 = C1227runtime.setAndCoerceProperty$Ex(Lit209, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex18 = C1227runtime.setAndCoerceProperty$Ex(Lit205, Lit47, Boolean.FALSE, Lit48);
            Object andCoerceProperty$Ex19 = C1227runtime.setAndCoerceProperty$Ex(Lit207, Lit47, Boolean.FALSE, Lit48);
            obj5 = C1227runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Boolean.TRUE, Lit48);
        } else {
            obj5 = Values.empty;
        }
        return obj5;
    }

    static Object lambda267() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit166, Lit226, Lit820, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit166, Lit821, Boolean.TRUE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit166, Lit822, Boolean.FALSE, Lit48);
    }

    static Object lambda268() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit166, Lit226, Lit820, Lit147);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit166, Lit821, Boolean.TRUE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit166, Lit822, Boolean.FALSE, Lit48);
    }

    static Object lambda269() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit832, "https://makeroid-default-firebase.firebaseio.com/", Lit107);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit833, "fz:arnob@gmail:com/", Lit107);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit834, "AIzaSyBM4we-oHwaQgGSjqPMupffzyE8rMHsUoY", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit835, "https://covidease-db.firebaseio.com/", Lit107);
    }

    static Object lambda270() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit832, "https://makeroid-default-firebase.firebaseio.com/", Lit107);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit833, "fz:arnob@gmail:com/", Lit107);
        Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit834, "AIzaSyBM4we-oHwaQgGSjqPMupffzyE8rMHsUoY", Lit107);
        return C1227runtime.setAndCoerceProperty$Ex(Lit189, Lit835, "https://covidease-db.firebaseio.com/", Lit107);
    }

    /* renamed from: io.kodular.fz_arnob.CovidEaseV1_2107.Screen1$frame2 */
    /* compiled from: Screen1.yail */
    public class frame2 extends ModuleBody {
        Object $value;
        final ModuleMethod lambda$Fn270;
        final ModuleMethod lambda$Fn271;

        public frame2() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 3, (Object) null, 0);
            this.lambda$Fn270 = moduleMethod;
            new ModuleMethod(this, 4, (Object) null, 0);
            this.lambda$Fn271 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            switch (moduleMethod2.selector) {
                case 3:
                    return lambda271();
                case 4:
                    return lambda272();
                default:
                    return super.apply0(moduleMethod2);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 3:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 4:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod2, callContext2);
            }
        }

        static Object lambda273(Object obj) {
            Object obj2;
            Object obj3;
            Object obj4;
            Object $number = obj;
            SimpleSymbol simpleSymbol = Screen1.Lit189;
            SimpleSymbol simpleSymbol2 = Screen1.Lit191;
            ModuleMethod moduleMethod = strings.string$Mnappend;
            if ($number instanceof Package) {
                Object[] objArr = new Object[3];
                objArr[0] = "The variable ";
                Object[] objArr2 = objArr;
                objArr2[1] = C1227runtime.getDisplayRepresentation(Screen1.Lit845);
                Object[] objArr3 = objArr2;
                objArr3[2] = " is not bound in the current context";
                obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
            } else {
                obj2 = $number;
            }
            Object callComponentMethod = C1227runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list2(C1227runtime.callYailPrimitive(moduleMethod, LList.list3("h", obj2, "json"), Screen1.Lit846, "join"), NotificationCompat.CATEGORY_ERROR), Screen1.Lit847);
            SimpleSymbol simpleSymbol3 = Screen1.Lit189;
            SimpleSymbol simpleSymbol4 = Screen1.Lit191;
            ModuleMethod moduleMethod2 = strings.string$Mnappend;
            if ($number instanceof Package) {
                Object[] objArr4 = new Object[3];
                objArr4[0] = "The variable ";
                Object[] objArr5 = objArr4;
                objArr5[1] = C1227runtime.getDisplayRepresentation(Screen1.Lit845);
                Object[] objArr6 = objArr5;
                objArr6[2] = " is not bound in the current context";
                obj3 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
            } else {
                obj3 = $number;
            }
            Object callComponentMethod2 = C1227runtime.callComponentMethod(simpleSymbol3, simpleSymbol4, LList.list2(C1227runtime.callYailPrimitive(moduleMethod2, LList.list3("h", obj3, "phone"), Screen1.Lit848, "join"), NotificationCompat.CATEGORY_ERROR), Screen1.Lit849);
            SimpleSymbol simpleSymbol5 = Screen1.Lit189;
            SimpleSymbol simpleSymbol6 = Screen1.Lit191;
            ModuleMethod moduleMethod3 = strings.string$Mnappend;
            if ($number instanceof Package) {
                Object[] objArr7 = new Object[3];
                objArr7[0] = "The variable ";
                Object[] objArr8 = objArr7;
                objArr8[1] = C1227runtime.getDisplayRepresentation(Screen1.Lit845);
                Object[] objArr9 = objArr8;
                objArr9[2] = " is not bound in the current context";
                obj4 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr9), "Unbound Variable");
            } else {
                obj4 = $number;
            }
            return C1227runtime.callComponentMethod(simpleSymbol5, simpleSymbol6, LList.list2(C1227runtime.callYailPrimitive(moduleMethod3, LList.list3("h", obj4, "details"), Screen1.Lit850, "join"), NotificationCompat.CATEGORY_ERROR), Screen1.Lit851);
        }

        /* access modifiers changed from: package-private */
        public Object lambda271() {
            Object obj;
            ModuleMethod moduleMethod = C1227runtime.yail$Mnnot$Mnequal$Qu;
            if (this.$value instanceof Package) {
                Object[] objArr = new Object[3];
                objArr[0] = "The variable ";
                Object[] objArr2 = objArr;
                objArr2[1] = C1227runtime.getDisplayRepresentation(Screen1.Lit839);
                Object[] objArr3 = objArr2;
                objArr3[2] = " is not bound in the current context";
                obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
            } else {
                obj = this.$value;
            }
            return C1227runtime.callYailPrimitive(moduleMethod, LList.list2(obj, NotificationCompat.CATEGORY_ERROR), Screen1.Lit842, "=");
        }

        /* access modifiers changed from: package-private */
        public Object lambda272() {
            Object obj;
            ModuleMethod moduleMethod = C1227runtime.yail$Mnnot$Mnequal$Qu;
            if (this.$value instanceof Package) {
                Object[] objArr = new Object[3];
                objArr[0] = "The variable ";
                Object[] objArr2 = objArr;
                objArr2[1] = C1227runtime.getDisplayRepresentation(Screen1.Lit839);
                Object[] objArr3 = objArr2;
                objArr3[2] = " is not bound in the current context";
                obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
            } else {
                obj = this.$value;
            }
            return C1227runtime.callYailPrimitive(moduleMethod, LList.list2(obj, "false"), Screen1.Lit843, "=");
        }
    }

    public Object Firebase_Database1$GotValue(Object $tag, Object $value) {
        frame2 frame22;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        Object obj12;
        Object obj13;
        Object obj14;
        new frame2();
        frame2 frame23 = frame22;
        Object sanitizeComponentData = C1227runtime.sanitizeComponentData($tag);
        frame23.$value = C1227runtime.sanitizeComponentData($value);
        Object $tag2 = sanitizeComponentData;
        C1227runtime.setThisForm();
        ModuleMethod moduleMethod = C1227runtime.yail$Mnequal$Qu;
        if ($tag2 instanceof Package) {
            Object[] objArr = new Object[3];
            objArr[0] = "The variable ";
            Object[] objArr2 = objArr;
            objArr2[1] = C1227runtime.getDisplayRepresentation(Lit837);
            Object[] objArr3 = objArr2;
            objArr3[2] = " is not bound in the current context";
            obj = C1227runtime.signalRuntimeError(strings.stringAppend(objArr3), "Unbound Variable");
        } else {
            obj = $tag2;
        }
        if (C1227runtime.callYailPrimitive(moduleMethod, LList.list2(obj, "update"), Lit838, "=") != Boolean.FALSE) {
            ModuleMethod moduleMethod2 = C1227runtime.yail$Mnequal$Qu;
            if (frame23.$value instanceof Package) {
                Object[] objArr4 = new Object[3];
                objArr4[0] = "The variable ";
                Object[] objArr5 = objArr4;
                objArr5[1] = C1227runtime.getDisplayRepresentation(Lit839);
                Object[] objArr6 = objArr5;
                objArr6[2] = " is not bound in the current context";
                obj14 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr6), "Unbound Variable");
            } else {
                obj14 = frame23.$value;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod2, LList.list2(obj14, "1"), Lit840, "=") != Boolean.FALSE) {
                Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit61, "Please Update from play store.", Lit107);
                Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Update", Lit107);
                Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit47, Boolean.TRUE, Lit48);
                Object callComponentMethod = C1227runtime.callComponentMethod(Lit166, Lit193, LList.Empty, LList.Empty);
            } else {
                Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit189, Lit191, LList.list2("count", NotificationCompat.CATEGORY_ERROR), Lit841);
            }
        }
        Object[] objArr7 = new Object[2];
        objArr7[0] = frame23.lambda$Fn270;
        Object[] objArr8 = objArr7;
        objArr8[1] = frame23.lambda$Fn271;
        if (C1227runtime.processAndDelayed$V(objArr8) != Boolean.FALSE) {
            ModuleMethod moduleMethod3 = C1227runtime.yail$Mnequal$Qu;
            if ($tag2 instanceof Package) {
                Object[] objArr9 = new Object[3];
                objArr9[0] = "The variable ";
                Object[] objArr10 = objArr9;
                objArr10[1] = C1227runtime.getDisplayRepresentation(Lit837);
                Object[] objArr11 = objArr10;
                objArr11[2] = " is not bound in the current context";
                obj4 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr11), "Unbound Variable");
            } else {
                obj4 = $tag2;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod3, LList.list2(obj4, "count"), Lit844, "=") != Boolean.FALSE) {
                Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit174, Lit244, Lit147);
                SimpleSymbol simpleSymbol = Lit4;
                if (frame23.$value instanceof Package) {
                    Object[] objArr12 = new Object[3];
                    objArr12[0] = "The variable ";
                    Object[] objArr13 = objArr12;
                    objArr13[1] = C1227runtime.getDisplayRepresentation(Lit839);
                    Object[] objArr14 = objArr13;
                    objArr14[2] = " is not bound in the current context";
                    obj12 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr14), "Unbound Variable");
                } else {
                    obj12 = frame23.$value;
                }
                Object addGlobalVarToCurrentFormEnvironment = C1227runtime.addGlobalVarToCurrentFormEnvironment(simpleSymbol, obj12);
                ModuleMethod moduleMethod4 = lambda$Fn272;
                IntNum intNum = Lit35;
                if (frame23.$value instanceof Package) {
                    Object[] objArr15 = new Object[3];
                    objArr15[0] = "The variable ";
                    Object[] objArr16 = objArr15;
                    objArr16[1] = C1227runtime.getDisplayRepresentation(Lit839);
                    Object[] objArr17 = objArr16;
                    objArr17[2] = " is not bound in the current context";
                    obj13 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr17), "Unbound Variable");
                } else {
                    obj13 = frame23.$value;
                }
                Object yailForRange = C1227runtime.yailForRange(moduleMethod4, intNum, obj13, Lit35);
                Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit174, Lit439, Lit147);
            }
            ModuleMethod moduleMethod5 = C1227runtime.string$Mncontains;
            if ($tag2 instanceof Package) {
                Object[] objArr18 = new Object[3];
                objArr18[0] = "The variable ";
                Object[] objArr19 = objArr18;
                objArr19[1] = C1227runtime.getDisplayRepresentation(Lit837);
                Object[] objArr20 = objArr19;
                objArr20[2] = " is not bound in the current context";
                obj5 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr20), "Unbound Variable");
            } else {
                obj5 = $tag2;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod5, LList.list2(obj5, "json"), Lit852, "contains") != Boolean.FALSE) {
                Object addGlobalVarToCurrentFormEnvironment2 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit6, C1227runtime.callYailPrimitive(AddOp.$Pl, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit6, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Lit35), Lit853, "+"));
                ModuleMethod moduleMethod6 = C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
                Object lookupGlobalVarInCurrentFormEnvironment = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit13, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
                if (frame23.$value instanceof Package) {
                    Object[] objArr21 = new Object[3];
                    objArr21[0] = "The variable ";
                    Object[] objArr22 = objArr21;
                    objArr22[1] = C1227runtime.getDisplayRepresentation(Lit839);
                    Object[] objArr23 = objArr22;
                    objArr23[2] = " is not bound in the current context";
                    obj10 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr23), "Unbound Variable");
                } else {
                    obj10 = frame23.$value;
                }
                Object callYailPrimitive = C1227runtime.callYailPrimitive(moduleMethod6, LList.list2(lookupGlobalVarInCurrentFormEnvironment, obj10), Lit854, "add items to list");
                Object callYailPrimitive2 = C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit18, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit6, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit855, "add items to list");
                SimpleSymbol simpleSymbol2 = Lit26;
                SimpleSymbol simpleSymbol3 = Lit41;
                if (frame23.$value instanceof Package) {
                    Object[] objArr24 = new Object[3];
                    objArr24[0] = "The variable ";
                    Object[] objArr25 = objArr24;
                    objArr25[1] = C1227runtime.getDisplayRepresentation(Lit839);
                    Object[] objArr26 = objArr25;
                    objArr26[2] = " is not bound in the current context";
                    obj11 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr26), "Unbound Variable");
                } else {
                    obj11 = frame23.$value;
                }
                Object callComponentMethod3 = C1227runtime.callComponentMethod(simpleSymbol2, simpleSymbol3, LList.list1(obj11), Lit856);
                if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit7, C1227runtime.$Stthe$Mnnull$Mnvalue$St), Boolean.TRUE), Lit857, "=") != Boolean.FALSE) {
                    Object addGlobalVarToCurrentFormEnvironment3 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit9, C1227runtime.callYailPrimitive(C1227runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C1227runtime.callComponentMethod(Lit26, Lit30, LList.Empty, LList.Empty), Lit35), Lit858, "select list item"));
                    Object addGlobalVarToCurrentFormEnvironment4 = C1227runtime.addGlobalVarToCurrentFormEnvironment(Lit7, Boolean.FALSE);
                }
                if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit6, C1227runtime.$Stthe$Mnnull$Mnvalue$St), C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit4, C1227runtime.$Stthe$Mnnull$Mnvalue$St)), Lit859, "=") != Boolean.FALSE) {
                    Object andCoerceProperty$Ex6 = C1227runtime.setAndCoerceProperty$Ex(Lit173, Lit174, Lit860, Lit147);
                    Object andCoerceProperty$Ex7 = C1227runtime.setAndCoerceProperty$Ex(Lit861, Lit862, "COVIDEASE", Lit107);
                    if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.callComponentMethod(Lit861, Lit191, LList.list2("useage", "new"), Lit863), "old"), Lit864, "=") != Boolean.FALSE) {
                        Object andCoerceProperty$Ex8 = C1227runtime.setAndCoerceProperty$Ex(Lit261, Lit47, Boolean.FALSE, Lit48);
                        Object andCoerceProperty$Ex9 = C1227runtime.setAndCoerceProperty$Ex(Lit262, Lit47, Boolean.TRUE, Lit48);
                        Object andCoerceProperty$Ex10 = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit198, Boolean.TRUE, Lit48);
                        Object andCoerceProperty$Ex11 = C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit198, Boolean.TRUE, Lit48);
                    } else {
                        Object callComponentMethod4 = C1227runtime.callComponentMethod(Lit861, Lit865, LList.list2("useage", "old"), Lit866);
                        Object andCoerceProperty$Ex12 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit61, "You can watch our Manual video<br>and touch &#xf05b; on Top-Right corner for your current location.", Lit107);
                        Object andCoerceProperty$Ex13 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Watch Video", Lit107);
                        Object andCoerceProperty$Ex14 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit47, Boolean.TRUE, Lit48);
                        Object andCoerceProperty$Ex15 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit47, Boolean.TRUE, Lit48);
                        Object callComponentMethod5 = C1227runtime.callComponentMethod(Lit166, Lit193, LList.Empty, LList.Empty);
                    }
                }
            }
            ModuleMethod moduleMethod7 = C1227runtime.string$Mncontains;
            if ($tag2 instanceof Package) {
                Object[] objArr27 = new Object[3];
                objArr27[0] = "The variable ";
                Object[] objArr28 = objArr27;
                objArr28[1] = C1227runtime.getDisplayRepresentation(Lit837);
                Object[] objArr29 = objArr28;
                objArr29[2] = " is not bound in the current context";
                obj6 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr29), "Unbound Variable");
            } else {
                obj6 = $tag2;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod7, LList.list2(obj6, "phone"), Lit867, "contains") != Boolean.FALSE) {
                ModuleMethod moduleMethod8 = C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
                Object lookupGlobalVarInCurrentFormEnvironment2 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit15, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
                if (frame23.$value instanceof Package) {
                    Object[] objArr30 = new Object[3];
                    objArr30[0] = "The variable ";
                    Object[] objArr31 = objArr30;
                    objArr31[1] = C1227runtime.getDisplayRepresentation(Lit839);
                    Object[] objArr32 = objArr31;
                    objArr32[2] = " is not bound in the current context";
                    obj9 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr32), "Unbound Variable");
                } else {
                    obj9 = frame23.$value;
                }
                Object callYailPrimitive3 = C1227runtime.callYailPrimitive(moduleMethod8, LList.list2(lookupGlobalVarInCurrentFormEnvironment2, obj9), Lit868, "add items to list");
            }
            ModuleMethod moduleMethod9 = C1227runtime.string$Mncontains;
            if ($tag2 instanceof Package) {
                Object[] objArr33 = new Object[3];
                objArr33[0] = "The variable ";
                Object[] objArr34 = objArr33;
                objArr34[1] = C1227runtime.getDisplayRepresentation(Lit837);
                Object[] objArr35 = objArr34;
                objArr35[2] = " is not bound in the current context";
                obj7 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr35), "Unbound Variable");
            } else {
                obj7 = $tag2;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod9, LList.list2(obj7, "details"), Lit869, "contains") != Boolean.FALSE) {
                ModuleMethod moduleMethod10 = C1227runtime.yail$Mnlist$Mnadd$Mnto$Mnlist$Ex;
                Object lookupGlobalVarInCurrentFormEnvironment3 = C1227runtime.lookupGlobalVarInCurrentFormEnvironment(Lit16, C1227runtime.$Stthe$Mnnull$Mnvalue$St);
                if (frame23.$value instanceof Package) {
                    Object[] objArr36 = new Object[3];
                    objArr36[0] = "The variable ";
                    Object[] objArr37 = objArr36;
                    objArr37[1] = C1227runtime.getDisplayRepresentation(Lit839);
                    Object[] objArr38 = objArr37;
                    objArr38[2] = " is not bound in the current context";
                    obj8 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr38), "Unbound Variable");
                } else {
                    obj8 = frame23.$value;
                }
                obj3 = C1227runtime.callYailPrimitive(moduleMethod10, LList.list2(lookupGlobalVarInCurrentFormEnvironment3, obj8), Lit870, "add items to list");
            } else {
                obj3 = Values.empty;
            }
        } else {
            ModuleMethod moduleMethod11 = C1227runtime.yail$Mnequal$Qu;
            if (frame23.$value instanceof Package) {
                Object[] objArr39 = new Object[3];
                objArr39[0] = "The variable ";
                Object[] objArr40 = objArr39;
                objArr40[1] = C1227runtime.getDisplayRepresentation(Lit839);
                Object[] objArr41 = objArr40;
                objArr41[2] = " is not bound in the current context";
                obj2 = C1227runtime.signalRuntimeError(strings.stringAppend(objArr41), "Unbound Variable");
            } else {
                obj2 = frame23.$value;
            }
            if (C1227runtime.callYailPrimitive(moduleMethod11, LList.list2(obj2, NotificationCompat.CATEGORY_ERROR), Lit871, "=") != Boolean.FALSE) {
                Object andCoerceProperty$Ex16 = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit61, "Dtabase Error!", Lit107);
                Object andCoerceProperty$Ex17 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit47, Boolean.TRUE, Lit48);
                obj3 = C1227runtime.callComponentMethod(Lit166, Lit193, LList.Empty, LList.Empty);
            } else {
                obj3 = Values.empty;
            }
        }
        return obj3;
    }

    static Object lambda274() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit878, Lit175, Lit147);
    }

    static Object lambda275() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit878, Lit175, Lit147);
    }

    public Object float_clk$Timer() {
        Object obj;
        C1227runtime.setThisForm();
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnnot$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit26, Lit228), Lit5), Lit880, "=") != Boolean.FALSE) {
            Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit26, Lit228, C1227runtime.getProperty$1(Lit26, Lit228), Lit147);
            Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit676, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit210, Lit47, Boolean.TRUE, Lit48);
            Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit881, Lit198, Boolean.TRUE, Lit48);
            obj = C1227runtime.setAndCoerceProperty$Ex(Lit263, Lit198, Boolean.FALSE, Lit48);
        } else {
            obj = Values.empty;
        }
        return obj;
    }

    static Object lambda276() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit881, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit881, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit881, Lit878, Lit175, Lit147);
    }

    static Object lambda277() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit881, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit881, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit881, Lit878, Lit175, Lit147);
    }

    public Object float_ckl1$Timer() {
        Object obj;
        C1227runtime.setThisForm();
        if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnnot$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit682, Lit228), Lit5), Lit888, "=") != Boolean.FALSE) {
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit693, Lit889, LList.list4(C1227runtime.lookupInCurrentFormEnvironment(Lit676), Lit5, C1227runtime.callYailPrimitive(AddOp.$Mn, LList.list2(C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit682)), Lit890), C1227runtime.callComponentMethod(Lit693, Lit695, LList.list1(C1227runtime.lookupInCurrentFormEnvironment(Lit697)), Lit891)), Lit892, "-"), Lit893), Lit894);
            obj = C1227runtime.setAndCoerceProperty$Ex(Lit881, Lit198, Boolean.FALSE, Lit48);
        } else {
            obj = Values.empty;
        }
        return obj;
    }

    static Object lambda278() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit878, Lit175, Lit147);
    }

    static Object lambda279() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit878, Lit175, Lit147);
    }

    public Object location_clk$Timer() {
        Object obj;
        C1227runtime.setThisForm();
        if (C1227runtime.callComponentMethod(Lit898, Lit899, LList.list2("android.permission.ACCESS_FINE_LOCATION", C1227runtime.getProperty$1(Lit898, Lit157)), Lit900) != Boolean.FALSE) {
            Object callComponentMethod = C1227runtime.callComponentMethod(Lit26, Lit901, LList.list1(Boolean.TRUE), Lit902);
            if (C1227runtime.callYailPrimitive(C1227runtime.yail$Mnnot, LList.list1(C1227runtime.callYailPrimitive(C1227runtime.yail$Mnequal$Qu, LList.list2(C1227runtime.getProperty$1(Lit903, Lit904), "passive"), Lit905, "=")), Lit906, "not") != Boolean.FALSE) {
                Object callComponentMethod2 = C1227runtime.callComponentMethod(Lit26, Lit255, LList.list3(Lit907, Lit908, Lit258), Lit909);
                obj = C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit198, Boolean.FALSE, Lit48);
            } else {
                Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit213, Lit61, "Turn on Location access and tap &#xf05b; on Top-Right corner.", Lit107);
                Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit253, Lit61, "Open Settings", Lit107);
                Object andCoerceProperty$Ex3 = C1227runtime.setAndCoerceProperty$Ex(Lit214, Lit47, Boolean.TRUE, Lit48);
                Object andCoerceProperty$Ex4 = C1227runtime.setAndCoerceProperty$Ex(Lit168, Lit47, Boolean.TRUE, Lit48);
                Object andCoerceProperty$Ex5 = C1227runtime.setAndCoerceProperty$Ex(Lit264, Lit198, Boolean.FALSE, Lit48);
                obj = C1227runtime.callComponentMethod(Lit166, Lit193, LList.Empty, LList.Empty);
            }
        } else {
            obj = Values.empty;
        }
        return obj;
    }

    static Object lambda280() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit903, Lit912, Lit5, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit903, Lit913, Lit704, Lit147);
    }

    static Object lambda281() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit903, Lit912, Lit5, Lit147);
        return C1227runtime.setAndCoerceProperty$Ex(Lit903, Lit913, Lit704, Lit147);
    }

    static Object lambda282() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit197, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit197, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit197, Lit878, Lit393, Lit147);
    }

    static Object lambda283() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit197, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit197, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit197, Lit878, Lit393, Lit147);
    }

    public Object keyboard_timer$Timer() {
        C1227runtime.setThisForm();
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit176, Lit47, Boolean.TRUE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit197, Lit198, Boolean.FALSE, Lit48);
    }

    static Object lambda284() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit923, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit923, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit923, Lit878, Lit924, Lit147);
    }

    static Object lambda285() {
        Object andCoerceProperty$Ex = C1227runtime.setAndCoerceProperty$Ex(Lit923, Lit877, Boolean.FALSE, Lit48);
        Object andCoerceProperty$Ex2 = C1227runtime.setAndCoerceProperty$Ex(Lit923, Lit198, Boolean.FALSE, Lit48);
        return C1227runtime.setAndCoerceProperty$Ex(Lit923, Lit878, Lit924, Lit147);
    }

    static Object lambda286() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit861, Lit862, "COVIDEASE", Lit107);
    }

    static Object lambda287() {
        return C1227runtime.setAndCoerceProperty$Ex(Lit861, Lit862, "COVIDEASE", Lit107);
    }

    public String getSimpleName(Object object) {
        return object.getClass().getSimpleName();
    }

    public void androidLogForm(Object message) {
    }

    public void addToFormEnvironment(Symbol symbol, Object obj) {
        Symbol name = symbol;
        Object object = obj;
        Object[] objArr = new Object[4];
        objArr[0] = "Adding ~A to env ~A with value ~A";
        Object[] objArr2 = objArr;
        objArr2[1] = name;
        Object[] objArr3 = objArr2;
        objArr3[2] = this.form$Mnenvironment;
        Object[] objArr4 = objArr3;
        objArr4[3] = object;
        androidLogForm(Format.formatToString(0, objArr4));
        this.form$Mnenvironment.put(name, object);
    }

    public Object lookupInFormEnvironment(Symbol symbol, Object obj) {
        Object obj2;
        Symbol name = symbol;
        Object default$Mnvalue = obj;
        boolean x = ((this.form$Mnenvironment == null ? 1 : 0) + 1) & true;
        if (!x ? !x : !this.form$Mnenvironment.isBound(name)) {
            obj2 = default$Mnvalue;
        } else {
            obj2 = this.form$Mnenvironment.get(name);
        }
        return obj2;
    }

    public boolean isBoundInFormEnvironment(Symbol name) {
        return this.form$Mnenvironment.isBound(name);
    }

    public void addToGlobalVarEnvironment(Symbol symbol, Object obj) {
        Symbol name = symbol;
        Object object = obj;
        Object[] objArr = new Object[4];
        objArr[0] = "Adding ~A to env ~A with value ~A";
        Object[] objArr2 = objArr;
        objArr2[1] = name;
        Object[] objArr3 = objArr2;
        objArr3[2] = this.global$Mnvar$Mnenvironment;
        Object[] objArr4 = objArr3;
        objArr4[3] = object;
        androidLogForm(Format.formatToString(0, objArr4));
        this.global$Mnvar$Mnenvironment.put(name, object);
    }

    public void addToEvents(Object component$Mnname, Object event$Mnname) {
        this.events$Mnto$Mnregister = C1245lists.cons(C1245lists.cons(component$Mnname, event$Mnname), this.events$Mnto$Mnregister);
    }

    public void addToComponents(Object container$Mnname, Object component$Mntype, Object component$Mnname, Object init$Mnthunk) {
        this.components$Mnto$Mncreate = C1245lists.cons(LList.list4(container$Mnname, component$Mntype, component$Mnname, init$Mnthunk), this.components$Mnto$Mncreate);
    }

    public void addToGlobalVars(Object var, Object val$Mnthunk) {
        this.global$Mnvars$Mnto$Mncreate = C1245lists.cons(LList.list2(var, val$Mnthunk), this.global$Mnvars$Mnto$Mncreate);
    }

    public void addToFormDoAfterCreation(Object thunk) {
        this.form$Mndo$Mnafter$Mncreation = C1245lists.cons(thunk, this.form$Mndo$Mnafter$Mncreation);
    }

    public void sendError(Object error) {
        Object obj = error;
        RetValManager.sendError(obj == null ? null : obj.toString());
    }

    public void processException(Object obj) {
        Object ex = obj;
        Object apply1 = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(ex, Lit1));
        RuntimeErrorAlert.alert(this, apply1 == null ? null : apply1.toString(), ex instanceof YailRuntimeError ? ((YailRuntimeError) ex).getErrorType() : "Runtime Error", "End Application");
    }

    public boolean dispatchEvent(Component component, String str, String str2, Object[] objArr) {
        boolean z;
        boolean z2;
        Component componentObject = component;
        String registeredComponentName = str;
        String eventName = str2;
        Object[] args = objArr;
        SimpleSymbol registeredObject = misc.string$To$Symbol(registeredComponentName);
        if (!isBoundInFormEnvironment(registeredObject)) {
            EventDispatcher.unregisterEventForDelegation(this, registeredComponentName, eventName);
            z = false;
        } else if (lookupInFormEnvironment(registeredObject) == componentObject) {
            try {
                Object apply2 = Scheme.apply.apply2(lookupHandler(registeredComponentName, eventName), LList.makeList(args, 0));
                z2 = true;
            } catch (PermissionException e) {
                PermissionException exception = e;
                exception.printStackTrace();
                boolean x = this == componentObject;
                if (!x ? !x : !IsEqual.apply(eventName, "PermissionNeeded")) {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                } else {
                    processException(exception);
                }
                z2 = false;
            } catch (Throwable th) {
                Throwable exception2 = th;
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
                z2 = false;
            }
            z = z2;
        } else {
            z = false;
        }
        return z;
    }

    public void dispatchGenericEvent(Component component, String str, boolean z, Object[] objArr) {
        Boolean bool;
        Component componentObject = component;
        String eventName = str;
        boolean notAlreadyHandled = z;
        Object[] args = objArr;
        Object[] objArr2 = new Object[4];
        objArr2[0] = "any$";
        Object[] objArr3 = objArr2;
        objArr3[1] = getSimpleName(componentObject);
        Object[] objArr4 = objArr3;
        objArr4[2] = "$";
        Object[] objArr5 = objArr4;
        objArr5[3] = eventName;
        Object handler = lookupInFormEnvironment(misc.string$To$Symbol(strings.stringAppend(objArr5)));
        if (handler != Boolean.FALSE) {
            try {
                Apply apply = Scheme.apply;
                Object obj = handler;
                Component component2 = componentObject;
                if (notAlreadyHandled) {
                    bool = Boolean.TRUE;
                } else {
                    bool = Boolean.FALSE;
                }
                Object apply2 = apply.apply2(obj, C1245lists.cons(component2, C1245lists.cons(bool, LList.makeList(args, 0))));
            } catch (PermissionException e) {
                PermissionException exception = e;
                exception.printStackTrace();
                boolean x = this == componentObject;
                if (!x ? !x : !IsEqual.apply(eventName, "PermissionNeeded")) {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                } else {
                    processException(exception);
                }
            } catch (Throwable th) {
                Throwable exception2 = th;
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
            }
        }
    }

    public Object lookupHandler(Object componentName, Object obj) {
        Object eventName = obj;
        Object obj2 = componentName;
        String obj3 = obj2 == null ? null : obj2.toString();
        Object obj4 = eventName;
        return lookupInFormEnvironment(misc.string$To$Symbol(EventDispatcher.makeFullEventName(obj3, obj4 == null ? null : obj4.toString())));
    }

    public void $define() {
        Object obj;
        Throwable th;
        Object obj2;
        Throwable th2;
        Object obj3;
        Throwable th3;
        Object obj4;
        Throwable th4;
        Object obj5;
        Throwable th5;
        Object obj6;
        Throwable th6;
        Object obj7;
        Throwable th7;
        Object obj8;
        Throwable th8;
        Throwable th9;
        Language.setDefaults(Scheme.getInstance());
        try {
            run();
        } catch (Exception e) {
            Exception exception = e;
            androidLogForm(exception.getMessage());
            processException(exception);
        }
        Screen1 = this;
        addToFormEnvironment(Lit0, this);
        Object obj9 = this.events$Mnto$Mnregister;
        while (true) {
            Object obj10 = obj9;
            if (obj10 == LList.Empty) {
                break;
            }
            Object obj11 = obj10;
            Object obj12 = obj11;
            try {
                Pair arg0 = (Pair) obj11;
                Object event$Mninfo = arg0.getCar();
                Object apply1 = C1245lists.car.apply1(event$Mninfo);
                String obj13 = apply1 == null ? null : apply1.toString();
                Object apply12 = C1245lists.cdr.apply1(event$Mninfo);
                EventDispatcher.registerEventForDelegation(this, obj13, apply12 == null ? null : apply12.toString());
                obj9 = arg0.getCdr();
            } catch (ClassCastException e2) {
                ClassCastException classCastException = e2;
                Throwable th10 = th9;
                new WrongType(classCastException, "arg0", -2, obj12);
                throw th10;
            }
        }
        try {
            LList components = C1245lists.reverse(this.components$Mnto$Mncreate);
            addToGlobalVars(Lit2, lambda$Fn1);
            LList event$Mninfo2 = components;
            while (event$Mninfo2 != LList.Empty) {
                Object obj14 = event$Mninfo2;
                obj6 = obj14;
                Pair arg02 = (Pair) obj14;
                Object component$Mninfo = arg02.getCar();
                Object apply13 = C1245lists.caddr.apply1(component$Mninfo);
                Object apply14 = C1245lists.cadddr.apply1(component$Mninfo);
                Object component$Mntype = C1245lists.cadr.apply1(component$Mninfo);
                Object apply15 = C1245lists.car.apply1(component$Mninfo);
                obj7 = apply15;
                Object component$Mnname = apply13;
                Object component$Mnobject = Invoke.make.apply2(component$Mntype, lookupInFormEnvironment((Symbol) apply15));
                Object apply3 = SlotSet.set$Mnfield$Ex.apply3(this, component$Mnname, component$Mnobject);
                Object obj15 = component$Mnname;
                obj8 = obj15;
                addToFormEnvironment((Symbol) obj15, component$Mnobject);
                event$Mninfo2 = arg02.getCdr();
            }
            LList reverse = C1245lists.reverse(this.global$Mnvars$Mnto$Mncreate);
            while (reverse != LList.Empty) {
                Object obj16 = reverse;
                obj4 = obj16;
                Pair arg03 = (Pair) obj16;
                Object var$Mnval = arg03.getCar();
                Object apply16 = C1245lists.car.apply1(var$Mnval);
                obj5 = apply16;
                addToGlobalVarEnvironment((Symbol) apply16, Scheme.applyToArgs.apply1(C1245lists.cadr.apply1(var$Mnval)));
                reverse = arg03.getCdr();
            }
            Object reverse2 = C1245lists.reverse(this.form$Mndo$Mnafter$Mncreation);
            while (reverse2 != LList.Empty) {
                Object obj17 = reverse2;
                obj3 = obj17;
                Pair arg04 = (Pair) obj17;
                Object force = misc.force(arg04.getCar());
                reverse2 = arg04.getCdr();
            }
            LList component$Mndescriptors = components;
            LList lList = component$Mndescriptors;
            while (lList != LList.Empty) {
                Object obj18 = lList;
                obj2 = obj18;
                Pair arg05 = (Pair) obj18;
                Object component$Mninfo2 = arg05.getCar();
                Object apply17 = C1245lists.caddr.apply1(component$Mninfo2);
                Object init$Mnthunk = C1245lists.cadddr.apply1(component$Mninfo2);
                if (init$Mnthunk != Boolean.FALSE) {
                    Object apply18 = Scheme.applyToArgs.apply1(init$Mnthunk);
                }
                lList = arg05.getCdr();
            }
            LList lList2 = component$Mndescriptors;
            while (lList2 != LList.Empty) {
                Object obj19 = lList2;
                obj = obj19;
                Pair arg06 = (Pair) obj19;
                Object component$Mninfo3 = arg06.getCar();
                Object component$Mnname2 = C1245lists.caddr.apply1(component$Mninfo3);
                Object apply19 = C1245lists.cadddr.apply1(component$Mninfo3);
                callInitialize(SlotGet.field.apply2(this, component$Mnname2));
                lList2 = arg06.getCdr();
            }
        } catch (ClassCastException e3) {
            ClassCastException classCastException2 = e3;
            Throwable th11 = th;
            new WrongType(classCastException2, "arg0", -2, obj);
            throw th11;
        } catch (ClassCastException e4) {
            ClassCastException classCastException3 = e4;
            Throwable th12 = th2;
            new WrongType(classCastException3, "arg0", -2, obj2);
            throw th12;
        } catch (ClassCastException e5) {
            ClassCastException classCastException4 = e5;
            Throwable th13 = th3;
            new WrongType(classCastException4, "arg0", -2, obj3);
            throw th13;
        } catch (ClassCastException e6) {
            ClassCastException classCastException5 = e6;
            Throwable th14 = th5;
            new WrongType(classCastException5, "add-to-global-var-environment", 0, obj5);
            throw th14;
        } catch (ClassCastException e7) {
            ClassCastException classCastException6 = e7;
            Throwable th15 = th4;
            new WrongType(classCastException6, "arg0", -2, obj4);
            throw th15;
        } catch (ClassCastException e8) {
            ClassCastException classCastException7 = e8;
            Throwable th16 = th8;
            new WrongType(classCastException7, "add-to-form-environment", 0, obj8);
            throw th16;
        } catch (ClassCastException e9) {
            ClassCastException classCastException8 = e9;
            Throwable th17 = th7;
            new WrongType(classCastException8, "lookup-in-form-environment", 0, obj7);
            throw th17;
        } catch (ClassCastException e10) {
            ClassCastException classCastException9 = e10;
            Throwable th18 = th6;
            new WrongType(classCastException9, "arg0", -2, obj6);
            throw th18;
        } catch (YailRuntimeError e11) {
            processException(e11);
        }
    }

    public static SimpleSymbol lambda1symbolAppend$V(Object[] argsArray) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        LList symbols = LList.makeList(argsArray, 0);
        LList lList = symbols;
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object obj = symbols;
        Object obj2 = LList.Empty;
        while (true) {
            Object obj3 = obj2;
            Object obj4 = obj;
            if (obj4 == LList.Empty) {
                Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(obj3));
                Object obj5 = apply2;
                try {
                    return misc.string$To$Symbol((CharSequence) apply2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th;
                    new WrongType(classCastException, "string->symbol", 1, obj5);
                    throw th4;
                }
            } else {
                Object obj6 = obj4;
                Object obj7 = obj6;
                try {
                    Pair arg0 = (Pair) obj6;
                    obj = arg0.getCdr();
                    Object car = arg0.getCar();
                    Object obj8 = car;
                    try {
                        obj2 = Pair.make(misc.symbol$To$String((Symbol) car), obj3);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th5 = th3;
                        new WrongType(classCastException2, "symbol->string", 1, obj8);
                        throw th5;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th2;
                    new WrongType(classCastException3, "arg0", -2, obj7);
                    throw th6;
                }
            }
        }
    }

    static Object lambda2() {
        return null;
    }
}
