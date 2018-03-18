package gui;

import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import java.awt.*;

public class QuestionElement extends GUIElement {

    FormEvaluator evaluator;
    Question node;

    public QuestionElement(Question node, int yLocation, FormEvaluator evaluator) {
        super(yLocation);
        this.node = node;
        this.evaluator = evaluator;
        height = 70;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawString(node.getLabel(), 50, yLocation);
    }

}
