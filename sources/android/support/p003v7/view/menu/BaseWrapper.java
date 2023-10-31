package android.support.p003v7.view.menu;

/* renamed from: android.support.v7.view.menu.BaseWrapper */
class BaseWrapper<T> {
    final T mWrappedObject;

    BaseWrapper(T t) {
        Throwable th;
        T object = t;
        if (null == object) {
            Throwable th2 = th;
            new IllegalArgumentException("Wrapped Object can not be null.");
            throw th2;
        }
        this.mWrappedObject = object;
    }

    public T getWrappedObject() {
        return this.mWrappedObject;
    }
}
