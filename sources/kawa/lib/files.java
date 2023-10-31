package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.FileUtils;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.io.File;
import java.io.IOException;
import kawa.standard.readchar;

/* compiled from: files.scm */
public class files extends ModuleBody {
    public static final ModuleMethod $Mn$Grpathname;
    public static final ModuleMethod $Pcfile$Mnseparator;
    public static final files $instance;
    static final SimpleSymbol Lit0;
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
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod URI$Qu;
    public static final ModuleMethod absolute$Mnpath$Qu;
    public static final ModuleMethod copy$Mnfile;
    public static final ModuleMethod create$Mndirectory;
    public static final ModuleMethod delete$Mnfile;
    public static final ModuleMethod directory$Mnfiles;
    public static final ModuleMethod file$Mndirectory$Qu;
    public static final ModuleMethod file$Mnexists$Qu;
    public static final ModuleMethod file$Mnreadable$Qu;
    public static final ModuleMethod file$Mnwritable$Qu;
    public static final ModuleMethod filepath$Qu;
    public static final ModuleMethod make$Mntemporary$Mnfile;
    public static final ModuleMethod path$Mnauthority;
    public static final ModuleMethod path$Mndirectory;
    public static final ModuleMethod path$Mnextension;
    public static final ModuleMethod path$Mnfile;
    public static final ModuleMethod path$Mnfragment;
    public static final ModuleMethod path$Mnhost;
    public static final ModuleMethod path$Mnlast;
    public static final ModuleMethod path$Mnparent;
    public static final ModuleMethod path$Mnport;
    public static final ModuleMethod path$Mnquery;
    public static final ModuleMethod path$Mnscheme;
    public static final ModuleMethod path$Mnuser$Mninfo;
    public static final ModuleMethod path$Qu;
    public static final ModuleMethod rename$Mnfile;
    public static final ModuleMethod resolve$Mnuri;
    public static final ModuleMethod system$Mntmpdir;

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
        files files;
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
        new SimpleSymbol("make-temporary-file");
        Lit29 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("resolve-uri");
        Lit28 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("system-tmpdir");
        Lit27 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("%file-separator");
        Lit26 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("->pathname");
        Lit25 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("directory-files");
        Lit24 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("create-directory");
        Lit23 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("copy-file");
        Lit22 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("rename-file");
        Lit21 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("delete-file");
        Lit20 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("file-writable?");
        Lit19 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("file-readable?");
        Lit18 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("file-directory?");
        Lit17 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("file-exists?");
        Lit16 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("path-fragment");
        Lit15 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("path-query");
        Lit14 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("path-port");
        Lit13 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("path-extension");
        Lit12 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("path-last");
        Lit11 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("path-parent");
        Lit10 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("path-directory");
        Lit9 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("path-file");
        Lit8 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("path-host");
        Lit7 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("path-user-info");
        Lit6 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("path-authority");
        Lit5 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("path-scheme");
        Lit4 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("absolute-path?");
        Lit3 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("URI?");
        Lit2 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("filepath?");
        Lit1 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("path?");
        Lit0 = (SimpleSymbol) simpleSymbol30.readResolve();
        new files();
        $instance = files;
        files files2 = $instance;
        new ModuleMethod(files2, 1, Lit0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Qu = moduleMethod;
        new ModuleMethod(files2, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        filepath$Qu = moduleMethod2;
        new ModuleMethod(files2, 3, Lit2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        URI$Qu = moduleMethod3;
        new ModuleMethod(files2, 4, Lit3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        absolute$Mnpath$Qu = moduleMethod4;
        new ModuleMethod(files2, 5, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnscheme = moduleMethod5;
        new ModuleMethod(files2, 6, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnauthority = moduleMethod6;
        new ModuleMethod(files2, 7, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnuser$Mninfo = moduleMethod7;
        new ModuleMethod(files2, 8, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnhost = moduleMethod8;
        new ModuleMethod(files2, 9, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnfile = moduleMethod9;
        new ModuleMethod(files2, 10, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mndirectory = moduleMethod10;
        new ModuleMethod(files2, 11, Lit10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnparent = moduleMethod11;
        new ModuleMethod(files2, 12, Lit11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnlast = moduleMethod12;
        new ModuleMethod(files2, 13, Lit12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnextension = moduleMethod13;
        new ModuleMethod(files2, 14, Lit13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnport = moduleMethod14;
        new ModuleMethod(files2, 15, Lit14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnquery = moduleMethod15;
        new ModuleMethod(files2, 16, Lit15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnfragment = moduleMethod16;
        new ModuleMethod(files2, 17, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnexists$Qu = moduleMethod17;
        new ModuleMethod(files2, 18, Lit17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mndirectory$Qu = moduleMethod18;
        new ModuleMethod(files2, 19, Lit18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnreadable$Qu = moduleMethod19;
        new ModuleMethod(files2, 20, Lit19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnwritable$Qu = moduleMethod20;
        new ModuleMethod(files2, 21, Lit20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        delete$Mnfile = moduleMethod21;
        new ModuleMethod(files2, 22, Lit21, 8194);
        rename$Mnfile = moduleMethod22;
        new ModuleMethod(files2, 23, Lit22, 8194);
        copy$Mnfile = moduleMethod23;
        new ModuleMethod(files2, 24, Lit23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        create$Mndirectory = moduleMethod24;
        new ModuleMethod(files2, 25, Lit24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        directory$Mnfiles = moduleMethod25;
        new ModuleMethod(files2, 26, Lit25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Mn$Grpathname = moduleMethod26;
        new ModuleMethod(files2, 27, Lit26, 0);
        $Pcfile$Mnseparator = moduleMethod27;
        new ModuleMethod(files2, 28, Lit27, 0);
        system$Mntmpdir = moduleMethod28;
        new ModuleMethod(files2, 29, Lit28, 8194);
        resolve$Mnuri = moduleMethod29;
        new ModuleMethod(files2, 30, Lit29, 4096);
        make$Mntemporary$Mnfile = moduleMethod30;
        $instance.run();
    }

    public files() {
        ModuleInfo.register(this);
    }

    public static FilePath makeTemporaryFile() {
        return makeTemporaryFile("kawa~d.tmp");
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static boolean isPath(Object path) {
        return path instanceof Path;
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
            case 3:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 4:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (Path.coerceToPathOrNull(obj3) == null) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 5:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (Path.coerceToPathOrNull(obj5) == null) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 6:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (Path.coerceToPathOrNull(obj7) == null) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 7:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (Path.coerceToPathOrNull(obj9) == null) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 8:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (Path.coerceToPathOrNull(obj11) == null) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 9:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (Path.coerceToPathOrNull(obj13) == null) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (Path.coerceToPathOrNull(obj15) == null) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 11:
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (Path.coerceToPathOrNull(obj17) == null) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 12:
                CallContext callContext11 = callContext2;
                Object obj19 = obj2;
                Object obj20 = obj19;
                if (Path.coerceToPathOrNull(obj19) == null) {
                    return -786431;
                }
                callContext11.value1 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 13:
                CallContext callContext12 = callContext2;
                Object obj21 = obj2;
                Object obj22 = obj21;
                if (Path.coerceToPathOrNull(obj21) == null) {
                    return -786431;
                }
                callContext12.value1 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 14:
                CallContext callContext13 = callContext2;
                Object obj23 = obj2;
                Object obj24 = obj23;
                if (Path.coerceToPathOrNull(obj23) == null) {
                    return -786431;
                }
                callContext13.value1 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 15:
                CallContext callContext14 = callContext2;
                Object obj25 = obj2;
                Object obj26 = obj25;
                if (Path.coerceToPathOrNull(obj25) == null) {
                    return -786431;
                }
                callContext14.value1 = obj26;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 16:
                CallContext callContext15 = callContext2;
                Object obj27 = obj2;
                Object obj28 = obj27;
                if (Path.coerceToPathOrNull(obj27) == null) {
                    return -786431;
                }
                callContext15.value1 = obj28;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 17:
                CallContext callContext16 = callContext2;
                Object obj29 = obj2;
                Object obj30 = obj29;
                if (Path.coerceToPathOrNull(obj29) == null) {
                    return -786431;
                }
                callContext16.value1 = obj30;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 18:
                CallContext callContext17 = callContext2;
                Object obj31 = obj2;
                Object obj32 = obj31;
                if (Path.coerceToPathOrNull(obj31) == null) {
                    return -786431;
                }
                callContext17.value1 = obj32;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 19:
                CallContext callContext18 = callContext2;
                Object obj33 = obj2;
                Object obj34 = obj33;
                if (FilePath.coerceToFilePathOrNull(obj33) == null) {
                    return -786431;
                }
                callContext18.value1 = obj34;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 20:
                CallContext callContext19 = callContext2;
                Object obj35 = obj2;
                Object obj36 = obj35;
                if (FilePath.coerceToFilePathOrNull(obj35) == null) {
                    return -786431;
                }
                callContext19.value1 = obj36;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 21:
                CallContext callContext20 = callContext2;
                Object obj37 = obj2;
                Object obj38 = obj37;
                if (FilePath.coerceToFilePathOrNull(obj37) == null) {
                    return -786431;
                }
                callContext20.value1 = obj38;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 24:
                CallContext callContext21 = callContext2;
                Object obj39 = obj2;
                Object obj40 = obj39;
                if (FilePath.coerceToFilePathOrNull(obj39) == null) {
                    return -786431;
                }
                callContext21.value1 = obj40;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 25:
                CallContext callContext22 = callContext2;
                Object obj41 = obj2;
                Object obj42 = obj41;
                if (FilePath.coerceToFilePathOrNull(obj41) == null) {
                    return -786431;
                }
                callContext22.value1 = obj42;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 26:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 30:
                CallContext callContext23 = callContext2;
                Object obj43 = obj2;
                Object obj44 = obj43;
                if (!(obj43 instanceof CharSequence)) {
                    return -786431;
                }
                callContext23.value1 = obj44;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static boolean isFilepath(Object path) {
        return path instanceof FilePath;
    }

    public static boolean URI$Qu(Object path) {
        return path instanceof URIPath;
    }

    public static boolean isAbsolutePath(Path path) {
        return path.isAbsolute();
    }

    public static Object pathScheme(Path p) {
        String s = p.getScheme();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathAuthority(Path p) {
        String s = p.getAuthority();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathUserInfo(Path p) {
        String s = p.getUserInfo();
        return s == null ? Boolean.FALSE : s;
    }

    public static String pathHost(Path p) {
        return p.getHost();
    }

    public static Object pathFile(Path p) {
        String s = p.getPath();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathDirectory(Path p) {
        Path s = p.getDirectory();
        return s == null ? Boolean.FALSE : s.toString();
    }

    public static Object pathParent(Path p) {
        Path s = p.getParent();
        return s == null ? Boolean.FALSE : s.toString();
    }

    public static Object pathLast(Path p) {
        String s = p.getLast();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathExtension(Path p) {
        String s = p.getExtension();
        return s == null ? Boolean.FALSE : s;
    }

    public static int pathPort(Path p) {
        return p.getPort();
    }

    public static Object pathQuery(Path p) {
        String s = p.getQuery();
        return s == null ? Boolean.FALSE : s;
    }

    public static Object pathFragment(Path p) {
        String s = p.getFragment();
        return s == null ? Boolean.FALSE : s;
    }

    public static boolean isFileExists(Path file) {
        return file.exists();
    }

    public static boolean isFileDirectory(Path file) {
        return file.isDirectory();
    }

    public static boolean isFileReadable(FilePath file) {
        return file.toFile().canRead();
    }

    public static boolean isFileWritable(FilePath file) {
        return file.toFile().canWrite();
    }

    public static void deleteFile(FilePath filePath) {
        Throwable th;
        FilePath file = filePath;
        if (!file.delete()) {
            Throwable th2 = th;
            Object[] objArr = new Object[2];
            objArr[0] = "cannot delete ~a";
            Object[] objArr2 = objArr;
            objArr2[1] = file;
            new IOException(Format.formatToString(0, objArr2).toString());
            throw th2;
        }
    }

    public static boolean renameFile(FilePath oldname, FilePath newname) {
        return oldname.toFile().renameTo(newname.toFile());
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 22:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (FilePath.coerceToFilePathOrNull(obj5) == null) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (FilePath.coerceToFilePathOrNull(obj7) == null) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 23:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (Path.coerceToPathOrNull(obj9) == null) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (Path.coerceToPathOrNull(obj11) == null) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 29:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (Path.coerceToPathOrNull(obj13) == null) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                CallContext callContext8 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (Path.coerceToPathOrNull(obj15) == null) {
                    return -786430;
                }
                callContext8.value2 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static void copyFile(Path from, Path to) {
        InPort openInputFile = ports.openInputFile(from);
        OutPort out = ports.openOutputFile(to);
        InPort in = openInputFile;
        Object apply1 = readchar.readChar.apply1(in);
        while (true) {
            Object ch = apply1;
            if (!ports.isEofObject(ch)) {
                ports.writeChar(ch, out);
                apply1 = readchar.readChar.apply1(in);
            } else {
                Object closeOutputPort = ports.closeOutputPort(out);
                Object closeInputPort = ports.closeInputPort(in);
                return;
            }
        }
    }

    public static boolean createDirectory(FilePath dirname) {
        return dirname.toFile().mkdir();
    }

    public static Object directoryFiles(FilePath dir) {
        Object makeList;
        File file = r6;
        File file2 = dir.toFile();
        File file3 = new File(file2 == null ? null : file2.toString());
        String[] files = file.list();
        if (files == null) {
            makeList = Boolean.FALSE;
        } else {
            makeList = LList.makeList(files, 0);
        }
        return makeList;
    }

    public static Path $To$Pathname(Object filename) {
        return Path.valueOf(filename);
    }

    public static String $PcFileSeparator() {
        return System.getProperty("file.separator");
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 27:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 28:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 30:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod2, callContext2);
        }
    }

    public static String systemTmpdir() {
        String name;
        String name2 = System.getProperty("java.io.tmpdir");
        if (name2 != null) {
            name = name2;
        } else {
            name = IsEqual.apply($PcFileSeparator(), "\\") ? "C:\\temp" : "/tmp";
        }
        return name;
    }

    public static Path resolveUri(Path uri, Path base) {
        return base.resolve(uri);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 22:
                try {
                    try {
                        return renameFile(FilePath.makeFilePath(obj3), FilePath.makeFilePath(obj4)) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th7 = th6;
                        new WrongType(classCastException, "rename-file", 2, obj4);
                        throw th7;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th8 = th5;
                    new WrongType(classCastException2, "rename-file", 1, obj3);
                    throw th8;
                }
            case 23:
                try {
                    try {
                        copyFile(Path.valueOf(obj3), Path.valueOf(obj4));
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th9 = th4;
                        new WrongType(classCastException3, "copy-file", 2, obj4);
                        throw th9;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th10 = th3;
                    new WrongType(classCastException4, "copy-file", 1, obj3);
                    throw th10;
                }
            case 29:
                try {
                    try {
                        return resolveUri(Path.valueOf(obj3), Path.valueOf(obj4));
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th11 = th2;
                        new WrongType(classCastException5, "resolve-uri", 2, obj4);
                        throw th11;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th12 = th;
                    new WrongType(classCastException6, "resolve-uri", 1, obj3);
                    throw th12;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static FilePath makeTemporaryFile(CharSequence fmt) {
        return FilePath.makeFilePath(FileUtils.createTempFile(fmt.toString()));
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        switch (moduleMethod2.selector) {
            case 27:
                return $PcFileSeparator();
            case 28:
                return systemTmpdir();
            case 30:
                return makeTemporaryFile();
            default:
                return super.apply0(moduleMethod2);
        }
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
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isPath(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                return isFilepath(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return URI$Qu(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                try {
                    return isAbsolutePath(Path.valueOf(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th22 = th21;
                    new WrongType(classCastException, "absolute-path?", 1, obj2);
                    throw th22;
                }
            case 5:
                try {
                    return pathScheme(Path.valueOf(obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th23 = th20;
                    new WrongType(classCastException2, "path-scheme", 1, obj2);
                    throw th23;
                }
            case 6:
                try {
                    return pathAuthority(Path.valueOf(obj2));
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th24 = th19;
                    new WrongType(classCastException3, "path-authority", 1, obj2);
                    throw th24;
                }
            case 7:
                try {
                    return pathUserInfo(Path.valueOf(obj2));
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th25 = th18;
                    new WrongType(classCastException4, "path-user-info", 1, obj2);
                    throw th25;
                }
            case 8:
                try {
                    return pathHost(Path.valueOf(obj2));
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th26 = th17;
                    new WrongType(classCastException5, "path-host", 1, obj2);
                    throw th26;
                }
            case 9:
                try {
                    return pathFile(Path.valueOf(obj2));
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th27 = th16;
                    new WrongType(classCastException6, "path-file", 1, obj2);
                    throw th27;
                }
            case 10:
                try {
                    return pathDirectory(Path.valueOf(obj2));
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th28 = th15;
                    new WrongType(classCastException7, "path-directory", 1, obj2);
                    throw th28;
                }
            case 11:
                try {
                    return pathParent(Path.valueOf(obj2));
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th29 = th14;
                    new WrongType(classCastException8, "path-parent", 1, obj2);
                    throw th29;
                }
            case 12:
                try {
                    return pathLast(Path.valueOf(obj2));
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th30 = th13;
                    new WrongType(classCastException9, "path-last", 1, obj2);
                    throw th30;
                }
            case 13:
                try {
                    return pathExtension(Path.valueOf(obj2));
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th31 = th12;
                    new WrongType(classCastException10, "path-extension", 1, obj2);
                    throw th31;
                }
            case 14:
                try {
                    return Integer.valueOf(pathPort(Path.valueOf(obj2)));
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th32 = th11;
                    new WrongType(classCastException11, "path-port", 1, obj2);
                    throw th32;
                }
            case 15:
                try {
                    return pathQuery(Path.valueOf(obj2));
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th33 = th10;
                    new WrongType(classCastException12, "path-query", 1, obj2);
                    throw th33;
                }
            case 16:
                try {
                    return pathFragment(Path.valueOf(obj2));
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th34 = th9;
                    new WrongType(classCastException13, "path-fragment", 1, obj2);
                    throw th34;
                }
            case 17:
                try {
                    return isFileExists(Path.valueOf(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th35 = th8;
                    new WrongType(classCastException14, "file-exists?", 1, obj2);
                    throw th35;
                }
            case 18:
                try {
                    return isFileDirectory(Path.valueOf(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th36 = th7;
                    new WrongType(classCastException15, "file-directory?", 1, obj2);
                    throw th36;
                }
            case 19:
                try {
                    return isFileReadable(FilePath.makeFilePath(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th37 = th6;
                    new WrongType(classCastException16, "file-readable?", 1, obj2);
                    throw th37;
                }
            case 20:
                try {
                    return isFileWritable(FilePath.makeFilePath(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th38 = th5;
                    new WrongType(classCastException17, "file-writable?", 1, obj2);
                    throw th38;
                }
            case 21:
                try {
                    deleteFile(FilePath.makeFilePath(obj2));
                    return Values.empty;
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th39 = th4;
                    new WrongType(classCastException18, "delete-file", 1, obj2);
                    throw th39;
                }
            case 24:
                try {
                    return createDirectory(FilePath.makeFilePath(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e19) {
                    ClassCastException classCastException19 = e19;
                    Throwable th40 = th3;
                    new WrongType(classCastException19, "create-directory", 1, obj2);
                    throw th40;
                }
            case 25:
                try {
                    return directoryFiles(FilePath.makeFilePath(obj2));
                } catch (ClassCastException e20) {
                    ClassCastException classCastException20 = e20;
                    Throwable th41 = th2;
                    new WrongType(classCastException20, "directory-files", 1, obj2);
                    throw th41;
                }
            case 26:
                return $To$Pathname(obj2);
            case 30:
                try {
                    return makeTemporaryFile((CharSequence) obj2);
                } catch (ClassCastException e21) {
                    ClassCastException classCastException21 = e21;
                    Throwable th42 = th;
                    new WrongType(classCastException21, "make-temporary-file", 1, obj2);
                    throw th42;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}
