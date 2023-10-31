package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipArchive {
    public ZipArchive() {
    }

    private static void usage() {
        System.err.println("zipfile [ptxq] archive [file ...]");
        System.exit(-1);
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        InputStream in = inputStream;
        OutputStream out = outputStream;
        byte[] buffer = bArr;
        long j = 0;
        while (true) {
            long total = j;
            int count = in.read(buffer);
            if (count <= 0) {
                return total;
            }
            out.write(buffer, 0, count);
            j = total + ((long) count);
        }
    }

    public static void copy(InputStream inputStream, String str, byte[] bArr) throws IOException {
        File file;
        OutputStream outputStream;
        OutputStream outputStream2;
        File file2;
        StringBuilder sb;
        InputStream in = inputStream;
        String name = str;
        byte[] buffer = bArr;
        new File(name);
        File f = file;
        String dir_name = f.getParent();
        if (dir_name != null) {
            new File(dir_name);
            File dir = file2;
            if (!dir.exists()) {
                PrintStream printStream = System.err;
                new StringBuilder();
                printStream.println(sb.append("mkdirs:").append(dir.mkdirs()).toString());
            }
        }
        if (name.charAt(name.length() - 1) != '/') {
            new FileOutputStream(f);
            new BufferedOutputStream(outputStream2);
            OutputStream out = outputStream;
            long copy = copy(in, out, buffer);
            out.close();
        }
    }

    public static void main(String[] strArr) throws IOException {
        StringBuilder sb;
        ZipFile zipFile;
        StringBuilder sb2;
        InputStream inputStream;
        InputStream inputStream2;
        ZipInputStream zipInputStream;
        ZipOutputStream zipOutputStream;
        OutputStream outputStream;
        File file;
        Throwable th;
        StringBuilder sb3;
        Throwable th2;
        StringBuilder sb4;
        FileInputStream fileInputStream;
        Throwable th3;
        StringBuilder sb5;
        ZipEntry zipEntry;
        String[] args = strArr;
        if (args.length < 2) {
            usage();
        }
        String command = args[0];
        String archive_name = args[1];
        try {
            if (command.equals("t") || command.equals("p") || command.equals("x")) {
                PrintStream out = System.out;
                byte[] buf = new byte[1024];
                if (args.length == 2) {
                    new FileInputStream(archive_name);
                    new BufferedInputStream(inputStream2);
                    new ZipInputStream(inputStream);
                    ZipInputStream zin = zipInputStream;
                    while (true) {
                        ZipEntry nextEntry = zin.getNextEntry();
                        ZipEntry zent = nextEntry;
                        if (nextEntry != null) {
                            String name = zent.getName();
                            if (command.equals("t")) {
                                out.print(name);
                                out.print(" size: ");
                                out.println(zent.getSize());
                            } else if (command.equals("p")) {
                                long copy = copy((InputStream) zin, (OutputStream) out, buf);
                            } else {
                                copy((InputStream) zin, name, buf);
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    new ZipFile(archive_name);
                    ZipFile zar = zipFile;
                    for (int i = 2; i < args.length; i++) {
                        String name2 = args[i];
                        ZipEntry zent2 = zar.getEntry(name2);
                        if (zent2 == null) {
                            PrintStream printStream = System.err;
                            new StringBuilder();
                            printStream.println(sb2.append("zipfile ").append(archive_name).append(":").append(args[i]).append(" - not found").toString());
                            System.exit(-1);
                        } else if (command.equals("t")) {
                            out.print(name2);
                            out.print(" size: ");
                            out.println(zent2.getSize());
                        } else if (command.equals("p")) {
                            long copy2 = copy(zar.getInputStream(zent2), (OutputStream) out, buf);
                        } else {
                            copy(zar.getInputStream(zent2), name2, buf);
                        }
                    }
                }
            } else if (command.equals("q")) {
                new FileOutputStream(archive_name);
                new ZipOutputStream(outputStream);
                ZipOutputStream zar2 = zipOutputStream;
                int i2 = 2;
                while (i2 < args.length) {
                    new File(args[i2]);
                    File in = file;
                    if (!in.exists()) {
                        Throwable th4 = th;
                        new StringBuilder();
                        new IOException(sb3.append(args[i2]).append(" - not found").toString());
                        throw th4;
                    } else if (!in.canRead()) {
                        Throwable th5 = th2;
                        new StringBuilder();
                        new IOException(sb4.append(args[i2]).append(" - not readable").toString());
                        throw th5;
                    } else {
                        int size = (int) in.length();
                        new FileInputStream(in);
                        FileInputStream fin = fileInputStream;
                        byte[] contents = new byte[size];
                        if (fin.read(contents) != size) {
                            Throwable th6 = th3;
                            new StringBuilder();
                            new IOException(sb5.append(args[i2]).append(" - read error").toString());
                            throw th6;
                        }
                        fin.close();
                        new ZipEntry(args[i2]);
                        ZipEntry ze = zipEntry;
                        ze.setSize((long) size);
                        ze.setTime(in.lastModified());
                        zar2.putNextEntry(ze);
                        zar2.write(contents, 0, size);
                        i2++;
                    }
                }
                zar2.close();
            } else {
                usage();
            }
        } catch (IOException e) {
            IOException ex = e;
            PrintStream printStream2 = System.err;
            new StringBuilder();
            printStream2.println(sb.append("I/O Exception:  ").append(ex).toString());
        }
    }
}
