package issuetracker;


import ql.ast.ASTNode;
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
    private final List<Error> errors;
    private final List<Warning> warnings;


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

    public void addWarning(ASTNode node, String warningMessage) {
        SourceLocation sourceLocation = node.getSourceLocation();
        addWarning(sourceLocation, warningMessage);
    }


    public void addError(ASTNode node, String errorMessage) {
        SourceLocation sourceLocation = node.getSourceLocation();
        addError(sourceLocation, errorMessage);
    }

    public void reset() {
        errors.clear();
        warnings.clear();
    }

    public void logErrors() {
        errors.forEach(error -> LOGGER.severe(error.getFormattedMessage()));
    }

    public void logWarnings() {
        warnings.forEach(warning -> LOGGER.warning(warning.getFormattedMessage()));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<Error> getErrors() {
        return new ArrayList<>(errors);
    }

    public List<Warning> getWarnings() {
        return new ArrayList<>(warnings);
    }

}
