package android.support.p000v4.app;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.p000v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.app.FragmentPagerAdapter */
public abstract class FragmentPagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;
    private final FragmentManager mFragmentManager;

    public abstract Fragment getItem(int i);

    public FragmentPagerAdapter(FragmentManager fm) {
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
        ViewGroup container = viewGroup;
        int position = i;
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        long itemId = getItemId(position);
        Fragment fragment = this.mFragmentManager.findFragmentByTag(makeFragmentName(container.getId(), itemId));
        if (fragment != null) {
            FragmentTransaction attach = this.mCurTransaction.attach(fragment);
        } else {
            fragment = getItem(position);
            FragmentTransaction add = this.mCurTransaction.add(container.getId(), fragment, makeFragmentName(container.getId(), itemId));
        }
        if (fragment != this.mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
        return fragment;
    }

    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        ViewGroup viewGroup2 = viewGroup;
        int i2 = i;
        Object object = obj;
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        FragmentTransaction detach = this.mCurTransaction.detach((Fragment) object);
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
        return null;
    }

    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    public long getItemId(int position) {
        return (long) position;
    }

    private static String makeFragmentName(int viewId, long id) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("android:switcher:").append(viewId).append(":").append(id).toString();
    }
}
