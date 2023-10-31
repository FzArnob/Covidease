package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.F32Vector;
import gnu.lists.F64Vector;
import gnu.lists.LList;
import gnu.lists.S16Vector;
import gnu.lists.S32Vector;
import gnu.lists.S64Vector;
import gnu.lists.S8Vector;
import gnu.lists.Sequence;
import gnu.lists.U16Vector;
import gnu.lists.U32Vector;
import gnu.lists.U64Vector;
import gnu.lists.U8Vector;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;

/* compiled from: uniform.scm */
public class uniform extends ModuleBody {
    public static final uniform $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
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
    static final SimpleSymbol Lit3;
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
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
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
    static final SimpleSymbol Lit6;
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
    static final SimpleSymbol Lit7;
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
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod f32vector;
    public static final ModuleMethod f32vector$Mn$Grlist;
    public static final ModuleMethod f32vector$Mnlength;
    public static final ModuleMethod f32vector$Mnref;
    public static final ModuleMethod f32vector$Mnset$Ex;
    public static final ModuleMethod f32vector$Qu;
    public static final ModuleMethod f64vector;
    public static final ModuleMethod f64vector$Mn$Grlist;
    public static final ModuleMethod f64vector$Mnlength;
    public static final ModuleMethod f64vector$Mnref;
    public static final ModuleMethod f64vector$Mnset$Ex;
    public static final ModuleMethod f64vector$Qu;
    public static final ModuleMethod list$Mn$Grf32vector;
    public static final ModuleMethod list$Mn$Grf64vector;
    public static final ModuleMethod list$Mn$Grs16vector;
    public static final ModuleMethod list$Mn$Grs32vector;
    public static final ModuleMethod list$Mn$Grs64vector;
    public static final ModuleMethod list$Mn$Grs8vector;
    public static final ModuleMethod list$Mn$Gru16vector;
    public static final ModuleMethod list$Mn$Gru32vector;
    public static final ModuleMethod list$Mn$Gru64vector;
    public static final ModuleMethod list$Mn$Gru8vector;
    public static final ModuleMethod make$Mnf32vector;
    public static final ModuleMethod make$Mnf64vector;
    public static final ModuleMethod make$Mns16vector;
    public static final ModuleMethod make$Mns32vector;
    public static final ModuleMethod make$Mns64vector;
    public static final ModuleMethod make$Mns8vector;
    public static final ModuleMethod make$Mnu16vector;
    public static final ModuleMethod make$Mnu32vector;
    public static final ModuleMethod make$Mnu64vector;
    public static final ModuleMethod make$Mnu8vector;
    public static final ModuleMethod s16vector;
    public static final ModuleMethod s16vector$Mn$Grlist;
    public static final ModuleMethod s16vector$Mnlength;
    public static final ModuleMethod s16vector$Mnref;
    public static final ModuleMethod s16vector$Mnset$Ex;
    public static final ModuleMethod s16vector$Qu;
    public static final ModuleMethod s32vector;
    public static final ModuleMethod s32vector$Mn$Grlist;
    public static final ModuleMethod s32vector$Mnlength;
    public static final ModuleMethod s32vector$Mnref;
    public static final ModuleMethod s32vector$Mnset$Ex;
    public static final ModuleMethod s32vector$Qu;
    public static final ModuleMethod s64vector;
    public static final ModuleMethod s64vector$Mn$Grlist;
    public static final ModuleMethod s64vector$Mnlength;
    public static final ModuleMethod s64vector$Mnref;
    public static final ModuleMethod s64vector$Mnset$Ex;
    public static final ModuleMethod s64vector$Qu;
    public static final ModuleMethod s8vector;
    public static final ModuleMethod s8vector$Mn$Grlist;
    public static final ModuleMethod s8vector$Mnlength;
    public static final ModuleMethod s8vector$Mnref;
    public static final ModuleMethod s8vector$Mnset$Ex;
    public static final ModuleMethod s8vector$Qu;
    public static final ModuleMethod u16vector;
    public static final ModuleMethod u16vector$Mn$Grlist;
    public static final ModuleMethod u16vector$Mnlength;
    public static final ModuleMethod u16vector$Mnref;
    public static final ModuleMethod u16vector$Mnset$Ex;
    public static final ModuleMethod u16vector$Qu;
    public static final ModuleMethod u32vector;
    public static final ModuleMethod u32vector$Mn$Grlist;
    public static final ModuleMethod u32vector$Mnlength;
    public static final ModuleMethod u32vector$Mnref;
    public static final ModuleMethod u32vector$Mnset$Ex;
    public static final ModuleMethod u32vector$Qu;
    public static final ModuleMethod u64vector;
    public static final ModuleMethod u64vector$Mn$Grlist;
    public static final ModuleMethod u64vector$Mnlength;
    public static final ModuleMethod u64vector$Mnref;
    public static final ModuleMethod u64vector$Mnset$Ex;
    public static final ModuleMethod u64vector$Qu;
    public static final ModuleMethod u8vector;
    public static final ModuleMethod u8vector$Mn$Grlist;
    public static final ModuleMethod u8vector$Mnlength;
    public static final ModuleMethod u8vector$Mnref;
    public static final ModuleMethod u8vector$Mnset$Ex;
    public static final ModuleMethod u8vector$Qu;

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
        uniform uniform;
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
        new SimpleSymbol("list->f64vector");
        Lit80 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("f64vector->list");
        Lit79 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("f64vector-set!");
        Lit78 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("f64vector-ref");
        Lit77 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("f64vector-length");
        Lit76 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("f64vector");
        Lit75 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("make-f64vector");
        Lit74 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("f64vector?");
        Lit73 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("list->f32vector");
        Lit72 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("f32vector->list");
        Lit71 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("f32vector-set!");
        Lit70 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("f32vector-ref");
        Lit69 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("f32vector-length");
        Lit68 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("f32vector");
        Lit67 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("make-f32vector");
        Lit66 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("f32vector?");
        Lit65 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("list->u64vector");
        Lit64 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("u64vector->list");
        Lit63 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("u64vector-set!");
        Lit62 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("u64vector-ref");
        Lit61 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("u64vector-length");
        Lit60 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("u64vector");
        Lit59 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("make-u64vector");
        Lit58 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("u64vector?");
        Lit57 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("list->s64vector");
        Lit56 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("s64vector->list");
        Lit55 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("s64vector-set!");
        Lit54 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("s64vector-ref");
        Lit53 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("s64vector-length");
        Lit52 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("s64vector");
        Lit51 = (SimpleSymbol) simpleSymbol30.readResolve();
        new SimpleSymbol("make-s64vector");
        Lit50 = (SimpleSymbol) simpleSymbol31.readResolve();
        new SimpleSymbol("s64vector?");
        Lit49 = (SimpleSymbol) simpleSymbol32.readResolve();
        new SimpleSymbol("list->u32vector");
        Lit48 = (SimpleSymbol) simpleSymbol33.readResolve();
        new SimpleSymbol("u32vector->list");
        Lit47 = (SimpleSymbol) simpleSymbol34.readResolve();
        new SimpleSymbol("u32vector-set!");
        Lit46 = (SimpleSymbol) simpleSymbol35.readResolve();
        new SimpleSymbol("u32vector-ref");
        Lit45 = (SimpleSymbol) simpleSymbol36.readResolve();
        new SimpleSymbol("u32vector-length");
        Lit44 = (SimpleSymbol) simpleSymbol37.readResolve();
        new SimpleSymbol("u32vector");
        Lit43 = (SimpleSymbol) simpleSymbol38.readResolve();
        new SimpleSymbol("make-u32vector");
        Lit42 = (SimpleSymbol) simpleSymbol39.readResolve();
        new SimpleSymbol("u32vector?");
        Lit41 = (SimpleSymbol) simpleSymbol40.readResolve();
        new SimpleSymbol("list->s32vector");
        Lit40 = (SimpleSymbol) simpleSymbol41.readResolve();
        new SimpleSymbol("s32vector->list");
        Lit39 = (SimpleSymbol) simpleSymbol42.readResolve();
        new SimpleSymbol("s32vector-set!");
        Lit38 = (SimpleSymbol) simpleSymbol43.readResolve();
        new SimpleSymbol("s32vector-ref");
        Lit37 = (SimpleSymbol) simpleSymbol44.readResolve();
        new SimpleSymbol("s32vector-length");
        Lit36 = (SimpleSymbol) simpleSymbol45.readResolve();
        new SimpleSymbol("s32vector");
        Lit35 = (SimpleSymbol) simpleSymbol46.readResolve();
        new SimpleSymbol("make-s32vector");
        Lit34 = (SimpleSymbol) simpleSymbol47.readResolve();
        new SimpleSymbol("s32vector?");
        Lit33 = (SimpleSymbol) simpleSymbol48.readResolve();
        new SimpleSymbol("list->u16vector");
        Lit32 = (SimpleSymbol) simpleSymbol49.readResolve();
        new SimpleSymbol("u16vector->list");
        Lit31 = (SimpleSymbol) simpleSymbol50.readResolve();
        new SimpleSymbol("u16vector-set!");
        Lit30 = (SimpleSymbol) simpleSymbol51.readResolve();
        new SimpleSymbol("u16vector-ref");
        Lit29 = (SimpleSymbol) simpleSymbol52.readResolve();
        new SimpleSymbol("u16vector-length");
        Lit28 = (SimpleSymbol) simpleSymbol53.readResolve();
        new SimpleSymbol("u16vector");
        Lit27 = (SimpleSymbol) simpleSymbol54.readResolve();
        new SimpleSymbol("make-u16vector");
        Lit26 = (SimpleSymbol) simpleSymbol55.readResolve();
        new SimpleSymbol("u16vector?");
        Lit25 = (SimpleSymbol) simpleSymbol56.readResolve();
        new SimpleSymbol("list->s16vector");
        Lit24 = (SimpleSymbol) simpleSymbol57.readResolve();
        new SimpleSymbol("s16vector->list");
        Lit23 = (SimpleSymbol) simpleSymbol58.readResolve();
        new SimpleSymbol("s16vector-set!");
        Lit22 = (SimpleSymbol) simpleSymbol59.readResolve();
        new SimpleSymbol("s16vector-ref");
        Lit21 = (SimpleSymbol) simpleSymbol60.readResolve();
        new SimpleSymbol("s16vector-length");
        Lit20 = (SimpleSymbol) simpleSymbol61.readResolve();
        new SimpleSymbol("s16vector");
        Lit19 = (SimpleSymbol) simpleSymbol62.readResolve();
        new SimpleSymbol("make-s16vector");
        Lit18 = (SimpleSymbol) simpleSymbol63.readResolve();
        new SimpleSymbol("s16vector?");
        Lit17 = (SimpleSymbol) simpleSymbol64.readResolve();
        new SimpleSymbol("list->u8vector");
        Lit16 = (SimpleSymbol) simpleSymbol65.readResolve();
        new SimpleSymbol("u8vector->list");
        Lit15 = (SimpleSymbol) simpleSymbol66.readResolve();
        new SimpleSymbol("u8vector-set!");
        Lit14 = (SimpleSymbol) simpleSymbol67.readResolve();
        new SimpleSymbol("u8vector-ref");
        Lit13 = (SimpleSymbol) simpleSymbol68.readResolve();
        new SimpleSymbol("u8vector-length");
        Lit12 = (SimpleSymbol) simpleSymbol69.readResolve();
        new SimpleSymbol("u8vector");
        Lit11 = (SimpleSymbol) simpleSymbol70.readResolve();
        new SimpleSymbol("make-u8vector");
        Lit10 = (SimpleSymbol) simpleSymbol71.readResolve();
        new SimpleSymbol("u8vector?");
        Lit9 = (SimpleSymbol) simpleSymbol72.readResolve();
        new SimpleSymbol("list->s8vector");
        Lit8 = (SimpleSymbol) simpleSymbol73.readResolve();
        new SimpleSymbol("s8vector->list");
        Lit7 = (SimpleSymbol) simpleSymbol74.readResolve();
        new SimpleSymbol("s8vector-set!");
        Lit6 = (SimpleSymbol) simpleSymbol75.readResolve();
        new SimpleSymbol("s8vector-ref");
        Lit5 = (SimpleSymbol) simpleSymbol76.readResolve();
        new SimpleSymbol("s8vector-length");
        Lit4 = (SimpleSymbol) simpleSymbol77.readResolve();
        new SimpleSymbol("s8vector");
        Lit3 = (SimpleSymbol) simpleSymbol78.readResolve();
        new SimpleSymbol("make-s8vector");
        Lit2 = (SimpleSymbol) simpleSymbol79.readResolve();
        new SimpleSymbol("s8vector?");
        Lit1 = (SimpleSymbol) simpleSymbol80.readResolve();
        new uniform();
        $instance = uniform;
        uniform uniform2 = $instance;
        new ModuleMethod(uniform2, 1, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s8vector$Qu = moduleMethod;
        new ModuleMethod(uniform2, 2, Lit2, 8193);
        make$Mns8vector = moduleMethod2;
        new ModuleMethod(uniform2, 4, Lit3, -4096);
        s8vector = moduleMethod3;
        new ModuleMethod(uniform2, 5, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s8vector$Mnlength = moduleMethod4;
        new ModuleMethod(uniform2, 6, Lit5, 8194);
        s8vector$Mnref = moduleMethod5;
        new ModuleMethod(uniform2, 7, Lit6, 12291);
        s8vector$Mnset$Ex = moduleMethod6;
        new ModuleMethod(uniform2, 8, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s8vector$Mn$Grlist = moduleMethod7;
        new ModuleMethod(uniform2, 9, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grs8vector = moduleMethod8;
        new ModuleMethod(uniform2, 10, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u8vector$Qu = moduleMethod9;
        new ModuleMethod(uniform2, 11, Lit10, 8193);
        make$Mnu8vector = moduleMethod10;
        new ModuleMethod(uniform2, 13, Lit11, -4096);
        u8vector = moduleMethod11;
        new ModuleMethod(uniform2, 14, Lit12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u8vector$Mnlength = moduleMethod12;
        new ModuleMethod(uniform2, 15, Lit13, 8194);
        u8vector$Mnref = moduleMethod13;
        new ModuleMethod(uniform2, 16, Lit14, 12291);
        u8vector$Mnset$Ex = moduleMethod14;
        new ModuleMethod(uniform2, 17, Lit15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u8vector$Mn$Grlist = moduleMethod15;
        new ModuleMethod(uniform2, 18, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Gru8vector = moduleMethod16;
        new ModuleMethod(uniform2, 19, Lit17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s16vector$Qu = moduleMethod17;
        new ModuleMethod(uniform2, 20, Lit18, 8193);
        make$Mns16vector = moduleMethod18;
        new ModuleMethod(uniform2, 22, Lit19, -4096);
        s16vector = moduleMethod19;
        new ModuleMethod(uniform2, 23, Lit20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s16vector$Mnlength = moduleMethod20;
        new ModuleMethod(uniform2, 24, Lit21, 8194);
        s16vector$Mnref = moduleMethod21;
        new ModuleMethod(uniform2, 25, Lit22, 12291);
        s16vector$Mnset$Ex = moduleMethod22;
        new ModuleMethod(uniform2, 26, Lit23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s16vector$Mn$Grlist = moduleMethod23;
        new ModuleMethod(uniform2, 27, Lit24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grs16vector = moduleMethod24;
        new ModuleMethod(uniform2, 28, Lit25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u16vector$Qu = moduleMethod25;
        new ModuleMethod(uniform2, 29, Lit26, 8193);
        make$Mnu16vector = moduleMethod26;
        new ModuleMethod(uniform2, 31, Lit27, -4096);
        u16vector = moduleMethod27;
        new ModuleMethod(uniform2, 32, Lit28, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u16vector$Mnlength = moduleMethod28;
        new ModuleMethod(uniform2, 33, Lit29, 8194);
        u16vector$Mnref = moduleMethod29;
        new ModuleMethod(uniform2, 34, Lit30, 12291);
        u16vector$Mnset$Ex = moduleMethod30;
        new ModuleMethod(uniform2, 35, Lit31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u16vector$Mn$Grlist = moduleMethod31;
        new ModuleMethod(uniform2, 36, Lit32, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Gru16vector = moduleMethod32;
        new ModuleMethod(uniform2, 37, Lit33, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s32vector$Qu = moduleMethod33;
        new ModuleMethod(uniform2, 38, Lit34, 8193);
        make$Mns32vector = moduleMethod34;
        new ModuleMethod(uniform2, 40, Lit35, -4096);
        s32vector = moduleMethod35;
        new ModuleMethod(uniform2, 41, Lit36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s32vector$Mnlength = moduleMethod36;
        new ModuleMethod(uniform2, 42, Lit37, 8194);
        s32vector$Mnref = moduleMethod37;
        new ModuleMethod(uniform2, 43, Lit38, 12291);
        s32vector$Mnset$Ex = moduleMethod38;
        new ModuleMethod(uniform2, 44, Lit39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s32vector$Mn$Grlist = moduleMethod39;
        new ModuleMethod(uniform2, 45, Lit40, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grs32vector = moduleMethod40;
        new ModuleMethod(uniform2, 46, Lit41, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u32vector$Qu = moduleMethod41;
        new ModuleMethod(uniform2, 47, Lit42, 8193);
        make$Mnu32vector = moduleMethod42;
        new ModuleMethod(uniform2, 49, Lit43, -4096);
        u32vector = moduleMethod43;
        new ModuleMethod(uniform2, 50, Lit44, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u32vector$Mnlength = moduleMethod44;
        new ModuleMethod(uniform2, 51, Lit45, 8194);
        u32vector$Mnref = moduleMethod45;
        new ModuleMethod(uniform2, 52, Lit46, 12291);
        u32vector$Mnset$Ex = moduleMethod46;
        new ModuleMethod(uniform2, 53, Lit47, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u32vector$Mn$Grlist = moduleMethod47;
        new ModuleMethod(uniform2, 54, Lit48, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Gru32vector = moduleMethod48;
        new ModuleMethod(uniform2, 55, Lit49, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s64vector$Qu = moduleMethod49;
        new ModuleMethod(uniform2, 56, Lit50, 8193);
        make$Mns64vector = moduleMethod50;
        new ModuleMethod(uniform2, 58, Lit51, -4096);
        s64vector = moduleMethod51;
        new ModuleMethod(uniform2, 59, Lit52, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s64vector$Mnlength = moduleMethod52;
        new ModuleMethod(uniform2, 60, Lit53, 8194);
        s64vector$Mnref = moduleMethod53;
        new ModuleMethod(uniform2, 61, Lit54, 12291);
        s64vector$Mnset$Ex = moduleMethod54;
        new ModuleMethod(uniform2, 62, Lit55, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s64vector$Mn$Grlist = moduleMethod55;
        new ModuleMethod(uniform2, 63, Lit56, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grs64vector = moduleMethod56;
        new ModuleMethod(uniform2, 64, Lit57, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u64vector$Qu = moduleMethod57;
        new ModuleMethod(uniform2, 65, Lit58, 8193);
        make$Mnu64vector = moduleMethod58;
        new ModuleMethod(uniform2, 67, Lit59, -4096);
        u64vector = moduleMethod59;
        new ModuleMethod(uniform2, 68, Lit60, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u64vector$Mnlength = moduleMethod60;
        new ModuleMethod(uniform2, 69, Lit61, 8194);
        u64vector$Mnref = moduleMethod61;
        new ModuleMethod(uniform2, 70, Lit62, 12291);
        u64vector$Mnset$Ex = moduleMethod62;
        new ModuleMethod(uniform2, 71, Lit63, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u64vector$Mn$Grlist = moduleMethod63;
        new ModuleMethod(uniform2, 72, Lit64, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Gru64vector = moduleMethod64;
        new ModuleMethod(uniform2, 73, Lit65, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f32vector$Qu = moduleMethod65;
        new ModuleMethod(uniform2, 74, Lit66, 8193);
        make$Mnf32vector = moduleMethod66;
        new ModuleMethod(uniform2, 76, Lit67, -4096);
        f32vector = moduleMethod67;
        new ModuleMethod(uniform2, 77, Lit68, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f32vector$Mnlength = moduleMethod68;
        new ModuleMethod(uniform2, 78, Lit69, 8194);
        f32vector$Mnref = moduleMethod69;
        new ModuleMethod(uniform2, 79, Lit70, 12291);
        f32vector$Mnset$Ex = moduleMethod70;
        new ModuleMethod(uniform2, 80, Lit71, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f32vector$Mn$Grlist = moduleMethod71;
        new ModuleMethod(uniform2, 81, Lit72, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grf32vector = moduleMethod72;
        new ModuleMethod(uniform2, 82, Lit73, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f64vector$Qu = moduleMethod73;
        new ModuleMethod(uniform2, 83, Lit74, 8193);
        make$Mnf64vector = moduleMethod74;
        new ModuleMethod(uniform2, 85, Lit75, -4096);
        f64vector = moduleMethod75;
        new ModuleMethod(uniform2, 86, Lit76, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f64vector$Mnlength = moduleMethod76;
        new ModuleMethod(uniform2, 87, Lit77, 8194);
        f64vector$Mnref = moduleMethod77;
        new ModuleMethod(uniform2, 88, Lit78, 12291);
        f64vector$Mnset$Ex = moduleMethod78;
        new ModuleMethod(uniform2, 89, Lit79, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f64vector$Mn$Grlist = moduleMethod79;
        new ModuleMethod(uniform2, 90, Lit80, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grf64vector = moduleMethod80;
        $instance.run();
    }

    public uniform() {
        ModuleInfo.register(this);
    }

    public static F32Vector makeF32vector(int i) {
        return makeF32vector(i, 0.0f);
    }

    public static F64Vector makeF64vector(int i) {
        return makeF64vector(i, 0.0d);
    }

    public static S16Vector makeS16vector(int i) {
        return makeS16vector(i, 0);
    }

    public static S32Vector makeS32vector(int i) {
        return makeS32vector(i, 0);
    }

    public static S64Vector makeS64vector(int i) {
        return makeS64vector(i, 0);
    }

    public static S8Vector makeS8vector(int i) {
        return makeS8vector(i, 0);
    }

    public static U16Vector makeU16vector(int i) {
        return makeU16vector(i, 0);
    }

    public static U32Vector makeU32vector(int i) {
        return makeU32vector(i, 0);
    }

    public static U64Vector makeU64vector(int i) {
        return makeU64vector(i, Lit0);
    }

    public static U8Vector makeU8vector(int i) {
        return makeU8vector(i, 0);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static boolean isS8vector(Object x) {
        return x instanceof S8Vector;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 2:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 5:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof S8Vector)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 8:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof S8Vector)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 9:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof LList)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 11:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 14:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof U8Vector)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 17:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (!(obj11 instanceof U8Vector)) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 18:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (!(obj13 instanceof LList)) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 19:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 20:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 23:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (!(obj15 instanceof S16Vector)) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 26:
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (!(obj17 instanceof S16Vector)) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 27:
                CallContext callContext11 = callContext2;
                Object obj19 = obj2;
                Object obj20 = obj19;
                if (!(obj19 instanceof LList)) {
                    return -786431;
                }
                callContext11.value1 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 29:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 32:
                CallContext callContext12 = callContext2;
                Object obj21 = obj2;
                Object obj22 = obj21;
                if (!(obj21 instanceof U16Vector)) {
                    return -786431;
                }
                callContext12.value1 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 35:
                CallContext callContext13 = callContext2;
                Object obj23 = obj2;
                Object obj24 = obj23;
                if (!(obj23 instanceof U16Vector)) {
                    return -786431;
                }
                callContext13.value1 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 36:
                CallContext callContext14 = callContext2;
                Object obj25 = obj2;
                Object obj26 = obj25;
                if (!(obj25 instanceof LList)) {
                    return -786431;
                }
                callContext14.value1 = obj26;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 37:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 38:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 41:
                CallContext callContext15 = callContext2;
                Object obj27 = obj2;
                Object obj28 = obj27;
                if (!(obj27 instanceof S32Vector)) {
                    return -786431;
                }
                callContext15.value1 = obj28;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 44:
                CallContext callContext16 = callContext2;
                Object obj29 = obj2;
                Object obj30 = obj29;
                if (!(obj29 instanceof S32Vector)) {
                    return -786431;
                }
                callContext16.value1 = obj30;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 45:
                CallContext callContext17 = callContext2;
                Object obj31 = obj2;
                Object obj32 = obj31;
                if (!(obj31 instanceof LList)) {
                    return -786431;
                }
                callContext17.value1 = obj32;
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
            case 50:
                CallContext callContext18 = callContext2;
                Object obj33 = obj2;
                Object obj34 = obj33;
                if (!(obj33 instanceof U32Vector)) {
                    return -786431;
                }
                callContext18.value1 = obj34;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 53:
                CallContext callContext19 = callContext2;
                Object obj35 = obj2;
                Object obj36 = obj35;
                if (!(obj35 instanceof U32Vector)) {
                    return -786431;
                }
                callContext19.value1 = obj36;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 54:
                CallContext callContext20 = callContext2;
                Object obj37 = obj2;
                Object obj38 = obj37;
                if (!(obj37 instanceof LList)) {
                    return -786431;
                }
                callContext20.value1 = obj38;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 55:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 56:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 59:
                CallContext callContext21 = callContext2;
                Object obj39 = obj2;
                Object obj40 = obj39;
                if (!(obj39 instanceof S64Vector)) {
                    return -786431;
                }
                callContext21.value1 = obj40;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 62:
                CallContext callContext22 = callContext2;
                Object obj41 = obj2;
                Object obj42 = obj41;
                if (!(obj41 instanceof S64Vector)) {
                    return -786431;
                }
                callContext22.value1 = obj42;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 63:
                CallContext callContext23 = callContext2;
                Object obj43 = obj2;
                Object obj44 = obj43;
                if (!(obj43 instanceof LList)) {
                    return -786431;
                }
                callContext23.value1 = obj44;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 64:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 65:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 68:
                CallContext callContext24 = callContext2;
                Object obj45 = obj2;
                Object obj46 = obj45;
                if (!(obj45 instanceof U64Vector)) {
                    return -786431;
                }
                callContext24.value1 = obj46;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 71:
                CallContext callContext25 = callContext2;
                Object obj47 = obj2;
                Object obj48 = obj47;
                if (!(obj47 instanceof U64Vector)) {
                    return -786431;
                }
                callContext25.value1 = obj48;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 72:
                CallContext callContext26 = callContext2;
                Object obj49 = obj2;
                Object obj50 = obj49;
                if (!(obj49 instanceof LList)) {
                    return -786431;
                }
                callContext26.value1 = obj50;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 73:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 74:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 77:
                CallContext callContext27 = callContext2;
                Object obj51 = obj2;
                Object obj52 = obj51;
                if (!(obj51 instanceof F32Vector)) {
                    return -786431;
                }
                callContext27.value1 = obj52;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 80:
                CallContext callContext28 = callContext2;
                Object obj53 = obj2;
                Object obj54 = obj53;
                if (!(obj53 instanceof F32Vector)) {
                    return -786431;
                }
                callContext28.value1 = obj54;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 81:
                CallContext callContext29 = callContext2;
                Object obj55 = obj2;
                Object obj56 = obj55;
                if (!(obj55 instanceof LList)) {
                    return -786431;
                }
                callContext29.value1 = obj56;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 82:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 83:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 86:
                CallContext callContext30 = callContext2;
                Object obj57 = obj2;
                Object obj58 = obj57;
                if (!(obj57 instanceof F64Vector)) {
                    return -786431;
                }
                callContext30.value1 = obj58;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 89:
                CallContext callContext31 = callContext2;
                Object obj59 = obj2;
                Object obj60 = obj59;
                if (!(obj59 instanceof F64Vector)) {
                    return -786431;
                }
                callContext31.value1 = obj60;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 90:
                CallContext callContext32 = callContext2;
                Object obj61 = obj2;
                Object obj62 = obj61;
                if (!(obj61 instanceof LList)) {
                    return -786431;
                }
                callContext32.value1 = obj62;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static S8Vector makeS8vector(int n, int init) {
        S8Vector s8Vector;
        new S8Vector(n, (byte) init);
        return s8Vector;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 2:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 6:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof S8Vector)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 11:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 15:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof U8Vector)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
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
            case 24:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof S16Vector)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 29:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 33:
                CallContext callContext6 = callContext2;
                Object obj11 = obj3;
                Object obj12 = obj11;
                if (!(obj11 instanceof U16Vector)) {
                    return -786431;
                }
                callContext6.value1 = obj12;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 38:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 42:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (!(obj13 instanceof S32Vector)) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 47:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 51:
                CallContext callContext8 = callContext2;
                Object obj15 = obj3;
                Object obj16 = obj15;
                if (!(obj15 instanceof U32Vector)) {
                    return -786431;
                }
                callContext8.value1 = obj16;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 56:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 60:
                CallContext callContext9 = callContext2;
                Object obj17 = obj3;
                Object obj18 = obj17;
                if (!(obj17 instanceof S64Vector)) {
                    return -786431;
                }
                callContext9.value1 = obj18;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 65:
                callContext2.value1 = obj3;
                CallContext callContext10 = callContext2;
                Object obj19 = obj4;
                Object obj20 = obj19;
                if (IntNum.asIntNumOrNull(obj19) == null) {
                    return -786430;
                }
                callContext10.value2 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 69:
                CallContext callContext11 = callContext2;
                Object obj21 = obj3;
                Object obj22 = obj21;
                if (!(obj21 instanceof U64Vector)) {
                    return -786431;
                }
                callContext11.value1 = obj22;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 74:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 78:
                CallContext callContext12 = callContext2;
                Object obj23 = obj3;
                Object obj24 = obj23;
                if (!(obj23 instanceof F32Vector)) {
                    return -786431;
                }
                callContext12.value1 = obj24;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 83:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 87:
                CallContext callContext13 = callContext2;
                Object obj25 = obj3;
                Object obj26 = obj25;
                if (!(obj25 instanceof F64Vector)) {
                    return -786431;
                }
                callContext13.value1 = obj26;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static S8Vector s8vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$S8vector(values);
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 4:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 13:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 22:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 31:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 40:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 49:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 58:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 67:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 76:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 85:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static int s8vectorLength(S8Vector v) {
        return v.size();
    }

    public static int s8vectorRef(S8Vector v, int i) {
        return v.intAt(i);
    }

    public static void s8vectorSet$Ex(S8Vector v, int i, int x) {
        v.setByteAt(i, (byte) x);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 7:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof S8Vector)) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 16:
                CallContext callContext4 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                if (!(obj9 instanceof U8Vector)) {
                    return -786431;
                }
                callContext4.value1 = obj10;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 25:
                CallContext callContext5 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof S16Vector)) {
                    return -786431;
                }
                callContext5.value1 = obj12;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 34:
                CallContext callContext6 = callContext2;
                Object obj13 = obj4;
                Object obj14 = obj13;
                if (!(obj13 instanceof U16Vector)) {
                    return -786431;
                }
                callContext6.value1 = obj14;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 43:
                CallContext callContext7 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (!(obj15 instanceof S32Vector)) {
                    return -786431;
                }
                callContext7.value1 = obj16;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 52:
                CallContext callContext8 = callContext2;
                Object obj17 = obj4;
                Object obj18 = obj17;
                if (!(obj17 instanceof U32Vector)) {
                    return -786431;
                }
                callContext8.value1 = obj18;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 61:
                CallContext callContext9 = callContext2;
                Object obj19 = obj4;
                Object obj20 = obj19;
                if (!(obj19 instanceof S64Vector)) {
                    return -786431;
                }
                callContext9.value1 = obj20;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 70:
                CallContext callContext10 = callContext2;
                Object obj21 = obj4;
                Object obj22 = obj21;
                if (!(obj21 instanceof U64Vector)) {
                    return -786431;
                }
                callContext10.value1 = obj22;
                callContext2.value2 = obj5;
                CallContext callContext11 = callContext2;
                Object obj23 = obj6;
                Object obj24 = obj23;
                if (IntNum.asIntNumOrNull(obj23) == null) {
                    return -786429;
                }
                callContext11.value3 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 79:
                CallContext callContext12 = callContext2;
                Object obj25 = obj4;
                Object obj26 = obj25;
                if (!(obj25 instanceof F32Vector)) {
                    return -786431;
                }
                callContext12.value1 = obj26;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 88:
                CallContext callContext13 = callContext2;
                Object obj27 = obj4;
                Object obj28 = obj27;
                if (!(obj27 instanceof F64Vector)) {
                    return -786431;
                }
                callContext13.value1 = obj28;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static LList s8vector$To$List(S8Vector v) {
        return LList.makeList(v);
    }

    public static S8Vector list$To$S8vector(LList l) {
        S8Vector s8Vector;
        new S8Vector((Sequence) l);
        return s8Vector;
    }

    public static boolean isU8vector(Object x) {
        return x instanceof U8Vector;
    }

    public static U8Vector makeU8vector(int n, int init) {
        U8Vector u8Vector;
        new U8Vector(n, (byte) init);
        return u8Vector;
    }

    public static U8Vector u8vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$U8vector(values);
    }

    public static int u8vectorLength(U8Vector v) {
        return v.size();
    }

    public static int u8vectorRef(U8Vector v, int i) {
        return v.intAt(i);
    }

    public static void u8vectorSet$Ex(U8Vector v, int i, int x) {
        v.setByteAt(i, (byte) x);
    }

    public static LList u8vector$To$List(U8Vector v) {
        return LList.makeList(v);
    }

    public static U8Vector list$To$U8vector(LList l) {
        U8Vector u8Vector;
        new U8Vector((Sequence) l);
        return u8Vector;
    }

    public static boolean isS16vector(Object x) {
        return x instanceof S16Vector;
    }

    public static S16Vector makeS16vector(int n, int init) {
        S16Vector s16Vector;
        new S16Vector(n, (short) init);
        return s16Vector;
    }

    public static S16Vector s16vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$S16vector(values);
    }

    public static int s16vectorLength(S16Vector v) {
        return v.size();
    }

    public static int s16vectorRef(S16Vector v, int i) {
        return v.intAt(i);
    }

    public static void s16vectorSet$Ex(S16Vector v, int i, int x) {
        v.setShortAt(i, (short) x);
    }

    public static LList s16vector$To$List(S16Vector v) {
        return LList.makeList(v);
    }

    public static S16Vector list$To$S16vector(LList l) {
        S16Vector s16Vector;
        new S16Vector((Sequence) l);
        return s16Vector;
    }

    public static boolean isU16vector(Object x) {
        return x instanceof U16Vector;
    }

    public static U16Vector makeU16vector(int n, int init) {
        U16Vector u16Vector;
        new U16Vector(n, (short) init);
        return u16Vector;
    }

    public static U16Vector u16vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$U16vector(values);
    }

    public static int u16vectorLength(U16Vector v) {
        return v.size();
    }

    public static int u16vectorRef(U16Vector v, int i) {
        return v.intAt(i);
    }

    public static void u16vectorSet$Ex(U16Vector v, int i, int x) {
        v.setShortAt(i, (short) x);
    }

    public static LList u16vector$To$List(U16Vector v) {
        return LList.makeList(v);
    }

    public static U16Vector list$To$U16vector(LList l) {
        U16Vector u16Vector;
        new U16Vector((Sequence) l);
        return u16Vector;
    }

    public static boolean isS32vector(Object x) {
        return x instanceof S32Vector;
    }

    public static S32Vector makeS32vector(int n, int init) {
        S32Vector s32Vector;
        new S32Vector(n, init);
        return s32Vector;
    }

    public static S32Vector s32vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$S32vector(values);
    }

    public static int s32vectorLength(S32Vector v) {
        return v.size();
    }

    public static int s32vectorRef(S32Vector v, int i) {
        return v.intAt(i);
    }

    public static void s32vectorSet$Ex(S32Vector v, int i, int x) {
        v.setIntAt(i, x);
    }

    public static LList s32vector$To$List(S32Vector v) {
        return LList.makeList(v);
    }

    public static S32Vector list$To$S32vector(LList l) {
        S32Vector s32Vector;
        new S32Vector((Sequence) l);
        return s32Vector;
    }

    public static boolean isU32vector(Object x) {
        return x instanceof U32Vector;
    }

    public static U32Vector makeU32vector(int n, long init) {
        U32Vector u32Vector;
        new U32Vector(n, (int) init);
        return u32Vector;
    }

    public static U32Vector u32vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$U32vector(values);
    }

    public static int u32vectorLength(U32Vector v) {
        return v.size();
    }

    public static long u32vectorRef(U32Vector v, int i) {
        return ((Number) v.get(i)).longValue();
    }

    public static void u32vectorSet$Ex(U32Vector v, int i, long x) {
        v.setIntAt(i, (int) x);
    }

    public static LList u32vector$To$List(U32Vector v) {
        return LList.makeList(v);
    }

    public static U32Vector list$To$U32vector(LList l) {
        U32Vector u32Vector;
        new U32Vector((Sequence) l);
        return u32Vector;
    }

    public static boolean isS64vector(Object x) {
        return x instanceof S64Vector;
    }

    public static S64Vector makeS64vector(int n, long init) {
        S64Vector s64Vector;
        new S64Vector(n, init);
        return s64Vector;
    }

    public static S64Vector s64vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$S64vector(values);
    }

    public static int s64vectorLength(S64Vector v) {
        return v.size();
    }

    public static long s64vectorRef(S64Vector v, int i) {
        return v.longAt(i);
    }

    public static void s64vectorSet$Ex(S64Vector v, int i, long x) {
        v.setLongAt(i, x);
    }

    public static LList s64vector$To$List(S64Vector v) {
        return LList.makeList(v);
    }

    public static S64Vector list$To$S64vector(LList l) {
        S64Vector s64Vector;
        new S64Vector((Sequence) l);
        return s64Vector;
    }

    public static boolean isU64vector(Object x) {
        return x instanceof U64Vector;
    }

    public static U64Vector makeU64vector(int n, IntNum init) {
        Throwable th;
        U64Vector u64Vector = r9;
        IntNum intNum = init;
        IntNum intNum2 = intNum;
        try {
            U64Vector u64Vector2 = new U64Vector(n, intNum.longValue());
            return u64Vector;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.lists.U64Vector.<init>(int,long)", 2, (Object) intNum2);
            throw th2;
        }
    }

    public static U64Vector u64vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$U64vector(values);
    }

    public static int u64vectorLength(U64Vector v) {
        return v.size();
    }

    public static IntNum u64vectorRef(U64Vector v, int i) {
        return LangObjType.coerceIntNum(v.get(i));
    }

    public static void u64vectorSet$Ex(U64Vector v, int i, IntNum x) {
        Throwable th;
        IntNum intNum = x;
        IntNum intNum2 = intNum;
        try {
            v.setLongAt(i, intNum.longValue());
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.lists.U64Vector.setLongAt(int,long)", 3, (Object) intNum2);
            throw th2;
        }
    }

    public static LList u64vector$To$List(U64Vector v) {
        return LList.makeList(v);
    }

    public static U64Vector list$To$U64vector(LList l) {
        U64Vector u64Vector;
        new U64Vector((Sequence) l);
        return u64Vector;
    }

    public static boolean isF32vector(Object x) {
        return x instanceof F32Vector;
    }

    public static F32Vector makeF32vector(int n, float init) {
        F32Vector f32Vector;
        new F32Vector(n, init);
        return f32Vector;
    }

    public static F32Vector f32vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$F32vector(values);
    }

    public static int f32vectorLength(F32Vector v) {
        return v.size();
    }

    public static float f32vectorRef(F32Vector v, int i) {
        return v.floatAt(i);
    }

    public static void f32vectorSet$Ex(F32Vector v, int i, float x) {
        v.setFloatAt(i, x);
    }

    public static LList f32vector$To$List(F32Vector v) {
        return LList.makeList(v);
    }

    public static F32Vector list$To$F32vector(LList l) {
        F32Vector f32Vector;
        new F32Vector((Sequence) l);
        return f32Vector;
    }

    public static boolean isF64vector(Object x) {
        return x instanceof F64Vector;
    }

    public static F64Vector makeF64vector(int n, double init) {
        F64Vector f64Vector;
        new F64Vector(n, init);
        return f64Vector;
    }

    public static F64Vector f64vector$V(Object[] argsArray) {
        LList values = LList.makeList(argsArray, 0);
        LList lList = values;
        return list$To$F64vector(values);
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 4:
                return s8vector$V(objArr2);
            case 13:
                return u8vector$V(objArr2);
            case 22:
                return s16vector$V(objArr2);
            case 31:
                return u16vector$V(objArr2);
            case 40:
                return s32vector$V(objArr2);
            case 49:
                return u32vector$V(objArr2);
            case 58:
                return s64vector$V(objArr2);
            case 67:
                return u64vector$V(objArr2);
            case 76:
                return f32vector$V(objArr2);
            case 85:
                return f64vector$V(objArr2);
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    public static int f64vectorLength(F64Vector v) {
        return v.size();
    }

    public static double f64vectorRef(F64Vector v, int i) {
        return v.doubleAt(i);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
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
        Throwable th17;
        Throwable th18;
        Throwable th19;
        Throwable th20;
        Throwable th21;
        Throwable th22;
        Throwable th23;
        Throwable th24;
        Throwable th25;
        Throwable th26;
        Throwable th27;
        Throwable th28;
        Throwable th29;
        Throwable th30;
        Throwable th31;
        Throwable th32;
        Throwable th33;
        Throwable th34;
        Throwable th35;
        Throwable th36;
        Throwable th37;
        Throwable th38;
        Throwable th39;
        Throwable th40;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 2:
                try {
                    try {
                        return makeS8vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th41 = th40;
                        new WrongType(classCastException, "make-s8vector", 2, obj4);
                        throw th41;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th42 = th39;
                    new WrongType(classCastException2, "make-s8vector", 1, obj3);
                    throw th42;
                }
            case 6:
                try {
                    try {
                        return Integer.valueOf(s8vectorRef((S8Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th43 = th38;
                        new WrongType(classCastException3, "s8vector-ref", 2, obj4);
                        throw th43;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th44 = th37;
                    new WrongType(classCastException4, "s8vector-ref", 1, obj3);
                    throw th44;
                }
            case 11:
                try {
                    try {
                        return makeU8vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th45 = th36;
                        new WrongType(classCastException5, "make-u8vector", 2, obj4);
                        throw th45;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th46 = th35;
                    new WrongType(classCastException6, "make-u8vector", 1, obj3);
                    throw th46;
                }
            case 15:
                try {
                    try {
                        return Integer.valueOf(u8vectorRef((U8Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th47 = th34;
                        new WrongType(classCastException7, "u8vector-ref", 2, obj4);
                        throw th47;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th48 = th33;
                    new WrongType(classCastException8, "u8vector-ref", 1, obj3);
                    throw th48;
                }
            case 20:
                try {
                    try {
                        return makeS16vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th49 = th32;
                        new WrongType(classCastException9, "make-s16vector", 2, obj4);
                        throw th49;
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th50 = th31;
                    new WrongType(classCastException10, "make-s16vector", 1, obj3);
                    throw th50;
                }
            case 24:
                try {
                    try {
                        return Integer.valueOf(s16vectorRef((S16Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th51 = th30;
                        new WrongType(classCastException11, "s16vector-ref", 2, obj4);
                        throw th51;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th52 = th29;
                    new WrongType(classCastException12, "s16vector-ref", 1, obj3);
                    throw th52;
                }
            case 29:
                try {
                    try {
                        return makeU16vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e13) {
                        ClassCastException classCastException13 = e13;
                        Throwable th53 = th28;
                        new WrongType(classCastException13, "make-u16vector", 2, obj4);
                        throw th53;
                    }
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th54 = th27;
                    new WrongType(classCastException14, "make-u16vector", 1, obj3);
                    throw th54;
                }
            case 33:
                try {
                    try {
                        return Integer.valueOf(u16vectorRef((U16Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e15) {
                        ClassCastException classCastException15 = e15;
                        Throwable th55 = th26;
                        new WrongType(classCastException15, "u16vector-ref", 2, obj4);
                        throw th55;
                    }
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th56 = th25;
                    new WrongType(classCastException16, "u16vector-ref", 1, obj3);
                    throw th56;
                }
            case 38:
                try {
                    try {
                        return makeS32vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e17) {
                        ClassCastException classCastException17 = e17;
                        Throwable th57 = th24;
                        new WrongType(classCastException17, "make-s32vector", 2, obj4);
                        throw th57;
                    }
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th58 = th23;
                    new WrongType(classCastException18, "make-s32vector", 1, obj3);
                    throw th58;
                }
            case 42:
                try {
                    try {
                        return Integer.valueOf(s32vectorRef((S32Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e19) {
                        ClassCastException classCastException19 = e19;
                        Throwable th59 = th22;
                        new WrongType(classCastException19, "s32vector-ref", 2, obj4);
                        throw th59;
                    }
                } catch (ClassCastException e20) {
                    ClassCastException classCastException20 = e20;
                    Throwable th60 = th21;
                    new WrongType(classCastException20, "s32vector-ref", 1, obj3);
                    throw th60;
                }
            case 47:
                try {
                    try {
                        return makeU32vector(((Number) obj3).intValue(), ((Number) obj4).longValue());
                    } catch (ClassCastException e21) {
                        ClassCastException classCastException21 = e21;
                        Throwable th61 = th20;
                        new WrongType(classCastException21, "make-u32vector", 2, obj4);
                        throw th61;
                    }
                } catch (ClassCastException e22) {
                    ClassCastException classCastException22 = e22;
                    Throwable th62 = th19;
                    new WrongType(classCastException22, "make-u32vector", 1, obj3);
                    throw th62;
                }
            case 51:
                try {
                    try {
                        return Long.valueOf(u32vectorRef((U32Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e23) {
                        ClassCastException classCastException23 = e23;
                        Throwable th63 = th18;
                        new WrongType(classCastException23, "u32vector-ref", 2, obj4);
                        throw th63;
                    }
                } catch (ClassCastException e24) {
                    ClassCastException classCastException24 = e24;
                    Throwable th64 = th17;
                    new WrongType(classCastException24, "u32vector-ref", 1, obj3);
                    throw th64;
                }
            case 56:
                try {
                    try {
                        return makeS64vector(((Number) obj3).intValue(), ((Number) obj4).longValue());
                    } catch (ClassCastException e25) {
                        ClassCastException classCastException25 = e25;
                        Throwable th65 = th16;
                        new WrongType(classCastException25, "make-s64vector", 2, obj4);
                        throw th65;
                    }
                } catch (ClassCastException e26) {
                    ClassCastException classCastException26 = e26;
                    Throwable th66 = th15;
                    new WrongType(classCastException26, "make-s64vector", 1, obj3);
                    throw th66;
                }
            case 60:
                try {
                    try {
                        return Long.valueOf(s64vectorRef((S64Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e27) {
                        ClassCastException classCastException27 = e27;
                        Throwable th67 = th14;
                        new WrongType(classCastException27, "s64vector-ref", 2, obj4);
                        throw th67;
                    }
                } catch (ClassCastException e28) {
                    ClassCastException classCastException28 = e28;
                    Throwable th68 = th13;
                    new WrongType(classCastException28, "s64vector-ref", 1, obj3);
                    throw th68;
                }
            case 65:
                try {
                    try {
                        return makeU64vector(((Number) obj3).intValue(), LangObjType.coerceIntNum(obj4));
                    } catch (ClassCastException e29) {
                        ClassCastException classCastException29 = e29;
                        Throwable th69 = th12;
                        new WrongType(classCastException29, "make-u64vector", 2, obj4);
                        throw th69;
                    }
                } catch (ClassCastException e30) {
                    ClassCastException classCastException30 = e30;
                    Throwable th70 = th11;
                    new WrongType(classCastException30, "make-u64vector", 1, obj3);
                    throw th70;
                }
            case 69:
                try {
                    try {
                        return u64vectorRef((U64Vector) obj3, ((Number) obj4).intValue());
                    } catch (ClassCastException e31) {
                        ClassCastException classCastException31 = e31;
                        Throwable th71 = th10;
                        new WrongType(classCastException31, "u64vector-ref", 2, obj4);
                        throw th71;
                    }
                } catch (ClassCastException e32) {
                    ClassCastException classCastException32 = e32;
                    Throwable th72 = th9;
                    new WrongType(classCastException32, "u64vector-ref", 1, obj3);
                    throw th72;
                }
            case 74:
                try {
                    try {
                        return makeF32vector(((Number) obj3).intValue(), ((Number) obj4).floatValue());
                    } catch (ClassCastException e33) {
                        ClassCastException classCastException33 = e33;
                        Throwable th73 = th8;
                        new WrongType(classCastException33, "make-f32vector", 2, obj4);
                        throw th73;
                    }
                } catch (ClassCastException e34) {
                    ClassCastException classCastException34 = e34;
                    Throwable th74 = th7;
                    new WrongType(classCastException34, "make-f32vector", 1, obj3);
                    throw th74;
                }
            case 78:
                try {
                    try {
                        return Float.valueOf(f32vectorRef((F32Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e35) {
                        ClassCastException classCastException35 = e35;
                        Throwable th75 = th6;
                        new WrongType(classCastException35, "f32vector-ref", 2, obj4);
                        throw th75;
                    }
                } catch (ClassCastException e36) {
                    ClassCastException classCastException36 = e36;
                    Throwable th76 = th5;
                    new WrongType(classCastException36, "f32vector-ref", 1, obj3);
                    throw th76;
                }
            case 83:
                try {
                    try {
                        return makeF64vector(((Number) obj3).intValue(), ((Number) obj4).doubleValue());
                    } catch (ClassCastException e37) {
                        ClassCastException classCastException37 = e37;
                        Throwable th77 = th4;
                        new WrongType(classCastException37, "make-f64vector", 2, obj4);
                        throw th77;
                    }
                } catch (ClassCastException e38) {
                    ClassCastException classCastException38 = e38;
                    Throwable th78 = th3;
                    new WrongType(classCastException38, "make-f64vector", 1, obj3);
                    throw th78;
                }
            case 87:
                try {
                    try {
                        return Double.valueOf(f64vectorRef((F64Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e39) {
                        ClassCastException classCastException39 = e39;
                        Throwable th79 = th2;
                        new WrongType(classCastException39, "f64vector-ref", 2, obj4);
                        throw th79;
                    }
                } catch (ClassCastException e40) {
                    ClassCastException classCastException40 = e40;
                    Throwable th80 = th;
                    new WrongType(classCastException40, "f64vector-ref", 1, obj3);
                    throw th80;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static void f64vectorSet$Ex(F64Vector v, int i, double x) {
        v.setDoubleAt(i, x);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
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
        Throwable th17;
        Throwable th18;
        Throwable th19;
        Throwable th20;
        Throwable th21;
        Throwable th22;
        Throwable th23;
        Throwable th24;
        Throwable th25;
        Throwable th26;
        Throwable th27;
        Throwable th28;
        Throwable th29;
        Throwable th30;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 7:
                try {
                    try {
                        try {
                            s8vectorSet$Ex((S8Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                            return Values.empty;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th31 = th30;
                            new WrongType(classCastException, "s8vector-set!", 3, obj6);
                            throw th31;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th32 = th29;
                        new WrongType(classCastException2, "s8vector-set!", 2, obj5);
                        throw th32;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th33 = th28;
                    new WrongType(classCastException3, "s8vector-set!", 1, obj4);
                    throw th33;
                }
            case 16:
                try {
                    try {
                        try {
                            u8vectorSet$Ex((U8Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                            return Values.empty;
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th34 = th27;
                            new WrongType(classCastException4, "u8vector-set!", 3, obj6);
                            throw th34;
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th35 = th26;
                        new WrongType(classCastException5, "u8vector-set!", 2, obj5);
                        throw th35;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th36 = th25;
                    new WrongType(classCastException6, "u8vector-set!", 1, obj4);
                    throw th36;
                }
            case 25:
                try {
                    try {
                        try {
                            s16vectorSet$Ex((S16Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                            return Values.empty;
                        } catch (ClassCastException e7) {
                            ClassCastException classCastException7 = e7;
                            Throwable th37 = th24;
                            new WrongType(classCastException7, "s16vector-set!", 3, obj6);
                            throw th37;
                        }
                    } catch (ClassCastException e8) {
                        ClassCastException classCastException8 = e8;
                        Throwable th38 = th23;
                        new WrongType(classCastException8, "s16vector-set!", 2, obj5);
                        throw th38;
                    }
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th39 = th22;
                    new WrongType(classCastException9, "s16vector-set!", 1, obj4);
                    throw th39;
                }
            case 34:
                try {
                    try {
                        try {
                            u16vectorSet$Ex((U16Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                            return Values.empty;
                        } catch (ClassCastException e10) {
                            ClassCastException classCastException10 = e10;
                            Throwable th40 = th21;
                            new WrongType(classCastException10, "u16vector-set!", 3, obj6);
                            throw th40;
                        }
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th41 = th20;
                        new WrongType(classCastException11, "u16vector-set!", 2, obj5);
                        throw th41;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th42 = th19;
                    new WrongType(classCastException12, "u16vector-set!", 1, obj4);
                    throw th42;
                }
            case 43:
                try {
                    try {
                        try {
                            s32vectorSet$Ex((S32Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                            return Values.empty;
                        } catch (ClassCastException e13) {
                            ClassCastException classCastException13 = e13;
                            Throwable th43 = th18;
                            new WrongType(classCastException13, "s32vector-set!", 3, obj6);
                            throw th43;
                        }
                    } catch (ClassCastException e14) {
                        ClassCastException classCastException14 = e14;
                        Throwable th44 = th17;
                        new WrongType(classCastException14, "s32vector-set!", 2, obj5);
                        throw th44;
                    }
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th45 = th16;
                    new WrongType(classCastException15, "s32vector-set!", 1, obj4);
                    throw th45;
                }
            case 52:
                try {
                    try {
                        try {
                            u32vectorSet$Ex((U32Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).longValue());
                            return Values.empty;
                        } catch (ClassCastException e16) {
                            ClassCastException classCastException16 = e16;
                            Throwable th46 = th15;
                            new WrongType(classCastException16, "u32vector-set!", 3, obj6);
                            throw th46;
                        }
                    } catch (ClassCastException e17) {
                        ClassCastException classCastException17 = e17;
                        Throwable th47 = th14;
                        new WrongType(classCastException17, "u32vector-set!", 2, obj5);
                        throw th47;
                    }
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th48 = th13;
                    new WrongType(classCastException18, "u32vector-set!", 1, obj4);
                    throw th48;
                }
            case 61:
                try {
                    try {
                        try {
                            s64vectorSet$Ex((S64Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).longValue());
                            return Values.empty;
                        } catch (ClassCastException e19) {
                            ClassCastException classCastException19 = e19;
                            Throwable th49 = th12;
                            new WrongType(classCastException19, "s64vector-set!", 3, obj6);
                            throw th49;
                        }
                    } catch (ClassCastException e20) {
                        ClassCastException classCastException20 = e20;
                        Throwable th50 = th11;
                        new WrongType(classCastException20, "s64vector-set!", 2, obj5);
                        throw th50;
                    }
                } catch (ClassCastException e21) {
                    ClassCastException classCastException21 = e21;
                    Throwable th51 = th10;
                    new WrongType(classCastException21, "s64vector-set!", 1, obj4);
                    throw th51;
                }
            case 70:
                try {
                    try {
                        try {
                            u64vectorSet$Ex((U64Vector) obj4, ((Number) obj5).intValue(), LangObjType.coerceIntNum(obj6));
                            return Values.empty;
                        } catch (ClassCastException e22) {
                            ClassCastException classCastException22 = e22;
                            Throwable th52 = th9;
                            new WrongType(classCastException22, "u64vector-set!", 3, obj6);
                            throw th52;
                        }
                    } catch (ClassCastException e23) {
                        ClassCastException classCastException23 = e23;
                        Throwable th53 = th8;
                        new WrongType(classCastException23, "u64vector-set!", 2, obj5);
                        throw th53;
                    }
                } catch (ClassCastException e24) {
                    ClassCastException classCastException24 = e24;
                    Throwable th54 = th7;
                    new WrongType(classCastException24, "u64vector-set!", 1, obj4);
                    throw th54;
                }
            case 79:
                try {
                    try {
                        try {
                            f32vectorSet$Ex((F32Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).floatValue());
                            return Values.empty;
                        } catch (ClassCastException e25) {
                            ClassCastException classCastException25 = e25;
                            Throwable th55 = th6;
                            new WrongType(classCastException25, "f32vector-set!", 3, obj6);
                            throw th55;
                        }
                    } catch (ClassCastException e26) {
                        ClassCastException classCastException26 = e26;
                        Throwable th56 = th5;
                        new WrongType(classCastException26, "f32vector-set!", 2, obj5);
                        throw th56;
                    }
                } catch (ClassCastException e27) {
                    ClassCastException classCastException27 = e27;
                    Throwable th57 = th4;
                    new WrongType(classCastException27, "f32vector-set!", 1, obj4);
                    throw th57;
                }
            case 88:
                try {
                    try {
                        try {
                            f64vectorSet$Ex((F64Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).doubleValue());
                            return Values.empty;
                        } catch (ClassCastException e28) {
                            ClassCastException classCastException28 = e28;
                            Throwable th58 = th3;
                            new WrongType(classCastException28, "f64vector-set!", 3, obj6);
                            throw th58;
                        }
                    } catch (ClassCastException e29) {
                        ClassCastException classCastException29 = e29;
                        Throwable th59 = th2;
                        new WrongType(classCastException29, "f64vector-set!", 2, obj5);
                        throw th59;
                    }
                } catch (ClassCastException e30) {
                    ClassCastException classCastException30 = e30;
                    Throwable th60 = th;
                    new WrongType(classCastException30, "f64vector-set!", 1, obj4);
                    throw th60;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static LList f64vector$To$List(F64Vector v) {
        return LList.makeList(v);
    }

    public static F64Vector list$To$F64vector(LList l) {
        F64Vector f64Vector;
        new F64Vector((Sequence) l);
        return f64Vector;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
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
        Throwable th17;
        Throwable th18;
        Throwable th19;
        Throwable th20;
        Throwable th21;
        Throwable th22;
        Throwable th23;
        Throwable th24;
        Throwable th25;
        Throwable th26;
        Throwable th27;
        Throwable th28;
        Throwable th29;
        Throwable th30;
        Throwable th31;
        Throwable th32;
        Throwable th33;
        Throwable th34;
        Throwable th35;
        Throwable th36;
        Throwable th37;
        Throwable th38;
        Throwable th39;
        Throwable th40;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isS8vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return makeS8vector(((Number) obj2).intValue());
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th41 = th40;
                    new WrongType(classCastException, "make-s8vector", 1, obj2);
                    throw th41;
                }
            case 5:
                try {
                    return Integer.valueOf(s8vectorLength((S8Vector) obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th42 = th39;
                    new WrongType(classCastException2, "s8vector-length", 1, obj2);
                    throw th42;
                }
            case 8:
                try {
                    return s8vector$To$List((S8Vector) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th43 = th38;
                    new WrongType(classCastException3, "s8vector->list", 1, obj2);
                    throw th43;
                }
            case 9:
                try {
                    return list$To$S8vector((LList) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th44 = th37;
                    new WrongType(classCastException4, "list->s8vector", 1, obj2);
                    throw th44;
                }
            case 10:
                return isU8vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 11:
                try {
                    return makeU8vector(((Number) obj2).intValue());
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th45 = th36;
                    new WrongType(classCastException5, "make-u8vector", 1, obj2);
                    throw th45;
                }
            case 14:
                try {
                    return Integer.valueOf(u8vectorLength((U8Vector) obj2));
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th46 = th35;
                    new WrongType(classCastException6, "u8vector-length", 1, obj2);
                    throw th46;
                }
            case 17:
                try {
                    return u8vector$To$List((U8Vector) obj2);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th47 = th34;
                    new WrongType(classCastException7, "u8vector->list", 1, obj2);
                    throw th47;
                }
            case 18:
                try {
                    return list$To$U8vector((LList) obj2);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th48 = th33;
                    new WrongType(classCastException8, "list->u8vector", 1, obj2);
                    throw th48;
                }
            case 19:
                return isS16vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 20:
                try {
                    return makeS16vector(((Number) obj2).intValue());
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th49 = th32;
                    new WrongType(classCastException9, "make-s16vector", 1, obj2);
                    throw th49;
                }
            case 23:
                try {
                    return Integer.valueOf(s16vectorLength((S16Vector) obj2));
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th50 = th31;
                    new WrongType(classCastException10, "s16vector-length", 1, obj2);
                    throw th50;
                }
            case 26:
                try {
                    return s16vector$To$List((S16Vector) obj2);
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th51 = th30;
                    new WrongType(classCastException11, "s16vector->list", 1, obj2);
                    throw th51;
                }
            case 27:
                try {
                    return list$To$S16vector((LList) obj2);
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th52 = th29;
                    new WrongType(classCastException12, "list->s16vector", 1, obj2);
                    throw th52;
                }
            case 28:
                return isU16vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 29:
                try {
                    return makeU16vector(((Number) obj2).intValue());
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th53 = th28;
                    new WrongType(classCastException13, "make-u16vector", 1, obj2);
                    throw th53;
                }
            case 32:
                try {
                    return Integer.valueOf(u16vectorLength((U16Vector) obj2));
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th54 = th27;
                    new WrongType(classCastException14, "u16vector-length", 1, obj2);
                    throw th54;
                }
            case 35:
                try {
                    return u16vector$To$List((U16Vector) obj2);
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th55 = th26;
                    new WrongType(classCastException15, "u16vector->list", 1, obj2);
                    throw th55;
                }
            case 36:
                try {
                    return list$To$U16vector((LList) obj2);
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th56 = th25;
                    new WrongType(classCastException16, "list->u16vector", 1, obj2);
                    throw th56;
                }
            case 37:
                return isS32vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 38:
                try {
                    return makeS32vector(((Number) obj2).intValue());
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th57 = th24;
                    new WrongType(classCastException17, "make-s32vector", 1, obj2);
                    throw th57;
                }
            case 41:
                try {
                    return Integer.valueOf(s32vectorLength((S32Vector) obj2));
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th58 = th23;
                    new WrongType(classCastException18, "s32vector-length", 1, obj2);
                    throw th58;
                }
            case 44:
                try {
                    return s32vector$To$List((S32Vector) obj2);
                } catch (ClassCastException e19) {
                    ClassCastException classCastException19 = e19;
                    Throwable th59 = th22;
                    new WrongType(classCastException19, "s32vector->list", 1, obj2);
                    throw th59;
                }
            case 45:
                try {
                    return list$To$S32vector((LList) obj2);
                } catch (ClassCastException e20) {
                    ClassCastException classCastException20 = e20;
                    Throwable th60 = th21;
                    new WrongType(classCastException20, "list->s32vector", 1, obj2);
                    throw th60;
                }
            case 46:
                return isU32vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 47:
                try {
                    return makeU32vector(((Number) obj2).intValue());
                } catch (ClassCastException e21) {
                    ClassCastException classCastException21 = e21;
                    Throwable th61 = th20;
                    new WrongType(classCastException21, "make-u32vector", 1, obj2);
                    throw th61;
                }
            case 50:
                try {
                    return Integer.valueOf(u32vectorLength((U32Vector) obj2));
                } catch (ClassCastException e22) {
                    ClassCastException classCastException22 = e22;
                    Throwable th62 = th19;
                    new WrongType(classCastException22, "u32vector-length", 1, obj2);
                    throw th62;
                }
            case 53:
                try {
                    return u32vector$To$List((U32Vector) obj2);
                } catch (ClassCastException e23) {
                    ClassCastException classCastException23 = e23;
                    Throwable th63 = th18;
                    new WrongType(classCastException23, "u32vector->list", 1, obj2);
                    throw th63;
                }
            case 54:
                try {
                    return list$To$U32vector((LList) obj2);
                } catch (ClassCastException e24) {
                    ClassCastException classCastException24 = e24;
                    Throwable th64 = th17;
                    new WrongType(classCastException24, "list->u32vector", 1, obj2);
                    throw th64;
                }
            case 55:
                return isS64vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 56:
                try {
                    return makeS64vector(((Number) obj2).intValue());
                } catch (ClassCastException e25) {
                    ClassCastException classCastException25 = e25;
                    Throwable th65 = th16;
                    new WrongType(classCastException25, "make-s64vector", 1, obj2);
                    throw th65;
                }
            case 59:
                try {
                    return Integer.valueOf(s64vectorLength((S64Vector) obj2));
                } catch (ClassCastException e26) {
                    ClassCastException classCastException26 = e26;
                    Throwable th66 = th15;
                    new WrongType(classCastException26, "s64vector-length", 1, obj2);
                    throw th66;
                }
            case 62:
                try {
                    return s64vector$To$List((S64Vector) obj2);
                } catch (ClassCastException e27) {
                    ClassCastException classCastException27 = e27;
                    Throwable th67 = th14;
                    new WrongType(classCastException27, "s64vector->list", 1, obj2);
                    throw th67;
                }
            case 63:
                try {
                    return list$To$S64vector((LList) obj2);
                } catch (ClassCastException e28) {
                    ClassCastException classCastException28 = e28;
                    Throwable th68 = th13;
                    new WrongType(classCastException28, "list->s64vector", 1, obj2);
                    throw th68;
                }
            case 64:
                return isU64vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 65:
                try {
                    return makeU64vector(((Number) obj2).intValue());
                } catch (ClassCastException e29) {
                    ClassCastException classCastException29 = e29;
                    Throwable th69 = th12;
                    new WrongType(classCastException29, "make-u64vector", 1, obj2);
                    throw th69;
                }
            case 68:
                try {
                    return Integer.valueOf(u64vectorLength((U64Vector) obj2));
                } catch (ClassCastException e30) {
                    ClassCastException classCastException30 = e30;
                    Throwable th70 = th11;
                    new WrongType(classCastException30, "u64vector-length", 1, obj2);
                    throw th70;
                }
            case 71:
                try {
                    return u64vector$To$List((U64Vector) obj2);
                } catch (ClassCastException e31) {
                    ClassCastException classCastException31 = e31;
                    Throwable th71 = th10;
                    new WrongType(classCastException31, "u64vector->list", 1, obj2);
                    throw th71;
                }
            case 72:
                try {
                    return list$To$U64vector((LList) obj2);
                } catch (ClassCastException e32) {
                    ClassCastException classCastException32 = e32;
                    Throwable th72 = th9;
                    new WrongType(classCastException32, "list->u64vector", 1, obj2);
                    throw th72;
                }
            case 73:
                return isF32vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 74:
                try {
                    return makeF32vector(((Number) obj2).intValue());
                } catch (ClassCastException e33) {
                    ClassCastException classCastException33 = e33;
                    Throwable th73 = th8;
                    new WrongType(classCastException33, "make-f32vector", 1, obj2);
                    throw th73;
                }
            case 77:
                try {
                    return Integer.valueOf(f32vectorLength((F32Vector) obj2));
                } catch (ClassCastException e34) {
                    ClassCastException classCastException34 = e34;
                    Throwable th74 = th7;
                    new WrongType(classCastException34, "f32vector-length", 1, obj2);
                    throw th74;
                }
            case 80:
                try {
                    return f32vector$To$List((F32Vector) obj2);
                } catch (ClassCastException e35) {
                    ClassCastException classCastException35 = e35;
                    Throwable th75 = th6;
                    new WrongType(classCastException35, "f32vector->list", 1, obj2);
                    throw th75;
                }
            case 81:
                try {
                    return list$To$F32vector((LList) obj2);
                } catch (ClassCastException e36) {
                    ClassCastException classCastException36 = e36;
                    Throwable th76 = th5;
                    new WrongType(classCastException36, "list->f32vector", 1, obj2);
                    throw th76;
                }
            case 82:
                return isF64vector(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 83:
                try {
                    return makeF64vector(((Number) obj2).intValue());
                } catch (ClassCastException e37) {
                    ClassCastException classCastException37 = e37;
                    Throwable th77 = th4;
                    new WrongType(classCastException37, "make-f64vector", 1, obj2);
                    throw th77;
                }
            case 86:
                try {
                    return Integer.valueOf(f64vectorLength((F64Vector) obj2));
                } catch (ClassCastException e38) {
                    ClassCastException classCastException38 = e38;
                    Throwable th78 = th3;
                    new WrongType(classCastException38, "f64vector-length", 1, obj2);
                    throw th78;
                }
            case 89:
                try {
                    return f64vector$To$List((F64Vector) obj2);
                } catch (ClassCastException e39) {
                    ClassCastException classCastException39 = e39;
                    Throwable th79 = th2;
                    new WrongType(classCastException39, "f64vector->list", 1, obj2);
                    throw th79;
                }
            case 90:
                try {
                    return list$To$F64vector((LList) obj2);
                } catch (ClassCastException e40) {
                    ClassCastException classCastException40 = e40;
                    Throwable th80 = th;
                    new WrongType(classCastException40, "list->f64vector", 1, obj2);
                    throw th80;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}
