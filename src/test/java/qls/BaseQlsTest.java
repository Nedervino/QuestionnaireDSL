package qls;

import ql.BaseQlTest;
import ql.utilities.IOHandler;
import qls.ast.Stylesheet;
import qls.parser.StylesheetBuilder;

public class BaseQlsTest extends BaseQlTest {

    protected static Stylesheet createStylesheet(String fileName) {
        return new StylesheetBuilder().createStylesheet(fileName);
    }

}
