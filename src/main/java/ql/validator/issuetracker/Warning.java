package ql.validator.issuetracker;

import ql.ast.SourceLocation;

public class Warning extends Issue {

    public Warning(SourceLocation sourceLocation, String message) {
        super(sourceLocation, message);
    }

    @Override
    public String toString() {
        return String.format("Warning: %s: %s", this.sourceLocation.toString(), this.message);
    }

}
