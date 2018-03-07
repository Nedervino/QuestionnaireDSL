package ql.gui;

import ql.ast.statements.ComputedQuestionNode;
import ql.evaluator.Evaluator;

import java.awt.*;

public class ComputedQuestionElement extends GUIElement {

    ComputedQuestionNode node;
    Evaluator evaluator;

    public ComputedQuestionElement(ComputedQuestionNode node, int yLoc, Evaluator evaluator) {
        super(yLoc);
        this.node = node;
        this.evaluator = evaluator;
    }

    @Override
    public void render(Graphics g) {
        String varName = node.getId();
        Object value = evaluator.get(varName);
        g.drawString(varName + ": " + String.valueOf(value), 50, yLoc);
        yLoc+=40;
    }
}
