package ql.gui;

import ql.ast.statements.ComputedQuestion;
import ql.evaluator.Evaluatable;
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
        System.out.println("Rendering computedQuestion");
        String varName = node.getId();
        Object value = "";

        Evaluatable evaluatable = evaluator.get(node);
        if(evaluatable!=null) {
            value = evaluatable.getValue();
        }

        g.setColor(Color.black);
        g.drawString(varName + ": " + String.valueOf(value), 50, yLocation);
    }
}
