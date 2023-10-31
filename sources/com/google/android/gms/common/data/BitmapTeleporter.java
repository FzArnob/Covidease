package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import gnu.expr.Declaration;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

@ShowFirstParty
@KeepForSdk
@SafeParcelable.Class(creator = "BitmapTeleporterCreator")
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Parcelable.Creator<BitmapTeleporter> CREATOR;
    @SafeParcelable.Field(mo25277id = 3)
    private final int mType;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(mo25277id = 2)
    private ParcelFileDescriptor zalg;
    private Bitmap zalh;
    private boolean zali;
    private File zalj;

    @SafeParcelable.Constructor
    BitmapTeleporter(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) ParcelFileDescriptor parcelFileDescriptor, @SafeParcelable.Param(mo25280id = 3) int i2) {
        this.zalf = i;
        this.zalg = parcelFileDescriptor;
        this.mType = i2;
        this.zalh = null;
        this.zali = false;
    }

    @KeepForSdk
    public BitmapTeleporter(Bitmap bitmap) {
        this.zalf = 1;
        this.zalg = null;
        this.mType = 0;
        this.zalh = bitmap;
        this.zali = true;
    }

    @KeepForSdk
    public Bitmap get() {
        DataInputStream dataInputStream;
        InputStream inputStream;
        Throwable th;
        if (!this.zali) {
            new ParcelFileDescriptor.AutoCloseInputStream(this.zalg);
            new DataInputStream(inputStream);
            DataInputStream dataInputStream2 = dataInputStream;
            try {
                byte[] bArr = new byte[dataInputStream2.readInt()];
                int readInt = dataInputStream2.readInt();
                int readInt2 = dataInputStream2.readInt();
                Bitmap.Config valueOf = Bitmap.Config.valueOf(dataInputStream2.readUTF());
                int read = dataInputStream2.read(bArr);
                zaa(dataInputStream2);
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                Bitmap createBitmap = Bitmap.createBitmap(readInt, readInt2, valueOf);
                createBitmap.copyPixelsFromBuffer(wrap);
                this.zalh = createBitmap;
                this.zali = true;
            } catch (IOException e) {
                IOException iOException = e;
                Throwable th2 = th;
                new IllegalStateException("Could not read from parcel file descriptor", iOException);
                throw th2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                zaa(dataInputStream2);
                throw th4;
            }
        }
        return this.zalh;
    }

    public void writeToParcel(Parcel parcel, int i) {
        OutputStream outputStream;
        DataOutputStream dataOutputStream;
        Throwable th;
        Parcel parcel2 = parcel;
        int i2 = i;
        if (this.zalg == null) {
            Bitmap bitmap = this.zalh;
            Bitmap bitmap2 = bitmap;
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap2.getHeight());
            bitmap2.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            new BufferedOutputStream(zabz());
            new DataOutputStream(outputStream);
            DataOutputStream dataOutputStream2 = dataOutputStream;
            try {
                dataOutputStream2.writeInt(array.length);
                dataOutputStream2.writeInt(bitmap2.getWidth());
                dataOutputStream2.writeInt(bitmap2.getHeight());
                dataOutputStream2.writeUTF(bitmap2.getConfig().toString());
                dataOutputStream2.write(array);
                zaa(dataOutputStream2);
            } catch (IOException e) {
                IOException iOException = e;
                Throwable th2 = th;
                new IllegalStateException("Could not write into unlinked file", iOException);
                throw th2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                zaa(dataOutputStream2);
                throw th4;
            }
        }
        Parcel parcel3 = parcel2;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel3);
        SafeParcelWriter.writeInt(parcel3, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel3, 2, this.zalg, i2 | 1, false);
        SafeParcelWriter.writeInt(parcel3, 3, this.mType);
        SafeParcelWriter.finishObjectHeader(parcel3, beginObjectHeader);
        this.zalg = null;
    }

    @KeepForSdk
    public void release() {
        if (!this.zali) {
            try {
                this.zalg.close();
            } catch (IOException e) {
                int w = Log.w("BitmapTeleporter", "Could not close PFD", e);
            }
        }
    }

    @KeepForSdk
    public void setTempDir(File file) {
        Throwable th;
        File file2 = file;
        if (file2 == null) {
            Throwable th2 = th;
            new NullPointerException("Cannot set null temp directory");
            throw th2;
        }
        this.zalj = file2;
    }

    private final FileOutputStream zabz() {
        Throwable th;
        Throwable th2;
        FileOutputStream fileOutputStream;
        Throwable th3;
        if (this.zalj == null) {
            Throwable th4 = th3;
            new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
            throw th4;
        }
        try {
            File createTempFile = File.createTempFile("teleporter", ".tmp", this.zalj);
            try {
                new FileOutputStream(createTempFile);
                FileOutputStream fileOutputStream2 = fileOutputStream;
                this.zalg = ParcelFileDescriptor.open(createTempFile, Declaration.IS_DYNAMIC);
                boolean delete = createTempFile.delete();
                return fileOutputStream2;
            } catch (FileNotFoundException e) {
                Throwable th5 = th2;
                new IllegalStateException("Temporary file is somehow already deleted");
                throw th5;
            }
        } catch (IOException e2) {
            IOException iOException = e2;
            Throwable th6 = th;
            new IllegalStateException("Could not create temporary file", iOException);
            throw th6;
        }
    }

    private static void zaa(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            int w = Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }

    static {
        Parcelable.Creator<BitmapTeleporter> creator;
        new zaa();
        CREATOR = creator;
    }
}
