package ql.evaluator;

import ql.ast.Form;
import ql.ast.statements.*;
import ql.ast.visitors.FormStatementVisitor;

import java.util.LinkedList;
import java.util.List;

public class QuestionCollector implements FormStatementVisitor<List<Question>> {

    List<Question> getQuestions(Form form){
        return visit(form);
    }

    @Override
    public List<Question> visit(Form form) {
        List<Question> questions = new LinkedList<>();
        for(Statement statement : form.getStatements()){
            questions.addAll(statement.accept(this));
        }
        return questions;
    }

    @Override
    public List<Question> visit(IfStatement ifStatement) {
        List<Question> questions = new LinkedList<>();
        for(Statement statement : ifStatement.getIfStatements()){
            questions.addAll(statement.accept(this));
        }
        return questions;
    }

    @Override
    public List<Question> visit(IfElseStatement ifElseStatement) {
        List<Question> questions = new LinkedList<>();
        for(Statement statement : ifElseStatement.getIfStatements()){
            questions.addAll(statement.accept(this));
        }
        for(Statement statement : ifElseStatement.getElseStatements()){
            questions.addAll(statement.accept(this));
        }
        return questions;
    }

    @Override
    public List<Question> visit(Question question) {
        LinkedList<Question> questions = new LinkedList<>();
        questions.add(question);
        return questions;
    }

    @Override
    public List<Question> visit(ComputedQuestion computedQuestion) {
        LinkedList<Question> questions = new LinkedList<>();
        questions.add(computedQuestion);
        return questions;
    }
}
