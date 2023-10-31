package android.support.p000v4.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: android.support.v4.print.PrintHelper */
public final class PrintHelper {
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_COLOR = 2;
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_MONOCHROME = 1;
    static final boolean IS_MIN_MARGINS_HANDLING_CORRECT;
    private static final String LOG_TAG = "PrintHelper";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION = (Build.VERSION.SDK_INT < 20 || Build.VERSION.SDK_INT > 23);
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode;
    final Context mContext;
    BitmapFactory.Options mDecodeOptions = null;
    final Object mLock;
    int mOrientation;
    int mScaleMode;

    /* renamed from: android.support.v4.print.PrintHelper$OnPrintFinishCallback */
    public interface OnPrintFinishCallback {
        void onFinish();
    }

    static {
        boolean z;
        if (Build.VERSION.SDK_INT != 23) {
            z = true;
        } else {
            z = false;
        }
        IS_MIN_MARGINS_HANDLING_CORRECT = z;
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public PrintHelper(@NonNull Context context) {
        Object obj;
        new Object();
        this.mLock = obj;
        this.mScaleMode = 2;
        this.mColorMode = 2;
        this.mOrientation = 1;
        this.mContext = context;
    }

    public void setScaleMode(int scaleMode) {
        int i = scaleMode;
        this.mScaleMode = i;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void setColorMode(int colorMode) {
        int i = colorMode;
        this.mColorMode = i;
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    public void setOrientation(int orientation) {
        int i = orientation;
        this.mOrientation = i;
    }

    public int getOrientation() {
        if (Build.VERSION.SDK_INT < 19 || this.mOrientation != 0) {
            return this.mOrientation;
        }
        return 1;
    }

    public void printBitmap(@NonNull String jobName, @NonNull Bitmap bitmap) {
        printBitmap(jobName, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String str, @NonNull Bitmap bitmap, @Nullable OnPrintFinishCallback onPrintFinishCallback) {
        PrintAttributes.MediaSize mediaSize;
        PrintAttributes.Builder builder;
        PrintDocumentAdapter printDocumentAdapter;
        String jobName = str;
        Bitmap bitmap2 = bitmap;
        OnPrintFinishCallback callback = onPrintFinishCallback;
        if (Build.VERSION.SDK_INT >= 19 && bitmap2 != null) {
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            if (isPortrait(bitmap2)) {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
            } else {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
            }
            new PrintAttributes.Builder();
            PrintAttributes attr = builder.setMediaSize(mediaSize).setColorMode(this.mColorMode).build();
            new PrintBitmapAdapter(this, jobName, this.mScaleMode, bitmap2, callback);
            PrintJob print = printManager.print(jobName, printDocumentAdapter, attr);
        }
    }

    @RequiresApi(19)
    /* renamed from: android.support.v4.print.PrintHelper$PrintBitmapAdapter */
    private class PrintBitmapAdapter extends PrintDocumentAdapter {
        private PrintAttributes mAttributes;
        private final Bitmap mBitmap;
        private final OnPrintFinishCallback mCallback;
        private final int mFittingMode;
        private final String mJobName;
        final /* synthetic */ PrintHelper this$0;

        PrintBitmapAdapter(PrintHelper printHelper, String jobName, int fittingMode, Bitmap bitmap, OnPrintFinishCallback callback) {
            this.this$0 = printHelper;
            this.mJobName = jobName;
            this.mFittingMode = fittingMode;
            this.mBitmap = bitmap;
            this.mCallback = callback;
        }

        public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes printAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            PrintDocumentInfo.Builder builder;
            PrintAttributes newPrintAttributes = printAttributes;
            CancellationSignal cancellationSignal2 = cancellationSignal;
            PrintDocumentAdapter.LayoutResultCallback layoutResultCallback2 = layoutResultCallback;
            Bundle bundle2 = bundle;
            this.mAttributes = newPrintAttributes;
            new PrintDocumentInfo.Builder(this.mJobName);
            layoutResultCallback2.onLayoutFinished(builder.setContentType(1).setPageCount(1).build(), !newPrintAttributes.equals(oldPrintAttributes));
        }

        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PageRange[] pageRangeArr2 = pageRangeArr;
            this.this$0.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
        }

        public void onFinish() {
            if (this.mCallback != null) {
                this.mCallback.onFinish();
            }
        }
    }

    public void printBitmap(@NonNull String jobName, @NonNull Uri imageFile) throws FileNotFoundException {
        printBitmap(jobName, imageFile, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String str, @NonNull Uri uri, @Nullable OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        PrintDocumentAdapter printDocumentAdapter;
        PrintAttributes.Builder builder;
        String jobName = str;
        Uri imageFile = uri;
        OnPrintFinishCallback callback = onPrintFinishCallback;
        if (Build.VERSION.SDK_INT >= 19) {
            new PrintUriAdapter(this, jobName, imageFile, callback, this.mScaleMode);
            PrintDocumentAdapter printDocumentAdapter2 = printDocumentAdapter;
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            new PrintAttributes.Builder();
            PrintAttributes.Builder builder2 = builder;
            PrintAttributes.Builder colorMode = builder2.setColorMode(this.mColorMode);
            if (this.mOrientation == 1 || this.mOrientation == 0) {
                PrintAttributes.Builder mediaSize = builder2.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
            } else if (this.mOrientation == 2) {
                PrintAttributes.Builder mediaSize2 = builder2.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
            }
            PrintJob print = printManager.print(jobName, printDocumentAdapter2, builder2.build());
        }
    }

    @RequiresApi(19)
    /* renamed from: android.support.v4.print.PrintHelper$PrintUriAdapter */
    private class PrintUriAdapter extends PrintDocumentAdapter {
        PrintAttributes mAttributes;
        Bitmap mBitmap = null;
        final OnPrintFinishCallback mCallback;
        final int mFittingMode;
        final Uri mImageFile;
        final String mJobName;
        AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
        final /* synthetic */ PrintHelper this$0;

        PrintUriAdapter(PrintHelper printHelper, String jobName, Uri imageFile, OnPrintFinishCallback callback, int fittingMode) {
            this.this$0 = printHelper;
            this.mJobName = jobName;
            this.mImageFile = imageFile;
            this.mCallback = callback;
            this.mFittingMode = fittingMode;
        }

        /* JADX INFO: finally extract failed */
        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            C03401 r16;
            PrintDocumentInfo.Builder builder;
            PrintAttributes oldPrintAttributes = printAttributes;
            PrintAttributes newPrintAttributes = printAttributes2;
            CancellationSignal cancellationSignal2 = cancellationSignal;
            PrintDocumentAdapter.LayoutResultCallback layoutResultCallback2 = layoutResultCallback;
            Bundle bundle2 = bundle;
            synchronized (this) {
                try {
                    this.mAttributes = newPrintAttributes;
                    if (cancellationSignal2.isCanceled()) {
                        layoutResultCallback2.onLayoutCancelled();
                    } else if (this.mBitmap != null) {
                        new PrintDocumentInfo.Builder(this.mJobName);
                        layoutResultCallback2.onLayoutFinished(builder.setContentType(1).setPageCount(1).build(), !newPrintAttributes.equals(oldPrintAttributes));
                    } else {
                        final CancellationSignal cancellationSignal3 = cancellationSignal2;
                        final PrintAttributes printAttributes3 = newPrintAttributes;
                        final PrintAttributes printAttributes4 = oldPrintAttributes;
                        final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback3 = layoutResultCallback2;
                        new AsyncTask<Uri, Boolean, Bitmap>(this) {
                            final /* synthetic */ PrintUriAdapter this$1;

                            {
                                this.this$1 = this$1;
                            }

                            /* access modifiers changed from: protected */
                            public void onPreExecute() {
                                CancellationSignal.OnCancelListener onCancelListener;
                                new CancellationSignal.OnCancelListener(this) {
                                    final /* synthetic */ C03401 this$2;

                                    {
                                        this.this$2 = this$2;
                                    }

                                    public void onCancel() {
                                        this.this$2.this$1.cancelLoad();
                                        boolean cancel = this.this$2.cancel(false);
                                    }
                                };
                                cancellationSignal3.setOnCancelListener(onCancelListener);
                            }

                            /* access modifiers changed from: protected */
                            public Bitmap doInBackground(Uri... uriArr) {
                                Uri[] uriArr2 = uriArr;
                                try {
                                    return this.this$1.this$0.loadConstrainedBitmap(this.this$1.mImageFile);
                                } catch (FileNotFoundException e) {
                                    FileNotFoundException fileNotFoundException = e;
                                    return null;
                                }
                            }

                            /* JADX INFO: finally extract failed */
                            /* access modifiers changed from: protected */
                            public void onPostExecute(Bitmap bitmap) {
                                PrintDocumentInfo.Builder builder;
                                Matrix matrix;
                                Bitmap bitmap2 = bitmap;
                                super.onPostExecute(bitmap2);
                                if (bitmap2 != null && (!PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION || this.this$1.this$0.mOrientation == 0)) {
                                    synchronized (this) {
                                        try {
                                            PrintAttributes.MediaSize mediaSize = this.this$1.mAttributes.getMediaSize();
                                            if (!(mediaSize == null || mediaSize.isPortrait() == PrintHelper.isPortrait(bitmap2))) {
                                                new Matrix();
                                                Matrix rotation = matrix;
                                                boolean postRotate = rotation.postRotate(90.0f);
                                                bitmap2 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), rotation, true);
                                            }
                                        } catch (Throwable th) {
                                            while (true) {
                                                Throwable th2 = th;
                                                throw th2;
                                            }
                                        }
                                    }
                                }
                                this.this$1.mBitmap = bitmap2;
                                if (bitmap2 != null) {
                                    new PrintDocumentInfo.Builder(this.this$1.mJobName);
                                    layoutResultCallback3.onLayoutFinished(builder.setContentType(1).setPageCount(1).build(), !printAttributes3.equals(printAttributes4));
                                } else {
                                    layoutResultCallback3.onLayoutFailed((CharSequence) null);
                                }
                                this.this$1.mLoadBitmap = null;
                            }

                            /* access modifiers changed from: protected */
                            public void onCancelled(Bitmap bitmap) {
                                Bitmap bitmap2 = bitmap;
                                layoutResultCallback3.onLayoutCancelled();
                                this.this$1.mLoadBitmap = null;
                            }
                        };
                        this.mLoadBitmap = r16.execute(new Uri[0]);
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        throw th2;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelLoad() {
            Object obj = this.this$0.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (this.this$0.mDecodeOptions != null) {
                        if (Build.VERSION.SDK_INT < 24) {
                            this.this$0.mDecodeOptions.requestCancelDecode();
                        }
                        this.this$0.mDecodeOptions = null;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        public void onFinish() {
            super.onFinish();
            cancelLoad();
            if (this.mLoadBitmap != null) {
                boolean cancel = this.mLoadBitmap.cancel(true);
            }
            if (this.mCallback != null) {
                this.mCallback.onFinish();
            }
            if (this.mBitmap != null) {
                this.mBitmap.recycle();
                this.mBitmap = null;
            }
        }

        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PageRange[] pageRangeArr2 = pageRangeArr;
            this.this$0.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static boolean isPortrait(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        return bitmap2.getWidth() <= bitmap2.getHeight();
    }

    @RequiresApi(19)
    private static PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
        PrintAttributes.Builder builder;
        PrintAttributes other = printAttributes;
        new PrintAttributes.Builder();
        PrintAttributes.Builder b = builder.setMediaSize(other.getMediaSize()).setResolution(other.getResolution()).setMinMargins(other.getMinMargins());
        if (other.getColorMode() != 0) {
            PrintAttributes.Builder colorMode = b.setColorMode(other.getColorMode());
        }
        if (Build.VERSION.SDK_INT >= 23 && other.getDuplexMode() != 0) {
            PrintAttributes.Builder duplexMode = b.setDuplexMode(other.getDuplexMode());
        }
        return b;
    }

    static Matrix getMatrix(int i, int i2, RectF rectF, int fittingMode) {
        Matrix matrix;
        float scale;
        int imageWidth = i;
        int imageHeight = i2;
        RectF content = rectF;
        new Matrix();
        Matrix matrix2 = matrix;
        float scale2 = content.width() / ((float) imageWidth);
        if (fittingMode == 2) {
            scale = Math.max(scale2, content.height() / ((float) imageHeight));
        } else {
            scale = Math.min(scale2, content.height() / ((float) imageHeight));
        }
        boolean postScale = matrix2.postScale(scale, scale);
        boolean postTranslate = matrix2.postTranslate((content.width() - (((float) imageWidth) * scale)) / 2.0f, (content.height() - (((float) imageHeight) * scale)) / 2.0f);
        return matrix2;
    }

    /* access modifiers changed from: package-private */
    @RequiresApi(19)
    public void writeBitmap(PrintAttributes printAttributes, int i, Bitmap bitmap, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        PrintAttributes.Margins margins;
        PrintAttributes pdfAttributes;
        C03391 r18;
        PrintAttributes attributes = printAttributes;
        int fittingMode = i;
        Bitmap bitmap2 = bitmap;
        ParcelFileDescriptor fileDescriptor = parcelFileDescriptor;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        PrintDocumentAdapter.WriteResultCallback writeResultCallback2 = writeResultCallback;
        if (IS_MIN_MARGINS_HANDLING_CORRECT) {
            pdfAttributes = attributes;
        } else {
            new PrintAttributes.Margins(0, 0, 0, 0);
            pdfAttributes = copyAttributes(attributes).setMinMargins(margins).build();
        }
        final CancellationSignal cancellationSignal3 = cancellationSignal2;
        final PrintAttributes printAttributes2 = pdfAttributes;
        final Bitmap bitmap3 = bitmap2;
        final PrintAttributes printAttributes3 = attributes;
        final int i2 = fittingMode;
        final ParcelFileDescriptor parcelFileDescriptor2 = fileDescriptor;
        final PrintDocumentAdapter.WriteResultCallback writeResultCallback3 = writeResultCallback2;
        new AsyncTask<Void, Void, Throwable>(this) {
            final /* synthetic */ PrintHelper this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: protected */
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Throwable doInBackground(java.lang.Void... r17) {
                /*
                    r16 = this;
                    r0 = r16
                    r1 = r17
                    r11 = r0
                    android.os.CancellationSignal r11 = r11     // Catch:{ Throwable -> 0x00fa }
                    boolean r11 = r11.isCanceled()     // Catch:{ Throwable -> 0x00fa }
                    if (r11 == 0) goto L_0x0010
                    r11 = 0
                    r0 = r11
                L_0x000f:
                    return r0
                L_0x0010:
                    android.print.pdf.PrintedPdfDocument r11 = new android.print.pdf.PrintedPdfDocument     // Catch:{ Throwable -> 0x00fa }
                    r15 = r11
                    r11 = r15
                    r12 = r15
                    r13 = r0
                    android.support.v4.print.PrintHelper r13 = r13.this$0     // Catch:{ Throwable -> 0x00fa }
                    android.content.Context r13 = r13.mContext     // Catch:{ Throwable -> 0x00fa }
                    r14 = r0
                    android.print.PrintAttributes r14 = r12     // Catch:{ Throwable -> 0x00fa }
                    r12.<init>(r13, r14)     // Catch:{ Throwable -> 0x00fa }
                    r2 = r11
                    r11 = r0
                    android.graphics.Bitmap r11 = r13     // Catch:{ Throwable -> 0x00fa }
                    r12 = r0
                    android.print.PrintAttributes r12 = r12     // Catch:{ Throwable -> 0x00fa }
                    int r12 = r12.getColorMode()     // Catch:{ Throwable -> 0x00fa }
                    android.graphics.Bitmap r11 = android.support.p000v4.print.PrintHelper.convertBitmapForColorMode(r11, r12)     // Catch:{ Throwable -> 0x00fa }
                    r3 = r11
                    r11 = r0
                    android.os.CancellationSignal r11 = r11     // Catch:{ Throwable -> 0x00fa }
                    boolean r11 = r11.isCanceled()     // Catch:{ Throwable -> 0x00fa }
                    if (r11 == 0) goto L_0x003c
                    r11 = 0
                    r0 = r11
                    goto L_0x000f
                L_0x003c:
                    r11 = r2
                    r12 = 1
                    android.graphics.pdf.PdfDocument$Page r11 = r11.startPage(r12)     // Catch:{ all -> 0x00dd }
                    r4 = r11
                    boolean r11 = android.support.p000v4.print.PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT     // Catch:{ all -> 0x00dd }
                    if (r11 == 0) goto L_0x00a8
                    android.graphics.RectF r11 = new android.graphics.RectF     // Catch:{ all -> 0x00dd }
                    r15 = r11
                    r11 = r15
                    r12 = r15
                    r13 = r4
                    android.graphics.pdf.PdfDocument$PageInfo r13 = r13.getInfo()     // Catch:{ all -> 0x00dd }
                    android.graphics.Rect r13 = r13.getContentRect()     // Catch:{ all -> 0x00dd }
                    r12.<init>(r13)     // Catch:{ all -> 0x00dd }
                    r5 = r11
                L_0x0059:
                    r11 = r3
                    int r11 = r11.getWidth()     // Catch:{ all -> 0x00dd }
                    r12 = r3
                    int r12 = r12.getHeight()     // Catch:{ all -> 0x00dd }
                    r13 = r5
                    r14 = r0
                    int r14 = r15     // Catch:{ all -> 0x00dd }
                    android.graphics.Matrix r11 = android.support.p000v4.print.PrintHelper.getMatrix(r11, r12, r13, r14)     // Catch:{ all -> 0x00dd }
                    r6 = r11
                    boolean r11 = android.support.p000v4.print.PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT     // Catch:{ all -> 0x00dd }
                    if (r11 == 0) goto L_0x0100
                L_0x0070:
                    r11 = r4
                    android.graphics.Canvas r11 = r11.getCanvas()     // Catch:{ all -> 0x00dd }
                    r12 = r3
                    r13 = r6
                    r14 = 0
                    r11.drawBitmap(r12, r13, r14)     // Catch:{ all -> 0x00dd }
                    r11 = r2
                    r12 = r4
                    r11.finishPage(r12)     // Catch:{ all -> 0x00dd }
                    r11 = r0
                    android.os.CancellationSignal r11 = r11     // Catch:{ all -> 0x00dd }
                    boolean r11 = r11.isCanceled()     // Catch:{ all -> 0x00dd }
                    if (r11 == 0) goto L_0x011a
                    r11 = 0
                    r7 = r11
                    r11 = r2
                    r11.close()     // Catch:{ Throwable -> 0x00fa }
                    r11 = r0
                    android.os.ParcelFileDescriptor r11 = r16     // Catch:{ Throwable -> 0x00fa }
                    if (r11 == 0) goto L_0x009a
                    r11 = r0
                    android.os.ParcelFileDescriptor r11 = r16     // Catch:{ IOException -> 0x0117 }
                    r11.close()     // Catch:{ IOException -> 0x0117 }
                L_0x009a:
                    r11 = r3
                    r12 = r0
                    android.graphics.Bitmap r12 = r13     // Catch:{ Throwable -> 0x00fa }
                    if (r11 == r12) goto L_0x00a4
                    r11 = r3
                    r11.recycle()     // Catch:{ Throwable -> 0x00fa }
                L_0x00a4:
                    r11 = r7
                    r0 = r11
                    goto L_0x000f
                L_0x00a8:
                    android.print.pdf.PrintedPdfDocument r11 = new android.print.pdf.PrintedPdfDocument     // Catch:{ all -> 0x00dd }
                    r15 = r11
                    r11 = r15
                    r12 = r15
                    r13 = r0
                    android.support.v4.print.PrintHelper r13 = r13.this$0     // Catch:{ all -> 0x00dd }
                    android.content.Context r13 = r13.mContext     // Catch:{ all -> 0x00dd }
                    r14 = r0
                    android.print.PrintAttributes r14 = r14     // Catch:{ all -> 0x00dd }
                    r12.<init>(r13, r14)     // Catch:{ all -> 0x00dd }
                    r6 = r11
                    r11 = r6
                    r12 = 1
                    android.graphics.pdf.PdfDocument$Page r11 = r11.startPage(r12)     // Catch:{ all -> 0x00dd }
                    r7 = r11
                    android.graphics.RectF r11 = new android.graphics.RectF     // Catch:{ all -> 0x00dd }
                    r15 = r11
                    r11 = r15
                    r12 = r15
                    r13 = r7
                    android.graphics.pdf.PdfDocument$PageInfo r13 = r13.getInfo()     // Catch:{ all -> 0x00dd }
                    android.graphics.Rect r13 = r13.getContentRect()     // Catch:{ all -> 0x00dd }
                    r12.<init>(r13)     // Catch:{ all -> 0x00dd }
                    r5 = r11
                    r11 = r6
                    r12 = r7
                    r11.finishPage(r12)     // Catch:{ all -> 0x00dd }
                    r11 = r6
                    r11.close()     // Catch:{ all -> 0x00dd }
                    goto L_0x0059
                L_0x00dd:
                    r11 = move-exception
                    r9 = r11
                    r11 = r2
                    r11.close()     // Catch:{ Throwable -> 0x00fa }
                    r11 = r0
                    android.os.ParcelFileDescriptor r11 = r16     // Catch:{ Throwable -> 0x00fa }
                    if (r11 == 0) goto L_0x00ee
                    r11 = r0
                    android.os.ParcelFileDescriptor r11 = r16     // Catch:{ IOException -> 0x014f }
                    r11.close()     // Catch:{ IOException -> 0x014f }
                L_0x00ee:
                    r11 = r3
                    r12 = r0
                    android.graphics.Bitmap r12 = r13     // Catch:{ Throwable -> 0x00fa }
                    if (r11 == r12) goto L_0x00f8
                    r11 = r3
                    r11.recycle()     // Catch:{ Throwable -> 0x00fa }
                L_0x00f8:
                    r11 = r9
                    throw r11     // Catch:{ Throwable -> 0x00fa }
                L_0x00fa:
                    r11 = move-exception
                    r2 = r11
                    r11 = r2
                    r0 = r11
                    goto L_0x000f
                L_0x0100:
                    r11 = r6
                    r12 = r5
                    float r12 = r12.left     // Catch:{ all -> 0x00dd }
                    r13 = r5
                    float r13 = r13.top     // Catch:{ all -> 0x00dd }
                    boolean r11 = r11.postTranslate(r12, r13)     // Catch:{ all -> 0x00dd }
                    r11 = r4
                    android.graphics.Canvas r11 = r11.getCanvas()     // Catch:{ all -> 0x00dd }
                    r12 = r5
                    boolean r11 = r11.clipRect(r12)     // Catch:{ all -> 0x00dd }
                    goto L_0x0070
                L_0x0117:
                    r11 = move-exception
                    r8 = r11
                    goto L_0x009a
                L_0x011a:
                    r11 = r2
                    java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch:{ all -> 0x00dd }
                    r15 = r12
                    r12 = r15
                    r13 = r15
                    r14 = r0
                    android.os.ParcelFileDescriptor r14 = r16     // Catch:{ all -> 0x00dd }
                    java.io.FileDescriptor r14 = r14.getFileDescriptor()     // Catch:{ all -> 0x00dd }
                    r13.<init>(r14)     // Catch:{ all -> 0x00dd }
                    r11.writeTo(r12)     // Catch:{ all -> 0x00dd }
                    r11 = 0
                    r7 = r11
                    r11 = r2
                    r11.close()     // Catch:{ Throwable -> 0x00fa }
                    r11 = r0
                    android.os.ParcelFileDescriptor r11 = r16     // Catch:{ Throwable -> 0x00fa }
                    if (r11 == 0) goto L_0x013e
                    r11 = r0
                    android.os.ParcelFileDescriptor r11 = r16     // Catch:{ IOException -> 0x014c }
                    r11.close()     // Catch:{ IOException -> 0x014c }
                L_0x013e:
                    r11 = r3
                    r12 = r0
                    android.graphics.Bitmap r12 = r13     // Catch:{ Throwable -> 0x00fa }
                    if (r11 == r12) goto L_0x0148
                    r11 = r3
                    r11.recycle()     // Catch:{ Throwable -> 0x00fa }
                L_0x0148:
                    r11 = r7
                    r0 = r11
                    goto L_0x000f
                L_0x014c:
                    r11 = move-exception
                    r8 = r11
                    goto L_0x013e
                L_0x014f:
                    r11 = move-exception
                    r10 = r11
                    goto L_0x00ee
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.print.PrintHelper.C03391.doInBackground(java.lang.Void[]):java.lang.Throwable");
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Throwable th) {
                Throwable throwable = th;
                if (cancellationSignal3.isCanceled()) {
                    writeResultCallback3.onWriteCancelled();
                } else if (throwable == null) {
                    writeResultCallback3.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    int e = Log.e(PrintHelper.LOG_TAG, "Error writing printed content", throwable);
                    writeResultCallback3.onWriteFailed((CharSequence) null);
                }
            }
        };
        AsyncTask execute = r18.execute(new Void[0]);
    }

    /* access modifiers changed from: package-private */
    public Bitmap loadConstrainedBitmap(Uri uri) throws FileNotFoundException {
        Throwable th;
        BitmapFactory.Options options;
        int sampleSize;
        Throwable th2;
        BitmapFactory.Options options2;
        Object obj;
        Uri uri2 = uri;
        if (uri2 == null || this.mContext == null) {
            Throwable th3 = th;
            new IllegalArgumentException("bad argument to getScaledBitmap");
            throw th3;
        }
        new BitmapFactory.Options();
        BitmapFactory.Options opt = options;
        opt.inJustDecodeBounds = true;
        Bitmap loadBitmap = loadBitmap(uri2, opt);
        int w = opt.outWidth;
        int h = opt.outHeight;
        if (w <= 0 || h <= 0) {
            return null;
        }
        int imageSide = Math.max(w, h);
        int i = 1;
        while (true) {
            sampleSize = i;
            if (imageSide <= MAX_PRINT_SIZE) {
                break;
            }
            imageSide >>>= 1;
            i = sampleSize << 1;
        }
        if (sampleSize <= 0 || 0 >= Math.min(w, h) / sampleSize) {
            return null;
        }
        Object obj2 = this.mLock;
        Object obj3 = obj2;
        synchronized (obj2) {
            try {
                new BitmapFactory.Options();
                this.mDecodeOptions = options2;
                this.mDecodeOptions.inMutable = true;
                this.mDecodeOptions.inSampleSize = sampleSize;
                BitmapFactory.Options decodeOptions = this.mDecodeOptions;
                try {
                    Bitmap loadBitmap2 = loadBitmap(uri2, decodeOptions);
                    Object obj4 = this.mLock;
                    obj3 = obj4;
                    synchronized (obj4) {
                        try {
                            this.mDecodeOptions = null;
                            return loadBitmap2;
                        } catch (Throwable th4) {
                            while (true) {
                                throw th2;
                            }
                        }
                    }
                } catch (Throwable th5) {
                    while (true) {
                        Throwable th6 = th5;
                        Object obj5 = obj;
                        throw th6;
                    }
                }
            } finally {
                while (true) {
                    th2 = th4;
                    Object obj6 = obj3;
                    Throwable th7 = th2;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private Bitmap loadBitmap(Uri uri, BitmapFactory.Options options) throws FileNotFoundException {
        Throwable th;
        Uri uri2 = uri;
        BitmapFactory.Options o = options;
        if (uri2 == null || this.mContext == null) {
            Throwable th2 = th;
            new IllegalArgumentException("bad argument to loadBitmap");
            throw th2;
        }
        InputStream is = null;
        try {
            is = this.mContext.getContentResolver().openInputStream(uri2);
            Bitmap decodeStream = BitmapFactory.decodeStream(is, (Rect) null, o);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    int w = Log.w(LOG_TAG, "close fail ", e);
                }
            }
            return decodeStream;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e2) {
                    int w2 = Log.w(LOG_TAG, "close fail ", e2);
                }
            }
            throw th4;
        }
    }

    static Bitmap convertBitmapForColorMode(Bitmap bitmap, int colorMode) {
        Canvas canvas;
        Paint paint;
        ColorMatrix colorMatrix;
        ColorFilter colorFilter;
        Bitmap original = bitmap;
        if (colorMode != 1) {
            return original;
        }
        Bitmap grayscale = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(grayscale);
        Canvas c = canvas;
        new Paint();
        Paint p = paint;
        new ColorMatrix();
        ColorMatrix cm = colorMatrix;
        cm.setSaturation(0.0f);
        new ColorMatrixColorFilter(cm);
        ColorFilter colorFilter2 = p.setColorFilter(colorFilter);
        c.drawBitmap(original, 0.0f, 0.0f, p);
        c.setBitmap((Bitmap) null);
        return grayscale;
    }
}
