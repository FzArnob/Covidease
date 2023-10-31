package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.mapping.InPort;
import gnu.mapping.WrappedException;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CompileFile {
    public CompileFile() {
    }

    public static final Compilation read(String str, SourceMessages sourceMessages) throws IOException, SyntaxException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        String name = str;
        SourceMessages messages = sourceMessages;
        try {
            InPort fstream = InPort.openFile(name);
            Compilation result = read(fstream, messages);
            fstream.close();
            return result;
        } catch (FileNotFoundException e) {
            FileNotFoundException e2 = e;
            Throwable th3 = th2;
            new StringBuilder();
            new WrappedException(sb2.append("compile-file: file not found: ").append(name).toString(), e2);
            throw th3;
        } catch (IOException e3) {
            IOException e4 = e3;
            Throwable th4 = th;
            new StringBuilder();
            new WrappedException(sb.append("compile-file: read-error: ").append(name).toString(), e4);
            throw th4;
        }
    }

    public static final Compilation read(InPort port, SourceMessages messages) throws IOException, SyntaxException {
        return Language.getDefaultLanguage().parse(port, messages, 0);
    }
}
