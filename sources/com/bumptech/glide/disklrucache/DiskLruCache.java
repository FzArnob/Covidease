package com.bumptech.glide.disklrucache;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class DiskLruCache implements Closeable {
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Callable<Void> cleanupCallable;
    /* access modifiers changed from: private */
    public final File directory;
    final ThreadPoolExecutor executorService;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public Writer journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries;
    private long maxSize;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size = 0;
    /* access modifiers changed from: private */
    public final int valueCount;

    static /* synthetic */ int access$402(DiskLruCache x0, int x1) {
        int i = x1;
        int i2 = i;
        x0.redundantOpCount = i2;
        return i;
    }

    private DiskLruCache(File file, int appVersion2, int valueCount2, long maxSize2) {
        LinkedHashMap<String, Entry> linkedHashMap;
        ThreadPoolExecutor threadPoolExecutor;
        BlockingQueue blockingQueue;
        Callable<Void> callable;
        File file2;
        File file3;
        File file4;
        File directory2 = file;
        new LinkedHashMap<>(0, 0.75f, true);
        this.lruEntries = linkedHashMap;
        this.nextSequenceNumber = 0;
        new LinkedBlockingQueue();
        new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, blockingQueue);
        this.executorService = threadPoolExecutor;
        new Callable<Void>(this) {
            final /* synthetic */ DiskLruCache this$0;

            {
                this.this$0 = r5;
            }

            public Void call() throws Exception {
                DiskLruCache diskLruCache = this.this$0;
                DiskLruCache diskLruCache2 = diskLruCache;
                synchronized (diskLruCache) {
                    try {
                        if (this.this$0.journalWriter == null) {
                            return null;
                        }
                        this.this$0.trimToSize();
                        if (this.this$0.journalRebuildRequired()) {
                            this.this$0.rebuildJournal();
                            int access$402 = DiskLruCache.access$402(this.this$0, 0);
                        }
                        return null;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        DiskLruCache diskLruCache3 = diskLruCache2;
                        throw th2;
                    }
                }
            }
        };
        this.cleanupCallable = callable;
        this.directory = directory2;
        this.appVersion = appVersion2;
        new File(directory2, JOURNAL_FILE);
        this.journalFile = file2;
        new File(directory2, JOURNAL_FILE_TEMP);
        this.journalFileTmp = file3;
        new File(directory2, JOURNAL_FILE_BACKUP);
        this.journalFileBackup = file4;
        this.valueCount = valueCount2;
        this.maxSize = maxSize2;
    }

    public static DiskLruCache open(File file, int i, int i2, long j) throws IOException {
        File file2;
        DiskLruCache diskLruCache;
        DiskLruCache diskLruCache2;
        StringBuilder sb;
        File file3;
        Throwable th;
        Throwable th2;
        File directory2 = file;
        int appVersion2 = i;
        int valueCount2 = i2;
        long maxSize2 = j;
        if (maxSize2 <= 0) {
            Throwable th3 = th2;
            new IllegalArgumentException("maxSize <= 0");
            throw th3;
        } else if (valueCount2 <= 0) {
            Throwable th4 = th;
            new IllegalArgumentException("valueCount <= 0");
            throw th4;
        } else {
            new File(directory2, JOURNAL_FILE_BACKUP);
            File backupFile = file2;
            if (backupFile.exists()) {
                new File(directory2, JOURNAL_FILE);
                File journalFile2 = file3;
                if (journalFile2.exists()) {
                    boolean delete = backupFile.delete();
                } else {
                    renameTo(backupFile, journalFile2, false);
                }
            }
            new DiskLruCache(directory2, appVersion2, valueCount2, maxSize2);
            DiskLruCache cache = diskLruCache;
            if (cache.journalFile.exists()) {
                try {
                    cache.readJournal();
                    cache.processJournal();
                    return cache;
                } catch (IOException e) {
                    IOException journalIsCorrupt = e;
                    PrintStream printStream = System.out;
                    new StringBuilder();
                    printStream.println(sb.append("DiskLruCache ").append(directory2).append(" is corrupt: ").append(journalIsCorrupt.getMessage()).append(", removing").toString());
                    cache.delete();
                }
            }
            boolean mkdirs = directory2.mkdirs();
            new DiskLruCache(directory2, appVersion2, valueCount2, maxSize2);
            DiskLruCache cache2 = diskLruCache2;
            cache2.rebuildJournal();
            return cache2;
        }
    }

    private void readJournal() throws IOException {
        StrictLineReader strictLineReader;
        InputStream inputStream;
        Throwable th;
        StringBuilder sb;
        int lineCount;
        Writer writer;
        Writer writer2;
        OutputStream outputStream;
        new FileInputStream(this.journalFile);
        new StrictLineReader(inputStream, Util.US_ASCII);
        StrictLineReader reader = strictLineReader;
        try {
            String magic = reader.readLine();
            String version = reader.readLine();
            String appVersionString = reader.readLine();
            String valueCountString = reader.readLine();
            String blank = reader.readLine();
            if (!MAGIC.equals(magic) || !VERSION_1.equals(version) || !Integer.toString(this.appVersion).equals(appVersionString) || !Integer.toString(this.valueCount).equals(valueCountString) || !"".equals(blank)) {
                Throwable th2 = th;
                new StringBuilder();
                new IOException(sb.append("unexpected journal header: [").append(magic).append(", ").append(version).append(", ").append(valueCountString).append(", ").append(blank).append("]").toString());
                throw th2;
            }
            lineCount = 0;
            while (true) {
                readJournalLine(reader.readLine());
                lineCount++;
            }
        } catch (EOFException e) {
            EOFException eOFException = e;
            this.redundantOpCount = lineCount - this.lruEntries.size();
            if (reader.hasUnterminatedLine()) {
                rebuildJournal();
            } else {
                new FileOutputStream(this.journalFile, true);
                new OutputStreamWriter(outputStream, Util.US_ASCII);
                new BufferedWriter(writer2);
                this.journalWriter = writer;
            }
            Util.closeQuietly(reader);
        } catch (Throwable th3) {
            Throwable th4 = th3;
            Util.closeQuietly(reader);
            throw th4;
        }
    }

    private void readJournalLine(String str) throws IOException {
        String key;
        Throwable th;
        StringBuilder sb;
        Editor editor;
        Entry entry;
        Throwable th2;
        StringBuilder sb2;
        String line = str;
        int firstSpace = line.indexOf(32);
        if (firstSpace == -1) {
            Throwable th3 = th2;
            new StringBuilder();
            new IOException(sb2.append("unexpected journal line: ").append(line).toString());
            throw th3;
        }
        int keyBegin = firstSpace + 1;
        int secondSpace = line.indexOf(32, keyBegin);
        if (secondSpace == -1) {
            key = line.substring(keyBegin);
            if (firstSpace == REMOVE.length() && line.startsWith(REMOVE)) {
                Object remove = this.lruEntries.remove(key);
                return;
            }
        } else {
            key = line.substring(keyBegin, secondSpace);
        }
        Entry entry2 = this.lruEntries.get(key);
        if (entry2 == null) {
            new Entry(this, key, (C15081) null);
            entry2 = entry;
            Object put = this.lruEntries.put(key, entry2);
        }
        if (secondSpace != -1 && firstSpace == CLEAN.length() && line.startsWith(CLEAN)) {
            String[] parts = line.substring(secondSpace + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            boolean access$602 = Entry.access$602(entry2, true);
            Editor access$702 = Entry.access$702(entry2, (Editor) null);
            entry2.setLengths(parts);
        } else if (secondSpace == -1 && firstSpace == DIRTY.length() && line.startsWith(DIRTY)) {
            new Editor(this, entry2, (C15081) null);
            Editor access$7022 = Entry.access$702(entry2, editor);
        } else if (secondSpace != -1 || firstSpace != READ.length() || !line.startsWith(READ)) {
            Throwable th4 = th;
            new StringBuilder();
            new IOException(sb.append("unexpected journal line: ").append(line).toString());
            throw th4;
        }
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator<Entry> i = this.lruEntries.values().iterator();
        while (i.hasNext()) {
            Entry entry = i.next();
            if (entry.currentEditor == null) {
                for (int t = 0; t < this.valueCount; t++) {
                    this.size += entry.lengths[t];
                }
            } else {
                Editor access$702 = Entry.access$702(entry, (Editor) null);
                for (int t2 = 0; t2 < this.valueCount; t2++) {
                    deleteIfExists(entry.getCleanFile(t2));
                    deleteIfExists(entry.getDirtyFile(t2));
                }
                i.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        Writer writer;
        Writer writer2;
        OutputStream outputStream;
        Writer writer3;
        Writer writer4;
        OutputStream outputStream2;
        StringBuilder sb;
        StringBuilder sb2;
        synchronized (this) {
            if (this.journalWriter != null) {
                this.journalWriter.close();
            }
            Writer writer5 = writer;
            new FileOutputStream(this.journalFileTmp);
            new OutputStreamWriter(outputStream, Util.US_ASCII);
            new BufferedWriter(writer2);
            Writer writer6 = writer5;
            try {
                writer6.write(MAGIC);
                writer6.write("\n");
                writer6.write(VERSION_1);
                writer6.write("\n");
                writer6.write(Integer.toString(this.appVersion));
                writer6.write("\n");
                writer6.write(Integer.toString(this.valueCount));
                writer6.write("\n");
                writer6.write("\n");
                for (Entry entry : this.lruEntries.values()) {
                    if (entry.currentEditor != null) {
                        new StringBuilder();
                        writer6.write(sb2.append("DIRTY ").append(entry.key).append(10).toString());
                    } else {
                        new StringBuilder();
                        writer6.write(sb.append("CLEAN ").append(entry.key).append(entry.getLengths()).append(10).toString());
                    }
                }
                writer6.close();
                if (this.journalFile.exists()) {
                    renameTo(this.journalFile, this.journalFileBackup, true);
                }
                renameTo(this.journalFileTmp, this.journalFile, false);
                boolean delete = this.journalFileBackup.delete();
                new FileOutputStream(this.journalFile, true);
                new OutputStreamWriter(outputStream2, Util.US_ASCII);
                new BufferedWriter(writer4);
                this.journalWriter = writer3;
            } catch (Throwable th) {
                Throwable th2 = th;
                writer6.close();
                throw th2;
            }
        }
    }

    private static void deleteIfExists(File file) throws IOException {
        Throwable th;
        File file2 = file;
        if (file2.exists() && !file2.delete()) {
            Throwable th2 = th;
            new IOException();
            throw th2;
        }
    }

    private static void renameTo(File file, File file2, boolean deleteDestination) throws IOException {
        Throwable th;
        File from = file;
        File to = file2;
        if (deleteDestination) {
            deleteIfExists(to);
        }
        if (!from.renameTo(to)) {
            Throwable th2 = th;
            new IOException();
            throw th2;
        }
    }

    public synchronized Value get(String str) throws IOException {
        Value value;
        Value value2;
        String key = str;
        synchronized (this) {
            checkNotClosed();
            Entry entry = this.lruEntries.get(key);
            if (entry == null) {
                value2 = null;
            } else if (!entry.readable) {
                value2 = null;
            } else {
                File[] arr$ = entry.cleanFiles;
                int len$ = arr$.length;
                int i$ = 0;
                while (true) {
                    if (i$ >= len$) {
                        this.redundantOpCount++;
                        Writer append = this.journalWriter.append(READ);
                        Writer append2 = this.journalWriter.append(' ');
                        Writer append3 = this.journalWriter.append(key);
                        Writer append4 = this.journalWriter.append(10);
                        if (journalRebuildRequired()) {
                            Future submit = this.executorService.submit(this.cleanupCallable);
                        }
                        Value value3 = value;
                        new Value(this, key, entry.sequenceNumber, entry.cleanFiles, entry.lengths, (C15081) null);
                        value2 = value3;
                    } else if (!arr$[i$].exists()) {
                        value2 = null;
                        break;
                    } else {
                        i$++;
                    }
                }
            }
        }
        return value2;
    }

    public Editor edit(String key) throws IOException {
        return edit(key, -1);
    }

    /* access modifiers changed from: private */
    public synchronized Editor edit(String str, long j) throws IOException {
        Editor editor;
        Editor editor2;
        Entry entry;
        String key = str;
        long expectedSequenceNumber = j;
        synchronized (this) {
            checkNotClosed();
            Entry entry2 = this.lruEntries.get(key);
            if (expectedSequenceNumber == -1 || (entry2 != null && entry2.sequenceNumber == expectedSequenceNumber)) {
                if (entry2 == null) {
                    new Entry(this, key, (C15081) null);
                    entry2 = entry;
                    Object put = this.lruEntries.put(key, entry2);
                } else if (entry2.currentEditor != null) {
                    editor = null;
                }
                new Editor(this, entry2, (C15081) null);
                Editor editor3 = editor2;
                Editor access$702 = Entry.access$702(entry2, editor3);
                Writer append = this.journalWriter.append(DIRTY);
                Writer append2 = this.journalWriter.append(' ');
                Writer append3 = this.journalWriter.append(key);
                Writer append4 = this.journalWriter.append(10);
                this.journalWriter.flush();
                editor = editor3;
            } else {
                editor = null;
            }
        }
        return editor;
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        long j;
        synchronized (this) {
            j = this.maxSize;
        }
        return j;
    }

    public synchronized void setMaxSize(long j) {
        long maxSize2 = j;
        synchronized (this) {
            this.maxSize = maxSize2;
            Future submit = this.executorService.submit(this.cleanupCallable);
        }
    }

    public synchronized long size() {
        long j;
        synchronized (this) {
            j = this.size;
        }
        return j;
    }

    /* access modifiers changed from: private */
    public synchronized void completeEdit(Editor editor, boolean z) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Editor editor2 = editor;
        boolean success = z;
        synchronized (this) {
            Entry entry = editor2.entry;
            if (entry.currentEditor != editor2) {
                Throwable th3 = th2;
                new IllegalStateException();
                throw th3;
            }
            if (success) {
                if (!entry.readable) {
                    int i = 0;
                    while (true) {
                        if (i >= this.valueCount) {
                            break;
                        } else if (!editor2.written[i]) {
                            editor2.abort();
                            Throwable th4 = th;
                            new StringBuilder();
                            new IllegalStateException(sb.append("Newly created entry didn't create value for index ").append(i).toString());
                            throw th4;
                        } else if (!entry.getDirtyFile(i).exists()) {
                            editor2.abort();
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            for (int i2 = 0; i2 < this.valueCount; i2++) {
                File dirty = entry.getDirtyFile(i2);
                if (!success) {
                    deleteIfExists(dirty);
                } else if (dirty.exists()) {
                    File clean = entry.getCleanFile(i2);
                    boolean renameTo = dirty.renameTo(clean);
                    long oldLength = entry.lengths[i2];
                    long newLength = clean.length();
                    entry.lengths[i2] = newLength;
                    this.size = (this.size - oldLength) + newLength;
                }
            }
            this.redundantOpCount++;
            Editor access$702 = Entry.access$702(entry, (Editor) null);
            if (entry.readable || success) {
                boolean access$602 = Entry.access$602(entry, true);
                Writer append = this.journalWriter.append(CLEAN);
                Writer append2 = this.journalWriter.append(' ');
                Writer append3 = this.journalWriter.append(entry.key);
                Writer append4 = this.journalWriter.append(entry.getLengths());
                Writer append5 = this.journalWriter.append(10);
                if (success) {
                    long j = this.nextSequenceNumber;
                    this.nextSequenceNumber = j + 1;
                    long access$1202 = Entry.access$1202(entry, j);
                }
            } else {
                Object remove = this.lruEntries.remove(entry.key);
                Writer append6 = this.journalWriter.append(REMOVE);
                Writer append7 = this.journalWriter.append(' ');
                Writer append8 = this.journalWriter.append(entry.key);
                Writer append9 = this.journalWriter.append(10);
            }
            this.journalWriter.flush();
            if (this.size > this.maxSize || journalRebuildRequired()) {
                Future submit = this.executorService.submit(this.cleanupCallable);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    public synchronized boolean remove(String str) throws IOException {
        boolean z;
        Throwable th;
        StringBuilder sb;
        String key = str;
        synchronized (this) {
            checkNotClosed();
            Entry entry = this.lruEntries.get(key);
            if (entry == null || entry.currentEditor != null) {
                z = false;
            } else {
                int i = 0;
                while (i < this.valueCount) {
                    File file = entry.getCleanFile(i);
                    if (!file.exists() || file.delete()) {
                        this.size -= entry.lengths[i];
                        entry.lengths[i] = 0;
                        i++;
                    } else {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IOException(sb.append("failed to delete ").append(file).toString());
                        throw th2;
                    }
                }
                this.redundantOpCount++;
                Writer append = this.journalWriter.append(REMOVE);
                Writer append2 = this.journalWriter.append(' ');
                Writer append3 = this.journalWriter.append(key);
                Writer append4 = this.journalWriter.append(10);
                Object remove = this.lruEntries.remove(key);
                if (journalRebuildRequired()) {
                    Future submit = this.executorService.submit(this.cleanupCallable);
                }
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.journalWriter == null;
        }
        return z;
    }

    private void checkNotClosed() {
        Throwable th;
        if (this.journalWriter == null) {
            Throwable th2 = th;
            new IllegalStateException("cache is closed");
            throw th2;
        }
    }

    public synchronized void flush() throws IOException {
        synchronized (this) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    public synchronized void close() throws IOException {
        ArrayList arrayList;
        synchronized (this) {
            if (this.journalWriter != null) {
                new ArrayList(this.lruEntries.values());
                Iterator i$ = arrayList.iterator();
                while (i$.hasNext()) {
                    Entry entry = (Entry) i$.next();
                    if (entry.currentEditor != null) {
                        entry.currentEditor.abort();
                    }
                }
                trimToSize();
                this.journalWriter.close();
                this.journalWriter = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            boolean remove = remove((String) this.lruEntries.entrySet().iterator().next().getKey());
        }
    }

    public void delete() throws IOException {
        close();
        Util.deleteContents(this.directory);
    }

    /* access modifiers changed from: private */
    public static String inputStreamToString(InputStream in) throws IOException {
        Reader reader;
        new InputStreamReader(in, Util.UTF_8);
        return Util.readFully(reader);
    }

    public final class Value {
        private final File[] files;
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        final /* synthetic */ DiskLruCache this$0;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Value(DiskLruCache x0, String x1, long x2, File[] x3, long[] x4, C15081 r24) {
            this(x0, x1, x2, x3, x4);
            C15081 r8 = r24;
        }

        private Value(DiskLruCache diskLruCache, String key2, long sequenceNumber2, File[] files2, long[] lengths2) {
            this.this$0 = diskLruCache;
            this.key = key2;
            this.sequenceNumber = sequenceNumber2;
            this.files = files2;
            this.lengths = lengths2;
        }

        public Editor edit() throws IOException {
            return this.this$0.edit(this.key, this.sequenceNumber);
        }

        public File getFile(int index) {
            return this.files[index];
        }

        public String getString(int index) throws IOException {
            InputStream is;
            new FileInputStream(this.files[index]);
            return DiskLruCache.inputStreamToString(is);
        }

        public long getLength(int index) {
            return this.lengths[index];
        }
    }

    public final class Editor {
        private boolean committed;
        /* access modifiers changed from: private */
        public final Entry entry;
        final /* synthetic */ DiskLruCache this$0;
        /* access modifiers changed from: private */
        public final boolean[] written;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Editor(DiskLruCache x0, Entry x1, C15081 r10) {
            this(x0, x1);
            C15081 r3 = r10;
        }

        private Editor(DiskLruCache diskLruCache, Entry entry2) {
            DiskLruCache diskLruCache2 = diskLruCache;
            Entry entry3 = entry2;
            this.this$0 = diskLruCache2;
            this.entry = entry3;
            this.written = entry3.readable ? null : new boolean[diskLruCache2.valueCount];
        }

        private InputStream newInputStream(int i) throws IOException {
            InputStream inputStream;
            Throwable th;
            int index = i;
            DiskLruCache diskLruCache = this.this$0;
            DiskLruCache diskLruCache2 = diskLruCache;
            synchronized (diskLruCache) {
                try {
                    if (this.entry.currentEditor != this) {
                        Throwable th2 = th;
                        new IllegalStateException();
                        throw th2;
                    } else if (!this.entry.readable) {
                        return null;
                    } else {
                        InputStream inputStream2 = inputStream;
                        new FileInputStream(this.entry.getCleanFile(index));
                        return inputStream2;
                    }
                } catch (FileNotFoundException e) {
                    FileNotFoundException fileNotFoundException = e;
                    DiskLruCache diskLruCache3 = diskLruCache2;
                    return null;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    DiskLruCache diskLruCache4 = diskLruCache2;
                    throw th4;
                }
            }
        }

        public String getString(int index) throws IOException {
            InputStream in = newInputStream(index);
            return in != null ? DiskLruCache.inputStreamToString(in) : null;
        }

        /* JADX INFO: finally extract failed */
        public File getFile(int i) throws IOException {
            Throwable th;
            int index = i;
            DiskLruCache diskLruCache = this.this$0;
            DiskLruCache diskLruCache2 = diskLruCache;
            synchronized (diskLruCache) {
                try {
                    if (this.entry.currentEditor != this) {
                        Throwable th2 = th;
                        new IllegalStateException();
                        throw th2;
                    }
                    if (!this.entry.readable) {
                        this.written[index] = true;
                    }
                    File dirtyFile = this.entry.getDirtyFile(index);
                    if (!this.this$0.directory.exists()) {
                        boolean mkdirs = this.this$0.directory.mkdirs();
                    }
                    File file = dirtyFile;
                    return file;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    DiskLruCache diskLruCache3 = diskLruCache2;
                    throw th4;
                }
            }
        }

        /* JADX WARNING: type inference failed for: r6v8, types: [java.io.Writer] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void set(int r12, java.lang.String r13) throws java.io.IOException {
            /*
                r11 = this;
                r0 = r11
                r1 = r12
                r2 = r13
                r6 = 0
                r3 = r6
                java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x002a }
                r10 = r6
                r6 = r10
                r7 = r10
                r8 = r0
                r9 = r1
                java.io.File r8 = r8.getFile(r9)     // Catch:{ all -> 0x002a }
                r7.<init>(r8)     // Catch:{ all -> 0x002a }
                r4 = r6
                java.io.OutputStreamWriter r6 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x002a }
                r10 = r6
                r6 = r10
                r7 = r10
                r8 = r4
                java.nio.charset.Charset r9 = com.bumptech.glide.disklrucache.Util.UTF_8     // Catch:{ all -> 0x002a }
                r7.<init>(r8, r9)     // Catch:{ all -> 0x002a }
                r3 = r6
                r6 = r3
                r7 = r2
                r6.write(r7)     // Catch:{ all -> 0x002a }
                r6 = r3
                com.bumptech.glide.disklrucache.Util.closeQuietly(r6)
                return
            L_0x002a:
                r6 = move-exception
                r5 = r6
                r6 = r3
                com.bumptech.glide.disklrucache.Util.closeQuietly(r6)
                r6 = r5
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.disklrucache.DiskLruCache.Editor.set(int, java.lang.String):void");
        }

        public void commit() throws IOException {
            this.this$0.completeEdit(this, true);
            this.committed = true;
        }

        public void abort() throws IOException {
            this.this$0.completeEdit(this, false);
        }

        public void abortUnlessCommitted() {
            if (!this.committed) {
                try {
                    abort();
                } catch (IOException e) {
                    IOException iOException = e;
                }
            }
        }
    }

    private final class Entry {
        File[] cleanFiles;
        /* access modifiers changed from: private */
        public Editor currentEditor;
        File[] dirtyFiles;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final long[] lengths;
        /* access modifiers changed from: private */
        public boolean readable;
        /* access modifiers changed from: private */
        public long sequenceNumber;
        final /* synthetic */ DiskLruCache this$0;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Entry(DiskLruCache x0, String x1, C15081 r10) {
            this(x0, x1);
            C15081 r3 = r10;
        }

        static /* synthetic */ long access$1202(Entry x0, long x1) {
            long j = x1;
            long j2 = j;
            x0.sequenceNumber = j2;
            return j;
        }

        static /* synthetic */ boolean access$602(Entry x0, boolean x1) {
            boolean z = x1;
            boolean z2 = z;
            x0.readable = z2;
            return z;
        }

        static /* synthetic */ Editor access$702(Entry x0, Editor x1) {
            Editor editor = x1;
            Editor editor2 = editor;
            x0.currentEditor = editor2;
            return editor;
        }

        private Entry(DiskLruCache diskLruCache, String str) {
            StringBuilder sb;
            File file;
            File file2;
            DiskLruCache diskLruCache2 = diskLruCache;
            String key2 = str;
            this.this$0 = diskLruCache2;
            this.key = key2;
            this.lengths = new long[diskLruCache2.valueCount];
            this.cleanFiles = new File[diskLruCache2.valueCount];
            this.dirtyFiles = new File[diskLruCache2.valueCount];
            new StringBuilder(key2);
            StringBuilder fileBuilder = sb.append('.');
            int truncateTo = fileBuilder.length();
            for (int i = 0; i < diskLruCache2.valueCount; i++) {
                StringBuilder append = fileBuilder.append(i);
                new File(diskLruCache2.directory, fileBuilder.toString());
                this.cleanFiles[i] = file;
                StringBuilder append2 = fileBuilder.append(".tmp");
                new File(diskLruCache2.directory, fileBuilder.toString());
                this.dirtyFiles[i] = file2;
                fileBuilder.setLength(truncateTo);
            }
        }

        public String getLengths() throws IOException {
            StringBuilder sb;
            new StringBuilder();
            StringBuilder result = sb;
            long[] arr$ = this.lengths;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                StringBuilder append = result.append(' ').append(arr$[i$]);
            }
            return result.toString();
        }

        /* access modifiers changed from: private */
        public void setLengths(String[] strArr) throws IOException {
            String[] strings = strArr;
            if (strings.length != this.this$0.valueCount) {
                throw invalidLengths(strings);
            }
            int i = 0;
            while (i < strings.length) {
                try {
                    this.lengths[i] = Long.parseLong(strings[i]);
                    i++;
                } catch (NumberFormatException e) {
                    NumberFormatException numberFormatException = e;
                    throw invalidLengths(strings);
                }
            }
        }

        private IOException invalidLengths(String[] strings) throws IOException {
            Throwable th;
            StringBuilder sb;
            Throwable th2 = th;
            new StringBuilder();
            new IOException(sb.append("unexpected journal line: ").append(Arrays.toString(strings)).toString());
            throw th2;
        }

        public File getCleanFile(int i) {
            return this.cleanFiles[i];
        }

        public File getDirtyFile(int i) {
            return this.dirtyFiles[i];
        }
    }
}
