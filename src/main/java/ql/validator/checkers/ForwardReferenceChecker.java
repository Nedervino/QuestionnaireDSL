package ql.validator.checkers;

import ql.ast.Form;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.Negation;
import ql.ast.expressions.unary.Negative;
import ql.ast.statements.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormStatementVisitor;

import java.util.HashSet;
import java.util.Set;

/**
 * Checks AST for question duplications, giving errors for duplicate identifiers and warnings for duplicate labels
 */
public class ForwardReferenceChecker extends BaseChecker implements FormStatementVisitor<Void>, ExpressionVisitor<Void> {

    private final Set<String> declarationHistory;

    public ForwardReferenceChecker(Form form) {
        super();
        this.declarationHistory = new HashSet<>();
        form.accept(this);
    }

    @Override
    public boolean passesTests() {
        return !issueTracker.hasErrors();
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
        addQuestion(question);
        return null;
    }

    private void addQuestion(Question question) {
        declarationHistory.add(question.getId());
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        computedQuestion.getExpression().accept(this);
        addQuestion(computedQuestion);
        return null;
    }


    /**
     * Check question for identifiers used up until now.
     * <p>
     * This will generate issues for:
     * <li>duplicate question declarations which refer to questions declared later in the form (Error)
     */
    private void checkForwardReference(Variable variable) {
        String variableName = variable.getName();
        if (!declarationHistory.contains(variableName)) {
            issueTracker.addError(variable, String.format("Question with identifier \"%s\" is referred to before it's declaration", variableName));
        }
    }

    @Override
    public Void visit(Addition addition) {
        visitChildren(addition);
        return null;
    }

    @Override
    public Void visit(And and) {
        visitChildren(and);
        return null;
    }

    @Override
    public Void visit(Division division) {
        visitChildren(division);
        return null;
    }

    @Override
    public Void visit(Equal equal) {
        visitChildren(equal);
        return null;
    }

    @Override
    public Void visit(GreaterThanEqual greaterThanEqual) {
        visitChildren(greaterThanEqual);
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan) {
        visitChildren(greaterThan);
        return null;
    }

    @Override
    public Void visit(LessThanEqual lessThanEqual) {
        visitChildren(lessThanEqual);
        return null;
    }

    @Override
    public Void visit(LessThan lessThan) {
        visitChildren(lessThan);
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication) {
        visitChildren(multiplication);
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual) {
        visitChildren(notEqual);
        return null;
    }

    @Override
    public Void visit(Or or) {
        visitChildren(or);
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction) {
        visitChildren(subtraction);
        return null;
    }

    @Override
    public Void visit(Negation negation) {
        negation.getExpression().accept(this);
        return null;
    }

    @Override
    public Void visit(Negative negative) {
        negative.getExpression().accept(this);
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Void visit(DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public Void visit(DecimalLiteral decimalLiteral) {
        return null;
    }

    @Override
    public Void visit(MoneyLiteral moneyLiteral) {
        return null;
    }

    @Override
    public Void visit(Variable variable) {
        checkForwardReference(variable);
        return null;
    }

    public void visitChildren(BinaryOperation binaryOperation) {
        binaryOperation.getLeft().accept(this);
        binaryOperation.getRight().accept(this);

    }
}
