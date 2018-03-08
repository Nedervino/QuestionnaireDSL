package ql.validator.issuetracker;

import ql.ast.SourceLocation;

public abstract class Issue {

    protected final SourceLocation sourceLocation;
    protected final String message;

    public Issue(SourceLocation sourceLocation, String message) {
        this.sourceLocation = sourceLocation;
        this.message = message;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    public String getMessage() {
        return message;
    }

    public abstract String toString();

}