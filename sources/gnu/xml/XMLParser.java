package gnu.xml;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.LineInputStreamReader;
import gnu.text.Path;
import gnu.text.SourceMessages;
import java.io.IOException;
import java.io.InputStream;

public class XMLParser {
    private static final int ATTRIBUTE_SEEN_EQ_STATE = 11;
    private static final int ATTRIBUTE_SEEN_NAME_STATE = 8;
    static final String BAD_ENCODING_SYNTAX = "bad 'encoding' declaration";
    static final String BAD_STANDALONE_SYNTAX = "bad 'standalone' declaration";
    private static final int BEGIN_ELEMENT_STATE = 2;
    private static final int DOCTYPE_NAME_SEEN_STATE = 16;
    private static final int DOCTYPE_SEEN_STATE = 13;
    private static final int END_ELEMENT_STATE = 4;
    private static final int EXPECT_NAME_MODIFIER = 1;
    private static final int EXPECT_RIGHT_STATE = 27;
    private static final int INIT_LEFT_QUEST_STATE = 30;
    private static final int INIT_LEFT_STATE = 34;
    private static final int INIT_STATE = 0;
    private static final int INIT_TEXT_STATE = 31;
    private static final int INVALID_VERSION_DECL = 35;
    private static final int MAYBE_ATTRIBUTE_STATE = 10;
    private static final int PREV_WAS_CR_STATE = 28;
    private static final int SAW_AMP_SHARP_STATE = 26;
    private static final int SAW_AMP_STATE = 25;
    private static final int SAW_ENTITY_REF = 6;
    private static final int SAW_EOF_ERROR = 37;
    private static final int SAW_ERROR = 36;
    private static final int SAW_LEFT_EXCL_MINUS_STATE = 22;
    private static final int SAW_LEFT_EXCL_STATE = 20;
    private static final int SAW_LEFT_QUEST_STATE = 21;
    private static final int SAW_LEFT_SLASH_STATE = 19;
    private static final int SAW_LEFT_STATE = 14;
    private static final int SKIP_SPACES_MODIFIER = 2;
    private static final int TEXT_STATE = 1;

    public XMLParser() {
    }

    public static void parse(Object obj, SourceMessages messages, Consumer out) throws IOException {
        Object uri = obj;
        parse(Path.openInputStream(uri), uri, messages, out);
    }

    public static LineInputStreamReader XMLStreamReader(InputStream strm) throws IOException {
        LineInputStreamReader lineInputStreamReader;
        Throwable th;
        new LineInputStreamReader(strm);
        LineInputStreamReader in = lineInputStreamReader;
        int b1 = in.getByte();
        int b2 = b1 < 0 ? -1 : in.getByte();
        int b3 = b2 < 0 ? -1 : in.getByte();
        if (b1 == 239 && b2 == 187 && b3 == 191) {
            in.resetStart(3);
            in.setCharset("UTF-8");
        } else if (b1 == 255 && b2 == 254 && b3 != 0) {
            in.resetStart(2);
            in.setCharset("UTF-16LE");
        } else if (b1 == 254 && b2 == 255 && b3 != 0) {
            in.resetStart(2);
            in.setCharset("UTF-16BE");
        } else {
            int b4 = b3 < 0 ? -1 : in.getByte();
            if (b1 == 76 && b2 == 111 && b3 == 167 && b4 == 148) {
                Throwable th2 = th;
                new RuntimeException("XMLParser: EBCDIC encodings not supported");
                throw th2;
            }
            in.resetStart(0);
            if ((b1 == 60 && ((b2 == 63 && b3 == 120 && b4 == 109) || (b2 == 0 && b3 == 63 && b4 == 0))) || (b1 == 0 && b2 == 60 && b3 == 0 && b4 == 63)) {
                char[] buffer = in.buffer;
                if (buffer == null) {
                    char[] cArr = new char[8192];
                    buffer = cArr;
                    in.buffer = cArr;
                }
                int pos = 0;
                int quote = 0;
                while (true) {
                    int b = in.getByte();
                    if (b != 0) {
                        if (b < 0) {
                            break;
                        }
                        int i = pos;
                        pos++;
                        buffer[i] = (char) (b & 255);
                        if (quote == 0) {
                            if (b == 62) {
                                break;
                            } else if (b == 39 || b == 34) {
                                quote = b;
                            }
                        } else if (b == quote) {
                            quote = 0;
                        }
                    }
                }
                in.pos = 0;
                in.limit = pos;
            } else {
                in.setCharset("UTF-8");
            }
        }
        in.setKeepFullLines(false);
        return in;
    }

    public static void parse(InputStream strm, Object obj, SourceMessages sourceMessages, Consumer consumer) throws IOException {
        Object uri = obj;
        SourceMessages messages = sourceMessages;
        Consumer out = consumer;
        LineInputStreamReader in = XMLStreamReader(strm);
        if (uri != null) {
            in.setName(uri);
        }
        parse((LineBufferedReader) in, messages, out);
        in.close();
    }

    public static void parse(LineBufferedReader lineBufferedReader, SourceMessages messages, Consumer out) throws IOException {
        XMLFilter xMLFilter;
        LineBufferedReader in = lineBufferedReader;
        new XMLFilter(out);
        XMLFilter filter = xMLFilter;
        filter.setMessages(messages);
        filter.setSourceLocator(in);
        filter.startDocument();
        Path uri = in.getPath();
        if (uri != null) {
            filter.writeDocumentUri(uri);
        }
        parse(in, filter);
        filter.endDocument();
    }

