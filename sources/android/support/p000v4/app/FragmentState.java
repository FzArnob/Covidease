package android.support.p000v4.app;

import android.arch.lifecycle.ViewModelStore;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* renamed from: android.support.v4.app.FragmentState */
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    FragmentState(Fragment fragment) {
        Fragment frag = fragment;
        this.mClassName = frag.getClass().getName();
        this.mIndex = frag.mIndex;
        this.mFromLayout = frag.mFromLayout;
        this.mFragmentId = frag.mFragmentId;
        this.mContainerId = frag.mContainerId;
        this.mTag = frag.mTag;
        this.mRetainInstance = frag.mRetainInstance;
        this.mDetached = frag.mDetached;
        this.mArguments = frag.mArguments;
        this.mHidden = frag.mHidden;
    }

    FragmentState(Parcel parcel) {
        Parcel in = parcel;
        this.mClassName = in.readString();
        this.mIndex = in.readInt();
        this.mFromLayout = in.readInt() != 0;
        this.mFragmentId = in.readInt();
        this.mContainerId = in.readInt();
        this.mTag = in.readString();
        this.mRetainInstance = in.readInt() != 0;
        this.mDetached = in.readInt() != 0;
        this.mArguments = in.readBundle();
        this.mHidden = in.readInt() != 0;
        this.mSavedFragmentState = in.readBundle();
    }

    public Fragment instantiate(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment, FragmentManagerNonConfig fragmentManagerNonConfig, ViewModelStore viewModelStore) {
        StringBuilder sb;
        FragmentHostCallback host = fragmentHostCallback;
        FragmentContainer container = fragmentContainer;
        Fragment parent = fragment;
        FragmentManagerNonConfig childNonConfig = fragmentManagerNonConfig;
        ViewModelStore viewModelStore2 = viewModelStore;
        if (this.mInstance == null) {
            Context context = host.getContext();
            if (this.mArguments != null) {
                this.mArguments.setClassLoader(context.getClassLoader());
            }
            if (container != null) {
                this.mInstance = container.instantiate(context, this.mClassName, this.mArguments);
            } else {
                this.mInstance = Fragment.instantiate(context, this.mClassName, this.mArguments);
            }
            if (this.mSavedFragmentState != null) {
                this.mSavedFragmentState.setClassLoader(context.getClassLoader());
                this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
            }
            this.mInstance.setIndex(this.mIndex, parent);
            this.mInstance.mFromLayout = this.mFromLayout;
            this.mInstance.mRestored = true;
            this.mInstance.mFragmentId = this.mFragmentId;
            this.mInstance.mContainerId = this.mContainerId;
            this.mInstance.mTag = this.mTag;
            this.mInstance.mRetainInstance = this.mRetainInstance;
            this.mInstance.mDetached = this.mDetached;
            this.mInstance.mHidden = this.mHidden;
            this.mInstance.mFragmentManager = host.mFragmentManager;
            if (FragmentManagerImpl.DEBUG) {
                new StringBuilder();
                int v = Log.v("FragmentManager", sb.append("Instantiated fragment ").append(this.mInstance).toString());
            }
        }
        this.mInstance.mChildNonConfig = childNonConfig;
        this.mInstance.mViewModelStore = viewModelStore2;
        return this.mInstance;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel dest = parcel;
        int i2 = i;
        dest.writeString(this.mClassName);
        dest.writeInt(this.mIndex);
        dest.writeInt(this.mFromLayout ? 1 : 0);
        dest.writeInt(this.mFragmentId);
        dest.writeInt(this.mContainerId);
        dest.writeString(this.mTag);
        dest.writeInt(this.mRetainInstance ? 1 : 0);
        dest.writeInt(this.mDetached ? 1 : 0);
        dest.writeBundle(this.mArguments);
        dest.writeInt(this.mHidden ? 1 : 0);
        dest.writeBundle(this.mSavedFragmentState);
    }

    static {
        Parcelable.Creator<FragmentState> creator;
        new Parcelable.Creator<FragmentState>() {
            public FragmentState createFromParcel(Parcel in) {
                FragmentState fragmentState;
                new FragmentState(in);
                return fragmentState;
            }

            public FragmentState[] newArray(int size) {
                return new FragmentState[size];
            }
        };
        CREATOR = creator;
    }
}
