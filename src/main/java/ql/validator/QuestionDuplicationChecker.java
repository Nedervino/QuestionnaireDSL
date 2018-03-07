package ql.validator;

import ql.ast.Form;
import ql.ast.statements.*;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.validator.issuetracker.IssueTracker;

import java.util.HashSet;
import java.util.Set;

/**
 * Checks AST for question duplications, giving errors for duplicate identifiers and warnings for duplicate labels
 */
public class QuestionDuplicationChecker implements FormVisitor<Void>, StatementVisitor<Void> {

    private final Set<String> questionLabels;
    private final IssueTracker issueTracker;
    private SymbolTable symbolTable;


    public QuestionDuplicationChecker(IssueTracker issueTracker) {
        this.issueTracker = issueTracker;
        this.questionLabels = new HashSet<>();
    }

    public boolean passesTests(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        visit(form);
        return issueTracker.getErrors().size() == 0;
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
        checkDuplication(question);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        checkDuplication(computedQuestion);
        return null;
    }

    public void checkDuplication(Question question) {
        if (symbolTable.isDeclared(question.getId())) {
            issueTracker.addError(0,0,String.format("VALIDATION ERROR: Question with identifier \"%s\" declared on multiple locations", question.getId()));
        } else {
            symbolTable.declare(question.getId(), question.getType());
        }
        if (questionLabels.contains(question.getLabel())) {
            issueTracker.addWarning(0,0,String.format("VALIDATION WARNING: Duplicate question label \"%s\" used on multiple locations", question.getLabel()));
        } else {
            questionLabels.add(question.getLabel());
        }
    }

}
