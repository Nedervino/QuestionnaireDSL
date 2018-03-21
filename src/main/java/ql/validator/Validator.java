package ql.validator;

import ql.ast.Form;
import issuetracker.IssueTracker;

import java.util.logging.Logger;


/**
 * Performs validation for the AST by checking invalid references, expressions, duplications, and cyclic dependencies
 */
public class Validator {

    private final IssueTracker issueTracker;
    private final QuestionDuplicationChecker questionDuplicationChecker;
    private final ExpressionChecker expressionChecker;
    private final CyclicDependencyChecker cyclicDependencyChecker;
    private final SymbolTable symbolTable;


    public Validator() {
        issueTracker = new IssueTracker();
        questionDuplicationChecker = new QuestionDuplicationChecker(issueTracker);
        expressionChecker = new ExpressionChecker(issueTracker);
        cyclicDependencyChecker = new CyclicDependencyChecker(issueTracker);
        symbolTable = new SymbolTable();
    }

    public boolean passesTypeChecks(Form form) {

        //Check for duplicate question identifiers and labels
        if (!questionDuplicationChecker.passesTests(form, symbolTable)) {
            issueTracker.logErrors();
            return false;
        }

        //Check for reference to undefined questions, non-boolean conditionals, and invalid operand types
        if (!expressionChecker.passesTests(form, symbolTable)) {
            issueTracker.logErrors();
            return false;
        }

        //Check cyclic dependencies between questions
        if (!cyclicDependencyChecker.passesTests(form)) {
            issueTracker.logErrors();
            return false;
        }

        issueTracker.logWarnings();

        return true;
    }

}
