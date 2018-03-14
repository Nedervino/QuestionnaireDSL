package ql.gui;

import ql.ast.Form;
import ql.evaluator.Evaluator;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class FormViewer extends JPanel {

    Evaluator evaluator;
    LinkedList<GUIElement> elements;

    public FormViewer(Evaluator evaluator) {
        this.evaluator = evaluator;
        evaluator.formViewer = this;
        elements = new LinkedList<>();
    }


    public void start(Form node) {
        init();

        GUIElementConstructionVisitor visitor = new GUIElementConstructionVisitor(this);
        visitor.visit(node);

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (GUIElement element : elements) {
            element.render(g);
        }
    }

    public void init() {
        JFrame frame = new JFrame("Form Viewer");
        JPanel panel = this;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 900);
        frame.setVisible(true);
        frame.add(panel);
    }

    public void addElement(GUIElement element) {
        elements.add(element);
    }

}
