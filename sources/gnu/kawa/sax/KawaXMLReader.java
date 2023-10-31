package gnu.kawa.sax;

import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.xml.XMLFilter;
import gnu.xml.XMLParser;
import java.io.IOException;
import java.io.Reader;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class KawaXMLReader extends ContentConsumer implements XMLReader {
    ErrorHandler errorHandler;

    public KawaXMLReader() {
    }

    public boolean getFeature(String str) {
        String str2 = str;
        return false;
    }

    public void setFeature(String name, boolean value) {
    }

    public Object getProperty(String str) {
        String str2 = str;
        return null;
    }

    public void setProperty(String name, Object value) {
    }

    public void setEntityResolver(EntityResolver resolver) {
    }

    public EntityResolver getEntityResolver() {
        return null;
    }

    public void setDTDHandler(DTDHandler handler) {
    }

    public DTDHandler getDTDHandler() {
        return null;
    }

    public void setErrorHandler(ErrorHandler handler) {
        ErrorHandler errorHandler2 = handler;
        this.errorHandler = errorHandler2;
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public void parse(InputSource inputSource) throws IOException, SAXException {
        SourceMessages sourceMessages;
        XMLFilter xMLFilter;
        LineBufferedReader lineBufferedReader;
        Throwable th;
        InputSource input = inputSource;
        Reader reader = input.getCharacterStream();
        if (reader == null) {
            reader = XMLParser.XMLStreamReader(input.getByteStream());
        }
        new SourceMessages();
        SourceMessages messages = sourceMessages;
        new XMLFilter(this);
        XMLFilter filter = xMLFilter;
        new LineBufferedReader(reader);
        LineBufferedReader lin = lineBufferedReader;
        filter.setSourceLocator(lin);
        getContentHandler().setDocumentLocator(filter);
        XMLParser.parse(lin, messages, filter);
        String err = messages.toString(20);
        if (err != null) {
            Throwable th2 = th;
            new SAXParseException(err, filter);
            throw th2;
        }
    }

    public void parse(String systemId) {
    }
}
