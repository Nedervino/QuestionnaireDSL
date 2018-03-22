package ql.validator;

import ql.ast.Form;
import issuetracker.IssueTracker;
import ql.validator.checkers.Checker;
import ql.validator.checkers.CyclicDependencyChecker;
import ql.validator.checkers.ExpressionChecker;
import ql.validator.checkers.QuestionDuplicationChecker;


/**
 * Performs validation for the AST by checking invalid references, expressions, duplications, and cyclic dependencies
 */
public class Validator {


    private final IssueTracker issueTracker;
    private final Checker questionDuplicationChecker;
    private final Checker expressionChecker;
    private final Checker cyclicDependencyChecker;
    private SymbolTable symbolTable;


    public Validator() {
        issueTracker = IssueTracker.getIssueTracker();
        questionDuplicationChecker = new QuestionDuplicationChecker(issueTracker);
        expressionChecker = new ExpressionChecker(issueTracker);
        cyclicDependencyChecker = new CyclicDependencyChecker(issueTracker);
    }

    public boolean passesTypeChecks(Form form) {

        //Initialize SymbolTable
        symbolTable = new SymbolTable(form);

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
        if (!cyclicDependencyChecker.passesTests(form, null)) {
            issueTracker.logErrors();
            return false;
        }

        issueTracker.logWarnings();

        return true;
    }

}
