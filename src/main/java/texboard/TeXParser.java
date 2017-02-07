package texboard;

import uk.ac.ed.ph.snuggletex.SessionConfiguration;
import uk.ac.ed.ph.snuggletex.SnuggleEngine;
import uk.ac.ed.ph.snuggletex.SnuggleInput;
import uk.ac.ed.ph.snuggletex.SnuggleSession;

import java.io.IOException;

public class TeXParser {
    private SnuggleEngine engine;
    private SessionConfiguration safeConfiguration;
    private SessionConfiguration unsafeConfiguration;

    public TeXParser() {
        engine = new SnuggleEngine();
        safeConfiguration = new SessionConfiguration();
        unsafeConfiguration = new SessionConfiguration();

        safeConfiguration.setFailingFast(true);
    }

    public MLNode parse(String formula) throws WrappedException {
        SnuggleSession session = engine.createSession(safeConfiguration);
        SnuggleInput input = new SnuggleInput(formula);

        try {
            if (session.parseInput(input)) {
                return new MLNode(session.buildDOMSubtree().item(0));
            } else {
                throw new WrappedException(session);
            }
        } catch (IOException e) {
            throw new WrappedException(e);
        }
    }
}
