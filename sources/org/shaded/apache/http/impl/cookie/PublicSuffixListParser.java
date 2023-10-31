package org.shaded.apache.http.impl.cookie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class PublicSuffixListParser {
    private static final int MAX_LINE_LEN = 256;
    private final PublicSuffixFilter filter;

    PublicSuffixListParser(PublicSuffixFilter filter2) {
        this.filter = filter2;
    }

    public void parse(Reader list) throws IOException {
        Collection<String> collection;
        Collection<String> collection2;
        Reader reader;
        StringBuilder sb;
        new ArrayList<>();
        Collection<String> rules = collection;
        new ArrayList<>();
        Collection<String> exceptions = collection2;
        new BufferedReader(list);
        Reader reader2 = reader;
        new StringBuilder(256);
        StringBuilder sb2 = sb;
        boolean more = true;
        while (more) {
            more = readLine(reader2, sb2);
            String line = sb2.toString();
            if (line.length() != 0 && !line.startsWith("//")) {
                if (line.startsWith(".")) {
                    line = line.substring(1);
                }
                boolean isException = line.startsWith("!");
                if (isException) {
                    line = line.substring(1);
                }
                if (isException) {
                    boolean add = exceptions.add(line);
                } else {
                    boolean add2 = rules.add(line);
                }
            }
        }
        this.filter.setPublicSuffixes(rules);
        this.filter.setExceptions(exceptions);
    }

    private boolean readLine(Reader reader, StringBuilder sb) throws IOException {
        char c;
        Throwable th;
        Reader r = reader;
        StringBuilder sb2 = sb;
        sb2.setLength(0);
        boolean hitWhitespace = false;
        do {
            int read = r.read();
            int b = read;
            if (read == -1 || (c = (char) b) == 10) {
                return b != -1;
            }
            if (Character.isWhitespace(c)) {
                hitWhitespace = true;
            }
            if (!hitWhitespace) {
                StringBuilder append = sb2.append(c);
            }
        } while (sb2.length() <= 256);
        Throwable th2 = th;
        new IOException("Line too long");
        throw th2;
    }
}
