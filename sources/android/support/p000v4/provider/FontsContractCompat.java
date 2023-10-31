package android.support.p000v4.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p000v4.graphics.TypefaceCompat;
import android.support.p000v4.graphics.TypefaceCompatUtil;
import android.support.p000v4.provider.SelfDestructiveThread;
import android.support.p000v4.util.C1648LruCache;
import android.support.p000v4.util.C1650SimpleArrayMap;
import android.support.p000v4.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.shaded.apache.http.HttpStatus;

/* renamed from: android.support.v4.provider.FontsContractCompat */
public class FontsContractCompat {
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread;
    private static final Comparator<byte[]> sByteArrayComparator;
    static final Object sLock;
    @GuardedBy("sLock")
    static final C1650SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> sPendingReplies;
    static final C1648LruCache<String, Typeface> sTypefaceCache;

    private FontsContractCompat() {
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$Columns */
    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";

        public Columns() {
        }
    }

    static {
        C1648LruCache<String, Typeface> lruCache;
        SelfDestructiveThread selfDestructiveThread;
        Object obj;
        C1650SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> simpleArrayMap;
        Comparator<byte[]> comparator;
        new C1648LruCache<>(16);
        sTypefaceCache = lruCache;
        new SelfDestructiveThread("fonts", 10, BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS);
        sBackgroundThread = selfDestructiveThread;
        new Object();
        sLock = obj;
        new C1650SimpleArrayMap<>();
        sPendingReplies = simpleArrayMap;
        new Comparator<byte[]>() {
            public int compare(byte[] bArr, byte[] bArr2) {
                byte[] l = bArr;
                byte[] r = bArr2;
                if (l.length != r.length) {
                    return l.length - r.length;
                }
                for (int i = 0; i < l.length; i++) {
                    if (l[i] != r[i]) {
                        return l[i] - r[i];
                    }
                }
                return 0;
            }
        };
        sByteArrayComparator = comparator;
    }

