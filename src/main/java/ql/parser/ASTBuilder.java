package ql.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.QLLexer;
import ql.QLParser;
import ql.ast.Form;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ASTBuilder {

    public Form buildASTFromFile(String filePath) {
        String formContent = loadFile(filePath);
        Form form = buildASTFromString(formContent);

        return form;
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

    public Form buildASTFromString(String formContent) {
        QLParser parser = createParser(formContent);

        // ParseTree parseTree = parser.form();
        // TreeView treeViewer = new TreeView();
        // treeViewer.start(parser, parseTree);

        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLParser.FormContext formContext = parser.form();
        Form form = (Form) astConstructionVisitor.visit(formContext);

        return form;
    }

    public QLParser createParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        QLLexer lexer = new QLLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokenStream);

        parser.removeErrorListeners();
        ExceptionErrorListener throwErrorListener = new ExceptionErrorListener();
        parser.addErrorListener(throwErrorListener);

        return parser;
    }
}
