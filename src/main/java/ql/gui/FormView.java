package ql.gui;

import ql.ast.ASTNode;
import ql.evaluator.Evaluator;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class FormView extends JPanel{

    Evaluator evaluator;
    LinkedList<GUIElement> elements;

    public FormView(Evaluator evaluator){
        elements = new LinkedList<>();
    }


    public void start(ASTNode node) {
        init();

        RenderVisitor visitor = new RenderVisitor(this);
        visitor.visit(node);

        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);

        for(GUIElement element : elements){
            element.render(g);
        }
    }

    public void init() {
        JFrame frame = new JFrame("Form Viewer");
        JPanel panel = this;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,900);
        frame.setVisible(true);
        frame.add(panel);
    }

    public void addElement(GUIElement element){
        elements.add(element);
    }

}
