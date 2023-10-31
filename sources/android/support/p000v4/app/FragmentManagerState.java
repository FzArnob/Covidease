package android.support.p000v4.app;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.app.FragmentManagerState */
/* compiled from: FragmentManager */
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR;
    FragmentState[] mActive;
    int[] mAdded;
    BackStackState[] mBackStack;
    int mNextFragmentIndex;
    int mPrimaryNavActiveIndex = -1;

    public FragmentManagerState() {
    }

    public FragmentManagerState(Parcel parcel) {
        Parcel in = parcel;
        this.mActive = (FragmentState[]) in.createTypedArray(FragmentState.CREATOR);
        this.mAdded = in.createIntArray();
        this.mBackStack = (BackStackState[]) in.createTypedArray(BackStackState.CREATOR);
        this.mPrimaryNavActiveIndex = in.readInt();
        this.mNextFragmentIndex = in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel dest = parcel;
        int flags = i;
        dest.writeTypedArray(this.mActive, flags);
        dest.writeIntArray(this.mAdded);
        dest.writeTypedArray(this.mBackStack, flags);
        dest.writeInt(this.mPrimaryNavActiveIndex);
        dest.writeInt(this.mNextFragmentIndex);
    }

    static {
        Parcelable.Creator<FragmentManagerState> creator;
        new Parcelable.Creator<FragmentManagerState>() {
            public FragmentManagerState createFromParcel(Parcel in) {
                FragmentManagerState fragmentManagerState;
                new FragmentManagerState(in);
                return fragmentManagerState;
            }

            public FragmentManagerState[] newArray(int size) {
                return new FragmentManagerState[size];
            }
        };
        CREATOR = creator;
    }
}
