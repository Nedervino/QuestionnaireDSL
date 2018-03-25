package issuetracker;

import ql.ast.SourceLocation;

public abstract class Issue {

    private final SourceLocation sourceLocation;
    private final String message;

    protected Issue(SourceLocation sourceLocation, String message) {
        this.sourceLocation = sourceLocation;
        this.message = message;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    public String getMessage() {
        return message;
    }

    public abstract String getFormattedMessage();

}