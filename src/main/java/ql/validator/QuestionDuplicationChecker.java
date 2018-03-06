package ql.validator;

import ql.ast.Form;
import ql.ast.statements.*;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;

import java.util.HashSet;
import java.util.Set;

public class QuestionDuplicationChecker implements FormVisitor<Void>, StatementVisitor<Void> {

    private SymbolTable symbolTable;
    private final Set<String> questionLabels;

    public QuestionDuplicationChecker() {
        questionLabels = new HashSet<>();
    }

    public boolean passesTests(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        visit(form);

        //TODO: Store warnings/errors in issueTracker object, return whether it's empty after visiting
        return true;
    }

    @Override
    public Void visit(Form form) {
        for(Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        for(Statement statement : ifStatement.getIfStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        for(Statement statement : ifElseStatement.getIfStatements()) {
            statement.accept(this);
        }
        for(Statement statement : ifElseStatement.getElseStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Question question) {
        if (symbolTable.isDeclared(question.getId())) {
            System.err.println(String.format("VALIDATION ERROR: Question with identifier \"%s\" declared on multiple locations", question.getId()));
        } else {
            symbolTable.declare(question.getId(), question.getType());
        }
        if (questionLabels.contains(question.getLabel())) {
            System.err.println(String.format("VALIDATION WARNING: Duplicate question label \"%s\" used on multiple locations", question.getLabel()));
        } else {
            questionLabels.add(question.getLabel());
        }
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        if (symbolTable.isDeclared(computedQuestion.getId())) {
            System.err.println(String.format("VALIDATION ERROR: Question with identifier \"%s\" declared on multiple locations", computedQuestion.getId()));
        } else {
            symbolTable.declare(computedQuestion.getId(), computedQuestion.getType());
        }
        if (questionLabels.contains(computedQuestion.getLabel())) {
            System.err.println(String.format("VALIDATION WARNING: Duplicate question label \"%s\" used on multiple locations", computedQuestion.getLabel()));
        } else {
            questionLabels.add(computedQuestion.getLabel());
        }
        return null;
    }
}
