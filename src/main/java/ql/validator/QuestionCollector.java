package ql.validator;

import ql.ast.Form;
import ql.ast.statements.*;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionCollector implements FormVisitor<Void>, StatementVisitor<Void> {

    List<Question> questions;

    public QuestionCollector(Form form) {
        questions = new ArrayList<>();
        form.accept(this);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public Void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        for (Statement statement : ifStatement.getIfStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        for (Statement statement : ifElseStatement.getIfStatements()) {
            statement.accept(this);
        }
        for (Statement statement : ifElseStatement.getElseStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Question question) {
        questions.add(question);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        questions.add(computedQuestion);
        return null;
    }

}
