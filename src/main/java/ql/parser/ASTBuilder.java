package ql.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ql.QLLexer;
import ql.QLParser;
import ql.ast.ASTNode;
import ql.typechecker.TypeChecker;
import ql.typechecker.legacy.TypeCheckVisitor;
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

    public void buildAST(String filePath) {
        try {

            String formContent = loadFile(filePath);
            ASTNode form = buildASTFromString(formContent);

            if (!passesTypeChecks(form)) {
                System.err.println("Form not passing type checks.");
            }

        } catch (IOException e) {
            System.err.println("Couldn't read source: " + e.getMessage());
        }
    }

    private String loadFile(String filePath) throws IOException{
        String formContent = new String(Files.readAllBytes(Paths.get(filePath)));
        return formContent;
    }

    public ASTNode buildASTFromString(String formContent) throws IOException {
            QLParser parser = createParser(formContent);

            ParseTree parseTree = parser.form();

            TreeView treeViewer = new TreeView();
            treeViewer.start(parser, parseTree);

            ASTConstructionVisitor astConstructionVisitor = new ASTConstructionVisitor();
            ASTNode form = astConstructionVisitor.visit(parseTree);

            FormView formViewer = new FormView();
            formViewer.start(form);

            return form;
    }

    public boolean passesTypeChecks (ASTNode form) {
        return new TypeChecker().passesTypeChecks(form);
    }

    public QLParser createParser (String input) throws IOException {
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        QLLexer lexer = new QLLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokenStream);
        parser.removeErrorListeners();
        ExceptionErrorListener throwErrorListener = new ExceptionErrorListener();
        parser.addErrorListener(throwErrorListener);
        return parser;
    }
}
