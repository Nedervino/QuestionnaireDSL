package ql.validator;

import ql.ast.Form;
import ql.validator.checkers.Checker;
import ql.validator.checkers.CyclicDependencyChecker;
import ql.validator.checkers.ExpressionChecker;
import ql.validator.checkers.QuestionDuplicationChecker;


/**
 * Performs validation for the AST by checking invalid references, expressions, duplications, and cyclic dependencies
 */
public class Validator {

    private static final Checker questionDuplicationChecker = new QuestionDuplicationChecker();
    private static final Checker expressionChecker = new ExpressionChecker();
    private static final Checker cyclicDependencyChecker = new CyclicDependencyChecker();

    public static boolean passesTypeChecks(Form form) {

        //TODO: passesTests return issuetracker. No global tracker, no singleton

        //Check for duplicate question identifiers and labels
        if (!questionDuplicationChecker.passesTests(form)) {
            questionDuplicationChecker.logErrors();
            return false;
        }

        //Check for reference to undefined questions, non-boolean conditionals, and invalid operand types
        if (!expressionChecker.passesTests(form)) {
            expressionChecker.logErrors();
            return false;
        }

        //Check cyclic dependencies between questions
        if (!cyclicDependencyChecker.passesTests(form)) {
            cyclicDependencyChecker.logErrors();
            return false;
        }

        logWarnings();

        return true;
    }

    private static void logWarnings() {
        questionDuplicationChecker.logWarnings();
        expressionChecker.logWarnings();
        cyclicDependencyChecker.logWarnings();
    }

}
