package gnu.lists;

public class VoidConsumer extends FilterConsumer {
    public static VoidConsumer instance;

    static {
        VoidConsumer voidConsumer;
        new VoidConsumer();
        instance = voidConsumer;
    }

    public static VoidConsumer getInstance() {
        return instance;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VoidConsumer() {
        super((Consumer) null);
        this.skipping = true;
    }

    public boolean ignoring() {
        return true;
    }
}
