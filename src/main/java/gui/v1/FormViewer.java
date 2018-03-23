package gui.v1;

import ql.ast.Form;
import ql.evaluator.FormEvaluator;
import qls.ast.Stylesheet;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class FormViewer {

    // private FormEvaluator evaluator;
    final FormEvaluator evaluator;
    // private JPanel panel;
    JPanel panel;
    private final LinkedList<GUIElement> elements;
    private JFrame frame;

    public FormViewer(FormEvaluator evaluator) {
        this.evaluator = evaluator;
        elements = new LinkedList<>();
    }

    public FormEvaluator getEvaluator() {
        return evaluator;
    }


    public void start(Form node, Stylesheet stylesheet) {
        initialiseFrame();

        GUIElementConstructionVisitorForm visitor = new GUIElementConstructionVisitorForm(this);
        visitor.visit(node);

        panel.repaint();
        Graphics g = panel.getGraphics();
        for (GUIElement element : elements) {
            element.render(g);
        }

    }

    private void initialiseFrame() {
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
