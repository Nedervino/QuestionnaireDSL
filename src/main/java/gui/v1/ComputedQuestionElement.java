package gui.v1;

import ql.ast.statements.ComputedQuestion;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Value;

import java.awt.*;

public class ComputedQuestionElement extends GUIElement {

    private final ComputedQuestion node;
    private final FormEvaluator evaluator;

    public ComputedQuestionElement(ComputedQuestion node, int yLoc, FormEvaluator evaluator) {
        super(yLoc);
        this.node = node;
        this.evaluator = evaluator;
    }

    @Override
    public void render(Graphics g) {
        System.out.println("Rendering computedQuestion");
        String varName = node.getId();
        Object renderValue = "";

        Value value = evaluator.getQuestionValue(varName);
        if (value != null) {
            renderValue = value.getValue();
        }

        g.setColor(Color.black);
        g.drawString(varName + ": " + String.valueOf(renderValue), 50, this.getYLocation());
    }
}
