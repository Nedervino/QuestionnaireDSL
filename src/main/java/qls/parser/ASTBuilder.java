package qls.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import qls.QLSLexer;
import qls.QLSParser;
import qls.ast.Stylesheet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ASTBuilder {

    //TODO: place file reader in separate java class in QL package

    public Stylesheet buildASTFromFile(String filePath) {
        String stylesheetContent = loadFile(filePath);
        Stylesheet stylesheet = buildASTFromString(stylesheetContent);

        return stylesheet;
    }

    private String loadFile(String filePath) {
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Couldn't process input source: " + e.getMessage());
        }
        return fileContent;
    }

    public Stylesheet buildASTFromString(String formContent) {
        QLSParser parser = createParser(formContent);

        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLSParser.StylesheetContext stylesheetContext = parser.stylesheet();
        // Stylesheet stylesheet = (Stylesheet) astConstructionVisitor.visit(stylesheetContext);
        // return stylesheet;
        return new Stylesheet("test", new ArrayList<>(), null);
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


