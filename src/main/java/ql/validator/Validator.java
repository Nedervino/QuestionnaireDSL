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

    //TODO: passesTests return issuetracker.

    public static boolean passesChecks(Form form) {

        Checker questionDuplicationChecker = new QuestionDuplicationChecker();
        Checker expressionChecker = new ExpressionChecker();
        Checker cyclicDependencyChecker = new CyclicDependencyChecker();

        //Check for duplicate question identifiers and labels
        if(detectsErrors(questionDuplicationChecker, form)) return false;

        //Check for reference to undefined questions, non-boolean conditionals, and invalid operand types
        if(detectsErrors(expressionChecker, form)) return false;

        //Check cyclic dependencies between questions
        if(detectsErrors(cyclicDependencyChecker, form)) return false;

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
