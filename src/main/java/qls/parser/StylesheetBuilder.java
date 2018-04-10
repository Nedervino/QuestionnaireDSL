package qls.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.parser.ExceptionErrorListener;
import ql.utilities.IOHandler;
import qls.QLSLexer;
import qls.QLSParser;
import qls.ast.Stylesheet;

import java.io.File;

public class StylesheetBuilder {

    public static Stylesheet createStylesheet(File qlsFile) {
        String formContent = IOHandler.toString(qlsFile);
        QLSParser parser = createParser(formContent);

        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLSParser.StylesheetContext stylesheetContext = parser.stylesheet();

        return (Stylesheet) astConstructionVisitor.visit(stylesheetContext);
    }

    public static QLSParser createParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        QLSLexer lexer = new QLSLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QLSParser parser = new QLSParser(tokenStream);

        parser.removeErrorListeners();
        ExceptionErrorListener throwErrorListener = new ExceptionErrorListener();
        parser.addErrorListener(throwErrorListener);

        return parser;
    }

}


