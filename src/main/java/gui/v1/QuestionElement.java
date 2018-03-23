package gui.v1;

import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import java.awt.*;

public class QuestionElement extends GUIElement {

    private final FormEvaluator evaluator;
    private final Question question;

    public QuestionElement(Question question, int yLocation, FormEvaluator evaluator) {
        super(yLocation);
        this.question = question;
        this.evaluator = evaluator;
        setHeight(70);
    }

    public Question getQuestion() {
        return question;
    }

    public FormEvaluator getEvaluator() {
        return evaluator;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawString(question.getLabel(), 50, this.getYLocation());
    }

}
