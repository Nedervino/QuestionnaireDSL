package issuetracker;

import ql.ast.SourceLocation;

public class Error extends Issue {

    public Error(SourceLocation sourceLocation, String message) {
        super(sourceLocation, message);
    }

    @Override
    public String toString() {
        return String.format("Error: %s: %s", this.getSourceLocation().toString(), this.getMessage());
    }

}
