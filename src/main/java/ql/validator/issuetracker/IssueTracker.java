package ql.validator.issuetracker;


import java.util.ArrayList;
import java.util.List;

/**
 * Stores validation errors and warnings
 */
public class IssueTracker {

    private static IssueTracker issueTracker;

    private final List<Error> errors;
    private final List<Warning> warnings;

    //
    // private issuetracker() {}
    //
    // public static issuetracker getIssueTracker() {
    //     if(issueTracker == null) {
    //         issueTracker = new issuetracker();
    //     }
    //     return issueTracker;
    // }

    public IssueTracker() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

    public void addWarning(int line, int column, String warningMessage) {
        Warning warning = new Warning(line, column, warningMessage);
        warnings.add(warning);
    }


    public void addError(int line, int column, String errorMessage) {
        Error error = new Error(line, column, errorMessage);
        errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public List<Warning> getWarnings() {
        return warnings;
    }

}
