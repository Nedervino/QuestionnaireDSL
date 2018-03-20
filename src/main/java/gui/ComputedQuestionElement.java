package gui;

import ql.ast.statements.ComputedQuestion;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import java.awt.*;

public class ComputedQuestionElement extends GUIElement {

    private ComputedQuestion node;
    private FormEvaluator evaluator;

    public ComputedQuestionElement(ComputedQuestion node, int yLoc, FormEvaluator evaluator) {
        super(yLoc);
        this.node = node;
        this.evaluator = evaluator;
    }

    @Override
    public void render(Graphics g) {
        System.out.println("Rendering computedQuestion");
        String varName = node.getId();
        Object value = "";

        Evaluatable evaluatable = evaluator.getQuestionValue(varName);
        if (evaluatable != null) {
            value = evaluatable.getValue();
        }

        g.setColor(Color.black);
        g.drawString(varName + ": " + String.valueOf(value), 50, this.getYLocation());
    }
}
