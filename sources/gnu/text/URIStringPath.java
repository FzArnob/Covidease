package gnu.text;

import java.net.URI;

/* compiled from: URIPath */
class URIStringPath extends URIPath {
    String uriString;

    public String toURIString() {
        return this.uriString;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public URIStringPath(URI uri, String uriString2) {
        super(uri);
        this.uriString = uriString2;
    }
}
