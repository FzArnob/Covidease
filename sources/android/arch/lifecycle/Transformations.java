package android.arch.lifecycle;

import android.arch.core.util.Function;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Transformations {
    private Transformations() {
    }

    @MainThread
    public static <X, Y> LiveData<Y> map(@NonNull LiveData<X> source, @NonNull Function<X, Y> func) {
        LiveData<X> liveData;
        Observer observer;
        new MediatorLiveData<>();
        LiveData<X> source2 = liveData;
        final MediatorLiveData mediatorLiveData = source2;
        final Function<X, Y> function = func;
        new Observer<X>() {
            public void onChanged(@Nullable X x) {
                mediatorLiveData.setValue(function.apply(x));
            }
        };
        source2.addSource(source, observer);
        return source2;
    }

    @MainThread
    public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> trigger, @NonNull Function<X, LiveData<Y>> func) {
        LiveData<X> liveData;
        Observer observer;
        new MediatorLiveData<>();
        LiveData<X> trigger2 = liveData;
        final Function<X, LiveData<Y>> function = func;
        final MediatorLiveData mediatorLiveData = trigger2;
        new Observer<X>() {
            LiveData<Y> mSource;

            public void onChanged(@Nullable X x) {
                Observer observer;
                LiveData<Y> newLiveData = (LiveData) function.apply(x);
                if (this.mSource != newLiveData) {
                    if (this.mSource != null) {
                        mediatorLiveData.removeSource(this.mSource);
                    }
                    this.mSource = newLiveData;
                    if (this.mSource != null) {
                        new Observer<Y>(this) {
                            final /* synthetic */ C00102 this$0;

                            {
                                this.this$0 = this$0;
                            }

                            public void onChanged(@Nullable Y y) {
                                mediatorLiveData.setValue(y);
                            }
                        };
                        mediatorLiveData.addSource(this.mSource, observer);
                    }
                }
            }
        };
        trigger2.addSource(trigger, observer);
        return trigger2;
    }
}
