package org.shaded.apache.http.message;

import java.util.NoSuchElementException;
import org.shaded.apache.http.HeaderIterator;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.TokenIterator;

public class BasicTokenIterator implements TokenIterator {
    public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
    protected String currentHeader;
    protected String currentToken;
    protected final HeaderIterator headerIt;
    protected int searchPos;

    public BasicTokenIterator(HeaderIterator headerIterator) {
        Throwable th;
        HeaderIterator headerIterator2 = headerIterator;
        if (headerIterator2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Header iterator must not be null.");
            throw th2;
        }
        this.headerIt = headerIterator2;
        this.searchPos = findNext(-1);
    }

    public boolean hasNext() {
        return this.currentToken != null;
    }

    public String nextToken() throws NoSuchElementException, ParseException {
        Throwable th;
        if (this.currentToken == null) {
            Throwable th2 = th;
            new NoSuchElementException("Iteration already finished.");
            throw th2;
        }
        String result = this.currentToken;
        this.searchPos = findNext(this.searchPos);
        return result;
    }

    public final Object next() throws NoSuchElementException, ParseException {
        return nextToken();
    }

    public final void remove() throws UnsupportedOperationException {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException("Removing tokens is not supported.");
        throw th2;
    }

    /* access modifiers changed from: protected */
    public int findNext(int i) throws ParseException {
        int from;
        int from2 = i;
        if (from2 >= 0) {
            from = findTokenSeparator(from2);
        } else if (!this.headerIt.hasNext()) {
            return -1;
        } else {
            this.currentHeader = this.headerIt.nextHeader().getValue();
            from = 0;
        }
        int start = findTokenStart(from);
        if (start < 0) {
            this.currentToken = null;
            return -1;
        }
        int end = findTokenEnd(start);
        this.currentToken = createToken(this.currentHeader, start, end);
        return end;
    }

    /* access modifiers changed from: protected */
    public String createToken(String value, int start, int end) {
        return value.substring(start, end);
    }

    /* access modifiers changed from: protected */
    public int findTokenStart(int i) {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        StringBuffer stringBuffer2;
        int from = i;
        if (from < 0) {
            Throwable th3 = th2;
            new StringBuffer();
            new IllegalArgumentException(stringBuffer2.append("Search position must not be negative: ").append(from).toString());
            throw th3;
        }
        boolean found = false;
        while (!found && this.currentHeader != null) {
            int to = this.currentHeader.length();
            while (!found && from < to) {
                char ch = this.currentHeader.charAt(from);
                if (isTokenSeparator(ch) || isWhitespace(ch)) {
                    from++;
                } else if (isTokenChar(this.currentHeader.charAt(from))) {
                    found = true;
                } else {
                    Throwable th4 = th;
                    new StringBuffer();
                    new ParseException(stringBuffer.append("Invalid character before token (pos ").append(from).append("): ").append(this.currentHeader).toString());
                    throw th4;
                }
            }
            if (!found) {
                if (this.headerIt.hasNext()) {
                    this.currentHeader = this.headerIt.nextHeader().getValue();
                    from = 0;
                } else {
                    this.currentHeader = null;
                }
            }
        }
        return found ? from : -1;
    }

    /* access modifiers changed from: protected */
    public int findTokenSeparator(int i) {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        StringBuffer stringBuffer2;
        Throwable th3;
        StringBuffer stringBuffer3;
        int from = i;
        if (from < 0) {
            Throwable th4 = th3;
            new StringBuffer();
            new IllegalArgumentException(stringBuffer3.append("Search position must not be negative: ").append(from).toString());
            throw th4;
        }
        boolean found = false;
        int to = this.currentHeader.length();
        while (!found && from < to) {
            char ch = this.currentHeader.charAt(from);
            if (isTokenSeparator(ch)) {
                found = true;
            } else if (isWhitespace(ch)) {
                from++;
            } else if (isTokenChar(ch)) {
                Throwable th5 = th2;
                new StringBuffer();
                new ParseException(stringBuffer2.append("Tokens without separator (pos ").append(from).append("): ").append(this.currentHeader).toString());
                throw th5;
            } else {
                Throwable th6 = th;
                new StringBuffer();
                new ParseException(stringBuffer.append("Invalid character after token (pos ").append(from).append("): ").append(this.currentHeader).toString());
                throw th6;
            }
        }
        return from;
    }

    /* access modifiers changed from: protected */
    public int findTokenEnd(int i) {
        Throwable th;
        StringBuffer stringBuffer;
        int from = i;
        if (from < 0) {
            Throwable th2 = th;
            new StringBuffer();
            new IllegalArgumentException(stringBuffer.append("Token start position must not be negative: ").append(from).toString());
            throw th2;
        }
        int to = this.currentHeader.length();
        int end = from + 1;
        while (end < to && isTokenChar(this.currentHeader.charAt(end))) {
            end++;
        }
        return end;
    }

    /* access modifiers changed from: protected */
    public boolean isTokenSeparator(char ch) {
        return ch == ',';
    }

    /* access modifiers changed from: protected */
    public boolean isWhitespace(char c) {
        char ch = c;
        return ch == 9 || Character.isSpaceChar(ch);
    }

    /* access modifiers changed from: protected */
    public boolean isTokenChar(char c) {
        char ch = c;
        if (Character.isLetterOrDigit(ch)) {
            return true;
        }
        if (Character.isISOControl(ch)) {
            return false;
        }
        if (isHttpSeparator(ch)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isHttpSeparator(char ch) {
        return HTTP_SEPARATORS.indexOf(ch) >= 0;
    }
}
