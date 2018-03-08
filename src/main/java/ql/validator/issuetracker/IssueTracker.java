package ql.validator.issuetracker;


import ql.ast.SourceLocation;

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

    public void addWarning(SourceLocation sourceLocation, String warningMessage) {
        Warning warning = new Warning(sourceLocation, warningMessage);
        warnings.add(warning);
    }


    public void addError(SourceLocation sourceLocation, String errorMessage) {
        Error error = new Error(sourceLocation, errorMessage);
        errors.add(error);
    }

    public void reset() {
        errors.clear();
        warnings.clear();
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public List<Warning> getWarnings() {
        return warnings;
    }

}
