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

        Checker questionDuplicationChecker = new QuestionDuplicationChecker();
        Checker expressionChecker = new ExpressionChecker();
        Checker cyclicDependencyChecker = new CyclicDependencyChecker();
        Checker forwardReferenceChecker = new ForwardReferenceChecker();

        //Check for duplicate question identifiers and labels
        if (detectsErrors(questionDuplicationChecker, form)) return false;

        //Check for reference to undefined questions, non-boolean conditionals, and invalid operand types
        if (detectsErrors(expressionChecker, form)) return false;

        //Check cyclic dependencies between questions
        if (detectsErrors(cyclicDependencyChecker, form)) return false;

        //Checks for forward references to questions
        if(detectsErrors(forwardReferenceChecker, form)) return false;

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
