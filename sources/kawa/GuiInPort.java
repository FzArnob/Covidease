package kawa;

import gnu.mapping.OutPort;
import gnu.mapping.TtyInPort;
import gnu.text.Path;
import java.io.IOException;
import java.io.Reader;

class GuiInPort extends TtyInPort {
    ReplDocument document;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuiInPort(Reader in, Path path, OutPort tie, ReplDocument document2) {
        super(in, path, tie);
        this.document = document2;
    }

    public void emitPrompt(String prompt) throws IOException {
        this.document.write(prompt, ReplDocument.promptStyle);
    }
}
