package gui;

import ql.ast.Form;
import ql.ast.statements.Question;
import ql.evaluator.Evaluator;
import ql.evaluator.FormEvaluator;

import java.util.ArrayList;
import java.util.List;

public class FormUIFactory {

    public FormUI getFormUI(Form form, FormEvaluator evaluator) {
        List<QuestionUI> questions = new ArrayList<>();
        for(Question question : evaluator.getQuestions()) {
            questions.add(new QuestionUI(evaluator, question));
        }
        return new FormUI(questions);
    }
}
