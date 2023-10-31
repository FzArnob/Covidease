package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
    private final GeneratedAdapter[] mGeneratedAdapters;

    CompositeGeneratedAdaptersObserver(GeneratedAdapter[] generatedAdapters) {
        this.mGeneratedAdapters = generatedAdapters;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        MethodCallsLogger methodCallsLogger;
        LifecycleOwner source = lifecycleOwner;
        Lifecycle.Event event2 = event;
        new MethodCallsLogger();
        MethodCallsLogger logger = methodCallsLogger;
        GeneratedAdapter[] generatedAdapterArr = this.mGeneratedAdapters;
        int length = generatedAdapterArr.length;
        for (int i = 0; i < length; i++) {
            generatedAdapterArr[i].callMethods(source, event2, false, logger);
        }
        GeneratedAdapter[] generatedAdapterArr2 = this.mGeneratedAdapters;
        int length2 = generatedAdapterArr2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            generatedAdapterArr2[i2].callMethods(source, event2, true, logger);
        }
    }
}
