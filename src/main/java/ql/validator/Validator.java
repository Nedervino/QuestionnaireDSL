package ql.validator;

import issuetracker.IssueTracker;
import ql.ast.Form;
import ql.validator.checkers.Checker;
import ql.validator.checkers.CyclicDependencyChecker;
import ql.validator.checkers.ExpressionChecker;
import ql.validator.checkers.QuestionDuplicationChecker;


/**
 * Performs validation for the AST by checking invalid references, expressions, duplications, and cyclic dependencies
 */
public class Validator {

    private final Checker questionDuplicationChecker;
    private final Checker expressionChecker;
    private final Checker cyclicDependencyChecker;

    public Validator() {
        questionDuplicationChecker = new QuestionDuplicationChecker();
        expressionChecker = new ExpressionChecker();
        cyclicDependencyChecker = new CyclicDependencyChecker();
    }

    public boolean passesTypeChecks(Form form) {

        //TODO: passesTests return issuetracker. No global tracker, no singleton

        //Check for duplicate question identifiers and labels
        if (!questionDuplicationChecker.passesTests(form)) {
            return false;
        }

        //Check for reference to undefined questions, non-boolean conditionals, and invalid operand types
        if (!expressionChecker.passesTests(form)) {
            return false;
        }

        //Check cyclic dependencies between questions
        if (!cyclicDependencyChecker.passesTests(form)) {
            return false;
        }

        return true;
    }

}
