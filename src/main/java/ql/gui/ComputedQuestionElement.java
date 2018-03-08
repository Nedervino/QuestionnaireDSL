package ql.gui;

import ql.ast.statements.ComputedQuestion;
import ql.evaluator.Evaluator;

import java.awt.*;

public class ComputedQuestionElement extends GUIElement {

    ComputedQuestion node;
    Evaluator evaluator;

    public ComputedQuestionElement(ComputedQuestion node, int yLoc, Evaluator evaluator) {
        super(yLoc);
        this.node = node;
        this.evaluator = evaluator;
    }

    @Override
    public void render(Graphics g) {
        String varName = node.getId();
        Object value = evaluator.get(node);
        g.setColor(Color.black);
        g.drawString(varName + ": " + String.valueOf(value), 50, yLocation);
    }
}
