package ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import view.FormView;
import view.TreeView;

import java.io.IOException;

public class Main {
    public static void main (String[] args) {

        String fileName = "src/input/ql/formIf.ql";

        CharStream inputStream = null;
        try {
            inputStream = CharStreams.fromFileName(fileName);
        } catch (IOException e) {
            System.err.println("Couldn't find source file: " + e.getMessage());
        }


        QLLexer lexer = new QLLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokenStream);
        // parser.removeErrorListeners();
        ParseTree parseTree = parser.form();


        TreeView treeViewer = new TreeView();
        treeViewer.start(parser, parseTree);


        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
        typeCheckVisitor.visit(parseTree);

        System.out.println("No type conflicts found.");


        FormView formViewer = new FormView();
        formViewer.start(parseTree);
    }
}
