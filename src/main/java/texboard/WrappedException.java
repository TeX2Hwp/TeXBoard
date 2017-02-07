package texboard;

import uk.ac.ed.ph.snuggletex.InputError;
import uk.ac.ed.ph.snuggletex.SnuggleSession;

public class WrappedException extends Exception {
    private boolean parseErrorFlag = false;
    private InputError[] parseErrors = null;

    public WrappedException() {
        super();
    }

    public WrappedException(String msg) {
        super(msg);
    }

    public WrappedException(Throwable cause) {
        super(cause);
    }

    public WrappedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public WrappedException(SnuggleSession session) {
        super("Syntax error");

        parseErrorFlag = true;
        parseErrors = session.getErrors().toArray(new InputError[0]);
    }

    public boolean isParseError() {
        return parseErrorFlag;
    }

    public InputError[] getParseErrors() {
        return parseErrors;
    }
}
