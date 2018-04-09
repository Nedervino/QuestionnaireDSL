package ql.validator;

import ql.ast.Form;
import ql.validator.checkers.*;


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
public class FormValidator {

    public static boolean passesChecks(Form form) {

        Checker questionDuplicationChecker = new QuestionDuplicationChecker(form);
        Checker expressionChecker = new ExpressionChecker(form);
        Checker cyclicDependencyChecker = new CyclicDependencyChecker(form);
        Checker forwardReferenceChecker = new ForwardReferenceChecker(form);

        //Check for duplicate question identifiers and labels
        if (detectsErrors(questionDuplicationChecker)) return false;

        //Check for reference to undefined questions, non-boolean conditionals, and invalid operand types
        if (detectsErrors(expressionChecker)) return false;

        //Check cyclic dependencies between questions
        if (detectsErrors(cyclicDependencyChecker)) return false;

        //Checks for forward references to questions
        if (detectsErrors(forwardReferenceChecker)) return false;

        return true;
    }

    private static boolean detectsErrors(Checker checker) {
        if (checker.passesTests()) {
            checker.logWarnings();
            return false;
        }
        checker.logErrors();
        return true;
    }

}
