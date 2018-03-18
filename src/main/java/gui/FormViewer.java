package gui;

import ql.ast.Form;
import ql.evaluator.Evaluator;
import qls.ast.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class FormViewer {

    // private Evaluator evaluator;
    Evaluator evaluator;
    // private JPanel panel;
    JPanel panel;
    private LinkedList<GUIElement> elements;
    private JFrame frame;

    public FormViewer(Evaluator evaluator) {
        this.evaluator = evaluator;
        elements = new LinkedList<>();
    }


    public void start(Form node, Stylesheet stylesheet) {
        initialiseFrame();

        GUIElementConstructionVisitor visitor = new GUIElementConstructionVisitor(this);
        visitor.visit(node);

        panel.repaint();
        Graphics g = panel.getGraphics();
        for (GUIElement element : elements) {
            element.render(g);
        }

    }

    public void initialiseFrame() {
        frame = new JFrame("Form Viewer");
        panel = new JPanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 900);
        frame.setVisible(true);
        frame.add(panel);
    }

    public void addElement(GUIElement element) {
        elements.add(element);
    }

}