    @NonNull
    static TypefaceResult getFontInternal(Context context, FontRequest request, int i) {
        TypefaceResult typefaceResult;
        TypefaceResult typefaceResult2;
        Context context2 = context;
        int style = i;
        try {
            FontFamilyResult result = fetchFonts(context2, (CancellationSignal) null, request);
            if (result.getStatusCode() == 0) {
                Typeface typeface = TypefaceCompat.createFromFontInfo(context2, (CancellationSignal) null, result.getFonts(), style);
                TypefaceResult typefaceResult3 = r9;
                TypefaceResult typefaceResult4 = new TypefaceResult(typeface, typeface != null ? 0 : -3);
                return typefaceResult3;
            }
            new TypefaceResult((Typeface) null, result.getStatusCode() == 1 ? -2 : -3);
            return typefaceResult2;
        } catch (PackageManager.NameNotFoundException e) {
            PackageManager.NameNotFoundException nameNotFoundException = e;
            new TypefaceResult((Typeface) null, -1);
            return typefaceResult;
        }
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$TypefaceResult */
    private static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(@Nullable Typeface typeface, int result) {
            this.mTypeface = typeface;
            this.mResult = result;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void resetCache() {
        sTypefaceCache.evictAll();
    }

    /* JADX INFO: finally extract failed */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Typeface getFontSync(Context context, FontRequest fontRequest, @Nullable ResourcesCompat.FontCallback fontCallback, @Nullable Handler handler, boolean z, int i, int i2) {
        StringBuilder sb;
        Callable<TypefaceResult> callable;
        SelfDestructiveThread.ReplyCallback<TypefaceResult> replyCallback;
        SelfDestructiveThread.ReplyCallback<TypefaceResult> replyCallback2;
        SelfDestructiveThread.ReplyCallback replyCallback3;
        ArrayList arrayList;
        Context context2 = context;
        FontRequest request = fontRequest;
        ResourcesCompat.FontCallback fontCallback2 = fontCallback;
        Handler handler2 = handler;
        boolean isBlockingFetch = z;
        int timeout = i;
        int style = i2;
        new StringBuilder();
        String id = sb.append(request.getIdentifier()).append("-").append(style).toString();
        Typeface cached = sTypefaceCache.get(id);
        if (cached != null) {
            if (fontCallback2 != null) {
                fontCallback2.onFontRetrieved(cached);
            }
            return cached;
        } else if (!isBlockingFetch || timeout != -1) {
            final Context context3 = context2;
            final FontRequest fontRequest2 = request;
            final int i3 = style;
            final String str = id;
            new Callable<TypefaceResult>() {
                public TypefaceResult call() throws Exception {
                    TypefaceResult typeface = FontsContractCompat.getFontInternal(context3, fontRequest2, i3);
                    if (typeface.mTypeface != null) {
                        Typeface put = FontsContractCompat.sTypefaceCache.put(str, typeface.mTypeface);
                    }
                    return typeface;
                }
            };
            Callable<TypefaceResult> fetcher = callable;
            if (isBlockingFetch) {
                try {
                    return ((TypefaceResult) sBackgroundThread.postAndWait(fetcher, timeout)).mTypeface;
                } catch (InterruptedException e) {
                    InterruptedException interruptedException = e;
                    return null;
                }
            } else {
                if (fontCallback2 == null) {
                    replyCallback2 = null;
                } else {
                    replyCallback2 = replyCallback;
                    final ResourcesCompat.FontCallback fontCallback3 = fontCallback2;
                    final Handler handler3 = handler2;
                    new SelfDestructiveThread.ReplyCallback<TypefaceResult>() {
                        public void onReply(TypefaceResult typefaceResult) {
                            TypefaceResult typeface = typefaceResult;
                            if (typeface == null) {
                                fontCallback3.callbackFailAsync(1, handler3);
                            } else if (typeface.mResult == 0) {
                                fontCallback3.callbackSuccessAsync(typeface.mTypeface, handler3);
                            } else {
                                fontCallback3.callbackFailAsync(typeface.mResult, handler3);
                            }
                        }
                    };
                }
                SelfDestructiveThread.ReplyCallback<TypefaceResult> reply = replyCallback2;
                Object obj = sLock;
                Object obj2 = obj;
                synchronized (obj) {
                    try {
                        if (sPendingReplies.containsKey(id)) {
                            if (reply != null) {
                                boolean add = sPendingReplies.get(id).add(reply);
                            }
                            return null;
                        }
                        if (reply != null) {
                            new ArrayList();
                            ArrayList arrayList2 = arrayList;
                            boolean add2 = arrayList2.add(reply);
                            ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> put = sPendingReplies.put(id, arrayList2);
                        }
                        final String str2 = id;
                        new SelfDestructiveThread.ReplyCallback<TypefaceResult>() {
                            /* JADX INFO: finally extract failed */
                            public void onReply(TypefaceResult typefaceResult) {
                                TypefaceResult typeface = typefaceResult;
                                Object obj = FontsContractCompat.sLock;
                                Object obj2 = obj;
                                synchronized (obj) {
                                    try {
                                        ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> replies = FontsContractCompat.sPendingReplies.get(str2);
                                        if (replies == null) {
                                            return;
                                        }
                                        ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> remove = FontsContractCompat.sPendingReplies.remove(str2);
                                        for (int i = 0; i < replies.size(); i++) {
                                            replies.get(i).onReply(typeface);
                                        }
                                    } catch (Throwable th) {
                                        while (true) {
                                            Throwable th2 = th;
                                            Object obj3 = obj2;
                                            throw th2;
                                        }
                                    }
                                }
                            }
                        };
                        sBackgroundThread.postAndReply(fetcher, replyCallback3);
                        return null;
                    } catch (Throwable th) {
                        while (true) {
                            Throwable th2 = th;
                            Object obj3 = obj2;
                            throw th2;
                        }
                    }
                }
            }
        } else {
            TypefaceResult typefaceResult = getFontInternal(context2, request, style);
            if (fontCallback2 != null) {
                if (typefaceResult.mResult == 0) {
                    fontCallback2.callbackSuccessAsync(typefaceResult.mTypeface, handler2);
                } else {
                    fontCallback2.callbackFailAsync(typefaceResult.mResult, handler2);
                }
            }
            return typefaceResult.mTypeface;
        }
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$FontInfo */
    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public FontInfo(@NonNull Uri uri, @IntRange(from = 0) int ttcIndex, @IntRange(from = 1, mo109to = 1000) int weight, boolean italic, int resultCode) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = ttcIndex;
            this.mWeight = weight;
            this.mItalic = italic;
            this.mResultCode = resultCode;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 0)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @IntRange(from = 1, mo109to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$FontFamilyResult */
    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public FontFamilyResult(int statusCode, @Nullable FontInfo[] fonts) {
            this.mStatusCode = statusCode;
            this.mFonts = fonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$FontRequestCallback */
    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final int RESULT_OK = 0;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        /* renamed from: android.support.v4.provider.FontsContractCompat$FontRequestCallback$FontRequestFailReason */
        public @interface FontRequestFailReason {
        }

        public FontRequestCallback() {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }

        public void onTypefaceRequestFailed(int reason) {
        }
    }

    public static void requestFont(@NonNull Context context, @NonNull FontRequest request, @NonNull FontRequestCallback callback, @NonNull Handler handler) {
        Handler callerThreadHandler;
        Runnable runnable;
        new Handler();
        final Context context2 = context;
        final FontRequest fontRequest = request;
        final Handler handler2 = callerThreadHandler;
        final FontRequestCallback fontRequestCallback = callback;
        new Runnable() {
            public void run() {
                Runnable runnable;
                Runnable runnable2;
                Runnable runnable3;
                Runnable runnable4;
                Runnable runnable5;
                Runnable runnable6;
                Runnable runnable7;
                Runnable runnable8;
                Runnable runnable9;
                try {
                    FontFamilyResult result = FontsContractCompat.fetchFonts(context2, (CancellationSignal) null, fontRequest);
                    if (result.getStatusCode() != 0) {
                        switch (result.getStatusCode()) {
                            case 1:
                                new Runnable(this) {
                                    final /* synthetic */ C03454 this$0;

                                    {
                                        this.this$0 = this$0;
                                    }

                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-2);
                                    }
                                };
                                boolean post = handler2.post(runnable8);
                                return;
                            case 2:
                                new Runnable(this) {
                                    final /* synthetic */ C03454 this$0;

                                    {
                                        this.this$0 = this$0;
                                    }

                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                };
                                boolean post2 = handler2.post(runnable7);
                                return;
                            default:
                                new Runnable(this) {
                                    final /* synthetic */ C03454 this$0;

                                    {
                                        this.this$0 = this$0;
                                    }

                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                };
                                boolean post3 = handler2.post(runnable9);
                                return;
                        }
                    } else {
                        FontInfo[] fonts = result.getFonts();
                        if (fonts == null || fonts.length == 0) {
                            new Runnable(this) {
                                final /* synthetic */ C03454 this$0;

                                {
                                    this.this$0 = this$0;
                                }

                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(1);
                                }
                            };
                            boolean post4 = handler2.post(runnable2);
                            return;
                        }
                        FontInfo[] fontInfoArr = fonts;
                        int length = fontInfoArr.length;
                        for (int i = 0; i < length; i++) {
                            FontInfo font = fontInfoArr[i];
                            if (font.getResultCode() != 0) {
                                int resultCode = font.getResultCode();
                                if (resultCode < 0) {
                                    new Runnable(this) {
                                        final /* synthetic */ C03454 this$0;

                                        {
                                            this.this$0 = this$0;
                                        }

                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(-3);
                                        }
                                    };
                                    boolean post5 = handler2.post(runnable6);
                                    return;
                                }
                                final int i2 = resultCode;
                                new Runnable(this) {
                                    final /* synthetic */ C03454 this$0;

                                    {
                                        this.this$0 = this$0;
                                    }

                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(i2);
                                    }
                                };
                                boolean post6 = handler2.post(runnable5);
                                return;
                            }
                        }
                        Typeface typeface = FontsContractCompat.buildTypeface(context2, (CancellationSignal) null, fonts);
                        if (typeface == null) {
                            new Runnable(this) {
                                final /* synthetic */ C03454 this$0;

                                {
                                    this.this$0 = this$0;
                                }

                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            };
                            boolean post7 = handler2.post(runnable4);
                            return;
                        }
                        final Typeface typeface2 = typeface;
                        new Runnable(this) {
                            final /* synthetic */ C03454 this$0;

                            {
                                this.this$0 = this$0;
                            }

                            public void run() {
                                fontRequestCallback.onTypefaceRetrieved(typeface2);
                            }
                        };
                        boolean post8 = handler2.post(runnable3);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    PackageManager.NameNotFoundException nameNotFoundException = e;
                    new Runnable(this) {
                        final /* synthetic */ C03454 this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-1);
                        }
                    };
                    boolean post9 = handler2.post(runnable);
                }
            }
        };
        boolean post = handler.post(runnable);
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fonts) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fonts, 0);
    }

    @RequiresApi(19)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fonts, CancellationSignal cancellationSignal) {
        HashMap hashMap;
        Context context2 = context;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        new HashMap();
        HashMap hashMap2 = hashMap;
        FontInfo[] fontInfoArr = fonts;
        int length = fontInfoArr.length;
        for (int i = 0; i < length; i++) {
            FontInfo font = fontInfoArr[i];
            if (font.getResultCode() == 0) {
                Uri uri = font.getUri();
                if (!hashMap2.containsKey(uri)) {
                    Object put = hashMap2.put(uri, TypefaceCompatUtil.mmap(context2, cancellationSignal2, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap2);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        FontFamilyResult fontFamilyResult;
        FontFamilyResult fontFamilyResult2;
        Context context2 = context;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        FontRequest request = fontRequest;
        ProviderInfo providerInfo = getProvider(context2.getPackageManager(), request, context2.getResources());
        if (providerInfo == null) {
            new FontFamilyResult(1, (FontInfo[]) null);
            return fontFamilyResult2;
        }
        new FontFamilyResult(0, getFontFromProvider(context2, request, providerInfo.authority, cancellationSignal2));
        return fontFamilyResult;
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Nullable
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws PackageManager.NameNotFoundException {
        List<byte[]> list;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        PackageManager packageManager2 = packageManager;
        FontRequest request = fontRequest;
        Resources resources2 = resources;
        String providerAuthority = request.getProviderAuthority();
        ProviderInfo info = packageManager2.resolveContentProvider(providerAuthority, 0);
        if (info == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new PackageManager.NameNotFoundException(sb2.append("No package found for authority: ").append(providerAuthority).toString());
            throw th3;
        } else if (!info.packageName.equals(request.getProviderPackage())) {
            Throwable th4 = th;
            new StringBuilder();
            new PackageManager.NameNotFoundException(sb.append("Found content provider ").append(providerAuthority).append(", but package was not ").append(request.getProviderPackage()).toString());
            throw th4;
        } else {
            List<byte[]> signatures = convertToByteArrayList(packageManager2.getPackageInfo(info.packageName, 64).signatures);
            Collections.sort(signatures, sByteArrayComparator);
            List<List<byte[]>> requestCertificatesList = getCertificates(request, resources2);
            for (int i = 0; i < requestCertificatesList.size(); i++) {
                new ArrayList<>(requestCertificatesList.get(i));
                List<byte[]> requestSignatures = list;
                Collections.sort(requestSignatures, sByteArrayComparator);
                if (equalsByteArrayList(signatures, requestSignatures)) {
                    return info;
                }
            }
            return null;
        }
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        FontRequest request = fontRequest;
        Resources resources2 = resources;
        if (request.getCertificates() != null) {
            return request.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources2, request.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        List<byte[]> signatures = list;
        List<byte[]> requestSignatures = list2;
        if (signatures.size() != requestSignatures.size()) {
            return false;
        }
        for (int i = 0; i < signatures.size(); i++) {
            if (!Arrays.equals(signatures.get(i), requestSignatures.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        List<byte[]> list;
        Signature[] signatures = signatureArr;
        new ArrayList();
        List<byte[]> shas = list;
        for (int i = 0; i < signatures.length; i++) {
            boolean add = shas.add(signatures[i].toByteArray());
        }
        return shas;
    }

    /* JADX INFO: finally extract failed */
    @VisibleForTesting
    @NonNull
    static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        ArrayList arrayList;
        Uri.Builder builder;
        Uri.Builder builder2;
        Cursor cursor;
        ArrayList arrayList2;
        Uri fileUri;
        Object obj;
        Context context2 = context;
        FontRequest request = fontRequest;
        String authority = str;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        new ArrayList();
        ArrayList arrayList3 = arrayList;
        new Uri.Builder();
        Uri uri = builder.scheme("content").authority(authority).build();
        new Uri.Builder();
        Uri fileBaseUri = builder2.scheme("content").authority(authority).appendPath("file").build();
        try {
            if (Build.VERSION.SDK_INT > 16) {
                String[] strArr = new String[7];
                strArr[0] = "_id";
                String[] strArr2 = strArr;
                strArr2[1] = Columns.FILE_ID;
                String[] strArr3 = strArr2;
                strArr3[2] = Columns.TTC_INDEX;
                String[] strArr4 = strArr3;
                strArr4[3] = Columns.VARIATION_SETTINGS;
                String[] strArr5 = strArr4;
                strArr5[4] = Columns.WEIGHT;
                String[] strArr6 = strArr5;
                strArr6[5] = Columns.ITALIC;
                String[] strArr7 = strArr6;
                strArr7[6] = Columns.RESULT_CODE;
                cursor = context2.getContentResolver().query(uri, strArr7, "query = ?", new String[]{request.getQuery()}, (String) null, cancellationSignal2);
            } else {
                String[] strArr8 = new String[7];
                strArr8[0] = "_id";
                String[] strArr9 = strArr8;
                strArr9[1] = Columns.FILE_ID;
                String[] strArr10 = strArr9;
                strArr10[2] = Columns.TTC_INDEX;
                String[] strArr11 = strArr10;
                strArr11[3] = Columns.VARIATION_SETTINGS;
                String[] strArr12 = strArr11;
                strArr12[4] = Columns.WEIGHT;
                String[] strArr13 = strArr12;
                strArr13[5] = Columns.ITALIC;
                String[] strArr14 = strArr13;
                strArr14[6] = Columns.RESULT_CODE;
                cursor = context2.getContentResolver().query(uri, strArr14, "query = ?", new String[]{request.getQuery()}, (String) null);
            }
            if (cursor != null && cursor.getCount() > 0) {
                int resultCodeColumnIndex = cursor.getColumnIndex(Columns.RESULT_CODE);
                new ArrayList();
                arrayList3 = arrayList2;
                int idColumnIndex = cursor.getColumnIndex("_id");
                int fileIdColumnIndex = cursor.getColumnIndex(Columns.FILE_ID);
                int ttcIndexColumnIndex = cursor.getColumnIndex(Columns.TTC_INDEX);
                int weightColumnIndex = cursor.getColumnIndex(Columns.WEIGHT);
                int italicColumnIndex = cursor.getColumnIndex(Columns.ITALIC);
                while (cursor.moveToNext()) {
                    int resultCode = resultCodeColumnIndex != -1 ? cursor.getInt(resultCodeColumnIndex) : 0;
                    int ttcIndex = ttcIndexColumnIndex != -1 ? cursor.getInt(ttcIndexColumnIndex) : 0;
                    if (fileIdColumnIndex == -1) {
                        fileUri = ContentUris.withAppendedId(uri, cursor.getLong(idColumnIndex));
                    } else {
                        fileUri = ContentUris.withAppendedId(fileBaseUri, cursor.getLong(fileIdColumnIndex));
                    }
                    new FontInfo(fileUri, ttcIndex, weightColumnIndex != -1 ? cursor.getInt(weightColumnIndex) : HttpStatus.SC_BAD_REQUEST, italicColumnIndex != -1 && cursor.getInt(italicColumnIndex) == 1, resultCode);
                    boolean add = arrayList3.add(obj);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return (FontInfo[]) arrayList3.toArray(new FontInfo[0]);
        } catch (Throwable th) {
            Throwable th2 = th;
            if (0 != 0) {
                Cursor cursor2 = null;
                cursor2.close();
            }
            throw th2;
        }
    }
}