    public static void parse(LineBufferedReader lineBufferedReader, SourceMessages messages, XMLFilter xMLFilter) throws IOException {
        LineBufferedReader in = lineBufferedReader;
        XMLFilter filter = xMLFilter;
        filter.setMessages(messages);
        filter.setSourceLocator(in);
        filter.startDocument();
        Path uri = in.getPath();
        if (uri != null) {
            filter.writeDocumentUri(uri);
        }
        parse(in, filter);
        filter.endDocument();
        in.close();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v14, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v177, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v178, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v180, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v181, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v183, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v184, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v186, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v187, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v189, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v190, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v192, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v193, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v195, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v196, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v198, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v199, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v201, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v202, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v204, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v206, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v269, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v270, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v103, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v209, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v20, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v276, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v211, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v212, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v105, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v277, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v213, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v214, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v106, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v215, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v217, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v218, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v284, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v285, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v286, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v109, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v222, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v223, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v225, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v226, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v228, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v229, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v299, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v114, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v233, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v304, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v115, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v235, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v238, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v239, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v241, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v242, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v244, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v245, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v247, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v248, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v250, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v251, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v253, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v254, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v256, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v257, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v259, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v261, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v336, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v23, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v337, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v126, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v264, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v347, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v348, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v349, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v130, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v269, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v358, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v132, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v273, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v363, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v133, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v275, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v276, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v26, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v289, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v291, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v27, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v422, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v150, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v423, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v151, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v424, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v425, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v426, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v427, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v311, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v430, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v312, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v431, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v432, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v433, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v314, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v152, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v437, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v153, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v317, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v28, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v440, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v58, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v29, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v30, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v326, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v152, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v331, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v31, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v32, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v513, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v375, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v514, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v376, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v377, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v201, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v516, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v78, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v519, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v520, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v521, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v80, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v33, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v526, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v204, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v385, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v34, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v529, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v386, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v530, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v387, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v205, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v532, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v533, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v206, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v389, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v81, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v542, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v543, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v544, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v545, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v546, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v82, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v548, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v549, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v36, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v554, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v209, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v37, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v38, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v597, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v39, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v40, resolved type: char} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0448, code lost:
        if (r11 != 0) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0452, code lost:
        if (r7 != 8) goto L_0x045f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0454, code lost:
        r19 = "missing or invalid attribute name";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0459, code lost:
        r13 = r19;
        r7 = 36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0467, code lost:
        if (r7 == 2) goto L_0x0473;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0471, code lost:
        if (r7 != 4) goto L_0x0479;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0473, code lost:
        r19 = "missing or invalid element name";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0479, code lost:
        r19 = "missing or invalid name";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0487, code lost:
        if (r10 != 'x') goto L_0x04d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x048b, code lost:
        if (r12 != 0) goto L_0x04d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x048d, code lost:
        r12 = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0499, code lost:
        if (r5 >= r6) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x04ad, code lost:
        if (r10 != ';') goto L_0x047f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x04af, code lost:
        r2.pos = r5;
        r3.emitCharacterReference(r11, r4, r14, (r5 - 1) - r14);
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x04dc, code lost:
        if (r11 < 134217728) goto L_0x04f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x04de, code lost:
        r2.pos = r5;
        r3.error('e', "invalid character reference");
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x04fa, code lost:
        if (r12 != 0) goto L_0x050f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x04fc, code lost:
        r19 = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x04fe, code lost:
        r15 = r19;
        r16 = java.lang.Character.digit(r10, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x050c, code lost:
        if (r16 >= 0) goto L_0x0512;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x050f, code lost:
        r19 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0512, code lost:
        r11 = (r11 * r15) + r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x07f1, code lost:
        if (r26 != '0') goto L_0x07f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x0c37, code lost:
        if (r5 >= r6) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:426:0x0c4b, code lost:
        if (r10 != '>') goto L_0x0d99;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:427:0x0c4d, code lost:
        r11 = (r5 - 1) - r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:428:0x0c61, code lost:
        if (r11 < 4) goto L_0x0cce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:430:0x0c6f, code lost:
        if (r4[r14] != '-') goto L_0x0cce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:432:0x0c81, code lost:
        if (r4[r14 + 1] != '-') goto L_0x0cce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:434:0x0c93, code lost:
        if (r4[r5 - 2] != '-') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:436:0x0ca5, code lost:
        if (r4[r5 - 3] != '-') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:437:0x0ca7, code lost:
        r2.pos = r5;
        r3.commentFromParser(r4, r14 + 2, r11 - 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:438:0x0cc4, code lost:
        r14 = r6;
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:440:0x0cd6, code lost:
        if (r11 < 6) goto L_0x0cc4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:442:0x0ce4, code lost:
        if (r4[r14] != '[') goto L_0x0cc4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:444:0x0cf6, code lost:
        if (r4[r14 + 1] != 'C') goto L_0x0cc4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:446:0x0d08, code lost:
        if (r4[r14 + 2] != 'D') goto L_0x0cc4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:448:0x0d1a, code lost:
        if (r4[r14 + 3] != 'A') goto L_0x0cc4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:450:0x0d2c, code lost:
        if (r4[r14 + 4] != 'T') goto L_0x0cc4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:452:0x0d3e, code lost:
        if (r4[r14 + 5] != 'A') goto L_0x0cc4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:454:0x0d50, code lost:
        if (r4[r14 + 6] != '[') goto L_0x0cc4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:456:0x0d62, code lost:
        if (r4[r5 - 2] != ']') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:458:0x0d74, code lost:
        if (r4[r5 - 3] != ']') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:459:0x0d76, code lost:
        r2.pos = r5;
        r3.writeCDATA(r4, r14 + 7, (r5 - 10) - r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:461:0x0da5, code lost:
        if (r5 != (r14 + 7)) goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:463:0x0db3, code lost:
        if (r4[r14] != 'D') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:465:0x0dc5, code lost:
        if (r4[r14 + 1] != 'O') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:467:0x0dd7, code lost:
        if (r4[r14 + 2] != 'C') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:469:0x0de9, code lost:
        if (r4[r14 + 3] != 'T') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:471:0x0dfb, code lost:
        if (r4[r14 + 4] != 'Y') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:473:0x0e0d, code lost:
        if (r4[r14 + 5] != 'P') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:475:0x0e17, code lost:
        if (r10 != 'E') goto L_0x0c2f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:476:0x0e19, code lost:
        r14 = r6;
        r7 = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:591:0x0042, code lost:
        continue;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:554:0x109d  */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void parse(gnu.text.LineBufferedReader r27, gnu.xml.XMLFilter r28) {
        /*
            r2 = r27
            r3 = r28
            r19 = r2
            r0 = r19
            char[] r0 = r0.buffer
            r19 = r0
            r4 = r19
            r19 = r2
            r0 = r19
            int r0 = r0.pos
            r19 = r0
            r5 = r19
            r19 = r2
            r0 = r19
            int r0 = r0.limit
            r19 = r0
            r6 = r19
            r19 = 0
            r7 = r19
            r19 = 60
            r8 = r19
            r19 = 14
            r9 = r19
            r19 = 32
            r10 = r19
            r19 = 0
            r11 = r19
            r19 = -1
            r12 = r19
            r19 = 0
            r13 = r19
            r19 = r6
            r14 = r19
        L_0x0042:
            r19 = r7
            switch(r19) {
                case 0: goto L_0x005c;
                case 1: goto L_0x00e7;
                case 2: goto L_0x05c3;
                case 3: goto L_0x02e5;
                case 4: goto L_0x1063;
                case 5: goto L_0x02e5;
                case 6: goto L_0x054a;
                case 7: goto L_0x02e5;
                case 8: goto L_0x0f6f;
                case 9: goto L_0x02e5;
                case 10: goto L_0x0f10;
                case 11: goto L_0x0fea;
                case 12: goto L_0x0298;
                case 13: goto L_0x0e23;
                case 14: goto L_0x057d;
                case 15: goto L_0x0298;
                case 16: goto L_0x0e31;
                case 17: goto L_0x02e5;
                case 18: goto L_0x0047;
                case 19: goto L_0x1055;
                case 20: goto L_0x0c43;
                case 21: goto L_0x05e6;
                case 22: goto L_0x0047;
                case 23: goto L_0x0298;
                case 24: goto L_0x02e5;
                case 25: goto L_0x0520;
                case 26: goto L_0x04a5;
                case 27: goto L_0x1082;
                case 28: goto L_0x0258;
                case 29: goto L_0x0298;
                case 30: goto L_0x05e6;
                case 31: goto L_0x0065;
                case 32: goto L_0x0298;
                case 33: goto L_0x02e5;
                case 34: goto L_0x0079;
                case 35: goto L_0x0091;
                case 36: goto L_0x009a;
                case 37: goto L_0x00d2;
                default: goto L_0x0047;
            }
        L_0x0047:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x109d
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
            goto L_0x0042
        L_0x005c:
            r19 = 1
            r7 = r19
            r19 = 31
            r7 = r19
            goto L_0x0047
        L_0x0065:
            r19 = r10
            r20 = 60
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0074
            r19 = 34
            r7 = r19
            goto L_0x0047
        L_0x0074:
            r19 = 1
            r7 = r19
            goto L_0x0042
        L_0x0079:
            r19 = r10
            r20 = 63
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x008c
            r19 = r5
            r14 = r19
            r19 = 33
            r7 = r19
            goto L_0x0047
        L_0x008c:
            r19 = 14
            r7 = r19
            goto L_0x0042
        L_0x0091:
            r19 = r12
            r5 = r19
            java.lang.String r19 = "invalid xml version specifier"
            r13 = r19
        L_0x009a:
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = 101(0x65, float:1.42E-43)
            r21 = r13
            r19.error(r20, r21)
        L_0x00ad:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x00b8
        L_0x00b7:
            return
        L_0x00b8:
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
            r19 = r10
            r20 = 62
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x00ad
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x00d2:
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = 102(0x66, float:1.43E-43)
            java.lang.String r21 = "unexpected end-of-file"
            r19.error(r20, r21)
            goto L_0x00b7
        L_0x00e7:
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r14 = r19
            r19 = r5
            r11 = r19
        L_0x00f3:
            r19 = r10
            r20 = r8
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x012d
            r19 = r9
            r7 = r19
        L_0x0101:
            r19 = r5
            r20 = r11
            int r19 = r19 - r20
            r11 = r19
            r19 = r11
            if (r19 <= 0) goto L_0x0122
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r19.textFromParser(r20, r21, r22)
        L_0x0122:
            r19 = r4
            r0 = r19
            int r0 = r0.length
            r19 = r0
            r14 = r19
            goto L_0x0047
        L_0x012d:
            r19 = r10
            r20 = 38
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x013c
            r19 = 25
            r7 = r19
            goto L_0x0101
        L_0x013c:
            r19 = r10
            r20 = 13
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x01e2
            r19 = r5
            r20 = r11
            int r19 = r19 - r20
            r11 = r19
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r11
            if (r19 <= 0) goto L_0x0167
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r19.textFromParser(r20, r21, r22)
        L_0x0167:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x01d7
            r19 = r4
            r20 = r5
            char r19 = r19[r20]
            r10 = r19
            r19 = r10
            r20 = 10
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x01a4
            r19 = r5
            r14 = r19
            int r5 = r5 + 1
            r19 = r5
            r11 = r19
        L_0x018d:
            r19 = r2
            r20 = 1
            r21 = r5
            r19.incrLineNumber(r20, r21)
        L_0x0196:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x024c
            int r11 = r11 + -1
            goto L_0x0101
        L_0x01a4:
            r19 = r3
            r19.linefeedFromParser()
            r19 = r10
            r20 = 133(0x85, float:1.86E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x01c2
            r19 = r5
            int r5 = r5 + 1
            r14 = r19
            r19 = r5
            r20 = 1
            int r19 = r19 + 1
            r11 = r19
            goto L_0x018d
        L_0x01c2:
            r19 = r2
            r20 = 1
            r21 = r5
            r19.incrLineNumber(r20, r21)
            r19 = r5
            r14 = r19
            int r5 = r5 + 1
            r19 = r5
            r11 = r19
            goto L_0x00f3
        L_0x01d7:
            r19 = r3
            r19.linefeedFromParser()
            r19 = 28
            r7 = r19
            goto L_0x0047
        L_0x01e2:
            r19 = r10
            r20 = 133(0x85, float:1.86E-43)
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x01f6
            r19 = r10
            r20 = 8232(0x2028, float:1.1535E-41)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0237
        L_0x01f6:
            r19 = r5
            r20 = r11
            int r19 = r19 - r20
            r11 = r19
            r19 = r2
            r20 = r5
            r21 = 1
            int r20 = r20 + -1
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r11
            if (r19 <= 0) goto L_0x021b
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r19.textFromParser(r20, r21, r22)
        L_0x021b:
            r19 = r3
            r19.linefeedFromParser()
            r19 = r2
            r20 = 1
            r21 = r5
            r19.incrLineNumber(r20, r21)
            r19 = r5
            r20 = 1
            int r19 = r19 + 1
            r11 = r19
            r19 = r5
            r14 = r19
            goto L_0x0196
        L_0x0237:
            r19 = r10
            r20 = 10
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0196
            r19 = r2
            r20 = 1
            r21 = r5
            r19.incrLineNumber(r20, r21)
            goto L_0x0196
        L_0x024c:
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
            goto L_0x00f3
        L_0x0258:
            r19 = 1
            r7 = r19
            r19 = r10
            r20 = 10
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0283
            r19 = 1
        L_0x0268:
            r20 = r10
            r21 = 133(0x85, float:1.86E-43)
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x0286
            r20 = 1
        L_0x0274:
            r19 = r19 | r20
            if (r19 == 0) goto L_0x0289
            r19 = r2
            r20 = 1
            r21 = r5
            r19.incrLineNumber(r20, r21)
            goto L_0x0047
        L_0x0283:
            r19 = 0
            goto L_0x0268
        L_0x0286:
            r20 = 0
            goto L_0x0274
        L_0x0289:
            r19 = r2
            r20 = 1
            r21 = r5
            r22 = 1
            int r21 = r21 + -1
            r19.incrLineNumber(r20, r21)
            goto L_0x0042
        L_0x0298:
            r19 = r10
            r20 = 32
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 9
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x02ae
            goto L_0x0047
        L_0x02ae:
            r19 = r10
            r20 = 10
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x02d6
            r19 = r10
            r20 = 13
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x02d6
            r19 = r10
            r20 = 133(0x85, float:1.86E-43)
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x02d6
            r19 = r10
            r20 = 8232(0x2028, float:1.1535E-41)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x02e1
        L_0x02d6:
            r19 = r2
            r20 = 1
            r21 = r5
            r19.incrLineNumber(r20, r21)
            goto L_0x0047
        L_0x02e1:
            int r7 = r7 + -2
            goto L_0x0042
        L_0x02e5:
            r19 = r14
            r20 = 1
            int r19 = r19 + 1
            r11 = r19
        L_0x02ed:
            r19 = r10
            r20 = 97
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x0301
            r19 = r10
            r20 = 122(0x7a, float:1.71E-43)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
        L_0x0301:
            r19 = r10
            r20 = 65
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x0315
            r19 = r10
            r20 = 90
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
        L_0x0315:
            r19 = r10
            r20 = 95
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0426
            r19 = r10
            r20 = 58
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0426
            r19 = r10
            r20 = 192(0xc0, float:2.69E-43)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x03c2
            r19 = r10
            r20 = 767(0x2ff, float:1.075E-42)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
            r19 = r10
            r20 = 880(0x370, float:1.233E-42)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x03c2
            r19 = r10
            r20 = 8191(0x1fff, float:1.1478E-41)
            r0 = r19
            r1 = r20
            if (r0 > r1) goto L_0x035b
            r19 = r10
            r20 = 894(0x37e, float:1.253E-42)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0426
        L_0x035b:
            r19 = r10
            r20 = 8204(0x200c, float:1.1496E-41)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x03c2
            r19 = r10
            r20 = 8205(0x200d, float:1.1498E-41)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
            r19 = r10
            r20 = 8304(0x2070, float:1.1636E-41)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x0383
            r19 = r10
            r20 = 8591(0x218f, float:1.2039E-41)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
        L_0x0383:
            r19 = r10
            r20 = 11264(0x2c00, float:1.5784E-41)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x0397
            r19 = r10
            r20 = 12271(0x2fef, float:1.7195E-41)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
        L_0x0397:
            r19 = r10
            r20 = 12289(0x3001, float:1.722E-41)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x03ac
            r19 = r10
            r20 = 55295(0xd7ff, float:7.7485E-41)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
        L_0x03ac:
            r19 = r10
            r20 = 63744(0xf900, float:8.9324E-41)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x03c2
            r19 = r10
            r20 = 65533(0xfffd, float:9.1831E-41)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
        L_0x03c2:
            r19 = r5
            r20 = r11
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x03e0
            r19 = r10
            r20 = 48
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x03e0
            r19 = r10
            r20 = 57
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
        L_0x03e0:
            r19 = r10
            r20 = 46
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0426
            r19 = r10
            r20 = 45
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0426
            r19 = r10
            r20 = 183(0xb7, float:2.56E-43)
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0426
            r19 = r10
            r20 = 768(0x300, float:1.076E-42)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x043c
            r19 = r10
            r20 = 879(0x36f, float:1.232E-42)
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0426
            r19 = r10
            r20 = 8255(0x203f, float:1.1568E-41)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x043c
            r19 = r10
            r20 = 8256(0x2040, float:1.1569E-41)
            r0 = r19
            r1 = r20
            if (r0 > r1) goto L_0x043c
        L_0x0426:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0047
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
            goto L_0x02ed
        L_0x043c:
            int r7 = r7 + -1
            r19 = r5
            r20 = r11
            int r19 = r19 - r20
            r11 = r19
            r19 = r11
            if (r19 != 0) goto L_0x0042
            r19 = r7
            r20 = 8
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x045f
            java.lang.String r19 = "missing or invalid attribute name"
            r13 = r19
        L_0x0459:
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x045f:
            r19 = r7
            r20 = 2
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0473
            r19 = r7
            r20 = 4
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0479
        L_0x0473:
            java.lang.String r19 = "missing or invalid element name"
            r13 = r19
            goto L_0x0459
        L_0x0479:
            java.lang.String r19 = "missing or invalid name"
            r13 = r19
            goto L_0x0459
        L_0x047f:
            r19 = r10
            r20 = 120(0x78, float:1.68E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x04d4
            r19 = r12
            if (r19 != 0) goto L_0x04d4
            r19 = 16
            r12 = r19
        L_0x0491:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0047
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
        L_0x04a5:
            r19 = r10
            r20 = 59
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x047f
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = r11
            r21 = r4
            r22 = r14
            r23 = r5
            r24 = 1
            int r23 = r23 + -1
            r24 = r14
            int r23 = r23 - r24
            r19.emitCharacterReference(r20, r21, r22, r23)
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x04d4:
            r19 = r11
            r20 = 134217728(0x8000000, float:3.85186E-34)
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x04f8
        L_0x04de:
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = 101(0x65, float:1.42E-43)
            java.lang.String r21 = "invalid character reference"
            r19.error(r20, r21)
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x04f8:
            r19 = r12
            if (r19 != 0) goto L_0x050f
            r19 = 10
        L_0x04fe:
            r15 = r19
            r19 = r10
            r20 = r15
            int r19 = java.lang.Character.digit(r19, r20)
            r16 = r19
            r19 = r16
            if (r19 >= 0) goto L_0x0512
            goto L_0x04de
        L_0x050f:
            r19 = r12
            goto L_0x04fe
        L_0x0512:
            r19 = r11
            r20 = r15
            int r19 = r19 * r20
            r20 = r16
            int r19 = r19 + r20
            r11 = r19
            goto L_0x0491
        L_0x0520:
            r19 = r10
            r20 = 35
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x053c
            r19 = 26
            r7 = r19
            r19 = r5
            r14 = r19
            r19 = 0
            r11 = r19
            r19 = 0
            r12 = r19
            goto L_0x0047
        L_0x053c:
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r14 = r19
            r19 = 7
            r7 = r19
            goto L_0x0042
        L_0x054a:
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r10
            r20 = 59
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0568
            r19 = r3
            r20 = 119(0x77, float:1.67E-43)
            java.lang.String r21 = "missing ';'"
            r19.error(r20, r21)
        L_0x0568:
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r19.emitEntityReference(r20, r21, r22)
            r19 = r6
            r14 = r19
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x057d:
            r19 = r10
            r20 = 47
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x058d
            r19 = 19
            r7 = r19
            goto L_0x0047
        L_0x058d:
            r19 = r10
            r20 = 63
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x05a1
            r19 = r5
            r14 = r19
            r19 = 24
            r7 = r19
            goto L_0x0047
        L_0x05a1:
            r19 = r10
            r20 = 33
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x05b5
            r19 = 20
            r7 = r19
            r19 = r5
            r14 = r19
            goto L_0x0047
        L_0x05b5:
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r14 = r19
            r19 = 3
            r7 = r19
            goto L_0x0042
        L_0x05c3:
            r19 = r2
            r20 = r5
            r21 = r11
            int r20 = r20 - r21
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r19.emitStartElement(r20, r21, r22)
            r19 = 12
            r7 = r19
            r19 = r6
            r14 = r19
            goto L_0x0042
        L_0x05e6:
            r19 = r12
            if (r19 >= 0) goto L_0x05f2
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r12 = r19
        L_0x05f2:
            r19 = r10
            r20 = 62
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c19
            r19 = r4
            r20 = r5
            r21 = 2
            int r20 = r20 + -2
            r26 = r20
            r20 = r26
            r21 = r26
            r15 = r21
            char r19 = r19[r20]
            r20 = 63
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c19
            r19 = r15
            r20 = r12
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x0c19
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r11
            r20 = 3
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c05
            r19 = r4
            r20 = r14
            char r19 = r19[r20]
            r20 = 120(0x78, float:1.68E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c05
            r19 = r4
            r20 = r14
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 109(0x6d, float:1.53E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c05
            r19 = r4
            r20 = r14
            r21 = 2
            int r20 = r20 + 2
            char r19 = r19[r20]
            r20 = 108(0x6c, float:1.51E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c05
            r19 = r7
            r20 = 30
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bfa
            r19 = r15
            r20 = r12
            r21 = 7
            int r20 = r20 + 7
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x06f8
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r20 = 118(0x76, float:1.65E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x06f8
            r19 = r4
            r20 = r12
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 101(0x65, float:1.42E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x06f8
            r19 = r4
            r20 = r12
            r21 = 2
            int r20 = r20 + 2
            char r19 = r19[r20]
            r20 = 114(0x72, float:1.6E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x06f8
            r19 = r4
            r20 = r12
            r21 = 3
            int r20 = r20 + 3
            char r19 = r19[r20]
            r20 = 115(0x73, float:1.61E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x06f8
            r19 = r4
            r20 = r12
            r21 = 4
            int r20 = r20 + 4
            char r19 = r19[r20]
            r20 = 105(0x69, float:1.47E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x06f8
            r19 = r4
            r20 = r12
            r21 = 5
            int r20 = r20 + 5
            char r19 = r19[r20]
            r20 = 111(0x6f, float:1.56E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x06f8
            r19 = r4
            r20 = r12
            r21 = 6
            int r20 = r20 + 6
            char r19 = r19[r20]
            r20 = 110(0x6e, float:1.54E-43)
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0707
        L_0x06f8:
            r19 = r12
            r5 = r19
            java.lang.String r19 = "xml declaration without version"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0707:
            int r12 = r12 + 7
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
        L_0x0711:
            r19 = r10
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x072e
            int r12 = r12 + 1
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x072e
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
            goto L_0x0711
        L_0x072e:
            r19 = r10
            r20 = 61
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x073e
            r19 = 35
            r7 = r19
            goto L_0x0042
        L_0x073e:
            r19 = r4
            int r12 = r12 + 1
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
        L_0x0748:
            r19 = r10
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x0765
            int r12 = r12 + 1
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0765
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
            goto L_0x0748
        L_0x0765:
            r19 = r10
            r20 = 39
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x077f
            r19 = r10
            r20 = 34
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x077f
            r19 = 35
            r7 = r19
            goto L_0x0042
        L_0x077f:
            r19 = r10
            r16 = r19
            int r12 = r12 + 1
            r19 = r12
            r17 = r19
        L_0x0789:
            r19 = r17
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0799
            r19 = 35
            r7 = r19
            goto L_0x0042
        L_0x0799:
            r19 = r4
            r20 = r17
            char r19 = r19[r20]
            r10 = r19
            r19 = r10
            r20 = r16
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x081e
            r19 = r17
            r20 = r12
            r21 = 3
            int r20 = r20 + 3
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x07f3
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r20 = 49
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x07f3
            r19 = r4
            r20 = r12
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 46
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x07f3
            r19 = r4
            r20 = r12
            r21 = 2
            int r20 = r20 + 2
            char r19 = r19[r20]
            r26 = r19
            r19 = r26
            r20 = r26
            r10 = r20
            r20 = 48
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x07fd
        L_0x07f3:
            r19 = r10
            r20 = 49
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0822
        L_0x07fd:
            r19 = r17
            r20 = 1
            int r19 = r19 + 1
            r12 = r19
        L_0x0805:
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0828
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x0828
            int r12 = r12 + 1
            goto L_0x0805
        L_0x081e:
            int r17 = r17 + 1
            goto L_0x0789
        L_0x0822:
            r19 = 35
            r7 = r19
            goto L_0x0042
        L_0x0828:
            r19 = r15
            r20 = r12
            r21 = 7
            int r20 = r20 + 7
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r20 = 101(0x65, float:1.42E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 110(0x6e, float:1.54E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            r21 = 2
            int r20 = r20 + 2
            char r19 = r19[r20]
            r20 = 99
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            r21 = 3
            int r20 = r20 + 3
            char r19 = r19[r20]
            r20 = 111(0x6f, float:1.56E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            r21 = 4
            int r20 = r20 + 4
            char r19 = r19[r20]
            r20 = 100
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            r21 = 5
            int r20 = r20 + 5
            char r19 = r19[r20]
            r20 = 105(0x69, float:1.47E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            r21 = 6
            int r20 = r20 + 6
            char r19 = r19[r20]
            r20 = 110(0x6e, float:1.54E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            r21 = 7
            int r20 = r20 + 7
            char r19 = r19[r20]
            r20 = 103(0x67, float:1.44E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c3
            int r12 = r12 + 8
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
        L_0x08cc:
            r19 = r10
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x08e9
            int r12 = r12 + 1
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x08e9
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
            goto L_0x08cc
        L_0x08e9:
            r19 = r10
            r20 = 61
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x08fe
            java.lang.String r19 = "bad 'encoding' declaration"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x08fe:
            r19 = r4
            int r12 = r12 + 1
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
        L_0x0908:
            r19 = r10
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x0925
            int r12 = r12 + 1
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0925
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
            goto L_0x0908
        L_0x0925:
            r19 = r10
            r20 = 39
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0944
            r19 = r10
            r20 = 34
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0944
            java.lang.String r19 = "bad 'encoding' declaration"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0944:
            r19 = r10
            r16 = r19
            int r12 = r12 + 1
            r19 = r12
            r17 = r19
        L_0x094e:
            r19 = r17
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0963
            java.lang.String r19 = "bad 'encoding' declaration"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0963:
            r19 = r4
            r20 = r17
            char r19 = r19[r20]
            r10 = r19
            r19 = r10
            r20 = r16
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x09c0
            java.lang.String r19 = new java.lang.String
            r26 = r19
            r19 = r26
            r20 = r26
            r21 = r4
            r22 = r12
            r23 = r17
            r24 = r12
            int r23 = r23 - r24
            r20.<init>(r21, r22, r23)
            r18 = r19
            r19 = r2
            r0 = r19
            boolean r0 = r0 instanceof gnu.text.LineInputStreamReader
            r19 = r0
            if (r19 == 0) goto L_0x099f
            r19 = r2
            gnu.text.LineInputStreamReader r19 = (gnu.text.LineInputStreamReader) r19
            r20 = r18
            r19.setCharset((java.lang.String) r20)
        L_0x099f:
            r19 = r17
            r20 = 1
            int r19 = r19 + 1
            r12 = r19
        L_0x09a7:
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x09c3
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x09c3
            int r12 = r12 + 1
            goto L_0x09a7
        L_0x09c0:
            int r17 = r17 + 1
            goto L_0x094e
        L_0x09c3:
            r19 = r15
            r20 = r12
            r21 = 9
            int r20 = r20 + 9
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r20 = 115(0x73, float:1.61E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 116(0x74, float:1.63E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 2
            int r20 = r20 + 2
            char r19 = r19[r20]
            r20 = 97
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 3
            int r20 = r20 + 3
            char r19 = r19[r20]
            r20 = 110(0x6e, float:1.54E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 4
            int r20 = r20 + 4
            char r19 = r19[r20]
            r20 = 100
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 5
            int r20 = r20 + 5
            char r19 = r19[r20]
            r20 = 97
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 6
            int r20 = r20 + 6
            char r19 = r19[r20]
            r20 = 108(0x6c, float:1.51E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 7
            int r20 = r20 + 7
            char r19 = r19[r20]
            r20 = 111(0x6f, float:1.56E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 8
            int r20 = r20 + 8
            char r19 = r19[r20]
            r20 = 110(0x6e, float:1.54E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            r21 = 9
            int r20 = r20 + 9
            char r19 = r19[r20]
            r20 = 101(0x65, float:1.42E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bd3
            int r12 = r12 + 10
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
        L_0x0a8b:
            r19 = r10
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x0aa8
            int r12 = r12 + 1
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0aa8
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
            goto L_0x0a8b
        L_0x0aa8:
            r19 = r10
            r20 = 61
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0abd
            java.lang.String r19 = "bad 'standalone' declaration"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0abd:
            r19 = r4
            int r12 = r12 + 1
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
        L_0x0ac7:
            r19 = r10
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x0ae4
            int r12 = r12 + 1
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0ae4
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r10 = r19
            goto L_0x0ac7
        L_0x0ae4:
            r19 = r10
            r20 = 39
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0b03
            r19 = r10
            r20 = 34
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0b03
            java.lang.String r19 = "bad 'standalone' declaration"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0b03:
            r19 = r10
            r16 = r19
            int r12 = r12 + 1
            r19 = r12
            r17 = r19
        L_0x0b0d:
            r19 = r17
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0b22
            java.lang.String r19 = "bad 'standalone' declaration"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0b22:
            r19 = r4
            r20 = r17
            char r19 = r19[r20]
            r10 = r19
            r19 = r10
            r20 = r16
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0b95
            r19 = r17
            r20 = r12
            r21 = 3
            int r20 = r20 + 3
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0b99
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r20 = 121(0x79, float:1.7E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0b99
            r19 = r4
            r20 = r12
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 101(0x65, float:1.42E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0b99
            r19 = r4
            r20 = r12
            r21 = 2
            int r20 = r20 + 2
            char r19 = r19[r20]
            r20 = 115(0x73, float:1.61E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0b99
        L_0x0b74:
            r19 = r17
            r20 = 1
            int r19 = r19 + 1
            r12 = r19
        L_0x0b7c:
            r19 = r12
            r20 = r15
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0bd3
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            boolean r19 = java.lang.Character.isWhitespace(r19)
            if (r19 == 0) goto L_0x0bd3
            int r12 = r12 + 1
            goto L_0x0b7c
        L_0x0b95:
            int r17 = r17 + 1
            goto L_0x0b0d
        L_0x0b99:
            r19 = r17
            r20 = r12
            r21 = 2
            int r20 = r20 + 2
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bc8
            r19 = r4
            r20 = r12
            char r19 = r19[r20]
            r20 = 110(0x6e, float:1.54E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bc8
            r19 = r4
            r20 = r12
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 111(0x6f, float:1.56E-43)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0bc8
            goto L_0x0b74
        L_0x0bc8:
            java.lang.String r19 = "bad 'standalone' declaration"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0bd3:
            r19 = r15
            r20 = r12
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0bec
            java.lang.String r19 = "junk at end of xml declaration"
            r13 = r19
            r19 = r12
            r5 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0bec:
            r19 = r6
            r14 = r19
            r19 = -1
            r12 = r19
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x0bfa:
            java.lang.String r19 = "<?xml must be at start of file"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0c05:
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r23 = r12
            r24 = r15
            r25 = r12
            int r24 = r24 - r25
            r19.processingInstructionFromParser(r20, r21, r22, r23, r24)
            goto L_0x0bec
        L_0x0c19:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0047
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
            goto L_0x05f2
        L_0x0c2f:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0047
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
        L_0x0c43:
            r19 = r10
            r20 = 62
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0d99
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r20 = r14
            int r19 = r19 - r20
            r11 = r19
            r19 = r11
            r20 = 4
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x0cce
            r19 = r4
            r20 = r14
            char r19 = r19[r20]
            r20 = 45
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cce
            r19 = r4
            r20 = r14
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 45
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cce
            r19 = r4
            r20 = r5
            r21 = 2
            int r20 = r20 + -2
            char r19 = r19[r20]
            r20 = 45
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r4
            r20 = r5
            r21 = 3
            int r20 = r20 + -3
            char r19 = r19[r20]
            r20 = 45
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = 2
            int r21 = r21 + 2
            r22 = r11
            r23 = 4
            int r22 = r22 + -4
            r19.commentFromParser(r20, r21, r22)
        L_0x0cc4:
            r19 = r6
            r14 = r19
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x0cce:
            r19 = r11
            r20 = 6
            r0 = r19
            r1 = r20
            if (r0 < r1) goto L_0x0cc4
            r19 = r4
            r20 = r14
            char r19 = r19[r20]
            r20 = 91
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cc4
            r19 = r4
            r20 = r14
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 67
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cc4
            r19 = r4
            r20 = r14
            r21 = 2
            int r20 = r20 + 2
            char r19 = r19[r20]
            r20 = 68
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cc4
            r19 = r4
            r20 = r14
            r21 = 3
            int r20 = r20 + 3
            char r19 = r19[r20]
            r20 = 65
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cc4
            r19 = r4
            r20 = r14
            r21 = 4
            int r20 = r20 + 4
            char r19 = r19[r20]
            r20 = 84
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cc4
            r19 = r4
            r20 = r14
            r21 = 5
            int r20 = r20 + 5
            char r19 = r19[r20]
            r20 = 65
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cc4
            r19 = r4
            r20 = r14
            r21 = 6
            int r20 = r20 + 6
            char r19 = r19[r20]
            r20 = 91
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0cc4
            r19 = r4
            r20 = r5
            r21 = 2
            int r20 = r20 + -2
            char r19 = r19[r20]
            r20 = 93
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r4
            r20 = r5
            r21 = 3
            int r20 = r20 + -3
            char r19 = r19[r20]
            r20 = 93
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = 7
            int r21 = r21 + 7
            r22 = r5
            r23 = 10
            int r22 = r22 + -10
            r23 = r14
            int r22 = r22 - r23
            r19.writeCDATA(r20, r21, r22)
            goto L_0x0cc4
        L_0x0d99:
            r19 = r5
            r20 = r14
            r21 = 7
            int r20 = r20 + 7
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r4
            r20 = r14
            char r19 = r19[r20]
            r20 = 68
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r4
            r20 = r14
            r21 = 1
            int r20 = r20 + 1
            char r19 = r19[r20]
            r20 = 79
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r4
            r20 = r14
            r21 = 2
            int r20 = r20 + 2
            char r19 = r19[r20]
            r20 = 67
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r4
            r20 = r14
            r21 = 3
            int r20 = r20 + 3
            char r19 = r19[r20]
            r20 = 84
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r4
            r20 = r14
            r21 = 4
            int r20 = r20 + 4
            char r19 = r19[r20]
            r20 = 89
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r4
            r20 = r14
            r21 = 5
            int r20 = r20 + 5
            char r19 = r19[r20]
            r20 = 80
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r10
            r20 = 69
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0c2f
            r19 = r6
            r14 = r19
            r19 = 15
            r7 = r19
            goto L_0x0047
        L_0x0e23:
            r19 = 17
            r7 = r19
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r14 = r19
            goto L_0x0042
        L_0x0e31:
            r19 = r12
            if (r19 >= 0) goto L_0x0e51
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r12 = r19
            r19 = r12
            r20 = r14
            int r19 = r19 - r20
            r12 = r19
            r19 = r12
            r20 = 1
            int r19 = r19 << 1
            r12 = r19
            r19 = 0
            r8 = r19
        L_0x0e51:
            r19 = r10
            r20 = 39
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0e65
            r19 = r10
            r20 = 34
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0e91
        L_0x0e65:
            r19 = r8
            if (r19 != 0) goto L_0x0e82
            r19 = r10
            r8 = r19
        L_0x0e6d:
            r19 = r5
            r20 = r6
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0047
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
            goto L_0x0e51
        L_0x0e82:
            r19 = r8
            r20 = r10
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0e6d
            r19 = 0
            r8 = r19
            goto L_0x0e6d
        L_0x0e91:
            r19 = r8
            if (r19 != 0) goto L_0x0e6d
            r19 = r10
            r20 = 91
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0ea8
            r19 = r12
            r20 = 1
            r19 = r19 | 1
            r12 = r19
            goto L_0x0e6d
        L_0x0ea8:
            r19 = r10
            r20 = 93
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0ebb
            r19 = r12
            r20 = -2
            r19 = r19 & -2
            r12 = r19
            goto L_0x0e6d
        L_0x0ebb:
            r19 = r10
            r20 = 62
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0e6d
            r19 = r12
            r20 = 1
            r19 = r19 & 1
            if (r19 != 0) goto L_0x0e6d
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r12
            r20 = 1
            int r19 = r19 >> 1
            r12 = r19
            r19 = r12
            r20 = r14
            int r19 = r19 + r20
            r12 = r19
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r23 = r12
            r24 = r5
            r25 = 1
            int r24 = r24 + -1
            r25 = r12
            int r24 = r24 - r25
            r19.emitDoctypeDecl(r20, r21, r22, r23, r24)
            r19 = 60
            r8 = r19
            r19 = r6
            r14 = r19
            r19 = -1
            r12 = r19
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x0f10:
            r19 = 60
            r8 = r19
            r19 = 14
            r9 = r19
            r19 = r10
            r20 = 47
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0f42
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r19.emitEndAttributes()
            r19 = r3
            r20 = 0
            r21 = 0
            r22 = 0
            r19.emitEndElement(r20, r21, r22)
            r19 = 27
            r7 = r19
            goto L_0x0047
        L_0x0f42:
            r19 = r10
            r20 = 62
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0f61
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r19.emitEndAttributes()
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x0f61:
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r14 = r19
            r19 = 9
            r7 = r19
            goto L_0x0042
        L_0x0f6f:
            r19 = r10
            r20 = 32
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 9
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 13
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 10
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 133(0x85, float:1.86E-43)
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 8232(0x2028, float:1.1535E-41)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0fad
            goto L_0x0047
        L_0x0fad:
            r19 = r2
            r20 = r5
            r21 = r11
            int r20 = r20 - r21
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r19.emitStartAttribute(r20, r21, r22)
            r19 = r6
            r14 = r19
            r19 = r10
            r20 = 61
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x0fda
            r19 = 11
            r7 = r19
            goto L_0x0047
        L_0x0fda:
            r19 = r3
            r19.emitEndAttributes()
            java.lang.String r19 = "missing or misplaced '=' after attribute name"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x0fea:
            r19 = r10
            r20 = 39
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0ffe
            r19 = r10
            r20 = 34
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x100c
        L_0x0ffe:
            r19 = r10
            r8 = r19
            r19 = 12
            r9 = r19
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x100c:
            r19 = r10
            r20 = 32
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 9
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 13
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 10
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 133(0x85, float:1.86E-43)
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0047
            r19 = r10
            r20 = 8232(0x2028, float:1.1535E-41)
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x104a
            goto L_0x0047
        L_0x104a:
            java.lang.String r19 = "missing or unquoted attribute value"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x1055:
            r19 = r5
            r20 = 1
            int r19 = r19 + -1
            r14 = r19
            r19 = 5
            r7 = r19
            goto L_0x0042
        L_0x1063:
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0
            r19 = r3
            r20 = r4
            r21 = r14
            r22 = r11
            r19.emitEndElement(r20, r21, r22)
            r19 = r6
            r14 = r19
            r19 = 29
            r7 = r19
            goto L_0x0042
        L_0x1082:
            r19 = r10
            r20 = 62
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x1097
            java.lang.String r19 = "missing '>'"
            r13 = r19
            r19 = 36
            r7 = r19
            goto L_0x0042
        L_0x1097:
            r19 = 1
            r7 = r19
            goto L_0x0047
        L_0x109d:
            r19 = r5
            r20 = r14
            int r19 = r19 - r20
            r15 = r19
            r19 = r15
            if (r19 <= 0) goto L_0x10be
            r19 = r2
            r20 = r14
            r0 = r20
            r1 = r19
            r1.pos = r0     // Catch:{ IOException -> 0x113d }
            r19 = r2
            r20 = r15
            r21 = 1
            int r20 = r20 + 1
            r19.mark(r20)     // Catch:{ IOException -> 0x113d }
        L_0x10be:
            r19 = r2
            r20 = r5
            r0 = r20
            r1 = r19
            r1.pos = r0     // Catch:{ IOException -> 0x113d }
            r19 = r2
            int r19 = r19.read()     // Catch:{ IOException -> 0x113d }
            r16 = r19
            r19 = r16
            if (r19 >= 0) goto L_0x10f0
            r19 = r7
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x10e8
            r19 = r7
            r20 = 28
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x10ea
        L_0x10e8:
            goto L_0x00b7
        L_0x10ea:
            r19 = 37
            r7 = r19
            goto L_0x0042
        L_0x10f0:
            r19 = r15
            if (r19 <= 0) goto L_0x1137
            r19 = r2
            r19.reset()     // Catch:{ IOException -> 0x113d }
            r19 = r2
            r20 = r15
            int r19 = r19.skip(r20)     // Catch:{ IOException -> 0x113d }
        L_0x1101:
            r19 = r2
            r0 = r19
            int r0 = r0.pos
            r19 = r0
            r5 = r19
            r19 = r2
            r0 = r19
            char[] r0 = r0.buffer
            r19 = r0
            r4 = r19
            r19 = r2
            r0 = r19
            int r0 = r0.limit
            r19 = r0
            r6 = r19
            r19 = r15
            if (r19 <= 0) goto L_0x1152
            r19 = r5
            r20 = r15
            int r19 = r19 - r20
        L_0x1129:
            r14 = r19
            r19 = r4
            r20 = r5
            int r5 = r5 + 1
            char r19 = r19[r20]
            r10 = r19
            goto L_0x0042
        L_0x1137:
            r19 = r2
            r19.unread_quick()     // Catch:{ IOException -> 0x113d }
            goto L_0x1101
        L_0x113d:
            r19 = move-exception
            r16 = r19
            java.lang.RuntimeException r19 = new java.lang.RuntimeException
            r26 = r19
            r19 = r26
            r20 = r26
            r21 = r16
            java.lang.String r21 = r21.getMessage()
            r20.<init>(r21)
            throw r19
        L_0x1152:
            r19 = r6
            goto L_0x1129
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLParser.parse(gnu.text.LineBufferedReader, gnu.xml.XMLFilter):void");
    }
}
