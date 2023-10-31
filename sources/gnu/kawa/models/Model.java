package gnu.kawa.models;

public abstract class Model implements Viewable {
    transient WeakListener listeners;

    public Model() {
    }

    public void addListener(ModelListener listener) {
        WeakListener weakListener;
        new WeakListener(listener, this.listeners);
        this.listeners = weakListener;
    }

    public void addListener(WeakListener weakListener) {
        WeakListener listener = weakListener;
        listener.next = this.listeners;
        this.listeners = listener;
    }

    public void notifyListeners(String str) {
        String key = str;
        WeakListener prev = null;
        WeakListener weakListener = this.listeners;
        while (true) {
            WeakListener wlistener = weakListener;
            if (wlistener != null) {
                Object listener = wlistener.get();
                WeakListener next = wlistener.next;
                if (listener != null) {
                    prev = wlistener;
                    wlistener.update(listener, this, key);
                } else if (prev != null) {
                    prev.next = next;
                }
                weakListener = next;
            } else {
                return;
            }
        }
    }
}
