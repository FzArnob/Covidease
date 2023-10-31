package gnu.kawa.util;

import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: FixupHtmlToc */
class FileInfo {
    static final Pattern childPat = Pattern.compile("<a .*</a>");
    static Hashtable fileMap;
    static final Pattern linkPat = Pattern.compile(" href=['\"]([^'\"]*)['\"]");
    static final Pattern parentPat = Pattern.compile("<ul[^>]* parent=['\"]([^'\"]*)['\"]");
    StringBuffer beforeNavbarText;
    ByteArrayOutputStream bout;
    String[] childLinkText;
    OutPort cout;
    File file;
    FileInputStream fin;

    /* renamed from: in */
    InPort f237in;
    int nchildren;
    StringBuffer newNavbarText;
    StringBuffer oldNavbarText;
    FileInfo parent;
    String parentName;
    String path;
    boolean scanned;
    boolean writeNeeded;

    FileInfo() {
    }

    static {
        Hashtable hashtable;
        new Hashtable();
        fileMap = hashtable;
    }

    public static FileInfo find(File file2) throws Throwable {
        FileInfo fileInfo;
        File file3 = file2;
        String abs = file3.getCanonicalPath();
        FileInfo info = (FileInfo) fileMap.get(abs);
        if (info == null) {
            new FileInfo();
            info = fileInfo;
            info.file = file3;
            Object put = fileMap.put(abs, info);
        }
        return info;
    }

