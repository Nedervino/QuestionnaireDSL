package ql.gui;

import ql.ast.Form;
import ql.ast.statements.Question;
import ql.environment.FormEnvironment;
import ql.environment.Environment;

import java.util.ArrayList;
import java.util.List;

public class FormUIFactory {

    public FormUI getFormUI(Form form) {
        //TODO: optionally move form to environment constructor
        Environment environment = new FormEnvironment(form);
        environment.evaluate();

        List<QuestionUI> questions = new ArrayList<>();
        for (Question question : environment.getQuestions()) {
            questions.add(getQuestionUI(environment, question));
        }
        return new FormUI(questions);
    }

    public QuestionUI getQuestionUI(Environment environment, Question question) {
        return new QuestionUI(environment, question);
    }

}
