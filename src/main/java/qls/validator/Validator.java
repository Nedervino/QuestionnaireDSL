package qls.validator;

import ql.validator.checkers.Checker;
import qls.ast.Stylesheet;
import qls.validator.checkers.QuestionReferenceChecker;
import qls.validator.checkers.WidgetCompatibilityChecker;

/**
 * The validator detects:
 * <p>
 * - no references to questions that are not in the QL program
 * - all questions of the QL program are placed by the QLS program.
 * - (default) widget assignments are compatible with question types (e.g. no radio button for integer widgets).
 * - you cannot place a single question multiple times.
 */
public class Validator {

    public static boolean passesChecks(Stylesheet stylesheet) {

        Checker questionReferenceChecker = new QuestionReferenceChecker();
        Checker widgetCompatibilityChecker = new WidgetCompatibilityChecker();

        //Check for missing question references, and references to undefined or the same questions
        if (detectsErrors(questionReferenceChecker, stylesheet)) return false;

        //Check for widget assignments which are incompatible with question types
        if (detectsErrors(widgetCompatibilityChecker, stylesheet)) return false;

        return true;
    }

    private static boolean detectsErrors(Checker checker, Stylesheet stylesheet) {
        if (checker.passesTests(stylesheet)) {
            checker.logWarnings();
            return false;
        }
        checker.logErrors();
        return true;
    }

}
