package ql.gui;

import ql.ast.Form;
import ql.ast.statements.Question;
import ql.evaluator.Evaluator;
import ql.evaluator.FormEvaluator;

import java.util.ArrayList;
import java.util.List;

public class FormUIFactory {

    public FormUI getFormUI(Form form) {
        //TODO: optionally move form to evaluator constructor
        FormEvaluator evaluator = new Evaluator(form);
        evaluator.evaluate();

        List<QuestionUI> questions = new ArrayList<>();
        for (Question question : evaluator.getQuestions()) {
            questions.add(getQuestionUI(evaluator, question));
        }
        return new FormUI(questions);
    }

    public QuestionUI getQuestionUI(FormEvaluator evaluator, Question question) {
        return new QuestionUI(evaluator, question);
    }

}
