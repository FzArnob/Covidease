package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SourceMessages implements SourceLocator {
    public static boolean debugStackTraceOnError = false;
    public static boolean debugStackTraceOnWarning = false;
    int current_column;
    String current_filename;
    int current_line;
    private int errorCount = 0;
    SourceError firstError;
    SourceError lastError;
    SourceError lastPrevFilename = null;
    SourceLocator locator;
    public boolean sortMessages;

    public SourceMessages() {
    }

    public SourceError getErrors() {
        return this.firstError;
    }

    public boolean seenErrors() {
        return this.errorCount > 0;
    }

    public boolean seenErrorsOrWarnings() {
        return this.firstError != null;
    }

    public int getErrorCount() {
        return this.errorCount;
    }

    public void clearErrors() {
        this.errorCount = 0;
    }

    public void clear() {
        this.lastError = null;
        this.firstError = null;
        this.errorCount = 0;
    }

    public void error(SourceError sourceError) {
        SourceError next;
        Throwable th;
        SourceError error = sourceError;
        if (error.severity == 'f') {
            this.errorCount = 1000;
        } else if (error.severity != 'w') {
            this.errorCount++;
        }
        if ((debugStackTraceOnError && (error.severity == 'e' || error.severity == 'f')) || (debugStackTraceOnWarning && error.severity == 'w')) {
            new Throwable();
            error.fakeException = th;
        }
        if (!(this.lastError == null || this.lastError.filename == null || this.lastError.filename.equals(error.filename))) {
            this.lastPrevFilename = this.lastError;
        }
        SourceError prev = this.lastPrevFilename;
        if (this.sortMessages && error.severity != 'f') {
            while (true) {
                if (prev == null) {
                    next = this.firstError;
                } else {
                    next = prev.next;
                }
                if (next == null || (error.line != 0 && next.line != 0 && (error.line < next.line || (error.line == next.line && error.column != 0 && next.column != 0 && error.column < next.column)))) {
                    break;
                }
                prev = next;
            }
        } else {
            prev = this.lastError;
        }
        if (prev == null) {
            error.next = this.firstError;
            this.firstError = error;
        } else {
            error.next = prev.next;
            prev.next = error;
        }
        if (prev == this.lastError) {
            this.lastError = error;
        }
    }

    public void error(char severity, String filename, int line, int column, String message) {
        SourceError sourceError;
        new SourceError(severity, filename, line, column, message);
        error(sourceError);
    }

    public void error(char severity, SourceLocator location, String message) {
        SourceError sourceError;
        new SourceError(severity, location, message);
        error(sourceError);
    }

    public void error(char severity, String filename, int line, int column, String message, String code) {
        SourceError sourceError;
        new SourceError(severity, filename, line, column, message);
        SourceError err = sourceError;
        err.code = code;
        error(err);
    }

    public void error(char severity, SourceLocator location, String message, String code) {
        SourceError sourceError;
        new SourceError(severity, location, message);
        SourceError err = sourceError;
        err.code = code;
        error(err);
    }

    public void error(char severity, String message) {
        SourceError sourceError;
        new SourceError(severity, this.current_filename, this.current_line, this.current_column, message);
        error(sourceError);
    }

    public void error(char severity, String message, Throwable exception) {
        SourceError sourceError;
        new SourceError(severity, this.current_filename, this.current_line, this.current_column, message);
        SourceError err = sourceError;
        err.fakeException = exception;
        error(err);
    }

    public void error(char severity, String message, String code) {
        SourceError sourceError;
        new SourceError(severity, this.current_filename, this.current_line, this.current_column, message);
        SourceError err = sourceError;
        err.code = code;
        error(err);
    }

    public void printAll(PrintStream printStream, int i) {
        PrintStream out = printStream;
        int max = i;
        SourceError sourceError = this.firstError;
        while (true) {
            SourceError err = sourceError;
            if (err != null) {
                max--;
                if (max >= 0) {
                    err.println(out);
                    sourceError = err.next;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public void printAll(PrintWriter printWriter, int i) {
        PrintWriter out = printWriter;
        int max = i;
        SourceError sourceError = this.firstError;
        while (true) {
            SourceError err = sourceError;
            if (err != null) {
                max--;
                if (max >= 0) {
                    err.println(out);
                    sourceError = err.next;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public String toString(int i) {
        StringBuffer stringBuffer;
        int max = i;
        if (this.firstError == null) {
            return null;
        }
        new StringBuffer();
        StringBuffer buffer = stringBuffer;
        SourceError sourceError = this.firstError;
        while (true) {
            SourceError err = sourceError;
            if (err == null) {
                break;
            }
            max--;
            if (max < 0) {
                break;
            }
            StringBuffer append = buffer.append(err);
            StringBuffer append2 = buffer.append(10);
            sourceError = err.next;
        }
        return buffer.toString();
    }

    public boolean checkErrors(PrintWriter printWriter, int i) {
        PrintWriter out = printWriter;
        int max = i;
        if (this.firstError == null) {
            return false;
        }
        printAll(out, max);
        this.lastError = null;
        this.firstError = null;
        int saveCount = this.errorCount;
        this.errorCount = 0;
        return saveCount > 0;
    }

    public boolean checkErrors(PrintStream printStream, int i) {
        PrintStream out = printStream;
        int max = i;
        if (this.firstError == null) {
            return false;
        }
        printAll(out, max);
        this.lastError = null;
        this.firstError = null;
        int saveCount = this.errorCount;
        this.errorCount = 0;
        return saveCount > 0;
    }

    public final void setSourceLocator(SourceLocator sourceLocator) {
        SourceLocator locator2 = sourceLocator;
        this.locator = locator2 == this ? null : locator2;
    }

    public final SourceLocator swapSourceLocator(SourceLocator locator2) {
        SourceLocator save = this.locator;
        this.locator = locator2;
        return save;
    }

    public final void setLocation(SourceLocator sourceLocator) {
        SourceLocator locator2 = sourceLocator;
        this.locator = null;
        this.current_line = locator2.getLineNumber();
        this.current_column = locator2.getColumnNumber();
        this.current_filename = locator2.getFileName();
    }

    public String getPublicId() {
        return this.locator == null ? null : this.locator.getPublicId();
    }

    public String getSystemId() {
        return this.locator == null ? this.current_filename : this.locator.getSystemId();
    }

    public boolean isStableSourceLocation() {
        return false;
    }

    public final String getFileName() {
        return this.current_filename;
    }

    public final int getLineNumber() {
        return this.locator == null ? this.current_line : this.locator.getLineNumber();
    }

    public final int getColumnNumber() {
        return this.locator == null ? this.current_column : this.locator.getColumnNumber();
    }

    public void setFile(String filename) {
        String str = filename;
        this.current_filename = str;
    }

    public void setLine(int line) {
        int i = line;
        this.current_line = i;
    }

    public void setColumn(int column) {
        int i = column;
        this.current_column = i;
    }

    public void setLine(String filename, int line, int column) {
        this.current_filename = filename;
        this.current_line = line;
        this.current_column = column;
    }
}
