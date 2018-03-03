package ql.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ql.QLLexer;
import ql.QLParser;
import ql.ast.ASTNode;
import ql.typechecker.TypeChecker;
import ql.ast.ASTConstructionVisitor;
import ql.ast.FormNode;
import ql.gui.FormView;
import ql.gui.TreeView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ASTBuilder {

    public FormNode buildAST(String filePath) {
        String formContent = loadFile(filePath);
        FormNode form = buildASTFromString(formContent);

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

    public FormNode buildASTFromString(String formContent) {
        QLParser parser = createParser(formContent);

        // ParseTree parseTree = parser.form();
        // TreeView treeViewer = new TreeView();
        // treeViewer.start(parser, parseTree);

        ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
        QLParser.FormContext formContext =  parser.form();
        FormNode form = (FormNode) astConstructionVisitor.visit(formContext);

        return form;
    }

    public QLParser createParser (String input) {
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
