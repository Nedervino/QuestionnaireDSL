package issuetracker;


import ql.ast.SourceLocation;
import ql.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Stores validation errors and warnings
 */
public class IssueTracker {

    private final static Logger LOGGER = Logger.getLogger(Validator.class.getName());
    private static IssueTracker issueTracker;
    private final List<Error> errors;
    private final List<Warning> warnings;


    private IssueTracker() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

    public static IssueTracker getIssueTracker() {
        if (issueTracker == null) {
            issueTracker = new IssueTracker();
        }
        return issueTracker;
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

    public void logErrors() {
        errors.forEach(error -> LOGGER.severe(error.toString()));
    }

    public void logWarnings() {
        warnings.forEach(warning -> LOGGER.warning(warning.toString()));
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public List<Error> getErrors() {
        return new ArrayList<>(errors);
    }

    public List<Warning> getWarnings() {
        return new ArrayList<>(warnings);
    }

}
