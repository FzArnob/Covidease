package android.support.p000v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.support.p000v4.provider.FontsContractCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.shaded.apache.http.HttpStatus;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompatBaseImpl */
class TypefaceCompatBaseImpl {
    private static final String CACHE_FILE_PREFIX = "cached_font_";
    private static final String TAG = "TypefaceCompatBaseImpl";

    /* renamed from: android.support.v4.graphics.TypefaceCompatBaseImpl$StyleExtractor */
    private interface StyleExtractor<T> {
        int getWeight(T t);

        boolean isItalic(T t);
    }

    TypefaceCompatBaseImpl() {
    }

    private static <T> T findBestFont(T[] tArr, int i, StyleExtractor<T> styleExtractor) {
        T[] fonts = tArr;
        int style = i;
        StyleExtractor<T> extractor = styleExtractor;
        int targetWeight = (style & 1) == 0 ? HttpStatus.SC_BAD_REQUEST : 700;
        boolean isTargetItalic = (style & 2) != 0;
        T best = null;
        int bestScore = Integer.MAX_VALUE;
        T[] tArr2 = fonts;
        int length = tArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            T font = tArr2[i2];
            int score = (Math.abs(extractor.getWeight(font) - targetWeight) * 2) + (extractor.isItalic(font) == isTargetItalic ? 0 : 1);
            if (best == null || bestScore > score) {
                best = font;
                bestScore = score;
            }
        }
        return best;
    }

    /* access modifiers changed from: protected */
    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fonts, int style) {
        StyleExtractor styleExtractor;
        new StyleExtractor<FontsContractCompat.FontInfo>(this) {
            final /* synthetic */ TypefaceCompatBaseImpl this$0;

            {
                this.this$0 = this$0;
            }

            public int getWeight(FontsContractCompat.FontInfo info) {
                return info.getWeight();
            }

            public boolean isItalic(FontsContractCompat.FontInfo info) {
                return info.isItalic();
            }
        };
        return (FontsContractCompat.FontInfo) findBestFont(fonts, style, styleExtractor);
    }

    /* access modifiers changed from: protected */
    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        InputStream is = inputStream;
        File tmpFile = TypefaceCompatUtil.getTempFile(context);
        if (tmpFile == null) {
            return null;
        }
        try {
            if (!TypefaceCompatUtil.copyToFile(tmpFile, is)) {
                boolean delete = tmpFile.delete();
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(tmpFile.getPath());
            boolean delete2 = tmpFile.delete();
            return createFromFile;
        } catch (RuntimeException e) {
            RuntimeException runtimeException = e;
            boolean delete3 = tmpFile.delete();
            return null;
        } catch (Throwable th) {
            Throwable th2 = th;
            boolean delete4 = tmpFile.delete();
            throw th2;
        }
    }

    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        Context context2 = context;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        FontsContractCompat.FontInfo[] fonts = fontInfoArr;
        int style = i;
        if (fonts.length < 1) {
            return null;
        }
        InputStream is = null;
        try {
            is = context2.getContentResolver().openInputStream(findBestInfo(fonts, style).getUri());
            Typeface createFromInputStream = createFromInputStream(context2, is);
            TypefaceCompatUtil.closeQuietly(is);
            return createFromInputStream;
        } catch (IOException e) {
            IOException iOException = e;
            TypefaceCompatUtil.closeQuietly(is);
            return null;
        } catch (Throwable th) {
            Throwable th2 = th;
            TypefaceCompatUtil.closeQuietly(is);
            throw th2;
        }
    }

    private FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, int style) {
        StyleExtractor styleExtractor;
        new StyleExtractor<FontResourcesParserCompat.FontFileResourceEntry>(this) {
            final /* synthetic */ TypefaceCompatBaseImpl this$0;

            {
                this.this$0 = this$0;
            }

            public int getWeight(FontResourcesParserCompat.FontFileResourceEntry entry) {
                return entry.getWeight();
            }

            public boolean isItalic(FontResourcesParserCompat.FontFileResourceEntry entry) {
                return entry.isItalic();
            }
        };
        return (FontResourcesParserCompat.FontFileResourceEntry) findBestFont(entry.getEntries(), style, styleExtractor);
    }

    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int i) {
        Context context2 = context;
        Resources resources2 = resources;
        int style = i;
        FontResourcesParserCompat.FontFileResourceEntry best = findBestEntry(entry, style);
        if (best == null) {
            return null;
        }
        return TypefaceCompat.createFromResourcesFontFile(context2, resources2, best.getResourceId(), best.getFileName(), style);
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        Resources resources2 = resources;
        int id = i;
        String str2 = str;
        int i3 = i2;
        File tmpFile = TypefaceCompatUtil.getTempFile(context);
        if (tmpFile == null) {
            return null;
        }
        try {
            if (!TypefaceCompatUtil.copyToFile(tmpFile, resources2, id)) {
                boolean delete = tmpFile.delete();
                return null;
            }
            Typeface createFromFile = Typeface.createFromFile(tmpFile.getPath());
            boolean delete2 = tmpFile.delete();
            return createFromFile;
        } catch (RuntimeException e) {
            RuntimeException runtimeException = e;
            boolean delete3 = tmpFile.delete();
            return null;
        } catch (Throwable th) {
            Throwable th2 = th;
            boolean delete4 = tmpFile.delete();
            throw th2;
        }
    }
}
