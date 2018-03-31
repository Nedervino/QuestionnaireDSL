package ql.validator;

import ql.ast.Form;
import ql.validator.checkers.Checker;
import ql.validator.checkers.CyclicDependencyChecker;
import ql.validator.checkers.ExpressionChecker;
import ql.validator.checkers.QuestionDuplicationChecker;


/**
 * The validator detects:
 * <p>
 * - reference to undefined questions
 * - duplicate question declarations with different types
 * - conditions that are not of the type boolean
 * - operands of invalid type to operators
 * - cyclic dependencies between questions
 * - duplicate labels (warning)
 */
public class Validator {

    public static boolean passesChecks(Form form) {

        Checker questionDuplicationChecker = new QuestionDuplicationChecker();
        Checker expressionChecker = new ExpressionChecker();
        Checker cyclicDependencyChecker = new CyclicDependencyChecker();

        //Check for duplicate question identifiers and labels
        if (detectsErrors(questionDuplicationChecker, form)) return false;

        //Check for reference to undefined questions, non-boolean conditionals, and invalid operand types
        if (detectsErrors(expressionChecker, form)) return false;

        //Check cyclic dependencies between questions
        if (detectsErrors(cyclicDependencyChecker, form)) return false;

        return true;
    }

    private static boolean detectsErrors(Checker checker, Form form) {
        if (checker.passesTests(form)) {
            checker.logWarnings();
            return false;
        }
        checker.logErrors();
        return true;
    }

}
