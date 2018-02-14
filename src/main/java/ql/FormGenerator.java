package ql;

import org.antlr.v4.runtime.*;

import java.io.IOException;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.gui.TreeViewer;

import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FormGenerator {

    public void start(String fileName) {

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

        //show AST in console
        System.out.println(parseTree.toStringTree(parser));

        //show AST in JFrame
        visualiseTree(parser, parseTree);

        QLCustomVisitor visitor = new QLCustomVisitor();

        visitor.visit(parseTree);


    }


    private void visualiseTree(QLParser parser, ParseTree parseTree) {
        JFrame frame = new JFrame("AST Visualisation");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()),parseTree);
        viewer.setScale(0.8);//scale a little
        panel.add(viewer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(1400,400);
        frame.setVisible(true);
    }


    public static void main(String[] args) {

    }

}
