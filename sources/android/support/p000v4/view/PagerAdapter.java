package android.support.p000v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.view.PagerAdapter */
public abstract class PagerAdapter {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private final DataSetObservable mObservable;
    private DataSetObserver mViewPagerObserver;

    public abstract int getCount();

    public abstract boolean isViewFromObject(@NonNull View view, @NonNull Object obj);

    public PagerAdapter() {
        DataSetObservable dataSetObservable;
        new DataSetObservable();
        this.mObservable = dataSetObservable;
    }

    public void startUpdate(@NonNull ViewGroup container) {
        startUpdate((View) container);
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return instantiateItem((View) container, position);
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        destroyItem((View) container, position, object);
    }

    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        setPrimaryItem((View) container, position, object);
    }

    public void finishUpdate(@NonNull ViewGroup container) {
        finishUpdate((View) container);
    }

    @Deprecated
    public void startUpdate(@NonNull View container) {
    }

    @Deprecated
    @NonNull
    public Object instantiateItem(@NonNull View view, int i) {
        Throwable th;
        View view2 = view;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException("Required method instantiateItem was not overridden");
        throw th2;
    }

    @Deprecated
    public void destroyItem(@NonNull View view, int i, @NonNull Object obj) {
        Throwable th;
        View view2 = view;
        int i2 = i;
        Object obj2 = obj;
        Throwable th2 = th;
        new UnsupportedOperationException("Required method destroyItem was not overridden");
        throw th2;
    }

    @Deprecated
    public void setPrimaryItem(@NonNull View container, int position, @NonNull Object object) {
    }

    @Deprecated
    public void finishUpdate(@NonNull View container) {
    }

    @Nullable
    public Parcelable saveState() {
        return null;
    }

    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
    }

    public int getItemPosition(@NonNull Object obj) {
        Object obj2 = obj;
        return -1;
    }

    public void notifyDataSetChanged() {
        synchronized (this) {
            try {
                if (this.mViewPagerObserver != null) {
                    this.mViewPagerObserver.onChanged();
                }
                this.mObservable.notifyChanged();
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        }
    }

    public void registerDataSetObserver(@NonNull DataSetObserver observer) {
        this.mObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(@NonNull DataSetObserver observer) {
        this.mObservable.unregisterObserver(observer);
    }

    /* access modifiers changed from: package-private */
    public void setViewPagerObserver(DataSetObserver dataSetObserver) {
        DataSetObserver observer = dataSetObserver;
        synchronized (this) {
            try {
                this.mViewPagerObserver = observer;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    @Nullable
    public CharSequence getPageTitle(int i) {
        int i2 = i;
        return null;
    }

    public float getPageWidth(int i) {
        int i2 = i;
        return 1.0f;
    }
}
