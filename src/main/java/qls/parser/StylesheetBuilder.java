package qls.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import qls.QLSLexer;
import qls.QLSParser;
import qls.ast.Stylesheet;

public class StylesheetBuilder {

    public Stylesheet createStylesheet(String formContent) {
        QLSParser parser = createParser(formContent);

        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLSParser.StylesheetContext stylesheetContext = parser.stylesheet();

        return (Stylesheet) astConstructionVisitor.visit(stylesheetContext);
    }

    public QLSParser createParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        QLSLexer lexer = new QLSLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QLSParser parser = new QLSParser(tokenStream);

        // parser.removeErrorListeners();
        // ExceptionErrorListener throwErrorListener = new ExceptionErrorListener();
        // parser.addErrorListener(throwErrorListener);

        return parser;
    }

}


