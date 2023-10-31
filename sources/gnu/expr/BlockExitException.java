package gnu.expr;

/* compiled from: BlockExp */
class BlockExitException extends RuntimeException {
    ExitExp exit;
    Object result;

    public BlockExitException(ExitExp exit2, Object result2) {
        this.exit = exit2;
        this.result = result2;
    }
}
