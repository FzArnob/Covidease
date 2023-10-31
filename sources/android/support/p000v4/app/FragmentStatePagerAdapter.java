package android.support.p000v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: android.support.v4.app.FragmentStatePagerAdapter */
public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentStatePagerAdapt";
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragments;
    private ArrayList<Fragment.SavedState> mSavedState;

    public abstract Fragment getItem(int i);

    public FragmentStatePagerAdapter(FragmentManager fm) {
        ArrayList<Fragment.SavedState> arrayList;
        ArrayList<Fragment> arrayList2;
        new ArrayList<>();
        this.mSavedState = arrayList;
        new ArrayList<>();
        this.mFragments = arrayList2;
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = fm;
    }

    public void startUpdate(@NonNull ViewGroup container) {
        Throwable th;
        StringBuilder sb;
        if (container.getId() == -1) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("ViewPager with adapter ").append(this).append(" requires a view id").toString());
            throw th2;
        }
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        Fragment.SavedState fss;
        Fragment f;
        ViewGroup container = viewGroup;
        int position = i;
        if (this.mFragments.size() > position && (f = this.mFragments.get(position)) != null) {
            return f;
        }
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        Fragment fragment = getItem(position);
        if (this.mSavedState.size() > position && (fss = this.mSavedState.get(position)) != null) {
            fragment.setInitialSavedState(fss);
        }
        while (this.mFragments.size() <= position) {
            boolean add = this.mFragments.add((Object) null);
        }
        fragment.setMenuVisibility(false);
        fragment.setUserVisibleHint(false);
        Fragment fragment2 = this.mFragments.set(position, fragment);
        FragmentTransaction add2 = this.mCurTransaction.add(container.getId(), fragment);
        return fragment;
    }

    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object object) {
        ViewGroup viewGroup2 = viewGroup;
        int position = i;
        Fragment fragment = (Fragment) object;
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        while (this.mSavedState.size() <= position) {
            boolean add = this.mSavedState.add((Object) null);
        }
        Fragment.SavedState savedState = this.mSavedState.set(position, fragment.isAdded() ? this.mFragmentManager.saveFragmentInstanceState(fragment) : null);
        Fragment fragment2 = this.mFragments.set(position, (Object) null);
        FragmentTransaction remove = this.mCurTransaction.remove(fragment);
    }

    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object object) {
        ViewGroup viewGroup2 = viewGroup;
        int i2 = i;
        Fragment fragment = (Fragment) object;
        if (fragment != this.mCurrentPrimaryItem) {
            if (this.mCurrentPrimaryItem != null) {
                this.mCurrentPrimaryItem.setMenuVisibility(false);
                this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            this.mCurrentPrimaryItem = fragment;
        }
    }

    public void finishUpdate(@NonNull ViewGroup viewGroup) {
        ViewGroup viewGroup2 = viewGroup;
        if (this.mCurTransaction != null) {
            this.mCurTransaction.commitNowAllowingStateLoss();
            this.mCurTransaction = null;
        }
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((Fragment) object).getView() == view;
    }

    public Parcelable saveState() {
        StringBuilder sb;
        Bundle bundle;
        Bundle bundle2;
        Bundle state = null;
        if (this.mSavedState.size() > 0) {
            new Bundle();
            state = bundle2;
            Fragment.SavedState[] fss = new Fragment.SavedState[this.mSavedState.size()];
            Object[] array = this.mSavedState.toArray(fss);
            state.putParcelableArray("states", fss);
        }
        for (int i = 0; i < this.mFragments.size(); i++) {
            Fragment f = this.mFragments.get(i);
            if (f != null && f.isAdded()) {
                if (state == null) {
                    new Bundle();
                    state = bundle;
                }
                new StringBuilder();
                this.mFragmentManager.putFragment(state, sb.append("f").append(i).toString(), f);
            }
        }
        return state;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        StringBuilder sb;
        Parcelable state = parcelable;
        ClassLoader loader = classLoader;
        if (state != null) {
            Bundle bundle = (Bundle) state;
            bundle.setClassLoader(loader);
            Parcelable[] fss = bundle.getParcelableArray("states");
            this.mSavedState.clear();
            this.mFragments.clear();
            if (fss != null) {
                for (int i = 0; i < fss.length; i++) {
                    boolean add = this.mSavedState.add((Fragment.SavedState) fss[i]);
                }
            }
            for (String key : bundle.keySet()) {
                if (key.startsWith("f")) {
                    int index = Integer.parseInt(key.substring(1));
                    Fragment f = this.mFragmentManager.getFragment(bundle, key);
                    if (f != null) {
                        while (this.mFragments.size() <= index) {
                            boolean add2 = this.mFragments.add((Object) null);
                        }
                        f.setMenuVisibility(false);
                        Fragment fragment = this.mFragments.set(index, f);
                    } else {
                        new StringBuilder();
                        int w = Log.w(TAG, sb.append("Bad fragment at key ").append(key).toString());
                    }
                }
            }
        }
    }
}
