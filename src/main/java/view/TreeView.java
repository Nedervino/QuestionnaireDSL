package view;

import org.antlr.v4.runtime.tree.ParseTree;
import ql.QLParser;

import javax.swing.*;
import java.util.Arrays;

public class TreeView {

    public void start(QLParser parser, ParseTree parseTree) {

        //show AST in console
        System.out.println(parseTree.toStringTree(parser));

        //show AST in JFrame
        visualiseTree(parser, parseTree);

    }


    private void visualiseTree(QLParser parser, ParseTree parseTree) {
        JFrame frame = new JFrame("AST Visualisation");
        JPanel panel = new JPanel();
        org.antlr.v4.gui.TreeViewer viewer = new org.antlr.v4.gui.TreeViewer(Arrays.asList(parser.getRuleNames()),parseTree);
        viewer.setScale(0.8);//scale a little
        panel.add(viewer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(1400,400);
        frame.setVisible(true);
    }

}
