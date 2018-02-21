package ql;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import view.FormView;
import view.TreeView;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FormGenerator {

    public void loadFile(String filePath) {
        try {
            String formContent = new String(Files.readAllBytes(Paths.get(filePath)));
            generateFromString(formContent);
        } catch (IOException e) {
            System.err.println("Couldn't read source: " + e.getMessage());
        }
    }

    public void generateFromString(String formContent) throws IOException {
            InputStream stream = new ByteArrayInputStream(formContent.getBytes(StandardCharsets.UTF_8));
            QLLexer lexer = new QLLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokenStream);
            parser.removeErrorListeners();
            ExceptionErrorListener throwErrorListener = new ExceptionErrorListener();
            parser.addErrorListener(throwErrorListener);

            ParseTree parseTree = parser.form();

            TreeView treeViewer = new TreeView();
            treeViewer.start(parser, parseTree);


            TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
            typeCheckVisitor.visit(parseTree);

            FormView formViewer = new FormView();
            formViewer.start(parseTree);
    }

}
