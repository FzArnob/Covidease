package androidx.versionedparcelable;

import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import java.io.InputStream;
import java.io.OutputStream;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ParcelUtils {
    private ParcelUtils() {
    }

    public static Parcelable toParcelable(VersionedParcelable obj) {
        Parcelable parcelable;
        new ParcelImpl(obj);
        return parcelable;
    }

    public static <T extends VersionedParcelable> T fromParcelable(Parcelable parcelable) {
        Throwable th;
        Parcelable p = parcelable;
        if (p instanceof ParcelImpl) {
            return ((ParcelImpl) p).getVersionedParcel();
        }
        Throwable th2 = th;
        new IllegalArgumentException("Invalid parcel");
        throw th2;
    }

    public static void toOutputStream(VersionedParcelable obj, OutputStream output) {
        VersionedParcelStream versionedParcelStream;
        new VersionedParcelStream((InputStream) null, output);
        VersionedParcelStream stream = versionedParcelStream;
        stream.writeVersionedParcelable(obj);
        stream.closeField();
    }

    public static <T extends VersionedParcelable> T fromInputStream(InputStream input) {
        VersionedParcelStream stream;
        new VersionedParcelStream(input, (OutputStream) null);
        return stream.readVersionedParcelable();
    }
}