    public void scan() throws Throwable {
        FileInputStream fileInputStream;
        InPort inPort;
        InputStream inputStream;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        Vector vector;
        File parentFile;
        ByteArrayOutputStream byteArrayOutputStream;
        OutPort outPort;
        if (!this.scanned) {
            this.scanned = true;
            new FileInputStream(this.file);
            this.fin = fileInputStream;
            new BufferedInputStream(this.fin);
            new InPort(inputStream);
            this.f237in = inPort;
            new StringBuffer();
            this.oldNavbarText = stringBuffer;
            new StringBuffer();
            this.newNavbarText = stringBuffer2;
            if (this.writeNeeded) {
                new ByteArrayOutputStream();
                this.bout = byteArrayOutputStream;
                new OutPort((OutputStream) this.bout);
                this.cout = outPort;
            }
            boolean inNavbar = false;
            boolean inChildList = false;
            new Vector();
            Vector chvec = vector;
            while (true) {
                String line = this.f237in.readLine();
                if (line == null) {
                    break;
                }
                if (inNavbar) {
                    if (line.indexOf("<!--end-generated-navbar-->") >= 0) {
                        break;
                    }
                    StringBuffer append = this.oldNavbarText.append(line);
                    StringBuffer append2 = this.oldNavbarText.append(10);
                    if (inChildList) {
                        if (line.indexOf("<!--end-children-toc-->") >= 0) {
                            inChildList = false;
                        } else {
                            Matcher childMatcher = childPat.matcher(line);
                            if (childMatcher.find()) {
                                boolean add = chvec.add(childMatcher.group());
                            }
                            Matcher parentMatcher = parentPat.matcher(line);
                            if (parentMatcher.find() && this.parentName == null) {
                                this.parentName = parentMatcher.group(1);
                            }
                        }
                    }
                    if (line.indexOf("<!--start-children-toc-->") >= 0) {
                        inChildList = true;
                    }
                } else if (line.indexOf("<!--start-generated-navbar-->") >= 0) {
                    inNavbar = true;
                }
                if (this.writeNeeded && !inNavbar) {
                    this.cout.println(line);
                }
            }
            String[] charr = new String[chvec.size()];
            this.nchildren = charr.length;
            chvec.copyInto(charr);
            this.childLinkText = charr;
            if (!this.writeNeeded) {
                this.f237in.close();
            }
            if (this.parentName != null) {
                new File(this.file.toURI().resolve(this.parentName));
                FileInfo parentInfo = find(parentFile);
                parentInfo.scan();
                this.parent = parentInfo;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeLinks(int r23, java.lang.StringBuffer r24) throws java.lang.Throwable {
        /*
            r22 = this;
            r2 = r22
            r3 = r23
            r4 = r24
            r17 = r2
            r5 = r17
            r17 = 0
            r6 = r17
            r17 = r3
            r7 = r17
        L_0x0012:
            int r7 = r7 + -1
            r17 = r7
            if (r17 < 0) goto L_0x0027
            r17 = r5
            r6 = r17
            r17 = r5
            r0 = r17
            gnu.kawa.util.FileInfo r0 = r0.parent
            r17 = r0
            r5 = r17
            goto L_0x0012
        L_0x0027:
            r17 = r3
            if (r17 != 0) goto L_0x0034
            r17 = r4
            java.lang.String r18 = "<!--start-children-toc-->\n"
            java.lang.StringBuffer r17 = r17.append(r18)
        L_0x0034:
            r17 = r3
            if (r17 != 0) goto L_0x0158
            r17 = r2
            r0 = r17
            java.lang.String r0 = r0.parentName
            r17 = r0
            if (r17 == 0) goto L_0x0158
            r17 = r4
            java.lang.String r18 = "<ul parent=\""
            java.lang.StringBuffer r17 = r17.append(r18)
            r17 = r4
            r18 = r2
            r0 = r18
            java.lang.String r0 = r0.parentName
            r18 = r0
            java.lang.StringBuffer r17 = r17.append(r18)
            r17 = r4
            java.lang.String r18 = "\">\n"
            java.lang.StringBuffer r17 = r17.append(r18)
        L_0x0062:
            r17 = r2
            r0 = r17
            java.io.File r0 = r0.file
            r17 = r0
            java.net.URI r17 = r17.toURI()
            r7 = r17
            r17 = r5
            r0 = r17
            java.io.File r0 = r0.file
            r17 = r0
            java.net.URI r17 = r17.toURI()
            r8 = r17
            r17 = 0
            r9 = r17
        L_0x0082:
            r17 = r9
            r18 = r5
            r0 = r18
            int r0 = r0.nchildren
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x019a
            r17 = r5
            r0 = r17
            java.lang.String[] r0 = r0.childLinkText
            r17 = r0
            r18 = r9
            r17 = r17[r18]
            r10 = r17
            r17 = 0
            r11 = r17
            r17 = r3
            if (r17 <= 0) goto L_0x0166
            java.util.regex.Pattern r17 = linkPat
            r18 = r10
            java.util.regex.Matcher r17 = r17.matcher(r18)
            r12 = r17
            r17 = r12
            boolean r17 = r17.find()
            r17 = r12
            r18 = 1
            java.lang.String r17 = r17.group(r18)
            r13 = r17
            r17 = r8
            r18 = r13
            java.net.URI r17 = r17.resolve(r18)
            r14 = r17
            r17 = r12
            java.lang.StringBuilder r18 = new java.lang.StringBuilder
            r21 = r18
            r18 = r21
            r19 = r21
            r19.<init>()
            java.lang.String r19 = " href=\""
            java.lang.StringBuilder r18 = r18.append(r19)
            r19 = r14
            java.lang.String r19 = r19.toString()
            r20 = r7
            java.lang.String r20 = r20.toString()
            java.lang.String r19 = gnu.text.Path.relativize(r19, r20)
            java.lang.StringBuilder r18 = r18.append(r19)
            java.lang.String r19 = "\""
            java.lang.StringBuilder r18 = r18.append(r19)
            java.lang.String r18 = r18.toString()
            java.lang.String r17 = r17.replaceFirst(r18)
            r10 = r17
            r17 = r13
            r18 = 35
            int r17 = r17.indexOf(r18)
            r15 = r17
            r17 = r15
            if (r17 < 0) goto L_0x011f
            r17 = r13
            r18 = 0
            r19 = r15
            java.lang.String r17 = r17.substring(r18, r19)
            r13 = r17
        L_0x011f:
            java.io.File r17 = new java.io.File
            r21 = r17
            r17 = r21
            r18 = r21
            r19 = r8
            r20 = r13
            java.net.URI r19 = r19.resolve(r20)
            r18.<init>(r19)
            gnu.kawa.util.FileInfo r17 = find(r17)
            r16 = r17
            r17 = r16
            r18 = r6
            r0 = r17
            r1 = r18
            if (r0 != r1) goto L_0x0163
            r17 = 1
        L_0x0144:
            r11 = r17
            r17 = r11
            if (r17 != 0) goto L_0x0166
            r17 = r3
            r18 = 1
            r0 = r17
            r1 = r18
            if (r0 <= r1) goto L_0x0166
        L_0x0154:
            int r9 = r9 + 1
            goto L_0x0082
        L_0x0158:
            r17 = r4
            java.lang.String r18 = "<ul>\n"
            java.lang.StringBuffer r17 = r17.append(r18)
            goto L_0x0062
        L_0x0163:
            r17 = 0
            goto L_0x0144
        L_0x0166:
            r17 = r4
            java.lang.String r18 = "<li>"
            java.lang.StringBuffer r17 = r17.append(r18)
            r17 = r4
            r18 = r10
            java.lang.StringBuffer r17 = r17.append(r18)
            r17 = r11
            if (r17 == 0) goto L_0x0190
            r17 = r4
            r18 = 10
            java.lang.StringBuffer r17 = r17.append(r18)
            r17 = r2
            r18 = r3
            r19 = 1
            int r18 = r18 + -1
            r19 = r4
            r17.writeLinks(r18, r19)
        L_0x0190:
            r17 = r4
            java.lang.String r18 = "</li>\n"
            java.lang.StringBuffer r17 = r17.append(r18)
            goto L_0x0154
        L_0x019a:
            r17 = r4
            java.lang.String r18 = "</ul>\n"
            java.lang.StringBuffer r17 = r17.append(r18)
            r17 = r3
            if (r17 != 0) goto L_0x01b0
            r17 = r4
            java.lang.String r18 = "<!--end-children-toc-->\n"
            java.lang.StringBuffer r17 = r17.append(r18)
        L_0x01b0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.util.FileInfo.writeLinks(int, java.lang.StringBuffer):void");
    }

    public void write() throws Throwable {
        Object obj;
        FileOutputStream fileOutputStream;
        StringBuilder sb;
        StringBuilder sb2;
        int level = 0;
        FileInfo current = this;
        while (current.parent != null) {
            current = current.parent;
            level++;
        }
        this.cout.println("<!--start-generated-navbar-->");
        writeLinks(level, this.newNavbarText);
        this.cout.print((Object) this.newNavbarText);
        this.cout.println("<!--end-generated-navbar-->");
        while (true) {
            String line = this.f237in.readLine();
            if (line == null) {
                break;
            }
            this.cout.println(line);
        }
        new StringBuffer();
        Object obj2 = obj;
        this.f237in.close();
        if (this.oldNavbarText.toString().equals(this.newNavbarText.toString())) {
            PrintStream printStream = System.err;
            new StringBuilder();
            printStream.println(sb2.append("fixup ").append(this.file).append(" - no change").toString());
            return;
        }
        new FileOutputStream(this.file);
        FileOutputStream outs = fileOutputStream;
        outs.write(this.bout.toByteArray());
        outs.close();
        PrintStream printStream2 = System.err;
        new StringBuilder();
        printStream2.println(sb.append("fixup ").append(this.file).append(" - updated").toString());
    }
}
