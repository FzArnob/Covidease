package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.Location;
import gnu.mapping.ThreadLocation;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xml.NodeTree;
import gnu.xml.XMLParser;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Hashtable;

public class Document {
    private static HashMap cache;
    private static ThreadLocation docMapLocation;
    public static final Document document;

    public Document() {
    }

    static {
        Document document2;
        ThreadLocation threadLocation;
        HashMap hashMap;
        new Document();
        document = document2;
        new ThreadLocation("document-map");
        docMapLocation = threadLocation;
        new HashMap();
        cache = hashMap;
    }

    public static void parse(Object obj, Consumer consumer) throws Throwable {
        SourceMessages sourceMessages;
        Throwable th;
        Object name = obj;
        Consumer out = consumer;
        new SourceMessages();
        SourceMessages messages = sourceMessages;
        if (out instanceof XConsumer) {
            ((XConsumer) out).beginEntity(name);
        }
        XMLParser.parse(name, messages, out);
        if (messages.seenErrors()) {
            Throwable th2 = th;
            new SyntaxException("document function read invalid XML", messages);
            throw th2;
        } else if (out instanceof XConsumer) {
            ((XConsumer) out).endEntity();
        }
    }

    public static KDocument parse(Object uri) throws Throwable {
        NodeTree nodeTree;
        KDocument kDocument;
        new NodeTree();
        NodeTree tree = nodeTree;
        parse(uri, tree);
        new KDocument(tree, 10);
        return kDocument;
    }

    private static class DocReference extends SoftReference {
        static ReferenceQueue queue;
        Path key;

        static {
            ReferenceQueue referenceQueue;
            new ReferenceQueue();
            queue = referenceQueue;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DocReference(Path key2, KDocument doc) {
            super(doc, queue);
            this.key = key2;
        }
    }

    public static void clearLocalCache() {
        docMapLocation.getLocation().set((Object) null);
    }

    public static void clearSoftCache() {
        HashMap hashMap;
        new HashMap();
        cache = hashMap;
    }

    public static KDocument parseCached(Object uri) throws Throwable {
        return parseCached(Path.valueOf(uri));
    }

    public static synchronized KDocument parseCached(Path path) throws Throwable {
        Object obj;
        KDocument kDocument;
        Hashtable hashtable;
        Path uri = path;
        synchronized (Document.class) {
            while (true) {
                DocReference oldref = (DocReference) DocReference.queue.poll();
                if (oldref == null) {
                    Location loc = docMapLocation.getLocation();
                    Hashtable map = (Hashtable) loc.get((Object) null);
                    if (map == null) {
                        new Hashtable();
                        map = hashtable;
                        loc.set(map);
                    }
                    KDocument doc = (KDocument) map.get(uri);
                    if (doc != null) {
                        kDocument = doc;
                    } else {
                        DocReference ref = (DocReference) cache.get(uri);
                        if (ref != null) {
                            KDocument doc2 = (KDocument) ref.get();
                            if (doc2 == null) {
                                Object remove = cache.remove(uri);
                            } else {
                                Object put = map.put(uri, doc2);
                                kDocument = doc2;
                            }
                        }
                        KDocument doc3 = parse(uri);
                        Object put2 = map.put(uri, doc3);
                        new DocReference(uri, doc3);
                        Object put3 = cache.put(uri, obj);
                        kDocument = doc3;
                    }
                } else {
                    Object remove2 = cache.remove(oldref.key);
                }
            }
        }
        return kDocument;
    }
}
