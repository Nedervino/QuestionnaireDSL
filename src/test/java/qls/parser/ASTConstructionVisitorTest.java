package qls.parser;

import org.junit.Test;
import qls.BaseQlsTest;
import qls.ast.Stylesheet;
import qls.ast.components.Section;

import static org.junit.Assert.assertEquals;

public class ASTConstructionVisitorTest extends BaseQlsTest {

    @Test
    public void visitStylesheet() {
        Stylesheet stylesheet = createStylesheet("src/input/qls/correct/simple.qls");
        assertEquals("simple", stylesheet.getStylesheetId());
        assertEquals(1, stylesheet.getPages().size());
    }

    @Test
    public void visitPages() {
        Stylesheet stylesheet = createStylesheet("src/input/qls/correct/simple.qls");
        assertEquals("Selling", stylesheet.getPages().get(0).getPageId());
        assertEquals(1, stylesheet.getPages().get(0).getComponents().size());
    }

    @Test
    public void visitSection() {
        Stylesheet stylesheet = createStylesheet("src/input/qls/correct/simple.qls");
        Section section = (Section) stylesheet.getPages().get(0).getComponents().get(0);
        Section section2 = ((Section) section.getComponents().get(0));
        assertEquals("You sold a house", section2.getSectionId());
        assertEquals(2, section2.getComponents().size());
        assertEquals(1, section2.getRules().size());
    }

}