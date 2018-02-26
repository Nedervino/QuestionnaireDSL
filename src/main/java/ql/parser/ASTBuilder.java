package ql.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ql.QLLexer;
import ql.QLParser;
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

    public void loadFile(String filePath) {
        try {
            String formContent = new String(Files.readAllBytes(Paths.get(filePath)));
            FormNode form = buildASTFromString(formContent);
        } catch (IOException e) {
            System.err.println("Couldn't read source: " + e.getMessage());
        }
    }

    public FormNode buildASTFromString(String formContent) throws IOException {
            QLParser parser = createParser(formContent);

            ParseTree parseTree = parser.form();

            TreeView treeViewer = new TreeView();
            treeViewer.start(parser, parseTree);


            TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
            typeCheckVisitor.visit(parseTree);


            FormView formViewer = new FormView();
            formViewer.start(parseTree);


            ASTConstructionVisitor astVisitor = new ASTConstructionVisitor();
            FormNode form = (FormNode) astVisitor.visit(parseTree);

            //TODO write a visitor for the ql.ast which checks types.

            return form;
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
