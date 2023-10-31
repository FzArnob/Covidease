package android.support.p000v4.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* renamed from: android.support.v4.view.AbsSavedState */
public abstract class AbsSavedState implements Parcelable {
    public static final Parcelable.Creator<AbsSavedState> CREATOR;
    public static final AbsSavedState EMPTY_STATE;
    private final Parcelable mSuperState;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ AbsSavedState(C03631 r4) {
        this();
        C03631 r1 = r4;
    }

    static {
        AbsSavedState absSavedState;
        Parcelable.Creator<AbsSavedState> creator;
        new AbsSavedState() {
        };
        EMPTY_STATE = absSavedState;
        new Parcelable.ClassLoaderCreator<AbsSavedState>() {
            public AbsSavedState createFromParcel(Parcel in, ClassLoader loader) {
                Throwable th;
                if (in.readParcelable(loader) == null) {
                    return AbsSavedState.EMPTY_STATE;
                }
                Throwable th2 = th;
                new IllegalStateException("superState must be null");
                throw th2;
            }

            public AbsSavedState createFromParcel(Parcel in) {
                return createFromParcel(in, (ClassLoader) null);
            }

            public AbsSavedState[] newArray(int size) {
                return new AbsSavedState[size];
            }
        };
        CREATOR = creator;
    }

    private AbsSavedState() {
        this.mSuperState = null;
    }

    protected AbsSavedState(@NonNull Parcelable parcelable) {
        Throwable th;
        Parcelable superState = parcelable;
        if (superState == null) {
            Throwable th2 = th;
            new IllegalArgumentException("superState must not be null");
            throw th2;
        }
        this.mSuperState = superState != EMPTY_STATE ? superState : null;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected AbsSavedState(@NonNull Parcel source) {
        this(source, (ClassLoader) null);
    }

    protected AbsSavedState(@NonNull Parcel source, @Nullable ClassLoader loader) {
        Parcelable superState = source.readParcelable(loader);
        this.mSuperState = superState != null ? superState : EMPTY_STATE;
    }

    @Nullable
    public final Parcelable getSuperState() {
        return this.mSuperState;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mSuperState, flags);
    }
}
