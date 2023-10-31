package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class FilePath extends Path implements Comparable<FilePath> {
    final File file;
    final String path;

    private FilePath(File file2) {
        File file3 = file2;
        this.file = file3;
        this.path = file3.toString();
    }

    private FilePath(File file2, String path2) {
        this.file = file2;
        this.path = path2;
    }

    public static FilePath valueOf(String str) {
        FilePath filePath;
        File file2;
        String str2 = str;
        new File(str2);
        new FilePath(file2, str2);
        return filePath;
    }

    public static FilePath valueOf(File file2) {
        FilePath filePath;
        new FilePath(file2);
        return filePath;
    }

    public static FilePath coerceToFilePathOrNull(Object obj) {
        String str;
        File file2;
        File file3;
        Object path2 = obj;
        if (path2 instanceof FilePath) {
            return (FilePath) path2;
        }
        if (path2 instanceof URIPath) {
            new File(((URIPath) path2).uri);
            return valueOf(file3);
        } else if (path2 instanceof URI) {
            new File((URI) path2);
            return valueOf(file2);
        } else if (path2 instanceof File) {
            return valueOf((File) path2);
        } else {
            if (path2 instanceof FString) {
                str = path2.toString();
            } else if (!(path2 instanceof String)) {
                return null;
            } else {
                str = (String) path2;
            }
            return valueOf(str);
        }
    }

    public static FilePath makeFilePath(Object obj) {
        Throwable th;
        Object arg = obj;
        FilePath path2 = coerceToFilePathOrNull(arg);
        if (path2 != null) {
            return path2;
        }
        Throwable th2 = th;
        new WrongType((String) null, -4, arg, "filepath");
        throw th2;
    }

    public boolean isAbsolute() {
        return this == Path.userDirPath || this.file.isAbsolute();
    }

    public boolean isDirectory() {
        int len;
        char last;
        if (this.file.isDirectory()) {
            return true;
        }
        if (this.file.exists() || (len = this.path.length()) <= 0 || ((last = this.path.charAt(len - 1)) != '/' && last != File.separatorChar)) {
            return false;
        }
        return true;
    }

    public boolean delete() {
        return toFile().delete();
    }

    public long getLastModified() {
        return this.file.lastModified();
    }

    public boolean exists() {
        return this.file.exists();
    }

    public long getContentLength() {
        long length = this.file.length();
        return (length != 0 || this.file.exists()) ? length : -1;
    }

    public String getPath() {
        return this.file.getPath();
    }

    public String getLast() {
        return this.file.getName();
    }

    public FilePath getParent() {
        File parent = this.file.getParentFile();
        if (parent == null) {
            return null;
        }
        return valueOf(parent);
    }

    public int compareTo(FilePath path2) {
        return this.file.compareTo(path2.file);
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        return (obj2 instanceof FilePath) && this.file.equals(((FilePath) obj2).file);
    }

    public int hashCode() {
        return this.file.hashCode();
    }

    public String toString() {
        return this.path;
    }

    public File toFile() {
        return this.file;
    }

    public URL toURL() {
        if (this == Path.userDirPath) {
            return resolve("").toURL();
        }
        if (!isAbsolute()) {
            return getAbsolute().toURL();
        }
        try {
            return this.file.toURI().toURL();
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    private static URI toUri(File file2) {
        URI uri;
        File file3 = file2;
        try {
            if (file3.isAbsolute()) {
                return file3.toURI();
            }
            String fname = file3.toString();
            char fileSep = File.separatorChar;
            if (fileSep != '/') {
                fname = fname.replace(fileSep, '/');
            }
            URI uri2 = uri;
            new URI((String) null, (String) null, fname, (String) null);
            return uri2;
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public URI toUri() {
        if (this == Path.userDirPath) {
            return resolve("").toURI();
        }
        return toUri(this.file);
    }

    public InputStream openInputStream() throws IOException {
        InputStream inputStream;
        new FileInputStream(this.file);
        return inputStream;
    }

    public OutputStream openOutputStream() throws IOException {
        OutputStream outputStream;
        new FileOutputStream(this.file);
        return outputStream;
    }

    public String getScheme() {
        return isAbsolute() ? "file" : null;
    }

    public Path resolve(String str) {
        File file2;
        File nfile;
        File file3;
        String relative = str;
        if (Path.uriSchemeSpecified(relative)) {
            return URLPath.valueOf(relative);
        }
        new File(relative);
        File rfile = file2;
        if (rfile.isAbsolute()) {
            return valueOf(rfile);
        }
        char sep = File.separatorChar;
        if (sep != '/') {
            relative = relative.replace('/', sep);
        }
        if (this == Path.userDirPath) {
            new File(System.getProperty("user.dir"), relative);
            nfile = file3;
        } else {
            File file4 = r9;
            File file5 = new File(isDirectory() ? this.file : this.file.getParentFile(), relative);
            nfile = file4;
        }
        return valueOf(nfile);
    }

    public Path getCanonical() {
        try {
            File canon = this.file.getCanonicalFile();
            if (!canon.equals(this.file)) {
                return valueOf(canon);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
        }
        return this;
    }
}
